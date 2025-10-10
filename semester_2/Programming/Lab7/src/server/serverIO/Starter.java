package server.serverIO;

import java.sql.Connection;
import java.sql.SQLException;

public class Starter {
    private final Connection conn;

    public Starter(Connection conn) {
        this.conn = conn;
    }

    public void start() throws SQLException {
        DataBaseManager.setConn(conn);
        DataBaseManager.createUsers();
        DataBaseManager.createDataBase();
        DataBaseManager.loadDataBase();
    }
}
