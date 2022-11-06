package com.example.sheduleforictis.ui.schedule.couples;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.databinding.FragmentScheduleDayBinding;
import com.example.sheduleforictis.models.Couple;
import com.example.sheduleforictis.models.Day;
import com.example.sheduleforictis.models.Week;
import com.example.sheduleforictis.ui.schedule.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDayFragment extends Fragment {

    private int positionFragment;

    private RecyclerScheduleAdapter recyclerScheduleAdapter;
    private LinearLayoutManager linearLayoutManager;
    private MainViewModel viewModel;
    private Day day;
    private List<Couple> couples;

    private FragmentScheduleDayBinding binding;
    private String[] numOfCouples;

    public ScheduleDayFragment(int positionFragment) {
        this.positionFragment = positionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentScheduleDayBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        numOfCouples = getResources().getStringArray(R.array.num_of_couple);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getCurrentWeekScheduleByIdGroup("96.htm").observe(getViewLifecycleOwner(), week -> {
            day = week.getDay(positionFragment);
            setupCouples();
        });
    }

    private void setupCouples() {
        couples = new ArrayList<>();
        for (Couple couple : day.getCouples()) {
            if (!couple.getNameOfCouple().equals("")) {
                couple.setNumOfCoupleStr(numOfCouples[couple.getNumOfCouple() - 1]);
                couples.add(couple);
            }
        }

        if (couples.isEmpty()) {
            binding.textView.setVisibility(View.VISIBLE);
        } else {
            setupRecyclerViewAdapter();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setupRecyclerViewAdapter() {
        if (recyclerScheduleAdapter == null) {
            recyclerScheduleAdapter = new RecyclerScheduleAdapter(couples, couple -> {
                //TODO: Заметки
            });
            binding.rvSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvSchedule.setAdapter(recyclerScheduleAdapter);
        } else {
            recyclerScheduleAdapter.notifyDataSetChanged();
        }
    }
}
