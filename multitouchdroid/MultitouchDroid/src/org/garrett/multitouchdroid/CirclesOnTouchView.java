package org.garrett.multitouchdroid;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CirclesOnTouchView extends View {
	private final int POINTER_DOWN_COLOR = 0xff74AC23;
	private final int POINTER_UP_COLOR = 0xffCCCCCC;
	private final int POINTER_DOWN_DIAMETER = 50;
	private final int POINTER_UP_DIAMETER = 25;
	
	private boolean[] pCircles = {false, false, false, false, false, false, false, false, false, false};
	private List<ShapeDrawable> circles = new ArrayList<ShapeDrawable>();
	
	public CirclesOnTouchView(Context context, AttributeSet attrs){
		super(context, attrs);
	}
	
	// add a new circle
	public void add(int x, int y, int d, int color){
		ShapeDrawable circle;
		
		circle = new ShapeDrawable(new OvalShape());
		circle.getPaint().setColor(color);
		circle.setBounds(x, y, x+d, y+d);
		
		circles.add(circle);
	}
	
	public void add(float x, float y, int d, int color){
		add((int)x, (int)y, d, color);		
	}
	
	// handle touch events, one pointer per time
	@Override
	public boolean onTouchEvent(MotionEvent event){
		int id;
		float x, y;
		
		// get the action and the corresponding pointer
		final int action = event.getActionMasked();
		final int idx = event.getActionIndex();
		
		// get pointer id and position
		id =  event.getPointerId(idx);
		x = event.getX(idx);
		y = event.getY(idx);
		
		switch(action){
        case MotionEvent.ACTION_DOWN:
        	// first pointer pressed: remove all circles and add the first one
        	circles.clear();
        	for(int i=0; i<10; i++)
        		pCircles[i] = false;
        	add(x-POINTER_DOWN_DIAMETER/2, y-POINTER_DOWN_DIAMETER/2, POINTER_DOWN_DIAMETER, POINTER_DOWN_COLOR);
        	pCircles[id] = true;
        	break;
		case MotionEvent.ACTION_MOVE:
			// pointer is still pressed but changed position
        	break;
		case MotionEvent.ACTION_POINTER_DOWN:
			// just draw the circle if the pointer is not yet pressed
			if(! pCircles[id]){
				add(x-POINTER_DOWN_DIAMETER/2, y-POINTER_DOWN_DIAMETER/2, POINTER_DOWN_DIAMETER, POINTER_DOWN_COLOR);
				pCircles[id] = true;
			}
        	break;
		case MotionEvent.ACTION_UP:
			// last pointer removed from screen
			add(x-POINTER_UP_DIAMETER/2, y-POINTER_UP_DIAMETER/2, POINTER_UP_DIAMETER, POINTER_UP_COLOR);
        	for(int i=0; i<10; i++)
        		pCircles[i] = false;
        	break;
		case MotionEvent.ACTION_POINTER_UP:
			// a pointer, not the last, was removed from screen
			add(x-POINTER_UP_DIAMETER/2, y-POINTER_UP_DIAMETER/2, POINTER_UP_DIAMETER, POINTER_UP_COLOR);
			pCircles[id] = false;
        	break;
		}
		
		return true;
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		// draw all circles on this view's canvas
		for(int i=0; i<circles.size(); i++){
			circles.get(i).draw(canvas);	
		}
		invalidate();
	}
}
