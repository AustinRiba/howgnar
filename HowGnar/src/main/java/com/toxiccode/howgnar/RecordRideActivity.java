package com.toxiccode.howgnar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by austin on 5/22/13.
 */
public class RecordRideActivity extends Activity implements SensorEventListener {
    public static final String GNARNESS = "com.toxiccode.howgnar.GNARNESS";
    private Calendar startCal;
    private Calendar endCal;
    private int gnars;
    private SensorManager sensorManager;
    double ax,ay,az;

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
        gnars = 0;
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

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
        float difference = (endCal.getTime().getTime()-startCal.getTime().getTime())/1000;
        float gnarness = this.gnars/difference; //gnars per second
        System.out.println("difference: " + difference + "gnarness: " + gnarness);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(GNARNESS, gnarness);
        startActivity(intent);

    }
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ax=event.values[0];
            ay=event.values[1];
            az=event.values[2];
            if((ax > 7 && ay > 7) || az > 16 || az < -7){
                System.out.println("===SENSOR=== x: "+ax+" y: "+ ay + "z: " + az);
                this.gnars++;
                TextView gnarCount = (TextView)findViewById(R.id.gnar_count);
                gnarCount.setText(""+gnars);
            }


        }
    }

}