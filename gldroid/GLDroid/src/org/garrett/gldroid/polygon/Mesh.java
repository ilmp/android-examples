package org.garrett.gldroid.polygon;

import java.util.ArrayList;
import java.util.List;

public class Mesh implements GLObject {
	private List<Polygon> polygons;
	
	public Mesh(){
		polygons = new ArrayList<Polygon>();		
	}
	
	public void add(Polygon p){
		polygons.add(p);
	}
	
	public void add(Polygon p, VertexList textureVertices, VertexList normalVertices){
		p.setTexturePosition(textureVertices.get2DArray());
		add(p);
	}
	
	public void draw(float[] vpMatrix){
		for(int i=0; i<polygons.size(); i++){
			polygons.get(i).draw(vpMatrix);
		}
	}
	
	public void translate(float x, float y, float z){
		for(int i=0; i<polygons.size(); i++){
			polygons.get(i).translate(x, y, z);
		}
	}
	
	public void rotate(float x, float y, float z){
		for(int i=0; i<polygons.size(); i++){
			polygons.get(i).rotate(x, y, z);
		}
	}
	
	public void globalRotate(float x, float y, float z){
		for(int i=0; i<polygons.size(); i++){
			polygons.get(i).globalRotate(x, y, z);
		}
	}
	
	public void setLightPosition(float x, float y, float z){
		for(int i=0; i<polygons.size(); i++){
			polygons.get(i).setLightPosition(x, y, z);
		}
	}
}

