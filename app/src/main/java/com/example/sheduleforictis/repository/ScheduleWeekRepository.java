package com.example.sheduleforictis.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sheduleforictis.App;
import com.example.sheduleforictis.models.Week;
import com.example.sheduleforictis.network.NetworkService;
import com.example.sheduleforictis.network.models.RequestModel;
import com.example.sheduleforictis.utils.ParserModels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleWeekRepository {
    private static ScheduleWeekRepository instance;
    private MutableLiveData<Week> weekSchedule;

    private ScheduleWeekRepository() {
        weekSchedule = new MutableLiveData<>();
    }

    public static ScheduleWeekRepository getInstance() {
        if (instance == null) {
            instance = new ScheduleWeekRepository();
        }
        return instance;
    }


    public LiveData<Week> getCurrentWeekScheduleByIdGroup(String id) {
        return App.getInstance().getWeekScheduleDao().getWeekSchedule();
    }

    public LiveData<Week> getCurrentWeekScheduleByIdGroupFromNet(String id) {
        NetworkService.getInstance().getApi().getGroupScheduleByID(id).enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(@NonNull Call<RequestModel> call, @NonNull Response<RequestModel> response) {
                assert response.body() != null;
                weekSchedule.setValue(ParserModels.parseFromNetwork(response.body()));
                new Thread(() ->
                        App.getInstance().getWeekScheduleDao().insertWeekSchedule(ParserModels.parseFromNetwork(response.body()))
                ).start();
            }

            @Override
            public void onFailure(@NonNull Call<RequestModel> call, @NonNull Throwable t) {
                weekSchedule.setValue(null);
            }
        });
        return weekSchedule;
    }

    /*public MutableLiveData<Week> getScheduleByIdGroupAndWeek(String id, int weekNum) {
        NetworkService.getInstance().getApi().getGroupScheduleByIDAndWeek(id, weekNum).enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(@NonNull Call<RequestModel> call, @NonNull Response<RequestModel> response) {
                assert response.body() != null;
                weekSchedule.setValue(ParserModels.parseFromNetwork(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<RequestModel> call, @NonNull Throwable t) {
                weekSchedule.postValue(null);
            }
        });

        return weekSchedule;
    }*/
}
