package com.example.sheduleforictis.ui.schedule.couples.note.list_notes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.databinding.FragmentNotesListBinding;
import com.example.sheduleforictis.models.Note;
import com.example.sheduleforictis.ui.schedule.couples.note.NotesViewModel;
import com.example.sheduleforictis.ui.schedule.couples.note.edit_note.EditNoteFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;


public class ListOfNotesFragment extends BottomSheetDialogFragment implements
        ListOfNotesRecyclerAdapter.OnNotesChangedListener
{


    public static final String TAG = "ListOfNotesFragment.TAG";

    private FragmentNotesListBinding binding;
    private ListOfNotesRecyclerAdapter listOfNotesRecyclerAdapter;
    private List<Note> notes;

    private NotesViewModel viewModel;

    private String nameOfCouple;
    private int numOfCouple;
    private String dateOfCouple;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotesListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        assert getArguments() != null;
        nameOfCouple = getArguments().getString("nameOfCoupleInListOfNotes");
        numOfCouple = getArguments().getInt("numOfCoupleInListOfNotes");
        dateOfCouple = getArguments().getString("dateOfCoupleInListOfNotes");

        viewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        assert getArguments() != null;
        viewModel.getNotesOfCouple(dateOfCouple, numOfCouple).observe(getViewLifecycleOwner(), notes -> {
            viewModel.setIdNotes(notes);
            this.notes = notes;
            setupRecyclerViewAdapter();
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @SuppressLint("NotifyDataSetChanged")
    private void setupRecyclerViewAdapter() {
        assert getArguments() != null;
        if (listOfNotesRecyclerAdapter == null) {
            listOfNotesRecyclerAdapter = new ListOfNotesRecyclerAdapter(nameOfCouple, notes, this);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recyclerView.setAdapter(listOfNotesRecyclerAdapter);

            ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(listOfNotesRecyclerAdapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(binding.recyclerView);
        } else {
            listOfNotesRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateNote() {
        viewModel.editNote(new Note("", dateOfCouple, numOfCouple), nameOfCouple);
        showEditNoteFragment();
    }

    @Override
    public void onEditNote(Note note) {
        viewModel.editNote(note, nameOfCouple);
        showEditNoteFragment();
    }

    @Override
    public void onDeleteNote(Note note) {
        viewModel.deleteNote(note);
    }

    public void showEditNoteFragment() {
        assert getArguments() != null;
        dismiss();
        getParentFragmentManager().beginTransaction()
                .replace(R.id.root, new EditNoteFragment())
                .addToBackStack(null)
                .commit();
    }
}
