package com.hfad.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends Activity {
    /* TODO: create KEY private final strings for use by savedInstanceState
        for "seconds", "running", and "wasRunning"
    (it doesn't really matter what these are so long as they are unique)
 */
    //private final String ...
    private final String SECONDS = "seconds";
    private final String  RUNNING = "runniung";
    private final String WASRUNNING = "wasrunniung";
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            /* TODO:  Use the Bundle get methods to restore the state
                seconds, running and wasRunning instance variables
*/
           seconds= savedInstanceState.getInt(SECONDS);
    running = savedInstanceState.getBoolean(RUNNING);
    wasRunning = savedInstanceState.getBoolean(WASRUNNING);
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
       /* TODO:  Use the Bundle put methods to save the state
                seconds, running and wasRunning instance variables
        */
        outState.putInt(SECONDS, seconds);
        outState.putBoolean(RUNNING, running);
        outState.putBoolean(WASRUNNING, wasRunning);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view) {
        running = true;
    }

    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) {
        running = false;
    }

    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    //Sets the number of seconds on the timer.
    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
