package com.example.sheduleforictis.ui.start;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sheduleforictis.application.App;
import com.example.sheduleforictis.databinding.FragmentLaunchBinding;

public class LaunchFragment extends Fragment {

    public interface OnLaunchListener {
        void onEnter();
        void onSkip();
    }
    private FragmentLaunchBinding binding;
    private final OnLaunchListener listener;

    public LaunchFragment(OnLaunchListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLaunchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.launchEnterBtn.setOnClickListener((view1 -> {
            listener.onEnter();
            writeSetting(true);
        }));
        binding.launchSkipBtn.setOnClickListener((view1 -> {
            listener.onSkip();
            writeSetting(false);
        }));
    }

    private void writeSetting(boolean isHaveAccount) {
        requireContext().getSharedPreferences(App.SETTINGS_NAME, Context.MODE_PRIVATE).edit()
                .putBoolean(App.IS_FIRST_ENTER_SETTING, isHaveAccount)
                .apply();
    }
}
