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

    public static final String insertqueary = "INSERT INTO Employee_Data (Emp_ID,Name_Prefix,First_Name,Middle_Initial,Last_Name,Gender,E-Mail,Date_of_Birth,Date_of_Joining,Salary INTEGER) VALUES (?,?,?,?,?,?,?,?,?,?)"


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

    public void createTable() {
        Connection conn = RemoteConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement(createtable);
            statement.executeQuery();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void updateTable(HashMap<Integer,Employee> map){
        Connection conn = RemoteConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement(insertqueary);
            for(Integer o: map.keySet()){
                statement.setInt(1,map.get(o).getEmployeeID());
                statement.setString(2,map.get(o).getNamePrefix());
                statement.setString(3,map.get(o).getFirstName());
                statement.setString(4,String.valueOf(map.get(o).getMiddleInitial()));
                statement.setString(5,map.get(o).getLastName());
                statement.setString(6,String.valueOf(map.get(o).getGender()));
                statement.setString(7,map.get(o).getEmail());
                statement.setDate(8, (Date) map.get(o).getDateOfBirth());
                statement.setDate(9,(Date) map.get(o).getDateOfJoining());
                statement.setInt(10,map.get(o).getSalary());
            }
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

