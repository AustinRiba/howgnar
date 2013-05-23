package com.toxiccode.howgnar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by austin on 5/22/13.
 */
public class ResultActivity extends Activity {
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        };
        Intent intent = getIntent();
        long gnarness = intent.getLongExtra(RecordRideActivity.GNARNESS, 0);
        System.out.println("from results: " + gnarness);
        TextView scoreView = (TextView)findViewById(R.id.gnar_score);
        if(gnarness < 5)
            scoreView.setText(R.string.hella_gnar);
        else if(gnarness < 10)
            scoreView.setText(R.string.kinda_gnar);
        else
            scoreView.setText(R.string.full_roadie);


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
}