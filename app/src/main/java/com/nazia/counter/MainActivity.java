package com.nazia.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textview ,textview2;
    private Button increasebtn;
    private Button resetbtn;
    private Integer target ;
    private final String COUNT_KEY = "count";
    private final String COUNT_TARGET = "target";
    final Context context = this;
    Integer m ;
    private SharedPreferences mPreferences;
    private String sharedCounter = "com.example.android.counter";

    MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.textView);
        textview2= findViewById(R.id.tv_target);
        increasebtn = findViewById(R.id.bti);
        mPreferences = getSharedPreferences(sharedCounter, MODE_PRIVATE);
        m = mPreferences.getInt(COUNT_KEY, 0);
        target = mPreferences.getInt(COUNT_TARGET,0);


        textview.setText(m.toString());
        textview2.setText(target.toString());


        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/2.ttf");
        TextView tv = (TextView) findViewById(R.id.textView);
        TextView tg = (TextView) findViewById(R.id.tv_target);
        tv.setTypeface(tf);
        tg.setTypeface(tf);


        increasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m != 9999) {
                    m++;
                    textview.setText(m.toString());

                }

                if (String.valueOf(m).equals(textview2.getText().toString())){
                    Toast.makeText(MainActivity.this, "Target Reached : "+textview2.getText().toString(), Toast.LENGTH_SHORT).show();
                    mp = MediaPlayer.create(MainActivity.this, R.raw.timeup);
                    mp.start();

                }
            }

        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
            case R.id.dialog:
                LayoutInflater li = LayoutInflater.from(context);
                View dialogView = li.inflate(R.layout.dialog,null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(dialogView);

                final EditText targetInput = (EditText) dialogView.findViewById(R.id.dialog_edittext);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        target = Integer.valueOf(targetInput.getText().toString());
                                        textview2.setText(targetInput.getText().toString());

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                AlertDialog alertDialog =alertDialogBuilder.create();
                alertDialog.show();
                Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)btnPositive.getLayoutParams();
                layoutParams.weight = 10;
                btnNegative.setLayoutParams(layoutParams);
                btnPositive.setLayoutParams(layoutParams);
                return true;

            case R.id.rst:
                onReset();
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_UP) {
                    onIncrement();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    onDecrement();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    private void onAbout() {
        Context context = getApplicationContext();
        CharSequence text = "Simple Tasbih Counter App";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        Toast.makeText(context, text, duration).show();
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
    }



    private void onReset() {
        m =0;
        target = 0;
        textview2.setText(target.toString());
        textview.setText(m.toString());
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
/*        SharedPreferences.Editor targetEditor = nPreferences.edit();
        targetEditor.clear();
        targetEditor.apply();*/
        preferencesEditor.clear();
        preferencesEditor.apply();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void resetTextView(View view) {
        m = 0;
        target = 0;
        textview2.setText(target.toString());
        textview.setText(m.toString());
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();

    }


    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_TARGET,target);
        preferencesEditor.putInt(COUNT_KEY, m);
        preferencesEditor.apply();

    }


    protected void onExit() {
        MainActivity.this.finish();

        System.exit(0);

    }

    protected void onDecrement() {
        if (m != 0) {
            m--;
            textview.setText(m.toString());
        }
        if (String.valueOf(m).equals(textview2.getText().toString())){
            Toast.makeText(MainActivity.this, "Target Reached : "+textview2.getText().toString(), Toast.LENGTH_SHORT).show();
            mp = MediaPlayer.create(MainActivity.this, R.raw.timeup);
            mp.start();
        }
    }
    protected void onIncrement() {
        if (m != 9999){
            m++;
        textview.setText(m.toString());}
        
        if (String.valueOf(m).equals(textview2.getText().toString())){
            Toast.makeText(MainActivity.this, "Target Reached : "+textview2.getText().toString(), Toast.LENGTH_SHORT).show();
            mp = MediaPlayer.create(MainActivity.this, R.raw.timeup);
            mp.start();

        }
    }


    public void onBook() {
        Intent myintent = new Intent(MainActivity.this, ImagesActivity.class);
        startActivity(myintent);


    }
}

