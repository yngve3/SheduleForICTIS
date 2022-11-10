package com.example.sheduleforictis.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey
    public int id;
    private String textOfNote;
    @NonNull private final String dateOfNote;
    private final int numOfCoupleNote;

    public Note(String textOfNote, @NonNull String dateOfNote, int numOfCoupleNote) {
        this.textOfNote = textOfNote;
        this.dateOfNote = dateOfNote;
        this.numOfCoupleNote = numOfCoupleNote;
    }

    public String getTextOfNote() {
        return textOfNote;
    }

    @NonNull
    public String getDateOfNote() {
        return dateOfNote;
    }

    public int getNumOfCoupleNote() {
        return numOfCoupleNote;
    }

    @Ignore
    public void changeText(String text) { textOfNote = text; }

}
