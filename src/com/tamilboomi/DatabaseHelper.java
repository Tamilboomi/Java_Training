package com.tamilboomi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/tamilboomi";

    static final String USERNAME = "root";
    static final String PASSWORD = "";

    Connection conn = null;

    public DatabaseHelper() {
        try {
            Class.forName(JDBC_DRIVER_NAME); // Check whether we have the mysql driver
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); // Setup the connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean executeSQLQuery(String query) {
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if(conn!=null) conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
