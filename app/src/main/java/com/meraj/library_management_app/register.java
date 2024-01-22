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
import com.meraj.library_management_app.books.RegModel;
import com.meraj.library_management_app.databinding.ActivityRegisterBinding;

import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

   ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();

        binding.registerBre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //error find

                String textEmail =binding.edittextIdEmail.getText().toString();
                String textConPdw =binding.edittextConfirmPassword.getText().toString();
                String textPdw =binding.edittextPassword.getText().toString();
                String  textRegister=binding.edittextRegNumber.getText().toString();
                String textfullname=binding.edittextConfirmPassword.getText().toString();

                // Add this to your onCreate method in the Application class or main activity


                if(TextUtils.isEmpty(textEmail))
                {
                    Toast.makeText(register.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    binding.edittextIdEmail.setError("Email is required");
                    binding.edittextIdEmail.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches())
                {
                    Toast.makeText(register.this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                    binding.edittextIdEmail.setError("Valid Email is required");
                    binding.edittextIdEmail.requestFocus();
                }
                else if(textRegister.length() != 10)
                {
                    Toast.makeText(register.this, "Please re-enter your register no.", Toast.LENGTH_SHORT).show();
                    binding.edittextRegNumber.setError("register No. should be 10 digits");
                    binding.edittextRegNumber.requestFocus();
                }

                //password

                else if(TextUtils.isEmpty(textPdw))
                {
                    Toast.makeText(register.this, "Please enter Your password", Toast.LENGTH_SHORT).show();

                    binding.edittextPassword.setError("Password is required");
                    binding.edittextPassword.requestFocus();

                }

                else if (textPdw.length() < 6)
                {
                    Toast.makeText(register.this, "password should be at least 6 digits", Toast.LENGTH_SHORT).show();
                    binding.edittextPassword.setError("password to week");
                    binding.edittextPassword.requestFocus();

                }

                else if(TextUtils.isEmpty(textConPdw))
                {
                    Toast.makeText(register.this, "Please confirm  Your password", Toast.LENGTH_SHORT).show();

                    binding.edittextConfirmPassword.setError("Password confirmation is required");
                    binding.edittextConfirmPassword.requestFocus();

                }

                else if (!textPdw.equals((textConPdw)))
                {
                    Toast.makeText(register.this, "please enter same password", Toast.LENGTH_SHORT).show();
                    binding.edittextPassword.setError("password not same");
                    binding.edittextPassword.requestFocus();

                    binding.edittextPassword.clearComposingText();
                    binding.edittextConfirmPassword.clearComposingText();

                }
                //error find
                else
                {
                    mAuth.createUserWithEmailAndPassword(binding.edittextIdEmail.getText().toString(),binding.edittextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                RegModel model=new RegModel(binding.edittextFullname.getText().toString(),binding.edittextIdEmail.getText().toString(),binding.edittextRegNumber.getText().toString(),binding.edittextPassword.getText().toString());

                                String id=task.getResult().getUser().getUid();

                                mDatabase.getReference().child("User").child(id).setValue(model);

                                Toast.makeText(register.this, "User Created Succesfully", Toast.LENGTH_SHORT).show();

                            }


                            Intent intent=new Intent(register.this,UserProfileActivity.class);
                            startActivity(intent);
                            finish();


                        }
                    });

                }


            }
        });


    }
}