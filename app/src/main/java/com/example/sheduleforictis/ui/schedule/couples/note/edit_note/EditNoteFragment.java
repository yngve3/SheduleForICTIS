package com.example.sheduleforictis.ui.schedule.couples.note.edit_note;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sheduleforictis.databinding.FragmentEditNoteBinding;
import com.example.sheduleforictis.models.Note;
import com.example.sheduleforictis.ui.schedule.MainActivity;
import com.example.sheduleforictis.ui.schedule.couples.note.NotesViewModel;

import java.util.Objects;

public class EditNoteFragment extends Fragment {

    public static String TAG = "EditNoteFragment.TAG";

    private FragmentEditNoteBinding binding;
    private EditNoteRecyclerAdapter editNoteRecyclerAdapter;

    private NotesViewModel viewModel;

    private String newText;

    private Note note;
    private String nameOfCouple;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        note = viewModel.getEditableNote();
        nameOfCouple = viewModel.getNameOfCouple();
        editNoteRecyclerAdapter = new EditNoteRecyclerAdapter(note.getTextOfNote(), (text) -> newText = text);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        binding.editableNote.setLayoutManager(linearLayoutManager);
        binding.editableNote.setAdapter(editNoteRecyclerAdapter);

        ((MainActivity) requireActivity()).setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        binding.tvDateOfNote.setText(note.getDateOfNote());
        binding.tvNameOfCoupleNote.setText(nameOfCouple);

        binding.btnSaveNote.setOnClickListener(view1 -> {
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            if (newText != null) {
                note.changeText(newText);
                viewModel.insertNote(note);
                getParentFragmentManager().beginTransaction().remove(this).commit();
            } else {
                if (note.getTextOfNote().equals("")) {
                    Toast.makeText(requireContext(), "Текст заметки не должен быть пустым", Toast.LENGTH_LONG)
                            .show();
                } else {
                    getParentFragmentManager().beginTransaction().remove(this).commit();
                }
            }
        });

    }
}
