package com.example.sheduleforictis;

import android.app.Application;

import androidx.room.Room;

import com.example.sheduleforictis.database.WeekScheduleDao;
import com.example.sheduleforictis.database.WeekScheduleDatabase;

public class App extends Application {
    private static App instance;
    private WeekScheduleDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, WeekScheduleDatabase.class, "database")
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public WeekScheduleDao getDao() {
        return database.weekScheduleDao();
    }

    public WeekScheduleDatabase getDatabase() {
        return database;
    }
}
