package com.meraj.library_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class FrontPage extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fontpage);

            //Objects.requireNonNull(getSupportActionBar()).setTitle("library management app");

            Button buttonlogin = findViewById(R.id.login);

            buttonlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FrontPage.this,login.class);
                    startActivities(new Intent[]{intent});
                }
            });

            Button buttonResister = findViewById(R.id.register);

                buttonResister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FrontPage.this,register.class);
                        startActivities(new Intent[]{intent});
                    }
                });
            }


}

