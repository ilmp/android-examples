package org.garrett.gldroid;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.app.Activity;

public class MainActivity extends Activity {
	
	private GLSurfaceView glView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// remove window title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// OpenGL view
		glView = new GLDroidSurfaceView(this);
		setContentView(glView);
	}
	
	@Override
	public void onStart(){
		// refresh shader and texture managers
		((GLApplication)getApplicationContext()).refreshManagers();
		
		super.onStart();
	}
}
