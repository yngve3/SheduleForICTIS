package com.example.sheduleforictis.ui.schedule.favorite;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.models.Group;
import com.example.sheduleforictis.ui.schedule.couples.note.list_notes.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class FavoriteScheduleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperCallback.ItemTouchHelperAdapter {

    public interface OnFavScheduleListChangeListener {
        void onAddNewSchedule();
        void onDeleteSchedule(Group group);
    }
    @Override
    public void onItemDismiss(int position) {
        listener.onDeleteSchedule(favSchedules.get(position));

        favSchedules.remove(position);
        notifyItemRemoved(position);
    }

    private static final int ITEM = 0;
    private final int BUTTON = 1;

    private final List<Group> favSchedules;
    private final OnFavScheduleListChangeListener listener;

    public FavoriteScheduleListAdapter(OnFavScheduleListChangeListener listener) {
        this.listener = listener;
        favSchedules = new ArrayList<>();
    }
    public void setFavSchedules(List<Group> favSchedules) {
        DiffUtilCallback callback = new DiffUtilCallback(this.favSchedules, favSchedules);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        result.dispatchUpdatesTo(this);
        this.favSchedules.clear();
        this.favSchedules.addAll(favSchedules);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM: {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_favorite_schedule_list, null);
                return new FavoriteScheduleListAdapter.ItemHolder(v);
            }
            case BUTTON: {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_favorite_schedule_list_add_btn, null);
                return new FavoriteScheduleListAdapter.ButtonHolder(v);
            }
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FavoriteScheduleListAdapter.ButtonHolder) {
            ((FavoriteScheduleListAdapter.ButtonHolder) holder).bind(listener);
        } else if (holder instanceof FavoriteScheduleListAdapter.ItemHolder) {
            if (favSchedules.size() != 0) {
                ((FavoriteScheduleListAdapter.ItemHolder) holder).bind(favSchedules.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return favSchedules.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) return BUTTON;
        else return ITEM;
    }
    public static class ButtonHolder extends RecyclerView.ViewHolder {

        private final Button addBtn;
        public ButtonHolder(@NonNull View v) {
            super(v);
            addBtn = v.findViewById(R.id.addNewFavScheduleBtn);
        }
        public void bind(final OnFavScheduleListChangeListener listener) {
            addBtn.setOnClickListener(view -> listener.onAddNewSchedule());
        }
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        private final TextView tvNameOfSchedule;

        public ItemHolder(@NonNull View v) {
            super(v);
            tvNameOfSchedule = v.findViewById(R.id.tvNameFavSchedule);
        }

        public void bind(Group group) {
            tvNameOfSchedule.setText(group.getName());
        }
    }
}
