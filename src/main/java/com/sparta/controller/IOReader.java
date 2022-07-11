package com.sparta.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class IOReader {
    public static void readFile(String filename) {

        try {


            FileReader fileReader = new FileReader(filename);



        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
