
// MVP matrix, containing model (rotation and translation), 
// view (camera) and projection (screen size)
uniform mat4 uMatrix;

// vertex coordinates vector {x, y, z, w}
attribute vec4 aPosition;

// to be passed into the fragment shader
varying vec3 vPosition;

void main(){
    // multiply the MVP matrix with the coordinates vector
    vec4 position = uMatrix * aPosition;
    
    // set vertex position
    gl_Position = position;
    
    // pass the vertices coordinates into the fragment shader for lighting
    vPosition = vec3(position);
}

