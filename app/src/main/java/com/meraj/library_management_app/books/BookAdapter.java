package com.meraj.library_management_app.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meraj.library_management_app.R;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    private static clicklistener clicklistener;

    Context context;
    String [] title;

    int [] images;

    public BookAdapter(Context context, String[] title, int[] images) {
        this.context = context;
        this.title = title;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

       View view = layoutInflater.inflate(R.layout.recyclenview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.titletextView.setText(title[i]);
        holder.flagImageView.setImageResource(images[i]);


    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        TextView titletextView;
        ImageView flagImageView;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titletextView=itemView.findViewById(R.id.bookName1);
            flagImageView=itemView.findViewById(R.id.bookImage1);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clicklistener.onItemClick(getAdapterPosition(),v);

        }

        @Override
        public boolean onLongClick(View v) {
            clicklistener.onItemLongClick(getAdapterPosition(),v);
            return false;
        }

        @Override
        public boolean onLongClickUseDefaultHapticFeedback(@NonNull View v) {
            return View.OnLongClickListener.super.onLongClickUseDefaultHapticFeedback(v);
        }
    }

    public interface clicklistener{

        void onItemClick (int position,View v);

        void onItemLongClick (int position,View v);

    }
   public void setOnItemClickLinenr(clicklistener clicklistener)
   {
       BookAdapter.clicklistener=clicklistener;
   }


}
