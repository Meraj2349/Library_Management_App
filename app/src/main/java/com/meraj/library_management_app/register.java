package com.meraj.library_management_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
   private EditText editemail;
   private EditText editfullname;
   private EditText editTextpassword;
   private EditText editPhoneNumber;
   private EditText editDateOfBarth;
   private EditText editpasswort;
   private EditText confirmpassword;
    ProgressBar progressBar;
    RadioButton radioButton;
   private RadioGroup radioGroupRegister;
   private static final String TAG = "register";

   private  DatePickerDialog picker;

    Button registerBatton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       Objects.requireNonNull(getSupportActionBar()).setTitle("Register");

       Toast.makeText(register.this, "you can register now", Toast.LENGTH_SHORT).show();

        editfullname      = findViewById(R.id.edittext_fullname);
        editemail         = findViewById(R.id.edittext_id_email);
        editTextpassword  = findViewById(R.id.edittext_password);
        confirmpassword   = findViewById(R.id.edittext_confirm_password);
        editDateOfBarth   = findViewById(R.id.edittext_id_dob);
        editPhoneNumber   = findViewById(R.id.edittext_number);
        radioGroupRegister= findViewById(R.id.radioGroupGender);
        radioGroupRegister.clearCheck();

        editDateOfBarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar= Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        editDateOfBarth.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                    }
                } ,year,month,day);
                picker.show();
            }
        });

        Button buttonRegister= findViewById(R.id.register_bre);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedGenderID= radioGroupRegister.getCheckedRadioButtonId();

                radioGroupRegister=findViewById(selectedGenderID);
                String textfullname = editfullname .getText().toString();
                String TextEmail=editemail.getText().toString();
                String textDob=editDateOfBarth.getText().toString();
                String textMobile=editPhoneNumber.getText().toString();
                String textpwd=editTextpassword.getText().toString();
                String textConPdw=confirmpassword.getText().toString();
                String textGender;

                //valid date mobile number
                String mobileRegex= "[0][1][0-9]{9}";
                Matcher mobileNumMatcher;
                Pattern mobileNumPattern= Pattern.compile(mobileRegex);
                mobileNumMatcher= mobileNumPattern.matcher(textMobile);



                 if (TextUtils.isEmpty(textfullname))
                 {
                     Toast.makeText(register.this, "Please enter your full name", Toast.LENGTH_SHORT).show();
                     editfullname.setError("Full Name is required");
                     editfullname.requestFocus();
                 }

                 else if(TextUtils.isEmpty(TextEmail))
                 {
                     Toast.makeText(register.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                     editemail.setError("Email is required");
                     editemail.requestFocus();
                 }
                 else if(!Patterns.EMAIL_ADDRESS.matcher(TextEmail).matches())
                 {
                     Toast.makeText(register.this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                     editemail.setError("Valid Email is required");
                     editemail.requestFocus();
                 }
                 else if(TextUtils.isEmpty(textDob))
                 {
                     Toast.makeText(register.this, "Please enter your date of birth", Toast.LENGTH_SHORT).show();
                     editDateOfBarth.setError("Date of Birth is required");
                     editDateOfBarth.requestFocus();
                 }
                 else if  (radioGroupRegister.getCheckedRadioButtonId()==-1)
                 {
                     Toast.makeText(register.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                     radioButton.setError("Gender is required");
                     radioButton.requestFocus();
                 }
                 else if(TextUtils.isEmpty(textMobile))
                 {
                     Toast.makeText(register.this, "Please enter your mobile no:", Toast.LENGTH_SHORT).show();
                     editPhoneNumber.setError("Mobile No. is required");
                     editPhoneNumber.requestFocus();
                 }
                 else if(textMobile.length() != 11)
                 {
                     Toast.makeText(register.this, "Please re-enter your mobile no.", Toast.LENGTH_SHORT).show();
                     editPhoneNumber.setError("Mobile No. should be 10 digits");
                     editPhoneNumber.requestFocus();
                 } else if (!mobileNumMatcher.find()) {

                     Toast.makeText(register.this, "Please re-enter your mobile no.", Toast.LENGTH_SHORT).show();
                     editPhoneNumber.setError("Mobile No. not valid ");
                     editPhoneNumber.requestFocus();

                 } else if(TextUtils.isEmpty(textpwd))
                 {
                     Toast.makeText(register.this, "Please enter Your password", Toast.LENGTH_SHORT).show();

                     editTextpassword.setError("Password is required");
                     editTextpassword.requestFocus();

                 }

                 else if (textpwd.length() < 6)
                 {
                     Toast.makeText(register.this, "password should be at least 6 digits", Toast.LENGTH_SHORT).show();
                     editpasswort.setError("password to week");
                     editpasswort.requestFocus();

                 }

                 else if(TextUtils.isEmpty(textConPdw))
                 {
                     Toast.makeText(register.this, "Please confirm  Your password", Toast.LENGTH_SHORT).show();

                     editTextpassword.setError("Password confirmation is required");
                     editTextpassword.requestFocus();

                 }

                 else if (!textpwd.equals((textConPdw)))
                 {
                     Toast.makeText(register.this, "please enter same password", Toast.LENGTH_SHORT).show();
                     editpasswort.setError("password not same");
                     editpasswort.requestFocus();

                     editpasswort.clearComposingText();
                     confirmpassword.clearComposingText();

                 }
                 else
                 {
                     textGender= radioButton.getText().toString();
                     progressBar.setVisibility(View.VISIBLE);
                     registerUser(textfullname,TextEmail,textDob,textGender,textMobile,textpwd);

                 }



            }
        });
    }

    private void registerUser(String textfullname, String textEmail, String textDob, String textGender, String textMobile, String textpwd) {
        FirebaseAuth mAuth;
        mAuth=FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(textEmail,textpwd)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(register.this, "Account is created",
                                    Toast.LENGTH_SHORT).show();


                            FirebaseUser user = mAuth.getCurrentUser();
                            //update display name
                            UserProfileChangeRequest profileChangeRequest= new UserProfileChangeRequest.Builder().setDisplayName(textfullname).build();

                            assert user != null;
                            user.updateProfile(profileChangeRequest);
                            //using database
                            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(textDob,textGender,textMobile);
                             //referance database

                            DatabaseReference referenceProfile= FirebaseDatabase.getInstance().getReference("Registered User");
                            referenceProfile.child(user.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()) {

                                      //  user.sendEmailVerification();

                                        Toast.makeText(register.this, "user registered successfully,please verify your email", Toast.LENGTH_SHORT).show();


                                    Intent  intent = new Intent(register.this,UserProfileActivity.class);



                                        startActivity(intent);

                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    finish();
                                    }
                                    else

                                    {
                                        Toast.makeText(register.this, "Registered failed ,please try again", Toast.LENGTH_SHORT).show();

                                    }
                                    progressBar.setVisibility(View.GONE);

                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            try {
                                throw Objects.requireNonNull(task.getException());
                            }
                            catch (FirebaseAuthWeakPasswordException e)
                            {
                                editpasswort.setError("your password is tow week .kindly use a mix of alphabet , number and special characters");
                                editpasswort.requestFocus();

                            }
                            catch (FirebaseAuthInvalidCredentialsException e)
                            {
                                editpasswort.setError("your email is invalid already use ,kindly re enter");
                                editpasswort.requestFocus();

                            }
                            catch (FirebaseAuthUserCollisionException e)
                            {
                                editpasswort.setError("you user is already use this email,use another email ");
                                editpasswort.requestFocus();

                            }
                            catch (Exception e)
                            {
                                Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                                Toast.makeText(register.this, e.getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }

                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

}