package com.example.sheduleforictis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheduleforictis.models.Couple;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Couple couple);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTimeStart;
        private final TextView tvTimeEnd;
        private final TextView tvNumOfCouple;
        private final TextView tvKindOfConducting;
        private final TextView tvNameOfCouple;
        private final TextView tvAudience;
        private final TextView tvProfessor;

        private final ImageView ivIndicator;

        public ViewHolder(@NonNull View v) {
            super(v);

            tvTimeStart = v.findViewById(R.id.tvTimeStart);
            tvTimeEnd = v.findViewById(R.id.tvTimeEnd);
            tvNumOfCouple = v.findViewById(R.id.tvNumOfCouple);
            tvKindOfConducting = v.findViewById(R.id.tvKindOfConducting);
            tvNameOfCouple = v.findViewById(R.id.tvNameOfCouple);
            tvAudience = v.findViewById(R.id.tvAudience);
            tvProfessor = v.findViewById(R.id.tvProfessor);

            ivIndicator = v.findViewById(R.id.ivIndicator);
        }

        public void bind(final Couple couple, final OnItemClickListener listener) {
            tvTimeStart.setText(couple.getTimeStart());
            tvTimeEnd.setText(couple.getTimeEnd());
            tvNumOfCouple.setText(couple.getNumOfCouple());
            tvKindOfConducting.setText(couple.getKindOfConducting());
            tvNameOfCouple.setText(couple.getNameOfCouple());
            tvAudience.setText(couple.getAudience());
            tvProfessor.setText(couple.getProfessor());

            if (couple.getOnline()) ivIndicator.setImageResource(R.drawable.green_circle);
            ivIndicator.setImageResource(R.drawable.red_circle);

            itemView.setOnClickListener(view -> listener.onItemClick(couple));
        }
    }

    private final List<Couple> couples;
    private final OnItemClickListener listener;

    public ScheduleAdapter(List<Couple> couples, OnItemClickListener listener) {
        this.couples = couples;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {
        holder.bind(couples.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return couples.size();
    }
}
