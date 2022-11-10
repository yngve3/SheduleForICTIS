package com.example.sheduleforictis.repository;

import androidx.lifecycle.LiveData;

import com.example.sheduleforictis.application.App;
import com.example.sheduleforictis.models.Note;

import java.util.List;

public class NotesRepository {
    private static NotesRepository instance;

    private NotesRepository() {}

    public static NotesRepository getInstance() {
        if (instance == null) {
            instance = new NotesRepository();
        }
        return instance;
    }

    public LiveData<List<Note>> getNotesOfCouple(String dateOfNotes, int numOfCoupleNote) {
        return App.getInstance().getNotesDao().getNotesOfCouple(dateOfNotes, numOfCoupleNote);
    }

    public void insertNote(Note note) {
        new Thread(()-> {
            App.getInstance().getNotesDao().insertNote(note);
        }).start();
    }

    public void updateNote(Note note) {
        new Thread(()-> App.getInstance().getNotesDao().updateNote(note)).start();
    }

    public void deleteNote(Note note) {
        new Thread(()-> App.getInstance().getNotesDao().deleteNote(note)).start();
    }
}
