package com.malishalakshanrosa.notemanageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

public class AllNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);
    }

    @Override
    protected void onResume() {
        super.onResume();

        File folder = getFilesDir();
        String[] files = folder.list();

        ListView lv_allNotes = findViewById(R.id.LV_allNotes);

        // Set Adapter to ListView for view files in Activity
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,layout, files);
        lv_allNotes.setAdapter(adapter);

        // View the Note in new Activity when click on it
        lv_allNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView tv = (TextView) view;
                String clickedFileName = tv.getText().toString();

                Intent intent = new Intent(AllNotesActivity.this, ViewEditNoteActivity.class);
                intent.putExtra("FileName",clickedFileName);
                startActivity(intent);
            }
        });

    }

    public void toNewNote(View v){
        Intent intent = new Intent(this, NewNoteActivity.class);
        startActivity(intent);
    }

    public void toViewEditNote(View v){
        Intent intent = new Intent(this, ViewEditNoteActivity.class);
        startActivity(intent);
    }
}