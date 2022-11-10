package com.example.sheduleforictis.database.schedule;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sheduleforictis.models.Week;

@Database(entities = Week.class, version = 1)
public abstract class WeekScheduleDatabase extends RoomDatabase {
    public abstract WeekScheduleDao weekScheduleDao();
}
