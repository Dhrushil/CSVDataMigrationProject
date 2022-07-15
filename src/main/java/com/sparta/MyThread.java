package com.sparta;

import com.sparta.controller.CleaningController;
import com.sparta.model.Employee;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class MyThread extends Thread{

    //define the Total No.Of Threads needed
    public static final int TOTAL_THREADS = 5;

    public final static Object obj = new Object();

    public volatile static Iterator keyIterator;   // iterate through keys

    public static HashMap<Integer, Employee> cMap;




    int threadNo;
    static volatile int counter = 1;

    public MyThread(int threadNo) throws ParseException {
        this.threadNo= threadNo;
        CleaningController clean = new CleaningController();

        cMap = clean.cleanFile("src/main/resources/csv/EmployeeRecords1.csv");

//        Date date = new Date();
//        cMap.put(10, new Employee(10,"d","name1",'s',"dsadsa",'F',"dsadas@sdf.com", date, date, 1000));
//        cMap.put(15, new Employee(15,"d","name2",'s',"dsadsa",'F',"dsadas@sdf.com", date, date, 1000));
//        cMap.put(20, new Employee(20,"d","name3",'s',"dsadsa",'F',"dsadas@sdf.com", date, date, 1000));
//        cMap.put(25, new Employee(25,"d","name4",'s',"dsadsa",'F',"dsadas@sdf.com", date, date, 1000));
//        cMap.put(30, new Employee(30,"d","name5",'s',"dsadsa",'F',"dsadas@sdf.com", date, date, 1000));
//        cMap.put(35, new Employee(35,"d","name6",'s',"dsadsa",'F',"dsadas@sdf.com", date, date, 1000));
//        cMap.put(40, new Employee(40,"d","name7",'s',"dsadsa",'F',"dsadas@sdf.com", date, date, 1000));
//        cMap.put(45, new Employee(45,"d","name8",'s',"dsadsa",'F',"dsadas@sdf.com", date, date, 1000));
//        cMap.put(50, new Employee(50,"d","name9",'s',"dsadsa",'F',"dsadas@sdf.com", date, date, 1000));

        this.keyIterator = cMap.keySet().iterator();

    }

    @Override
    public void run(){

        //in a synchronized block to acquire lock
        synchronized (obj) {

            while(counter<=9){

                if(counter == threadNo || (counter%TOTAL_THREADS == threadNo) ||
                        ((counter%TOTAL_THREADS == 0) && (TOTAL_THREADS == threadNo))){

                    //Display the output as desired
                    String s = this.threadNo + " printing" + " " + counter++;
                    System.out.println(cMap.get(keyIterator.next()).getFirstName());

                    //then
                    //notify
                    obj.notifyAll();
                }else{

                    //current thread not eligible for printing the current counter value, so wait till its notified
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
        for(int i = 1; i<=TOTAL_THREADS;i++){
            MyThread th = new MyThread(i);
            th.start();
        }
    }
}
