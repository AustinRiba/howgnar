package com.toxiccode.howgnar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;

/**
 * Created by austin on 5/22/13.
 */
public class RecordRideActivity extends Activity {
    public static final String GNARNESS = "com.toxiccode.howgnar.GNARNESS";
    private Calendar startCal;
    private Calendar endCal;
    private int gnars;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        startCal = Calendar.getInstance();
        gnars = 1;

        setContentView(R.layout.activity_record_ride);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void countAccel(View view){
        this.gnars++;
        System.out.println(gnars);
    }

    public void endRecord(View view){
        this.endCal = Calendar.getInstance();
        long difference = ((endCal.getTime().getTime()-startCal.getTime().getTime())/1000)+1;
        long gnarness = difference/this.gnars;
        //gnarness: lower is gnarlier
        System.out.println("difference: " + difference + "gnarness: " + gnarness);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(GNARNESS, gnarness);
        startActivity(intent);

    }

}