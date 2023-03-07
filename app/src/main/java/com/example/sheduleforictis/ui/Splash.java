package com.example.sheduleforictis.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sheduleforictis.ui.schedule.MainActivity;
import com.example.sheduleforictis.ui.start.EnterActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intent intent;

        if (firebaseAuth.getCurrentUser() == null) {
            intent = new Intent(this, EnterActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
