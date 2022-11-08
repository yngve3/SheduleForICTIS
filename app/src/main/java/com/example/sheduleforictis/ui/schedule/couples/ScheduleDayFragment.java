package com.example.sheduleforictis.ui.schedule.couples;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sheduleforictis.R;
import com.example.sheduleforictis.databinding.FragmentScheduleDayBinding;
import com.example.sheduleforictis.models.Couple;
import com.example.sheduleforictis.models.Day;
import com.example.sheduleforictis.ui.schedule.MainViewModel;
import com.example.sheduleforictis.ui.schedule.couples.note.edit_note.EditNoteFragment;
import com.example.sheduleforictis.ui.schedule.couples.note.list_notes.ListOfNotesFragment;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDayFragment extends Fragment implements
        RecyclerScheduleAdapter.OnItemClickListener
{

    private int positionFragment;

    private RecyclerScheduleAdapter recyclerScheduleAdapter;
    private MainViewModel viewModel;
    private Day day;
    private List<Couple> couples;

    private FragmentScheduleDayBinding binding;
    private String[] numOfCouples;

    private ListOfNotesFragment listOfNotesFragment;

    public ScheduleDayFragment(int positionFragment) {
        this.positionFragment = positionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentScheduleDayBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        numOfCouples = getResources().getStringArray(R.array.num_of_couple);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getCurrentWeekScheduleByIdGroup("96.htm").observe(getViewLifecycleOwner(), week -> {
            day = week.getDay(positionFragment);
            setupCouples();
        });
    }

    private void setupCouples() {
        couples = new ArrayList<>();
        for (Couple couple : day.getCouples()) {
            if (!couple.getNameOfCouple().equals("")) {
                couple.setNumOfCoupleStr(numOfCouples[couple.getNumOfCouple() - 1]);
                couples.add(couple);
            }
        }

        if (couples.isEmpty()) {
            binding.textView.setVisibility(View.VISIBLE);
        } else {
            setupRecyclerViewAdapter();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setupRecyclerViewAdapter() {
        if (recyclerScheduleAdapter == null) {
            recyclerScheduleAdapter = new RecyclerScheduleAdapter(couples, this);
            binding.rvSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvSchedule.setAdapter(recyclerScheduleAdapter);
        } else {
            recyclerScheduleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(Couple couple) {
        listOfNotesFragment = new ListOfNotesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nameOfCoupleInListOfNotes", couple.getNameOfCouple());
        String date = day.getDayOfWeek() + ", " + day.getDayOfMonth() + " " + day.getMonth();
        bundle.putString("dateOfCoupleInListOfNotes", date);
        bundle.putInt("numOfCoupleInListOfNotes", couple.getNumOfCouple());
        listOfNotesFragment.setArguments(bundle);
        listOfNotesFragment.show(getParentFragmentManager(), ListOfNotesFragment.TAG);
    }
}
