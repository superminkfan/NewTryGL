package shaders;

import entities.Camera;
import entities.Light;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import toolBox.Maths;

public  class StaticShader extends ShaderProgram{


    private static final String VERTEX_FILE = "src/shaders/GLSL/vertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/shaders/GLSL/fragmentShader.glsl";

    private int location_transformationMatrix;//деаем настройку матриц
    private int location_projectionMatrix;
    private int location_viewMatrix ;
    private int location_lightPosition ;
    private int location_lightColour;
    private int location_shineDamper;
    private int location_reflectivity;
    private int location_useFakeLighting;
    private int location_skyColour;



    public StaticShader()
    {
        super(VERTEX_FILE,FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix") ;
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_lightColour = super.getUniformLocation("lightColour");
        location_shineDamper = super.getUniformLocation("shineDamper");
        location_reflectivity = super.getUniformLocation("reflectivity");
        location_useFakeLighting = super.getUniformLocation("useFakeLighting");
        location_skyColour = super.getUniformLocation("skyColour");



    }

    public void loadSkuColourVariable(float r , float g , float b)
    {
        super.loadVector(location_skyColour, new Vector3f(r,g,b));
    }

    public void loadFakeLightingVariable(boolean useFake)
    {
        super.loadBoolean(location_useFakeLighting,useFake);
    }


    public void loadShineVariables(float damper , float reflectivity)
    {
        super.loadFloat(location_shineDamper , damper);
        super.loadFloat(location_reflectivity , reflectivity);
    }


    public void loadTransforationMatrix(Matrix4f matix)
    {
        super.loadMatrix(location_transformationMatrix , matix);
    }

    public void loadLight(Light light)
    {
        super.loadVector(location_lightPosition , light.getPosition());
        super.loadVector(location_lightColour , light.getColour());
    }


    public void loadViewMatrix(Camera camera)
    {

        Matrix4f vieMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix , vieMatrix);
    }

    public void loadProjectionMatrix(Matrix4f matix)
    {
        super.loadMatrix(location_projectionMatrix , matix);
    }


    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"position");
        super.bindAttribute(1,"textureCoords");
        super.bindAttribute(2,"normal");

    }
}