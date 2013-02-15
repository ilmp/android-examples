package org.garrett.acceldroid;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity {
	private SensorManager sensorManager;
	private Sensor sensor;
	private MovingCircleView movingCircle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// get the sensor from the system
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
		movingCircle = (MovingCircleView) findViewById(R.id.movingCircle);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		// ask for 5ms updates from the sensor
        sensorManager.registerListener(movingCircle, sensor, 5000);
    }

	@Override
    public void onStop() {
		super.onStop();
		
    	// stop the sensor
        sensorManager.unregisterListener(movingCircle);
    }
}
