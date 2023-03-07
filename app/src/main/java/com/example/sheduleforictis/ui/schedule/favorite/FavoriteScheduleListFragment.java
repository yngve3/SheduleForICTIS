package com.example.sheduleforictis.ui.schedule.favorite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sheduleforictis.databinding.FragmentFavoriteScheduleListBinding;
import com.example.sheduleforictis.models.Group;
import com.example.sheduleforictis.ui.schedule.couples.note.list_notes.ItemTouchHelperCallback;

import java.util.Objects;

public class FavoriteScheduleListFragment extends Fragment {

    public interface OnAddNewFavScheduleListener {
        void onAddNewFavSchedule();
        void onSaveListFavSchedules();
    }

    private final String TAG = "FavScheduleListFrag.TAG";

    private final OnAddNewFavScheduleListener listener;

    private FragmentFavoriteScheduleListBinding binding;
    private FavoriteScheduleListAdapter favoriteScheduleListAdapter;

    private FavoriteScheduleViewModel viewModel;

    public FavoriteScheduleListFragment(OnAddNewFavScheduleListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteScheduleListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.favSchedListToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        favoriteScheduleListAdapter = new FavoriteScheduleListAdapter(new FavoriteScheduleListAdapter.OnFavScheduleListChangeListener() {
            @Override
            public void onAddNewSchedule() {
                listener.onAddNewFavSchedule();
            }

            @Override
            public void onDeleteSchedule(Group group) {
                viewModel.deleteFavoriteGroup(group);
            }
        });

        viewModel = new ViewModelProvider(this).get(FavoriteScheduleViewModel.class);

        binding.favSchedList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.favSchedList.setAdapter(favoriteScheduleListAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(favoriteScheduleListAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.favSchedList);

        setEnabled(binding.favSchedListBtnSave, false);
        binding.favSchedListBtnSave.setOnClickListener(view1 -> listener.onSaveListFavSchedules());
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getListOfFavoriteGroups().observe(getViewLifecycleOwner(), result -> {
            if (result.isSuccess()) {
                favoriteScheduleListAdapter.setFavSchedules(result.getData());
                setEnabled(binding.favSchedListBtnSave, result.getData().size() != 0);
            } else {
                Log.d(TAG, result.getMessage());
            }
        });
    }

    private void setEnabled(View view, boolean enabled) {
        view.setEnabled(enabled);

        if (enabled) {
            view.setAlpha(1);
        } else {
            view.setAlpha(0.2f);
        }
    }
}
