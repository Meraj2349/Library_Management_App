package com.meraj.library_management_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.meraj.library_management_app.databinding.ActivityUplodeProfileBinding;
import com.meraj.library_management_app.databinding.ActivityUserProfileBinding;

public class Uplode_profile_Activity extends AppCompatActivity {

    ActivityUplodeProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUplodeProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Upload profile picture");




    }
}