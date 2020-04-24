package com.example.android.xfactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;

import static java.util.TimeZone.SHORT;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    int inputNumber = 0;
    int score = 0;
    int maxscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("max", Context.MODE_PRIVATE);

            maxscore = prefs.getInt("key1", 0);
            TextView v = findViewById(R.id.max_score);
            v.setText("Max Score : " + Integer.toString(maxscore));

            TextView view = findViewById(R.id.score);
            view.setText("Score Streak : " + score);

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("myscore", 0);
        prefs.edit().clear().commit();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        SharedPreferences pref = getSharedPreferences("myscore", Context.MODE_PRIVATE);
        score = pref.getInt("key", 0);
        TextView v = findViewById(R.id.score);
        v.setText("Score Streak : " + Integer.toString(score));

        SharedPreferences prefs = getSharedPreferences("max", Context.MODE_PRIVATE);
        if (score > maxscore) {
            maxscore = prefs.getInt("key1", 0);
            TextView view = findViewById(R.id.max_score);
            view.setText("Max Score : " + Integer.toString(maxscore));
            if (maxscore != 0) {
                Toast txt = Toast.makeText(this, "Congrats ! You've made a new high score ", Toast.LENGTH_SHORT);
                txt.show();
            }
        }

    }

    public void factor(View v) {
        boolean valid=true;
        try {
            EditText factorNumber = findViewById(R.id.text_number);
            inputNumber = Integer.parseInt(factorNumber.getText().toString());

        }catch(NumberFormatException e){
            valid=false;}

        if(valid){
        if (inputNumber == 0 || inputNumber == 1) {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(80);
            Toast msg = Toast.makeText(getApplicationContext(), "Input a valid number\nYou can't factorise " + inputNumber, Toast.LENGTH_LONG);
            msg.show();
        } else if (inputNumber == 2) {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(80);
            Toast txt = Toast.makeText(getApplicationContext(), "You can have 1 and 2 as factor. Both are correct.", Toast.LENGTH_LONG);
            txt.show();
        } else {
            Intent intent = new Intent(this, second.class);
            intent.putExtra("number", inputNumber);
            intent.putExtra("score", score);
            intent.putExtra("max",maxscore);
            startActivity(intent);
        }
    }else{
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(80);
            Toast msg = Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_LONG);
            msg.show();
        }
        }
}
