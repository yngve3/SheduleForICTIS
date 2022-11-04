package com.example.sheduleforictis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sheduleforictis.models.Week;
import com.example.sheduleforictis.network.NetworkService;
import com.example.sheduleforictis.network.models.RequestModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView audience;
    private TextView professor;
    private TextView subject;
    private RequestModel requestModel;

    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;
    private LinearLayoutManager linearLayoutManager;

    private ViewPager2 viewPager2;
    private DaysOfWeekAdapter daysOfWeekAdapter;
    private FragmentManager fragmentManager;
    private TabLayout tabLayout;

    /*
    TODO: Вывод расписания занятий по группам на день, неделю, много недель
    TODO: Запоминание того расписания, что хочет пользователь
    TODO: Поиск по группам, преподавателям, аудиториям
    TODO: Виджет с расписанием
    TODO: Добавлнеие задач
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkService.getInstance().getApi().getGroupScheduleByID("96.htm").enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                assert response.body() != null;
                Week week = Parser.parse(response.body(), getResources().getStringArray(R.array.num_of_couple));
                fragmentManager = getSupportFragmentManager();
                viewPager2 = findViewById(R.id.viewPager);
                daysOfWeekAdapter = new DaysOfWeekAdapter(fragmentManager, getLifecycle(), week);
                tabLayout = findViewById(R.id.tlDaysOfWeek);
                viewPager2.setAdapter(daysOfWeekAdapter);

                String[] tabTitles = new String[] { "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ" };
                String[] tabTitlesDate = new String[] { "31", "1", "2", "3", "4", "5" };
                String[] tabTitlesM = new String[] { "ОКТ", "НОЯ", "НОЯ", "НОЯ", "НОЯ", "НОЯ" };

                new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
                    @SuppressLint("InflateParams") FrameLayout tv = (FrameLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_title_exp, null);
                    ((TextView)tv.findViewById(R.id.tvDayOfWeek)).setText(tabTitles[position]);
                    ((TextView)tv.findViewById(R.id.tvDayOfMonth)).setText(tabTitlesDate[position]);
                    ((TextView)tv.findViewById(R.id.tvMonth)).setText(tabTitlesM[position]);
                    tab.setCustomView(tv);
                }).attach();
            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {

            }
        });
    }
}