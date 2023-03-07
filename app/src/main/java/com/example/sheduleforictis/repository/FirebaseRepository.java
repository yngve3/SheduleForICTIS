package com.example.sheduleforictis.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sheduleforictis.models.Group;
import com.example.sheduleforictis.models.LoginModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.core.Single;

public class FirebaseRepository {

    private final String TAG = "FirebaseRepository.TAG";

    private final String URL_DATABASE = "https://scheduleforictis-default-rtdb.europe-west1.firebasedatabase.app/";
    private final String GROUPS_ITEM = "groups";
    private final FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private FirebaseRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null)
            databaseReference = FirebaseDatabase.getInstance(URL_DATABASE)
                    .getReference(Objects.requireNonNull(firebaseAuth.getUid()));
    }
    private static FirebaseRepository instance;

    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }
        return instance;
    }

    public void insertFavoriteGroup(Group group) {
        if (firebaseAuth.getCurrentUser() != null) {
            databaseReference.child(GROUPS_ITEM).child(group.getId()).setValue(group.getName());
        }
    }

    public void insertFavoriteGroups(List<Group> groups) {
        if (firebaseAuth.getCurrentUser() != null) {
            new Thread(() -> {
                for (Group group : groups) {
                    databaseReference.child(GROUPS_ITEM).child(group.getId()).setValue(group.getName());
                }
            }).start();
        }
    }

    public void deleteFavoriteGroup(Group group) {
        if (firebaseAuth.getCurrentUser() != null) {
            databaseReference.child(GROUPS_ITEM)
                    .child(group.getId()).removeValue();
        }
    }

    public Single<List<Group>> getFavoriteGroups() {
        return Single.create(subscriber -> {
            databaseReference.child(GROUPS_ITEM).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Group> responseGroups = new ArrayList<>();
                    for (DataSnapshot group : snapshot.getChildren()) {
                        responseGroups.add(
                                new Group(Objects.requireNonNull(
                                        group.getKey()),
                                        (String) group.getValue()
                                )
                        );
                    }

                    subscriber.onSuccess(responseGroups);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    subscriber.onError(error.toException());
                }
            });
        });
    }
}
