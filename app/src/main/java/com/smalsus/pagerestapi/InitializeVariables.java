package com.smalsus.pagerestapi;

public class InitializeVariables
{
    String firstName;
    String lastName;
    String mobileNumber;
    String userName;
    String DateOfBirth;
    int gender;
    String email;
    String password;
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public InitializeVariables(String firstName, String lastName, String mobileNumber, String userName, String dateOfBirth, int gender, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.userName = userName;
        DateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }


}
