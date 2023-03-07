package com.example.sheduleforictis.ui.start;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.databinding.ActivityEnterBinding;
import com.example.sheduleforictis.ui.schedule.MainActivity;
import com.example.sheduleforictis.ui.schedule.favorite.FavoriteScheduleListAddNewFragment;
import com.example.sheduleforictis.ui.schedule.favorite.FavoriteScheduleListFragment;

public class EnterActivity extends AppCompatActivity {

    private ActivityEnterBinding binding;
    private LoginFragment loginFragment;
    private RegistrationFragment registrationFragment;
    private FavoriteScheduleListFragment favoriteScheduleListFragment;
    private FavoriteScheduleListAddNewFragment favoriteScheduleListAddNewFragment;
    private LaunchFragment launchFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginFragment = new LoginFragment(new LoginFragment.OnLoginChangeListener() {
            @Override
            public void reg() {
                switchFragment(registrationFragment);
            }

            @Override
            public void login() {
               startActivityM(MainActivity.class);
            }
        });

        registrationFragment = new RegistrationFragment(() -> switchFragment(favoriteScheduleListFragment));

        favoriteScheduleListFragment = new FavoriteScheduleListFragment(new FavoriteScheduleListFragment.OnAddNewFavScheduleListener() {
            @Override
            public void onAddNewFavSchedule() {
                switchFragment(favoriteScheduleListAddNewFragment);
            }

            @Override
            public void onSaveListFavSchedules() {
                startActivityM(MainActivity.class);
            }
        });

        favoriteScheduleListAddNewFragment = new FavoriteScheduleListAddNewFragment(this::onBackPressed);

        launchFragment = new LaunchFragment(new LaunchFragment.OnLaunchListener() {
            @Override
            public void onEnter() {
                switchFragment(loginFragment);
            }

            @Override
            public void onSkip() {
                switchFragment(favoriteScheduleListFragment);
            }
        });

        if (savedInstanceState == null) {
            switchFragment(launchFragment);
        }

    }

    public void startActivityM(Class<?> cls) {
        startActivity(new Intent(getApplicationContext(), cls));
        finish();
    }
    public void switchFragment(Fragment fragment) {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (getSupportFragmentManager().findFragmentById(R.id.containerEnter) == null) {
                ft.add(R.id.containerEnter, fragment);
            } else {
                ft.replace(R.id.containerEnter, fragment);
            }
            ft.addToBackStack(null);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
