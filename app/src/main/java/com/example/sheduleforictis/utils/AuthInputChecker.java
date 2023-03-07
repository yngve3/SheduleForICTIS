package com.example.sheduleforictis.utils;

import android.util.Patterns;

public class AuthInputChecker {
    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPassValid(String password) {
        if (password.length() < 8) return false;
        return true;
    }
}
