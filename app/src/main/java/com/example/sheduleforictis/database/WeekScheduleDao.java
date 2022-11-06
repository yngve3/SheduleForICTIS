package com.example.sheduleforictis.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sheduleforictis.models.Week;

@Dao
public interface WeekScheduleDao {
    @Insert(onConflict = REPLACE)
    void insertWeekSchedule(Week week);

    @Query("SELECT * FROM week_schedule")
    LiveData<Week> getWeekSchedule();
}
