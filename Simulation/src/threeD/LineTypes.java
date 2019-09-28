package threeD;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class LineTypes extends JFrame {

  SimpleUniverse u;
  Canvas3D canvas;
  View view;
  Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
  // NumberFormat to print out floats with only two digits
  NumberFormat nf;
  
  Group createLineTypes() 
  {
	Group lineGroup = new Group();
    Appearance app = new Appearance();
    ColoringAttributes ca = new ColoringAttributes(black, ColoringAttributes.SHADE_FLAT);
    app.setColoringAttributes(ca);
    
    Point3f[] plaPts = new Point3f[2];
    plaPts[0] = new Point3f(-0.9f, -0.7f, 0.0f);
    plaPts[1] = new Point3f(-0.5f, 0.7f, 0.0f);
    LineArray pla = new LineArray(2, LineArray.COORDINATES);
    pla.setCoordinates(0, plaPts);
    Shape3D plShape = new Shape3D(pla, app);
    lineGroup.addChild(plShape);

    // line pattern dot-dash
/*    Point3f[] dotDashPts = new Point3f[2];
    dotDashPts[0] = new Point3f(0.5f, -0.7f, 0.0f);
    dotDashPts[1] = new Point3f(0.9f, 0.7f, 0.0f);
    LineArray dotDash = new LineArray(2, LineArray.COORDINATES);
    dotDash.setCoordinates(0, dotDashPts);
    LineAttributes dotDashLa = new LineAttributes();
    dotDashLa.setLineWidth(4.0f);
    dotDashLa.setLinePattern(LineAttributes.PATTERN_DASH_DOT);
    Appearance dotDashApp = new Appearance();
    dotDashApp.setLineAttributes(dotDashLa);
    dotDashApp.setColoringAttributes(ca);
    Shape3D dotDashShape = new Shape3D(dotDash, dotDashApp);
    lineGroup.addChild(dotDashShape);*/

    return lineGroup;
  }

  BranchGroup createSceneGraph() 
  {
    BranchGroup objRoot = new BranchGroup();
    TransformGroup objScale = new TransformGroup();
    Transform3D scaleTrans = new Transform3D();
    
    objScale.setTransform(scaleTrans);
    objRoot.addChild(objScale);
    
    TransformGroup objTrans = new TransformGroup();
    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    objScale.addChild(objTrans);

    objTrans.addChild(createLineTypes());

    BoundingSphere bounds = new BoundingSphere(new Point3d(), 100.0);    
    Background bg = new Background(new Color3f(1.0f, 1.0f, 1.0f));
    bg.setApplicationBounds(bounds);
    objTrans.addChild(bg);

    // set up the mouse rotation behavior
    MouseRotate mr = new MouseRotate();
    mr.setTransformGroup(objTrans);
    mr.setSchedulingBounds(bounds);
    mr.setFactor(0.007);
    objTrans.addChild(mr);

    // Set up the ambient light
    Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
    AmbientLight ambientLightNode = new AmbientLight(ambientColor);
    ambientLightNode.setInfluencingBounds(bounds);
    objRoot.addChild(ambientLightNode);

    // Set up the directional lights
    Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
    Vector3f light1Direction = new Vector3f(0.0f, -0.2f, -1.0f);

    DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
    light1.setInfluencingBounds(bounds);
    objRoot.addChild(light1);
    
    return objRoot;
  }
 
  public LineTypes() 
  {
	// set up a NumFormat object to print out float with only 3 fraction
	 	  
    nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(3);

    setLayout(new BorderLayout());
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    canvas = new Canvas3D(config);
    add("Center", canvas);

    BranchGroup scene = createSceneGraph();
    u = new SimpleUniverse(canvas);

    u.getViewingPlatform().setNominalViewingTransform();
    u.addBranchGraph(scene);
    view = u.getViewer().getView();
  }
  
  public static void main(String[] args)
  {
	  LineTypes lt=new LineTypes();
	  lt.setSize(300,300);
	  lt.setVisible(true);  
  }
}

