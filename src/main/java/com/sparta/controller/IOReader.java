package com.sparta.controller;

import com.sparta.model.Employee;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class IOReader {
    public static HashMap<Integer, Employee> readFile(String filename) {

        HashMap<Integer, Employee> file = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
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
                Date dateOfBirth = new SimpleDateFormat("MM/dd/yyyy").parse(dataArray[7]);
                Date dateOfJoining = new SimpleDateFormat("MM/dd/yyyy").parse(dataArray[8]);
                int salary = Integer.parseInt(dataArray[9]);

                file.put(employeeID, new Employee(employeeID, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary));
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
    public static void writeFile(String filename, HashMap<Integer, Employee> hash){
        try {
            String line;
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

                writer.write(String.valueOf(hash));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

