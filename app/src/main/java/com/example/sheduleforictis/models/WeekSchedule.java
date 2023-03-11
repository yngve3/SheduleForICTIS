package com.example.sheduleforictis.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.TypeConverters;

import com.example.sheduleforictis.database.Converters;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "week_schedule", primaryKeys = {"numOfWeek", "group"})
public class WeekSchedule {
    private int numOfWeek;
    @NonNull
    private String group;
    @TypeConverters({Converters.class})
    private List<DaySchedule> week;

    public WeekSchedule(int numOfWeek, List<DaySchedule> week, @NonNull String group) {
        this.numOfWeek = numOfWeek;
        this.week = week;
        this.group = group;
    }

    public void setNumOfWeek(int numOfWeek) {
        this.numOfWeek = numOfWeek;
    }

    public void setWeek(List<DaySchedule> week) {
        this.week = week;
    }

    public int getNumOfWeek() {
        return numOfWeek;
    }

    public List<DaySchedule> getWeek() {
        return week;
    }

    public String getGroup() {
        return group;
    }

    @Ignore
    public DaySchedule getDay(int position) {
        return week.get(position);
    }

    @Ignore
    public List<Integer> getDaysOfMonthInWeek() {
        List<Integer> list = new ArrayList<>();
        for (DaySchedule daySchedule : week) {
            list.add(daySchedule.getDayOfMonth());
        }

        return list;
    }

    @Ignore
    public List<String> getMonthFullInWeek() {
        List<String> list = new ArrayList<>();
        for (DaySchedule daySchedule : week) {
            list.add(daySchedule.getMonth());
        }

        return list;
    }

    @Ignore
    public List<String> getMonthAbbrInWeek(int n) {
        List<String> list = new ArrayList<>();
        for (DaySchedule daySchedule : week) {
            list.add(daySchedule.getMonth().substring(0, n));
        }

        return list;
    }
}
