
//********************Animation in Frame**********************************************8
package threeD;
import java.awt.*;
import java.awt.event.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;

import javax.swing.*;
import javax.swing.event.*;
public class Sample2 extends JFrame implements ActionListener, KeyListener,ChangeListener 
{
	private JButton goBt = new JButton("go");
	private JButton closeBt=new JButton("close");
	private TransformGroup objTrans;
	private Transform3D transX = new Transform3D();
	private Transform3D transY = new Transform3D();
	private Transform3D transZ = new Transform3D();
	private float height=0.0f;
	private float sign = 1.0f; // going up or down
	private Timer timer;
	private float xloc=0.0f;
	private float zLoc=-10f;
	private float yLoc=0.0f;
	
	JSlider xRotateSlider;
	JSlider yRotateSlider;
	JSlider zRotateSlider;
	
	public BranchGroup createSceneGraph() 
	{
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();
		
		ColorCube cube = new ColorCube(3f);
		
		
		Appearance app=new Appearance();
		//Box cube=new Box(2.0f, 3.0f, 1.0f, app);
		Color3f objColor=new Color3f(0f,1f,0.0f);
		Color3f black=new Color3f(0.0f,0.0f,0.0f);
		app.setMaterial(new Material(objColor,black,objColor,black,80.0f));
		//Box cube=new Box(0.8f,0.8f,0.8f,app);
		

		objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yAxis = new Transform3D();
		yAxis.setTranslation(new Vector3f(0.0f,0.0f,zLoc));
		objTrans.setTransform(yAxis);
		objTrans.addChild(cube);
		objRoot.addChild(objTrans);
		
		BoundingSphere bounds =  new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		Color3f light1Color = new Color3f(1.0f, 0.0f, 0.2f);
		Vector3f light1Direction = new Vector3f(-6.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		objRoot.addChild(light1);
		// Set up the ambient light
		
		Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		objRoot.addChild(ambientLightNode);
		return objRoot;
		
	}

	public Sample2() {

		setLayout(new BorderLayout());
   
		//------------------------NORTH PANEL------------------------------------
		JPanel northPanel=new JPanel();
		goBt.addActionListener(this);
		goBt.addKeyListener(this);
		closeBt.addActionListener(this);
		
	
		northPanel.add(goBt);
		northPanel.add(closeBt);
		//-----------------------------------------------------------------------
		
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
		
		
		//timer = new Timer(1000,this);
		
		//timer.start();
		
		
		
		
		

		
		// Create a simple scene and attach it to the virtual universe
		
		//-----------------------SOUTH PANEL----------------------------------------
		JPanel southPanel=new JPanel();
		
		//-----------------------EAST PANEL------------------------------------------
		JPanel eastPanel=new JPanel();
		//eastPanel.setLayout(null);
		xRotateSlider=new JSlider();
		xRotateSlider.addChangeListener(this);
		
		yRotateSlider=new JSlider();
		yRotateSlider.addChangeListener(this);
		
		zRotateSlider=new JSlider();
		zRotateSlider.addChangeListener(this);
		
		
		
		
		eastPanel.add(xRotateSlider);
		eastPanel.add(yRotateSlider);
		eastPanel.add(zRotateSlider);
		
		//-----------------------WEST PANEL-----------------------------------------
		JPanel westPanel=new JPanel();		

		add("North",northPanel);
		add("Center",centerPanel);			
		add("South",southPanel);
		add("East",eastPanel); 
		add("West",westPanel);
	}
	
	public void keyPressed(KeyEvent e) 
	{
		
		//	Invoked when a key has been pressed.
		
		if (e.getKeyChar()=='s') {xloc = xloc + .1f;}
		if (e.getKeyChar()=='a') {xloc = xloc - .1f;}
		
		if (e.getKeyChar()=='w') {yLoc = yLoc + .1f;}
		if (e.getKeyChar()=='z') {yLoc = yLoc - .1f;}
		
		transY.setTranslation(new Vector3f(xloc,yLoc,zLoc));
	    objTrans.setTransform(transY);
		
	}
	
	public void keyReleased(KeyEvent e)
	{	
		// Invoked when a key has been released.	
	}
	
	public void keyTyped(KeyEvent e)
	{
		//Invoked when a key has been typed.
	}

	public void actionPerformed(ActionEvent e ) 
	{
		// start timer when button is pressed
		if (e.getSource()==goBt)
		{
			if (!timer.isRunning()) 
			{
				timer.start();
			}
		}
		else 
		{
			height += .1 * sign;
			if (Math.abs(height *2) >= 10 ) sign = -1.0f * sign;
			
			transY.setTranslation(new Vector3f(xloc,yLoc,zLoc));
			objTrans.setTransform(transY);
		}
		
		//zLoc=zLoc+0.05f;
		
		if(e.getSource()==closeBt)
		{
			System.exit(0);
		}
	}
	
	
	//-----------------IMPLEMENTING CHANGE EVENT----------------------------
	public void stateChanged(ChangeEvent ce)
	{
		if(ce.getSource()==xRotateSlider)
		{
			System.out.println(xRotateSlider.getValue());			
			float val=xRotateSlider.getValue();
			//zLoc=val-50;
			
			transX.rotX(val/50);
			transX.mul(transY);
			transX.mul(transZ);
			transY.setTranslation(new Vector3f(xloc,yLoc,zLoc));
			transX.setTranslation(new Vector3f(xloc,yLoc,zLoc));
			transZ.setTranslation(new Vector3f(xloc,yLoc,zLoc));
			objTrans.setTransform(transX);
		}
		
		if(ce.getSource()==yRotateSlider)
		{
			System.out.println(xRotateSlider.getValue());			
			float val=yRotateSlider.getValue();
			//zLoc=val-50;
			
			transY.rotY(val/50);	
			transY.mul(transX);
			transY.mul(transZ);
			transY.setTranslation(new Vector3f(xloc,yLoc,zLoc));
			transX.setTranslation(new Vector3f(xloc,yLoc,zLoc));
			transZ.setTranslation(new Vector3f(xloc,yLoc,zLoc));
			objTrans.setTransform(transY);
		}
		
		if(ce.getSource()==zRotateSlider)
		{
			System.out.println(xRotateSlider.getValue());			
			float val=zRotateSlider.getValue();
			//zLoc=val-50;
			
			transZ.rotZ(val/50);
			transZ.mul(transX);
			transZ.mul(transY);
			transY.setTranslation(new Vector3f(xloc,yLoc,zLoc));
			transX.setTranslation(new Vector3f(xloc,yLoc,zLoc));
			transZ.setTranslation(new Vector3f(xloc,yLoc,zLoc));
			objTrans.setTransform(transZ);
	//		trans.set(arg0);
		}

	}
	
	public static void main(String[] args) 
	{
		System.out.println("Program Started");
		Sample2 bb = new Sample2();
		bb.addKeyListener(bb);
		//MainFrame mf = new MainFrame(bb, 256, 256);
		bb.setSize(400, 400);
		bb.setVisible(true);
	}

}