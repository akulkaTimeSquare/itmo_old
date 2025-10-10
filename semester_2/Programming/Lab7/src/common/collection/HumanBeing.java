package common.collection;

import common.exceptions.InvalidDataException;
import common.exceptions.NullDataException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    /** HumanBeing is self building class with automatic checker in field setting */
    @Serial
    private static final long serialVersionUID = 1L;
    private final LinkedHashMap<String, String> invTexts = new LinkedHashMap<>(){
        {
            put("name", "Введите имя");
            put("coordinates", "Введите координаты (x - long, y - Integer)");
            put("realHero", "Реальный персонаж (да, нет)?");
            put("hasToothpick", "Есть ли зубочистка (да, нет)?");
            put("impactSpeed", "Введите скорость (<=915)");
            put("soundtrackName", "Какая любимая песня?");
            put("minutesOfWaiting", "Введите минуты ожидания (int+)");
            put("mood", "Настроение (sadness, gloom, apathy, frenzy)");
            put("car", "Введите название машины");
        }
    };
    private final ArrayList<String> args = new ArrayList<>(invTexts.keySet());

    /** field id is not null, id > 0, is unique, automatic generated */
    private Long id;
    /** field name is not null */
    private String name;
    /** filed coordinates is not null */
    private Coordinates coordinates;
    /** field creation date is not null, automatic generated */
    private final java.time.LocalDateTime creationDate;
    private boolean realHero;
    private boolean hasToothpick;
    /** max value of impactSpeed field is 915, impactSpeed is not null */
    private Integer impactSpeed;
    /** field soundtrackName is not null */
    private String soundtrackName;
    private int minutesOfWaiting;
    /** field mood is not null */
    private Mood mood;
    /** field car is not null */
    private Car car;

    private int userId;

    public HumanBeing(long id, LocalDateTime creationDate) throws InvalidDataException {
        if (id == 0) this.id = (new Random()).nextLong(1, 1000);
        else this.id = id;
        if (creationDate.isBefore(LocalDateTime.parse("2023-01-01T00:00:00")) ||
                creationDate.isAfter(LocalDateTime.now()))
            throw new InvalidDataException("Некорректное время создания");
        this.creationDate = creationDate;
    }

    public HumanBeing(long id) {
        if (id == 0) this.id = (new Random()).nextLong(1, 1000);
        else this.id = id;
        this.creationDate = LocalDateTime.now();
    }

    public HumanBeing() {
        this.id = (new Random()).nextLong(1, 1000);
        this.creationDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) throws InvalidDataException {
        if (name == null)
            throw new NullDataException("Аргументом не может служить null!");
        if (name.equals(""))
            throw new InvalidDataException("Имя не должно быть пустым!");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(String coordinates) throws InvalidDataException {
        if (coordinates == null)
            throw new NullDataException("Аргументом не может служить null!");
        if (!coordinates.matches("-?\\d*[.,]?\\d* -?\\d*[.,]?\\d*"))
            throw new InvalidDataException("Несоответствие данных (x - double, y - float, y <= 87)!");
        try {
            String[] coors = coordinates.split(" ");
            double x = Double.parseDouble(coors[0]);
            float y = Float.parseFloat(coors[1]);
            this.coordinates = new Coordinates(x, y);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Несоответствие данных (x - double, y - float, y <= 87)!");
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setRealHero(String realHero) throws InvalidDataException {
        if (realHero == null)
            throw new NullDataException("Аргументом не может служить null!");
        if (!(realHero.equals("да") || realHero.equals("нет") ||
                realHero.equals("yes") || realHero.equals("no")))
            throw new InvalidDataException("Введите да/нет!");
        this.realHero = (realHero.equals("да") || realHero.equals("yes"));
    }

    public boolean isRealHero() {
        return realHero;
    }

    public void setHasToothpick(String hasToothpick) throws InvalidDataException {
        if (hasToothpick == null)
            throw new NullDataException("Аргументом не может служить null!");
        if (!(hasToothpick.equals("да") || hasToothpick.equals("нет") ||
                hasToothpick.equals("yes") || hasToothpick.equals("no")))
            throw new InvalidDataException("Введите да/нет!");
        this.hasToothpick = hasToothpick.equals("да");
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }

    public void setImpactSpeed(String impactSpeed) throws InvalidDataException{
        if (impactSpeed == null)
            throw new NullDataException("Аргументом не может служить null!");
        if (!impactSpeed.matches("-?\\d*"))
            throw new InvalidDataException("Введите число!");
        try {
            int is = Integer.parseInt(impactSpeed);
            if (is > 915) throw new InvalidDataException("Число не должно превышать 915!");
            this.impactSpeed = is;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Несоответствие данных (ImpactSpeed - Integer)!");
        }
    }

    public Integer getImpactSpeed() {
        return impactSpeed;
    }

    public void setSoundtrackName(String soundtrackName) throws InvalidDataException {
        if (soundtrackName == null)
            throw new NullDataException("Аргументом не может служить null!");
        if (soundtrackName.isEmpty())
            throw new InvalidDataException("Название не должно быть пустым!");
        this.soundtrackName = soundtrackName;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public void setMinutesOfWaiting(String waitingMinutes) throws InvalidDataException {
        if (waitingMinutes == null)
            throw new NullDataException("Аргументом не может служить null!");
        if (!waitingMinutes.matches("\\d*")) {
            throw new InvalidDataException("Несоответствие данных (minutesOfWaiting - int+)");
        }
        try {
            this.minutesOfWaiting = Integer.parseInt(waitingMinutes);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Несоответствие данных (minutesOfWaiting - int)");
        }
    }

    public int getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public void setMood(String moodText) throws InvalidDataException {
        if (moodText == null)
            throw new NullDataException("Аргументом не может служить null!");
        try {
            mood = Mood.valueOf(moodText.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Несоответствие данных (sadness, gloom, apathy, frenzy)");
        }
    }

    public Mood getMood() {
        return mood;
    }

    public void setCar(String carText) throws InvalidDataException {
        if (carText == null)
            throw new NullDataException("Аргументом не может служить null!");
        if (carText.equals(""))
            throw new InvalidDataException("Название не должно быть пустым!");
        car = new Car(carText);
    }

    public Car getCar() {
        return car;
    }

    public boolean setArgument(String argument, String value) {
        if (!invTexts.containsKey(argument)) return false;
        boolean flag = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (flag) {
            if (value == null)
                System.out.print("\t" + invTexts.get(argument) + ": ");
            try {
                String val = (value == null ? "" : value);
                System.out.print("\t" + invTexts.get(argument) + ": ");
                if (val.isEmpty())
                    val = br.readLine();
                if (val == null) return false;
                val = val.strip();
                if (val.equals("exit")) return false;
                setArgumentSwitch(argument, val);
                flag = false;
            } catch (InvalidDataException e) {
                System.out.println("\t" + e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public boolean setArgument(String argument) {
        return setArgument(argument, "");
    }

    public void setArgumentSwitch(String argument, String value) throws InvalidDataException {
        switch (argument) {
            case "name" -> this.setName(value);
            case "coordinates" -> this.setCoordinates(value);
            case "realHero" -> this.setRealHero(value);
            case "hasToothpick" -> this.setHasToothpick(value);
            case "impactSpeed" -> this.setImpactSpeed(value);
            case "soundtrackName" -> this.setSoundtrackName(value);
            case "minutesOfWaiting" -> this.setMinutesOfWaiting(value);
            case "mood" -> this.setMood(value);
            case "car" -> this.setCar(value);
        }
    }

    public boolean buildElement(ArrayList<String> values) {
        if (values != null && values.size() != getArgs().size()) return false;
        ArrayList<String> args = getArgs();
        for (int i = 0; i < args.size(); i++) {
            if (!(values == null ?
                    setArgument(args.get(i)) :
                    setArgument(args.get(i), values.get(i))))
                return false;
        }
        return true;
    }

    public boolean buildElement() {
        return buildElement(null);
    }

    public ArrayList<String> getArgs() {
        return args;
    }

    public Object getArgSwitch(String argument) {
        return switch (argument) {
            case "name" -> getName();
            case "coordinates" -> getCoordinates();
            case "realHero" -> this.isRealHero();
            case "hasToothpick" -> this.isHasToothpick();
            case "impactSpeed" -> this.getImpactSpeed();
            case "soundtrackName" -> this.getSoundtrackName();
            case "minutesOfWaiting" -> this.getMinutesOfWaiting();
            case "mood" -> this.getMood();
            case "car" -> this.getCar();
            default -> null;
        };
    }

    @Override
    public int compareTo(HumanBeing el) {
        int result = this.name.compareTo(el.name);
        if (result == 0) {
            result = (this.realHero ? 1 : 0) - (el.isRealHero() ? 1 : 0);
            if (result == 0) {
                result = (this.hasToothpick ? 1 : 0) - (el.isHasToothpick() ? 1 : 0);
                if (result == 0) {
                    result = this.coordinates.compareTo(el.getCoordinates());
                    if (result == 0) {
                        result = this.mood.compareTo(el.getMood());
                        if (result == 0) {
                            result = this.car.compareTo(el.getCar());
                            if (result == 0) {
                                result = this.impactSpeed.compareTo(el.getImpactSpeed());
                                if (result == 0) {
                                    result = this.minutesOfWaiting - el.getMinutesOfWaiting();
                                    if (result == 0)
                                        result = this.soundtrackName.compareTo(el.getSoundtrackName());
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public String simpleView(){
        return "{id: " + id + ", name: " + name + ", realHero: " + (realHero ? "да" : "нет") + "}";
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** String view of the element */
    @Override
    public String toString() {
        return ("id: " + id + "\n" +
                "\tname: " + name + "\n" +
                "\tcreationDate: " + creationDate.getDayOfMonth() + " " + creationDate.getMonth() + " " +
                creationDate.getYear() + " " +
                (creationDate.getHour() < 10 ? "0" : "") + creationDate.getHour() + ":" +
                (creationDate.getMinute() < 10 ? "0" : "") + creationDate.getMinute() + ":" +
                (creationDate.getSecond() < 10 ? "0" : "") + creationDate.getSecond() + "\n" +
                "\trealHero: " + (this.realHero ? "да" : "нет") + "\n" +
                "\thasToothpick: " + (this.hasToothpick ? "да" : "нет") + "\n" +
                "\tcoordinates: " + coordinates + "\n" +
                "\tmood: " + mood + "\n" +
                "\tcar: " + car + "\n" +
                "\timpactSpeed: " + impactSpeed + "\n" +
                "\tminutesOfWaiting: " + minutesOfWaiting + "\n" +
                "\tsoundtrackName: " + soundtrackName + "\n");
    }
}
