
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


public class ThreeDWorkAreaDialog extends JDialog implements KeyListener,ActionListener
{
	Base base;
	Appearance app4;
	
	SimpleUniverse simpleUniverse;
	BranchGroup sceneBranchGroup;
	MouseRotate mouseRotate;
	BoundingSphere bounds;
	
	Appearance dropSnApp;
	
	Appearance lineApp;
	Point3f oldPoint=new Point3f(0f, 0f, 0f);
	
	
	public TransformGroup objTrans;
	public TransformGroup dropSnObjTrans;
	public Transform3D dropSnTrans;
	
	public TransformGroup xAxisObjTrans;
	public TransformGroup yAxisObjTrans;
	public TransformGroup zAxisObjTrans;
	
	public TransformGroup masterTransformGroup;
	
	public Transform3D trans = new Transform3D();
	public Transform3D transY = new Transform3D();
	public Transform3D transZ = new Transform3D();
	
	
	
	//---------------------bhasad-------------------------------
	public TransformGroup trackMarkerObjTrans;
	public Transform3D trackMarkerTrans;
	
	
	ArrayList <ThreeDCoordinate> trackRecordList=new ArrayList <ThreeDCoordinate>();
	ArrayList <TransformGroup> trackTransformGroup=new ArrayList<TransformGroup>();
	
	
	//----------------------------------------------------------
	
	
	//private Timer timer;
	public float xLoc=0.0f;
	public float zLoc=-20.0f;
	public float yLoc=-5.0f;
	
	public float zVal=-10.0f;
	
	JSlider xRotateSlider;
	JSlider yRotateSlider;
	JSlider zRotateSlider;
	
	ImageIcon minIcon=new ImageIcon("images/minimize.jpg");
	ImageIcon closeIcon=new ImageIcon("images/close.jpg");
	ImageIcon popOutIcon=new ImageIcon("images/popout.jpg");
	
	JButton closeBt,minBt,popOutBt;
	
	//------------------------CREATING BS ARRAYLIST----------------------------------
	ArrayList <BaseStation3d> bs3dList=new ArrayList<BaseStation3d>();
	ArrayList <SensorNode3d> sn3dList=new ArrayList<SensorNode3d>();
	ArrayList <ThreeDCoordinate> trackList=new ArrayList<ThreeDCoordinate>();
	//ArrayList <Cone> bsConeList=new ArrayList<Cone>();
	//-------------------------------------------------------------------------------
		
	
/*	public BranchGroup createSceneGraph() 
	{
		
		
	}*/

	public ThreeDWorkAreaDialog(Base b) 
	{
		super(b, "3-D-Panel");
		this.base=b;		
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
		
		//BranchGroup scene = createSceneGraph();
		
		
		//--------------------------------------------------------------
		
		
		// Create the root of the branch graph
		sceneBranchGroup = new BranchGroup();
		
		
		sceneBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		sceneBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		sceneBranchGroup.setCapability(BranchGroup.ALLOW_AUTO_COMPUTE_BOUNDS_WRITE);
		sceneBranchGroup.setCapability(BranchGroup.ALLOW_COLLISION_BOUNDS_WRITE);
		sceneBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
		
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
		
		
		Appearance bsApp=new Appearance();
		Color3f bsDiffusiveColor=new Color3f(0.6f,0.6f,0.6f);
		Color3f bsSpecularColor=new Color3f(1f,1f,1f);
		Color3f bsEmmisiveColor=new Color3f(0f,0f,0f);
		Color3f bsAmbientColor=new Color3f(0.3f,0.3f,0.3f);
		bsApp.setMaterial(new Material(bsAmbientColor,bsEmmisiveColor,bsDiffusiveColor,bsSpecularColor,100.0f));
		//Box cube=new Box(0.8f,0.8f,0.8f,app);
		//Cone bsCone=new Cone(0.2f,1.5f, app3);
		
		
		
		
		if(base.masterBSList.size()>0)
		{			
			for(int i=0;i<base.masterBSList.size();i++)
			{
				TransformGroup bsObjTrans=new TransformGroup();
				bsObjTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				VLabel tempBs=base.masterBSList.get(i);
				int x=tempBs.getX()-100;
				int y=tempBs.getY()-100;	
				float fx=x/100.0f;
				fx=fx*2;
				
				float fy=y/100.0f;
				fy=fy*2;
				Cone tempBsCone=new Cone(0.2f, 1.5f, bsApp);
				
				Transform3D bsTrans=new Transform3D();
				bsTrans.setTranslation(new Vector3f(-5f+fx,.80f,zVal-5+fy));
				bsObjTrans.setTransform(bsTrans);
				bsObjTrans.addChild(tempBsCone);	
				masterTransformGroup.addChild(bsObjTrans);
				
				//------------ADDING BS TO THE LIST-------------------------
				
				BaseStation3d tempBS3d=new BaseStation3d(tempBsCone,bsObjTrans);
				bs3dList.add(tempBS3d);
				
				//---------------------------------------------------------				
			}			
		}
		
		
		Appearance snApp=new Appearance();
		Color3f snDiffusiveColor=new Color3f(.2f,0.2f,1f);
		Color3f snSpecularColor=new Color3f(1f,1f,1f);
		Color3f snEmmisiveColor=new Color3f(0f,0f,0f);
		Color3f snAmbientColor=new Color3f(0.5f,0.1f,0.1f);
		snApp.setMaterial(new Material(snAmbientColor,snEmmisiveColor,snDiffusiveColor,snSpecularColor,100.0f));
		
		
		if(base.masterNodeList.size()>0)
		{			
			for(int i=0;i<base.masterNodeList.size();i++)
			{
				TransformGroup snObjTrans=new TransformGroup();
				snObjTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				VLabel tempSn=base.masterNodeList.get(i);
				int x=tempSn.getX()-100;
				int y=tempSn.getY()-100;	
				float fx=x/100.0f;
				fx=fx*2;
				
				float fy=y/100.0f;
				fy=fy*2;
				Cone tempSnCone=new Cone(0.1f, 0.35f, snApp);
				
				Transform3D snTrans=new Transform3D();
				snTrans.setTranslation(new Vector3f(-5f+fx,0.2f,zVal-5+fy));
				snObjTrans.setTransform(snTrans);
				snObjTrans.addChild(tempSnCone);	
				masterTransformGroup.addChild(snObjTrans);
				
				//------------ADDING BS TO THE LIST-------------------------
				
				SensorNode3d tempSn3d=new SensorNode3d(tempSnCone,snObjTrans);
				sn3dList.add(tempSn3d);
				
				//---------------------------------------------------------
				
			}
		}
		
		
		//###################################################################
		
/*		Runnable rv=new Runnable()
		{
			public void run()
			{
				
				Appearance trackApp=new Appearance();
				Color3f trackDiffusiveColor=new Color3f(.2f,0.2f,1f);
				Color3f trackSpecularColor=new Color3f(1f,1f,1f);
				Color3f trackEmmisiveColor=new Color3f(0f,0f,0f);
				Color3f trackAmbientColor=new Color3f(0.5f,0.1f,0.1f);
				trackApp.setMaterial(new Material(trackAmbientColor,trackEmmisiveColor,trackDiffusiveColor,trackSpecularColor,100.0f));
				
				
				for(;;)
				{					
					System.out.println("thread rv workig");					
					if(trackRecordList.size()>0)
					{			
						while(trackRecordList.size()>0)
						{
							try
							{
							TransformGroup trackObjTrans=new TransformGroup();
							trackObjTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
							ThreeDCoordinate tempCoordinate=trackRecordList.get(0);
							trackRecordList.remove(0);
							float fx=tempCoordinate.xCoordinate;
							float fy=tempCoordinate.yCoordinate;	
							float fz=tempCoordinate.zCoordinate;

							Cone trackCone=new Cone(0.1f, 0.35f, trackApp);
				
							Transform3D trackTrans=new Transform3D();
							trackTrans.setTranslation(new Vector3f(fx,fy,fz));
							trackObjTrans.setTransform(trackTrans);
							trackObjTrans.addChild(trackCone);	
							masterTransformGroup.addChild(trackObjTrans);
							
							//------------ADDING BS TO THE LIST-------------------------
				
							//SensorNode3d tempSn3d=new SensorNode3d(tempSnCone,snObjTrans);
							//sn3dList.add(tempSn3d);							
							//---------------------------------------------------------
							}catch(Exception e){e.printStackTrace();}
						}
					}		
					try{Thread.sleep(100);}catch(Exception e){}
				}
			}
		};
		new Thread(rv).start();*/
		
		
		//###################################################################
		
		if(base.candidateLoc!=null)
		{
			Appearance desiredLocApp=new Appearance();
			Color3f desiredLocDiffusiveColor=new Color3f(.2f,0.2f,1f);
			Color3f desiredLocSpecularColor=new Color3f(1f,1f,1f);
			Color3f desiredLocEmmisiveColor=new Color3f(0f,0f,0f);
			Color3f desiredLocAmbientColor=new Color3f(0.5f,0.1f,0.1f);
			desiredLocApp.setMaterial(new Material(desiredLocAmbientColor,desiredLocEmmisiveColor,desiredLocDiffusiveColor,desiredLocSpecularColor,100.0f));
			
			TransformGroup desiredLocObjTrans=new TransformGroup();
			desiredLocObjTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			
			int x1=base.candidateLoc.getXLoc()-100;
			int y1=base.candidateLoc.getYLoc()-100;	
			float fx=x1/100.0f;
			fx=fx*2;
			
			float fy=y1/100.0f;
			fy=fy*2;
			
			Cylinder desiredLocCylender=new Cylinder(0.2f,0.05f,desiredLocApp);
			
			Transform3D desiredLocTrans=new Transform3D();
			desiredLocTrans.setTranslation(new Vector3f(-5f+fx,0.1f,zVal-5+fy));
			desiredLocObjTrans.setTransform(desiredLocTrans);
			desiredLocObjTrans.addChild(desiredLocCylender);	
			masterTransformGroup.addChild(desiredLocObjTrans);						
		}
		
		//---------------------AADS-DROP--------------------------------------
		
		dropSnApp=new Appearance();
		Color3f dropSnDiffusiveColor=new Color3f(1f,0.2f,0.2f);
		Color3f dropSnSpecularColor=new Color3f(1f,1f,1f);
		Color3f dropSnEmmisiveColor=new Color3f(1f,0f,0f);
		Color3f dropSnAmbientColor=new Color3f(0.5f,0.1f,0.1f);
		dropSnApp.setMaterial(new Material(dropSnAmbientColor,dropSnEmmisiveColor,dropSnDiffusiveColor,dropSnSpecularColor,100.0f));
		
		dropSnObjTrans=new TransformGroup();
		dropSnObjTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		Sphere dropSnSphere = new Sphere(0.15f, dropSnApp);
		
		dropSnTrans=new Transform3D();
		dropSnTrans.setTranslation(new Vector3f(-5f,0.2f,zVal-5));
		dropSnObjTrans.setTransform(dropSnTrans);
		dropSnObjTrans.addChild(dropSnSphere);	
		masterTransformGroup.addChild(dropSnObjTrans);
				
		
		//-------------------------------------------------------------------
		
										
		app4=new Appearance();
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
	    Text3D north3dTx = new Text3D(font3d, new String("North"), new Point3f(-2.0f, -0.5f, zVal-6f));
	    north3dTx.setCapability(Geometry.ALLOW_INTERSECT);
	    Shape3D north3dShape = new Shape3D();
	    north3dShape.setGeometry(north3dTx);
	    north3dShape.setAppearance(a);
	    
	    Text3D south3dTx = new Text3D(font3d, new String("South"), new Point3f(-2.0f, -0.5f, zVal+6f));
	    south3dTx.setCapability(Geometry.ALLOW_INTERSECT);
	    Shape3D south3dShape = new Shape3D();
	    south3dShape.setGeometry(south3dTx);
	    south3dShape.setAppearance(a);
	    
	    
	    Text3D east3dTx = new Text3D(font3d, new String("East"), new Point3f(6.0f, -0.5f, zVal));
	    south3dTx.setCapability(Geometry.ALLOW_INTERSECT);
	    Shape3D east3dShape = new Shape3D();
	    east3dShape.setGeometry(east3dTx);
	    east3dShape.setAppearance(a);
	    
	    
	    Text3D west3dTx = new Text3D(font3d, new String("West"), new Point3f(-8.0f, -0.5f, zVal));
	    south3dTx.setCapability(Geometry.ALLOW_INTERSECT);
	    Shape3D west3dShape = new Shape3D();
	    west3dShape.setGeometry(west3dTx);
	    west3dShape.setAppearance(a);
	    
		
				
		Group lineGroup=new Group();
		lineApp=new Appearance();
	    ColoringAttributes coloringAttrib = new ColoringAttributes(new Color3f(0.0f, -0.5f, 0.0f), ColoringAttributes.SHADE_FLAT);
	    lineApp.setColoringAttributes(coloringAttrib);
		
	    Point3f[][] plaPts1 = new Point3f[11][2];
	    
	    	for(int x=0;x<=10;x+=2)
	    	{	    		
	    		plaPts1[x][0] = new Point3f((-5+x), 0f,zVal-5);
	    		plaPts1[x][1] = new Point3f((-5+x), 10f,zVal-5);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts1[x]);	    			    			    		
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);	    		
	    	}
	    	
		    Point3f[][] plaPts2 = new Point3f[11][2];
		    
	    	for(int z=0;z<=10;z+=2)
	    	{
	    		plaPts2[z][0] = new Point3f(-5, 0f, zVal-5+z);
	    		plaPts2[z][1] = new Point3f(-5, 10f, zVal-5+z);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts2[z]);
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);
	    	}
	    		    		    	
	    
	    Point3f[][] plaPts3 = new Point3f[11][2];
	    

	    	for(int x=0;x<=10;x+=2)
	    	{
	    		plaPts3[x][0] = new Point3f((-5+x), 0.052f, zVal-5);
	    		plaPts3[x][1] = new Point3f((-5+x), 0.052f,zVal+5);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts3[x]);
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);
	    	}
	    	
	    	
		    Point3f[][] plaPts4 = new Point3f[11][2];
		    

	    	for(int y=0;y<=10;y+=2)
	    	{
	    		plaPts4[y][0] = new Point3f(-5, 0.062f+y, zVal-5);
	    		plaPts4[y][1] = new Point3f(-5, 0.062f+y,zVal+5);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts4[y]);
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);
	    	}
	    	
		    Point3f[][] plaPts5 = new Point3f[11][2];
		    

	    	for(int z=0;z<=10;z+=2)
	    	{
	    		plaPts5[z][0] = new Point3f(-5, 0.062f, zVal-5+z);
	    		plaPts5[z][1] = new Point3f( 5, 0.062f,zVal-5+z);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts5[z]);
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);
	    	}
	    	
	    	Point3f[][] plaPts6 = new Point3f[11][2];
	    	
	    	for(int y=0;y<=10;y+=2)
	    	{
	    		plaPts6[y][0] = new Point3f(-5, 0.058f+y, zVal-5);
	    		plaPts6[y][1] = new Point3f( 5, 0.058f+y,zVal-5);
	    		LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    		pla.setCoordinates(0, plaPts6[y]);
	    		Shape3D plShape = new Shape3D(pla, lineApp);
	    		lineGroup.addChild(plShape);
	    	}
		
		

		

		objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yAxis = new Transform3D();
		yAxis.setTranslation(new Vector3f(0.0f,0.0f,zVal));
		objTrans.setTransform(yAxis);
		objTrans.addChild(deploymentRegionBox);
		//objTrans.addChild(bsCone);
		masterTransformGroup.addChild(objTrans);
		

		
		xAxisObjTrans=new TransformGroup();
		xAxisObjTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D xAxisTrans=new Transform3D();
		xAxisTrans.setTranslation(new Vector3f(-5.0f,3f,zVal-5f));
		xAxisObjTrans.setTransform(xAxisTrans);
		xAxisObjTrans.addChild(xAxisCylinder);
		masterTransformGroup.addChild(lineGroup);
		
		
		
		//masterTransformGroup.addChild(north3dShape);
		//masterTransformGroup.addChild(south3dShape);
		//masterTransformGroup.addChild(west3dShape);
		//masterTransformGroup.addChild(east3dShape);
		
		
		
		sceneBranchGroup.addChild(masterTransformGroup);
      	
      	//masterTransformGroup.addChild(externalTrans);
		
		bounds =  new BoundingSphere(new Point3d(0.0,0.0,0.0), 250.0);
		Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
		Vector3f light1Direction = new Vector3f(-6.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		sceneBranchGroup.addChild(light1);
		
		// Set up the ambient light
		
/*		Color3f ambientCol = new Color3f(5.0f, 1.0f, 1.0f);
		AmbientLight ambientLightNode = new AmbientLight(ambientCol);
		ambientLightNode.setInfluencingBounds(bounds);
		objRoot.addChild(ambientLightNode);*/
		
		Background background=new Background();
		background.setColor(1f,1f,1f);
		background.setApplicationBounds(bounds);
		sceneBranchGroup.addChild(background);
		
		mouseRotate=new MouseRotate();
		mouseRotate.setTransformGroup(masterTransformGroup);
		mouseRotate.setSchedulingBounds(bounds);
		mouseRotate.setFactor(0.007);
		//masterTransformGroup.addChild(mouseRotate);
		sceneBranchGroup.addChild(mouseRotate);
		
		transY.setTranslation(new Vector3f(xLoc,yLoc,zLoc));
	    masterTransformGroup.setTransform(transY);	
		
		
		//-----------------------------------------------------------------
	    sceneBranchGroup.compile();
		simpleUniverse = new SimpleUniverse(canvas);
		ViewingPlatform vf=simpleUniverse.getViewingPlatform();
		vf.setNominalViewingTransform();
		simpleUniverse.addBranchGraph(sceneBranchGroup);
		
		centerPanel.add("Center",canvas);
		
		add("North",northPanel);
		add("Center",centerPanel);		
		
		
		
	    //------------WORKING THREAD------------------------------------
	    
	    Runnable r=new Runnable()
	    {
	    	//float fx=-2f;
	    	public void run()
	    	{
	    		//for(;;)
	    		while(base.currentHeight>0)
	    		{
	    			//int x=base.gliderTail1Loc.getXLoc();
	    			//int z=base.gliderTail1Loc.getYLoc();
	    			
	    			int x=(int)base.gliderCenterLoc.floatX;
	    			int z=(int)base.gliderCenterLoc.floatY;
	    			
	    			float fy=(float)base.currentHeight;
	    			
	    			x=x-100;
	    			z=z-100;
	    			
	    			x=x*2;
	    			z=z*2;
	    			fy=fy*2;
	    				    			
	    			float fx=x/100f;	
	    			float fz=z/100f;
	    			fy=fy/100f;
	    			
	    			dropSnTrans.setTranslation(new Vector3f(-5f+fx,fy,zVal+fz-5f));
	    			dropSnObjTrans.setTransform(dropSnTrans);
	    			try{Thread.sleep(100);}catch(Exception e){}
	    			
	    		}
	    	}
	    };
	    new Thread(r).start();
	    
	    
	    
	    
	    
	    //-------------------------------------------------------------------
	    
	    Runnable r1=new Runnable()
	    {
	    	public void run()
	    	{
	    		//for(;;)
	    		while(base.currentHeight>0)
	    		{	
	    			
	    			//int x=base.gliderTail1Loc.getXLoc();
	    			//int z=base.gliderTail1Loc.getYLoc();
	    			
	    			int x=(int)base.gliderCenterLoc.floatX;
	    			int z=(int)base.gliderCenterLoc.floatY;
	    			float fy=(float)base.currentHeight;
	    			base.trajectory3DList.add(new VCoordinate3d(x,z,fy));
	    			x=x-100;
	    			z=z-100;
	    			
	    			x=x*2;
	    			z=z*2;
	    			fy=fy*2;
	    				    			
	    			float fx=x/100f;	
	    			float fz=z/100f;
	    			fy=fy/100f;
	    			
	    			System.out.println("the function is working ");
	    			
	    			ThreeDCoordinate newThreeDCoordinate=new ThreeDCoordinate(-5f+fx,fy,zVal+fz-5f);
	    			sceneBranchGroup.addChild( createSphere(-5f+fx,fy,zVal+fz-5f));
	    			trackRecordList.add(newThreeDCoordinate);
	    			try{Thread.sleep(500);}catch(Exception e){}
	    		}
	    	}
	    };
	    new Thread(r1).start();	    	    		
		
	}
	
	
	
	//----------------------------------------------------------
	float counter=0;
	
	 public BranchGroup createSphere(float fx, float fy,float fz)
	 {
		 	BranchGroup bg = new BranchGroup();

		 	bg.setCapability( BranchGroup.ALLOW_DETACH );
		 	// bg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		 		 			
		 	TransformGroup masterTrackGroup=new TransformGroup();
		 	masterTrackGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		 	masterTrackGroup.setTransform(transY);
		 	
			TransformGroup trackObjTrans=new TransformGroup();
			trackObjTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			
			//masterTransformGroup.addChild(snObjTrans);
		 		 		 	
			Appearance app = new Appearance();
			Color3f objColor = new Color3f(1.0f, 0.7f, 0.8f);
			Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
			app.setMaterial(new Material(objColor, black, objColor, black, 80.0f));		 		 		 
		 
			Sphere s=new Sphere( 0.1f, dropSnApp );
		 		 
			Transform3D trackTrans=new Transform3D();
			trackTrans.setTranslation(new Vector3f(fx,fy,fz));
			trackObjTrans.setTransform(trackTrans);
			trackObjTrans.addChild(s);
			
			
			if(trackTransformGroup.size()==0)
			{
				oldPoint=new Point3f(fx,fy,fz);
			}
			
			else
			{
				Point3f linePoint []=new Point3f[2];
				linePoint[0] = oldPoint;
				Point3f newPoint=new Point3f(fx,fy,fz);
				linePoint[1] = newPoint;
				LineArray pla = new LineArray(2, LineArray.COORDINATES);
				pla.setCoordinates(0, linePoint);
				Shape3D plShape = new Shape3D(pla, lineApp);
				masterTrackGroup.addChild(plShape);				
				oldPoint=newPoint;
			}
			
			
			masterTrackGroup.addChild(trackObjTrans);

			bg.addChild(masterTrackGroup);
			bg.setUserData( "Sphere" );
		 
			MouseRotate mr=new MouseRotate();
			mr.setTransformGroup(masterTrackGroup);
			mr.setSchedulingBounds(bounds);
			mr.setFactor(0.007);
			
			masterTrackGroup.addChild(mr);
			trackTransformGroup.add(masterTrackGroup);
			
		 return bg;
	 }
	
	//---------------------------------------------------------
	
	public void keyPressed(KeyEvent e) 
	{		
		//	Invoked when a key has been pressed.
		
		if (e.getKeyChar()=='s') {xLoc = xLoc + .1f;}
		if (e.getKeyChar()=='a') {xLoc = xLoc - .1f;}
		
		if (e.getKeyChar()=='w') {yLoc = yLoc + .1f;}
		if (e.getKeyChar()=='z') {yLoc = yLoc - .1f;}
		
		if (e.getKeyChar()=='e') {zLoc = zLoc + .1f;}
		if (e.getKeyChar()=='x') {zLoc = zLoc - .1f;}
		
		transY.setTranslation(new Vector3f(xLoc,yLoc,zLoc));
	    masterTransformGroup.setTransform(transY);
	    
	    for(int i=0;i<trackTransformGroup.size();i++)
	    {
	    	trackTransformGroup.get(i).setTransform(transY);
	    }
	    
	  //  trans.setTranslation(new Vector3f(xloc-2.0f,yLoc+1f,zLoc+2f));
	  //  bsObjTrans.setTransform(trans);	    
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
		ThreeDWorkAreaDialog bb = new ThreeDWorkAreaDialog(new Base());
		bb.addKeyListener(bb);
		//MainFrame mf = new MainFrame(bb, 256, 256);
		bb.setSize(400, 400);
		bb.setVisible(true);
	}

}