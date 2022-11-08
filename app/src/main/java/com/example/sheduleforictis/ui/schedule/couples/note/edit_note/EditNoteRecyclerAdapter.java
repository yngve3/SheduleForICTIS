package com.example.sheduleforictis.ui.schedule.couples.note.edit_note;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheduleforictis.R;

public class EditNoteRecyclerAdapter extends RecyclerView.Adapter<EditNoteRecyclerAdapter.ViewHolder> {
    public interface OnTextChangedListener {
        void onTextChanged(String text);
    }

    private String noteText;
    private final EditNoteRecyclerAdapter.OnTextChangedListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private EditText etTextNote;

        public ViewHolder(@NonNull View v) {
            super(v);
            etTextNote = v.findViewById(R.id.etTextNote);
        }

        public void bind(final String note, final EditNoteRecyclerAdapter.OnTextChangedListener listener) {
            etTextNote.setText(note);
            etTextNote.requestFocus();

            etTextNote.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    listener.onTextChanged(editable.toString());
                }
            });
        }
    }

    public EditNoteRecyclerAdapter(String noteText, EditNoteRecyclerAdapter.OnTextChangedListener listener) {
        this.listener = listener;
        this.noteText = noteText;
    }

    @NonNull
    @Override
    public EditNoteRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_note_edit, parent, false);

        return new EditNoteRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EditNoteRecyclerAdapter.ViewHolder holder, int position) {
        holder.bind(noteText, listener);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
