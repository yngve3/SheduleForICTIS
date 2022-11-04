package com.example.sheduleforictis;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sheduleforictis.models.Week;

public class DaysOfWeekAdapter extends FragmentStateAdapter {
    private Week week;
    public DaysOfWeekAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Week week) {
        super(fragmentManager, lifecycle);
        this.week = week;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new ScheduleDayFragment(week.getDay(position));
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
