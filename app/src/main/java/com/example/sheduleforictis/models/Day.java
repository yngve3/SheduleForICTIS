package com.example.sheduleforictis.models;

import java.util.List;

public class Day {
    private String dayOfWeek;
    private int dayOfMonth;
    private String month;
    private int numOfWeek;
    private List<Couple> couples;

    public Day(String dayOfWeek, int dayOfMonth, String month, int numOfWeek, List<Couple> couples) {
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.numOfWeek = numOfWeek;
        this.couples = couples;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public String getMonth() {
        return month;
    }

    public int getNumOfWeek() {
        return numOfWeek;
    }

    public List<Couple> getCouples() {
        return couples;
    }
}
