package com.meraj.library_management_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.meraj.library_management_app.books.BookAdapter;
import com.meraj.library_management_app.books.BookModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int [] images= {
            R.drawable.a_philosopy_of_software_desine,R.drawable.clean_code,R.drawable.refactoring,R.drawable.the_mythical_man_month,R.drawable.the_pragmatic_progmmer,
    };
    String [] title,desc;

    ArrayList<BookModel>booklist;
    RecyclerView recyclerView;
    private SearchView searchView;

    BookAdapter bookAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        Recycleview
        recyclerView.findViewById(R.id.recyclerview_book);


        //adeptar

        title=getResources().getStringArray(R.array.Book_Name);

        bookAdapter=new BookAdapter(this,title,images);

        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookAdapter.setOnItemClickLinenr(new BookAdapter.clicklistener() {
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(MainActivity.this, "on item click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position, View v) {

                Toast.makeText(MainActivity.this, "on item long click", Toast.LENGTH_SHORT).show();

            }
        });



        //search bar

        searchView.findViewById(R.id.search);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);
                return true;
            }
        });
        booklist =new ArrayList<>();

        booklist.add(new BookModel(R.drawable.google,"era","rafi vai"));
        booklist.add(new BookModel(R.drawable.google,"era","rafi vai"));
        booklist.add(new BookModel(R.drawable.google,"era","rafi vai"));
        booklist.add(new BookModel(R.drawable.google,"era","rafi vai"));



    }

    private void fileList(String text) {
        List<BookModel> filteredList=new ArrayList<>();

        for (BookModel item: booklist)
        {
            if(item.getBookname().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
            if(filteredList.isEmpty())
            {

                Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
            }


        }

    }
}