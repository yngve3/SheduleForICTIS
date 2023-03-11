package com.example.sheduleforictis.database.schedule;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sheduleforictis.models.WeekSchedule;

@Dao
public interface WeekScheduleDao {
    @Insert(onConflict = REPLACE)
    void insertWeekSchedule(WeekSchedule weekSchedule);

    @Query("SELECT * FROM WeekSchedule WHERE `group`=:group")
    LiveData<WeekSchedule> getWeekScheduleByGroup(String group);

    @Query("SELECT * FROM WeekSchedule WHERE `group`=:group AND numOfWeek=:weekNum")
    LiveData<WeekSchedule> getWeekScheduleByGroupAndWeekNum(String group, int weekNum);
}
