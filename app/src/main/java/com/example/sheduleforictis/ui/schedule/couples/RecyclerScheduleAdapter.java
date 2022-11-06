package com.example.sheduleforictis.ui.schedule.couples;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.models.Couple;

import java.util.List;

public class RecyclerScheduleAdapter extends RecyclerView.Adapter<RecyclerScheduleAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Couple couple);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @StringRes
        private static final int[] IS_ONLINE = new int[]{R.string.offline, R.string.online};

        private final TextView tvTimeStart;
        private final TextView tvTimeEnd;
        private final TextView tvNumOfCouple;
        private final TextView tvKindOfConducting;
        private final TextView tvNameOfCouple;
        private final TextView tvAudience;
        private final TextView tvProfessor;

        private final View ivIndicator;

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
            tvNumOfCouple.setText(couple.getNumOfCoupleStr());
            tvNameOfCouple.setText(couple.getNameOfCouple());
            tvAudience.setText(couple.getAudience());
            tvProfessor.setText(couple.getProfessor());

            if (couple.getOnline()) {
                ivIndicator.setBackgroundResource(R.drawable.indicator_green);
                tvKindOfConducting.setText(IS_ONLINE[1]);
            } else {
                tvKindOfConducting.setText(IS_ONLINE[0]);
                ivIndicator.setBackgroundResource(R.drawable.indicator_red);
            }

            itemView.setOnClickListener(view -> listener.onItemClick(couple));
        }
    }

    private final List<Couple> couples;
    private final OnItemClickListener listener;

    public RecyclerScheduleAdapter(List<Couple> couples, OnItemClickListener listener) {
        this.couples = couples;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerScheduleAdapter.ViewHolder holder, int position) {
        holder.bind(couples.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return couples.size();
    }
}
