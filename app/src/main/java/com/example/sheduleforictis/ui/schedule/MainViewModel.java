package com.example.sheduleforictis.ui.schedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sheduleforictis.repository.ScheduleWeekRepository;
import com.example.sheduleforictis.models.Week;

import java.util.Objects;

public class MainViewModel extends ViewModel {
    private LiveData<Week> weekSchedule;
    private LiveData<String> tabTitleMonth;
    private LiveData<String> tabTitleDay;
    private final ScheduleWeekRepository repository;

    public MainViewModel() {
        weekSchedule = new MutableLiveData<>();
        repository = ScheduleWeekRepository.getInstance();
    }

    public LiveData<Week> getCurrentWeekScheduleFromNet(String id) {
        weekSchedule = repository.getCurrentWeekScheduleByIdGroupFromNet(id);
        return weekSchedule;
    }

    public LiveData<Week> getCurrentWeekScheduleByIdGroup(String id) {
        weekSchedule = loadCurrentWeekSchedule(id);
        return weekSchedule;
    }

    private LiveData<Week> loadCurrentWeekSchedule(String id) {
        return repository.getCurrentWeekScheduleByIdGroup(id);
    }
}
