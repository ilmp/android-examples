package org.garrett.gldroid.polygon;

// methods which all cubes must implement 
public interface Cube {
	public void draw(float[] vpMatrix);
	public void translate(float x, float y, float z);
	public void rotate(float x, float y, float z);
	public void globalRotate(float x, float y, float z);
	public void setLightPosition(float x, float y, float z);
}
