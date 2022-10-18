package com.malishalakshanrosa.notemanageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences profile = getSharedPreferences("profile", Context.MODE_PRIVATE);

        String loggedUserName = profile.getString("UNAME","GUEST");

        TextView tv_noteUserName = findViewById(R.id.TV_noteUserName);
        tv_noteUserName.setText(loggedUserName);

    }

    public void toAllNotes(View v){
        Intent intent = new Intent(this, AllNotesActivity.class);
        startActivity(intent);
    }

    public void toUserProfile(View v){
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }
}