package com.example.sheduleforictis.network;

import com.example.sheduleforictis.network.models.RequestModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiHelper {
    @GET("api?request=schedule")
    Call<RequestModel> getGroupScheduleByID(@Query("group") String groupID);

    @GET("api?request=schedule")
    Call<RequestModel> getGroupScheduleByIDAndWeek(@Query("group") String groupID, @Query("week") int week);
}