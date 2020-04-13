package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Singleton class for connection to DB
public final class SingletonDB {

    //static variable of type Singleton
    private static SingletonDB dbInstance;

    private static Connection connection;

    //DB connection credentials
    String host = "jdbc:mysql://localhost:3306/";
    String db = "db_examples";
    String user = "moonica13";
    String password = "zW.99/";

    private SingletonDB() { }

    //lazy initialization
    public static SingletonDB getInstance() {
        if (dbInstance == null) {

            dbInstance = new SingletonDB();

        }
        return dbInstance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(host + db, user, password);

            } catch (SQLException e) {
                Logger.getLogger(SingletonDB.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return connection;
    }

}
