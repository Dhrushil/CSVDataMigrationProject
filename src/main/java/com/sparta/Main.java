package com.sparta;

import com.sparta.controller.CleaningController;
import com.sparta.controller.ReadWriteCon;
import com.sparta.model.Employee;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws ParseException {

        ReadWriteCon maincon = new ReadWriteCon();

        maincon.table();
    }
}