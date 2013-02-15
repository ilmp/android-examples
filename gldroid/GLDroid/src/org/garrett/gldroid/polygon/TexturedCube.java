package org.garrett.gldroid.polygon;

import org.garrett.gldroid.GLApplication;
import org.garrett.gldroid.R;
import org.garrett.gldroid.texture.Texture;

import android.content.Context;

public class TexturedCube extends SimpleCube implements GLObject {
	// UV mapping of the texture
	private final float[] TEXTURE_POSITION = {
		0.0f, 0.0f,
		1.0f, 0.0f,
		1.0f, 1.0f,
		0.0f, 0.0f,
		1.0f, 1.0f,
		0.0f, 1.0f,
	};
	
	public TexturedCube(Context c, float edgeSize){
		super(c, edgeSize);
		
		Texture t = ((GLApplication)context.getApplicationContext()).getTextureManager().getTexture(context, R.raw.cube_texture);
		
		// set faces texture
		faces.get(0).setTexture(t);
		faces.get(0).setTexturePosition(TEXTURE_POSITION);
		
		faces.get(1).setTexture(t);
		faces.get(1).setTexturePosition(TEXTURE_POSITION);
		
		faces.get(2).setTexture(t);
		faces.get(2).setTexturePosition(TEXTURE_POSITION);
		
		faces.get(3).setTexture(t);
		faces.get(3).setTexturePosition(TEXTURE_POSITION);
		
		faces.get(4).setTexture(t);
		faces.get(4).setTexturePosition(TEXTURE_POSITION);
		
		faces.get(5).setTexture(t);
		faces.get(5).setTexturePosition(TEXTURE_POSITION);
	}
}
