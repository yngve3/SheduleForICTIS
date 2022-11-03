package com.example.sheduleforictis.network.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableModel {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("week")
    @Expose
    private Integer week;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("table")
    @Expose
    private List<List<String>> table = null;
    @SerializedName("link")
    @Expose
    private String link;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getWeek() {
        return week;
    }

    public String getGroup() {
        return group;
    }

    public List<List<String>> getTable() {
        return table;
    }

    public String getLink() {
        return link;
    }

}