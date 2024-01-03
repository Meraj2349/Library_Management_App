package com.meraj.library_management_app.books;

public class BookModel {
    int bookimag;

    String bookname,otthername;

    public BookModel() {
    }

    public BookModel(int bookimag, String bookname, String otthername) {
        this.bookimag = bookimag;
        this.bookname = bookname;
        this.otthername = otthername;
    }

    public int getBookimag() {
        return bookimag;
    }

    public void setBookimag(int bookimag) {
        this.bookimag = bookimag;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getOtthername() {
        return otthername;
    }

    public void setOtthername(String otthername) {
        this.otthername = otthername;
    }
}
