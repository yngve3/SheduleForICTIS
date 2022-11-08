package com.example.sheduleforictis.ui.schedule.couples.note.list_notes;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.models.Note;

import java.util.List;

public class ListOfNotesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }

    private final String nameOfCouple;
    private final List<Note> notes;
    private final ListOfNotesRecyclerAdapter.OnItemClickListener onItemClickListener;
    private final OnButtonClickListener onButtonClickListener;

    private final int HEADER = 0;
    private final int ITEM = 1;
    private final int BUTTON = 2;

    public static class ItemHolder extends RecyclerView.ViewHolder {

        private final TextView tvTextNote;
        private final TextView tvDateOfNote;
        private final TextView tvNoteNameOfCouple;

        public ItemHolder(@NonNull View v) {
            super(v);
            tvTextNote = v.findViewById(R.id.tvTextNote);
            tvDateOfNote = v.findViewById(R.id.tvDateOfNote);
            tvNoteNameOfCouple = v.findViewById(R.id.tvNoteNameOfCouple);
        }

        public void bind(final String nameOfCouple, final Note note, final ListOfNotesRecyclerAdapter.OnItemClickListener onItemClickListener) {

            tvTextNote.setText(note.getTextOfNote());
            tvDateOfNote.setText(note.getDateOfNote());
            tvNoteNameOfCouple.setText(nameOfCouple);

            itemView.setOnClickListener(view -> { onItemClickListener.onItemClick(note); });
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {

        private final TextView tvCountOfNotes;

        public HeaderHolder(@NonNull View v) {
            super(v);
            tvCountOfNotes = v.findViewById(R.id.tvCountOfCouples);
        }

        @SuppressLint("SetTextI18n")
        public void bind(int numOfNotes) {
            tvCountOfNotes.setText("У пары " + numOfNotes + " заметок");
        }
    }

    public static class ButtonHolder extends RecyclerView.ViewHolder {

        private final TextView btnCreateNote;

        public ButtonHolder(@NonNull View v) {
            super(v);
            btnCreateNote = v.findViewById(R.id.btnCreateNote);
        }

        public void bind(final OnButtonClickListener onButtonClickListener) {
            btnCreateNote.setOnClickListener(view -> onButtonClickListener.onButtonClick());
        }
    }

    public ListOfNotesRecyclerAdapter(
            String nameOfCouple,
            List<Note> notes,
            ListOfNotesRecyclerAdapter.OnItemClickListener onItemClickListener,
            OnButtonClickListener onButtonClickListener
    ) {
        this.onItemClickListener = onItemClickListener;
        this.onButtonClickListener = onButtonClickListener;
        this.notes = notes;
        this.nameOfCouple = nameOfCouple;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addNote(Note note) {
        notes.add(note);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER: {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.title_notes_dialog, null);
                return new HeaderHolder(v);
            }
            case ITEM: {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.note_notes_dialog, null);
                return new ItemHolder(v);
            }
            case BUTTON: {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.btn_notes_dialog, null);
                return new ButtonHolder(v);
            }
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderHolder) {
            ((HeaderHolder) holder).bind(notes.size());
        } else if (holder instanceof ButtonHolder) {
            ((ButtonHolder) holder).bind(onButtonClickListener);
        } else if (holder instanceof ItemHolder) {
            if (notes.size() != 0) {
                ((ItemHolder) holder).bind(nameOfCouple, notes.get(position - 1), onItemClickListener);
            }
        }
    }

    @Override
    public int getItemCount() {
        return notes.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return HEADER;
        else if (position == getItemCount() - 1) return BUTTON;
        else return ITEM;
    }
}
