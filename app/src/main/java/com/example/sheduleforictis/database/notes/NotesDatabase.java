package com.example.sheduleforictis.database.notes;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sheduleforictis.models.Note;

@Database(entities = Note.class, version = 5)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
}
