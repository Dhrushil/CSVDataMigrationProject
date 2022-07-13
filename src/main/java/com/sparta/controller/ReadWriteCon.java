package com.sparta.controller;

import com.sparta.DatabaseConnection.RemoteConnection;
import com.sparta.model.Employee;

import java.sql.*;
import java.util.HashMap;

public class ReadWriteCon {

    public static final String createtable = "CREATE TABLE Employee_Data " +
            "(Emp_ID INTEGER not NULL," +
            "Name_Prefix varchar(50)," +
            "First_Name varchar(50)," +
            "Middle_Initial varchar(50)," +
            "Last_Name varchar(50)," +
            "Gender varchar(50)," +
            "E-Mail varchar(50)," +
            "Date_of_Birth DATE," +
            "Date_of_Joining DATE," +
            "Salary INTEGER";


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
        HashMap<Integer,Employee> map = new HashMap<>();
        map = readingRecords1();

        try {
            Statement statement = conn.createStatement();
            statement.execute(createtable);
            for(Object o:map.keySet()){
               statement.execute("INSERT INTO Employee_Data ") map.get(o).get
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}

