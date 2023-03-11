package com.example.sheduleforictis.database;

import androidx.room.TypeConverter;

import com.example.sheduleforictis.models.Couple;
import com.example.sheduleforictis.models.DaySchedule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {
    @TypeConverter
    public List<DaySchedule> stringToDayEnt(String dayEnt) {
        Gson gson = new Gson();

        if (dayEnt == null) {
            Collections.emptyList();
        }

        Type listType = new TypeToken<List<DaySchedule>>() {}.getType();
        return gson.fromJson(dayEnt, listType);
    }

    @TypeConverter
    public String dayEntToString(List<DaySchedule> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public List<Couple> stringToCoupleEnt(String coupleEnt) {
        Gson gson = new Gson();

        if (coupleEnt == null) {
            Collections.emptyList();
        }

        Type listType = new TypeToken<List<Couple>>() {}.getType();
        return gson.fromJson(coupleEnt, listType);
    }

    @TypeConverter
    public String coupleEntToString(List<Couple> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
