package com.example.sheduleforictis.ui.schedule.couples.note.list_notes;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.models.Note;

import java.util.List;

public class ListOfNotesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperCallback.ItemTouchHelperAdapter {

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onItemDismiss(int position) {
        onNotesChangedListener.onDeleteNote(notes.get(position - 1));
        notes.remove(position - 1);
        notifyDataSetChanged();
    }

    public interface OnNotesChangedListener {
        void onEditNote(Note note);
        void onCreateNote();
        void onDeleteNote(Note note);
    }

    private final String nameOfCouple;
    private final List<Note> notes;
    private final OnNotesChangedListener onNotesChangedListener;

    private final int HEADER = 0;
    private static final int ITEM = 1;
    private final int BUTTON = 2;

    public ListOfNotesRecyclerAdapter(
            String nameOfCouple,
            List<Note> notes,
            OnNotesChangedListener onNotesChangedListener
    ) {
        this.onNotesChangedListener = onNotesChangedListener;
        this.notes = notes;
        this.nameOfCouple = nameOfCouple;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER: {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_notes_list_title, null);
                return new HeaderHolder(v);
            }
            case ITEM: {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_notes_list_note, null);
                return new ItemHolder(v);
            }
            case BUTTON: {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_notes_list_btn, null);
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
            ((ButtonHolder) holder).bind(onNotesChangedListener);
        } else if (holder instanceof ItemHolder) {
            if (notes.size() != 0) {
                ((ItemHolder) holder).bind(nameOfCouple, notes.get(position - 1), onNotesChangedListener);
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

        public void bind(final String nameOfCouple, final Note note, final OnNotesChangedListener onNotesChangedListener) {

            tvTextNote.setText(note.getTextOfNote());
            tvDateOfNote.setText(note.getDateOfNote());
            tvNoteNameOfCouple.setText(nameOfCouple);

            itemView.setOnClickListener(view -> { onNotesChangedListener.onEditNote(note); });
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

        public void bind(final OnNotesChangedListener onNotesChangedListener) {
            btnCreateNote.setOnClickListener(view -> onNotesChangedListener.onCreateNote());
        }
    }
}
