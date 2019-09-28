
//********************Animation in Frame**********************************************8
package threeD;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import javax.swing.*;
import javax.swing.event.*;

import home.*;


public class ThreeDWorkAreaPanel extends JPanel implements KeyListener,ActionListener
{
	Base base;
	
	private TransformGroup objTrans;
	private TransformGroup bsObjTrans;
	
	private TransformGroup xAxisObjTrans;
	private TransformGroup yAxisObjTrans;
	private TransformGroup zAxisObjTrans;
	
	
	private TransformGroup externalTrans;
	TransformGroup masterTransformGroup;
	
	private Transform3D trans = new Transform3D();
	private Transform3D transY = new Transform3D();
	private Transform3D transZ = new Transform3D();
	private float height=0.0f;
	private float sign = 1.0f; // going up or down
	//private Timer timer;
	private float xloc=0.0f;
	private float zLoc=-10f;
	private float yLoc=0.0f;
	
	JSlider xRotateSlider;
	JSlider yRotateSlider;
	JSlider zRotateSlider;
	
	ImageIcon minIcon=new ImageIcon("images/minimize.jpg");
	ImageIcon closeIcon=new ImageIcon("images/close.jpg");
	ImageIcon popOutIcon=new ImageIcon("images/popout.jpg");
	
	JButton closeBt,minBt,popOutBt;
	
	//------------------------CREATING BS ARRAYLIST----------------------------------
	ArrayList <Cone> bsConeList=new ArrayList<Cone>();
	//-------------------------------------------------------------------------------
		
	
	public BranchGroup createSceneGraph() 
	{
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();
		
		masterTransformGroup=new TransformGroup();
		masterTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		//ColorCube cube = new ColorCube(2f);
		
		
		Appearance app=new Appearance();
		Color3f diffusiveColor=new Color3f(0f,1f,0f);
		Color3f specularColor=new Color3f(1f,1f,1f);
		Color3f emmisiveColor=new Color3f(0f,0f,0f);
		Color3f ambientColor=new Color3f(0.1f,0.3f,0.1f);
		app.setMaterial(new Material(ambientColor,emmisiveColor,diffusiveColor,specularColor,100.0f));
		//Box cube=new Box(0.8f,0.8f,0.8f,app);
		Box deploymentRegionBox=new Box(5.0f, .05f, 5.0f, app);
		
		
		Appearance app3=new Appearance();
		Color3f bsDiffusiveColor=new Color3f(1f,0.2f,0.2f);
		Color3f bsSpecularColor=new Color3f(1f,1f,1f);
		Color3f bsEmmisiveColor=new Color3f(0f,0f,0f);
		Color3f bsAmbientColor=new Color3f(0.5f,0.1f,0.1f);
		app3.setMaterial(new Material(bsAmbientColor,bsEmmisiveColor,bsDiffusiveColor,bsSpecularColor,100.0f));
		//Box cube=new Box(0.8f,0.8f,0.8f,app);
		//Cone bsCone=new Cone(0.2f,1.5f, app3);
		
		if(base.masterBSList.size()>0)
		{
			bsObjTrans=new TransformGroup();
			bsObjTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			
			for(int i=0;i<base.masterBSList.size();i++)
			{
				VLabel tempBs=base.masterBSList.get(i);
				int x=tempBs.getX()-100;
				int y=tempBs.getY()-100;	
				float fx=x/100.0f;
				fx=fx*2;
				
				float fy=y/100.0f;
				fy=fy*2;
				Cone tempBsCone=new Cone(0.2f, 1.5f, app3);
				
				Transform3D bsTrans=new Transform3D();
				bsTrans.setTranslation(new Vector3f(-5f+fx,.80f,zLoc-5+fy));
				bsObjTrans.setTransform(bsTrans);
				bsObjTrans.addChild(tempBsCone);								
			}
			masterTransformGroup.addChild(bsObjTrans);
		}
										
		Appearance app4=new Appearance();
		Color3f xAxisDiffusiveColor=new Color3f(0.2f,0.2f,0.2f);
		Color3f xAxisSpecularColor=new Color3f(1f,1f,1f);
		Color3f xAxisEmmisiveColor=new Color3f(0f,0f,0f);
		Color3f xAxisAmbientColor=new Color3f(0.1f,0.1f,0.1f);
		app4.setMaterial(new Material(xAxisAmbientColor,xAxisEmmisiveColor,xAxisDiffusiveColor,xAxisSpecularColor,100f));
		Cylinder xAxisCylinder=new Cylinder(0.01f,6f);
		
		
	    Material m = new Material(ambientColor,emmisiveColor,diffusiveColor,specularColor,100.0f);
	    Appearance a = new Appearance();
	    m.setLightingEnable(true);
	    a.setMaterial(m);
	    Font3D font3d = new Font3D(new Font("North", Font.PLAIN,1), new FontExtrusion());
	    Text3D north3dTx = new Text3D(font3d, new String("North"), new Point3f(-2.0f, 0.0f, -16.0f));
	    north3dTx.setCapability(Geometry.ALLOW_INTERSECT);
	    Shape3D north3dShape = new Shape3D();
	    north3dShape.setGeometry(north3dTx);
	    north3dShape.setAppearance(a);
	    
	    Text3D south3dTx = new Text3D(font3d, new String("South"), new Point3f(-2.0f, 0.0f, -2.0f));
	    south3dTx.setCapability(Geometry.ALLOW_INTERSECT);
	    Shape3D south3dShape = new Shape3D();
	    south3dShape.setGeometry(south3dTx);
	    south3dShape.setAppearance(a);
	    
	    
	    Text3D east3dTx = new Text3D(font3d, new String("East"), new Point3f(6.0f, 0.0f, -8.0f));
	    south3dTx.setCapability(Geometry.ALLOW_INTERSECT);
	    Shape3D east3dShape = new Shape3D();
	    east3dShape.setGeometry(east3dTx);
	    east3dShape.setAppearance(a);
	    
	    
	    Text3D west3dTx = new Text3D(font3d, new String("West"), new Point3f(-8.0f, 0.0f, -8.0f));
	    south3dTx.setCapability(Geometry.ALLOW_INTERSECT);
	    Shape3D west3dShape = new Shape3D();
	    west3dShape.setGeometry(west3dTx);
	    west3dShape.setAppearance(a);
	    
		
				
		Group lineGroup=new Group();
		Appearance lineApp=new Appearance();
	    ColoringAttributes coloringAttrib = new ColoringAttributes(new Color3f(0.0f, 0.0f, 0.0f), ColoringAttributes.SHADE_FLAT);
	    lineApp.setColoringAttributes(coloringAttrib);
		
	    Point3f[][] plaPts1 = new Point3f[11][2];
	    
	    	for(int x=0;x<=10;x++)
	    	{	    		
	    		plaPts1[x][0] = new Point3f((-5+x), 0f, -15);
	    		plaPts1[x][1] = new Point3f((-5+x), 10f, -15);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts1[x]);	    			    			    		
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);	    		
	    	}
	    	
		    Point3f[][] plaPts2 = new Point3f[11][2];
		    
	    	for(int z=0;z<=10;z++)
	    	{
	    		plaPts2[z][0] = new Point3f(-5, 0f, -15+z);
	    		plaPts2[z][1] = new Point3f(-5, 10f, -15+z);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts2[z]);
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);
	    	}
	    		    		    	
	    
	    Point3f[][] plaPts3 = new Point3f[11][2];
	    

	    	for(int x=0;x<=10;x++)
	    	{
	    		plaPts3[x][0] = new Point3f((-5+x), 0.052f, -15);
	    		plaPts3[x][1] = new Point3f((-5+x), 0.052f,-5);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts3[x]);
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);
	    	}
	    	
	    	
		    Point3f[][] plaPts4 = new Point3f[11][2];
		    

	    	for(int y=0;y<=10;y++)
	    	{
	    		plaPts4[y][0] = new Point3f(-5, 0.062f+y, -15);
	    		plaPts4[y][1] = new Point3f(-5, 0.062f+y,-5);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts4[y]);
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);
	    	}
	    	
		    Point3f[][] plaPts5 = new Point3f[11][2];
		    

	    	for(int z=0;z<=10;z++)
	    	{
	    		plaPts5[z][0] = new Point3f(-5, 0.062f, -15+z);
	    		plaPts5[z][1] = new Point3f( 5, 0.062f,-15+z);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts5[z]);
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);
	    	}
	    	
	    	Point3f[][] plaPts6 = new Point3f[11][2];
	    	
	    	for(int y=0;y<=10;y++)
	    	{
	    		plaPts6[y][0] = new Point3f(-5, 0.058f+y, -15);
	    		plaPts6[y][1] = new Point3f( 5, 0.058f+y,-15);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts6[y]);
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);
	    	}
		
		

		

		objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yAxis = new Transform3D();
		yAxis.setTranslation(new Vector3f(0.0f,0.0f,zLoc));
		objTrans.setTransform(yAxis);
		objTrans.addChild(deploymentRegionBox);
		//objTrans.addChild(bsCone);
		masterTransformGroup.addChild(objTrans);
		

		
		xAxisObjTrans=new TransformGroup();
		xAxisObjTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D xAxisTrans=new Transform3D();
		xAxisTrans.setTranslation(new Vector3f(-5.0f,3f,zLoc-5f));
		xAxisObjTrans.setTransform(xAxisTrans);
		xAxisObjTrans.addChild(xAxisCylinder);
		masterTransformGroup.addChild(lineGroup);
		
		
		
		masterTransformGroup.addChild(north3dShape);
		masterTransformGroup.addChild(south3dShape);
		masterTransformGroup.addChild(west3dShape);
		masterTransformGroup.addChild(east3dShape);
		
		objRoot.addChild(masterTransformGroup);
      	
      	//masterTransformGroup.addChild(externalTrans);
		
		BoundingSphere bounds =  new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
		Vector3f light1Direction = new Vector3f(-6.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		objRoot.addChild(light1);
		
		// Set up the ambient light
		
/*		Color3f ambientCol = new Color3f(5.0f, 1.0f, 1.0f);
		AmbientLight ambientLightNode = new AmbientLight(ambientCol);
		ambientLightNode.setInfluencingBounds(bounds);
		objRoot.addChild(ambientLightNode);*/
		
		Background background=new Background();
		background.setColor(1f,1f,1f);
		background.setApplicationBounds(bounds);
		objRoot.addChild(background);
		
		MouseRotate mouseRotate=new MouseRotate();
		mouseRotate.setTransformGroup(masterTransformGroup);
		mouseRotate.setSchedulingBounds(bounds);
		mouseRotate.setFactor(0.007);
		masterTransformGroup.addChild(mouseRotate);
		
		return objRoot;
		
	}

	public ThreeDWorkAreaPanel(Base base) 
	{
		this.base=base;		
		setLayout(new BorderLayout());
   
		//----------------------NORTH PANEL-------------------------------------
		
		JPanel northPanel=new JPanel();
		northPanel.setLayout(null);
		closeBt=new JButton(closeIcon);
		closeBt.addActionListener(this);
		closeBt.setBounds(2, 2,10,10);
		minBt=new JButton(minIcon);
		minBt.addActionListener(this);
		popOutBt=new JButton(popOutIcon);
		popOutBt.addActionListener(this);
		northPanel.add(closeBt);
		//northPanel.add(minBt);
		//northPanel.add(popOutBt);
		//
		
		
		//------------------------CENTER PANEL-----------------------------------
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);
		canvas.addKeyListener(this);
		
		BranchGroup scene = createSceneGraph();
		scene.compile();
		SimpleUniverse simpleUniverse = new SimpleUniverse(canvas);
		ViewingPlatform vf=simpleUniverse.getViewingPlatform();
		vf.setNominalViewingTransform();
		simpleUniverse.addBranchGraph(scene);
		
		centerPanel.add("Center",canvas);
		
		add("North",northPanel);
		add("Center",centerPanel);			
	}
	
	public void keyPressed(KeyEvent e) 
	{
		
		//	Invoked when a key has been pressed.
		
		if (e.getKeyChar()=='s') {xloc = xloc + .1f;}
		if (e.getKeyChar()=='a') {xloc = xloc - .1f;}
		
		if (e.getKeyChar()=='w') {yLoc = yLoc + .1f;}
		if (e.getKeyChar()=='z') {yLoc = yLoc - .1f;}
		
		if (e.getKeyChar()=='e') {zLoc = zLoc + .1f;}
		if (e.getKeyChar()=='x') {zLoc = zLoc - .1f;}
		
		transY.setTranslation(new Vector3f(xloc,yLoc,zLoc));
	    masterTransformGroup.setTransform(transY);
	    
	  //  trans.setTranslation(new Vector3f(xloc-2.0f,yLoc+1f,zLoc+2f));
//	    bsObjTrans.setTransform(trans);
		
	}
	
	public void keyReleased(KeyEvent e)
	{	
		// Invoked when a key has been released.	
	}
	
	public void keyTyped(KeyEvent e)
	{
		//Invoked when a key has been typed.
	}

	//-----------------IMPLEMENTING CHANGE EVENT----------------------------
	
	//--------------------IMPLEMENTING ACTION EVENT-------------------------
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==closeBt)
		{
			
		}
		
		if(ae.getSource()==minBt)
		{
			
		}
		
		if(ae.getSource()==popOutBt)
		{
			
		}
	}
	
	//----------------------------------------------------------------------
	
	
	
	public static void main(String[] args) 
	{
		System.out.println("Program Started");
		ThreeDWorkAreaPanel bb = new ThreeDWorkAreaPanel(new Base());
		bb.addKeyListener(bb);
		//MainFrame mf = new MainFrame(bb, 256, 256);
		bb.setSize(400, 400);
		bb.setVisible(true);
	}

}