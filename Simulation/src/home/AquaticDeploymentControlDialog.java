package home;
import javax.swing.*;
import javax.transaction.xa.Xid;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class AquaticDeploymentControlDialog extends JDialog implements ActionListener,KeyListener,ItemListener
{
	VFigure foreFigure;
	ArrayList<LineSegment>lineList;		
	VToolBox vToolBox;
	JButton stablizeBt,okBt,cancelBt;
	JComboBox selectLocCb;
	JLabel densityWaterLb, densityAirLb,windDirectionLb,windSpeedLb, turningRadiusLb, simpleDispLb,serpentineDispLb;
	
	JLabel loc_distLb, currentDistLb,diffLb;
	
	JTextField densityWaterTf, densityAirTf,windSpeedKmphTf,windSpeedMpsTf,turningRadiusTf, simpleDispTf, serpentineDispTf;
	
	JComboBox windFromCb;//,windToCb;
	
	//----------------------------------------------------------
		JPanel virtualPathPanel;
		JCheckBox virtualPathChb;
		JLabel pathUpdationIntervalLb;
		JTextField pathUpdationIntervalTf;		
	//----------------------------------------------------------
		
	//----------------------------------------------------------
		JPanel radiusReductionPanel;
		JLabel effectiveRangeLb;
		JTextField effectiveRangeTf;
		JCheckBox radiusReductionChb;
	//----------------------------------------------------------
	
	String windDirectionSt[]={"Null","North","South","East","West"};
	
	String locSt[]={"1","2","3"};
	Base base;
	
	int shape;
	Color color;
	
	AquaticDeploymentControlDialog(Base b)
	{
		super(b, "Aquatic deployment control dialog");
		base=b;
		
		decorate();
		
		vToolBox=new VToolBox(base);
		setLayout(new BorderLayout());
		JPanel northPanel=new JPanel();
		
		
		JPanel westPanel=new JPanel();
		westPanel.setBorder(BorderFactory.createTitledBorder("control panel"));
		stablizeBt=new JButton("stablize");
		stablizeBt.addActionListener(this);
		westPanel.add(stablizeBt);
		
		GridLayout gl=new GridLayout(15,1);
		gl.setVgap(5);
		westPanel.setLayout(gl);
		
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		//centerPanel.setLayout(new GridLayout(2,1));
		JPanel upperCenterPanel=new JPanel();
		upperCenterPanel.setLayout(null);
		//---------------------------------------------------------------
		densityWaterLb=new JLabel("density of water");						densityWaterTf=new JTextField();
		densityAirLb=new JLabel("density of air");									densityAirTf=new JTextField();
		windDirectionLb=new JLabel("wind from");								windFromCb=new JComboBox(windDirectionSt);				//windToCb=new JComboBox(windDirectionSt);
		windSpeedLb=new JLabel("wind speed (Km/hr)");						windSpeedKmphTf=new JTextField();						windSpeedMpsTf=new JTextField();                         windSpeedMpsTf.setEditable(false);   
																																														windSpeedKmphTf.addKeyListener(this);
		turningRadiusLb=new JLabel("Turning radius");							turningRadiusTf=new JTextField();
		simpleDispLb=new JLabel("Smple Displacement");					simpleDispTf=new JTextField();							simpleDispTf.setEditable(false);
		serpentineDispLb=new JLabel("Serpentine Displacement");     serpentineDispTf=new JTextField();						serpentineDispTf.setEditable(false);
																																														
		
		//---------------------VIRTUAL PATH PANEL------------------------------------------
		virtualPathPanel=new JPanel();
		virtualPathPanel.setLayout(null);
		virtualPathPanel.setBorder(BorderFactory.createTitledBorder("virtual path"));
		
		pathUpdationIntervalLb=new JLabel("Path updation interval:");
		pathUpdationIntervalTf=new JTextField();
		
		virtualPathChb=new JCheckBox("Virtual path updation");
		virtualPathChb.addActionListener(this);
		virtualPathChb.setSelected(true);
		
		
		virtualPathChb.setBounds(10,20,200,16);
		pathUpdationIntervalLb.setBounds(15,40,130,16);
		pathUpdationIntervalTf.setBounds(160,40,100,16);
		
		virtualPathPanel.add(virtualPathChb);
		virtualPathPanel.add(pathUpdationIntervalLb);
		virtualPathPanel.add(pathUpdationIntervalTf);
		
		virtualPathPanel.setBounds(5,220,400,80);
		//---------------------------------------------------------------------------------
		
		radiusReductionChb=new JCheckBox("Radius reduction");
		radiusReductionChb.addActionListener(this);
								
		densityWaterLb.setBounds(10,50,130,16);							densityWaterTf.setBounds(160,50,70,16);
		densityAirLb.setBounds(10,70,130,16);								densityAirTf.setBounds(160,70,70,16);
		windDirectionLb.setBounds(10,90,130,16);					     	windFromCb.setBounds(160,90,70,16);
		windSpeedLb.setBounds(10,110,130,16);							windSpeedKmphTf.setBounds(160,110,100,16);				windSpeedMpsTf.setBounds(280,110,100,16);
		turningRadiusLb.setBounds(10,130,130,60);						turningRadiusTf.setBounds(160,130,100,16);	
		simpleDispLb.setBounds(10,150,130,60);							simpleDispTf.setBounds(160,150,100,16);
		serpentineDispLb.setBounds(10,170,130,60);						serpentineDispTf.setBounds(160,170,100,16);
		//virtualPathChb.setBounds(10,250,200,16);
		
		
		radiusReductionPanel=new JPanel();
		radiusReductionPanel.setBorder(BorderFactory.createTitledBorder("radius reduction"));
		radiusReductionPanel.setLayout(null);
		radiusReductionPanel.setBounds(5,310,400,80);
		
		
		effectiveRangeLb=new JLabel("Effective range :"); 
		effectiveRangeTf=new JTextField();				effectiveRangeTf.setEditable(false);
		
		
		radiusReductionChb.setBounds(10,20,200,16);
		effectiveRangeLb.setBounds(15,40,130,16);
		effectiveRangeTf.setBounds(160,40,100,16);
		
		radiusReductionPanel.add(radiusReductionChb);
		radiusReductionPanel.add(effectiveRangeLb);
		radiusReductionPanel.add(effectiveRangeTf);
		
		
		
		//tunnelWidthLb=new JLabel("tunnel width");						tunnelWidthTf=new JTextField("20");
		//tunnelWidthLb.setBounds(10,190,100,16);							tunnelWidthTf.setBounds(120,190,100,16);
		
		
		
		//upperCenterPanel.add(airDensityLb);								upperCenterPanel.add(airDensityTf);		
		
		centerPanel.add(densityWaterLb);							centerPanel.add(densityWaterTf);
		centerPanel.add(densityAirLb);								centerPanel.add(densityAirTf);
		centerPanel.add(windDirectionLb);							centerPanel.add(windFromCb);						//upperCenterPanel.add(windToCb);
		centerPanel.add(windSpeedLb);								centerPanel.add(windSpeedKmphTf);					centerPanel.add(windSpeedMpsTf);
		centerPanel.add(turningRadiusLb);							centerPanel.add(turningRadiusTf);
		centerPanel.add(simpleDispLb);								centerPanel.add(simpleDispTf);
		centerPanel.add(serpentineDispLb);						centerPanel.add(serpentineDispTf);
		//upperCenterPanel.add(wingSurfaceAreaLb);						upperCenterPanel.add(wingSurfaceAreaTf);
		//upperCenterPanel.add(dropSpeedLb);							upperCenterPanel.add(dropSpeedTf);
		//upperCenterPanel.add(tunnelWidthLb);							upperCenterPanel.add(tunnelWidthTf);
		centerPanel.add(virtualPathPanel);
		centerPanel.add(radiusReductionPanel);
		

		

		
		//centerPanel.add(upperCenterPanel);
		//centerPanel.add(lowerCenterPanel);
		
		
		
		//----------------------------------------------------------------	
		JPanel southPanel=new JPanel();
		okBt=new JButton("ok");
		okBt.addActionListener(this);
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);
		southPanel.add(okBt);
		southPanel.add(cancelBt);
		//-----------------------------------------------------------------
		add("West",westPanel);
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
		
		//vToolBox.blowWind(10);
		
/*		Runnable r=new Runnable()
		{
			public void run()
			{
				while(true)
				{
					dropHeightTf.setText(base.currentHeight+"");
					//dropSpeedTf.setText(base.droppingSpeed+"  m/s");
					
					if(base.candidateLoc!=null)
					{
						Location tempLoc=base.candidateLoc;
						bs0ValLb.setText(tempLoc.distFromBSList.get(0).toString());
						bs1ValLb.setText(tempLoc.distFromBSList.get(1).toString());
						bs2ValLb.setText(tempLoc.distFromBSList.get(2).toString());
						bs3ValLb.setText(tempLoc.distFromBSList.get(3).toString());
						float totalDist=tempLoc.distFromBSList.get(0)+tempLoc.distFromBSList.get(1)+tempLoc.distFromBSList.get(2)+tempLoc.distFromBSList.get(3);
						totalLb.setText(""+totalDist);
						
						try
						{
							nodeBS0IntensityLb.setText(Float.toString(base.candidateNode.distFromBS0));
							nodeBS1IntensityLb.setText(Float.toString(base.candidateNode.distFromBS1));
							nodeBS2IntensityLb.setText(Float.toString(base.candidateNode.distFromBS2));
							nodeBS3IntensityLb.setText(Float.toString(base.candidateNode.distFromBS3));
							

							float totalDiff=base.candidateNode.diff0+base.candidateNode.diff1+base.candidateNode.diff2+base.candidateNode.diff3;
							
							diff0Lb.setText(Float.toString(base.candidateNode.diff0));
							diff1Lb.setText(Float.toString(base.candidateNode.diff1));
							diff2Lb.setText(Float.toString(base.candidateNode.diff2));
							diff3Lb.setText(Float.toString(base.candidateNode.diff3));
							totalDiffLb.setText(Float.toString(totalDiff));
						}
						catch(Exception e){}
					}
					try{Thread.sleep(100);}catch(Exception e){}
				}
			}
		};
		new Thread(r).start();*/
	}
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==windFromCb)
		{
			if(windFromCb.getSelectedItem().toString().equals("North"))
			{
				base.centerPanel.add(base.northArrowLb);
				base.centerPanel.remove(base.southArrowLb);
				base.centerPanel.remove(base.westArrowLb);
				base.centerPanel.remove(base.eastArrowLb);
				base.windFrom=base.NORTH;
				base.windX=0;
				base.windY=1;
			}
			
			if(windFromCb.getSelectedItem().toString().equals("South"))
			{
				base.centerPanel.add(base.southArrowLb);
				base.centerPanel.remove(base.northArrowLb);
				base.centerPanel.remove(base.westArrowLb);
				base.centerPanel.remove(base.eastArrowLb);
				base.windFrom=base.SOUTH;
				base.windX=0;
				base.windY=-1;
			}
			
			if(windFromCb.getSelectedItem().toString().equals("East"))
			{
				base.centerPanel.add(base.eastArrowLb);
				base.centerPanel.remove(base.northArrowLb);
				base.centerPanel.remove(base.westArrowLb);
				base.centerPanel.remove(base.southArrowLb);
				base.windFrom=base.EAST;
				base.windX=-1;
				base.windY=0;
			}
			
			if(windFromCb.getSelectedItem().toString().equals("West"))
			{
				base.centerPanel.add(base.westArrowLb);
				base.centerPanel.remove(base.northArrowLb);
				base.centerPanel.remove(base.southArrowLb);
				base.centerPanel.remove(base.eastArrowLb);
				base.windFrom=base.WEST;
				base.windX=1;
				base.windY=0;
			}
			
			if(windFromCb.getSelectedItem().toString().equals("Null"))
			{
				base.centerPanel.remove(base.westArrowLb);
				base.centerPanel.remove(base.northArrowLb);
				base.centerPanel.remove(base.southArrowLb);
				base.centerPanel.remove(base.eastArrowLb);
				base.windFrom=0;
				base.windX=0;
				base.windY=0;
			}
			
		}		
	}
	
	public void keyPressed(KeyEvent ke)
	{
		
	}
	
	public void keyReleased(KeyEvent ke)
	{
		if(ke.getSource()==windSpeedKmphTf)
		{
			try
			{
				float windSpeedKmph=Float.parseFloat(windSpeedKmphTf.getText());
				base.windSpeedMps=windSpeedKmph*1000.0f/3600.0f;
				windSpeedMpsTf.setText(base.windSpeedMps+"  m/s");
				base.windDelay=1000.0f/base.windSpeedMps;
			}
			catch(Exception e){base.windDelay=1000000;windSpeedMpsTf.setText("0  m/s");}
		}
	}
	public void keyTyped(KeyEvent ke)
	{

	}
		
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource()==stablizeBt)			// DROPS THE NODE FROM FLYING MACHINE WITH CONSTANT SPEED
			{
				try
				{
					String fileName="glider.vf";
					base.figEditorDialog.loadFigure(fileName,base.foregroundFigureList);
				}catch(Exception e){e.printStackTrace();}
				
				if(true)// isOkToGo()
				{	
					//-------------------------------------------
					
					if(windFromCb.getSelectedItem()!="Null")
					{
						//vToolBox.blowWind();
						//base.glidingSpeedKmph=Float.parseFloat(glidingSpeedKmphTf.getText());
						base.glidingSpeedMps=base.glidingSpeedKmph*1000.0f/3600.0f;
						switch(windFromCb.getSelectedIndex())
						{
							case 1:
							{
								base.windDxFloat=0.0f;
								base.windDyFloat=base.windSpeedMps/base.glidingSpeedMps;
								
								System.out.println("windSpeed = "+base.windSpeedMps+" gliding speed = "+ base.glidingSpeedMps +"  wind dy :"+base.windDyFloat);
								//System.exit(0);
							}
							break;
							
							case 2:
							{
								
							}
							break;
							
							case 3:
							{
								
							}
							break;
							
							case 4:
							{
								
							}
							break;
						}								
					}
					
					
								
					//-------------------------------------------
					
					//base.angleOfGlide=Double.parseDouble(glideAngleTf.getText());
					System.out.println("Gliding angle :"+base.angleOfGlide);
					
					//base.droppingHeight=base.scale*Double.parseDouble(dropHeightTf.getText());
					base.turningRadius=base.scale*(Float.parseFloat(turningRadiusTf.getText()));
					base.maxVirtualPathUpdateDist=(float)Math.PI*base.turningRadius;
					
					base.simpleDist=base.droppingHeight/Math.sin(Math.toRadians(base.angleOfGlide));
					base.serpentineDisp=(2*base.simpleDist)/Math.PI;
					
					//---horizontal displacement while serpentine path...
					
					
					System.out.println("simple displacement :"+base.simpleDist);
					simpleDispTf.setText((int)base.simpleDist+"");
					serpentineDispTf.setText((int)base.serpentineDisp+"");
					
					base.gliderTotalStepsForARotation=(float)(Math.PI*base.turningRadius);
					base.gliderRotationAnglePerStep=(float)Math.PI/base.gliderTotalStepsForARotation;
					
					//System.exit(0);
					
				//-------------------------------------------
									
					base.candidateNode=new VLabel(1,base.BLUE,base);
					base.candidateNode.setBounds(base.gliderCenterLoc.x,base.gliderCenterLoc.y,4,4);
					base.centerPanel.add(base.candidateNode);
				//  base.revalidate();
					base.gads=true;	
					base.turningPoint=new Location(base.startPoint.x,base.startPoint.y,base);
				//	base.pathWidth=Integer.parseInt(tunnelWidthTf.getText());
					base.pathWidth=base.pathWidth/2;
					base.oldDist=vToolBox.getDist(base.candidateLoc,base.candidateNode.getLoc());
				//---------------------------------------------------------------------
					base.areaOfInterest.startX=100;
					base.areaOfInterest.startY=100;
					base.areaOfInterest.endX=Integer.parseInt(base.dxTf.getText())+100;
					base.areaOfInterest.endY=Integer.parseInt(base.dyTf.getText())+100;
					//---------------------------------------------------------------------
					
					vToolBox.moveAhead();
					

					
					if(virtualPathChb.isSelected())
					{
						//vToolBox.updatePath(2000);// mark
					}
				//	vToolBox.startAADSController();
					
				//-------------------------------------------------------------------------	
					vToolBox.freezePath();  // marked
					//vToolBox.followTrack(100);
					base.newDist=vToolBox.getDist(base.candidateLoc,base.candidateNode.getLoc());
					//vToolBox.followPath(); // marked
				//-------------------------------------------------------------------------	
					
				}
				
				else
				{
					base.alert.display("uncomplete data");
				}
			}
		}
	
	
	//**************FUNCTION TO HANDLE NODE DROPPING IN AADS*******************************
	
	public void dropNode(final VLabel node)
	{
		base.nodeTrackList.add(base.startPoint);
		base.x=base.startPoint.x;
		base.y=base.startPoint.y;
		color=base.startPoint.color;
		shape=base.startPoint.shape;
			
		try
		{

		}catch(Exception e){}
		Runnable r=new Runnable()
		{
			public void run()
			{
				
			}
		};
		new Thread(r).start();
	}
	
	
	
	public void dropNode1(final VLabel node)
	{
		//base.centerPanel.spreadCoordinateMat(1000,1000);
		
		base.nodeTrackList.add(base.startPoint);
		base.x=base.startPoint.x;
		base.y=base.startPoint.y;
		color=base.startPoint.color;
		shape=base.startPoint.shape;
		Runnable r=new Runnable()
		{
			public void run()
			{							
				for(;;)
				{
					base.x=base.x+base.xInr;
					base.y=base.y+base.yInr;
					base.centerPanel.coordinateMat[base.x][base.y].color=base.RED;
					VCoordinate tempCoordinate=base.centerPanel.coordinateMat[base.x][base.y];
					base.nodeTrackList.add(tempCoordinate);
/*					if(base.masterBSList.size()>=3)
					{
						node.distFromBS0=vToolBox.getDist(node,base.masterBSList.get(0));
						node.distFromBS1=vToolBox.getDist(node,base.masterBSList.get(1));
						node.distFromBS2=vToolBox.getDist(node,base.masterBSList.get(2));
						node.distFromBS3=vToolBox.getDist(node,base.masterBSList.get(3));
					}*/	
					node.updateLocation(tempCoordinate);
					//System.out.println("the name of author is vikrant sharma: "+ base.nodeTrackList.get(base.nodeTrackList.size()-1).x);
					try{Thread.sleep(50);}catch(Exception e){}
				}
			}
		};
		new Thread(r).start();
	}
	
	//*************************************************************************************
	
	//-----------------SPREAD 3 DIMENSIONAL COORDINATES------------------------------------
	public void spread3DCoordinateData(int x, int y,int z)
	{
		for(int i=0;i<y;i++)
		{
			for(int j=0;j<x;j++)
			{
				for(int k=0;k<z;k++)
				{
					//base.centerPanel.coordinateMat[i][j].temperature=50;
				}
			}
		}
		
	}		
	//-------------------------------------------------------------------------------------
	
	//-------------DECORATE CENTER PANEL---------------------------------------------------
	
	void decorate()
	{
		base.northDirectionLb.setBounds(330,80,100,20);
		base.centerPanel.add(base.northDirectionLb);
		base.northArrowLb.setBounds(300,70,20,30);
		//base.centerPanel.add(base.northArrowLb);
		
		base.southDirectionLb.setBounds(330,605,100,20);
		base.centerPanel.add(base.southDirectionLb);
		base.southArrowLb.setBounds(300,605,20,30);
		//base.centerPanel.add(base.southArrowLb);
		
		base.westDirectionLb.setBounds(40,300,60,20);
		base.centerPanel.add(base.westDirectionLb);
		base.westArrowLb.setBounds(40,330,30,20);
		//base.centerPanel.add(base.westArrowLb);
		
		base.eastDirectionLb.setBounds(605,300,80,20);
		base.centerPanel.add(base.eastDirectionLb);
		base.eastArrowLb.setBounds(605,330,30,20);
		//base.centerPanel.add(base.eastArrowLb);
		
		base.centerPanel.revalidate();
	}
	//-------------------------------------------------------------------------------------

	
	public static void main(String args[])
	{
		System.out.println("the name of the auhhor is vikrant sharma");
		GadsDeploymentControlDialog dmp=new GadsDeploymentControlDialog(null);
		dmp.setBounds(200,200,600,500);
		dmp.setVisible(true);		
	}	
}
