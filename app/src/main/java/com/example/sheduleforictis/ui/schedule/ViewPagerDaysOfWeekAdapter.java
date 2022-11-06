package com.example.sheduleforictis.ui.schedule;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sheduleforictis.ui.schedule.couples.ScheduleDayFragment;
import com.example.sheduleforictis.models.Week;

public class ViewPagerDaysOfWeekAdapter extends FragmentStateAdapter {
    public ViewPagerDaysOfWeekAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new ScheduleDayFragment(position);
    }

    @Override
    public int getItemCount() {
        return 6;
    }


}
