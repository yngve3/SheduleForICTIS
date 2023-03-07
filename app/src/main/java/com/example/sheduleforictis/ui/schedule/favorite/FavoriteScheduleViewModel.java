package com.example.sheduleforictis.ui.schedule.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sheduleforictis.models.Group;
import com.example.sheduleforictis.network.NetworkService;
import com.example.sheduleforictis.network.models.RequestModel;
import com.example.sheduleforictis.repository.FavoriteScheduleRepository;
import com.example.sheduleforictis.repository.ScheduleWeekRepository;
import com.example.sheduleforictis.utils.ParserModels;
import com.example.sheduleforictis.utils.ResultStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteScheduleViewModel extends ViewModel {

    private final ScheduleWeekRepository scheduleWeekRepository;
    private final FavoriteScheduleRepository favoriteScheduleRepository;

    private RequestModel requestModel;
    private final CompositeDisposable disposables;

    public FavoriteScheduleViewModel() {
        this.scheduleWeekRepository = ScheduleWeekRepository.getInstance();
        this.favoriteScheduleRepository = FavoriteScheduleRepository.getInstance();
        disposables = new CompositeDisposable();
    }

    public LiveData<ResultStatus<List<Group>>> search(String searchRequest) {
        //TODO Оптимизировать, сделав поиск среди уже полученных данных
        //TODO Добавить проверку на правильность группы

        final MutableLiveData<ResultStatus<List<Group>>> result = new MutableLiveData<>();

        NetworkService.getInstance().getApi().searchGroupByName(searchRequest.trim())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<RequestModel>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull RequestModel requestModel) {
                        if (requestModel.getGroups() != null) {
                            result.setValue(new ResultStatus.Success<>(requestModel.getGroups()));
                        } else if (requestModel.getTable() != null) {
                            result.setValue(new ResultStatus.Success<>(
                                    new ArrayList<>(Collections.singletonList(new Group(
                                        requestModel.getTable().getGroup(),
                                        requestModel.getTable().getName()
                            )))));
                        } else {
                            result.setValue(new ResultStatus.Success<>(null));
                        }

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        result.setValue(new ResultStatus.Error<>("Что-то пошло не так"));
                    }
                });

        return result;
    }

    public void saveFavoriteSchedule() {
        if (this.requestModel != null) {
            favoriteScheduleRepository.insertFavoriteGroup(
                    new Group(
                            requestModel.getTable().getGroup().replace('.', '_'),
                            requestModel.getTable().getName()
                    )
            );

            scheduleWeekRepository.insertScheduleWeek((ParserModels.parseFromNetwork(requestModel)));
        }

    }

    public void preSaveRequestModel(RequestModel requestModel) {
        this.requestModel = requestModel;
    }

    public LiveData<ResultStatus<Boolean>> getGroupScheduleByID(String group) {
        final MutableLiveData<ResultStatus<Boolean>> result = new MutableLiveData<>();
        scheduleWeekRepository.getCurrentWeekScheduleByIdGroupFromNet(group)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<RequestModel>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull RequestModel requestModel) {
                        preSaveRequestModel(requestModel);
                        result.setValue(new ResultStatus.Success<>(true));
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        result.setValue(new ResultStatus.Error<>("Что-то пошло не так"));
                    }
                });

        return result;
    }

    public void deleteFavoriteGroup(Group group) {
        new Thread(() -> favoriteScheduleRepository.deleteFavoriteGroup(group)).start();
    }

    public LiveData<ResultStatus<List<Group>>> getListOfFavoriteGroups() {
        final MutableLiveData<ResultStatus<List<Group>>> result = new MutableLiveData<>();

        disposables.add(favoriteScheduleRepository.getFavoriteGroups()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(groups -> result.postValue(new ResultStatus.Success<>(groups))));

        return result;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
        disposables.dispose();
    }
}
