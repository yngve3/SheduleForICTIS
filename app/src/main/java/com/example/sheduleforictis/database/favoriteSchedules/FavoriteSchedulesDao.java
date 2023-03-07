package com.example.sheduleforictis.database.favoriteSchedules;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sheduleforictis.models.Group;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface FavoriteSchedulesDao {

    @Insert(onConflict = REPLACE)
    void insertFavoriteSchedule(Group group);

    @Insert(onConflict = REPLACE)
    void insertFavoriteSchedules(List<Group> groups);

    @Delete
    void deleteFavoriteSchedule(Group group);

    @Query("SELECT * FROM group_table")
    Flowable<List<Group>> getFavoriteSchedules();
}
