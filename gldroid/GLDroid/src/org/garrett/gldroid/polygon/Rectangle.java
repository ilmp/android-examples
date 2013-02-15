package org.garrett.gldroid.polygon;

import android.content.Context;

public class Rectangle extends Polygon {
	
	public Rectangle(Context context, Vertex topL, Vertex botL, Vertex botR, Vertex topR){
		super(context);
		
		// a rectangle must be formed by two triangles in OpenGL ES 2.0
		// vertices must be added in counterclockwise order
		add(topL);
		add(botL);
		add(botR);
		
		add(topL);
		add(botR);
		add(topR);
	}
	
	public Rectangle(Context context, VertexList vList, VertexList tList, VertexList nList){
		super(context);
		
		add(vList.get(0));
		add(vList.get(1));
		add(vList.get(2));
		add(vList.get(0));
		add(vList.get(2));
		add(vList.get(3));
		
		if(tList.size() > 0){
			VertexList newTList = new VertexList();
			
			newTList.add(tList.get(0));
			newTList.add(tList.get(1));
			newTList.add(tList.get(2));
			newTList.add(tList.get(0));
			newTList.add(tList.get(2));
			newTList.add(tList.get(3));
			
			setTexturePosition(newTList.get2DArray());
		}
		
		setNormal(nList);
	}
}
