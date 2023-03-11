package com.example.sheduleforictis.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sheduleforictis.application.App;
import com.example.sheduleforictis.models.LoginModel;
import com.google.firebase.auth.FirebaseAuth;

public class AuthRepository {

    public enum AuthResult {
        SUCCESS,
        FAIL
    }

    public static AuthRepository instance;

    private final String TAG = "AuthService.TAG";

    private final FirebaseAuth firebaseAuth;

    public AuthRepository() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public static AuthRepository getInstance() {
        if (instance == null) {
            instance = new AuthRepository();
        }

        return instance;
    }

    public boolean isFirstEnter() {
        return App.getInstance().isFirstEnter();
    }

    public void setFirstEnter(boolean isFirstEnter) {
        App.getInstance().setFirstEnter(isFirstEnter);
    }

    public LiveData<AuthRepository.AuthResult> login(LoginModel loginModel) {
        MutableLiveData<AuthRepository.AuthResult> mAuthResult = new MutableLiveData<>();
        firebaseAuth.signInWithEmailAndPassword(loginModel.getEmail(), loginModel.getPassword())
                .addOnSuccessListener(authResult -> {
                    mAuthResult.setValue(AuthRepository.AuthResult.SUCCESS);
                })
                .addOnFailureListener(e -> {
                    mAuthResult.setValue(AuthRepository.AuthResult.FAIL);
                    Log.e(TAG, e.getMessage());
                });

        return mAuthResult;
    }

    public LiveData<AuthRepository.AuthResult> register(LoginModel loginModel) {
        MutableLiveData<AuthRepository.AuthResult> mAuthResult = new MutableLiveData<>();
        firebaseAuth.createUserWithEmailAndPassword(loginModel.getEmail(), loginModel.getPassword())
                .addOnSuccessListener(authResult -> {
                    mAuthResult.setValue(AuthRepository.AuthResult.SUCCESS);
                })
                .addOnFailureListener(e -> {
                    mAuthResult.setValue(AuthRepository.AuthResult.FAIL);
                    Log.e(TAG, e.getMessage());
                });

        return mAuthResult;
    }
}
