package com.malishalakshanrosa.notemanageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
    }

    public  void saveNewNote(View v){

        EditText et_newNote = findViewById(R.id.ET_newNote);
        String newNote = et_newNote.getText().toString();

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
            String sdf_date = sdf.format(new Date());
            String uniqueFileName = getFilesDir() + File.separator + sdf_date + ".txt";

            FileOutputStream fos = new FileOutputStream(uniqueFileName);
            fos.write(newNote.getBytes());
            fos.close();
            Toast.makeText(this, "Note saved successfully", Toast.LENGTH_LONG).show();

        }catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, "Error: Failed to save the note", Toast.LENGTH_LONG).show();
        }

    }
}