package com.example.sheduleforictis.network.models;

import java.util.List;

import com.example.sheduleforictis.models.Group;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestModel {

    @SerializedName("table")
    @Expose
    private TableModel table;
    @SerializedName("weeks")
    @Expose
    private List<Integer> weeks = null;

    public TableModel getTable() {
        return table;
    }

    public void setTable(TableModel table) {
        this.table = table;
    }

    public List<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Integer> weeks) {
        this.weeks = weeks;
    }

    @SerializedName("choices")
    @Expose
    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}