package com.example.noteit;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Query("Select * from notes_table order by id ASC")
    LiveData<List<Note>> getAllNotes();
}
