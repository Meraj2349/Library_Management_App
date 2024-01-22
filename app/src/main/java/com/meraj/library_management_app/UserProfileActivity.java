package com.meraj.library_management_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meraj.library_management_app.books.RegModel;
import com.meraj.library_management_app.databinding.ActivityUserProfileBinding;

import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity {
    ActivityUserProfileBinding binding;

    //private String fullmane,email,ResisterNumber;
    // FirebaseUser firebaseUser;
   // FirebaseAuth authProfile;
   //DatabaseReference referenceprofile;

    String userId;
    FirebaseDatabase databasae;
    FirebaseAuth mAuth;
    Button button;
    RegModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");

        TextView textViewFullName = (TextView) findViewById(R.id.textView_show_full_name);
        TextView textViewEmailId = (TextView) findViewById(R.id.textView_show_email);
        TextView textViewRegisterNumber = (TextView) findViewById(R.id.textview_registerNumber);
        TextView txt_book = (TextView) findViewById(R.id.txt_borrowedbook);



        button = findViewById(R.id.searchProfilebutton);
        model =new RegModel();

//       search activity login
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, LibraryActivity.class);

                startActivity(intent);

                finish();
            }
        });
        // up-lode profile activity
        binding.imageViewProfileDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, Uplode_profile_Activity.class);

                startActivity(intent);

                finish();
            }
        });

        //firebase database
        databasae =FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();

        //select the book

        Intent intent = getIntent();
        if (intent != null) {
            String receivedData = intent.getStringExtra("book_name");

            txt_book.setText(receivedData);

        }

        //database information read


        databasae.getReference().child("User").child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()) {

                    model=dataSnapshot.getValue(RegModel.class);
                    binding.textViewShowFullName.setText(model.getFullName().toString());
                    binding.textviewRegisterNumber.setText(model.getRegester_number().toString());
                    binding.textViewShowEmail.setText(model.getEmail().toString());

                } else {
                    Toast.makeText(UserProfileActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

//        referenceprofile = FirebaseDatabase.getInstance().getReference("registerUser");

//        userId = firebaseUser.getUid();
//

/*
        if (firebaseUser == null) {

            Toast.makeText(UserProfileActivity.this, "firebase null ", Toast.LENGTH_SHORT).show();

        } else {
            referenceprofile.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

             RegModel regModel = dataSnapshot.getValue(RegModel.class);

                    assert regModel != null;
                    String fullName = regModel.fullName;
                    String email = regModel.email;
                    String regNum = regModel.regester_number;

                    textViewEmailId.setText(email);
                    textViewFullName.setText(fullName);
                    textViewRegisterNumber.setText(regNum);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(UserProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
        }
*/
    }
//
           //   chackIfEmailVverifide();
////            progressBar.setVisibility(View.VISIBLE);
//           showUserProfile(firebaseUser);
//           // Toast.makeText(UserProfileActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();

//    private void chackIfEmailVverifide(FirebaseUser firebaseUser) {
//        if (!firebaseUser.isEmailVerified())
//        {
//            showAlertDilog();
//        }
//    }

//    private void showAlertDilog() {
//        AlertDialog.Builder builder= new AlertDialog.Builder(UserProfileActivity.this);
//        builder.setTitle("email not verified");
//        builder.setMessage("please verify your email now");
//        builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                Intent intent= new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
//
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//
//
//                startActivity(intent);
//
//
//
//            }
//        });
//    }

//    private void showUserProfile(FirebaseUser firebaseUser) {
//
//        referenceprofile.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ReadWriteUserDetails readWriteUserDetails = dataSnapshot.getValue(ReadWriteUserDetails.class);
//                if (readWriteUserDetails != null) {
//
//                    fullmane = firebaseUser.getDisplayName();
//                    email = firebaseUser.getEmail();
//                    ResisterNumber = firebaseUser.getPhoneNumber();
//
//                    binding.textViewShowFullName.setText(fullmane);
//                    binding.textViewShowEmail.setText(email);
//                    binding.textviewRegisterNumber.setText(ResisterNumber);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(UserProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

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
            startActivity(intent);
        }
        else if (id == R.id.Update_email) {
            Intent intent= new Intent(UserProfileActivity.this,Uplode_profile_Activity.class);
            startActivity(intent);
        }
        else if (id == R.id.setting) {
            Intent intent= new Intent(UserProfileActivity.this,Uplode_profile_Activity.class);
            startActivity(intent);
        }
        else if (id == R.id.change_password) {
            Intent intent= new Intent(UserProfileActivity.this,Uplode_profile_Activity.class);
            startActivity(intent);
        }
        else if (id == R.id.delete_profile) {
            Intent intent= new Intent(UserProfileActivity.this,Uplode_profile_Activity.class);
             startActivity(intent);
        }
        else if (id == R.id.log_out) {
            Intent intent= new Intent(UserProfileActivity.this,login.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}