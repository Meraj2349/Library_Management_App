package com.meraj.library_management_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class login extends AppCompatActivity {

   private TextInputEditText editTextemail,editTextpassword;

    Button loginbar;

    FirebaseAuth  mAuth;

    ProgressBar progressBar;
    private static final String TAG= "LoginActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();

        editTextemail=findViewById(R.id.entermail);
        editTextpassword=findViewById(R.id.password);
        loginbar=findViewById(R.id.login_btn);
        progressBar=findViewById(R.id.progress);

        loginbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;

                email=String.valueOf(editTextemail.getText());
                password=String.valueOf(editTextpassword.getText());

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(login.this,"enter email",Toast.LENGTH_SHORT).show();

                    editTextemail.setError("email is required");

                    editTextemail.requestFocus();

                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(login.this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                    editTextemail.setError("valid email is required");
                    editTextemail.requestFocus();
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(login.this,"enter password",Toast.LENGTH_SHORT).show();
                    editTextemail.setError("valid email is required");
                    editTextemail.requestFocus();
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);

                    loginUser(email,password);
                }




            }
        });
    }

    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(login.this, "login successful",
                                    Toast.LENGTH_SHORT).show();
                            
                            Intent intent= new Intent(login.this,UserProfileActivity.class);

                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
                            finish();
                            startActivities(new Intent[]{intent});


                        } else {
                            // If sign in fails, display a message to the user.
                            try {
                                throw Objects.requireNonNull(task.getException());
                            }
                            catch (FirebaseAuthWeakPasswordException e)
                            {
                                editTextemail.setError("your password is tow week .kindly use a mix of alphabet , number and special characters");
                                editTextemail.requestFocus();

                            }
                            catch (FirebaseAuthInvalidCredentialsException e)
                            {
                                editTextemail.setError("your email is invalid already use ,kindly re enter");
                                editTextemail.requestFocus();

                            }

                            catch (Exception e)
                            {
                                Log.e(TAG, Objects.requireNonNull(e.getMessage()));

                                Toast.makeText(login.this, e.getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }



                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}
