package com.example.sheduleforictis.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sheduleforictis.databinding.FragmentRegistrationBinding;
import com.example.sheduleforictis.ui.schedule.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.Objects;

public class RegistrationFragment extends Fragment {

    private FragmentRegistrationBinding binding;

    private FirebaseAuth firebaseAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnRegister.setOnClickListener((view1 -> {
            String email = Objects.requireNonNull(binding.etRegEmail.getEditText()).getText().toString();
            String pass = Objects.requireNonNull(binding.etRegPassword.getEditText()).getText().toString();

            if (isEmailValid(email) && isPassValid(pass)) {
                register(email, pass);
            }
        }));

        Objects.requireNonNull(binding.etRegGroup.getEditText()).setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                binding.btnRegister.callOnClick();
                return true;
            }
            return false;
        });
    }

    private void createUserInDB() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://scheduleforictis-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = firebaseDatabase.getReference(Objects.requireNonNull(firebaseAuth.getUid()));
        reference.child("group")
                .setValue(Objects.requireNonNull(binding.etRegGroup.getEditText()).getText().toString().toLowerCase(Locale.ROOT));
    }

    private void register(String email, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    createUserInDB();
                    startActivity(new Intent(getContext(), MainActivity.class));
                });
    }

    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPassValid(String password) {
        if (password.length() < 8) return false;
        return true;
    }
}
