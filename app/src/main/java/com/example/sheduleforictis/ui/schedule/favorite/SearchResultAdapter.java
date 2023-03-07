package com.example.sheduleforictis.ui.schedule.favorite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.models.Group;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    public interface OnSearchResultTouchListener {
        void onTouch(Group group);
    }

    private final OnSearchResultTouchListener listener;

    private final List<Group> results;

    public void setResults(List<Group> results) {
        DiffUtilCallback callback = new DiffUtilCallback(this.results, results);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        result.dispatchUpdatesTo(this);
        this.results.clear();
        this.results.addAll(results);
    }

    public SearchResultAdapter(OnSearchResultTouchListener listener) {
        this.listener = listener;
        results = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_schedule_add_search, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(results.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNameOfGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameOfGroup = itemView.findViewById(R.id.searchResultsTvNameOfGroup);
        }

        public void bind(Group group, OnSearchResultTouchListener listener) {
            tvNameOfGroup.setText(group.getName());

            itemView.setOnClickListener(view -> listener.onTouch(group));
        }
    }
}
