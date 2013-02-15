package org.garrett.cameradroid;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
	private Camera camera;
	private CameraPreview preview;
	private PictureCallback picture;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// creates the picture callback for capturing
		picture = new PictureCallback() {
		    @Override
		    public void onPictureTaken(byte[] data, Camera camera) {
		        File pictureFile = getOutputMediaFile();

		        try {
		            FileOutputStream fos = new FileOutputStream(pictureFile);
		            fos.write(data);
		            fos.close();
		            Toast.makeText(MainActivity.this, pictureFile.getPath(), Toast.LENGTH_LONG).show();
		        } catch (Exception e) {
		            // error while writing to file
		        }
		    }
		};
	}
	
	@Override
	public void onStart(){
		super.onStart();
		
		// start camera preview
		startPreview();
	}
	
	@Override
	public void onStop(){
		super.onStop();
		
		// stop camera preview
		stopPreview();
	}
	
	private void stopPreview(){
		if(camera != null){
			camera.stopPreview();
			camera.release();
			camera = null;
		}
	}
	
	private void startPreview(){
		// Create an instance of Camera
		camera = getCameraInstance();

		// Create our Preview view and set it as the content of our activity.
		preview = new CameraPreview(this, camera);
		FrameLayout frame = (FrameLayout)findViewById(R.id.camera_preview);
		frame.addView(preview);
	}

	public static Camera getCameraInstance(){
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		}
		catch (Exception e){
			// Camera is not available (in use or does not exist)
	    }
		return c; // returns null if camera is unavailable
	}
	
	// capture picture
	public void captureClicked(View v){
		if(camera != null){
			camera.takePicture(null, null, picture);
			camera.startPreview();
		}
	}
	
	private File getOutputMediaFile(){
		// get the public images directory
	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraDroid");

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            // failed to create directory
	            return null;
	        }
	    }

	    // Create a media file name
	    String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault()).format(new Date());
	    File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timestamp + ".jpg");

	    return mediaFile;
	}
	
}
