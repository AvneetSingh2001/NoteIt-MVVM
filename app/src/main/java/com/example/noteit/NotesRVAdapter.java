package com.example.noteit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesRVAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    List<Note> allNotes = new ArrayList<Note>();
    Context context;
    INotesRVAdapter listener;

    public NotesRVAdapter(Context context, INotesRVAdapter listener){
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_notes,parent,false);
        NoteViewHolder viewHolder = new NoteViewHolder(v);
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(allNotes.get(viewHolder.getAdapterPosition()));
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = allNotes.get(position);
        holder.textView.setText(currentNote.getText());
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    void updateList(List<Note> newList){
        allNotes.clear();
        allNotes = newList;

        notifyDataSetChanged();
    }
}

class NoteViewHolder extends RecyclerView.ViewHolder{

    TextView textView;
    ImageButton deleteButton;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.text);
        deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);

    }
}

interface INotesRVAdapter{
    void onItemClick(Note note);
}


