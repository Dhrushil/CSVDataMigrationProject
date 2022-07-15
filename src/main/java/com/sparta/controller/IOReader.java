package com.sparta.controller;

import com.sparta.model.Employee;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class IOReader {


    public static HashMap<Integer,Employee> readFile(String filename){
        try {
            HashMap<Integer,Employee> map = new HashMap<Integer,Employee>();
            List<String> ID = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(s -> s.split(",")[0])
                    .toList();

            List<String> namePrefix = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(s -> s.split(",")[1])
                    .toList();
            List<String> firstName = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(s -> s.split(",")[2])
                    .toList();
            List<String> middleInitial = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(s -> s.split(",")[3])
                    .toList();
            List<String> lastName = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(s -> s.split(",")[4])
                    .toList();
            List<String> gender = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(s -> s.split(",")[5])
                    .toList();
            List<String> email = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(s -> s.split(",")[6])
                    .toList();
            List<String> dateOfBirth = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(s -> s.split(",")[7])
                    .toList();
            List<String> dateOfJoining = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(s -> s.split(",")[8])
                    .toList();
            List<String> salary = Files.lines(Path.of(filename))
                    .skip(1)
                    .map(s -> s.split(",")[9])
                    .toList();

            for (int i=0; i<11;i++){
                    map.put(ID.get(i),new Employee( Integer.parseInt(ID.get(i)), namePrefix.get(i),firstName.get(i), middleInitial.get(i).charAt(0),
                            lastName.get(i),gender.get(i).charAt(0), email.get(i),Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(dateOfBirth.get(i)), dateOfJoining.get(i),Integer.parseInt(salary.get(i))));

                }
            return map;





        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }













   /* public static HashMap<Integer, Employee> readFile(String filename) {

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
    */

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

