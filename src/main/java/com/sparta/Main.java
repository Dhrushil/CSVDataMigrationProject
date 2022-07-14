package com.sparta;

import com.sparta.controller.CleaningController;
import com.sparta.controller.ReadWriteCon;
import com.sparta.model.Employee;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws ParseException, SQLException {

        ReadWriteCon maincon = new ReadWriteCon();
        CleaningController clean = new CleaningController();
        HashMap<Integer, Employee> test = clean.cleanFile("src/main/resources/csv/EmployeeRecords1.csv");
        maincon.table();
        maincon.updateTable(test);
        Employee test1 = maincon.getEmployeeByID(427608);
        System.out.println(test1.getFirstName());
        maincon.close();
    }
}