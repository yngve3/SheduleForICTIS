package com.example.sheduleforictis.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "group_table")
public class Group {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String code;
    @SerializedName("group")
    @NonNull
    @Expose
    @PrimaryKey
    private String id;

    public Group(@NonNull String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}
