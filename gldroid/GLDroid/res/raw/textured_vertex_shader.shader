   
// MVP matrix, containing model (rotation and translation), 
// view (camera) and projection (screen size)
uniform mat4 uMatrix;

// vertex coordinates vector {x, y, z, w}
attribute vec4 aPosition;

// texture UV mapping {u, v}
attribute vec2 aTexPosition;

// texture UV mapping passed into the fragment shader
varying vec2 vTexPosition;

// to be passed into the fragment shader
varying vec3 vPosition;

void main(){
    // multiply the MVP matrix with the coordinates vector
    vec4 position = uMatrix * aPosition;
   
    // set vertex position
    gl_Position = position;
    
    // pass texture coordinates (UV) into the fragment shader
    vTexPosition = aTexPosition;
    
    // pass the vertices coordinates into the fragment shader for lighting
    vPosition = vec3(position);
}
