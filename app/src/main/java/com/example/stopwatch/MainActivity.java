package com.example.stopwatch;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.SystemClock;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;


public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCenter.start(getApplication(), "9d531afe-635d-4bed-b88f-3385415566ee",
                Analytics.class, Crashes.class);

        //TO distribute
        AppCenter.start(getApplication(), "{9d531afe-635d-4bed-b88f-3385415566ee}", Distribute.class);
        //To track a simple event simply use the trackEvent()
        Analytics.trackEvent("My custom event");
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Stop WatchGO: %s");
  //      Distribute.isEnabled();


    }

    //This is the logic for the stop watch
    public void startChronometer(View v) {
        Analytics.trackEvent("Start button clicked");
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }

    }

    //This is the logic for the pause watch

    public void pauseChronometer(View v) {
        Analytics.trackEvent("Pause button clicked");
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }

    }

    public void resetChronometer(View v) {
        Analytics.trackEvent("Reset button clicked");
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
//        throw new RuntimeException("This is a crash");
        Crashes.generateTestCrash();
    }



}
