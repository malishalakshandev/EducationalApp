package com.malishalakshanrosa.notemanageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewEditNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_note);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView headerFileName = findViewById(R.id.TV_noteName);
        EditText et_noteViewEdit = findViewById(R.id.ET_noteViewEdite);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String createdFileName = bundle.getString("FileName");

        try {

            FileInputStream fis = openFileInput(createdFileName);
            byte[] chars = new byte[fis.available()];
            fis.read(chars);
            fis.close();

            String createdNote = new String(chars);

            headerFileName.setText(createdFileName);
            et_noteViewEdit.setText(createdNote);

        }catch (Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, "Error: Cannot read the file", Toast.LENGTH_LONG).show();
        }

    }

    public void saveEditNote(View view){

        TextView headerFileName = findViewById(R.id.TV_noteName);
        EditText et_noteEditView = findViewById(R.id.ET_noteViewEdite);

        try {

            String newEditNote = et_noteEditView.getText().toString();

//            String FileNameLocation = getFilesDir() + File.separator + headerFileName.getText().toString();
            String fileName = headerFileName.getText().toString();

//            FileOutputStream fos = new FileOutputStream(FileNameLocation);
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(newEditNote.getBytes());
            fos.close();

            Toast.makeText(this, "Editted note saved successfully", Toast.LENGTH_LONG).show();

        }catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, "Error: Failed to save this ediited note", Toast.LENGTH_LONG).show();
        }

    }

    public void deleteFile(View v){

        AlertDialog.Builder alert = new AlertDialog.Builder(ViewEditNoteActivity.this);
        alert.setTitle("DELETE");
        alert.setMessage("Are you sure you want to delete?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                TextView headerFileName = findViewById(R.id.TV_noteName);

                File fileLocation = getFilesDir();
                File file = new File(fileLocation, headerFileName.getText().toString());
                boolean deleted = file.delete();

                if (deleted){
                    Toast.makeText(ViewEditNoteActivity.this, "File deleted successfully", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(ViewEditNoteActivity.this, AllNotesActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(ViewEditNoteActivity.this, "Error: File note deleted", Toast.LENGTH_LONG).show();
                }

                dialog.dismiss();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(ViewEditNoteActivity.this, "File not deleted", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        alert.show();






    }


}