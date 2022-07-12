package com.sparta.controller;

import com.sparta.model.Employee;
import com.sparta.view.UserInterfaceView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CleaningController {

    private int numBadEmails = 0;
    private int numBadDOJ = 0;
    private int numBadDOB = 0;
    private int numBadGender = 0;

    public int getNumBadEmails() {
        return numBadEmails;
    }

    public int getNumBadDOJ() {
        return numBadDOJ;
    }

    public int getNumBadDOB() {
        return numBadDOB;
    }

    public int getNumBadGender() {
        return numBadGender;
    }

    UserInterfaceView userInterface = new UserInterfaceView();

    public HashMap<Integer, Employee> cleanFile() throws ParseException {
        HashMap<Integer, Employee> clean = IOReader.readFile("src/main/resources/csv/EmployeeRecords2.csv");
        List<Integer> elementsToRemove = new ArrayList<>();
        for (Integer key : clean.keySet()) {
            if (!isDateValid(clean.get(key).getDateOfBirth())) {
                numBadDOB++;
                elementsToRemove.add(key);
                continue;
            }
            if (!isDateOfJoinValid(clean.get(key).getDateOfBirth(), clean.get(key).getDateOfJoining())) {
                numBadDOJ++;
                elementsToRemove.add(key);
                continue;
            }
            if (!isValidEmail(clean.get(key).getEmail())) {
                numBadEmails++;
                elementsToRemove.add(key);
                continue;
            }
            if (!isValidGender(clean.get(key).getGender())) {
                numBadGender++;
                elementsToRemove.add(key);
                continue;
            }
        }

        for (Integer x : elementsToRemove) {
            clean.remove(x);
        }
        userInterface.displayReading(clean, (numBadDOB+numBadEmails+numBadGender+numBadDOJ));
        return clean;

    }

    private boolean isDateValid(Date d) throws ParseException {
        Date min = new SimpleDateFormat("MM/dd/yyyy").parse("1/29/1900");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date max = new Date();

        return d.after(min) && d.before(max);
    }

    private boolean isDateOfJoinValid(Date dob, Date doj) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date current = new Date();

        return doj.after(dob) && doj.before(current);
    }

    private boolean isValidEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean isValidGender(char gender) {
        if (gender == 'F' || gender =='M') return true;
        return false;
    }
}
