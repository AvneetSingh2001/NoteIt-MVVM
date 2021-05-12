package com.example.noteit;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class NoteRepository {
    private NoteDao noteDao;

    LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){

        NoteDatabase db = NoteDatabase.getDatabase(application);
        noteDao = db.NoteDao();
        allNotes = noteDao.getAllNotes();

    }

    LiveData<List<Note>> getAllWords() {
        return allNotes;
    }

    void insert(Note note){
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.insert(note);
        });
    }

    void delete(Note note){

        NoteDatabase.databaseWriteExecutor.execute(() -> {
             noteDao.delete(note);
        });

    }


}
