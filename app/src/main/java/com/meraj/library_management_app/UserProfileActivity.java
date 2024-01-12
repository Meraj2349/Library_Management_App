package com.meraj.library_management_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {

    private TextView textViewWelcome,textViewFullname,textViewEmail,textViewDob,textViewGender,textViewmoblile;
    ProgressBar progressBar;
    private String fullmane,email,dob,gender,mobile;
    private ImageView imageView;

    FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        textViewWelcome=findViewById(R.id.textView_show_welcome);
        textViewFullname=findViewById(R.id.textView_show_full_name);
        textViewEmail=findViewById(R.id.textView_show_email);
        textViewDob=findViewById(R.id.textView_show_dob);
        textViewGender=findViewById(R.id.textView_show_gender);
        textViewmoblile=findViewById(R.id.textView_show_mobile);
        progressBar =findViewById(R.id.progress_bar);

        authProfile =FirebaseAuth.getInstance();

        FirebaseUser firebaseUser =authProfile.getCurrentUser();

        if (firebaseUser== null)
        {
            Toast.makeText(UserProfileActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);

        }

    }

    private void showUserProfile(FirebaseUser firebaseUser) {

        String userId = firebaseUser.getUid();

        DatabaseReference referenceprofile = FirebaseDatabase.getInstance().getReference("register user");
        referenceprofile.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ReadWriteUserDetails readWriteUserDetails = dataSnapshot.getValue(ReadWriteUserDetails.class);
                if (readWriteUserDetails != null)
                {
                    fullmane =firebaseUser.getDisplayName();
                    email=firebaseUser.getEmail();
                    dob =readWriteUserDetails.dob;
                    gender=readWriteUserDetails.gender;


                    textViewWelcome.setText("welcome,"+fullmane+"!");
                    textViewFullname.setText(fullmane);
                    textViewEmail.setText(email);
                    textViewDob.setText(dob);

                    textViewGender.setText(gender);
                    textViewmoblile.setText(mobile);


                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}