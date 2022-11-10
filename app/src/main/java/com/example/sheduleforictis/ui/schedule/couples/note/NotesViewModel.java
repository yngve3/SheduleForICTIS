package com.example.sheduleforictis.ui.schedule.couples.note;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sheduleforictis.models.Note;
import com.example.sheduleforictis.repository.NotesRepository;

import java.util.ArrayList;
import java.util.List;

public class NotesViewModel extends ViewModel {
    private final NotesRepository notesRepository;
    private Note note;
    private String nameOfCouple;
    private List<Integer> idNotes;

    public NotesViewModel() {
        notesRepository = NotesRepository.getInstance();
        idNotes = new ArrayList<>();
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
    }

    public void editNote(Note note, String nameOfCouple) {
        this.note = note;
        this.nameOfCouple = nameOfCouple;
    }

    public void deleteNote(Note note) {
        notesRepository.deleteNote(note);
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
