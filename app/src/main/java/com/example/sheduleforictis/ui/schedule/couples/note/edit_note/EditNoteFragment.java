package com.example.sheduleforictis.ui.schedule.couples.note.edit_note;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.databinding.FragmentEditNoteBinding;
import com.example.sheduleforictis.ui.schedule.MainActivity;

import java.util.Objects;

public class EditNoteFragment extends Fragment {

    public static String TAG = "EditNoteFragment.TAG";

    private FragmentEditNoteBinding binding;
    private EditNoteRecyclerAdapter editNoteRecyclerAdapter;

    private String textOfNote;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        editNoteRecyclerAdapter = new EditNoteRecyclerAdapter(getArguments()
                .getString("textNote"), (text) -> {
                    textOfNote = text;
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        binding.editableNote.setLayoutManager(linearLayoutManager);
        binding.editableNote.setAdapter(editNoteRecyclerAdapter);

        ((MainActivity) requireActivity()).setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        binding.tvDateOfNote.setText(getArguments().getString("dateOfNote"));
        binding.tvNameOfCoupleNote.setText(getArguments().getString("nameOfCoupleNote"));

        binding.btnSaveNote.setOnClickListener(view1 -> {
            getParentFragmentManager().beginTransaction().remove(this).commit();
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            if (textOfNote != null)
                Toast.makeText(getContext(), textOfNote, Toast.LENGTH_LONG).show();
        });

    }
}
