package org.garrett.gldroid.polygon;

import android.content.Context;

public class Triangle extends Polygon {
	public Triangle(Context context, Vertex top, Vertex bottomLeft, Vertex bottomRight){
		super(context);
		
		add(top);
		add(bottomLeft);
		add(bottomRight);
		
		System.out.print(top.toString() + " ");
		System.out.print(bottomLeft.toString() + " ");
		System.out.println(bottomRight.toString());
	}
	
	public Triangle(Context context, VertexList vList, VertexList tList, VertexList nList){
		super(context);
		
		add(vList.get(0));
		add(vList.get(1));
		add(vList.get(2));
		
		setTexturePosition(tList.get2DArray());
		setNormal(nList);
	}
}
