package home;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;
import java.io.*;

public class BasicToolKitDialog extends JDialog implements ActionListener 
{
	JButton ssnBt,msnBt,bsBt,obstacleBt,hexagonBt,actuatorHexagonBt,unifDistributeBt,actuatorUnifDistributeBt,analysisBt,graph2Bt,calculateCoverageBt,locStatBt,actuatorBt,partitionBt,gadsPhase1Bt,gadsPhase2Bt,gadsPhase3Bt,calcAngleBt, testBt;
	JButton forestFireEventBt,mobileBsBt,mobileSinkBt,sinkBt,eventBt;
	//ImageIcon bsIcon=new ImageIcon("images/bs4.png");
	
	ImageIcon ssnIcon,msnIcon,actuatorIcon,bsIcon,fireIcon,eventIcon;
	
	Base base;
	PropertiesDialog propertiesDialog;
	ActuatorPropertiesDialog actuatorPropertiesDialog;
	BasePropertiesDialog basePropertiesDialog;
	ObstacleDialog obstacleDialog;
	VToolBox vToolBox;
	float escapeRate=0.0f;
	int extraNodeCount;
	ArrayList<Range>rangeList;
	
	//--------------DECLARING VARIABLES FOR DHDS2-----------------------
	Thread thread2;
	//------------------------------------------------------------------
	
	BasicToolKitDialog(Base b)
	{
		super(b,"tool kit");
		base=b;
		//JPanel westPanel=new JPanel();
		vToolBox=new VToolBox(base);
		GridLayout gl=new GridLayout(18,2);
		gl.setVgap(5);
		setLayout(gl);
		//setLayout(null);
		//setBorder(BorderFactory.createTitledBorder("tool kit"));
		//westPanel.setLayout(null);
		
		//------------------INITIALIZING ICONS---------------------------------
		
		ssnIcon=new ImageIcon("images/ssn.png");
		msnIcon=new ImageIcon("images/msn.png");
		actuatorIcon=new ImageIcon("images/actuator.png");
		bsIcon=new ImageIcon("images/bs.png");
		fireIcon=new ImageIcon("images/fire.png");
		eventIcon=new ImageIcon("images/event.png");
		
		//---------------------------------------------------------------------
		
		//-------------------INITIALIZING VARIABLES----------------------------
		propertiesDialog=new PropertiesDialog(base);
		actuatorPropertiesDialog=new ActuatorPropertiesDialog(base);
		basePropertiesDialog=new BasePropertiesDialog(base);
		//---------------------------------------------------------------------
		testBt=new JButton("test");
		testBt.setToolTipText("test");
		testBt.setFont(new Font("arial",Font.BOLD,11));
		testBt.addActionListener(this);
		
		ssnBt=new JButton(ssnIcon);
		ssnBt.setToolTipText("Static Sensor Node");
		ssnBt.setFont(new Font("arial",Font.BOLD,11));
		ssnBt.addActionListener(this);
		
		
		msnBt=new JButton(msnIcon);
		msnBt.setToolTipText("Mobile Sensor Node");
		msnBt.setFont(new Font("arial",Font.BOLD,11));
		msnBt.addActionListener(this);
		
		
		actuatorBt=new JButton(actuatorIcon);
		actuatorBt.setToolTipText("Actuator");
		actuatorBt.setFont(new Font("arial",Font.BOLD,11));
		actuatorBt.addActionListener(this);
		
		
		partitionBt=new JButton("partition");
		partitionBt.setFont(new Font("arial",Font.BOLD,11));
		partitionBt.addActionListener(this);
		
		
		sinkBt=new JButton("sink");
		//sinkBt.setBounds(5,30,50,20);
		sinkBt.addActionListener(this);
		
		bsBt=new JButton(bsIcon);
		bsBt.setToolTipText("Base Station");
		bsBt.setFont(new Font("arial",Font.BOLD,11));
		bsBt.addActionListener(this);
		
		mobileSinkBt=new JButton("mobile sink");
		mobileSinkBt.addActionListener(this);
		
		mobileBsBt=new JButton("mob-base");
		mobileBsBt.addActionListener(this);
		
		forestFireEventBt=new JButton(fireIcon);
		forestFireEventBt.setToolTipText("Fire");
		forestFireEventBt.addActionListener(this);
		
		eventBt=new JButton(eventIcon);
		eventBt.setToolTipText("Event");
		eventBt.addActionListener(this);
		
		obstacleBt=new JButton("obstacle");
		obstacleBt.setFont(new Font("arial",Font.BOLD,11));
		obstacleBt.addActionListener(this);
		
		hexagonBt=new JButton("hexagons");
		hexagonBt.setFont(new Font("arial",Font.BOLD,11));
		hexagonBt.addActionListener(this);
		
		actuatorHexagonBt=new JButton("MMU hexagons");
		actuatorHexagonBt.setFont(new Font("arial",Font.BOLD,11));
		actuatorHexagonBt.addActionListener(this);
		
		unifDistributeBt=new JButton("MSN uniform distribution");
		unifDistributeBt.setFont(new Font("arial",Font.BOLD,11));
		unifDistributeBt.addActionListener(this);
		
		actuatorUnifDistributeBt=new JButton("MMU uniform distribution");
		actuatorUnifDistributeBt.setFont(new Font("arial",Font.BOLD,11));
		actuatorUnifDistributeBt.addActionListener(this);
		
		gadsPhase1Bt=new JButton("GADS-Phase1");
		gadsPhase1Bt.setFont(new Font("arial",Font.BOLD,11));
		gadsPhase1Bt.addActionListener(this);
		
		gadsPhase2Bt=new JButton("GADS-Phase2");
		gadsPhase2Bt.setFont(new Font("arial",Font.BOLD,11));
		gadsPhase2Bt.addActionListener(this);
		
		gadsPhase3Bt=new JButton("GADS-Phase3");
		gadsPhase3Bt.setFont(new Font("arial",Font.BOLD,11));
		gadsPhase3Bt.addActionListener(this);
		
		calcAngleBt=new JButton("calc angle");
		calcAngleBt.setFont(new Font("arial",Font.BOLD,11));
		calcAngleBt.addActionListener(this);
		
		analysisBt=new JButton("analysis");
		analysisBt.setFont(new Font("arial",Font.BOLD,11));
		analysisBt.addActionListener(this);
		
		graph2Bt=new JButton("graph2");
		graph2Bt.setFont(new Font("arial",Font.BOLD,11));
		graph2Bt.addActionListener(this);
		
		calculateCoverageBt=new JButton("calculate coverage");
		calculateCoverageBt.setFont(new Font("arial",Font.BOLD,11));
		calculateCoverageBt.addActionListener(this);
		
		locStatBt=new JButton("location status");
		locStatBt.setFont(new Font("arial",Font.BOLD,11));
		locStatBt.addActionListener(this);
		
				
		add(ssnBt);
		add(msnBt);
		//add(sinkBt);
		add(actuatorBt);
		add(bsBt);
		//add(mobileSinkBt);
		//add(mobileBsBt);
		add(forestFireEventBt);
		add(eventBt);
		add(obstacleBt);
		add(hexagonBt);
		add(actuatorHexagonBt);
		add(unifDistributeBt);
		add(actuatorUnifDistributeBt);
		add(gadsPhase1Bt);
		add(gadsPhase2Bt);
		add(gadsPhase3Bt);
		add(calcAngleBt);
		add(partitionBt);
		add(analysisBt);
		add(graph2Bt);
		add(calculateCoverageBt);
		add(locStatBt);
		add(testBt);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==locStatBt)
		{
			for(int i=0;i<base.rangeListForNodes.size();i++)
			{
				System.out.println("RANGE : "+i);
				System.out.println("============================================");
				for(int j=0;j<base.rangeListForNodes.get(i).desiredLocInRangeList.size();j++)
				{
					Location tempLoc=base.rangeListForNodes.get(i).desiredLocInRangeList.get(j);
					System.out.println(tempLoc.getXLoc()+","+tempLoc.getYLoc()+"------>>"+tempLoc.occupied+ "  by :"+tempLoc.node.uniqueId);
				}
								
			}
			System.exit(0);
		}
		
		if(ae.getSource()==msnBt)
		{
			Runnable r=new Runnable()
			{
				public void run()
				{
					//System.out.println("node button have been pressed");					
					propertiesDialog.setBounds(200,200,300,400);
					propertiesDialog.setVisible(true);
				}
			};
			new Thread(r).start();
			
			
		}
		
		if(ae.getSource()==actuatorBt)
		{
			Runnable r=new Runnable()
			{
				public void run()
				{
					//System.out.println("node button have been pressed");					
					actuatorPropertiesDialog.setBounds(200,200,300,400);
					actuatorPropertiesDialog.setVisible(true);
				}
			};
			new Thread(r).start();
			
		}
		
		if(ae.getSource()==partitionBt)
		{
			PartitionDialog pd=new PartitionDialog(base);
			pd.setBounds(150,200,300,200);
			pd.setVisible(true);
			/**/
		}
		
		if(ae.getSource()==sinkBt)
		{
			Runnable r=new Runnable()
			{
				public void run()
				{
					//System.out.println("sink button have been pressed");
					SinkPropertiesDialog pd=new SinkPropertiesDialog(base);
					pd.setBounds(200,200,300,400);
					pd.setVisible(true);
				}
			};
			new Thread(r).start();
		}
		
		if(ae.getSource()==bsBt)
		{
			Runnable r=new Runnable()
			{
				public void run()
				{
					//System.out.println("base button have been pressed");					
					basePropertiesDialog.setBounds(200,200,300,400);
					basePropertiesDialog.setVisible(true);
					
					
					//try{Thread.sleep(1000);}catch(Exception e){}
					//vToolBox.generateHexagons(40);
					//base.plotHexagon=true;
					
					
				}
			};
			new Thread(r).start();
			
		}
			
		if(ae.getSource()==forestFireEventBt)
		{
			Runnable r=new Runnable()
			{
				public void run()
				{
					base.centerPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
					base.exLb.setForeground(Color.RED);
					base.eyLb.setForeground(Color.RED);
					base.eventFlag=true;
				}
			};
			new Thread(r).start();
		}
		
		if(ae.getSource()==eventBt)
		{
			Runnable r=new Runnable()
			{
				public void run()
				{
					VEventDialog ved=new VEventDialog(base);
					ved.setBounds(200,200,300,400);
					ved.setVisible(true);
				}
			};
			new Thread(r).start();
		}
		
		
		if(ae.getSource()==analysisBt)
		{
			
			//------------------READING THE SAVED READINGS FROM THE DATA BASE-----------------------------
			
			supplyDataToGraph("select nos,coverage from nostocoveragebyothers where technique='floor' and rc=60 and rs=60 ",1,Color.red);
			//supplyDataToGraph("select nos,coverage from nostocoveragebyothers where technique='floor' and rc=40 and rs=60 ",2,Color.red);
			supplyDataToGraph("select nos,coverage from nostocoveragebyothers where technique='cpvf' and rc=60 and rs=60 ",3,Color.GRAY);
			//supplyDataToGraph("select nos,coverage from nostocoveragebyothers where technique='cpvf' and rc=40 and rs=60 ",4,Color.GRAY);
			//supplyDataToGraph("select nos,coverage from proposedModel where technique='hexagon' and rc=70",5,Color.BLACK); MMMM
			supplyDataToGraph("select nos,avg(coverage)'coverage' from proposedModel where technique='hexagon' and rc=70 group by nos",5,Color.BLACK);
			supplyDataToGraph("select nos,avg(coverage)'coverage' from proposedModel where technique='hexagon' and rc=61 group by nos",6,Color.BLACK);
			
			//********************************************************************************************
			
			//--------------------------------------------------------------------------------------------
			
			
			System.out.println("analysis button have been pressed");
		/*	int a[]={0,100,200,300,400,500};
			int b[]={0,100,200,300,400,500};
			base.graphPanel.canMakeGraph=true;
			base.graphPanel.plotGraph(a,b,a.length,Color.red);*/
			
			base.remove(base.centerPanel);

			base.graphPanel.add(base.graphPanel.minBt);
			base.graphPanel.add(base.graphPanel.closeBt);
			base.graphPanel.add(base.graphPanel.popOutBt);
			
			base.add("Center",base.graphPanel);
			base.graphPanelBt[0]=new JButton("graph 1 ^");
			base.graphPanelBt[0].setBackground(Color.GRAY);
			base.graphPanelBt[0].addActionListener(base);
			base.upperSouthPanel.add(base.graphPanelBt[0]);
			//--------------------------------------------------------------
			/*base.graphPanel2.add(base.graphPanel2.minBt);
			base.graphPanel2.add(base.graphPanel2.closeBt);
			base.graphPanel2.add(base.graphPanel2.popOutBt);
			base.graphPanelBt[1]=new JButton("graph 2 ^");
			base.graphPanelBt[1].setBackground(Color.GRAY);
			base.graphPanelBt[1].addActionListener(base);
			base.upperSouthPanel.add(base.graphPanelBt[1]);*/
			//--------------------------------------------------------------
			base.upperSouthPanel.revalidate();
		}
		
		
		if(ae.getSource()==graph2Bt)
		{
			//------------------READING THE SAVED READINGS FROM THE DATA BASE-----------------------------
			
			supplyDataToGraph2("select nos,avgmov from nostoavgmovbyothers where technique='floor'",1,Color.red);
			//supplyDataToGraph2("select nos,coverage from nostocoveragebyothers where technique='floor' and rc=40 and rs=60 ",2,Color.red);
			supplyDataToGraph2("select nos,avgmov from nostoavgmovbyothers where technique='cpvf'",3,Color.red);
			//supplyDataToGraph2("select nos,coverage from nostocoveragebyothers where technique='cpvf' and rc=60 and rs=60 ",3,Color.GRAY);
			//supplyDataToGraph2("select nos,coverage from nostocoveragebyothers where technique='cpvf' and rc=40 and rs=60 ",4,Color.GRAY);
			//supplyDataToGraph2("select nos,coverage from proposedModel where technique='hexagon'",5,Color.BLACK);
			//supplyDataToGraph2("select nos,averagemov from proposedmodel where rc=61",7,Color.red);//MMMMMMMMMMM
			supplyDataToGraph2("select nos,avg(averagemov)'averagemov' from proposedmodel where rc=61 group by nos",7,Color.red);
			
			supplyDataToGraph2("select nos,avg(averagemov)'avgmov' from proposedmodel where rc=70 group by nos",5,Color.red);
			//supplyDataToGraph2("select nos,averagemov from proposedmodel where rc=61",7,Color.red);
			
			//********************************************************************************************
			Graph2 g2=new Graph2(base);
			g2.setSize(1100,800);
			g2.setVisible(true);
		}
		
		if(ae.getSource()==obstacleBt)
		{
			System.out.println("obstacle button have been pressed");
			obstacleDialog=new ObstacleDialog(base);
			obstacleDialog.setBounds(150,200,150,200);
			obstacleDialog.setVisible(true);
		}
		
		if(ae.getSource()==hexagonBt)
		{
			if(base.sensingRangePerNode>1)
			{
				int rs=(int)(base.sensingRangePerNode);
				if(base.dAShapeCb.getSelectedItem()=="square")
				{
					vToolBox.generateHexagonsForSN(new Location(110,110,base),rs);
				}
				
				if(base.dAShapeCb.getSelectedItem()=="circle")
				{
					vToolBox.generateHexagonsForSN(new Location(base.dxCenter,base.dyCenter,base),rs);
				}
			}
			else
			{
				base.alert.display("sensing range not specified");
			}
		}
		
		if(ae.getSource()==actuatorHexagonBt)
		{
			vToolBox.generateHexagonsForActuators();
		}
		
		if(ae.getSource()==actuatorUnifDistributeBt)
		{
			uniformDistributeMMU();
		}
		
		if(ae.getSource()==gadsPhase3Bt)
		{
/*			getNeighborLocList();
			
			for(int i=0;i<base.masterNodeList.size();i++)
			{
				vToolBox.updateNeighboringLocList(base.masterNodeList.get(i));
				if(base.masterNodeList.get(i).neighbouringLocList.size()>0)
				{
					base.masterNodeList.get(i).setBackground(Color.BLACK);
					for(int k=0;k<base.masterNodeList.get(i).neighbouringLocList.size();k++)
					{
						Location loc=base.masterNodeList.get(i).neighbouringLocList.get(k);
						if(!vToolBox.findLoc(base.remainingLocList, loc))
						{
							base.remainingLocList.add(loc);
						}
					}
					
				}
			}
			System.out.println("REMAINING LOC LIST"+base.remainingLocList.size());
			//System.exit(0);
			
			for(int i=0;i<base.masterBSList.get(0).packetQueue.size();i++)
			{
				Packet packet=base.masterBSList.get(0).packetQueue.get(i);
				base.unplacedNodeList.add(packet.source);
			}
			
			System.out.println("unplaced node list "+base.unplacedNodeList.size());
			
			ArrayList<Loc_Node> locNodeList=base.dhds2.getLocNodeTable((ArrayList)base.unplacedNodeList.clone(),base.remainingLocList);
			System.out.println("loc_nodeList:  "+locNodeList.size());
			
						
			for(int i=0;i<locNodeList.size();i++)
			{
				VLabel node=locNodeList.get(i).getNode();
				Location loc=locNodeList.get(i).getLoc();
				base.placedNodeList.add(node);
				base.placedNodesCount=base.placedNodesCount+1;
				float dist=vToolBox.getDist(node, loc);
				base.totalMovement=base.totalMovement+(dist/base.scale);
				vToolBox.moveNode(node,node.getLoc(),loc,5);
			}
			
			vToolBox.refreshConnections();*/
									
		}
		
		
		if(ae.getSource()==calcAngleBt)
		{
			System.out.println("angle calculation in progress");
			for(int i=0;i<base.desiredLocList.size();i++)
			{
				Location tempLoc=base.desiredLocList.get(i);
				tempLoc.printLoc();
			}
			Location a=base.desiredLocList.get(0);
			Location b=base.desiredLocList.get(1);
			Location c=base.desiredLocList.get(2);
			
			double s1=(double)(a.y-b.y)/(double)(a.x-b.x);
			System.out.println(s1);
			
			double s2=(double)(b.y-c.y)/(double)(b.x-c.x);
			System.out.println(s2);
			
			double a1=Math.toDegrees(Math.atan(s1));
			System.out.println(a1);
			
			double a2=Math.toDegrees(Math.atan(s2));
			System.out.println(a2);
			
			double angle;
			
			if(a1<0 && a2<0)
			{
				angle=Math.abs(a1-a2);
			}
			else if(a1>0 && a2>0)
			{
				angle=Math.abs(a1-a2);
			}
			else
			{
				angle=180-(Math.abs(a1)+Math.abs(a2));
			}
			
			System.out.println("angle  : "+angle);
			
		}		
		if(ae.getSource()==unifDistributeBt)
		{
			
			
			
			//------TWO CASES CAN BE THERE-------------------------------------------
			extraNodeCount=base.masterNodeList.size()-base.desiredLocList.size();
			if(base.masterNodeList.size()>base.desiredLocList.size())
			{
				escapeRate=(((float)base.masterNodeList.size()-base.desiredLocList.size())/(float)base.masterNodeList.size());
				System.out.println(escapeRate);
				base.alert.display(extraNodeCount+"--------"+escapeRate);
			}
			distributeUniformly(base.rangeListForNodes,base.SENSORNODE);
			//??????????????????????????????????????????????????????????????????????????????????????????
			//else
			//{		//	}
			
			
			
			
			
			
			
			
			//base.baseStationArray[0].activeNodeList=(ArrayList<VLabel>)base.baseStationArray[0].nodesConnectingBaseList.clone();
			//extraNodeCount=base.nodeCount-base.desiredLocList.size();
			//if(base.nodeCount>base.desiredLocList.size())
			//{
			//	escapeRate=(((float)base.nodeCount-base.desiredLocList.size())/(float)base.nodeCount);
				//System.out.println(escapeRate);
				//base.alert.display(escapeRate+"");
			//}
			
			/*Runnable r=new Runnable()
			{
				public void run()
				{
					int startr1=0;
					int endr1=0;
					int startr2=0;
					int endr2=0;
					String dualRange="";
					

					
					for(int l=0;l<10;l++)
					{
						ArrayList<Loc_Node>locNodeList=new ArrayList<Loc_Node>();
						startr1=startr1;
						endr1=startr1+70;
						//endr1=startr1+35;
						startr2=endr1;
						endr2=startr2+70;
						//endr2=startr2+35;
						//dualRange=startr1+","+endr1+","+startr2+","+endr2;
						dualRange=0+","+endr1+","+0+","+endr2;
						
						System.out.println("distributing uniformly");
						if(base.baseStationArray[0].activeNodeList.size()>0)
						{
							Location tempLoc=new Location(base.baseStationArray[0].getX(),base.baseStationArray[0].getY(),base);

							ArrayList<Location> points=new ArrayList<Location>();
							int pointListSize=points.size();
				
								points=(ArrayList)vToolBox.getRequiredPoints(base.availDesiredLocList,base.baseStationArray[0].activeNodeList.size());

							
							for(int i=0;i<base.baseStationArray[0].activeNodeList.size() && i< pointListSize;i++)
							{
								VLabel node=base.baseStationArray[0].activeNodeList.get(i);
								node.isConnected=true;
							}
							locNodeList=vToolBox.getLocNodeTable(base.baseStationArray[0].activeNodeList,points,startr1,endr1);
							
							//-------------ADDING BASE STATION TO LOC NODE LIST------------------------
							Location baseStationLoc=base.baseStationArray[0].getLoc();
							Loc_Node baseStationLocNode=new Loc_Node(baseStationLoc,base.baseStationArray[0],base);
							locNodeList.add(baseStationLocNode);
							//-------------------------------------------------------------------------
							base.vBaseOptionDialog.broadcastToMoveDualRangeSelectedNodes(base.baseStationArray[0],locNodeList,dualRange);
						}
						
						else
						{
							//--------ADDING BASE STATION TO THE LOC NODE LIST-------------------------
							//System.exit(0);
							Location baseStationLoc=base.baseStationArray[0].getLoc();
							Loc_Node baseStationLocNode=new Loc_Node(baseStationLoc,base.baseStationArray[0],base);
							locNodeList.add(baseStationLocNode);
							//--------------------------------------------------------------------------
								Location loc=new Location(base.baseStationArray[0].getX(),base.baseStationArray[0].getY(),base);
								base.vBaseOptionDialog.broadcastToMoveDualRangeSelectedNodes(base.baseStationArray[0],locNodeList,dualRange);
						}
						try
						{
							//System.exit(0);
							Thread.sleep(20000);
						}catch(Exception e){}
						
						startr1=endr1;
						base.baseStationArray[0].activeNodeList.removeAll(base.baseStationArray[0].activeNodeList);
						//base.baseStationArray[0].activeList=vToolBox.getNodesInRange(startr1,startr1+70);
						base.baseStationArray[0].activeNodeList=vToolBox.getNodesInRange(0,startr1+70);
						//base.baseStationArray[0].activeList=vToolBox.getNodesInRange(0,startr1+35);
						
						//**********************************************************************************************
						//REMOVING THE EXTRA NODES FROM ACTIVE LIST IF NUMBER OF NODES IS GREATER THEN DESIRED LOCATIONS
						
						if(base.spareList.size()<extraNodeCount)
						{
							int count=(int)Math.ceil(base.baseStationArray[0].activeNodeList.size()*escapeRate);
							base.alert.display(count+"");
							for(int i=0;i<count && base.spareList.size()<extraNodeCount ;i++)
							{
								base.baseStationArray[0].activeNodeList.get(0).isPlaced=true;
								base.baseStationArray[0].activeNodeList.get(0).defaultColor=Color.ORANGE;
								base.baseStationArray[0].activeNodeList.get(0).setBackground(Color.ORANGE);
								base.spareList.add(base.baseStationArray[0].activeNodeList.get(0));
								base.baseStationArray[0].activeNodeList.remove(0);
							}
						}
						
						if(base.baseStationArray[0].activeNodeList.size()>base.availDesiredLocList.size())
						{
							while(base.baseStationArray[0].activeNodeList.size()>base.availDesiredLocList.size())
							{
								base.baseStationArray[0].activeNodeList.get(0).isPlaced=true;
								base.baseStationArray[0].activeNodeList.get(0).defaultColor=Color.ORANGE;
								base.baseStationArray[0].activeNodeList.get(0).setBackground(Color.ORANGE);
								base.spareList.add(base.baseStationArray[0].activeNodeList.get(0));
								base.baseStationArray[0].activeNodeList.remove(0);
							}
						}
						
						vToolBox.setIsPlacedVariableTrue(base.baseStationArray[0].activeNodeList);
						
						
					}
		}
	};new Thread(r).start();*/
			
		}
		
/*		if(ae.getSource()==gadsPhase1Bt)
		{	
				dhds2Phase1();
		}*/
		
	/*	if(ae.getSource()==gadsPhase2Bt)
		{
			Runnable r=new Runnable()
			{
				public void run()
				{
					base.masterBSList.get(0).nodesConnectingBaseList.removeAll(base.masterBSList.get(0).nodesConnectingBaseList);
					vToolBox.broadcast(base.masterBSList.get(0));
					try{Thread.sleep(3000);}catch(Exception e){}
					vToolBox.refreshConnections();
					
					
					System.out.println("scan button has been pressed");
					floodForGradient(base.masterBSList.get(0));
					try{Thread.sleep(10000);}catch(Exception e){}
					
					base.alert.display("unplaced node intimation");
					int counter=1;
					for(int i=0;i<base.masterNodeList.size();i++)
					{
						VLabel thisNode=base.masterNodeList.get(i);
						if(thisNode.isPlaced==false)
						{
						//thisNode.defaultColor=base.GREEN;
							System.out.println("counter"+counter);
							counter=counter+1;
							thisNode.setBackground(Color.GREEN);
							thisNode.defaultColor=Color.GREEN;
							Packet newPacket=new Packet(base);
							newPacket.parentSequence.removeAll(newPacket.parentSequence);
							newPacket.data=base.centerPanel.coordinateMat[thisNode.getY()][thisNode.getX()].temperature;
							newPacket.queryType=base.REPLY;
							newPacket.sourceType=base.SENSORNODE;
							newPacket.source=thisNode;							
							newPacket.destination=base.masterBSList.get(0);
							newPacket.ttl=50;
							newPacket.hopCount=0;
							newPacket.protocolType=base.DIR_DIFFUSION;
							newPacket.pathStack=(Stack)thisNode.gradientStackPriorityQueue.get(0).clone();
							newPacket.packetType=base.UNPLACED_NODE_INFORMER;
					
						
						//if(newPacket.protocolType==base.DIR_DIFFUSION)
						//{
						System.out.println(thisNode.gradientStackPriorityQueue.size());
							//System.exit(0);
							
							if(newPacket.pathStack.size()>0)
							{
								//System.exit(0);
								newPacket.pathStack.pop();
								thisNode.algo.dirDiffusion(thisNode,newPacket);
							}
						//}
						//----------------------------------------------------------
							//try{Thread.sleep(2000);}catch(Exception e){}
							try{Thread.sleep(50);}catch(Exception e){}
						}
						
					}
					
					
				}
			};
			new Thread(r).start();
		}
	*/	
		if(ae.getSource()==calculateCoverageBt)
		{
			System.out.println("coverage area selected");
			for(int i=0;i<base.masterNodeList.size();i++)
			{
				int x=base.masterNodeList.get(i).xPos;
				int y=base.masterNodeList.get(i).yPos;
				int rs=(int)base.sensingRangePerNode;
					//vToolBox.circle(base.xEventOuterList,base.yEventOuterList,x,y,j);
					for(int yLoc=y-rs;yLoc<=y+rs;yLoc++)
					{
						for(int xLoc=x-rs;xLoc<=x+rs;xLoc++)
						{
								double distX=Math.sqrt((x-xLoc)*(x-xLoc));
								double distY=Math.sqrt((y-yLoc)*(y-yLoc));
								double dist=Math.sqrt((distX*distX)+(distY*distY));
								if(dist<=base.sensingRangePerNode)
								{
									try{
									base.xEventOuterList.add(xLoc);
									base.yEventOuterList.add(yLoc);
									base.centerPanel.coordinateMat[yLoc][xLoc].covered=true;
									}catch(Exception e){}
									
								}
						}
					}
				
			}
			
			int coveredCounter=0;
			int uncoveredCounter=0;
			for(int y=100;y<600;y++)
			{
				for(int x=100;x<600;x++)
				{
					if((base.centerPanel.coordinateMat[y][x]).covered)
					{
						coveredCounter=coveredCounter+1;
					}
					else
					{
						uncoveredCounter=uncoveredCounter+1;
					}

				}
			}
			System.out.println(coveredCounter);
			System.out.println(uncoveredCounter);
			int width=Integer.parseInt(base.dxTf.getText());
			int height=Integer.parseInt(base.dyTf.getText());
			double percentageCoverage=((double)coveredCounter/((double)height*(double)width))*100;
			System.out.println("percentage coverage:"+percentageCoverage);
		}
		
		
		if(ae.getSource()==testBt)
		{
			
			base.alert.display("test");
			
			Runnable r=new Runnable()
			{
				public void run()
				{
					for(;;)
					{
						System.out.println(System.currentTimeMillis());
						
						try{
							
							Thread.sleep(1000);
						}catch(Exception e){}
				}
				}
			};
			new Thread(r).start();
			
			
			
		}
	}
	
	//----------------------------------DHDS2 PHASE1----------------------------------------
/*	void dhds2Phase1()
	{
		Runnable r2=new Runnable()
		{
			public void run()
			{
				for(int i=0;i<base.masterNodeList.size();i++)
				{					
						VLabel tempNode=base.masterNodeList.get(i);
						if(tempNode.iAmDeserving)
						{
							tempNode.setBackground(Color.RED);
							base.placedNodesCount=base.placedNodesCount+1;
							float dist=vToolBox.getDist(tempNode.getLoc(),tempNode.nearestDesiredPoint);
							base.totalMovement=base.totalMovement+(dist/base.scale);
							vToolBox.moveNode(tempNode,tempNode.getLoc(),tempNode.nearestDesiredPoint,5);
							tempNode.group=tempNode.nearestDesiredPoint.group;
							tempNode.isPlaced=true;		
							tempNode.iAmDeserving=false;
							tempNode.setBackground(Color.GREEN);
							base.placedNodeList.add(tempNode);
						}
				}
				
				try
				{
					Thread.sleep(2000);
				}catch(Exception e){}
			}
		};
		thread2=new Thread(r2);
		thread2.start();
		
		Runnable r3=new Runnable()
		{
			public void run()
			{
				try
				{
				thread2.join();
				
				
				for(int i=0;i<base.masterNodeList.size();i++)
				{
					if(!base.masterNodeList.get(i).isPlaced)
					{
						VLabel tempNode=base.masterNodeList.get(i);
						tempNode.setBackground(Color.GRAY);
						tempNode.desired_Loc_List.remove(0);
						
					}
						
				}																	
				
				//VLabel node=base.placedNodeList.get(0);
				ArrayList<Location>tempDesiredLocList=(ArrayList<Location>)base.desiredLocList.clone();
				for(int i=0;i<tempDesiredLocList.size();)
				{
					if(base.concurrencyList.size()==0)
					{
						//VLabel node=base.placedNodeList.get(0);
						Location loc=tempDesiredLocList.get(0);
						ArrayList<Location>tempList=new ArrayList<Location>();
						tempList.add(loc);
						base.concurrencyList.add(tempList);
						tempDesiredLocList.remove(0);
					}
					else
					{
						
						Location loc=tempDesiredLocList.get(0);
						tempDesiredLocList.remove(0);
						loc.flag=true;
						for(int j=0;j<base.concurrencyList.size();j++)
						{
							loc.flag=true;
							ArrayList<Location>tempList=base.concurrencyList.get(j);
							for(int k=0;k<tempList.size();k++)
							{
								float dist=vToolBox.getDist(loc,tempList.get(k));
								//if(dist<2*base.commRangePerNode)
								
								//float concurrencyDist=(float)Math.sqrt((66.3)*(66.3)+((base.sensingRangePerNode*2.5))*((base.sensingRangePerNode*2.5)));
								//float concurrencyDist=(float)Math.sqrt((base.tempCommRangePerNode/2)*(base.tempCommRangePerNode/2)+(base.sensingRangePerNode*3)*(base.sensingRangePerNode*3));
								//float concurrencyDist=(float)Math.sqrt((Math.sqrt(3)*base.tempCommRangePerNode/2)*(Math.sqrt(3)*base.tempCommRangePerNode/2)+(base.tempCommRangePerNode*2.5)*(base.tempCommRangePerNode*2.5));
								float concurrencyDist1=(float)(Math.sqrt(3)*base.tempCommRangePerNode/2)*4;								
								//float concurrencyDist=3*base.tempCommRangePerNode;
								float concurrencyDist=(float)((2*base.tempCommRangePerNode*Math.cos(Math.toRadians(20)))+(base.tempCommRangePerNode*Math.cos(Math.toRadians(40))));
								//float concurrencyDist=(float)((140*Math.cos(20))+(70*Math.cos(40)));
								base.alert.display("concurrency dist = " +concurrencyDist);
								System.out.println(base.tempCommRangePerNode);
								System.out.println(concurrencyDist);
								System.out.println(concurrencyDist1);
								//System.exit(0);
								
								if(dist<concurrencyDist+10)
								{
									loc.flag=false;
									break;
								}
							}
							if(loc.flag==true)
							{
								tempList.add(loc);
								break;
							}
							else if(base.concurrencyList.size()==j+1)
								{
									ArrayList<Location>tempList1=new ArrayList<Location>();
									tempList1.add(loc);
									base.concurrencyList.add(tempList1);
									break;
								}
								else
								{
									continue;
								}
						}
					}												
				}
				
				System.out.println(base.concurrencyList.size());
				
				//---------------------------------------------------
				}
				catch(InterruptedException e){}
				
				
				//-------------------UPDATING THE NEIGHBOR LOCATION LIST OF PLACED NODES-----------
				getNeighborLocList();
				//-------------------------------------------------------------------------------
				dhds2Phase2();					
			}
		};
		new Thread(r3).start();
	}*/
	//---------------------------------------------------------------------------------
	
	
	//-----------------------------------DHDS2 PHASE2----------------------------------------
	void dhds2Phase2()
	{
		for(int i=0;i<base.concurrencyList.size();i++)
		//for(int i=0;i<1;i++)
		{						
			ArrayList<VLabel>tempNodeList=new ArrayList<VLabel>();
			ArrayList<Location>tempLocList=base.concurrencyList.get(i);
			//for(int j=0;j<tempLocList.size();j++)
			//{
				//System.out.println(tempLocList.get(j).printLoc());
			System.out.println(tempLocList.size());
				for(int k=0;k<base.masterNodeList.size();k++)
				{
					Packet packet=new Packet(base);
					packet.queryType=base.DHDS2_PHASE2;                  // QUERY TYPE = DHDS2_PHASE2
					packet.data=base.masterBSList.get(0);
					packet.protocolType=base.BROADCAST;
					
					packet.source=base.masterBSList.get(0);
					packet.destination=base.BROADCAST_DEST;
					packet.sourceType=base.BASESTATION;
					packet.ttl=1;
					packet.hopCount=0;
					packet.identifier=7;
					packet.fromNode=base.masterBSList.get(0);
					packet.desired_Loc_List=tempLocList;
					base.masterNodeList.get(k).buffer.add(packet);
				}							
			//}
			//System.out.println(tempLocList.size());
				
				base.alert.display("next phase");
				try{Thread.sleep(2000);}catch(Exception e){}
				//try{Thread.sleep(50);}catch(Exception e){}
		}
		
	}
				
	
	//----------------------------------------------------------------------------------------
	
/*	//-------------------------
	void getNeighborLocList()
	{
		
		for(int i=0;i<base.placedNodeList.size();i++)
		{
			base.placedNodeList.get(i).neighbouringLocList.removeAll(base.placedNodeList.get(i).neighbouringLocList);
			for(int j=0;j<base.desiredLocList.size();j++)
			{
				Location tempLoc=base.desiredLocList.get(j);
				VLabel tempNode=base.placedNodeList.get(i);
				//tempNode.neighbouringLocList.removeAll(tempNode.neighbouringLocList);
				if(vToolBox.getDist(tempNode,tempLoc)<base.commRangePerNode+2)
				{
					tempNode.neighbouringLocList.add(tempLoc);
				}
			}
		}
	}
	*/
	//-------------------------FOOD FOR GRADIENT----------------------------------------------
	
	void floodForGradient(final VLabel node)
	{
		Runnable r=new Runnable()
		{
			public void run()
			{
		
				System.out.println("flooding is selected");
				//System.exit(0);
				Packet packet=new Packet(base);
				packet.identifier=2;
				packet.sourceType=base.BASESTATION;
				packet.source=node;
				//packet.fromNode=node;
				packet.protocolType=base.GOSSIPING;
				packet.queryType=base.GOSSIP;
				packet.ttl=50;
				packet.hopCount=0;
				packet.destination=base.BROADCAST_DEST;
				packet.parentSequence.push(node);
		
				for(int i=0;i<node.nodesConnectingBaseList.size();i++)
				{
					Packet newPacket=(Packet)packet.clone();
					newPacket.parentSequence=(Stack)packet.parentSequence.clone();
					System.out.println("clone  "+i);
					if(node.nodesConnectingBaseList.get(i).buffer.size()<node.nodesConnectingBaseList.get(i).defaultBufferSize)
					{
						node.nodesConnectingBaseList.get(i).buffer.add(newPacket);
						node.nodesConnectingBaseList.get(i).setBackground(base.YELLOW);
						System.out.println(node.nodesConnectingBaseList.get(i).uniqueId);
					}	
				}
			}
		};
		new Thread(r).start();
	}
	
	
	
	//---------------------------------------------------------------------------------------
	
	
	
	
	
	//-------------FUNCTION TO TAKE VALUES FROM DATABASE AND PLOT A GRAPH---------------------
	void supplyDataToGraph(String query,int shape,Color color)
	{
		try
		{
			ResultSet rs=base.joint.getDataByResultSet(query);
			ArrayList<VCoordinate> coordinateList=new ArrayList<VCoordinate>();
			while(rs.next())
			{
				int x=rs.getInt(1);
				int y=rs.getInt(2);
				coordinateList.add(new VCoordinate(x,y,shape,color,base));
			}
	
			base.graphPanel.plotGraph(coordinateList,color,2,4);
			VCoordinate abc[]=coordinateList.toArray(new VCoordinate[coordinateList.size()]);
			System.out.println(abc.length);
			
		}catch(Exception e){e.printStackTrace();}
	}
	
	void supplyDataToGraph2(String query,int shape,Color color)
	{
		try
		{
			ResultSet rs=base.joint.getDataByResultSet(query);
			ArrayList<VCoordinate> coordinateList=new ArrayList<VCoordinate>();
			while(rs.next())
			{
				int x=rs.getInt(1);
				int y=rs.getInt(2);
				System.out.println(x+"------,----------"+y);
				coordinateList.add(new VCoordinate(x,y,shape,color,base));
			}
	
			base.graphPanel2.plotGraph(coordinateList,color,2,0.4f);
			VCoordinate abc[]=coordinateList.toArray(new VCoordinate[coordinateList.size()]);
			System.out.println(abc.length);
			//System.exit(0);
			
		}catch(Exception e){e.printStackTrace();}
	}
	
	//------------FUNCTION FOR UNIFORM DISTRIBUTION OF NODES/ MMUS-----------------
	
public void distributeUniformly(ArrayList<Range>rl, final int nodeType)
//public void distributeUniformly()
{
	rangeList=rl;
	Runnable r=new Runnable()
	{
		public void run()
		{
			String dualRange="";
			int extendedRange=0;//????
			base.masterBSList.get(0).activeNodeList=(ArrayList<VLabel>)base.masterBSList.get(0).nodesConnectingBaseList.clone();
			ArrayList <Location>activeLocList=new ArrayList<Location>();
			ArrayList<Loc_Node>locNodeList=new ArrayList<Loc_Node>();
			//for(int i=0;i<10;)
			float range=0;
			switch(nodeType)
			{
				case 1:  // FOR SENSORNODES
					range=base.commRangePerNode/2;
					break;
					
				case 4:  // FOR ACTUATORS/MMUs
					range=base.commRangePerActuator/2;
				break;		
			}
			
			int loopValue=0;
			try
			{
				int b=Integer.parseInt(base.dxTf.getText());
				int p=Integer.parseInt(base.dyTf.getText());
				float h=(float)Math.sqrt(b*b+p*p);
				loopValue=(int)Math.ceil((h+range)/range);
			}catch(Exception e){base.alert.display("base station not deployed"+e);}
			for(int i=0;i<=loopValue+1;)
			{
				base.alert.display(""+i+"        ex"+extendedRange);
				//dualRange=base.rangeList.get(i).from+","+base.rangeList.get(i).to+","+base.rangeList.get(i+1).from+","+base.rangeList.get(i+1).to;
				try
				{
					dualRange="0,"+rangeList.get(i).to+",0,"+rangeList.get(i+1).to;
				}
				catch(Exception e)
				{
					dualRange="0,"+rangeList.get(9).to+",0,"+rangeList.get(9).to;
				}
				//activeLocList=base.rangeList.get(i).desiredLocInRangeList;
				activeLocList=rangeList.get(i).getUnoccupiedLocInRangeList();
				
				//	----------THREE CASES CAN BE THERE------------------------------------
				if(base.masterBSList.get(0).activeNodeList.size()==0)
				{
					System.out.println(" NO NODE IS IN RANGE "+rangeList.get(i).from+"   to  "+ rangeList.get(i).to);
					if(extendedRange>2)
					{
						dualRange="0,"+rangeList.get(i).to+",0,"+rangeList.get(extendedRange).to;
						base.vBaseOptionDialog.broadcastToMoveDualRangeSelectedNodes(base.masterBSList.get(0),locNodeList,dualRange,nodeType);
					}
				}
				else if(base.masterBSList.get(0).activeNodeList.size()>activeLocList.size())
				{
					System.out.println(" MORE NODE IS IN RANGE "+rangeList.get(i).from+"   to  "+ rangeList.get(i).to);
					//int startr1=base.rangeList.get(i).from;
					int startr1=0;
					int endr1=rangeList.get(i).to;
					//locNodeList.removeAll(locNodeList);
					//locNodeList=vToolBox.getLocNodeTable((ArrayList<VLabel>)base.baseStationArray[0].activeNodeList.clone(),activeLocList,startr1,endr1);
					locNodeList.addAll(vToolBox.getLocNodeTable((ArrayList<VLabel>)base.masterBSList.get(0).activeNodeList.clone(),activeLocList,startr1,endr1));
					//removePlacedNodesFromActiveList(locNodeList);
					
					for(int m=0;m<base.masterBSList.get(0).activeNodeList.size();m++)
					{
						System.out.println("BEFORE   RRRRRRRRRRRRRRRRRRRRR:"+base.masterBSList.get(0).activeNodeList.get(m).uniqueId);
					}
					
					
			
					//------REMOVING PLACED NODES FROM THE ACTIVE NODE LIST------------------
					for(int d=0;d<locNodeList.size();d++)
					{
						for(int c=0;c<base.masterBSList.get(0).activeNodeList.size();c++)
						{
							if(locNodeList.get(d).getNode().uniqueId==base.masterBSList.get(0).activeNodeList.get(c).uniqueId)
							{
								
								base.masterBSList.get(0).activeNodeList.remove(c);
								//break;
							}
						}
					}
					
					//---------------------------------------------------------------------
					
					
					for(int m=0;m<base.masterBSList.get(0).activeNodeList.size();m++)
					{
						System.out.println("AFTER  RRRRRRRRRRRRRRRRRRRRR:"+base.masterBSList.get(0).activeNodeList.get(m).uniqueId);
					}
					
					
					activeLocList.removeAll(activeLocList);
					
					int j=i+1;
					while(base.masterBSList.get(0).activeNodeList.size()>activeLocList.size() && j<10)
					{
						//	activeLocList.addAll(base.rangeList.get(j).desiredLocInRangeList);
						activeLocList.addAll(rangeList.get(j).getUnoccupiedLocInRangeList());
						j=j+1;
					}
					
					ArrayList<Loc_Node> tmpLocNodeList=new ArrayList<Loc_Node>();
					tmpLocNodeList=	vToolBox.getLocNodeTable((ArrayList<VLabel>)base.masterBSList.get(0).activeNodeList.clone(),activeLocList,startr1,endr1);
					locNodeList.addAll(tmpLocNodeList);
					
					
					//********************************************************************
					//BROADCAST THIS LIST TO RANGE I AND I+1;
					//MARK LOCATIONS AS ALLOCATED BEFORE BROADCASTING
					//MARK NODES AS PLACED BEFORE BROADCASTING
					

			
					System.out.println("loc node list size :"+locNodeList.size());
					System.out.println("ACTIVE LOC LIST SIZE:"+activeLocList.size());
					System.out.println("ACTIVE NODE LIST SIZE:"+base.masterBSList.get(0).activeNodeList.size());
					//System.exit(0);
					
					
					System.out.println("VVVVVVVVV"+activeLocList.size());
					//System.exit(0);
				}
				else
				{
					System.out.println(" LESS OR EQUAL NODE IS IN RANGE "+rangeList.get(i).from+"   to  "+ rangeList.get(i).to);
					//int startr1=base.rangeList.get(i).from;
					int startr1=0;
					int endr1=rangeList.get(i).to;
					//locNodeList=vToolBox.getLocNodeTable(base.baseStationArray[0].activeNodeList,activeLocList,startr1,endr1);
					locNodeList.addAll(vToolBox.getLocNodeTable(base.masterBSList.get(0).activeNodeList,activeLocList,startr1,endr1));
					System.out.println("loc node list size :"+locNodeList.size());
					//System.exit(0);
				}
				
				//-----------MARKING LOCATIONS AS OCCUPIED AND NODES AS PLACES------------
				for(int k=0;k<locNodeList.size();k++)
				{
					locNodeList.get(k).getLoc().occupied=true;
					locNodeList.get(k).getLoc().node=locNodeList.get(k).getNode();
					locNodeList.get(k).getNode().isPlaced=true;
					
					locNodeList.get(k).getNode().setBackground(Color.RED);
					locNodeList.get(k).getNode().defaultColor=Color.red;
				}
				//------------------------------------------------------------------------
				
				//-------------ADDING BASE STATION TO LOC NODE LIST------------------------
				Location baseStationLoc=base.masterBSList.get(0).getLoc();
				Loc_Node baseStationLocNode=new Loc_Node(baseStationLoc,base.masterBSList.get(0),base);
				locNodeList.add(baseStationLocNode);
				//-------------------------------------------------------------------------
				
				base.vBaseOptionDialog.broadcastToMoveDualRangeSelectedNodes(base.masterBSList.get(0),locNodeList,dualRange,nodeType);
				
				
				
				try{Thread.sleep(3000);}catch(Exception e){}
				
				if(rangeList.get(i).getUnoccupiedLocInRangeList().size()==0)
				{
					i=i+1;
					extendedRange=i;
				}
				else
				{
					extendedRange=extendedRange+1;
				}
				//locNodeList.removeAll(locNodeList);
				base.masterBSList.get(0).activeNodeList.removeAll(base.masterBSList.get(0).activeNodeList);
				//base.baseStationArray[0].activeNodeList=vToolBox.getNodesInRangeAndIdentified(base.rangeList.get(i).from,base.rangeList.get(i).to);
				try
				{
					base.masterBSList.get(0).activeNodeList=vToolBox.getNodesInRangeAndIdentified(0,rangeList.get(i).to);
				}
				catch(Exception e)
				{
					base.masterBSList.get(0).activeNodeList=vToolBox.getNodesInRangeAndIdentified(0,rangeList.get(9).to);
				}
				
				//--------PRINTING THE NODE LIST TO CHECK THE REDUNDENCY--------------------
				
				/*if(base.spareList.size()<extraNodeCount)
				{
					int count=(int)Math.ceil(base.baseStationArray[0].activeNodeList.size()*escapeRate);
					System.out.println("TTTTTTTTTTTTTTTTTTTTTTT:"+count);
					while(count>0)
					{
						VLabel tempNode=base.baseStationArray[0].activeNodeList.get(0);
						tempNode.isPlaced=true;
						tempNode.setBackground(Color.ORANGE);
						tempNode.defaultColor=Color.ORANGE;
						base.spareList.add(tempNode);
						base.baseStationArray[0].activeNodeList.remove(0);
						count=count-1;
					}
					
				}*/
				
				
				
			}
		}
	};
	new Thread(r).start();
}

	

//----------------FUNCTION FOR UNIFORM DISTRIBUTION OF ACTUATORS-------------------
void uniformDistributeMMU()
{
	for(int i=0;i<base.actuatorDesiredLocList.size();i++)
	{
		int x=base.actuatorDesiredLocList.get(i).getXLoc();
		int y=base.actuatorDesiredLocList.get(i).getYLoc();
		base.actuatorList.get(i).setBounds(x,y,base.actuatorList.get(i).getWidth(),base.actuatorList.get(i).getHeight());
	}
}
//----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------

	
}
