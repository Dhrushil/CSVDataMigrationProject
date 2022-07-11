package com.sparta.controller;

import com.sparta.model.Employee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CleaningController {

    public HashMap<Integer, Employee> cleanFile() {
        HashMap<Integer, Employee> clean = IOReader.readFile("EmployeeRecords1.csv");


        return clean;

    }
}
