package com.sparta.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class RemoteConnection implements AutoCloseable {

    public void remoteConnection(){
        Connection conn = DriverManager.getConnection()
    }

    @Override
    public void close() throws Exception {

    }
}
