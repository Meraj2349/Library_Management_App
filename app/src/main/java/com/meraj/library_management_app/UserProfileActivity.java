package com.meraj.library_management_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity {

    private TextView textViewWelcome,textViewFullname,textViewEmail,textViewDob,textViewGender,textViewmoblile;
    ProgressBar progressBar;
    private String fullmane,email,dob,gender,mobile;
    private ImageView imageView;

    FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");
        setContentView(R.layout.activity_user_profile);
        textViewWelcome=findViewById(R.id.textView_show_welcome);
        textViewFullname=findViewById(R.id.textView_show_full_name);
        textViewEmail=findViewById(R.id.textView_show_email);
        textViewDob=findViewById(R.id.textView_show_dob);
        textViewGender=findViewById(R.id.textView_show_gender);
        textViewmoblile=findViewById(R.id.textView_show_mobile);
        progressBar =findViewById(R.id.progress_bar);
        //image uplode button
        imageView =findViewById(R.id.imageView_profile_dp);

        imageView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserProfileActivity.this,Uplode_profile_Activity.class);

                startActivities(new Intent[]{intent});
            }
        });


        authProfile =FirebaseAuth.getInstance();

        FirebaseUser firebaseUser =authProfile.getCurrentUser();

        if (firebaseUser== null)
        {
            Toast.makeText(UserProfileActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
        }
        else
        {

            chackIfEmailVverifide(firebaseUser);
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);

        }

    }

    private void chackIfEmailVverifide(FirebaseUser firebaseUser) {
        if (!firebaseUser.isEmailVerified())
        {
            showAlertDilog();
        }
    }

    private void showAlertDilog() {
        AlertDialog.Builder builder= new AlertDialog.Builder(UserProfileActivity.this);
        builder.setTitle("email not verified");
        builder.setMessage("please verify your email now");
        builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent= new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);



                startActivities(new Intent[]{intent});


            }
        });
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

                Toast.makeText(UserProfileActivity.this, "Something went to wrong ", Toast.LENGTH_SHORT).show();
              progressBar.setVisibility(View.GONE);
            }
        });
    }

    //Action bar menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.menu_refresh)
        {
            //refresh activity
            startActivities(new Intent[]{getIntent()});
            finish();
            overridePendingTransition(0,0);


        } else if (id == R.id.Update_profile) {
            Intent intent= new Intent(UserProfileActivity.this,Uplode_profile_Activity.class);
            startActivities(new Intent[] {intent});
        }
        else if (id == R.id.Update_email) {
            Intent intent= new Intent(UserProfileActivity.this,Uplode_profile_Activity.class);
            startActivities(new Intent[] {intent});
        }
        else if (id == R.id.setting) {
            Intent intent= new Intent(UserProfileActivity.this,Uplode_profile_Activity.class);
            startActivities(new Intent[] {intent});
        }
        else if (id == R.id.change_password) {
            Intent intent= new Intent(UserProfileActivity.this,Uplode_profile_Activity.class);
            startActivities(new Intent[] {intent});
        }
        else if (id == R.id.delete_profile) {
            Intent intent= new Intent(UserProfileActivity.this,Uplode_profile_Activity.class);
            startActivities(new Intent[] {intent});
        }
        else if (id == R.id.log_out) {
            Intent intent= new Intent(UserProfileActivity.this,login.class);
            startActivities(new Intent[] {intent});
        }
        return super.onOptionsItemSelected(item);
    }
}