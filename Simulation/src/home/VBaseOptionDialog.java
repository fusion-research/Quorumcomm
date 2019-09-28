package home;
import java .awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.text.Segment;

import java.awt.event.*;
import java.util.Stack;
public class VBaseOptionDialog extends JDialog 
{
	Base base;
	JList optionList;
	static String option1[]={"source node","sink node","preview","create MST","create Path Tree","nodes reachable","check interference","MMU placement","-----","broadcast","broadcast desired loc","inspection","broadcastToMove","broadcastToMoveSelectedNodes","flooding","get_temp","getNeighbors","properties","master pcd","broadcast_for_clustering"};
	static String option2[]={"sink node","preview","create MST","create Path Tree","nodes reachable","check interference","MMU placement","-----","broadcast","broadcast desired loc","inspection","broadcastToMove","broadcastToMoveSelectedNodes","flooding","get_temp","getNeighbors","properties","master pcd","broadcast_for_clustering"};
	static String option3[]={"source node","preview","create MST","create Path Tree","nodes reachable","check interference","MMU placement","-----","broadcast","broadcast desired loc","inspection","broadcastToMove","broadcastToMoveSelectedNodes","flooding","get_temp","getNeighbors","properties","master pcd","broadcast_for_clustering"};
	static String option4[]={"preview","create MST","create Path Tree","nodes reachable","check interference","MMU placement","-----","broadcast","broadcast desired loc","inspection","broadcastToMove","broadcastToMoveSelectedNodes","flooding","get_temp","getNeighbors","properties","master pcd","broadcast_for_clustering"};
	final MouseEvent mouseEvent;
	VToolBox vToolBox;
	Thread pathTreeTh;
	ArrayList <NeighborInfo>distSortedList =new ArrayList<NeighborInfo>();

	VBaseOptionDialog(Base b,final MouseEvent mouseEvent)
	{
		super(b);
		setUndecorated(true);
		//ow=this;
		this.mouseEvent=mouseEvent;
		base=b;
		vToolBox=new VToolBox(base);
		//this.mouseEvent=mouseEvent;
		if(base.sourceNode==false && base.sinkNode==false)
		{
			optionList=new JList(option1);
		}
		if(base.sourceNode==true)
		{
			optionList=new JList(option2);
		}
		if(base.sinkNode==true)
		{
			optionList=new JList(option3);
		}
		if(base.sinkNode==true && base.sourceNode==true)
		{
			optionList=new JList(option4);
		}
		
		optionList.setSelectedIndex(4);
		optionList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		optionList.setBackground(new Color(245,245,245));

		
		optionList.addMouseListener
		(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent me)
					{
						if(((JList)me.getSource()).getSelectedValue()=="properties")
						{
							PropertiesDialog pd=new PropertiesDialog(base,(VLabel)mouseEvent.getSource());
							pd.setBounds(200,200,300,300);
							pd.setVisible(true);
						}
						
						
						
						if(((JList)me.getSource()).getSelectedValue()=="source node")
						{
							((VLabel)mouseEvent.getSource()).sourceNode=true;
							((VLabel)mouseEvent.getSource()).setBackground(base.BROWN);
							((VLabel)mouseEvent.getSource()).defaultColor=base.BROWN;
							base.whoIsSource=((VLabel)mouseEvent.getSource());
							base.sourceNode=true;
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="sink node")
						{
							((VLabel)mouseEvent.getSource()).sinkNode=true;
							((VLabel)mouseEvent.getSource()).setBackground(base.VOILET);
							((VLabel)mouseEvent.getSource()).defaultColor=base.VOILET;
							base.whoIsSink=((VLabel)mouseEvent.getSource());
							base.sinkNode=true;
						}
						
						
						if(((JList)me.getSource()).getSelectedValue()=="preview")
						{
							Preview pr=new Preview(((VLabel)mouseEvent.getSource()),base);
						}
												
						
						
						
						
						if(((JList)me.getSource()).getSelectedValue()=="create MST")
						{
							Runnable r1=new Runnable()
							{
								public void run()
								{							
									int counter=0;
									for(int i=0;i<base.masterNodeList.size();i++)
									{
										for(int j=0;j<base.masterNodeList.get(i).neighborList.size();j++)
										{
											VLabel startNode=base.masterNodeList.get(i);
											VLabel endNode=startNode.neighborList.get(j);
											float dist=vToolBox.getDist(startNode,endNode);												
											distSortedList.add(new NeighborInfo(startNode,endNode,dist));																																								
										}
									}
									System.out.println("COUNTER="+counter);
									System.out.println("SIZE="+distSortedList.size());
									Collections.sort(distSortedList);
									for(int l=0;l<distSortedList.size();l++)
									{
										System.out.println(distSortedList.get(l).startNode.uniqueId+"   to  "+distSortedList.get(l).endNode.uniqueId+"    Dist= "+distSortedList.get(l).dist);
									}	
									
									vToolBox.removeConnections();
									
							//	----------------CODE TO IMPLEMENT KRUSKAL ALGORITHM-----------------------------
									for(int i=0;i<distSortedList.size();i++)
									//for(int i=0;i<=5;i++)
									{
										VLabel startNode=distSortedList.get(i).startNode;
										VLabel endNode=distSortedList.get(i).endNode;
										
										if(i==0)
										{
											startNode.mstTraversed=true;
											endNode.mstTraversed=true;
											System.out.println("START NODE= "+startNode.uniqueId);
											System.out.println("END NODE= "+endNode.uniqueId);
											startNode.mstNeighborList.add(endNode);
											endNode.mstNeighborList.add(startNode);
											startNode.setBackground(Color.CYAN);
											endNode.setBackground(Color.CYAN);
											base.mstConnectionPairList.add(new ConnectionPair(startNode,endNode));
											
										}
										
										if(startNode.mstTraversed==false || endNode.mstTraversed==false)
										{
											startNode.mstTraversed=true;
											endNode.mstTraversed=true;
											System.out.println("START NODE= "+startNode.uniqueId);
											System.out.println("END NODE= "+endNode.uniqueId);
											startNode.mstNeighborList.add(endNode);
											endNode.mstNeighborList.add(startNode);
											//try{Thread.sleep(1000);}catch(Exception e){};
											startNode.setBackground(Color.CYAN);
											endNode.setBackground(Color.CYAN);
											base.mstConnectionPairList.add(new ConnectionPair(startNode,endNode));
										}
										else
										{
											if(!vToolBox.loopCheckForMST(startNode, endNode))
											{
												startNode.mstNeighborList.add(endNode);
												endNode.mstNeighborList.add(startNode);
												//try{Thread.sleep(1000);}catch(Exception e){};
												startNode.setBackground(Color.CYAN);
												endNode.setBackground(Color.CYAN);
												base.mstConnectionPairList.add(new ConnectionPair(startNode,endNode));
											}										
										}
									}
									
								}
							};
							new Thread(r1).start();
									
									
							//	--------------------------------------------------------------------------------
						}
						
						
						if(((JList)me.getSource()).getSelectedValue()=="master pcd")
						{
							Master_PCD_Diaplay_Dialog mpcd=new Master_PCD_Diaplay_Dialog(base);
							mpcd.setSize(800,500);
							mpcd.setVisible(true);
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="create Path Tree")
						{							
							createPathTree_FS();
						}
								
						if(((JList)me.getSource()).getSelectedValue()=="nodes reachable")
						{
							int reachableNodeCount=base.masterBSList.get(0).nodesReachable.size();
							
						}				
						
						if(((JList)me.getSource()).getSelectedValue()=="broadcast")
						{
							vToolBox.broadcast(((VLabel)mouseEvent.getSource()));
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="broadcast desired loc")
						{
							broadcastDesiredLoc(((VLabel)mouseEvent.getSource()));
						}
						
						
						if(((JList)me.getSource()).getSelectedValue()=="inspection")
						{
							pathTreeInspection();
						}
						
					
						if(((JList)me.getSource()).getSelectedValue()=="check interference")
						{
							checkInterference();
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="MMU placement")
						{
							placeMMU();
						}
						
						
						if(((JList)me.getSource()).getSelectedValue()=="broadcastToMove")
						{
							//System.exit(0);
							broadcastToMoveNodes(((VLabel)mouseEvent.getSource()));
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="broadcastToMoveSelectedNodes")
						{
							//broadcastToMoveDualRangeSelectedNodes(((VLabel)mouseEvent.getSource()),"0,70,70,140");
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="flooding")
						{
							base.connectionBreaker(base.masterNodeList);
							floodForGradient(((VLabel)mouseEvent.getSource()));
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="get_temp")
						{
							base.connectionBreaker(base.masterNodeList);
							getTemp(((VLabel)mouseEvent.getSource()),0);
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="getNeighbors")
						{
							base.connectionBreaker(base.masterNodeList);
							//getNeighbors(((VLabel)mouseEvent.getSource()),0);
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="broadcast_for_clustering")
						{
							broadcastForClusterng(base.baseStationClipBoard,100);
						}
						
					
						
					}
				}
				
		);
		
		add(optionList);
		this.setFocusable(true);
		
		this.addFocusListener
		(
			new FocusAdapter()
			{
				public void focusLost(FocusEvent fe)
				{
					System.out.println("focus have been lost");
					dispose();
				}
			}
		);
		
		//this.setVisible(true);
		
	}
	
	//****************************FUNCTIONS**********************************

	
	
	//-------------------------------------------------------------------------
	void broadcastDesiredLoc(VLabel sourceNode)
	{
		if(base.masterNodeList.size()<base.desiredLocList.size())
		{
			Runnable r=new Runnable()
			{
				
			public void run()
			{
			//-------COMPUTE THE RADIUS IN WHICH THE EXISTING NUMBER OF NODES CAN BE ADJUSTED-------------
			
			int b=Integer.parseInt(base.dxTf.getText());
			int p=Integer.parseInt(base.dyTf.getText());
			float h=(float)Math.sqrt(b*b+p*p);
			
			ArrayList<Location>effectiveLocList=new ArrayList<Location>();
			//int effectiveRadius=0;
			for(int i=0;i<h+base.commRangePerNode;i++)
			{
				effectiveLocList.removeAll(effectiveLocList);
				for(int j=0;j<base.desiredLocList.size();j++)
				{
					Location tempLoc=base.desiredLocList.get(j);
					if(vToolBox.getDist(base.masterBSList.get(0),tempLoc)<=i)
					{
						effectiveLocList.add(tempLoc);
					}
				}
				
				if(effectiveLocList.size()>=base.masterNodeList.size())
				{
					base.effectiveRadius=i;
					break;
				}
			}
			
			System.out.println("effective radius :"+ base.effectiveRadius);
			base.desiredLocList=effectiveLocList;
			
			
			for(int i=0;i<base.masterNodeList.size();i++)
			{
				if(vToolBox.getDist(base.masterNodeList.get(i),base.masterBSList.get(0))>base.effectiveRadius)
				{
					VLabel tempNode=base.masterNodeList.get(i);
					tempNode.setBackground(Color.GREEN);

					float totalDist=vToolBox.getDist(base.masterBSList.get(0),tempNode);
					int moveUptoDist=base.effectiveRadius+3;

					int totalMovement=(int)(totalDist-moveUptoDist);
					base.totalMovement=base.totalMovement+totalMovement;
					vToolBox.moveNode(tempNode,tempNode.getLoc(),base.masterBSList.get(0).getLoc(),moveUptoDist,5);
					//try{Thread.sleep(4000);}catch(Exception e){}
					//vToolBox.moveNode(tempNode,tempNode.getLoc(),loc,5);
				}
			}
			int x=Integer.parseInt(base.dxTf.getText());
			int y=Integer.parseInt(base.dyTf.getText());
			if(base.effectiveRadius<=x)
			{
				float maxDist1=(float)Math.sqrt((x*x)+(y*y));
				base.diagonal=maxDist1;
				maxDist1=maxDist1-base.effectiveRadius;
				float maxDist2=(float)Math.sqrt((base.effectiveRadius*base.effectiveRadius)+(base.effectiveRadius*base.effectiveRadius));
				base.maxDist=maxDist1+maxDist2;
			}			
			else
			{
				float maxDist1=(float)Math.sqrt((x*x)+(y*y));
				base.diagonal=maxDist1;
				maxDist1=maxDist1-base.effectiveRadius;
				
				float p1=(float)Math.sqrt((base.effectiveRadius*base.effectiveRadius)-(x*x));
				float p2=x-p1;
				float maxDist2=(float)Math.sqrt((x*x)+(p2*p2));
				
				base.maxDist=maxDist1+maxDist2;
			}
			
			
			base.alert.display("communication range increased for some time and max dist="+base.maxDist);
			try{Thread.sleep(4000);}catch(Exception e){}
			
			for(int i=0;i<base.masterNodeList.size();i++)
			{
				if(vToolBox.getDist(base.masterNodeList.get(i),base.masterBSList.get(0))>base.effectiveRadius)
				{
					VLabel tempNode=base.masterNodeList.get(i);
					tempNode.setBackground(Color.GREEN);
					Random rand=new Random();
					int randX=0;
					int randY=0;
					while(true)
					{
						randX=rand.nextInt(base.effectiveRadius);
						randY=rand.nextInt(base.effectiveRadius);
						
						randX=randX+100;
						randY=randY+100;
						
						Location loc=new Location(randX,randY,base);
						if(vToolBox.getDist(base.masterBSList.get(0).getLoc(),loc)<base.effectiveRadius)
						{
							break;
						}
					}
					
					
					Location loc=new Location(randX,randY,base);
					float dist=vToolBox.getDist(tempNode, loc);
					base.totalMovement=base.totalMovement+dist;
					
					vToolBox.moveNode(tempNode,tempNode.getLoc(),loc,5);
				}
			}
							
			//dhds2Phase1();
			}
			};
			new Thread(r).start();
		}
		//if(base.masterNodeList.size()<base.desiredLocList.size())
		//{
			try{Thread.sleep(9000);}catch(Exception e){}
		//}
		
		for(int i=0;i<sourceNode.neighborList.size();i++)
		{
		Packet packet=new Packet(base);
		packet.queryType=base.SET_DESIRED_LOC;
		packet.data=sourceNode;
		packet.protocolType=base.BROADCAST;
		
		packet.source=sourceNode;
		packet.destination=base.BROADCAST_DEST;
		packet.sourceType=base.BASESTATION;
		packet.ttl=1;
		packet.hopCount=0;
		packet.identifier=7;
		packet.fromNode=sourceNode;
		
		packet.desired_Loc_List=(ArrayList<Location>)base.desiredLocList.clone();
		
		base.baseProperties.showConnection();
		sourceNode.setBackground(base.BLACK);
		
			packet.toNode=sourceNode.neighborList.get(i);
		//	if(sourceNode.neighborList.get(i).buffer.size()<sourceNode.neighborList.get(i).defaultBufferSize)
		//	{
				sourceNode.neighborList.get(i).buffer.add(packet);
				sourceNode.neighborList.get(i).setBackground(Color.GRAY);
		//	}
			
		}
	}
	
//*************************************************************************
	
//*************************************************************************
//BROADCAST THE PACKET TO NODES FOR SELECTED MOVE
	
	void broadcastToMoveSelectedNodes(VLabel sourceNode,String distRange)
	{
		//Location location=new Location(sourceNode.getX(),sourceNode.yPos);
		Packet packet=new Packet(base);
		packet.queryType=base.SELECTED_MOVE;
		packet.data=distRange;
		packet.protocolType=base.BROADCAST;
		
		packet.source=sourceNode;
		packet.destination=base.BROADCAST_DEST;
		packet.sourceType=base.BASESTATION;
		packet.ttl=1;
		packet.hopCount=0;
		packet.identifier=7;
		packet.fromNode=sourceNode;
		
		base.baseProperties.showConnection();
		sourceNode.setBackground(base.BLACK);
		for(int i=0;i<sourceNode.neighborList.size();i++)
		{
			packet.toNode=sourceNode.neighborList.get(i);
			if(sourceNode.neighborList.get(i).buffer.size()<sourceNode.neighborList.get(i).defaultBufferSize)
				sourceNode.neighborList.get(i).buffer.add(packet);
				sourceNode.neighborList.get(i).setBackground(base.VOILET);
			
		}
			
	}
	//---------------------------------------------------------------------
	
	
	//*************************************************************************
	//BROADCAST THE PACKET TO NODES/ACTUATORS FOR SELECTED MOVE IN DUAL RANGE PATTERN
		
		void broadcastToMoveDualRangeSelectedNodes(VLabel sourceNode,ArrayList<Loc_Node>locNodeList,String distRange,int nodeType)
		{
			//Location location=new Location(sourceNode.getX(),sourceNode.yPos);
			Packet packet=new Packet(base);
			packet.queryType=base.DUALRANGE_SELECTED_MOVE;
			packet.data=distRange;
			packet.locNodeList=locNodeList;
			packet.protocolType=base.BROADCAST;
			
			packet.source=sourceNode;
			packet.destination=base.BROADCAST_DEST;
			packet.sourceType=base.BASESTATION;
			packet.ttl=1;
			packet.hopCount=0;
			packet.identifier=7;
			packet.fromNode=sourceNode;
			
			base.baseProperties.showConnection();
			sourceNode.setBackground(base.BLACK);
			if(nodeType==base.SENSORNODE)
			{
				for(int i=0;i<sourceNode.neighborList.size();i++)
				{
					packet.toNode=sourceNode.neighborList.get(i);
					if(sourceNode.neighborList.get(i).buffer.size()<sourceNode.neighborList.get(i).defaultBufferSize)
						sourceNode.neighborList.get(i).buffer.add(packet);
						sourceNode.neighborList.get(i).setBackground(base.VOILET);				
				}
			}
			
			if(nodeType==base.MMU)
			{
				for(int i=0;i<base.actuatorList.size();i++)
				{
					//packet.toNode=sourceNode.neighborList.get(i);
					packet.toNodeId=base.actuatorList.get(i).uniqueId;
					if(base.actuatorList.get(i).buffer.size()<base.actuatorList.get(i).defaultBufferSize)
						base.actuatorList.get(i).buffer.add(packet);
						base.actuatorList.get(i).setBackground(base.VOILET);				
				}		
			}
		}
		//---------------------------------------------------------------------
	
	
	//*************************************************************************
	//BROADCAST THE PACKET TO NODES FOR MOVE
		
		void broadcastToMoveNodes(VLabel sourceNode)
		{
			//Location location=new Location(sourceNode.getX(),sourceNode.yPos);
			Packet packet=new Packet(base);
			packet.queryType=base.MOVE;
			packet.data=sourceNode;
			packet.protocolType=base.BROADCAST;
			
			packet.source=sourceNode;
			packet.destination=base.BROADCAST_DEST;
			packet.sourceType=base.BASESTATION;
			packet.ttl=1;
			packet.hopCount=0;
			packet.identifier=7;
			packet.fromNode=sourceNode;
			
			base.baseProperties.showConnection();
			sourceNode.setBackground(base.BLACK);
			for(int i=0;i<sourceNode.neighborList.size();i++)
			{
				packet.toNode=sourceNode.neighborList.get(i);
				if(sourceNode.neighborList.get(i).buffer.size()<sourceNode.neighborList.get(i).defaultBufferSize)
					sourceNode.neighborList.get(i).buffer.add(packet);
					sourceNode.neighborList.get(i).setBackground(base.VOILET);				
			}
				
		}
		//---------------------------------------------------------------------

	
	
	void getTemp(VLabel sourceNode,int counter)
	{
		//Location location=new Location(sourceNode.getX(),sourceNode.yPos);
		Packet packet=new Packet(base);
		packet.queryType=base.GET_TEMP;
		packet.data=null;
		packet.protocolType=base.BROADCAST;
		
		packet.source=sourceNode;
		//packet.destination=base.nodeArray[counter];
		packet.destination=base.masterNodeList.get(counter);
		packet.sourceType=base.BASESTATION;
		packet.ttl=1;
		packet.hopCount=0;
		packet.identifier=7;
		packet.fromNode=sourceNode;
		
		base.baseProperties.showConnection();
		sourceNode.setBackground(base.BLACK);

		packet.toNode=base.masterBSList.get(0).neighborList.get(counter);
		
			if(base.masterBSList.get(0).neighborList.get(counter).buffer.size()<base.masterBSList.get(0).neighborList.get(counter).defaultBufferSize)
				//sourceNode.neighborList.get(i).buffer.add(packet);
				base.masterBSList.get(0).neighborList.get(counter).buffer.add(packet);
			//System.out.println(base.baseStationArray[0].neighborList.get(counter).gradientStack);
			//System.exit(0);
			
			
		//}
			
	}
	
	
	//**************************************************************************
	//FUNCTION FOR GETTING THE NEIGHBORS OF THE NODE****************************
	
	void getNeighbors(VLabel sourceNode,VLabel destNode,VLabel parentNode)
	{
		//Location location=new Location(sourceNode.getX(),sourceNode.yPos);
		Packet packet=new Packet(base);
		packet.queryType=base.GET_DISCONNECTED_NEIGHBORS;
		
		packet.data=parentNode;
		packet.protocolType=base.BROADCAST;
		
		packet.source=sourceNode;
		packet.destination=destNode;
		packet.sourceType=base.SENSORNODE;
	
		packet.ttl=1;
		packet.hopCount=0;
		packet.identifier=7;
		packet.fromNode=sourceNode;
		
		base.baseProperties.showConnection();
		sourceNode.setBackground(base.BLACK);
		System.out.println("VVVVVVVVVVVvv"+parentNode.uniqueId);

		//packet.toNode=base.baseStationArray[0].neighborList.get(0);
		
			if(destNode.buffer.size()<destNode.defaultBufferSize)
			{
				destNode.buffer.add(packet);
			}
			//System.out.println(base.baseStationArray[0].neighborList.get(counter).gradientStack);
			//System.exit(0);
			
			
		//}
			
	}
	//------------------------------------------------------------------------------
	
	
	
		
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
				packet.protocolType=base.FLOODING;
				packet.queryType=base.SET_GRADIENT;
				packet.ttl=13;
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
	
	//-----------------------------------------------------------------------
	//-------------------FUNCTION TO CREATE PATH TREE------------------------
	void createPathTree_FS()
	{
		Runnable r=new Runnable()
		{
			public void run()
			{
				//base.startTime1=System.currentTimeMillis();
				vToolBox.initNodeState();
				base.masterBSList.get(0).master_Pcd_List.removeAll(base.masterBSList.get(0).master_Pcd_List);
				System.out.println("number of neighbours :"+base.masterBSList.get(0).nodesConnectingBaseList.size());
				
				Queue <VLabel>tempQueue=new LinkedList<VLabel>();
				base.masterBSList.get(0).traversed_for_tree=true;
				vToolBox.removeConnections();
				//
				for(int i=0;i<base.masterBSList.get(0).nodesConnectingBaseList.size();i++)
				{
					base.masterBSList.get(0).pathTreeNeighborList.add(base.masterBSList.get(0).nodesConnectingBaseList.get(i));					
					tempQueue.add(base.masterBSList.get(0).nodesConnectingBaseList.get(i));
					float dist=vToolBox.getDist(base.masterBSList.get(0),base.masterBSList.get(0).nodesConnectingBaseList.get(i));
					Parent_Child_Dist temp_Pcd=new Parent_Child_Dist(base.masterBSList.get(0),base.masterBSList.get(0).nodesConnectingBaseList.get(i),dist,base);
					temp_Pcd.connection=true;
					base.masterBSList.get(0).master_Pcd_List.add(temp_Pcd);
					base.masterBSList.get(0).nodesConnectingBaseList.get(i).traversed_for_tree=true;																			
				}
			//	------------ADDING PARENT AND ROOT TO ROUTING PATH----------------------
				for(int i=0;i<base.masterBSList.get(0).master_Pcd_List.size();i++)
				{
					VLabel tempChild=base.masterBSList.get(0).master_Pcd_List.get(i).child;
					Object rt=base.masterBSList.get(0);
					Object p=base.masterBSList.get(0);
					Root_Parent_Children rpc=new Root_Parent_Children(rt,p);
					tempChild.path_Tree_Routing_Table.add(rpc);
				}
			//	------------------------------------------------------------------------
				
				vToolBox.createPathTree(base.masterBSList.get(0),tempQueue);
				
				//base.endTime1=System.currentTimeMillis();
				//long timeTaken=base.endTime1-base.startTime1;
				//base.alert.display("time taken for path tree :"+timeTaken);
			}
		};
		pathTreeTh=new Thread(r);
		pathTreeTh.start();
		Runnable r1=new Runnable()
		{								
			public void run()
			{							
				try
				{
					pathTreeTh.join();
					vToolBox.completePathTreeTable(base.masterBSList.get(0),base.masterBSList.get(0).master_Pcd_List);
				
				}
				catch(Exception e){}
			}
		};
		new Thread(r1).start();
		
		
		Runnable r2=new Runnable()
		{
			public void run()
			{
				
				for(int i=0;i<base.actuatorList.size();i++)
				{
					if(base.actuatorList.get(i).isAlive)
					{
						try{Thread.sleep(100);}catch(Exception e){}
						base.actuatorList.get(i).createPathTree();
					}
				}
			}
		};
		new Thread(r2).start();	
	}
	//-------------------------------------------------------------------------
	
/*	
	void createPathTree(Queue<VLabel>tempQueue)
	{
		ArrayList<Parent_Child_Dist> pcd_List=new ArrayList<Parent_Child_Dist>();
		Queue<VLabel>childrenQueue=new LinkedList<VLabel>();
		boolean found_replaced=false;
		while(tempQueue.size()>0)
		{
			VLabel tempLb=tempQueue.poll();
			tempLb.setBackground(Color.BLUE);
			System.out.println("node id ;"+tempLb.uniqueId);
			
			
			for(int j=0;j<tempLb.neighborList.size();j++)
			{
				if(tempLb.neighborList.get(j).traversed_for_tree==false)
				{
					VLabel parent=tempLb;
					VLabel child=tempLb.neighborList.get(j);			
					float dist=vToolBox.getDist(tempLb,tempLb.neighborList.get(j));
					found_replaced=false;
					for(int k=0;k<pcd_List.size();k++)
					{
						
						if(pcd_List.get(k).child==child)
						{
							found_replaced=true;
							if(dist<pcd_List.get(k).dist )
							{
								pcd_List.remove(k);
								Parent_Child_Dist temp_pcd=new Parent_Child_Dist(parent,child,dist,base);
								temp_pcd.connection=true;
								pcd_List.add(temp_pcd);
								child.traversed_for_tree=true;
								childrenQueue.add(child);
							}
						}
						
					}
					if(!found_replaced)
					{
						Parent_Child_Dist temp_pcd=new Parent_Child_Dist(parent,child,dist,base);
						temp_pcd.connection=true;
						pcd_List.add(temp_pcd);
						child.traversed_for_tree=true;
						childrenQueue.add(child);
					}
					
				}
			}
		}
		
		base.baseStationArray[0].master_Pcd_List.addAll(pcd_List);
		//System.out.println("LIST SIZE :"+pcd_List.size());
		base.drawPathTree=true;
	//	System.out.println("Master pcd list"+base.baseStationArray[0].master_Pcd_List.size());
	//for(int l=0;l<base.baseStationArray[0].master_Pcd_List.size();l++)
	//	{
	//		Parent_Child_Dist tempPcd=base.baseStationArray[0].master_Pcd_List.get(l);
	//		System.out.println("parent id :"+tempPcd.parent.uniqueId+"      child id :"+tempPcd.child.uniqueId+"     distance :"+tempPcd.dist);
	//	}
		//System.exit(0);
		
		try
		{
			createPathTree(childrenQueue);
		}catch(Exception e){}

	}*/
	//-------------------------------------------------------------------------
	
	//-------FUNCTION TO PERFORM INSPECTION------------------------------------
	void pathTreeInspection()
	{
		Runnable r=new Runnable()
		{
			public void run()
			{		
				base.masterBSList.get(0).nodesReachable.removeAll(base.masterBSList.get(0).nodesReachable);
				Packet packet=new Packet(base);
				packet.queryType=base.INSPECTOR;
				packet.root=base.masterBSList.get(0);
				packet.movement=base.FORWARD;
				
				for(int i=0;i<base.masterBSList.get(0).nodesConnectingBaseList.size();i++)
				{
				//	if(base.baseStationArray[0].nodesConnectingBaseList.get(i).buffer.size()<base.baseStationArray[0].nodesConnectingBaseList.get(i).defaultBufferSize)
				//	{
					base.masterBSList.get(0).nodesConnectingBaseList.get(i).buffer.add(packet);
				//	}
				}
			}
		};
		new Thread(r).start();
		
	//	-----------------STARTING INSPECTION FROM ACTUATORS---------------------
		for(int i=0;i<base.actuatorList.size();i++)
			{
				if(base.actuatorList.get(i).isAlive)
				{
					base.actuatorList.get(i).inspect();
				}
			}
	}
	//-------------------------------------------------------------------------
	
	//---------FUNCTION TO CHECK INTERFERENCE----------------------------------
	void checkInterference()
	{
	Partition part=new Partition();
	part.actuator_List.add(base.masterBSList.get(0));
	base.partition_List.add(part);
	
	for(int i=0;i<base.actuatorList.size();i++)
	{
		if(base.actuatorList.get(i).isAlive)
		{
			
		
		if(base.partition_List.size()==0)
		{
			Partition partition=new Partition();
			partition.actuator_List.add(base.actuatorList.get(i));
			base.partition_List.add(partition);
		}
		
		else
		{
			boolean intersected=false;
			for(int j=0;j<base.partition_List.size();j++)
			{
				Partition tempPartition=base.partition_List.get(j);
				ArrayList<Object>tempActList=tempPartition.actuator_List;
				for(int k=0;k<tempActList.size();k++)
				{
					ArrayList <VLabel>reachableNodeList=new ArrayList<VLabel>();
					try
					{	reachableNodeList=Actuator.class.cast(tempActList.get(k)).nodesReachable;
					}catch(Exception e){}
					try
					{	reachableNodeList=VLabel.class.cast(tempActList.get(k)).nodesReachable;
					}catch(Exception e){}
                      					
					if(vToolBox.interferance(base.actuatorList.get(i).nodesReachable,reachableNodeList))
					{
						tempActList.add(base.actuatorList.get(i));
						intersected=true;
						break;
					}
				}
			}
			if(!intersected)
			{
				Partition partition=new Partition();
				partition.actuator_List.add(base.actuatorList.get(i));
				base.partition_List.add(partition);										
			}
		}
		}
	}
	
	System.out.println("partitions ="+ base.partition_List.size());
	
	for(int i=0;i<base.partition_List.size();i++)
	{
		for(int j=0;j<base.partition_List.get(i).actuator_List.size();j++)
		{
			if(i==0)
			{
				try
				{
					Actuator.class.cast(base.partition_List.get(i).actuator_List.get(j)).setBackground(Color.BLUE);
				}catch(Exception e){}
				try
				{
					VLabel.class.cast(base.partition_List.get(i).actuator_List.get(j)).setBackground(Color.BLUE);
				}catch(Exception e){}
			}
			if(i==1)
			{
				try
				{
					Actuator.class.cast(base.partition_List.get(i).actuator_List.get(j)).setBackground(Color.PINK);
				}catch(Exception e){}
				try
				{
					VLabel.class.cast(base.partition_List.get(i).actuator_List.get(j)).setBackground(Color.PINK);
				}catch(Exception e){}
			}
			if(i==2)
			{
				try
				{
					Actuator.class.cast(base.partition_List.get(i).actuator_List.get(j)).setBackground(Color.GREEN);
				}catch(Exception e){}
				try
				{
					VLabel.class.cast(base.partition_List.get(i).actuator_List.get(j)).setBackground(Color.GREEN);
				}catch(Exception e){}
			}
			
			if(i==3)
			{
				try
				{
					Actuator.class.cast(base.partition_List.get(i).actuator_List.get(j)).setBackground(Color.ORANGE);
				}catch(Exception e){}
				try
				{
					VLabel.class.cast(base.partition_List.get(i).actuator_List.get(j)).setBackground(Color.ORANGE);
				}catch(Exception e){}
			}
		}
		
	}
	}
	//-------------------------------------------------------------------------
	
	//-----------------FUNCTION TO PLACE MMUS----------------------------------
	void placeMMU()
	{
	base.startTime=System.currentTimeMillis();
	
	base.actuator_Tree_Connection_Showable=false;
	base.baseStation_Tree_Connection_Showable=false;
	base.mmu_Node_Connection_Showable=false;
	Runnable r=new Runnable()
	{																
	public void run()
	{														
		Set<VLabel>tempSet=new HashSet<VLabel>();							
		for(int i=0;i<base.partition_List.size();i++)
		{
			Partition tempPart=base.partition_List.get(i);
			for(int j=0;j<tempPart.actuator_List.size();j++)
			{
				
				try
				{
					VLabel tempNode=VLabel.class.cast(tempPart.actuator_List.get(j));
					for(int k=0;k<tempNode.nodesReachable.size();k++)
					{
				//	System.exit(0);
						if(tempSet.add(tempNode.nodesReachable.get(k)))
						{
							tempPart.node_List.add(tempNode.nodesReachable.get(k));
						}
					}
				}catch(Exception e){}
				
				try
				{
					Actuator tempAct=Actuator.class.cast(tempPart.actuator_List.get(j));
					for(int k=0;k<tempAct.nodesReachable.size();k++)
					{
				//	System.exit(0);
						if(tempSet.add(tempAct.nodesReachable.get(k)))
						{
							tempPart.node_List.add(tempAct.nodesReachable.get(k));
						}
					}
				}catch(Exception e){}
			}
		}
		
		for(int i=0;i<base.partition_List.size();i++)
		{
			System.out.println("Partition "+i+" : "+base.partition_List.get(i).node_List.size());
		}
		
	//	System.exit(0);
		
		while(base.partition_List.size()>1)
		{
			
			float minDist=9999f;
			VLabel node1=null;
			VLabel node2=null;
			Partition newPartition=null;
	//	for(int i=0;i<base.partition_List.size();i++)
	//	{
			
			Partition tempPart1=base.partition_List.get(0);
			for(int j=1;j<base.partition_List.size();j++)
			{
				Partition tempPart2=base.partition_List.get(j);
				for(int k=0;k<tempPart1.node_List.size();k++)
				{
					VLabel tempNode=tempPart1.node_List.get(k);
					for(int l=0;l<tempPart2.node_List.size();l++)
					{
						float dist=vToolBox.getDist(tempNode,tempPart2.node_List.get(l));
						if(dist<minDist)
						{
							minDist=dist;
							node1=tempNode;
							node2=tempPart2.node_List.get(l);
							newPartition=tempPart2;
						}
					}
				}
			}
			
			Actuator selectedActuator2=null;
			Actuator selectedActuator1=null;
			Float dist=9999f;
		//	-----------------DETERMINING THE NEAREST NON-PLACED ACTUATOR FROM REGION 1--------
			for(int i=1;i<tempPart1.actuator_List.size();i++)
			{
				
				
				
				if(!(Actuator.class.cast(tempPart1.actuator_List.get(i)).isPlaced))
				{
					Actuator candidateActuator=Actuator.class.cast(tempPart1.actuator_List.get(i));
					float newDist=vToolBox.getDist(candidateActuator,node1);
					if(newDist<dist)
					{
						dist=newDist;
						selectedActuator1=candidateActuator;
					}
				}
			}					
			//----------------------------------------------------------------------------------
		
			//-----------------DETERMINING THE NEAREST NON-PLACED ACTUATOR FROM REGION 2--------
			dist=9999f;
			for(int i=0;i<newPartition.actuator_List.size();i++)
			{
				
													
				if(!(Actuator.class.cast(newPartition.actuator_List.get(i)).isPlaced))
				{
					Actuator candidateActuator=Actuator.class.cast(newPartition.actuator_List.get(i));
					float newDist=vToolBox.getDist(candidateActuator,node2);
					if(newDist<dist)
					{
						dist=newDist;
						selectedActuator2=candidateActuator;
						System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
					}
				}
			}					
			//----------------------------------------------------------------------------------
																		
			selectedActuator1.isPlaced=true;
			selectedActuator2.isPlaced=true;
			
			selectedActuator1.setBackground(Color.CYAN);
			selectedActuator2.setBackground(Color.CYAN);
			
			
			//try{Thread.sleep(10000);}catch(Exception e){}
			
			vToolBox.moveActuator(selectedActuator1,node1,base.ROAMING,selectedActuator1.getLoc(),node1.getLoc(),10);
			float distMoved1=vToolBox.getDist(selectedActuator1.getLoc(),node1.getLoc());
			selectedActuator1.distMoved=distMoved1;		
			base.totalActuatorMovement=base.totalActuatorMovement+selectedActuator1.distMoved;
			base.actuatorMovementList.add(selectedActuator1.distMoved);
			vToolBox.moveActuator(selectedActuator2,node1,base.ROAMING,selectedActuator2.getLoc(),node2.getLoc(),10);
			float distMoved2=vToolBox.getDist(selectedActuator2.getLoc(),node2.getLoc());
			selectedActuator2.distMoved=distMoved2;
			base.totalActuatorMovement=base.totalActuatorMovement+selectedActuator2.distMoved;
			base.actuatorMovementList.add(selectedActuator2.distMoved);
			
			
			
			node1.setBackground(Color.black);							
			node2.setBackground(Color.black);
			
			tempPart1.actuator_List.addAll(newPartition.actuator_List);
			tempPart1.node_List.addAll(newPartition.node_List);
			
			int index=base.partition_List.indexOf(newPartition);							
			base.partition_List.remove(index);																										
		}
		base.endTime=System.currentTimeMillis();
		long duration=base.endTime-base.startTime;
		base.alert.display("time taken : "+duration);
		
		}
		};
		new Thread(r).start();
}
	//-------------------------------------------------------------------------

	
    // BROADCAST TO SUPPLY THE NODES WITH THE LOCATION OF BASE STATION-------
	void broadcastForClusterng(VLabel sourceNode,int subDivisionSide)
	{				
		//Location location=new Location(sourceNode.getX(),sourceNode.yPos);
		Packet packet=new Packet(base);
		packet.queryType=base.SET_CLUSTER;
		packet.data=subDivisionSide;
		packet.protocolType=base.BROADCAST;
		
		packet.source=sourceNode;
		packet.destination=base.BROADCAST_DEST;
		packet.sourceType=base.BASESTATION;
		packet.ttl=10;
		packet.hopCount=0;
		packet.identifier=7;
		packet.fromNode=sourceNode;
		
		base.baseProperties.showConnection();
		sourceNode.setBackground(base.BLACK);
		for(int i=0;i<sourceNode.neighborList.size();i++)
		{
			packet.toNode=sourceNode.neighborList.get(i);
			if(sourceNode.neighborList.get(i).buffer.size()<sourceNode.neighborList.get(i).defaultBufferSize)
				sourceNode.neighborList.get(i).buffer.add(packet);
				sourceNode.neighborList.get(i).setBackground(base.PINK);
			
		}
			
	}
//-------------------------------------------------------------------------
	
	
	
	
	public static void main(String args[])
	{
		VBaseOptionDialog ov=new VBaseOptionDialog(null,null);
	}
}
