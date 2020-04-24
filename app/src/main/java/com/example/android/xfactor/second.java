package com.example.android.xfactor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Collections;
import java.util.Scanner;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class second extends AppCompatActivity {
    private static final String TAG = "second";
    int input = 0;
    int ans = 0;
    int current_score = 0;
    int max_score = 0;
    int time;
    CountDownTimer countDownTimer;
    ArrayList<Integer> list = new ArrayList<Integer>();
    Random rand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);

        Intent IncomingIntent = getIntent();
        Bundle bundle = IncomingIntent.getExtras();
        if (bundle != null) {
            input = bundle.getInt("number");
            current_score = bundle.getInt("score");
            max_score = bundle.getInt("max");
        }
        if (savedInstanceState != null) {
            list.add(savedInstanceState.getInt("b1"));
            list.add(savedInstanceState.getInt("b2"));
            list.add(savedInstanceState.getInt("b3"));
            ans = savedInstanceState.getInt("ans");
            time = savedInstanceState.getInt("sec_left");

            countDownTimer = new CountDownTimer(time * 1000, 1000) {
                TextView t = findViewById(R.id.timer);

                @Override
                public void onTick(long millisUntilFinished) {
                    t.setText(time + "sec left");
                    time--;
                }

                @Override
                public void onFinish() {
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(80);

                    Toast txt = Toast.makeText(getApplicationContext(), "Time Up. Correct answer is " + ans, Toast.LENGTH_SHORT);
                    txt.show();
                    changeScore("no");
                    goback();
                }
            };
            btn(list.get(0), R.id.button1);
            btn(list.get(1), R.id.button2);
            btn(list.get(2), R.id.button3);
            countDownTimer.start();
        } else {
            ArrayList<Integer> factors = new ArrayList<Integer>();
            int c = 0;
            for (int i = 2; i <= input; i++) {
                if (input % i == 0) {
                    factors.add(i);
                    c++;
                }
            }
            list.add(factors.get(rand.nextInt(c)));
            ans = list.get(0);
            time = 6;
            countDownTimer = new CountDownTimer(time * 1000, 1000) {
                TextView t = findViewById(R.id.timer);

                @Override
                public void onTick(long millisUntilFinished) {
                    t.setText(time + "sec left");
                    time--;
                }

                @Override
                public void onFinish() {
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(80);

                    Toast txt = Toast.makeText(getApplicationContext(), "Time Up. Correct answer is " + ans, Toast.LENGTH_SHORT);
                    txt.show();
                    changeScore("no");
                    goback();
                }
            };
            options(input);
        }
    }

    public void btn(int x, int id) {
        TextView v = findViewById(id);
        v.setText(Integer.toString(x));
    }

    public int choice(int x) {
        int num;
        if (x > 5) {
            do {
                num = rand.nextInt(x);
            } while (x % num == 0);
            return num;
        } else
            return rand.nextInt(x);
    }

    public void changeScore(String str) {
        TextView v = findViewById(R.id.current_score);
        if (str == "yes")
            current_score++;
        else if (str == "no")
            current_score = 0;
        v.setText("Score Streak : " + Integer.toString(current_score));
    }

    public void options(int i) {
        countDownTimer.start();
        if (i == 3) {
            Log.d(TAG, "options: 3 is here");
            list.add(2);
            list.add(2);
        } else if (i == 4) {
            list.add(3);
            list.add(3);
        } else {
            int option1 = choice(i);
            list.add(option1);
            int option2 = choice(i);
            list.add(option2);
        }
        Collections.shuffle(list);
        btn(list.get(0), R.id.button1);
        btn(list.get(1), R.id.button2);
        btn(list.get(2), R.id.button3);
    }

    public void checkAns1(View v) {
        countDownTimer.cancel();
        LinearLayout layout = findViewById(R.id.layout);
        int n = list.get(0);
        if (n == ans) {
            Toast txt = Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT);
            txt.show();
            layout.setBackgroundColor(Color.GREEN);
            changeScore("yes");
        } else {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(80);

            Toast txt = Toast.makeText(this, "Wrong Answer\nCorrect answer is " + ans, Toast.LENGTH_SHORT);
            txt.show();
            layout.setBackgroundColor(Color.RED);
            changeScore("no");
        }
        goback();
    }

    public void checkAns2(View v) {
        countDownTimer.cancel();
        LinearLayout layout = findViewById(R.id.layout);
        int n = list.get(1);
        if (n == ans) {
            Toast txt = Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT);
            txt.show();
            layout.setBackgroundColor(Color.GREEN);
            changeScore("yes");
        } else {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(80);

            Toast txt = Toast.makeText(this, "Wrong Answer\nCorrect answer is " + ans, Toast.LENGTH_SHORT);
            txt.show();
            layout.setBackgroundColor(Color.RED);
            changeScore("no");
        }
        goback();
    }

    public void checkAns3(View v) {
        countDownTimer.cancel();
        LinearLayout layout = findViewById(R.id.layout);
        int n = list.get(2);
        if (n == ans) {
            Toast txt = Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT);
            txt.show();
            layout.setBackgroundColor(Color.GREEN);
            changeScore("yes");
        } else {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(80);

            Toast txt = Toast.makeText(this, "Wrong Answer\nCorrect answer is " + ans, Toast.LENGTH_SHORT);
            txt.show();
            layout.setBackgroundColor(Color.RED);

            changeScore("no");
        }
        goback();
    }

    public void goback() {
        SharedPreferences pref = getSharedPreferences("myscore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("key", current_score);
        editor.apply();

        if (current_score != 0 && current_score > max_score) {
            SharedPreferences prefs = getSharedPreferences("max", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = prefs.edit();
            editor1.putInt("key1", current_score);
            editor1.apply();
        }

        finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("b1", list.get(0));
        outState.putInt("b2", list.get(1));
        outState.putInt("b3", list.get(2));
        outState.putInt("ans", ans);
        outState.putInt("sec_left", time);
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }
}


