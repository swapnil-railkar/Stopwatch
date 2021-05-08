package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds=0;
    private boolean running;
    private boolean wasrunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null)
        {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasrunning=savedInstanceState.getBoolean("wasrunning");
        }
        runtime(); 
    }
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasrunnig",wasrunning);
    }
    protected void onPause() {

        super.onPause();
        wasrunning=running;
        running=false;
    }
    public void onResume() {

        super.onResume();
        if (wasrunning)
            running=true;
    }
    public void onclickStart(View view)
    {
        running=true;
    }
    public void onclickStop(View view)
    {
        running=false;
    }
    public void onclickReset(View view)
    {
        running=false;
        seconds=0;
    }
    private void runtime()
    {
        final TextView timer=(TextView)findViewById(R.id.textView);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
               int hours=seconds/3600;
               int minutes=(seconds%3600)/60;
               int secs=seconds%60;
               String time=String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
               timer.setText(time);
               if(running){
                   seconds++;
               }
               handler.postDelayed(this,1000);
            }
        });
    }
}