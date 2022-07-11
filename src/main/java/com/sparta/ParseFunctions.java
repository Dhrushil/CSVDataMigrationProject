package com.sparta;

import java.sql.Date;

public class ParseFunctions {
    public boolean bDayParse(String bDate){
        String day = bDate.substring(0,1);
        String month = bDate.substring(3,4);
        String year = bDate.substring(6,9);
        //Date date = new Date();

        //check if birthdate:
        //is not US version
        if (Integer.parseInt(month) > 12){
            return false;
        }
        //is before join date
        //after 1900
        //18 years old

        //Date.valueOf();
        return true;
    }


}
