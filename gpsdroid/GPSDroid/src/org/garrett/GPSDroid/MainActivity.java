package org.garrett.GPSDroid;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class MainActivity extends Activity {
	private final int UPDATE_METERS = 10;
	private final int UPDATE_TIME = 5000; // 5 seconds
	
	private TextView distanceView, locationView, totalView;
	private Location initialLocation, lastLocation;
	private LocationManager locationManager;
	private LocationListener locationListener;
	private float totalDistance = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		distanceView = (TextView)findViewById(R.id.distance);
		locationView = (TextView)findViewById(R.id.location);
		totalView = (TextView)findViewById(R.id.total);
		
		distanceView.setText("0 m");
		totalView.setText("0 m");
		
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	}
	
	@Override
	public void onStop(){
		super.onStop();
		if(locationListener != null)
			locationManager.removeUpdates(locationListener);
	}
	
	@Override
	public void onStart(){
		super.onStart();
		
		// check if GPS is enabled
		final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if(!gpsEnabled){
			// ask user if he wants to enable GPS
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.ask_gps_settings)
				.setTitle(R.string.gps_disabled);
			
			// open settings
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id){
					Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(settingsIntent);
				}
			});
			
			// finish application
			builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
		        	   finish();
		           }
			});
			
			AlertDialog dialog = builder.create();
			dialog.show();
		}
		else {
			locationListener = new LocationListener() {
				@Override
				public void onLocationChanged(Location location) {
					newLocation(location);
				}

				@Override
				public void onProviderDisabled(String p) {
				}
				
				@Override
				public void onProviderEnabled(String p) {
				}
				
				@Override
				public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				}
			};
				
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_TIME, UPDATE_METERS, locationListener);
		}
	}

	protected void newLocation(Location location){
		float distance;

		// new location
		String lStr = String.valueOf(location.getLatitude());
		lStr += ", " + String.valueOf(location.getLongitude());
		locationView.setText(lStr);
		
		if(initialLocation != null){
			// distance to the initial location
			distance = location.distanceTo(initialLocation);
			distanceView.setText(String.valueOf((int)distance) + " m");
		}
		
		if(lastLocation != null){
			// total distance
			totalDistance += location.distanceTo(lastLocation);
			totalView.setText(String.valueOf((int)totalDistance) + " m");
		}
				
		lastLocation = location;
	}
	
	public void locationButtonPressed(View v){
		initialLocation = lastLocation;
		totalDistance = 0;
		totalView.setText("0 m");
		distanceView.setText("0 m");
	}
}
