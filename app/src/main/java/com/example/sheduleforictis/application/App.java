package com.example.sheduleforictis.application;

import android.app.Application;

import androidx.room.Room;

import com.example.sheduleforictis.database.notes.NotesDao;
import com.example.sheduleforictis.database.notes.NotesDatabase;
import com.example.sheduleforictis.database.schedule.WeekScheduleDao;
import com.example.sheduleforictis.database.schedule.WeekScheduleDatabase;

public class App extends Application {
    private static App instance;
    private WeekScheduleDatabase weekScheduleDatabase;
    private NotesDatabase notesDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        weekScheduleDatabase = Room
                .databaseBuilder(this, WeekScheduleDatabase.class, "weekScheduleDatabase")
                .build();

        notesDatabase = Room
                .databaseBuilder(this, NotesDatabase.class, "notesDatabase")
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public WeekScheduleDao getWeekScheduleDao() {
        return weekScheduleDatabase.weekScheduleDao();
    }

    public NotesDao getNotesDao() { return  notesDatabase.notesDao(); }
}
