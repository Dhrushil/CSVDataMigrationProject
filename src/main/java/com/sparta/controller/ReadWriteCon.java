package com.sparta.controller;

import com.sparta.DatabaseConnection.RemoteConnection;
import com.sparta.model.Employee;

import java.sql.*;
import java.util.HashMap;

public class ReadWriteCon {



    private static final String createtable = "CREATE TABLE Employee_Data (\n" +
            "    employeeID int,\n" +
            "    namePrefix varchar(10),\n" +
            "    firstName varchar(50),\n" +
            "    middleInitial varchar(1),\n" +
            "    lastName varchar(50), \n" +
            "    gender varchar(1),\n" +
            "    email varchar(255), \n" +
            "    dateOfBirth date,\n" +
            "    dateOfJoining date, \n" +
            "    salary int\n" +
            ")";
    private static final String SELECT_USER_BY_ID = "select * from Employee_Data where employeeID = ?";


    private static final String insertqueary = "INSERT INTO Employee_Data (employeeID,namePrefix,firstName,middleInitial,lastName,gender,email,dateOfBirth,dateOfJoining,salary) VALUES (?,?,?,?,?,?,?,?,?,?)";

    private static final String tablecheck = "DROP TABLE IF EXISTS Employee_Data";

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

    public void table() {
        Connection conn = RemoteConnection.getConn();
        try {
            PreparedStatement statement1 = conn.prepareStatement(tablecheck);
            PreparedStatement statement2 = conn.prepareStatement(createtable);
            statement1.executeUpdate();
            statement2.executeUpdate();

            //conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Employee getEmployeeByID(int id) {
        Employee employee = null;
        try(Connection conn = RemoteConnection.getConn();
            PreparedStatement statement = conn.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                int employeeID = rs.getInt("employeeID");
                String namePrefix = rs.getString("namePrefix");
                String firstName = rs.getString("firstName");
                String middleInitial = rs.getString("middleInitial");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                Date dateOfJoining = rs.getDate("dateOfJoining");
                int salary = rs.getInt("salary");

                employee = new Employee(employeeID, namePrefix, firstName, middleInitial.charAt(0), lastName, gender.charAt(0), email, dateOfBirth, dateOfJoining, salary);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }
    public void updateTable(HashMap<Integer,Employee> map){

        try(Connection conn = RemoteConnection.getConn();
            PreparedStatement statement = conn.prepareStatement(insertqueary)) {

            for(Integer o: map.keySet()) {

                statement.setInt(1, map.get(o).getEmployeeID());
                statement.setString(2, map.get(o).getNamePrefix());
                statement.setString(3, map.get(o).getFirstName());
                statement.setString(4, String.valueOf(map.get(o).getMiddleInitial()));
                statement.setString(5, map.get(o).getLastName());
                statement.setString(6, String.valueOf(map.get(o).getGender()));
                statement.setString(7, map.get(o).getEmail());
                statement.setDate(8, new java.sql.Date(map.get(o).getDateOfBirth().getTime()));
                statement.setDate(9, new java.sql.Date(map.get(o).getDateOfBirth().getTime()));
                statement.setInt(10, map.get(o).getSalary());
                statement.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

