package com.sparta.controller;

import com.sparta.model.Employee;
import com.sparta.view.UserInterfaceView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.ParseException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TestClass {

    @Test
    void readFile() {
        HashMap<Integer, Employee> testMap = IOReader.readFile("src/main/resources/csv/EmployeeRecords1.csv");
        Assertions.assertEquals("serafina.bumgarner@exxonmobil.com", testMap.get(198429).getEmail());
        Assertions.assertEquals("Fri Sep 09 00:00:00 BST 1983", testMap.get(198429).getDateOfBirth().toString());
        Assertions.assertEquals("Mrs.", testMap.get(198429).getNamePrefix());
        Assertions.assertEquals("Serafina", testMap.get(198429).getFirstName());
        Assertions.assertEquals('I', testMap.get(198429).getMiddleInitial());
        Assertions.assertEquals("Bumgarner", testMap.get(198429).getLastName());
        Assertions.assertEquals('F', testMap.get(198429).getGender());
        Assertions.assertEquals(198429, testMap.get(198429).getEmployeeID());
        Assertions.assertEquals(69294, testMap.get(198429).getSalary());
        Assertions.assertEquals("Wed Jan 02 00:00:00 GMT 2008", testMap.get(198429).getDateOfJoining().toString());

        //System.out.println(testMap.get(198429).getDateOfBirth().toString());

    }

    @Test
    void testCleanFile(){
        CleaningController clean = new CleaningController();
        try {
            HashMap<Integer, Employee> test = clean.cleanFile("src/main/resources/csv/EmployeeRecords1.csv");

            Assertions.assertEquals("serafina.bumgarner@exxonmobil.com", test.get(198429).getEmail());
            Assertions.assertEquals("Mrs.", test.get(198429).getNamePrefix());
            Assertions.assertEquals("Serafina", test.get(198429).getFirstName());
            Assertions.assertEquals('I', test.get(198429).getMiddleInitial());
            Assertions.assertEquals("Bumgarner", test.get(198429).getLastName());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void writeFile() {

        HashMap<Integer, Employee> testMap = IOReader.readFile("src/main/resources/csv/EmployeeRecords1.csv");
        IOReader.writeFile("newTempFile.csv", testMap);
        Assertions.assertTrue(new File("newTempFile.csv").isFile()); //check if new file exists
    }

    @Test
    void testUI(){
        UserInterfaceView ui = new UserInterfaceView();
        HashMap<Integer, Employee> testMap = IOReader.readFile("src/main/resources/csv/EmployeeRecords1.csv");
        //Assertions.assertEquals(9943, );
        ui.displayReading(testMap, 0);
    }

    @Test
    void testGetEmployee(){
        ReadWriteCon rWC = new ReadWriteCon();
        Employee employee = rWC.getEmployeeByID(198429);
        System.out.println(employee.getSalary());
    }
}