package com.example.sheduleforictis.ui.schedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sheduleforictis.models.Note;
import com.example.sheduleforictis.repository.NotesRepository;
import com.example.sheduleforictis.repository.ScheduleWeekRepository;
import com.example.sheduleforictis.models.Week;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<Week> weekSchedule;
    private final ScheduleWeekRepository scheduleWeekRepository;
    private final NotesRepository notesRepository;

    public MainViewModel() {
        weekSchedule = new MutableLiveData<>();
        scheduleWeekRepository = ScheduleWeekRepository.getInstance();
        notesRepository = NotesRepository.getInstance();
    }

    public LiveData<Week> getCurrentWeekScheduleFromNet(String id) {
        //weekSchedule = scheduleWeekRepository.getCurrentWeekScheduleByIdGroupFromNet(id);
        return weekSchedule;
    }

    public LiveData<Week> getCurrentWeekScheduleByIdGroup(String id) {
        weekSchedule = loadCurrentWeekSchedule(id);
        return weekSchedule;
    }

    public void insertNotesInBD(List<Note> notes) {
        notesRepository.insertNotes(notes);
    }

    private LiveData<Week> loadCurrentWeekSchedule(String id) {
        return new MutableLiveData<>();
    }

}
