package com.example.noteit;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    int id = 0;

    @NonNull
    @ColumnInfo(name = "text")
    String Text;

    public Note(@NonNull String Text){
        this.Text = Text;
    }

    public String getText(){
        return this.Text;
    }

}
