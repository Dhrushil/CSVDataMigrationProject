package com.sparta.view;

import com.sparta.model.Employee;

import java.util.HashMap;

public class UserInterfaceView {
    public void displayReading(HashMap<Integer, Employee> clean, int invalidRecords) {
        System.out.println("Number of clean records = " + clean.size());

        System.out.println("Number of invalid records found = " + invalidRecords);

        //System.out.println("Number of duplicate records = ");
    }
}
