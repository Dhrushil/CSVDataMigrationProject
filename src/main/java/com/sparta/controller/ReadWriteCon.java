package com.sparta.controller;

import com.sparta.DatabaseConnection.RemoteConnection;
import com.sparta.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ReadWriteCon {
    public HashMap<Integer,Employee> readingRecords1() {
        HashMap<Integer,Employee> map = new HashMap<>();
        map = IOReader.readFile("src/main/resources/csv/EmployeeRecords1.csv");
        return map;
    }

    public HashMap<Integer,Employee> readingRecords2() {
        HashMap<Integer,Employee> map = new HashMap<>();
        map = IOReader.readFile("EmployeeRecords2.csv");
        return map;
    }

    public HashMap<Integer,Employee> readingRecordsLarge() {
        HashMap<Integer,Employee> map = new HashMap<>();
        map = IOReader.readFile("EmployeeRecordsLarge");
        return map;
    }

    public void table(String file) {
        Connection conn = RemoteConnection.getConn();
        try {

            HashMap<Integer,Employee> map = new HashMap<>();
            map = readingRecords1();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

