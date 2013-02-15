
// request medium precision for the float values
precision mediump float;

// texture sampler
uniform sampler2D uTexture;

// texture UV mapping {u, v} passed by the vertex shader
varying vec2 vTexPosition;

// light position
uniform vec3 uLightPosition;

// vertex position
varying vec3 vPosition;

void main(){
    // light position
    vec3 lightPosition = vec3(-0.5, 0.8, -2.0);
    
    // light distance
    float distance = length(lightPosition - vPosition);
    
    // diffuse lighting with attenuation
    float diffuse = 2.0 * (1.0 / (1.0 + (0.2 * distance * distance)));
    
    // add ambient lighting
    diffuse = max(diffuse, 0.5);
    
    // based on the texture coordinates for each vertex
    // get the corresponding color on the texture
    vec3 t = texture2D(uTexture, vTexPosition).xyz;
    
    // apply light to the color
    vec3 color = t * diffuse;
    gl_FragColor = vec4(color, 1.0);
}
