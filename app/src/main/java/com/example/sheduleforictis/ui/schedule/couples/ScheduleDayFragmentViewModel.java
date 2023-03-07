package com.example.sheduleforictis.ui.schedule.couples;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sheduleforictis.repository.ScheduleWeekRepository;
import com.example.sheduleforictis.models.Week;

public class ScheduleDayFragmentViewModel extends ViewModel {
    private LiveData<Week> weekSchedule;
    private final ScheduleWeekRepository repository;

    public ScheduleDayFragmentViewModel() {
        weekSchedule = new MutableLiveData<>();
        repository = ScheduleWeekRepository.getInstance();
    }

    public LiveData<Week> getCurrentWeekScheduleByIdGroup(String id) {
        weekSchedule = loadCurrentWeekSchedule(id);
        return weekSchedule;
    }

    private LiveData<Week> loadCurrentWeekSchedule(String id) {
        return new MutableLiveData<>();
    }
}
