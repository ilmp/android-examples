
// request medium precision for the float values
precision mediump float;

// color vector {red, green, blue, alpha}
uniform vec4 uColor;

// light position
uniform vec3 uLightPosition;

// vertex position
varying vec3 vPosition;

void main() {
    // light distance
    float distance = length(uLightPosition - vPosition);
    
    // diffuse lighting with attenuation
    float diffuse = 2.0 * (1.0 / (1.0 + (0.2 * distance * distance)));
    
    // add ambient lighting
    diffuse = max(diffuse, 0.5);
    
    // apply light to the color
    vec3 color = vec3(uColor * diffuse);
    gl_FragColor = vec4(color, uColor.a);
}

