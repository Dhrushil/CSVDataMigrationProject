package com.sparta;

import com.sparta.controller.CleaningController;
import com.sparta.controller.ReadWriteCon;
import com.sparta.model.Employee;
import com.sparta.view.UserInterfaceView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws ParseException {

        ReadWriteCon maincon = new ReadWriteCon();
        //CleaningController clean = new CleaningController();
        //UserInterfaceView ui = new UserInterfaceView();

        //String fileName = ui.display();
        //HashMap<Integer, Employee> test = clean.cleanFile(fileName);
        //ui.updating(test);
        maincon.table();
        for(int i = 1; i<=MyThread.TOTAL_THREADS;i++){
            MyThread th = new MyThread(i, "src/main/resources/csv/EmployeeRecords1.csv");
            th.start();
        }
//        maincon.updateTable(test);
    }
}