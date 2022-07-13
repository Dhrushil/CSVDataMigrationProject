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
    private static final String SELECT_USER_BY_ID = "select * from users where Emp_ID = ?";


    private static final String insertqueary = "INSERT INTO Employee_Data (Emp_ID,Name_Prefix,First_Name,Middle_Initial,Last_Name,Gender,E-Mail,Date_of_Birth,Date_of_Joining,Salary INTEGER) VALUES (?,?,?,?,?,?,?,?,?,?)";

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

            conn.close();

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

                int employeeID = rs.getInt("Emp_ID");
                String namePrefix = rs.getString("Name_Prefix");
                String firstName = rs.getString("First_Name");
                String middleInitial = rs.getString("Middle_Initial");
                String lastName = rs.getString("Last_Name");
                String gender = rs.getString("Gender");
                String email = rs.getString("E-Mail");
                Date dateOfBirth = rs.getDate("Date_of_Birth");
                Date dateOfJoining = rs.getDate("Date_of_Joining");
                int salary = rs.getInt("Salary");

                employee = new Employee(employeeID, namePrefix, firstName, middleInitial.charAt(0), lastName, gender.charAt(0), email, dateOfBirth, dateOfJoining, salary);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
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

