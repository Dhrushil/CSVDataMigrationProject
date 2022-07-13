package com.sparta.view;

import com.sparta.controller.ReadWriteCon;
import com.sparta.model.Employee;

import java.util.HashMap;
import java.util.Scanner;

public class UserInterfaceView {
    ReadWriteCon readWrite = new ReadWriteCon();
    Scanner scanner = new Scanner(System.in);
    public String display() {
        String fileName = "";
        System.out.println("Would you like to create a new table? y to continue");
        String choice = scanner.next();

        if (choice.equals("y"))
        {
            readWrite.table();

            System.out.println("Please enter a record you would like to be cleaned. 1, 2 or 3");
            int record = scanner.nextInt();

            switch (record) {
                case 1 -> fileName="src/main/resources/csv/EmployeeRecords1.csv";
                case 2 -> fileName="src/main/resources/csv/EmployeeRecords2.csv";
                case 3 -> fileName="src/main/resources/csv/EmployeeRecordsLarge.csv";
            }
        }
        return fileName;
    }

    public void displayReading(HashMap<Integer, Employee> clean, int invalidRecords) {
        System.out.println("Number of clean records = " + clean.size());

        System.out.println("Number of invalid records found = " + invalidRecords);
    }
}
