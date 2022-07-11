package com.sparta.controller;

public class ReadWriteCon {
    public void readingRecords1(){

        IOReader.readFile("EmployeeRecords1.csv");
    }
    public void readingRecords2(){
        IOReader.readFile("EmployeeRecords2.csv");
    }

    public void readingRecordsLarge(){
        IOReader.readFile("EmployeeRecordsLarge");
    }




}
