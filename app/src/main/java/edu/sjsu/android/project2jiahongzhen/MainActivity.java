package edu.sjsu.android.project2jiahongzhen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;
    private Sensor sensor;
    private Display display;
    public  static float x, y;
    public static long timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView view = new MyView(this);
        setContentView(view);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor == null) {
            Toast.makeText(this, "Accelerometer not found on your device!", Toast.LENGTH_LONG).show();
            return;
        }
        //initial display
        display = getWindowManager().getDefaultDisplay();
    }
    @Override
    public void onStart(){
        super.onStart();
        if(sensor != null)
            manager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(sensor != null)
            manager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // TODO: set x, y, timestamp based on the sensor data
        // lesson 9 page 16
        if (display.getRotation() == Surface.ROTATION_0) {
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
        }
        else if (display.getRotation() == Surface.ROTATION_90) {
            x = sensorEvent.values[1];
            y = sensorEvent.values[0];
        }
        else if(display.getRotation() == Surface.ROTATION_180){
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
        }
        else {
            x = sensorEvent.values[1];
            y = sensorEvent.values[0];
        }

        timestamp = sensorEvent.timestamp;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}