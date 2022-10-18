package com.malishalakshanrosa.notemanageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {

    EditText et_userName, et_contactNumber, et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        et_userName = findViewById(R.id.ET_userName);
        et_contactNumber = findViewById(R.id.ET_userContactNumber);
        et_email = findViewById(R.id.ET_userEmail);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences profile = getSharedPreferences("profile", Context.MODE_PRIVATE);

        String uname = profile.getString("UNAME", "");
        String ucontact = profile.getString("UCONTACT","");
        String uemail = profile.getString("UEMAIL","");

        et_userName.setText(uname);
        et_contactNumber.setText(ucontact);
        et_email.setText(uemail);

    }

    public void saveUserProfile(View v){

        //        get text fields values
        String uname = et_userName.getText().toString();
        String ucontact = et_contactNumber.getText().toString();
        String uemail = et_email.getText().toString();

        //        access created profile file in shared preferences
        SharedPreferences profile = getSharedPreferences("profile", Context.MODE_PRIVATE);

        //        enable edit permission to profile and assign it to editor variable
        SharedPreferences.Editor editor = profile.edit();

        //        add entered string values in EditText into editor variable
        editor.putString("UNAME", uname);
        editor.putString("UCONTACT", ucontact);
        editor.putString("UEMAIL", uemail);

        //        save the data into profile file
        editor.commit();

        //        show success message to user
        Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show();

    }


}