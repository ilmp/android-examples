package org.garrett.gldroid.polygon;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

// define a simple cube structure without coloring or textures
public class SimpleCube implements GLObject {
	protected List<Rectangle> faces;
	protected Context context;
	
	public SimpleCube(Context c, float edgeSize){
		Vertex topLF, topLB, topRF, topRB;
		Vertex botLF, botLB, botRF, botRB;
		float offset = Math.abs(edgeSize/2);
		
		context = c;
		
		// defining vertices
		topLF = new Vertex(-offset, offset, offset);
		topLB = new Vertex(-offset, offset, -offset);
		topRF = new Vertex(offset, offset, offset);
		topRB = new Vertex(offset, offset, -offset);
		botLF = new Vertex(-offset, -offset, offset);
		botLB = new Vertex(-offset, -offset, -offset);
		botRF = new Vertex(offset, -offset, offset);
		botRB = new Vertex(offset, -offset, -offset);
		
		// faces
		faces = new ArrayList<Rectangle>();
		faces.add(new Rectangle(context, topLF, botLF, botRF, topRF)); // front face
		faces.add(new Rectangle(context, topRB, botRB, botLB, topLB)); // back face
		faces.add(new Rectangle(context, topLB, topLF, topRF, topRB)); // top face
		faces.add(new Rectangle(context, botRB, botRF, botLF, botLB)); // bottom face
		faces.add(new Rectangle(context, topLB, botLB, botLF, topLF)); // left face
		faces.add(new Rectangle(context, topRF, botRF, botRB, topRB)); // right face
	}
	
	public void draw(float[] vpMatrix){
		for(int i=0; i<faces.size(); i++){
			faces.get(i).draw(vpMatrix);
		}
	}
	
	public void translate(float x, float y, float z){
		for(int i=0; i<faces.size(); i++){
			faces.get(i).translate(x, y, z);
		}
	}
	
	public void rotate(float x, float y, float z){
		for(int i=0; i<faces.size(); i++){
			faces.get(i).rotate(x, y, z);
		}
	}
	
	public void globalRotate(float x, float y, float z){
		for(int i=0; i<faces.size(); i++){
			faces.get(i).globalRotate(x, y, z);
		}
	}
	
	public void setLightPosition(float x, float y, float z){
		for(int i=0; i<faces.size(); i++){
			faces.get(i).setLightPosition(x, y, z);
		}
	}
}
