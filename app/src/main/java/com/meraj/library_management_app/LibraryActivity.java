package com.meraj.library_management_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.meraj.library_management_app.books.BookAdapter2;
import com.meraj.library_management_app.books.BookModel;
import com.meraj.library_management_app.databinding.ActivityLibraryBinding;

import java.util.ArrayList;
import java.util.Objects;

public class LibraryActivity extends AppCompatActivity {
    ArrayList<BookModel> booklist;
  ActivityLibraryBinding binding;
//meraj
    //BookAdapter2 newbook= new BookAdapter2();
//meraj


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLibraryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setTitle("Library Activity");
        // meraj//

        binding.search.clearFocus();

        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filreList(newText);
                return true;
            }
        });
        //meraj//



        booklist =new ArrayList<BookModel>();

        booklist.add(new BookModel(R.drawable.a_philosopy_of_software_desine,"A philosopy of software desine"," how to decompose complex software systems into modules (such as classes and methods) that can be implemented relatively independently"));
        booklist.add(new BookModel(R.drawable.clean_code,"clean code"," a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent "));
        booklist.add(new BookModel(R.drawable.refactoring,"Refactoring","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.the_pragmatic_progmmer,"the_pragmatic_progmmer","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.a_philosopy_of_software_desine,"A philosopy of software desine","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.clean_code,"clean code","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.refactoring,"Refactoring","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.the_pragmatic_progmmer,"the_pragmatic_progmmer","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.a_philosopy_of_software_desine,"A philosopy of software desine","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.clean_code,"clean code","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.refactoring,"Refactoring","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.the_pragmatic_progmmer,"the_pragmatic_progmmer","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.a_philosopy_of_software_desine,"A philosopy of software desine","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.clean_code,"clean code","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.refactoring,"Refactoring","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));
        booklist.add(new BookModel(R.drawable.the_pragmatic_progmmer,"the_pragmatic_progmmer","a book that focuses on writing code that is well-designed, easy to read, and expresses the author's intent"));




        BookAdapter2 adapter2=new BookAdapter2(LibraryActivity.this,booklist);
        binding.recBook.setAdapter(adapter2);
        binding.recBook.setLayoutManager(new LinearLayoutManager(this));
    }
//meraj
    private void filreList(String newText) {

         ArrayList<BookModel> bookModelsItem=new ArrayList<>();

        for (BookModel books: booklist) {

            if(books.getBookname().toLowerCase().contains(newText.toLowerCase())){
                bookModelsItem.add(books);
            }

        }

        BookAdapter2 adapter2 = new BookAdapter2(LibraryActivity.this, booklist);
        binding.recBook.setAdapter(adapter2);
        binding.recBook.setLayoutManager(new LinearLayoutManager(this));

//      Inside the filreList method
        adapter2.FilterList(bookModelsItem);

        //meraj
    }
}