package com.example.sheduleforictis.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sheduleforictis.database.favoriteSchedules.FavoriteSchedulesDao;
import com.example.sheduleforictis.database.notes.NotesDao;
import com.example.sheduleforictis.database.schedule.WeekScheduleDao;
import com.example.sheduleforictis.models.Group;
import com.example.sheduleforictis.models.Note;
import com.example.sheduleforictis.models.WeekSchedule;

@Database(entities = {WeekSchedule.class, Note.class, Group.class}, version = 4)
public abstract class mDatabase extends RoomDatabase {
    public abstract WeekScheduleDao weekScheduleDao();
    public abstract NotesDao notesDao();
    public abstract FavoriteSchedulesDao favoriteSchedulesDao();
}
