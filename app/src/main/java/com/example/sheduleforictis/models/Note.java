package com.example.sheduleforictis.models;

public class Note {
    private final String textOfNote;
    private final String dateOfNote;
    private final int numOfCoupleNote;

    public Note(String textOfNote, String dateOfNote, int numOfCoupleNote) {
        this.textOfNote = textOfNote;
        this.dateOfNote = dateOfNote;
        this.numOfCoupleNote = numOfCoupleNote;
    }

    public String getTextOfNote() {
        return textOfNote;
    }

    public String getDateOfNote() {
        return dateOfNote;
    }

    public int getNumOfCoupleNote() {
        return numOfCoupleNote;
    }
}
