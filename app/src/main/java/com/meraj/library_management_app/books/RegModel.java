package com.meraj.library_management_app.books;

public class RegModel {


    public String regester_number;
    public String fullName;
    public String email;
 String  password;


    public RegModel() {
    }

    public RegModel(String fullName, String email, String regesterNumber, String password) {
        this.fullName = fullName;
        this.email = email;
        this.regester_number = regesterNumber;
        this.password = password;
    }

    public String getRegester_number() {
        return regester_number;
    }

    public void setRegester_number(String regester_number) {
        this.regester_number = regester_number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
