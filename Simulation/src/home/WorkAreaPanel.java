package home;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;

public class WorkAreaPanel extends JPanel implements MouseMotionListener,MouseListener
{
	
	Graphics gr;
	MouseEvent mouseEvent;
	Base base;
	int x=20;
	int y=20;
	int x1=0;
	int y1=0;
	
	
	
	int typ;
	
	//int xConnectorArray[];
	//int yConnectorArray[];
	
	JLabel lb=new JLabel("love u");
	ImageIcon imgb=new ImageIcon("images/bnode.jpg");
	ImageIcon imgr=new ImageIcon("images/rnode.jpg");
	ImageIcon imgg=new ImageIcon("images/gnode.jpg");
	
	ImageIcon eventIcon=new ImageIcon("images/event.gif");
	JLabel lb2=new JLabel(imgb);
	VLabel clipBoardLb=null;
	Actuator actuatorClipBoard=null;
	VLabel emptyLb=new VLabel(0,"",base);
	Actuator emptyActuator=new Actuator(base,Color.PINK);
	
	//JLabel timeStampLb, timeStampLb2;
	
	JPanel thisPanel; 
	
	InfoPanel infoPanel;
	//-------------------------DECLARING PRIVATE VARIABLES----------------------
	VToolBox vToolBox;
	int x3,x4,y3,y4;
	int x7,y7,x8,y8;
	//-------------------------------------------------------------------------
	
	//********************DECLAIRING COORDINATE MAT****************************
	VCoordinate coordinateMat[][]=new VCoordinate[2000][2000];	
	//-------------------------------------------------------------------------
	int eventCondition=0;
	
	//ArrayList<Location>eventLocList=new ArrayList<Location>();
	
	VDaOptionDialog daOptionWindow;
	
	WorkAreaPanel(Base b)
	{
		base=b;
		vToolBox=new VToolBox(base);
		
		
		//---------------------LOADING DEFAULTS-------------------------
		
/*		if(base.showCrossbar==true)
		{
			base.figEditorDialog.loadFigure("crossbar.vf",base.backgroundFigureList);
		}
		*/
		if(base.showGrid==true)
		{
			base.figEditorDialog.loadFigure("grid.vf",base.backgroundFigureList);
		}
		
		
		//--------------------------------------------------------------
		
		thisPanel=this;
		setLayout(null);
		daOptionWindow=new VDaOptionDialog(base);
		Dimension d=base.getSize();
		//System.out.println(d.getHeight());
		//System.out.println(d.getWidth());
		base.baseHeight=(int)d.getHeight();
		base.baseWidth=(int)d.getWidth();
		
		int crHeight=Integer.parseInt(base.dyTf.getText());
		int crWidth=Integer.parseInt(base.dxTf.getText());
		
		spreadCoordinateMat(100,100,crHeight,crWidth);
		spreadTemperature(100,100,crHeight,crWidth);
		
		
		
		infoPanel=new InfoPanel(base);
		infoPanel.setBounds(0,0,1500,80);//??????????????
		
		add(infoPanel);					
		
		addMouseMotionListener
		(
				new MouseMotionAdapter()
				{
					public void mouseDragged(MouseEvent me)
					{
						if(clipBoardLb==null)
						{
							x=me.getX()-100;
							y=me.getY()-100;
							//base.dxTf.setText(Integer.toString(x));
							//base.dyTf.setText(Integer.toString(y));
						}
					}
					
					public void mouseMoved(MouseEvent me)
					{
						try
						{
						if(base.candidateObstacle.obstacleResizable==true)
						{
							int width=base.candidateObstacle.getWidth();
							int height=base.candidateObstacle.getHeight();
							int x=base.candidateObstacle.getX();
							int y=base.candidateObstacle.getY();
							
							//base.westPanel.obstacleDialog.obstacle.setBounds(base.westPanel.obstacleDialog.obstacle.getX(),base.westPanel.obstacleDialog.obstacle.getY(),me.getX()-(width+20),me.getY()-(height+20));
							base.candidateObstacle.setBounds(base.candidateObstacle.getX(),base.candidateObstacle.getY(),me.getX()-(x+20),me.getY()-(y+20));
							base.candidateObstacle.resizeLb.setBounds(width-15,height-17,15,15);
							int x1=base.candidateObstacle.xLen=base.candidateObstacle.getWidth();
							int y1=base.candidateObstacle.yLen=base.candidateObstacle.getHeight();
							
							base.candidateObstacle.posLb.setText(x1+" X "+y1);
							base.candidateObstacle.revalidate();
						}
						
						if(base.candidateObstacle.obstacleMovable==true)
						{
							int width=base.candidateObstacle.getWidth();
							int height=base.candidateObstacle.getHeight();
							base.candidateObstacle.setBounds(me.getX()-width,me.getY()-height,width,height);	
						}
						}
						catch(Exception e)
						{
							//System.out.println("workPanel line 119"+e);
						}
						
						//-------------------ADDING THE EVENT LOCATION INFO-----------------
						if(base.eventFlag)
						{
							base.exLb.setText(Integer.toString(me.getX()));
							base.eyLb.setText(Integer.toString(me.getY()));
						}
						//------------------------------------------------------------------
						
					//	if(clipBoardLb!=null)
					//	{
							x1=me.getX();
							y1=me.getY();
							
							base.mxLb.setText(Integer.toString(x1));
							base.myLb.setText(Integer.toString(y1));
														
							try
							{
								if(clipBoardLb.getNodeType()==1)
								{
									clipBoardLb.setBounds(x1-15, y1-15,base.nodeSize,base.nodeSize);
									clipBoardLb.xPos=x1-15;
									clipBoardLb.yPos=y1-15;									
								}
								if(clipBoardLb.getNodeType()==2)
									clipBoardLb.setBounds(x1-15, y1-15,12,12);
								if(clipBoardLb.getNodeType()==3)
									clipBoardLb.setBounds(x1-25,y1-25,18,18);
							}
							catch(NullPointerException e){}
						//	if(actuatorClipBoard!=null)
						//	{
							try
							{
								actuatorClipBoard.setBounds(x1-25,y1-25,18,18);
							}
							catch(NullPointerException e){}
							//}
								
							//repaint();
							revalidate();
						}
					//}
				}
		);
		
		addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent me)
					{
						int mouseButton=me.getButton();
						deselectAll();
						if(mouseButton==1)
						{
							try
							{
								if(clipBoardLb.getNodeType()==1)
									clipBoardLb.setBackground(clipBoardLb.defaultColor);//???????????????????????????????????????///
								if(clipBoardLb.getNodeType()==2)
									clipBoardLb.setBackground(clipBoardLb.defaultColor);
								
								actuatorClipBoard.setBackground(actuatorClipBoard.defaultColor);
							}
							catch(Exception e){}
						}
						
						if(mouseButton==3 && me.getX()<=Integer.parseInt(base.dxTf.getText())+100 && me.getY()<=Integer.parseInt(base.dyTf.getText())+100 && me.getX()>=100 && me.getY()>=100)
						{		
							base.locationClipBoard=new Location(me.getX(),me.getY(),base);
							//daOptionWindow.setBounds(me.getX()+70,me.getY()+70,100,250);
							base.clipBoardLoc=new Location(me.getX(),me.getY(),base);
							daOptionWindow.setBounds(me.getX()+40,100,150,500);
							daOptionWindow.setVisible(true);
						}
						
						//**************************STARTING THE EVENT******************************
						
						//**************************************************************************
						if(base.eventFlag==true)
						{
							base.epicenterX=me.getX();
							base.epicenterY=me.getY();
							base.eventFlag=false;
							System.exit(0);
						}
						
						clipBoardLb=emptyLb;
						actuatorClipBoard=emptyActuator;
						
					}
				}
		);
		

		
		addMouseListener
		(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent me)
					{
						for(int i=0;i<base.obstacleCount;i++)
						{
							base.obstacleArray[i].obstacleResizable=false;
							base.obstacleArray[i].obstacleMovable=false;
						}
						if(base.eventFlag)
						{
							
							base.epicenterX=me.getX();
							base.epicenterY=me.getY();
							base.eventFlag=false;
							base.centerPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							base.exLb.setForeground(Color.BLACK);
							base.eyLb.setForeground(Color.BLACK);
							//System.exit(0);
							Runnable r=new Runnable()
							{
								public void run()
								{
									eventCondition=200;
									for(int i=1;i<eventCondition;i++)
									{
										
										try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
								        base.radius=base.radius+1;
								        //base.xEventOuterList.removeAll(base.xEventMiddleList);
								        //base.yEventOuterList.removeAll(base.yEventMiddleList);
								        try
								        {
								        	vToolBox.circle(base.xEventOuterList, base.yEventOuterList,base.epicenterX,base.epicenterY,base.radius);								     
								        	vToolBox.circle(base.xEventMiddleList, base.yEventMiddleList,base.epicenterX,base.epicenterY,base.radius-20);
								        	vToolBox.circle(base.xEventInnerList, base.yEventInnerList,base.epicenterX,base.epicenterY,base.radius-40);
								        }
								        catch(Exception e){System.out.println(e);}
								        //repaint();
								        //revalidate();
								       // System.out.println("fire expanding");
									}
								}
							};
							new Thread(r).start();
						}
					}
				}
			);


		
		
	}
	
	//**********************PAINTER WORK PLACE********************************************
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		gr=g;
		base.baseGraphics=g;
		
		
		this.setBackground(new Color(255,255,255));
	
		add(lb);	
		//add(lb2);
		
		//g.setColor(new Color(245,250,250));
		
		
		try
		{
			base.scale=Float.parseFloat(base.scaleTf.getText());
			
		}catch(Exception e){}
		
		
		
		try
		{
			g.setColor(Color.WHITE);
			
			if(base.coverageShowable)
			{
				g.setColor(new Color(230,230,230));
			}
			
			if(base.dAShapeCb.getSelectedItem()=="square")
			{
				g.fillRect(100, 100,Integer.parseInt(base.dxTf.getText()),Integer.parseInt(base.dyTf.getText()));
			}
			if(base.dAShapeCb.getSelectedItem()=="circle")
			{
				g.fillOval(base.dxCenter-Integer.parseInt(base.dRadiusTf.getText()),base.dxCenter-Integer.parseInt(base.dRadiusTf.getText()) ,2*(Integer.parseInt(base.dRadiusTf.getText())),2*(Integer.parseInt(base.dRadiusTf.getText())));
			}
			

			if(base.drawBackgroundFig==true && base.gridShowable==true)
			{
				for(int i=0;i<base.backgroundFigureList.size();i++) //0 is a cross bar
				{
					
					for(int j=0;j<base.backgroundFigureList.get(i).lineList.size();j++)
					{
						int x1=(int)base.backgroundFigureList.get(i).lineList.get(j).x1;
						int y1=(int)base.backgroundFigureList.get(i).lineList.get(j).y1;
						int x2=x1+(int)(base.backgroundFigureList.get(i).lineList.get(j).length*Math.sin(base.backgroundFigureList.get(i).lineList.get(j).angle));
						int y2=y1+(int)(base.backgroundFigureList.get(i).lineList.get(j).length*Math.cos(base.backgroundFigureList.get(i).lineList.get(j).angle));
						
						g.setColor(base.backgroundFigureList.get(i).lineList.get(j).color);
						g.drawLine(x1, y1, x2, y2);
	/*					g.setColor(Color.RED);
						g.fillOval(x1-2,y1-2,4,4);
						
						g.setColor(Color.GREEN);
						g.fillOval(x2-2,y2-2,4,4);*/
						
						g.setColor(Color.BLACK);
					}
				}
				g.setColor(new Color(255,255,255));
			}
			
			if(base.coverageShowable)
			{
				g.setColor(new Color(230,230,230));
			}
/*			else
			{
				g.setColor(new Color(255,255,255));
			}*/
			
			if(base.dAShapeCb.getSelectedItem()=="square")
			{
				g.setColor(new Color(100,100,100));
				g.drawRect(100,100,Integer.parseInt(base.dxTf.getText()),Integer.parseInt(base.dyTf.getText()));
				g.drawRect(99,99,Integer.parseInt(base.dxTf.getText())+2,Integer.parseInt(base.dyTf.getText())+2);
			}
			
			if(base.coverageShowable)
			{
				g.setColor(new Color(230,230,230));
			}	
			
			if(base.dAShapeCb.getSelectedItem()=="circle")
			{
				g.setColor(new Color(100,100,100));
				g.fillOval(base.dxCenter-3,base.dxCenter-3,6,6);
				g.drawOval(base.dxCenter-Integer.parseInt(base.dRadiusTf.getText()),base.dxCenter-Integer.parseInt(base.dRadiusTf.getText()) ,2*(Integer.parseInt(base.dRadiusTf.getText())),2*(Integer.parseInt(base.dRadiusTf.getText())));
				g.drawOval(base.dxCenter-Integer.parseInt(base.dRadiusTf.getText())-1,base.dxCenter-Integer.parseInt(base.dRadiusTf.getText())-1 ,2*(Integer.parseInt(base.dRadiusTf.getText()))+2,2*(Integer.parseInt(base.dRadiusTf.getText()))+2);
			}
			g.setColor(Color.black);
		}
		catch(Exception e){System.out.println("line 278 "+e);}
		
		
		//------------MAKING RECTANGULAR PARTITION---------------------------------
		g.setColor(new Color(125,222,242));
		int halfOfPartiton=base.partitionWidth/2;
		g.fillRect(100,base.randomY+100-halfOfPartiton,base.partitionCounterX,base.partitionWidth);
		g.fillRect(base.randomX+100-halfOfPartiton,100,base.partitionWidth,base.partitionCounterY);
		//-------------------------------------------------------------------------
		
			int dia=base.radius*2;
			g.setColor(new Color(247,250,135));
			g.fillOval(base.epicenterX-((dia)/2),base.epicenterY-((dia)/2),dia,dia);
		
			g.setColor(Color.ORANGE);
			g.fillOval(base.epicenterX-((dia-40)/2),base.epicenterY-((dia-40)/2), dia-40,dia-40);
		
			g.setColor(new Color(225,225,225));
			g.fillOval(base.epicenterX-((dia-80)/2),base.epicenterY-((dia-80)/2),dia-80,dia-80);
		
		/*g.setColor(new Color(177,158,228));
		for(int i=0;i<base.xEventOuterList.size();i++)
		{
			try
			{
				g.drawRect(base.xEventOuterList.get(i), base.yEventOuterList.get(i),0,0);
			}
			catch(Exception e){System.out.println(e);}
		}
		
		g.setColor(Color.RED);
		for(int i=0;i<base.xEventMiddleList.size();i++)
		{
			try
			{
				g.drawRect(base.xEventMiddleList.get(i), base.yEventMiddleList.get(i),0,0);
			}
			catch(Exception e){System.out.println(e);}
		}*/
		
			
		//===============================================================================	
			
		    if(base.areaOfInterest!=null)
		    {
		    	//g.setColor(new Color(196,236,168));
		    	g.setColor(new Color(255,255,255));
		    	g.fillRect(base.areaOfInterest.startX,base.areaOfInterest.startY, base.areaOfInterest.endX-base.areaOfInterest.startX,base.areaOfInterest.endY-base.areaOfInterest.startY);
		    }		
		
		//===============================================================================
		//--------------------------------------MODIFY-----------------------------------
		for(int i=0;i<base.tempList.size();i++)
		{
					g.setColor(Color.magenta);
					int x=base.tempList.get(i).getXLoc();
					int y=base.tempList.get(i).getYLoc();
					g.drawRect(x,y,0,0);			
		}
		
/*		for(int i=0;i<base.tempList2.size();i++)
		{
					g.setColor(Color.magenta);
					int x=base.tempList2.get(i).getXLoc();
					int y=base.tempList2.get(i).getYLoc();
					g.drawRect(x,y,0,0);			
		}*/
		
		for(int i=0;i<base.daSubdivisionList.size();i++)
		{
			ArrayList<Location>tempLocList=base.daSubdivisionList.get(i);
			for(int j=0;j<tempLocList.size();j++)
			{
				g.setColor(Color.GRAY);
				int x=tempLocList.get(j).getXLoc();
				int y=tempLocList.get(j).getYLoc();
				g.drawRect(x,y,0,0);
			}
		}
		
		
		
		//-------------------------------------------------------------------------------
		
		g.setColor(Color.DARK_GRAY);
		for(int i=0;i<base.xEventInnerList.size();i++)
		{
			try
			{
				g.drawRect(base.xEventInnerList.get(i), base.yEventInnerList.get(i),1,1);
			}
			catch(Exception e){System.out.println(e);}
		}
		
		//Point p=new Point();
		//**************************CONNECTIONS OF BASE STATIONS*************************
		/*if(base.bsConnShowable)
		{
			for(int i=0;i<base.baseStationCount;i++)
			{
				int x1=base.baseStationArray[i].getX();
				int y1=base.baseStationArray[i].getY();
				g.setColor(base.baseStationArray[i].lineColor);
				for(int j=0;j<base.baseStationArray[i].neighborList.size();j++)
				{
					try
					{
						if(base.baseStationArray[i].connectionList.get(j)==true)
							g.drawLine(x1, y1,base.baseStationArray[i].neighborList.get(j).getX(),base.baseStationArray[i].neighborList.get(j).getY());
					}
					catch(Exception e){System.out.println("line no: 293 work panel:"+e);}
				}
			}
		}*/
		//------------------------------------------------------------------------------
		
		
		
		
	   
		
		g.setColor(Color.black);
		
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			int c=0;
			VLabel tempVLabel=null;
			x3=base.masterNodeList.get(i).xPos;  //new
			y3=base.masterNodeList.get(i).yPos;  //new
			
			g.setColor(Color.RED);
			for(int k=0;k<base.masterBSList.size();k++)
			{
				VLabel baseStation=base.masterBSList.get(k);
				for(int l=0;l<baseStation.nodesConnectingBaseList.size();l++)
				{
					VLabel tempNode=baseStation.nodesConnectingBaseList.get(l);
					g.drawLine(baseStation.getX(), baseStation.getY(), tempNode.getX(), tempNode.getY());
				}
				
			}
			
			for(int j=0;j<base.masterNodeList.get(i).neighborList.size();j++)
			//	for(int j=0;j<base.nodeCount;j++)
			{
				try{
				tempVLabel=base.masterNodeList.get(i).neighborList.get(j);
				x4=tempVLabel.xPos; 
				y4=tempVLabel.yPos;
				if(base.masterNodeList.get(i).commRange>vToolBox.getDist(x3, y3, x4, y4) && base.masterNodeList.get(i).connectionList.get(j))
				{
					if(base.connectionShowable)
					{
						g.setColor(base.masterNodeList.get(i).lineColor);
						g.drawLine(x3,y3,x4,y4);
						c++;
					}
				}
			}catch(Exception e){}
			}
		}
		
		if(base.clusterConnShowable)
		{
			for(int i=0;i<base.masterNodeList.size();i++)
			{
				VLabel tempNode=base.masterNodeList.get(i);
				if(tempNode.isClusterHead==false && tempNode.nodeClusterHead!=null)
				{
					int x1=tempNode.getX();
					int y1=tempNode.getY();
					int x2=tempNode.nodeClusterHead.getX();
					int y2=tempNode.nodeClusterHead.getY();
					g.drawLine(x1, y1, x2, y2);									
				}
			}
		}
		
		
		//------DRAWING LINES TO SHOW MST CONNECTIONS------------------
		for(int i=0;i<base.mstConnectionPairList.size();i++)
		{
			VLabel startNode=base.mstConnectionPairList.get(i).startNode;
			VLabel endNode=base.mstConnectionPairList.get(i).endNode;
			if(startNode.isAlive && endNode.isAlive)
			{
				int x1=startNode.getX();
				int y1=startNode.getY();
				int x2=endNode.getX();
				int y2=endNode.getY();		
				g.setColor(Color.BLUE);
				g.drawLine(x1, y1, x2, y2);
			}
		}
		//-------------------------------------------------------------
		
		//-----DRAWING LINES TO SHOW THE ROOTED TREE CONNECTIONS FROM BASE STATION-------------
		if(base.baseStation_Tree_Connection_Showable)
			{
			for(int i=0;base.drawPathTree==true && i<base.masterBSList.get(0).master_Pcd_List.size();i++)
			{
				//	System.out.println(")
				if(base.masterBSList.get(0).master_Pcd_List.get(i).connection)
				{
					VLabel parent=(VLabel)base.masterBSList.get(0).master_Pcd_List.get(i).parent;
					VLabel child=base.masterBSList.get(0).master_Pcd_List.get(i).child;
					
					int x1=parent.getX();
					int y1=parent.getY();
					int x2=child.getX();
					int y2=child.getY();
					g.setColor(Color.ORANGE);
					g.drawLine(x1, y1, x2, y2);
				}
			}
		}
		
		//-------------------------------------------------------------------
		
		//------DRAWING LINES TO SHOW THE ROOTED TREE CONNECTION FROM ACTUATORS--------
		if(base.actuator_Tree_Connection_Showable)
		{
			g.setColor(Color.GRAY);
			for(int i=0;i<base.actuatorList.size();i++)
			{
				Actuator tempActuator=base.actuatorList.get(i);
				for(int j=0;j<tempActuator.master_Pcd_List.size();j++)
				{
					if(tempActuator.master_Pcd_List.get(j).connection)
					{
						Object parent;
						VLabel child;
						parent=tempActuator.master_Pcd_List.get(j).parent;
						child=tempActuator.master_Pcd_List.get(j).child;
						int x1=0;
						int y1=0;
						try
						{
							x1=VLabel.class.cast(parent).getX();
							y1=VLabel.class.cast(parent).getY();
						}	
						catch(Exception e){};
						
						try
						{
							x1=Actuator.class.cast(parent).getX();
							y1=Actuator.class.cast(parent).getY();
						}
						catch(Exception e){}
						
						int x2=child.getX();
						int y2=child.getY();
						
						g.drawLine(x1, y1, x2, y2);
					}
				}
			}
		}
		
		//---------MAKING INTER CLUSTER CONNECTIONS-----------------------
		
		if(base.interClusterConnectionShowable)
		{
			for(int i=0;i<base.clusterList.size();i++)
			{
				Cluster tempCluster=base.clusterList.get(i);
				VLabel clusterHeader=tempCluster.clusterHead;
				ArrayList<VLabel>memberList=tempCluster.memberList;
				int x=clusterHeader.getX();
				int y=clusterHeader.getY();
				for(int j=0;j<memberList.size();j++)
				{
					VLabel clusterNeighbor=memberList.get(j);
					g.drawLine(x, y,clusterNeighbor.getX(),clusterNeighbor.getY());
				}
			}
			
		}
		//-------------------------------------------------------------------
		
		//----------CLUSTER HEAD DATA TRANSMISSION---------------------------
		
		try
		{
			g.setColor(Color.RED);
			int x1=base.tempNode.getX();
			int y1=base.tempNode.getY();
			int x2=base.masterBSList.get(0).getX();
			int y2=base.masterBSList.get(0).getY();
			g.drawLine(x1, y1, x2, y2);
		}
		catch(Exception e){}
		//-------------------------------------------------------------------
		
		
		//*******DRAWING CIRCLES AROUND THE NODES******************
		
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			int commRange=(int)base.masterNodeList.get(i).commRange;
			int sensingRange=(int)base.masterNodeList.get(i).sensingRange;
			if(base.commRangeShowable)
			{
				g.setColor(new Color(255,195,195));
				g.drawOval(base.masterNodeList.get(i).xPos-commRange,base.masterNodeList.get(i).yPos-commRange,commRange*2,commRange*2);
			}
			if(base.sensingRangeShowable)
			{
				if(base.coverageShowable)
				{
					g.setColor(new Color(240,200,200));
					g.fillOval(base.masterNodeList.get(i).xPos-sensingRange,base.masterNodeList.get(i).yPos-sensingRange,sensingRange*2,sensingRange*2);
				}
				
				g.setColor(new Color(100,100,255));
				g.drawOval(base.masterNodeList.get(i).xPos-sensingRange,base.masterNodeList.get(i).yPos-sensingRange,sensingRange*2,sensingRange*2);
				

			}
		}
		
		//***********************************************************
		
		//-----------------DRAWING CIRCLES AROUND THE ACTUATORS-----------------
		for(int i=0;i<base.actuatorList.size();i++)
		{
			int actuatorCommRange=(int)base.commRangePerActuator;
			int xPos=base.actuatorList.get(i).getX();
			int yPos=base.actuatorList.get(i).getY();
			if(base.actuatorCommRangeShowable && base.actuatorList.get(i).isAlive )
			{
				g.setColor(new Color(255,195,195));
				g.drawOval(base.actuatorList.get(i).getX()-actuatorCommRange,base.actuatorList.get(i).getY()-actuatorCommRange,actuatorCommRange*2,actuatorCommRange*2);
			}
		}
		//----------------------------------------------------------------------
		
		//-------------------DRAWING EFFECTIVE RADIUS CENTERED AT BS------------
		g.setColor(Color.BLUE);
		g.drawOval(100-base.effectiveRadius,100-base.effectiveRadius,base.effectiveRadius*2,base.effectiveRadius*2);
		
		//----------------------------------------------------------------------
		
		if(base.sendFrom!=null && base.sendTo!=null)
		{
			g.setColor(Color.RED);
			g.drawLine(base.sendFrom.getXLoc(),base.sendFrom.getYLoc(),base.sendTo.getXLoc(),base.sendTo.getYLoc());
			base.sendTo=null;
			base.sendFrom=null;
		}
		
		
		
		//------------DRAWING CONNECTION LINES TO NODES DIRECTLY CONNECTING MMU-------
		g.setColor(Color.RED);
		if(base.mmu_Node_Connection_Showable)
		{
			for(int i=0;i<base.actuatorList.size();i++)
			{
				for(int j=0;j<base.actuatorList.get(i).nodesDirectlyConnectedActuator.size();j++)
				{
					if(vToolBox.getDist(base.actuatorList.get(i),base.actuatorList.get(i).nodesDirectlyConnectedActuator.get(j))<base.commRangePerNode && base.actuatorList.get(i).isAlive)
					{
						int x1=base.actuatorList.get(i).getX();
						int y1=base.actuatorList.get(i).getY();
						int x2=base.actuatorList.get(i).nodesDirectlyConnectedActuator.get(j).getX();
						int y2=base.actuatorList.get(i).nodesDirectlyConnectedActuator.get(j).getY();
						g.drawLine(x1, y1, x2, y2);
					}
				}
			}
		}
		//----------------------------------------------------------------------------
		
		//****************drawing horizontal lines for nodes********************
		
		if(base.plotHexagonForNodes==true)
		{
			int width=Integer.parseInt(base.dxTf.getText());
			int height=Integer.parseInt(base.dyTf.getText());
			int counter=1;
			for(float k=0;k<height;k=k+((base.commRangePerNode-1)/2))
			{
				if(base.floorLinesShowable==true)
				{
					g.setColor(new Color(200,200,200));
					g.drawLine(100,(int)(100+k),100+width,(int)(100+k));
				}
				
				/*for(int l=0;l<width;l=l+100)
				{
					g.setColor(new Color(50,50,50));
					if(counter%2==0)
						g.drawOval(100+l,(int)(100+k),3,3);
					else
						g.drawOval(150+l,(int)(100+k),3,3);	
				}
				counter=counter+1;*/
			
			}			
		}
		
		if(base.desiredLocShowable)
		{
			g.setColor(new Color(0,0,0));
			for(int a=0;a<base.desiredLocList.size();a++)
			{
				g.drawOval(base.desiredLocList.get(a).getXLoc()-4,base.desiredLocList.get(a).getYLoc()-4,8,8);
/*				JLabel lb=new JLabel(Integer.toString(base.desiredLocList.get(a).group));
				lb.setBounds(base.desiredLocList.get(a).getXLoc(),base.desiredLocList.get(a).getYLoc(),15,15);
				base.centerPanel.add(lb);
				base.centerPanel.revalidate();*/
			}
		}
				
		//****************DRAWING HORIZONTAL LINES FOR ACTUATORS********************
		
		if(base.plotHexagonForActuators==true)
		{
			int width=Integer.parseInt(base.dxTf.getText());
			int height=Integer.parseInt(base.dyTf.getText());
			int counter=1;
			for(float k=0;k<height;k=k+((base.commRangePerActuator-1)/2))
			{
				if(base.floorLinesShowable==true)
				{
					g.setColor(new Color(200,200,255));
					g.drawLine(100,(int)(100+k),100+width,(int)(100+k));
				}
				
				/*for(int l=0;l<width;l=l+100)
				{
					g.setColor(new Color(50,50,50));
					if(counter%2==0)
						g.drawOval(100+l,(int)(100+k),3,3);
					else
						g.drawOval(150+l,(int)(100+k),3,3);	
				}
				counter=counter+1;*/
			
			}
			
			if(base.desiredLocShowable)
			{
				g.setColor(new Color(0,0,0));
				for(int a=0;a<base.actuatorDesiredLocList.size();a++)
				{
					g.drawOval(base.actuatorDesiredLocList.get(a).getXLoc()-3,base.actuatorDesiredLocList.get(a).getYLoc()-3,6,6);
				}
			}
		}
		
		if(base.nodesShowable)
		{
			g.setColor(new Color(0,0,0));
			for(int a=0;a<base.masterNodeList.size();a++)
			{
				g.fillOval(base.masterNodeList.get(a).getX()-4, base.masterNodeList.get(a).getY()-4,8,8);
			}
		}
		
		if(base.rangeShowable)
		{
//			int radius=70;
			//int radius=35;
			//int radius=base.range;
			try
			{
				int radius=(int)(base.commRangePerNode-1)/2;//?????????????????????????????????????????????????????????????
				int loopValue=(int)Math.ceil(710/radius);
				//int radius=35;
				for(int i=1;i<=loopValue+1;i++)
				{
					int val=radius*i;
					try
					{
						g.drawOval(base.masterBSList.get(0).getX()-val,base.masterBSList.get(0).getY()-val,val*2,val*2);
					}catch(Exception e){}
				}
			}
			catch(Exception ex){}
		}
		//--------------------------MODIFICATION FOR TEST------------------------------
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			try
			{
				for(int j=0;j<base.masterNodeList.get(i).obstacleShapeList.size();j++)
				{
					Location tempLoc=base.masterNodeList.get(i).obstacleShapeList.get(j);
					int x=tempLoc.getXLoc();
					int y=tempLoc.getYLoc();
					g.setColor(tempLoc.color);
					g.fillRect(x, y,2,2);
				}
			}catch(Exception e){System.out.println("obstacle not present"+e);}
		}
		//-----------------------------------------------------------------------------
		
		//-----------------RANDOM EVENT GENERATION-------------------------------------
	/*	for(int l=0;l<eventLocList.size();l++)
		{
			try
			{
			Location tempLoc=eventLocList.get(l);
			JLabel lb=new JLabel(eventIcon);
			int x=tempLoc.getXLoc();
			int y=tempLoc.getYLoc();
			lb.setBounds(x+100,y+100,20,20);
			add(lb);
			}catch(Exception e){}
		}*/
		//-----------------------------------------------------------------------------
		
		for(int i=0;i<base.testPoints.size();i++)
		{
			Location loc=base.testPoints.get(i);
			g.setColor(Color.BLACK);
			g.fillOval(loc.x,loc.y,5,5);
		}
		
		
		if(base.pathSlope.stepCount>0 && base.pathUpdater==true)
		{			
			base.px1=base.gliderCenterLoc.floatX;
			base.py1=base.gliderCenterLoc.floatY;											
			g.setColor(Color.BLACK);			
			g.drawLine(base.candidateLoc.getXLoc(),base.candidateLoc.getYLoc(),(int)base.px1,(int)base.py1);	
		}
		

		if(base.drawForegroundFig==true)
		{
			Location startPoint=new Location(0,0,base);
			for(int i=0;i<base.foregroundFigureList.size();i++)
			{
				if(base.pathList.size()>0)
					startPoint=base.pathList.get(0);
				
				for(int j=0;j<base.foregroundFigureList.get(i).lineList.size();j++)
				{
					int x1=(int)(startPoint.getXLoc()+base.foregroundFigureList.get(i).lineList.get(j).x1);
					int y1=(int)(startPoint.getYLoc()+base.foregroundFigureList.get(i).lineList.get(j).y1);
					int x2=x1+(int)(base.foregroundFigureList.get(i).lineList.get(j).length*Math.sin(base.foregroundFigureList.get(i).lineList.get(j).angle));
					int y2=y1+(int)(base.foregroundFigureList.get(i).lineList.get(j).length*Math.cos(base.foregroundFigureList.get(i).lineList.get(j).angle));
					
					g.setColor(base.foregroundFigureList.get(i).lineList.get(j).color);
					g.drawLine(x1, y1, x2, y2);
/*					g.setColor(Color.RED);
					g.fillOval(x1-2,y1-2,4,4);
					
					g.setColor(Color.GREEN);
					g.fillOval(x2-2,y2-2,4,4);*/
					
					g.setColor(Color.BLACK);
				}
			}
		}
		
		if(base.colorizeCR)
		{
			for(int i=100;i<600;i+=4)
			{
				for(int j=100;j<600;j+=4)
				{
					g.setColor(coordinateMat[i][j].color);
					g.fillOval(j,i,3,3);
				}
			} 
		}
		
		
		
		Location oldLoc=null;
		for(int i=0;i<base.pathList.size();i++)
		{			
			Location loc=base.pathList.get(i);
			if(oldLoc!=null && oldLoc.flag)
			g.drawLine(oldLoc.getXLoc(),oldLoc.getYLoc(),loc.getXLoc(),loc.getYLoc());
			g.setColor(Color.BLACK);
			g.drawOval(loc.getXLoc()-3,loc.getYLoc()-3,6,6);
			oldLoc=new Location(loc.getXLoc(),loc.getYLoc(),base);
			oldLoc.flag=loc.flag;
		}
		
		
		for(int i=0;i<base.deploymentLocList.size();i++)
		{
			Location loc=base.deploymentLocList.get(i);
			g.setColor(Color.RED);
			//g.fillOval(loc.getXLoc()-2,loc.getYLoc()-2,5,5);
			double temp=(base.deploymentError/100)*base.droppingHeight;
			temp=temp*base.scale;
			//System.out.println(base.scale);
			//System.out.println("dropping height :"+base.droppingHeight);
			//System.out.println("deployment error :"+base.deploymentError);
			//System.out.println("temp"+temp);
			//g.drawOval(loc.getXLoc()-(int)(temp),loc.getYLoc()-(int)(temp),(int)temp*2,(int)temp*2);
		}
		
		
		
		
		
		if(base.pathFreezed==true && base.virtualPathShowable)
		{
			g.setColor(new Color(100,100,100));
			//base.alert.display(""+(int)base.px1+","+(int)base.py1);
			g.drawLine(base.candidateLoc.x,base.candidateLoc.y,(int)base.px1,(int)base.py1);
			g.drawLine(base.candidateLoc.x,base.candidateLoc.y+1,(int)base.px1,(int)base.py1+1);
		}
		
		
		//********DRAWING NODE TRACK LIST FOR AADS*************************************
	    for(int i=0;i<base.nodeTrackList.size();i++)
	    {
	    	try
	    	{
	    		VCoordinate tempCoordinate=base.nodeTrackList.get(i);
	    		g.setColor(tempCoordinate.color);
	    	//	g.setColor(base.RED);
	    	//	System.out.println("x: "+tempCoordinate.x+" y: "+tempCoordinate.y);
	    		if(base.pathTurningPointsShowable && tempCoordinate.shape==1 && base.virtualPathUpdateEnabled)
	    		{
	    			//g.fillOval(tempCoordinate.x-2, tempCoordinate.y-2,5,5);
	    		}
	    		else
	    		{
	    			g.setColor(base.RED);
	    			g.fillRect(tempCoordinate.x-1,tempCoordinate.y-1,1,1);
	    		}
	    	}catch(Exception e){e.printStackTrace();}
	    	
	    }
		//*****************************************************************************
	    try{
		g.setColor(base.GREEN);
		g.fillRect(base.furthestPointofCircle.x-3,base.furthestPointofCircle.y-3,6,6);
	    }catch(Exception e){}
	    //*******PLOTTING THE TURNING CENTER*******************************************
	    
	    if(base.turningCenter!=null)
	    {
	    	Color color=base.turningCenter.color;
	    	int x=base.turningCenter.x;
	    	int y=base.turningCenter.y;
	    	g.setColor(color);
	    	g.fillRect(x, y,3,3);
	    }
	    
	    //*****************************************************************************
	    
	    if(base.candidateLoc!=null)
	    {
	    	g.setColor(base.OFF_WHITE);
	    	g.drawLine(0,base.candidateLoc.y,1000,base.candidateLoc.y);
	    	g.drawLine(base.candidateLoc.x,0,base.candidateLoc.x,1000);
	    	
	    	/*g.drawLine(base.masterBSList.get(0).getX(),base.masterBSList.get(0).getY(),base.candidateLoc.x,base.candidateLoc.y);
	    	g.drawLine(base.masterBSList.get(1).getX(),base.masterBSList.get(1).getY(),base.candidateLoc.x,base.candidateLoc.y);
	    	g.drawLine(base.masterBSList.get(2).getX(),base.masterBSList.get(2).getY(),base.candidateLoc.x,base.candidateLoc.y);
	    	g.drawLine(base.masterBSList.get(3).getX(),base.masterBSList.get(3).getY(),base.candidateLoc.x,base.candidateLoc.y);*/
	    	
	    	g.setColor(Color.GRAY);
	    	if(base.masterBSList.size()>3)
	    	{
	    		g.drawOval(base.masterBSList.get(0).getX()-(int)(base.candidateLoc.distFromBSList.get(0)*1),base.masterBSList.get(0).getY()-(int)(base.candidateLoc.distFromBSList.get(0)*1),(int)(base.candidateLoc.distFromBSList.get(0)*2),(int)(base.candidateLoc.distFromBSList.get(0)*2));
	    		g.drawOval(base.masterBSList.get(1).getX()-(int)(base.candidateLoc.distFromBSList.get(1)*1),base.masterBSList.get(1).getY()-(int)(base.candidateLoc.distFromBSList.get(1)*1),(int)(base.candidateLoc.distFromBSList.get(1)*2),(int)(base.candidateLoc.distFromBSList.get(1)*2));
	    		g.drawOval(base.masterBSList.get(2).getX()-(int)(base.candidateLoc.distFromBSList.get(2)*1),base.masterBSList.get(2).getY()-(int)(base.candidateLoc.distFromBSList.get(2)*1),(int)(base.candidateLoc.distFromBSList.get(2)*2),(int)(base.candidateLoc.distFromBSList.get(2)*2));
	    		g.drawOval(base.masterBSList.get(3).getX()-(int)(base.candidateLoc.distFromBSList.get(3)*1),base.masterBSList.get(3).getY()-(int)(base.candidateLoc.distFromBSList.get(3)*1),(int)(base.candidateLoc.distFromBSList.get(3)*2),(int)(base.candidateLoc.distFromBSList.get(3)*2));
	    	}
	    	
	    	g.setColor(Color.BLACK);
	    	g.fillOval(base.candidateLoc.x-2,base.candidateLoc.y-2,5,5);
	    	
	    	if(base.coordinateShowable)
	    	{
	    		g.setColor(Color.BLACK);
	    		String tempSt2="("+base.candidateLoc.x+" , "+base.candidateLoc.y+" )";
	    		g.drawString(tempSt2,(int)(base.candidateLoc.x)+30,(int)(base.candidateLoc.y)-30);
	    	}
	    }
	    

	    
	    if(base.gads)
	    {
	    	//base.gliderNoseLoc.x=base.dcp.gliderChesis.
	    	
	    	try
	    	{	    			    		
				VFigure foreFigure=base.foregroundFigureList.get(0);
				ArrayList<LineSegment>lineList=foreFigure.lineList;	
				LineSegment gliderChesis0=lineList.get(0);
				
	    		int x1=(int)gliderChesis0.x1;
	    		int y1=(int)gliderChesis0.y1;
	    		int x2=x1+(int)(gliderChesis0.length*Math.sin(gliderChesis0.angle));
	    		int y2=y1+(int)(gliderChesis0.length*Math.cos(gliderChesis0.angle));
	    		
	    		int x3=x1+(int)(2*Math.sin(gliderChesis0.angle));
	    		int y3=y1+(int)(2*Math.cos(gliderChesis0.angle));
	    		
	    		base.gliderNoseLoc.x=x2;
	    		base.gliderNoseLoc.y=y2;

	    		base.gliderAdvLoc.x=x3;
	    		base.gliderAdvLoc.y=y3;

	    	}
	    	catch(Exception e){ e.printStackTrace();}

	    	
			vToolBox.setSlope(base.gliderCenterLoc,base.gliderNoseLoc,base.glideSlope);
			g.setColor(Color.BLACK);
			if(base.coordinateShowable)
			{
				String tempSt="("+(-100+(int)base.gliderCenterLoc.floatX)+" , "+(-100+(int)base.gliderCenterLoc.floatY)+" )";
				g.drawString(tempSt,(int)(base.gliderCenterLoc.floatX)-30,(int)(base.gliderCenterLoc.floatY)-30);
			
				String tempSt2="("+(base.startPoint.x-100)+" , "+(base.startPoint.y-100)+" )";
				g.drawString(tempSt2,(int)(base.startPoint.x)-10,(int)(base.startPoint.y)-10);
			}
						
			try
			{
				g.setColor(Color.ORANGE);
				g.fillOval(base.oldMark.x-2,base.oldMark.y-2,6,6);
			}
			catch(Exception e){}
			
			try
			{
				g.setColor(Color.MAGENTA);
				g.fillOval(base.newMark.x-2,base.newMark.y-2,6,6);
			}
			catch(Exception e){}
			
			try
			{
				g.setColor(Color.ORANGE);
				g.fillOval(base.oldCenter.x-2,base.oldCenter.y-2,5,5);
			}
			catch(Exception e){}
			
			try
			{
				g.setColor(Color.MAGENTA);
				g.fillOval(base.newCenter.x-2,base.newCenter.y-2,5,5);
			}
			catch(Exception e){}
			
	    }
	    
	    
	    
		repaint();
		revalidate();
	}
	
	//************************************************************************************
	
	//**************************FUNCTIONS*************************************************
	
	public void nodeDeployer(int type,ArrayList<Location>nodeLocList,ArrayList<VLabel> nodeList)
	{
		
		//System.out.println("STARTING  THE NODE DEPLOYER");
		typ=type;
		//base.nodeCount=count;
		//base.nodeArray=nodeArray;		
		base.masterNodeList=nodeList;
		//for(int i=0;i<nodeLocList.size();i++)
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			base.masterNodeList.get(i).addMouseListener(this);
			base.masterNodeList.get(i).uniqueId=i;       // setting i as unique id of the node.
			base.masterNodeList.get(i).commCounter=i;
			base.masterNodeList.get(i).setToolTipText(Integer.toString(i));
			base.masterNodeList.get(i).xPos=nodeLocList.get(i).getXLoc();
			base.masterNodeList.get(i).yPos=nodeLocList.get(i).getYLoc();//????????????????????????????to be implemented for fixed
			//base.nodeArray[i].xPos=base.nodeArray[i].getX()-(base.nodeArray[i].getWidth()/2);
			//base.nodeArray[i].yPos=base.nodeArray[i].getY()-(base.nodeArray[i].getHeight()/2);
			//base.nodeArray[i].setScanningCoordinates();
			base.masterNodeList.get(i).setBounds(nodeLocList.get(i).getXLoc(),nodeLocList.get(i).getYLoc(),base.nodeSize,base.nodeSize);
			
			JLabel label=new JLabel(Integer.toString(i));
			label.setBounds(1,4,17,10);
			label.setForeground(Color.white);
			label.setFont(new Font("Arial Narrow",Font.PLAIN,10));
			base.masterNodeList.get(i).add(label);
			
			
			add(base.masterNodeList.get(i));
		}
		
		base.centerPanel.repaint();
		base.centerPanel.revalidate();
		
		//-----------------------------------------------------------------------
		
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			int c=0;
			for(int j=0;j<base.masterNodeList.size();j++)
			{	
				x7=base.masterNodeList.get(i).xPos;
				y7=base.masterNodeList.get(i).yPos;
				x8=base.masterNodeList.get(j).xPos;
				y8=base.masterNodeList.get(j).yPos;
				
				if(i!=j && base.masterNodeList.get(i).commRange>vToolBox.getDist(x7, y7, x8, y8))
				{
					base.masterNodeList.get(i).connectionList.add(true);
					base.masterNodeList.get(i).neighborList.add(base.masterNodeList.get(j));
					
					//System.out.println(c+"          "+vToolBox.getDist(x7, y7, x8, y8)+"  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+base.nodeArray [i].connectionList.get(c));
					c++;
				}
			}
			//System.out.println("-----------------------------------------------------");
		}
		//--------------------------------------------------------------------------
		
		
		
		base.centerPanel.repaint();
		base.centerPanel.revalidate();
	}
	
	
	public void nodeDeployer(int type,int count,int xArray[],int yArray[],ArrayList<VLabel> nodeList)
	{
		
		//System.out.println("STARTING  THE NODE DEPLOYER");
		typ=type;
		//base.nodeCount=count;
		//base.nodeArray=nodeArray;		
		base.masterNodeList=nodeList;
		for(int i=0;i<count;i++)
		{
			base.masterNodeList.get(i).addMouseListener(this);
			base.masterNodeList.get(i).uniqueId=i;       // setting i as unique id of the node.
			base.masterNodeList.get(i).commCounter=i;
			base.masterNodeList.get(i).setToolTipText(Integer.toString(i));
			base.masterNodeList.get(i).xPos=xArray[i]+100;
			base.masterNodeList.get(i).yPos=yArray[i]+100;//????????????????????????????to be implemented for fixed
			//base.nodeArray[i].xPos=base.nodeArray[i].getX()-(base.nodeArray[i].getWidth()/2);
			//base.nodeArray[i].yPos=base.nodeArray[i].getY()-(base.nodeArray[i].getHeight()/2);
			//base.nodeArray[i].setScanningCoordinates();
			base.masterNodeList.get(i).setBounds(xArray[i]+100,yArray[i]+100,base.nodeSize,base.nodeSize);
			
			JLabel label=new JLabel(Integer.toString(i));
			label.setBounds(1,4,17,10);
			label.setForeground(Color.white);
			label.setFont(new Font("Arial Narrow",Font.PLAIN,10));
			base.masterNodeList.get(i).add(label);
			
			
			add(base.masterNodeList.get(i));
		}
		
		base.centerPanel.repaint();
		base.centerPanel.revalidate();
		
		//-----------------------------------------------------------------------
		
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			int c=0;
			for(int j=0;j<base.masterNodeList.size();j++)
			{	
				x7=base.masterNodeList.get(i).xPos;
				y7=base.masterNodeList.get(i).yPos;
				x8=base.masterNodeList.get(j).xPos;
				y8=base.masterNodeList.get(j).yPos;
				
				if(i!=j && base.masterNodeList.get(i).commRange>vToolBox.getDist(x7, y7, x8, y8))
				{
					base.masterNodeList.get(i).connectionList.add(true);
					base.masterNodeList.get(i).neighborList.add(base.masterNodeList.get(j));
					
					//System.out.println(c+"          "+vToolBox.getDist(x7, y7, x8, y8)+"  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+base.nodeArray [i].connectionList.get(c));
					c++;
				}
			}
			//System.out.println("-----------------------------------------------------");
		}
		//--------------------------------------------------------------------------
		
		
		
		base.centerPanel.repaint();
		base.centerPanel.revalidate();
	}
	
	public void actuatorDeployer(int type,int count,int xArray[],int yArray[],ArrayList<Actuator>actuatorList)
	{
		typ=type;
		base.actuatorCount=count;
		base.actuatorList=actuatorList;
		//for(int i=0;i<count;i++)
		for(int i=0;i<count;i++)
		{
			actuatorList.get(i).addMouseListener(this);
			actuatorList.get(i).setBounds(xArray[i]+100,yArray[i]+100,18,18);
			//actuatorArray[i].setBounds(100,100,18,18);
			
			add(actuatorList.get(i));
			
			JLabel label=new JLabel("M"+i);
			label.setBounds(1,4,17,10);
			label.setForeground(Color.white);
			label.setFont(new Font("Verdana",Font.BOLD,7));
			actuatorList.get(i).add(label);
			
		}
	}
	
	public void baseDeployer(int type,int x,int y,VLabel baseStation)
	{
		typ=type;
		int bsNumber=base.masterBSList.size();
		base.masterBSList.add(baseStation);
		baseStation.addMouseListener(this);
		baseStation.setBounds(x, y,18,18);
		add(baseStation);
		JLabel label=new JLabel("B"+bsNumber);
		label.setBounds(1,4,17,10);
		label.setForeground(Color.white);
		label.setFont(new Font("Verdana",Font.BOLD,11));
		baseStation.add(label);		
	}
	
	public void sinkDeployer(int type,int count,int xArray[],int yArray[],VLabel sinkArray[])
	{
		typ=type;
		base.sinkCount=count;
		base.sinkArray=sinkArray;
		for(int i=0;i<count;i++)
		{
			sinkArray[i].addMouseListener(this);
			sinkArray[i].setBounds(xArray[i]+10,yArray[i]+40,12,12);
			add(sinkArray[i]);
		}
	}
	
	
	void spreadCoordinateMat(int startX, int startY, int yWidth,int xWidth)
	{
		for(int i=startY;i<startY+yWidth;i++)
		{
			for(int j=startX;j<startX+xWidth;j++)
			{
				coordinateMat[i][j]=new VCoordinate(base);
				coordinateMat[i][j].x=i;
				coordinateMat[i][j].y=j;
				Location tempLoc=new Location(i,j,base);
				coordinateMat[i][j].location=tempLoc;
			}
		}
	}
	
	void clearCoordinateMat(int y,int x)
	{
		for(int i=0;i<y;i++)
		{
			for(int j=0;j<x;j++)
			{
				coordinateMat[i][j].temperature=0;
				coordinateMat[i][j].humidity=0;
				coordinateMat[i][j].light=0;

			}
		}
	}
	
	void spreadTemperature(int startX, int startY,int yWidth,int xWidth)
	{
		for(int i=startY;i<yWidth;i++)
		{
			for(int j=startX;j<xWidth;j++)
			{
				coordinateMat[i][j].temperature=50;
			}
		}	
	}
	
	void clearAreaOfInterest(int startX, int startY, int y,int x)
	{
		for(int i=100+startY;i<y+100;i++)
		{
			for(int j=100+startX;j<x+100;j++)
			{
				coordinateMat[i][j].areaOfInterest=false;
			}
		}	
	}
	
	void updateAreaOfInterest(int y1,int x1,int y2,int x2)
	{
		int daWidth=Integer.parseInt(base.dxTf.getText());
		int daHeight=Integer.parseInt(base.dyTf.getText());
		base.centerPanel.clearAreaOfInterest(0,0,daWidth,daHeight);
		
		for(int i=y1;i<=y2;i++)
		{
			for(int j=x1;j<x2;j++)
			{
				coordinateMat[i][j].areaOfInterest=true;
			}
		}	
	}
	
	
	
	//*****************FUNCTION TO FORM HEXAGONS*************************
	public int plotHexagon(int startX,int startY,int width,int height,int commRange,int sensingRange)
	{
		int floorSpacing=commRange/2;
		int onFloorNodeSpacing=sensingRange*3;
		return 0;
	}
	
	//-------------------------------------------------------------------
	
	//*********FUNCTION TO GENERATE EVENT AT RANDOM LOCATION AT RANDOM INTERVAL***********
	public void generateRandomEvents(JPanel panel,int maxX,int maxY,int maxInterval)
	{
		 Random randX=new Random();
		 Random randY=new Random();
		 Random randI=new Random();
		 int randomInterval=0;	 
		 while(base.shouldGenerateEvents==true)
		 {
			 try
			 {	
				 int tempX=randX.nextInt(maxX);
				 int tempY=randY.nextInt(maxY);
				 randomInterval=randI.nextInt(maxInterval);
				 
				 Location tempEventLoc=new Location(tempX,tempY,base);
				// eventLocList.add(tempEventLoc);
				 RandomEvent randEvent=new RandomEvent(base,panel,100);
				 randEvent.setIcon(eventIcon);
				 randEvent.setBounds(tempX+100,tempY+100,20,15);
				 panel.add(randEvent);
				 panel.revalidate();
				// System.out.println("X = "+tempX);
				 //System.out.println("Y = "+tempY);
				 //System.out.println("Interval = "+randomInterval);
				 
				 try{Thread.sleep(randomInterval);}catch(Exception e){}
				 
			 }
			 catch(Exception e)
			 {
				 MsgDialog md=new MsgDialog("deployment area not set",null);
				 md.setBounds(300,300,300,150);
				 md.setVisible(true);
				
				 break;
			 }
		 }
		 return;
	}
	//------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------
	
	public void mouseMoved(MouseEvent me)
	{
		
	}
	
	public void mouseDragged(MouseEvent me)
	{
		


	}
	
	//---------------------------------------------------------------------------------------
	public void mouseClicked(MouseEvent me)
	{
		try
		{
			int mouseButton=me.getButton();
			VLabel tempLb=(VLabel)me.getSource();
			if(tempLb.getNodeType()==base.SENSORNODE || tempLb.getNodeType()==base.SINKNODE)
			{
	
				deselectAll();
				tempLb.setBorder(BorderFactory.createLineBorder(Color.RED));
			}
			if(mouseButton==1)
			{
				clipBoardLb=tempLb;
			}
			if(mouseButton==3 && (tempLb.getNodeType()==base.SENSORNODE || tempLb.getNodeType()==base.SINKNODE))
			{
				if(base==null)
				{
					System.exit(0);
				}
				VNodeOptionDialog optionDialog=new VNodeOptionDialog(base,me);
				optionDialog.setBounds(((VLabel)me.getSource()).getX()+15,((VLabel)me.getSource()).getY()+70,100,250);
				optionDialog.setVisible(true);
			}
			
			if(mouseButton==3 && tempLb.getNodeType()==base.BASESTATION)
			{
				VBaseOptionDialog optionWindow=new VBaseOptionDialog(base,me);
				base.baseStationClipBoard=(VLabel)me.getSource();
				//optionWindow.setBounds(((VLabel)me.getSource()).getX()+15,((VLabel)me.getSource()).getY()+70,150,500);
				optionWindow.setBounds(100,100,150,500);
				optionWindow.setVisible(true);
			}
			
		}
		catch(Exception e){}
		
		try
		{
			int mouseButton=me.getButton();
			Actuator tempActuator=(Actuator)me.getSource();
			//if(tempLb.getNodeType()==base.SENSORNODE || tempLb.getNodeType()==base.SINKNODE)
			//{
				//tempLb.setBackground(base.RED);
				deselectAll();
				tempActuator.setBorder(BorderFactory.createLineBorder(Color.RED));
					
			//}
			if(mouseButton==1)
			{
				actuatorClipBoard=tempActuator;
				//base.alert.display(actuatorClipBoard.id+"");
			}
			if(mouseButton==3 )
			{
				//base.alert.display("banaana baaki hai abhi");
				
				VActuatorOptionDialog optionDialog=new VActuatorOptionDialog(base,me);
				optionDialog.setBounds(((Actuator)me.getSource()).getX()+15,((Actuator)me.getSource()).getY()+70,100,250);
				//optionDialog.setBounds(10,10,100,150);
				optionDialog.setVisible(true);
			}
			
		}
		catch(Exception e){}
		
	}
	public void mouseEntered(MouseEvent me)
	{

	}
	
	public void mouseReleased(MouseEvent me)
	{}
	
	public void mousePressed(MouseEvent me)
	{}
	
	public void mouseExited(MouseEvent me)
	{}
	
	public void deselectAll()
	{
		try
		{
			clipBoardLb.setBorder(BorderFactory.createEmptyBorder());
			clipBoardLb=null;
		}
		catch(Exception e){System.out.println("clipboard is empty");}
		
		try
		{
			actuatorClipBoard.setBorder(BorderFactory.createEmptyBorder());
			actuatorClipBoard=null;
		}
		catch(Exception e){System.out.println("clipboard is empty");}
	}
	
	

}
