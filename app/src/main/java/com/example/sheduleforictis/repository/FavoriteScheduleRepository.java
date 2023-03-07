package com.example.sheduleforictis.repository;

import com.example.sheduleforictis.application.App;
import com.example.sheduleforictis.models.Group;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteScheduleRepository {

    private static FavoriteScheduleRepository instance;
    private final CompositeDisposable disposables;

    private FavoriteScheduleRepository() {
        disposables = new CompositeDisposable();
    }

    public static FavoriteScheduleRepository getInstance() {
        if (instance == null) {
            instance = new FavoriteScheduleRepository();
        }
        return instance;
    }

    public void insertFavoriteGroup(Group group) {
        new Thread(() -> App.getInstance().getFavoriteSchedulesDao().insertFavoriteSchedule(group)).start();
        FirebaseRepository.getInstance().insertFavoriteGroup(group);
    }

    public void insertFavoriteGroups(List<Group> groups) {
        new Thread(() -> App.getInstance().getFavoriteSchedulesDao().insertFavoriteSchedules(groups)).start();
    }

    public void deleteFavoriteGroup(Group group) {
        new Thread(() -> App.getInstance().getFavoriteSchedulesDao().deleteFavoriteSchedule(group)).start();
        FirebaseRepository.getInstance().deleteFavoriteGroup(group);
    }

    public Flowable<List<Group>> getFavoriteGroups() {
        return App.getInstance().getFavoriteSchedulesDao().getFavoriteSchedules();
    }

    public void downloadFavoriteGroups() {
        disposables.add(FirebaseRepository.getInstance().getFavoriteGroups()
                .subscribeOn(Schedulers.io())
                .subscribe(this::insertFavoriteGroups));
    }

    public void uploadFavoriteGroups() {
        disposables.add(getFavoriteGroups()
                .subscribeOn(Schedulers.io())
                .subscribe(groups -> FirebaseRepository.getInstance().insertFavoriteGroups(groups)));
    }
}
