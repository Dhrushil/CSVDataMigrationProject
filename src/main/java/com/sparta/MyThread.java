package com.sparta;

import com.sparta.controller.CleaningController;
import com.sparta.controller.ReadWriteCon;
import com.sparta.model.Employee;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;

public class MyThread extends Thread{

    //define the Total No.Of Threads needed
    public static final int TOTAL_THREADS = 23;

    public final static Object obj = new Object();

    public volatile static Iterator keyIterator;   // iterate through keys

    CleaningController clean = new CleaningController();

    public static HashMap<Integer, Employee> cMap;

    ReadWriteCon maincon = new ReadWriteCon();

    String fileName;



    int threadNo;
    static volatile int counter = 1;

    public MyThread(int threadNo, String fileName){
        this.threadNo= threadNo;
        this.fileName = fileName;
    }

    @Override
    public void run(){

        try {
            cMap = clean.cleanFile(fileName);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        keyIterator = cMap.keySet().iterator();

        //in a synchronized block to acquire lock
        synchronized (obj) {

            while(counter<=cMap.size()){

                if(counter == threadNo || (counter%TOTAL_THREADS == threadNo) ||
                        ((counter%TOTAL_THREADS == 0) && (TOTAL_THREADS == threadNo))){

                    //Display the output as desired
                   // String s = this.threadNo + " printing" + " " + counter++;
                    counter++;
                    maincon.updateTableWithThreads(cMap.get(keyIterator.next()));


                    obj.notifyAll();
                }else{

                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public static void main (String args[]) throws ParseException {

        /*
         * Creating as many threads as needed.
         */



        for(int i = 1; i<=MyThread.TOTAL_THREADS;i++){
            MyThread th = new MyThread(i,"src/main/resources/csv/EmployeeRecords1.csv");
            th.start();
        }
    }
}
