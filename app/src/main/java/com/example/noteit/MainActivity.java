package com.example.noteit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements INotesRVAdapter {

    NoteViewModel viewModel;
    RecyclerView mRecyclerView;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEditText = (EditText) findViewById(R.id.input);


        NotesRVAdapter adapter = new NotesRVAdapter(this,this);

        mRecyclerView.setAdapter(adapter);


        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NoteViewModel.class);

        viewModel.allNotes.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.updateList(notes);
            }
        });



    }

    @Override
    public void onItemClick(Note note) {
        viewModel.deleteNote(note);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }

    public void submitData(View view) {
        String inputText = mEditText.getText().toString();
        if (!inputText.isEmpty()){
            viewModel.insertNote(new Note(inputText));
            mEditText.setText("");
        }
    }
}