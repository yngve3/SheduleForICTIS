package com.example.sheduleforictis.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.sheduleforictis.database.Converters;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "week_schedule", primaryKeys = {"numOfWeek", "group"})
public class Week {
    private int numOfWeek;
    @NonNull
    private String group;
    @TypeConverters({Converters.class})
    private List<Day> week;

    public Week(int numOfWeek, List<Day> week, @NonNull String group) {
        this.numOfWeek = numOfWeek;
        this.week = week;
        this.group = group;
    }

    public void setNumOfWeek(int numOfWeek) {
        this.numOfWeek = numOfWeek;
    }

    public void setWeek(List<Day> week) {
        this.week = week;
    }

    public int getNumOfWeek() {
        return numOfWeek;
    }

    public List<Day> getWeek() {
        return week;
    }

    public String getGroup() {
        return group;
    }

    @Ignore
    public Day getDay(int position) {
        return week.get(position);
    }

    @Ignore
    public List<Integer> getDaysOfMonthInWeek() {
        List<Integer> list = new ArrayList<>();
        for (Day day : week) {
            list.add(day.getDayOfMonth());
        }

        return list;
    }

    @Ignore
    public List<String> getMonthFullInWeek() {
        List<String> list = new ArrayList<>();
        for (Day day : week) {
            list.add(day.getMonth());
        }

        return list;
    }

    @Ignore
    public List<String> getMonthAbbrInWeek(int n) {
        List<String> list = new ArrayList<>();
        for (Day day : week) {
            list.add(day.getMonth().substring(0, n));
        }

        return list;
    }
}
