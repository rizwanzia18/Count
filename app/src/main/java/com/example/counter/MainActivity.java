package com.example.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textview;
    private Button increasebtn;
    private Button resetbtn;

    private final String COUNT_KEY = "count";
    Integer m=0;
    private SharedPreferences mPreferences;
    private String sharedCounter = "com.example.android.counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.textView);
        increasebtn = findViewById(R.id.bti);
        resetbtn = findViewById(R.id.btr);
               mPreferences = getSharedPreferences(sharedCounter, MODE_PRIVATE);
        m = mPreferences.getInt(COUNT_KEY, 0);
        textview.setText(m.toString());

        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/2.ttf");
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setTypeface(tf);

        increasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m!= 9999) {
                    m++;
                    textview.setText(m.toString());
                }
            }

        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit:
                onExit();
                return true;
            case R.id.dc:
                onDecrement();
                return true;
            case R.id.book:
                onBook();
                return true;

            case R.id.abt:
                onAbout();
                return true;
            case R.id.settings:
                onSetting();
                return true;
            case R.id.rst:
                onReset();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void resetTextView(View view){
        m =0;
        textview.setText(m.toString());
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();

    }


    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY,m);
        preferencesEditor.apply();
        }


    protected void onExit(){
        MainActivity.this.finish();
        System.exit(0);

    }
    protected void onDecrement(){
        if(m!=0)
        m--;
        textview.setText(m.toString());
    }
    public void onBook(){
        Intent myintent = new Intent(MainActivity.this,ImagesActivity.class);
        startActivity(myintent);
    }
    public void onSetting(){
        Intent setintent = new Intent(MainActivity.this,SettingsActivity.class);
        startActivity(setintent);
    }
    public void onReset() {
        resetbtn.setVisibility(View.VISIBLE);



       /* m =0;
        textview.setText(m.toString());
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();*/


    }
    protected void onAbout(){

        Context context = getApplicationContext();
        CharSequence text = "Simple Tasbih Counter App";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text,duration);
        Toast.makeText(context,text,duration).show();
        toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
    }
}

