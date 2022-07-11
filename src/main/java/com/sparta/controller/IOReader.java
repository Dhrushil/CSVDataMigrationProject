package com.sparta.controller;

import com.sparta.model.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class IOReader {
    public static HashMap<Integer, Employee> readFile(String filename) {

        HashMap<Integer, Employee> file = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/csv/EmployeeRecords1.csv"));
            reader.readLine(); // this will read the first line
            String data=null;
            while ((data = reader.readLine()) != null){ //loop will run from 2nd line
                String[] dataArray = data.split(",");
                int employeeID = Integer.parseInt(dataArray[0]);
                String namePrefix = dataArray[1];
                String firstName = dataArray[2];
                char middleInitial = dataArray[3].charAt(0);
                String lastName = dataArray[4];
                char gender = dataArray[5].charAt(0);
                String email = dataArray[6];
                String dateOfBirth = dataArray[7];
                String dateOfJoining = dataArray[8];
                int salary = Integer.parseInt(dataArray[9]);

                file.put(employeeID, new Employee(employeeID, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary));
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

}

