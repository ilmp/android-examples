package org.garrett.gldroid.polygon;

import android.content.Context;

public class ColoredCube extends SimpleCube {
	
	public ColoredCube(Context c, float edgeSize){
		super(c, edgeSize);
		
		// set faces colors
		Color white = new Color(1, 1, 1);
		Color red = new Color(1, 0, 0);
		Color green = new Color(0, 1, 0);
		Color blue = new Color(0, 0, 1);
		Color grey = new Color(0.5f, 0.5f, 0.5f);
		Color purple = new Color(1, 0, 1);
		
		faces.get(0).setColor(white);
		faces.get(1).setColor(red);
		faces.get(2).setColor(green);
		faces.get(3).setColor(blue);
		faces.get(4).setColor(grey);
		faces.get(5).setColor(purple);
	}
}
