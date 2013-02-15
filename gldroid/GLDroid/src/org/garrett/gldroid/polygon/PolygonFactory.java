package org.garrett.gldroid.polygon;

import android.content.Context;

public class PolygonFactory {
	public static Polygon createPolygon(Context context, VertexList vList, VertexList tList, VertexList nList){
		Polygon p;
		
		int count = vList.size();
		if(count == 3)
			p = new Triangle(context, vList, tList, nList);
		else if(count == 4)
			p = new Rectangle(context, vList, tList, nList);
		else 
			p = new Polygon(context, vList, tList, nList);
			
		return p;
	}
}
