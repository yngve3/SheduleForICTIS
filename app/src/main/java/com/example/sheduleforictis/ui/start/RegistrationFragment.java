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

import com.example.sheduleforictis.databinding.FragmentRegistrationBinding;
import com.example.sheduleforictis.models.LoginModel;
import com.example.sheduleforictis.repository.AuthRepository;
import com.example.sheduleforictis.utils.AuthInputChecker;

import java.util.Objects;

public class RegistrationFragment extends Fragment {

    public interface OnRegisterEndsListener {
        void onRegisterEnds();
    }

    private FragmentRegistrationBinding binding;

    private final OnRegisterEndsListener listener;

    public RegistrationFragment(OnRegisterEndsListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.btnRegister.setOnClickListener((view1 -> {
            String email = Objects.requireNonNull(binding.etRegEmail.getEditText()).getText().toString();
            String pass = Objects.requireNonNull(binding.etRegPassword.getEditText()).getText().toString();

            boolean inputIsCorrect = true;
            if (!AuthInputChecker.isEmailValid(email)) {
                binding.etRegEmail.setError("E-mail некорректен");
                inputIsCorrect = false;
            } else {
                binding.etRegEmail.setErrorEnabled(false);
            }
            if (!AuthInputChecker.isPassValid(pass)) {
                binding.etRegPassword.setError("Пароль некорректен");
                inputIsCorrect = false;
            } else {
                binding.etRegPassword.setErrorEnabled(false);
            }

            if (inputIsCorrect) {
                register(new LoginModel(email, pass));
            }
        }));

        Objects.requireNonNull(binding.etRegPassword.getEditText()).setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                binding.btnRegister.callOnClick();
                return true;
            }
            return false;
        });
    }

    private void register(LoginModel loginModel) {
        AuthRepository.getInstance().register(loginModel).observe(getViewLifecycleOwner(), authResult -> {
            switch (authResult) {
                case SUCCESS: {
                    listener.onRegisterEnds();
                    break;
                }
                case FAIL: {
                    Toast.makeText(requireContext(), "Что-то пошло не так, повторите попытку", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
