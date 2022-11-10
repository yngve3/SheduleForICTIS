package com.example.sheduleforictis.ui.schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    /*
    TODO: Вывод расписания занятий по группам на день, неделю, много недель
    TODO: Запоминание того расписания, что хочет пользователь
    TODO: Поиск по группам, преподавателям, аудиториям
    TODO: Виджет с расписанием
    TODO: Добавлнеие задач
     */

    private ViewPagerDaysOfWeekAdapter viewPagerDaysOfWeekAdapter;

    private TextView tvDateNow;
    private TextView tvStudyWeek;

    private MainViewModel mainViewModel;

    private ActivityMainBinding binding;

    private List<Integer> tabTitlesDate;
    private List<String> tabTitlesMAbbr;
    private List<String> tabTitlesMFull;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LocalDate date;
        int dayOfWeek;
        boolean flag;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDate.now();
            dayOfWeek = date.getDayOfWeek().getValue();
            flag = true;
        } else {
            dayOfWeek = -1;
            flag = false;
        }


        tvDateNow = binding.tvDateNow;
        tvStudyWeek = binding.tvStudyWeek;

        String[] daysOfWeekAbbr = getResources().getStringArray(R.array.num_of_week_abbr);
        String[] daysOfWeekFull = getResources().getStringArray(R.array.num_of_week_full);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getCurrentWeekScheduleFromNet("96.htm").observe(this, week -> {
            tabTitlesDate = week.getDaysOfMonthInWeek();
            tabTitlesMAbbr = week.getMonthAbbrInWeek(3);
            tabTitlesMFull = week.getMonthFullInWeek();
            tvStudyWeek.setText("Учебная неделя №" + week.getNumOfWeek());

            viewPagerDaysOfWeekAdapter = new ViewPagerDaysOfWeekAdapter(getSupportFragmentManager(), getLifecycle());
            binding.viewPager.setAdapter(viewPagerDaysOfWeekAdapter);

            new TabLayoutMediator(binding.tlDaysOfWeek, binding.viewPager, (tab, position) -> {
                @SuppressLint("InflateParams") ConstraintLayout tv = (ConstraintLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_title_exp, null);
                ((TextView) tv.findViewById(R.id.tvDayOfWeek)).setText(daysOfWeekAbbr[position]);
                ((TextView) tv.findViewById(R.id.tvDayOfMonth)).setText(String.valueOf(tabTitlesDate.get(position)));
                ((TextView) tv.findViewById(R.id.tvMonth)).setText(String.valueOf(tabTitlesMAbbr.get(position)));
                tab.setCustomView(tv);
            }).attach();


            if (flag && dayOfWeek != 7) {
                Objects.requireNonNull(binding.tlDaysOfWeek.getTabAt(dayOfWeek - 1)).select();
            }
        });

        binding.tlDaysOfWeek.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tvDateNow.setText(daysOfWeekFull[tab.getPosition()] + ", "
                        + tabTitlesDate.get(tab.getPosition()) + " "
                        + tabTitlesMFull.get(tab.getPosition())
                );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}