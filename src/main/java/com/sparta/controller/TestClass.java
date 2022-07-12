package com.sparta.controller;

import com.sparta.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
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
        //HashMap<Integer, Employee> test = clean.cleanFile();
    }

    @Test
    void writeFile() {
<<<<<<< Updated upstream

=======
        HashMap<Integer, Employee> testMap = IOReader.readFile("src/main/resources/csv/EmployeeRecords1.csv");
        IOReader.writeFile("newTempFile.csv", testMap);
        Assertions.assertTrue(new File("newTempFile.csv").isFile()); //check if new file exists

        //HashMap<Integer, Employee> newMap = IOReader.readFile("newTempFile.csv");


        /*Assertions.assertEquals("serafina.bumgarner@exxonmobil.com", newMap.get(198429).getEmail());
        Assertions.assertEquals("Fri Sep 09 00:00:00 BST 1983", newMap.get(198429).getDateOfBirth().toString());
        Assertions.assertEquals("Mrs.", newMap.get(198429).getNamePrefix());
        Assertions.assertEquals("Serafina", newMap.get(198429).getFirstName());
        Assertions.assertEquals('I', newMap.get(198429).getMiddleInitial());
        Assertions.assertEquals("Bumgarner", newMap.get(198429).getLastName());
        Assertions.assertEquals('F', newMap.get(198429).getGender());
        Assertions.assertEquals(198429, newMap.get(198429).getEmployeeID());
        Assertions.assertEquals(69294, newMap.get(198429).getSalary());
        Assertions.assertEquals("Wed Jan 02 00:00:00 GMT 2008", newMap.get(198429).getDateOfJoining().toString());*/
>>>>>>> Stashed changes
    }
}