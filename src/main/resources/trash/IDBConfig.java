package com.example.managestaff.config;

public interface IDBConfig {
    public static final String HOSTNAME = "localhost";
    public static final String PORT = "3306";
    public static final String DBNAME = "manage_staff_fx";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456789";
    public static final String DB_URL = "jdbc:mysql://" +
            HOSTNAME + ":" + PORT + "/"
            + DBNAME + "?useSSL=false";
}
