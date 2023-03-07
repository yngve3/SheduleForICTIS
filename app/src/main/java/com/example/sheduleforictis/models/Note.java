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
    @NonNull private String dateOfNote;
    private int numOfCoupleNote;
    private String userId;

    public Note(String textOfNote, @NonNull String dateOfNote, int numOfCoupleNote, String  userId) {
        this.textOfNote = textOfNote;
        this.dateOfNote = dateOfNote;
        this.numOfCoupleNote = numOfCoupleNote;
        this.userId = userId;
    }

    @Ignore
    public Note() {}

    public void setTextOfNote(String textOfNote) {
        this.textOfNote = textOfNote;
    }

    public void setDateOfNote(@NonNull String dateOfNote) {
        this.dateOfNote = dateOfNote;
    }

    public void setNumOfCoupleNote(int numOfCoupleNote) {
        this.numOfCoupleNote = numOfCoupleNote;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    @Ignore
    public void changeText(String text) { textOfNote = text; }

}
