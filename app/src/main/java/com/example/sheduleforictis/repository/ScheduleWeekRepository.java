package com.example.sheduleforictis.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sheduleforictis.application.App;
import com.example.sheduleforictis.models.Week;
import com.example.sheduleforictis.network.NetworkService;
import com.example.sheduleforictis.network.models.RequestModel;
import com.example.sheduleforictis.utils.ParserModels;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


    public LiveData<Week> getWeekScheduleByIdGroup(String group, int weekNum) {
        return App.getInstance().getWeekScheduleDao().getWeekScheduleByGroupAndWeekNum(group, weekNum);
    }

    public Single<RequestModel> getCurrentWeekScheduleByIdGroupFromNet(String id) {
        return NetworkService.getInstance().getApi().getGroupScheduleByID(id);
    }

    public Single<RequestModel> getScheduleByIdGroupAndWeekFromNet(String id, int weekNum) {
        return NetworkService.getInstance().getApi().getGroupScheduleByIDAndWeek(id, weekNum);
    }

    public void insertScheduleWeek(Week week) {
        new Thread(() -> App.getInstance().getWeekScheduleDao().insertWeekSchedule(week)).start();
    }
}
