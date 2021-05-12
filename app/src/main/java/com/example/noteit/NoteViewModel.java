package com.example.noteit;

import android.app.Application;
import android.content.res.AssetFileDescriptor;
import android.os.AsyncTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static com.example.noteit.NoteDatabase.databaseWriteExecutor;
import static java.time.chrono.IsoChronology.INSTANCE;

public class NoteViewModel extends AndroidViewModel {

    NoteRepository mNoteRepository;
    NoteDao mDao;
    LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mNoteRepository = new NoteRepository(application);
        allNotes = mNoteRepository.getAllWords();
    }
    public void deleteNote(Note note){
            mNoteRepository.delete(note);
    }

    public void insertNote(Note note){
            mNoteRepository.insert(note);

    }
}
