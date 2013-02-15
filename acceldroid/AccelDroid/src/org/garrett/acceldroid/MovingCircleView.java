package org.garrett.acceldroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

// this custom view also works as a sensor listener
public class MovingCircleView extends View implements SensorEventListener {
	private final int CIRCLE_COLOR = 0xff74AC23;
	private final int CIRCLE_DIAMETER = 50;
	private final double NOISE = 0.065;
	private final int MAX_STEP = 10;
	private int x, y; // circle's position
	ShapeDrawable circle;

	public MovingCircleView(Context context, AttributeSet attrs){
		super(context, attrs);
						
		x = 0;
		y = 0;
		circle = createCircle();
	}
	
	// create a new circle
	private ShapeDrawable createCircle(){
		ShapeDrawable circle;
		
		circle = new ShapeDrawable(new OvalShape());
		
		circle.getPaint().setColor(CIRCLE_COLOR);
		circle.setBounds(x, y, x+CIRCLE_DIAMETER, y+CIRCLE_DIAMETER);
		
		return circle;
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		// move circle to new coordinates and draw it
		circle.setBounds(x, y, x+CIRCLE_DIAMETER, y+CIRCLE_DIAMETER);
		circle.draw(canvas);
		invalidate();
	}
	
    public void onSensorChanged(SensorEvent event) {
    	// get data from the rotation sensor
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
        	float R[], O[], x, y, z;
        	
        	R = new float[16];
        	O = new float[4];
        	
        	// the rotation sensor is relative to the earth's axis.
        	// we need to transform it into a rotation matrix and,  
        	// using this matrix, get the orientation of the device
        	SensorManager.getRotationMatrixFromVector(R, event.values);        	
        	SensorManager.getOrientation(R, O);

        	// angle of the device in each axis
        	x = O[1];
        	y = O[2];
        	z = O[0];
        	
        	newData(y, x, z);
        }
    }
    
    private void newData(float x, float y, float z){
    	int deltaX, deltaY;
    	deltaX = deltaY = 0;
    	
    	// ignore noise
    	if(Math.abs(x) < NOISE)
    		x = 0;
    	if(Math.abs(y) < NOISE)
    		y = 0;
    	
    	deltaX = (int)(MAX_STEP * x);
    	deltaY = (int)(MAX_STEP * y * -1);
    	
    	// update the coordinates
    	updatePosition(deltaX, deltaY);
    }
    
    // update the coordinates and check if the circle will
    // not go out of the view
    private void updatePosition(int moveX, int moveY){
    	int x, y, w, h;
    	
    	x = this.x + moveX;
    	y = this.y + moveY;
    	w = h = CIRCLE_DIAMETER;
    	
    	if(x < 0)
    		x = 0;
    	if(y < 0)
    		y = 0;
    	
    	if((x+w) >= getRight())
    		x = getRight() - w - 1;
    	if((y+h) >= getBottom())
    		y = getBottom() - h - 1;
    	
    	this.x = x;
    	this.y = y;
    }
    
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// nothing but the wind
	}
}
