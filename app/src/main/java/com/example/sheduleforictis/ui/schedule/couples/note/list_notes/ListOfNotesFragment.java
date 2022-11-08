package com.example.sheduleforictis.ui.schedule.couples.note.list_notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.databinding.ActivityMainBinding;
import com.example.sheduleforictis.databinding.NotesDialogBinding;
import com.example.sheduleforictis.models.Note;
import com.example.sheduleforictis.ui.schedule.couples.note.edit_note.EditNoteFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class ListOfNotesFragment extends BottomSheetDialogFragment implements
        ListOfNotesRecyclerAdapter.OnButtonClickListener,
        ListOfNotesRecyclerAdapter.OnItemClickListener
{


    public static final String TAG = "ListOfNotesFragment";

    private NotesDialogBinding binding;
    private ListOfNotesRecyclerAdapter listOfNotesRecyclerAdapter;
    private List<Note> notes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = NotesDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        notes = new ArrayList<>();
        assert getArguments() != null;
        listOfNotesRecyclerAdapter = new ListOfNotesRecyclerAdapter(getArguments().getString("nameOfCoupleInListOfNotes"),
                notes, this, this
        );

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(listOfNotesRecyclerAdapter);
    }

    @Override
    public void onButtonClick() {
        showEditNoteFragment("");
    }

    @Override
    public void onItemClick(Note note) {
        showEditNoteFragment(note.getTextOfNote());
    }

    public void showEditNoteFragment(String noteText) {
        assert getArguments() != null;
        dismiss();
        Bundle bundle = new Bundle();
        bundle.putString("textNote", noteText);
        bundle.putString("nameOfCoupleNote", getArguments().getString("nameOfCoupleInListOfNotes"));
        bundle.putString("dateOfNote", getArguments().getString("dateOfCoupleInListOfNotes"));
        EditNoteFragment editNoteFragment = new EditNoteFragment();
        editNoteFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.root, editNoteFragment)
                .addToBackStack(null)
                .commit();
    }
}
