package com.example.sheduleforictis.database.schedule;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sheduleforictis.models.Week;

@Dao
public interface WeekScheduleDao {
    @Insert(onConflict = REPLACE)
    void insertWeekSchedule(Week week);

    @Query("SELECT * FROM week_schedule WHERE `group`=:group")
    LiveData<Week> getWeekScheduleByGroup(String group);

    @Query("SELECT * FROM week_schedule WHERE `group`=:group AND numOfWeek=:weekNum")
    LiveData<Week> getWeekScheduleByGroupAndWeekNum(String group, int weekNum);
}
