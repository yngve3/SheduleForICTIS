package com.example.sheduleforictis.ui.schedule.favorite;

import androidx.recyclerview.widget.DiffUtil;

import com.example.sheduleforictis.models.Group;

import java.util.List;

public class DiffUtilCallback extends DiffUtil.Callback {

    private final List<Group> oldList;
    private final List<Group> newList;

    public DiffUtilCallback(List<Group> oldList, List<Group> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).hashCode() == newList.get(newItemPosition).hashCode();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }
}
