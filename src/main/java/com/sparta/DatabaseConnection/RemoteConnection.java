package com.sparta.DatabaseConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class RemoteConnection implements AutoCloseable {

    public static Connection conn = null;

    public static Connection getConn(){
        Properties dbprop = new Properties();
        try {
            dbprop.load(new FileReader("src/main/java/com/sparta/DatabaseConnection/database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(conn == null){
            try {
                conn = DriverManager.getConnection(dbprop.getProperty("db.url"), dbprop.getProperty("db.user"), dbprop.getProperty("db.password"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }
}
