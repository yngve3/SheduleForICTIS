package com.example.sheduleforictis.models;

import java.util.List;

public class Week {
    private int numOfWeek;
    private List<Day> week;

    public Week(int numOfWeek, List<Day> week) {
        this.numOfWeek = numOfWeek;
        this.week = week;
    }

    public int getNumOfWeek() {
        return numOfWeek;
    }

    public List<Day> getWeek() {
        return week;
    }

    public Day getDay(int position) {
        return week.get(position);
    }
}
