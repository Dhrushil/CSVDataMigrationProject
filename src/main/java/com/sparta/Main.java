package com.sparta;

import com.sparta.controller.CleaningController;
import com.sparta.model.Employee;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        CleaningController test = new CleaningController();

        HashMap<Integer, Employee> testMap = test.cleanFile();

    }
}