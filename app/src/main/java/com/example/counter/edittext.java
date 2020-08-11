package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class edittext extends AppCompatActivity {
    private EditText editText;
    private String target;
    private Button button;
    private TextView targetview;
   // private SharedPreferences mPreferences;
    private final String TARGET_KEY = "targetvalue";
    private String sharedCounter = "com.example.android.counter";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        editText =findViewById(R.id.etn);
        button = findViewById(R.id.button);
        targetview = findViewById(R.id.tgtview);
        //mPreferences = getSharedPreferences(sharedCounter, MODE_PRIVATE);
       // target = mPreferences.getString(TARGET_KEY, target);
        //Toast.makeText(edittext.this, target, Toast.LENGTH_SHORT).show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                target = editText.getText().toString();
//                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
//                preferencesEditor.putString(TARGET_KEY, target);
//                preferencesEditor.apply();



                Intent back = new Intent(edittext.this,MainActivity.class);
                back.putExtra("target",target);
                startActivity(back);
            }
        });



    }
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
//        preferencesEditor.putString(TARGET_KEY, target);
//        preferencesEditor.apply();
//    }

}