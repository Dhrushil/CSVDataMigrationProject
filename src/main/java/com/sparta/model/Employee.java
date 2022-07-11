package com.sparta.model;

public class Employee {
    private int employeeID;

    public Employee(int employeeID, String namePrefix, String firstName, char middleInitial, String lastName, char gender, String email, String dateOfBirth, String dateOfJoining, int salary) {
        this.employeeID = employeeID;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
    }

    private String namePrefix;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private char gender;
    private String email;
    private String dateOfBirth;
    private String dateOfJoining;
    private int salary;

    public int getEmployeeID() {
        return employeeID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public int getSalary() {
        return salary;
    }
}
