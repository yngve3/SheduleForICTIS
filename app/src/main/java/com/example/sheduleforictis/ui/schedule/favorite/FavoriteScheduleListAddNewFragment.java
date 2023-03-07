package com.example.sheduleforictis.ui.schedule.favorite;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.sheduleforictis.databinding.FragmentFavoriteScheduleAddBinding;
import com.example.sheduleforictis.models.Group;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteScheduleListAddNewFragment extends Fragment implements SearchResultAdapter.OnSearchResultTouchListener {

    public interface OnSaveNewScheduleListener {
        void onSaveNewSchedule();
    }

    private FragmentFavoriteScheduleAddBinding binding;
    private final OnSaveNewScheduleListener listener;
    private SearchResultAdapter adapter;
    private FavoriteScheduleViewModel viewModel;

    public FavoriteScheduleListAddNewFragment(OnSaveNewScheduleListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteScheduleAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(FavoriteScheduleViewModel.class);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.favSchedAddToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        enableBtn(binding.favSchedAddBtnSave, false);

        adapter = new SearchResultAdapter(this);
        binding.searchResultsList.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        binding.searchResultsList.setAdapter(adapter);

        Objects.requireNonNull(binding.favSchedAddEtSearch.getEditText())
                .setOnEditorActionListener((textView, i, keyEvent) -> {
            //TODO Добавить проверку запроса с помощью регулярного выражения
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(binding.favSchedAddEtSearch.getEditText().getText().toString()).observe(getViewLifecycleOwner(), result -> {
                    if (result.isSuccess()) {
                        if (result.dataIsNull()) {
                            Toast.makeText(requireContext(), "Таких групп нет", Toast.LENGTH_SHORT).show();
                        } else {
                            adapter.setResults(result.getData());
                        }
                    } else {
                        Toast.makeText(requireContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
            return false;
        });

        Objects.requireNonNull(binding.favSchedAddEtSearch.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.preSaveRequestModel(null);
                enableBtn(binding.favSchedAddBtnSave, false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.favSchedAddBtnSave.setOnClickListener(view1 -> {
            viewModel.saveFavoriteSchedule();
            listener.onSaveNewSchedule();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.favSchedAddEtSearch.requestFocus();
    }

    @Override
    public void onTouch(Group group) {
        setText(Objects.requireNonNull(binding.favSchedAddEtSearch.getEditText()), group.getName());
        adapter.setResults(new ArrayList<>());
        viewModel.getGroupScheduleByID(group.getId()).observe(getViewLifecycleOwner(), result -> {
            if (result.isSuccess()) enableBtn(binding.favSchedAddBtnSave, true);
        });
    }

    private void enableBtn(Button btn, boolean isEnable) {
        btn.setEnabled(isEnable);

        if (isEnable) {
            btn.setAlpha(1);
        } else {
            btn.setAlpha(0.2f);
        }
    }

    private void setText(EditText editText, String text) {
        editText.setText(text);
        editText.setSelection(Objects.requireNonNull(binding.favSchedAddEtSearch.getEditText()).getText().length());
    }
}
