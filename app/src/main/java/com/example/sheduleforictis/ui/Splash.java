package com.example.sheduleforictis.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sheduleforictis.repository.AuthRepository;
import com.example.sheduleforictis.ui.schedule.MainActivity;
import com.example.sheduleforictis.ui.start.EnterActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;

        if (AuthRepository.getInstance().isFirstEnter()) {
            intent = new Intent(this, EnterActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
