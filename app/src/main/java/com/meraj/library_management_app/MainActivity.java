package com.meraj.library_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.meraj.library_management_app.books.BookModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<BookModel>booklist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booklist =new ArrayList<>();

        booklist.add(new BookModel(R.drawable.download,"era","rafi vai"));
        booklist.add(new BookModel(R.drawable.download,"era","rafi vai"));
        booklist.add(new BookModel(R.drawable.download,"era","rafi vai"));
        booklist.add(new BookModel(R.drawable.download,"era","rafi vai"));

    }
}