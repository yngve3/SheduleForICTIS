package com.example.sheduleforictis.repository;

import androidx.lifecycle.LiveData;

import com.example.sheduleforictis.application.App;
import com.example.sheduleforictis.models.WeekSchedule;
import com.example.sheduleforictis.network.NetworkService;
import com.example.sheduleforictis.network.models.RequestModel;

import io.reactivex.rxjava3.core.Single;

public class ScheduleWeekRepository {
    private static ScheduleWeekRepository instance;
    private ScheduleWeekRepository() {

    }

    public static ScheduleWeekRepository getInstance() {
        if (instance == null) {
            instance = new ScheduleWeekRepository();
        }
        return instance;
    }


    public LiveData<WeekSchedule> getWeekScheduleByIdGroup(String group, int weekNum) {
        return App.getInstance().getWeekScheduleDao().getWeekScheduleByGroupAndWeekNum(group, weekNum);
    }

    public Single<RequestModel> getCurrentWeekScheduleByIdGroupFromNet(String id) {
        return NetworkService.getInstance().getApi().getGroupScheduleByID(id);
    }

    public Single<RequestModel> getScheduleByIdGroupAndWeekFromNet(String id, int weekNum) {
        return NetworkService.getInstance().getApi().getGroupScheduleByIDAndWeek(id, weekNum);
    }

    public void insertScheduleWeek(WeekSchedule weekSchedule) {
        new Thread(() -> App.getInstance().getWeekScheduleDao().insertWeekSchedule(weekSchedule)).start();
    }
}
