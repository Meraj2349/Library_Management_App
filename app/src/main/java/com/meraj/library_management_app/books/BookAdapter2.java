package com.meraj.library_management_app.books;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meraj.library_management_app.R;
import com.meraj.library_management_app.UserProfileActivity;

import java.util.ArrayList;

public class BookAdapter2 extends RecyclerView.Adapter<BookAdapter2.MyViewHolder> {


    Context context;
    public ArrayList<BookModel> list;

    public BookAdapter2(Context context, ArrayList<BookModel> list) {
        this.context = context;
        this.list = list;
    }

    public BookAdapter2() {

    }

    @NonNull
    @Override
    public BookAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(context).inflate(R.layout.recyclenview,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter2.MyViewHolder myViewHolder, int i) {

        BookModel model=list.get(i);

        myViewHolder.imageView.setImageResource(model.getBookimag());
        myViewHolder.bookname.setText(model.getBookname());
        myViewHolder.autherName.setText(model.getOtthername());


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UserProfileActivity.class);
                intent.putExtra("book_name",model.getBookname());
                context.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    // meraj

    public void FilterList(ArrayList<BookModel> filterList)
    {
        this.list=filterList;

       notifyDataSetChanged();
    }

    //meraj

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookname,autherName;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookname=itemView.findViewById(R.id.bookName1);
            autherName=itemView.findViewById(R.id.autherName1);
            imageView=itemView.findViewById(R.id.bookImage1);
        }
    }
}
