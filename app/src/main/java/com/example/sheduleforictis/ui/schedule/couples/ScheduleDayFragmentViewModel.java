package com.example.sheduleforictis.ui.schedule.couples;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sheduleforictis.models.WeekSchedule;
import com.example.sheduleforictis.repository.ScheduleWeekRepository;

public class ScheduleDayFragmentViewModel extends ViewModel {
    private LiveData<WeekSchedule> weekSchedule;
    private final ScheduleWeekRepository repository;

    public ScheduleDayFragmentViewModel() {
        weekSchedule = new MutableLiveData<>();
        repository = ScheduleWeekRepository.getInstance();
    }

    public LiveData<WeekSchedule> getCurrentWeekScheduleByIdGroup(String id) {
        weekSchedule = loadCurrentWeekSchedule(id);
        return weekSchedule;
    }

    private LiveData<WeekSchedule> loadCurrentWeekSchedule(String id) {
        return new MutableLiveData<>();
    }
}
