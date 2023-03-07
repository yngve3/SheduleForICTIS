package com.example.sheduleforictis.application;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;

import com.example.sheduleforictis.database.favoriteSchedules.FavoriteSchedulesDao;
import com.example.sheduleforictis.database.mDatabase;
import com.example.sheduleforictis.database.notes.NotesDao;
import com.example.sheduleforictis.database.schedule.WeekScheduleDao;

public class App extends Application {
    private static App instance;
    private mDatabase mDatabase;
    public static final String SETTINGS_NAME = "settings";
    public static final String IS_HAVE_ACCOUNT_SETTING = "isNotAccount";
    public boolean isHaveAccount;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        instance = this;
        mDatabase = Room
                .databaseBuilder(this, mDatabase.class, "Database")
                .fallbackToDestructiveMigration()
                .build();

        isHaveAccount = getSharedPreferences(SETTINGS_NAME, MODE_PRIVATE)
                .getBoolean(IS_HAVE_ACCOUNT_SETTING, false);
    }

    public static App getInstance() {
        return instance;
    }

    public WeekScheduleDao getWeekScheduleDao() {
        return mDatabase.weekScheduleDao();
    }

    public NotesDao getNotesDao() { return  mDatabase.notesDao(); }

    public FavoriteSchedulesDao getFavoriteSchedulesDao() { return mDatabase.favoriteSchedulesDao(); }
}
