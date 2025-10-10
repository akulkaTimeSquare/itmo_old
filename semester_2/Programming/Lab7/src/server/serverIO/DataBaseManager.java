package server.serverIO;

import common.collection.Coordinates;
import common.collection.HumanBeing;
import common.exceptions.InvalidDataException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DataBaseManager {
    private static Connection conn;
    private static final ReentrantLock collectionLock = new ReentrantLock();
    private static String pepper = "@#FJfKi$aed";

    public static long insert(HumanBeing element, int userId) throws SQLException {
        String sql = "INSERT INTO HUMAN_BEING (name, x, y, creationDate, realHero," +
                " hasToothpick, impactSpeed, soundtrackName, minutesOfWaiting, mood, car, user_id)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, element.getName());
        ps.setDouble(2, element.getCoordinates().getX());
        ps.setFloat(3, element.getCoordinates().getY());
        ps.setTimestamp(4, Timestamp.valueOf(element.getCreationDate()));
        ps.setBoolean(5, element.isRealHero());
        ps.setBoolean(6, element.isHasToothpick());
        ps.setInt(7, element.getImpactSpeed());
        ps.setString(8, element.getSoundtrackName());
        ps.setInt(9, element.getMinutesOfWaiting());
        ps.setString(10, element.getMood().toString());
        ps.setString(11, element.getCar().getName());
        ps.setInt(12, userId);
        ps.executeUpdate();
        PreparedStatement ps_return = conn.prepareStatement("SELECT MAX(id) FROM HUMAN_BEING");
        ResultSet rs = ps_return.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public static void clearDataBase(int userId) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM HUMAN_BEING WHERE user_id = " + userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int removeById(long id, int userId) {
        String sql = "DELETE FROM HUMAN_BEING WHERE id = " + id + " AND user_id = " + userId;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int removeById(long id, String login) {
        return removeById(id, getUserId(login));
    }

    public static void updateId(long id, HumanBeing element, String login) throws SQLException {
        int userId = getUserId(login);
        removeById(id, userId);
        insert(element, userId);
    }

    public static void createDataBase() {
        String sql = """
                CREATE TABLE IF NOT EXISTS HUMAN_BEING (
                    id serial primary key,
                    name varchar(25) not null,
                    x double precision,
                    y float check ( y <= 87 ),
                    creationDate timestamp not null check (creationDate > '2023-09-01'),
                    realHero bool,
                    hasToothPick bool,
                    impactSpeed int check (impactSpeed <= 915) not null,
                    soundtrackName varchar(25) not null,
                    minutesOfWaiting int,
                    mood varchar(10) check (mood = 'sadness' or mood = 'gloom' or mood = 'apathy' or mood = 'frenzy'),
                    car varchar(20) not null,
                    user_id integer references users(id) ON DELETE CASCADE
                );
                """;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createUsers() {
        String sql = """
            CREATE TABLE IF NOT EXISTS USERS
            (id SERIAL PRIMARY KEY,
            login VARCHAR(255) UNIQUE NOT NULL,
            password VARCHAR(255) NOT NULL
            );
            """;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getUserId(String login) {
        if (!checkUserLogin(login)) return -1;
        String sql = "SELECT * FROM users WHERE login = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkUserLogin(String login) {
        if (login == null || login.length() < 3) return false;
        String sql = "SELECT * FROM USERS WHERE (login = ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkUserPassword(String password) {
        return password.length() >= 3;
    }

    public static boolean checkUser(String login, String password) {
        if (login == null || password == null) return false;
        String sql = "SELECT * FROM USERS WHERE (login = ?) AND (password = ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] encryptedPassword = messageDigest.digest((password + pepper).getBytes(StandardCharsets.UTF_8));
            ps.setString(2, Arrays.toString(encryptedPassword));
            return ps.executeQuery().next();
        } catch (NoSuchAlgorithmException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertUser(String login, String password){
        String sql = "INSERT INTO USERS (login, password) VALUES (?, ?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] encryptedPassword = messageDigest.digest((password + pepper).getBytes(StandardCharsets.UTF_8));
            ps.setString(2, Arrays.toString(encryptedPassword));
            ps.executeUpdate();
        } catch (NoSuchAlgorithmException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HumanBeing buildElement(ResultSet rs) throws InvalidDataException {
        try {
            HumanBeing el = new HumanBeing(rs.getLong(1), rs.getTimestamp(5).toLocalDateTime());
            el.setName(rs.getString(2));
            el.setCoordinates(new Coordinates(rs.getDouble(3), rs.getFloat(4)).toString());
            el.setRealHero(rs.getBoolean(6) ? "да" : "нет");
            el.setHasToothpick(rs.getBoolean(7) ? "да" : "нет");
            el.setImpactSpeed(String.valueOf(rs.getInt(8)));
            el.setSoundtrackName(rs.getString(9));
            el.setMinutesOfWaiting(String.valueOf(rs.getInt(10)));
            el.setMood(rs.getString(11));
            el.setCar(rs.getString(12));
            return el;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadDataBase() {
        try {
            String sql = "SELECT * FROM human_being";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HumanBeing el = buildElement(rs);
                CollectionManager.addElement(el);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidDataException e) {
            System.out.println("Некорректные поля в базе данных!");
        }
    }

    public static void setConn(Connection conn) {
        DataBaseManager.conn = conn;
    }

    public static ReentrantLock getCollectionLock() {
        return collectionLock;
    }
}