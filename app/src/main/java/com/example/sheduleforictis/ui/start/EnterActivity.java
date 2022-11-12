package com.example.sheduleforictis.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.databinding.ActivityEnterBinding;
import com.example.sheduleforictis.ui.schedule.MainActivity;
import com.example.sheduleforictis.ui.schedule.couples.note.edit_note.EditNoteFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class EnterActivity extends AppCompatActivity {

    private final String TAG = "EnterActivity.TAG";

    private ActivityEnterBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnEnter.setOnClickListener((view -> {
            String email = Objects.requireNonNull(binding.etEmail.getEditText()).getText().toString();
            String pass = Objects.requireNonNull(binding.etPassword.getEditText()).getText().toString();

            login(email, pass);
        }));

        binding.btnRegistration.setOnClickListener((view -> {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new RegistrationFragment())
                    .addToBackStack(null)
                    .commit();
        }));

        Objects.requireNonNull(binding.etPassword.getEditText()).setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                binding.btnEnter.callOnClick();
                return true;
            }
            return false;
        });
    }

    private void login(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Неправильный логин или пароль",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
