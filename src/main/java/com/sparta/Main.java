package com.sparta;

import com.sparta.controller.CleaningController;
import com.sparta.model.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws ParseException {

        CleaningController clean = new CleaningController();

        HashMap<Integer, Employee> test = clean.cleanFile();

        System.out.println(test.get(338634).getDateOfBirth());
    }
}