package com.example.sheduleforictis.network;

import com.example.sheduleforictis.network.models.RequestModel;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiHelper {
    @GET("schedule-api/")
    Single<RequestModel> getGroupScheduleByID(@Query("group") String groupID);

    @GET("schedule-api/")
    Single<RequestModel> getGroupScheduleByIDAndWeek(@Query("group") String groupID, @Query("week") int week);

    @GET("schedule-api/")
    Single<RequestModel> searchGroupByName(@Query("query") String nameOfGroup);
}