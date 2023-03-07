package com.example.sheduleforictis.database.notes;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sheduleforictis.models.Note;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert(onConflict = REPLACE)
    void insertNote(Note note);

    @Insert(onConflict = REPLACE)
    void insertNotes(List<Note> notes);

    @Query("SELECT * FROM note_table WHERE dateOfNote = :dateOfNotes AND numOfCoupleNote = :numOfCoupleNote AND userId = :userId")
    LiveData<List<Note>> getNotesOfCouple(String dateOfNotes, int numOfCoupleNote, String userId);

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);
}
