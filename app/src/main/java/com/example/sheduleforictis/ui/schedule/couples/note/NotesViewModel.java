package com.example.sheduleforictis.ui.schedule.couples.note;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sheduleforictis.models.Note;
import com.example.sheduleforictis.repository.NotesRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotesViewModel extends ViewModel {
    private final NotesRepository notesRepository;
    private Note note;
    private String nameOfCouple;
    private List<Integer> idNotes;

    private final FirebaseAuth firebaseAuth;
    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference reference;

    public NotesViewModel() {
        notesRepository = NotesRepository.getInstance();
        idNotes = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://scheduleforictis-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = firebaseDatabase.getReference(Objects.requireNonNull(firebaseAuth.getUid()));
    }

    public LiveData<List<Note>> getNotesOfCouple(String dateOfNotes, int numOfCoupleNote) {
        return notesRepository.getNotesOfCouple(dateOfNotes, numOfCoupleNote);
    }

    public void insertNote(Note note) {
        if (idNotes.contains(note.id)) {
            notesRepository.updateNote(note);
        } else {
            note.id = note.hashCode();
            notesRepository.insertNote(note);
            idNotes.add(note.id);
        }
        reference.child("notes").child(String.valueOf(note.id)).setValue(note);
    }

    public void editNote(Note note, String nameOfCouple) {
        this.note = note;
        this.nameOfCouple = nameOfCouple;
    }

    public void deleteNote(Note note) {
        notesRepository.deleteNote(note);
        reference.child("notes").child(String.valueOf(note.id)).removeValue();
    }

    public Note getEditableNote() {
        Note note = this.note;
        this.note = null;
        return note;
    }

    public String getNameOfCouple() {
        return nameOfCouple;
    }

    public void setIdNotes(List<Note> notes) {
        this.idNotes = getIdNotes(notes);
    }

    private List<Integer> getIdNotes(List<Note> notes) {
        List<Integer> res = new ArrayList<>();
        for (Note note : notes) {
            res.add(note.id);
        }

        return res;
    }
}
