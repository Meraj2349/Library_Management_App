package com.meraj.library_management_app;

public class ReadWriteUserDetails {

   public String Fullname,ResisterNumber,mail;

    public ReadWriteUserDetails() {
    }

    public ReadWriteUserDetails(String fullname,String email,String resisterNumber) {

       this.ResisterNumber=resisterNumber;
       this.mail=email;
       this.Fullname=fullname;

    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getResisterNumber() {
        return ResisterNumber;
    }

    public void setResisterNumber(String resisterNumber) {
        ResisterNumber = resisterNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
