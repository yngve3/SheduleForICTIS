package com.example.sheduleforictis.repository;

import androidx.lifecycle.LiveData;

import com.example.sheduleforictis.application.App;
import com.example.sheduleforictis.models.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Objects;

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
        return App.getInstance().getNotesDao().getNotesOfCouple(dateOfNotes, numOfCoupleNote, FirebaseAuth.getInstance().getUid());
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

    public void insertNotes(List<Note> notes) {
        new Thread(()-> {
            App.getInstance().getNotesDao().insertNotes(notes);
        }).start();
    }
}
