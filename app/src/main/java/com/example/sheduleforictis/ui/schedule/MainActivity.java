package com.example.sheduleforictis.ui.schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.application.App;
import com.example.sheduleforictis.databinding.ActivityEnterBinding;
import com.example.sheduleforictis.databinding.ActivityMainBinding;
import com.example.sheduleforictis.models.Note;
import com.example.sheduleforictis.models.Week;
import com.example.sheduleforictis.ui.start.EnterActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private String group;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        ActivityEnterBinding bindingTest = ActivityEnterBinding.inflate(getLayoutInflater());
        setContentView(bindingTest.getRoot());


        /*firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://scheduleforictis-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference reference = firebaseDatabase.getReference(Objects.requireNonNull(firebaseAuth.getUid()));
        reference.child("group").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                group = (String) snapshot.getValue();
                setupTabLayout();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child("notes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Note> notes = new ArrayList<>();
                for (DataSnapshot note : snapshot.getChildren()) {
                    notes.add(note.getValue(Note.class));
                }
                mainViewModel.insertNotesInBD(notes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.imageButton.setOnClickListener(view -> {
            firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(), EnterActivity.class));
            finish();
        });*/
    }

    //@SuppressLint("SetTextI18n")
    /*private void setupTabLayout() {

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

        mainViewModel.getCurrentWeekScheduleFromNet(App.getInstance().getGroupId(group)).observe(this, week -> {
            tabTitlesDate = week.getDaysOfMonthInWeek();
            tabTitlesMAbbr = week.getMonthAbbrInWeek(3);
            tabTitlesMFull = week.getMonthFullInWeek();
            tvStudyWeek.setText("Учебная неделя №" + week.getNumOfWeek());

            viewPagerDaysOfWeekAdapter = new ViewPagerDaysOfWeekAdapter(getSupportFragmentManager(), getLifecycle());
            binding.viewPager.setAdapter(viewPagerDaysOfWeekAdapter);

            new TabLayoutMediator(binding.tlDaysOfWeek, binding.viewPager, (tab, position) -> {
                @SuppressLint("InflateParams") ConstraintLayout tv = (ConstraintLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_layout_tab_title, null);
                ((TextView) tv.findViewById(R.id.tvDayOfWeek)).setText(daysOfWeekAbbr[position]);
                ((TextView) tv.findViewById(R.id.tvDayOfMonth)).setText(String.valueOf(tabTitlesDate.get(position)));
                ((TextView) tv.findViewById(R.id.tvMonth)).setText(String.valueOf(tabTitlesMAbbr.get(position)));
                tab.setCustomView(tv);
            }).attach();

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

            if (flag && dayOfWeek != 7) {
                Objects.requireNonNull(binding.tlDaysOfWeek.getTabAt(dayOfWeek - 1)).select();
            }
        });
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}