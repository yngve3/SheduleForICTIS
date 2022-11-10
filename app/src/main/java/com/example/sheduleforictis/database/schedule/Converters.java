package com.example.sheduleforictis.database.schedule;

import androidx.room.TypeConverter;

import com.example.sheduleforictis.models.Couple;
import com.example.sheduleforictis.models.Day;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {
    @TypeConverter
    public List<Day> stringToDayEnt(String dayEnt) {
        Gson gson = new Gson();

        if (dayEnt == null) {
            Collections.emptyList();
        }

        Type listType = new TypeToken<List<Day>>() {}.getType();
        return gson.fromJson(dayEnt, listType);
    }

    @TypeConverter
    public String dayEntToString(List<Day> list) {
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
