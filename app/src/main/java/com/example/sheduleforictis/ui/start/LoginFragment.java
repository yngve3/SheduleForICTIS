package com.example.sheduleforictis.ui.start;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sheduleforictis.databinding.FragmentLoginBinding;
import com.example.sheduleforictis.models.LoginModel;
import com.example.sheduleforictis.repository.AuthRepository;
import com.example.sheduleforictis.utils.AuthInputChecker;

import java.util.Objects;

public class LoginFragment extends Fragment {

    public interface OnLoginChangeListener {
        void reg();
        void login();
    }

    private FragmentLoginBinding binding;

    private final OnLoginChangeListener listener;

    public LoginFragment(OnLoginChangeListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnEnter.setOnClickListener((view1 -> {
            String email = Objects.requireNonNull(binding.etEmail.getEditText()).getText().toString();
            String pass = Objects.requireNonNull(binding.etPassword.getEditText()).getText().toString();

            boolean inputIsCorrect = true;
            if (!AuthInputChecker.isEmailValid(email)) {
                binding.etEmail.setError("E-mail некорректен");
                inputIsCorrect = false;
            } else {
                binding.etEmail.setErrorEnabled(false);
            }
            if (!AuthInputChecker.isPassValid(pass)) {
                binding.etPassword.setError("Пароль некорректен");
                inputIsCorrect = false;
            } else {
                binding.etPassword.setErrorEnabled(false);
            }

            if (inputIsCorrect) {
                login(new LoginModel(email, pass));
            }

        }));

        binding.btnRegistration.setOnClickListener((view1 -> listener.reg()));

        Objects.requireNonNull(binding.etPassword.getEditText()).setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                binding.btnEnter.callOnClick();
                return true;
            }
            return false;
        });
    }

    private void login(LoginModel loginModel) {
        AuthRepository.getInstance().login(loginModel).observe(getViewLifecycleOwner(), authResult -> {
            switch (authResult) {
                case SUCCESS: {
                    listener.login();
                    break;
                }
                case FAIL: {
                    Toast.makeText(requireContext(), "Неправильный логин или пароль",
                            Toast.LENGTH_LONG).show();
                    break;
                }
            }
        });
    }
}
