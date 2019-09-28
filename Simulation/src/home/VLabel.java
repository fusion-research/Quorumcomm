package home;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.*;
import java.util.*;

import javax.swing.*;
public class VLabel extends JPanel implements Comparator<VLabel>
{
	
	
	float tempVar;
	
	//------------------DECLARING VARIABLES FOR AADS-------------------------
	
	float distFromBS0,distFromBS1,distFromBS2,distFromBS3,distFromRef;
	float diff0=100,diff1=100,diff2=100,diff3=100,diffRef=0;
	int intersectionCounter=0;
	
	//-----------------------------------------------------------------------
	
	//----------------DECLARING VARIABLES FOR NADS---------------------------
	
	int group=0;
	ArrayList<Long>timeLine=new ArrayList<Long>();
	Packet packetNadsPhase2;
	Packet replyPacketNadsPhase2;
	ArrayList<VLabel>unplacedNeighborList=new ArrayList<VLabel>();
	ArrayList<Location>unallocatedDlList=new ArrayList<Location>();
	ArrayList<Integer>receivedUnallocatedDlList=new ArrayList<Integer>();
	ArrayList<VLabel>unplacedFriendSNList=new ArrayList<VLabel>();
	
	ArrayList<ArrayList<LocNodeDist>> masterNodeDistlist=new ArrayList<ArrayList<LocNodeDist>>(); 
	
	
	Stack<VLabel>parentSequenceStack;
	Stack<VLabel>childrenSequenceStack;
	
	BinarySearchTree bst31;
	BinarySearchTree bst32;
	
	boolean reserved=false;
	
	long currentTime;
	int timeStepCounter=0;
	
	int group1Counter=0;
	int group2Counter=0;
	int group3Counter=0;
	int group4Counter=0;
	int group5Counter=0;
	int group6Counter=0;
	int group7Counter=0;
	//-----------------------------------------------------------------------
	
	//-------------------DECLARING VARIABLES FOR EDS-------------------------
	
	int subDivisionNumber=0;
	Cluster memberOfCluster;
	ArrayList <VLabel>clusterMemberList=new ArrayList<VLabel>();
	ArrayList<Node_Dist>nodeDistList=new ArrayList<Node_Dist>();
	ArrayList<VLabel>dataRecievedFromCHList=new ArrayList<VLabel>();
	ArrayList<BS_Cluster>BS_Cluster_List=new ArrayList<BS_Cluster>();
	VLabel nodeClusterHead;
	boolean isClusterHead=false;
	String dataSt="";
	int commCounter=0;
	int lastRound=0;
	
	//-----------------------------------------------------------------------
	
	//----------------DECLARING VARIABLES FOR DEBUGGING----------------------
	
	Thread communicationThread;
	
	//----------------------------------------------------------------------
	
	
	
	VToolBox toolBox;
	int nodeType;
	
	float commRange;
	float sensingRange;
	double power=10;
	int uniqueId;
	int defaultHopCount=999;
	int isInRange=0;
	float nodeMovement=0;
	int chargingCycleCounter=0;		//COUNTER OF NUMBER OF TIMES THE BATTERY HAVE BEEN CHARGED
	int criticalEnergyLevel=100;
	Location finalLocation;
	Location nearestDesiredPoint;  	// NEAREST DESIRED POINT WHERE NODE NEED TO BE SHIFTED
	//int minMoveRequired;			// MINIMUM MOVEMENT REQUIRED BY THE NODE TO 
	
	int xPos;
	int yPos;
	
	boolean sourceNode=false;
	boolean sinkNode=false;
	boolean traversed=false;
	boolean isConnected=false;		//IF NODE IS IN DIRECT OR INDIRECT CONNECTION WITH BASE STATION
	boolean identified=false;
	boolean movable=true;			//IF NODE CAN MOVE OR NOT
	boolean isPlaced=false;			//IF NODE IS PLACED IN DESIRED LOCATION OR NOT
	boolean isMoving=false;
	boolean isAlive=true;
	boolean traversed_for_tree=false;
	boolean iAmDeserving=true;
	Packet newPacket=null;
	
	boolean flag=true;
	
	
	
	Color lineColor=Color.black;
	Color defaultColor;
	//int defaultBufferSize=5;
	int defaultBufferSize=20;
	
	ArrayList <VLabel> neighborList=new ArrayList<VLabel>();
	
	ArrayList <Boolean> connectionList=new ArrayList<Boolean>();
	ArrayList <VLabel> nodesConnectingBaseList=new ArrayList<VLabel>();
	
	ArrayList <VLabel> activeNodeList=new ArrayList <VLabel>();
	
	ArrayList<NodeAndParent>nodeParentList=new ArrayList<NodeAndParent>();
	
	//ArrayList<Loc_Node> locNodeList=new ArrayList<Loc_Node>();
	
	ArrayList<Loc_Dist> locDistList=new ArrayList<Loc_Dist>();
	
	ArrayList<Location>obstacleShapeList=new ArrayList<Location>();  // FOR STORING THE CO-ORDINATES AROUND THE OBSTACLE
	
	ArrayList<VLabel>mstNeighborList=new ArrayList<VLabel>();
	ArrayList<VLabel>pathTreeNeighborList=new ArrayList<VLabel>();
	ArrayList<VLabel>clusterNeighborList=new ArrayList<VLabel>();
	
	
	
	
	VLabel pathTreeParent;
	
	//-----------------------STORING TREE RECORDS FOR BASE STATION-----------------------------
	ArrayList<Parent_Child_Dist >master_Pcd_List=new ArrayList<Parent_Child_Dist>();
	//------------------------------------------------------------------------------------------
	//------ROUTING TABLE FOR PATH TREE--TO ROUTE INSPECTOR PACKETS-----------------------------
	ArrayList<Root_Parent_Children>path_Tree_Routing_Table=new ArrayList<Root_Parent_Children>();
	ArrayList<VLabel> nodesReachable = new ArrayList<VLabel>();
	//------------------------------------------------------------------------------------------
	boolean mstTraversed=false;
	
	
	Queue<Packet>buffer=new LinkedList<Packet>();
	ArrayList<Packet>packetQueue=new ArrayList<Packet>();
	
	ArrayList<Stack<VLabel>> gradientStackPriorityQueue=new ArrayList<Stack<VLabel>>();
	//Location baseLoc;
	VLabel baseStationId;
	VLabel parentTowardsBase=null;//?????????????????????????????????????????????????
	
	
	//------------VARIABLES FOR BROADCAST DESIRED LOCATION SCHEME-----------------------
	ArrayList<Location> desired_Loc_List;
	Location nearestLoc;
	int counter=0;
	ArrayList<VLabel> groupMember_List=new ArrayList<VLabel>();
	//ArrayList<Location>neighbouringDlList=new ArrayList<Location>();
	//ArrayList<VLabel>unplacedNeighbourList=new ArrayList<VLabel>();
	ArrayList<Integer>packetIdList=new ArrayList<Integer>();
	ArrayList<LocNodeDist>locNodeDistList=new ArrayList<LocNodeDist>();
	//----------------------------------------------------------------------------------
	
	//VLabel parentNode;
	Base base;
	VLabel thisVLabel;
	VLabel thisNode;
	
	boolean working=true;
	boolean sensing=false;
	
	boolean serviceRequestSent=false;
	Algorithms algo;
	Actuator clusterHead;
	Thread thread1;
	VLabel()
	{}
	
	VLabel(int nodeType,String value,Base b)
	{
		//super(value);
		this.base=b;
		this.nodeType=nodeType;
		thisNode=this;
		//thisNode.isInRange=base.RANGE0;

	}

	VLabel(int nodeType,Color color,Base b)
	{
		//super(img);
		

		
		
		this.setLayout(null);
		this.setBackground(color);
		this.defaultColor=color;
		this.nodeType=nodeType;
		this.base=b;
		
		bst31=new BinarySearchTree(base);
		bst32=new BinarySearchTree(base);
		
		thisVLabel=this;
		thisNode=this;
		toolBox=new VToolBox(base);
		xPos=this.getX()-(this.getWidth()/2);
		xPos=this.getY()-(this.getHeight()/2);
		
		


		
		
		algo=new Algorithms(base);
		
		//------------PACKET FOR NADS- PHASE-2-----------------------------------
		packetNadsPhase2=new Packet(base);
		packetNadsPhase2.queryType=base.NADS_PHASE2;
		packetNadsPhase2.protocolType=base.BROADCAST;
		
		packetNadsPhase2.source=thisNode;
		packetNadsPhase2.destination=base.BROADCAST_DEST;
		packetNadsPhase2.sourceType=base.SENSORNODE;
		packetNadsPhase2.ttl=1;
		packetNadsPhase2.hopCount=0;
		packetNadsPhase2.identifier=7;
		packetNadsPhase2.fromNode=thisNode;
		//float dist=toolBox.getDist(thisNode,thisNode.nearestDesiredPoint);
		//packetNadsPhase2.loc_Dist=new Loc_Dist(thisNode.nearestDesiredPoint,dist,base);
		//-----------------------------------------------------------------------
		
		
		//------------REPLY FOR NADS- PHASE-2-----------------------------------
		replyPacketNadsPhase2=new Packet(base);
		replyPacketNadsPhase2.queryType=base.REPLY_NADS_PHASE2;
		replyPacketNadsPhase2.protocolType=base.BROADCAST;
		
		replyPacketNadsPhase2.source=thisNode;
		//replyPacketNadsPhase2.destination=base.BROADCAST_DEST;
		replyPacketNadsPhase2.sourceType=base.SENSORNODE;
		replyPacketNadsPhase2.ttl=1;
		replyPacketNadsPhase2.hopCount=0;
		replyPacketNadsPhase2.identifier=7;
		replyPacketNadsPhase2.fromNode=thisNode;
		//float dist=toolBox.getDist(thisNode,thisNode.nearestDesiredPoint);
		//packetNadsPhase2.loc_Dist=new Loc_Dist(thisNode.nearestDesiredPoint,dist,base);
		//-----------------------------------------------------------------------
		
		//------------RELOCATE FOR NADS- PHASE-2-----------------------------------

		//-----------------------------------------------------------------------
		
		
		Runnable r1=new Runnable()
		{
			public void run()
			{
				//while(power>0 && working && base.signalHouse.snCommThreadFlag)
				while(working && base.signalHouse.snCommThreadFlag)
				{
				//-----------------GADS TIMELINE OPERATION---------------
					
					if(timeLine.size()>timeStepCounter)
					{
						currentTime=System.currentTimeMillis();
						if(currentTime>=timeLine.get(timeStepCounter))
						{
							//timeLine.remove(0);
							switch(timeStepCounter)
							{
								case 0:
								{
									timeStepCounter++;
									defaultColor=Color.BLUE;
									
									desired_Loc_List=(ArrayList<Location>)base.desiredLocList.clone();
									
									Location loc=thisNode.getLoc();
									desired_Loc_List=toolBox.vSort(loc,desired_Loc_List);
									
									
									thisNode.nearestDesiredPoint=desired_Loc_List.get(0);
									Packet packet=new Packet(base);
									packet.queryType=base.SET_GROUP;
									packet.protocolType=base.BROADCAST;									
									packet.source=thisNode;
									packet.destination=base.BROADCAST_DEST;
									packet.sourceType=base.SENSORNODE;
									packet.ttl=1;
									packet.hopCount=0;
									packet.identifier=7;
									packet.fromNode=thisNode;
									float dist=toolBox.getDist(thisNode,thisNode.nearestDesiredPoint);
									packet.loc_Dist=new Loc_Dist(thisNode.nearestDesiredPoint,dist,base);
									
									for(int i=0;i<thisNode.neighborList.size();i++)
									{
										thisNode.neighborList.get(i).buffer.add(packet);										
									}
								}
								break;
								
								case 1:
								{
									timeStepCounter++;
									//base.alert.display("workin");
									
									if(iAmDeserving)
									{
										setBackground(Color.RED);
										base.placedNodesCount=base.placedNodesCount+1;
										
										group=nearestDesiredPoint.group;
										isPlaced=true;		
										thisNode.nearestDesiredPoint.occupied=true;
										iAmDeserving=false;
										//setBackground(Color.GREEN);
										base.placedNodeList.add(thisNode);
										base.nodesPlacedNadsPh1=base.nodesPlacedNadsPh1+1;
										
										float dist=toolBox.getDist(thisNode.getLoc(),thisNode.nearestDesiredPoint);
										base.totalMovement=base.totalMovement+(dist/base.scale);
										base.totalMovementNadsPh1=base.totalMovementNadsPh1+(dist/base.scale);
										toolBox.moveNode(thisNode,thisNode.getLoc(),thisNode.nearestDesiredPoint,20);

									}
								}								
								break;
								
								case 2:
								{
									timeStepCounter++;
									toolBox.refreshConnections();
									
									//base.alert.display("time stamp 2");
									if(thisNode.isPlaced && thisNode.group==1)
									{
										//thisNode.addNeighboringDL();
										//thisNode.defaultColor=Color.GREEN;
										BroadcastPacket(thisNode,packetNadsPhase2);
									}																		
								}
								break;
								
								case 3:
								{									
									
								//----------------residue of previous timestamp--------------------------------
									if(thisNode.isPlaced && thisNode.group==1)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											for(int j=0;j<thisNode.unplacedNeighborList.size();j++)
											{
												float tempDist=toolBox.getDist(thisNode.unplacedNeighborList.get(j),thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i));						
												locNodeDistList.add(new LocNodeDist(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i),thisNode.unplacedNeighborList.get(j),tempDist,base));
											}
										}																				
									}
									
									toolBox.vSort(locNodeDistList);
									
									for(int i=0;i<locNodeDistList.size();i++)
									{
										Location loc=locNodeDistList.get(i).getLoc();
										VLabel node=locNodeDistList.get(i).getNode();
											if(loc.occupied==true || node.isPlaced==true)
											{
												continue;
											}
											else
											{
												Packet relocatePacketNadsPhase2=getRelocatePacketNadsPhase2();
												loc.occupied=true;
												node.isPlaced=true;
												node.group=loc.group;
												base.placedNodesCount=base.placedNodesCount+1;
												base.nodesPlacedNadsPh2_1++;
												base.nodesPlacedNadsPh2++;
												relocatePacketNadsPhase2.moveTo=loc;
												relocatePacketNadsPhase2.destination=node;
												
												
												//thisNode.nearestDesiredPoint=loc;
												float dist=toolBox.getDist(thisNode.getLoc(),loc);
												base.totalMovement=base.totalMovement+(dist/base.scale);
												base.totalMovementNadsPh2_1=base.totalMovementNadsPh2_1+(dist/base.scale);
												base.totalMovementNadsPh2=base.totalMovementNadsPh2+(dist/base.scale);												
												node.buffer.add(relocatePacketNadsPhase2);												
											}									
									}
								//---------------------residue of previous timestamp--------------------	
									timeStepCounter++;
									toolBox.refreshConnections();
									//base.alert.display("time stamp 3");
									if(thisNode.isPlaced && thisNode.group==2)
									{
										//thisNode.addNeighboringDL();
										//thisNode.defaultColor=Color.GREEN;
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
								}
								break;
								case 4:
								{
									
								//	----------------residue of previous timestamp--------------------------------
									if(thisNode.isPlaced && thisNode.group==2)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											for(int j=0;j<thisNode.unplacedNeighborList.size();j++)
											{
												float tempDist=toolBox.getDist(thisNode.unplacedNeighborList.get(j),thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i));						
												locNodeDistList.add(new LocNodeDist(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i),thisNode.unplacedNeighborList.get(j),tempDist,base));
											}
										}																				
									}
									
									toolBox.vSort(locNodeDistList);
									
									for(int i=0;i<locNodeDistList.size();i++)
									{
										Location loc=locNodeDistList.get(i).getLoc();
										VLabel node=locNodeDistList.get(i).getNode();								
											if(loc.occupied==true || node.isPlaced==true)
											{
												continue;
											}
											else
											{
												Packet relocatePacketNadsPhase2=getRelocatePacketNadsPhase2();
												loc.occupied=true;
												node.isPlaced=true;
												node.group=loc.group;
												base.placedNodesCount=base.placedNodesCount+1;
												base.nodesPlacedNadsPh2_2++;
												base.nodesPlacedNadsPh2++;
												relocatePacketNadsPhase2.moveTo=loc;
												relocatePacketNadsPhase2.destination=node;
												
												//thisNode.nearestDesiredPoint=loc;
												float dist=toolBox.getDist(thisNode.getLoc(),loc);
												base.totalMovement=base.totalMovement+(dist/base.scale);
												base.totalMovementNadsPh2_2=base.totalMovementNadsPh2_2+(dist/base.scale);
												base.totalMovementNadsPh2=base.totalMovementNadsPh2+(dist/base.scale);
												node.buffer.add(relocatePacketNadsPhase2);												
											}							
									}
								//---------------------residue of previous timestamp--------------------
									
									timeStepCounter++;
									toolBox.refreshConnections();
									//base.alert.display("time stamp 4");
									if(thisNode.isPlaced && thisNode.group==3)
									{
										//thisNode.addNeighboringDL();
										//thisNode.defaultColor=Color.GREEN;
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
								}
								break;
								case 5:
								{
									
									//----------------residue of previous timestamp--------------------------------
									if(thisNode.isPlaced && thisNode.group==3)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											for(int j=0;j<thisNode.unplacedNeighborList.size();j++)
											{
												float tempDist=toolBox.getDist(thisNode.unplacedNeighborList.get(j),thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i));						
												locNodeDistList.add(new LocNodeDist(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i),thisNode.unplacedNeighborList.get(j),tempDist,base));
											}
										}																				
									}
									
									toolBox.vSort(locNodeDistList);
									
									for(int i=0;i<locNodeDistList.size();i++)
									{
										Location loc=locNodeDistList.get(i).getLoc();
										VLabel node=locNodeDistList.get(i).getNode();
											if(loc.occupied==true || node.isPlaced==true)
											{
												continue;
											}
											else
											{
												Packet relocatePacketNadsPhase2=getRelocatePacketNadsPhase2();
												loc.occupied=true;
												node.isPlaced=true;
												node.group=loc.group;
												base.placedNodesCount=base.placedNodesCount+1;
												base.nodesPlacedNadsPh2_3++;
												base.nodesPlacedNadsPh2++;
												relocatePacketNadsPhase2.moveTo=loc;
												relocatePacketNadsPhase2.destination=node;
												
												//thisNode.nearestDesiredPoint=loc;
												float dist=toolBox.getDist(thisNode.getLoc(),loc);
												base.totalMovement=base.totalMovement+(dist/base.scale);
												base.totalMovementNadsPh2_3=base.totalMovementNadsPh2_3+(dist/base.scale);
												base.totalMovementNadsPh2=base.totalMovementNadsPh2+(dist/base.scale);
												
												node.buffer.add(relocatePacketNadsPhase2);												
											}										
									}
								//---------------------residue of previous timestamp--------------------
									
									timeStepCounter++;
									toolBox.refreshConnections();
									//base.alert.display("time stamp 5");
									if(thisNode.isPlaced && thisNode.group==4)
									{
										//thisNode.addNeighboringDL();
										//thisNode.defaultColor=Color.GREEN;
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
								}
								break;
								case 6:
								{
									
									//----------------residue of previous timestamp--------------------------------
									if(thisNode.isPlaced && thisNode.group==4)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											for(int j=0;j<thisNode.unplacedNeighborList.size();j++)
											{
												float tempDist=toolBox.getDist(thisNode.unplacedNeighborList.get(j),thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i));						
												locNodeDistList.add(new LocNodeDist(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i),thisNode.unplacedNeighborList.get(j),tempDist,base));
											}
										}																				
									}
									
									toolBox.vSort(locNodeDistList);
									
									for(int i=0;i<locNodeDistList.size();i++)
									{
										Location loc=locNodeDistList.get(i).getLoc();
										VLabel node=locNodeDistList.get(i).getNode();
											if(loc.occupied==true || node.isPlaced==true)
											{
												continue;
											}
											else
											{
												Packet relocatePacketNadsPhase2=getRelocatePacketNadsPhase2();
												loc.occupied=true;
												node.isPlaced=true;
												node.group=loc.group;
												base.placedNodesCount=base.placedNodesCount+1;
												base.nodesPlacedNadsPh2_4++;
												base.nodesPlacedNadsPh2++;
												relocatePacketNadsPhase2.moveTo=loc;
												relocatePacketNadsPhase2.destination=node;
												
												//thisNode.nearestDesiredPoint=loc;
												float dist=toolBox.getDist(thisNode.getLoc(),loc);
												base.totalMovement=base.totalMovement+(dist/base.scale);
												base.totalMovementNadsPh2_4=base.totalMovementNadsPh2_4+(dist/base.scale);
												base.totalMovementNadsPh2=base.totalMovementNadsPh2+(dist/base.scale);
												
												node.buffer.add(relocatePacketNadsPhase2);												
											}
									}
								//---------------------residue of previous timestamp--------------------
									
									timeStepCounter++;
									toolBox.refreshConnections();
									//base.alert.display("time stamp 6");
									if(thisNode.isPlaced && thisNode.group==5)
									{
										//thisNode.addNeighboringDL();
										//thisNode.defaultColor=Color.GREEN;
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
								}
								break;
								case 7:
								{
									
									//----------------residue of previous timestamp--------------------------------
									if(thisNode.isPlaced && thisNode.group==5)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											for(int j=0;j<thisNode.unplacedNeighborList.size();j++)
											{
												float tempDist=toolBox.getDist(thisNode.unplacedNeighborList.get(j),thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i));						
												locNodeDistList.add(new LocNodeDist(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i),thisNode.unplacedNeighborList.get(j),tempDist,base));
											}
										}																				
									}
									
									toolBox.vSort(locNodeDistList);
									
									for(int i=0;i<locNodeDistList.size();i++)
									{
										Location loc=locNodeDistList.get(i).getLoc();
										VLabel node=locNodeDistList.get(i).getNode();
											if(loc.occupied==true || node.isPlaced==true)
											{
												continue;
											}
											else
											{
												Packet relocatePacketNadsPhase2=getRelocatePacketNadsPhase2();
												loc.occupied=true;
												node.isPlaced=true;
												node.group=loc.group;
												base.placedNodesCount=base.placedNodesCount+1;
												base.nodesPlacedNadsPh2_5++;
												base.nodesPlacedNadsPh2++;
												relocatePacketNadsPhase2.moveTo=loc;
												relocatePacketNadsPhase2.destination=node;
												
												//thisNode.nearestDesiredPoint=loc;
												float dist=toolBox.getDist(thisNode.getLoc(),loc);
												base.totalMovement=base.totalMovement+(dist/base.scale);
												base.totalMovementNadsPh2_5=base.totalMovementNadsPh2_5+(dist/base.scale);
												base.totalMovementNadsPh2=base.totalMovementNadsPh2+(dist/base.scale);
												node.buffer.add(relocatePacketNadsPhase2);												
											}
									}
								//---------------------residue of previous timestamp--------------------
									
									timeStepCounter++;
									toolBox.refreshConnections();
									//base.alert.display("time stamp 7");
									if(thisNode.isPlaced && thisNode.group==6)
									{
										//thisNode.addNeighboringDL();
										//thisNode.defaultColor=Color.GREEN;
										BroadcastPacket(thisNode,packetNadsPhase2);										
									}
								}
								break;
								case 8:
								{
									
									//----------------residue of previous timestamp--------------------------------
									if(thisNode.isPlaced && thisNode.group==6)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											for(int j=0;j<thisNode.unplacedNeighborList.size();j++)
											{
												float tempDist=toolBox.getDist(thisNode.unplacedNeighborList.get(j),thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i));						
												locNodeDistList.add(new LocNodeDist(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i),thisNode.unplacedNeighborList.get(j),tempDist,base));
											}
										}																				
									}
									
									toolBox.vSort(locNodeDistList);
									
									for(int i=0;i<locNodeDistList.size();i++)
									{
										Location loc=locNodeDistList.get(i).getLoc();
										VLabel node=locNodeDistList.get(i).getNode();
											if(loc.occupied==true || node.isPlaced==true)
											{
												continue;
											}
											else
											{
												Packet relocatePacketNadsPhase2=getRelocatePacketNadsPhase2();
												loc.occupied=true;
												node.isPlaced=true;
												node.group=loc.group;
												base.placedNodesCount=base.placedNodesCount+1;
												base.nodesPlacedNadsPh2_6++;
												base.nodesPlacedNadsPh2++;
												relocatePacketNadsPhase2.moveTo=loc;
												relocatePacketNadsPhase2.destination=node;
												
												//thisNode.nearestDesiredPoint=loc;
												float dist=toolBox.getDist(thisNode.getLoc(),loc);
												base.totalMovement=base.totalMovement+(dist/base.scale);
												base.totalMovementNadsPh2_6=base.totalMovementNadsPh2_6+(dist/base.scale);
												base.totalMovementNadsPh2=base.totalMovementNadsPh2+(dist/base.scale);
												node.buffer.add(relocatePacketNadsPhase2);												
											}
									}
								//---------------------residue of previous timestamp--------------------
									
									timeStepCounter++;
									toolBox.refreshConnections();
									//base.alert.display("time stamp 8");
									if(thisNode.isPlaced && thisNode.group==7)
									{
										//thisNode.addNeighboringDL();
										//thisNode.defaultColor=Color.GREEN;
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
								}
								break;
								
								case 9:
								{
									//----------------residue of previous timestamp--------------------------------
									if(thisNode.isPlaced && thisNode.group==7)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											for(int j=0;j<thisNode.unplacedNeighborList.size();j++)
											{
												float tempDist=toolBox.getDist(thisNode.unplacedNeighborList.get(j),thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i));						
												locNodeDistList.add(new LocNodeDist(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i),thisNode.unplacedNeighborList.get(j),tempDist,base));
											}
										}																				
									}
									
									toolBox.vSort(locNodeDistList);
									
									for(int i=0;i<locNodeDistList.size();i++)
									{
										Location loc=locNodeDistList.get(i).getLoc();
										VLabel node=locNodeDistList.get(i).getNode();
											if(loc.occupied==true || node.isPlaced==true)
											{
												continue;
											}
											else
											{
												Packet relocatePacketNadsPhase2=getRelocatePacketNadsPhase2();
												loc.occupied=true;
												node.isPlaced=true;
												node.group=loc.group;
												base.placedNodesCount=base.placedNodesCount+1;
												base.nodesPlacedNadsPh2_7++;
												base.nodesPlacedNadsPh2++;
												relocatePacketNadsPhase2.moveTo=loc;
												relocatePacketNadsPhase2.destination=node;
												
												//thisNode.nearestDesiredPoint=loc;
												float dist=toolBox.getDist(thisNode.getLoc(),loc);
												base.totalMovement=base.totalMovement+(dist/base.scale);
												base.totalMovementNadsPh2_7=base.totalMovementNadsPh2_7+(dist/base.scale);
												base.totalMovementNadsPh2=base.totalMovementNadsPh2+(dist/base.scale);												
												node.buffer.add(relocatePacketNadsPhase2);												
											}
									}
								//---------------------residue of previous timestamp--------------------
									
									timeStepCounter++;
									toolBox.refreshConnections();						
								}
								break;
								
								case 10:
								{
									//System.exit(0);
									//base.alert.display("timestamp 10 executing");
									if(thisNode.isPlaced && thisNode.group==1)					
									{
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.removeAll(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList);
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList=(ArrayList<Location>)thisNode.nearestDesiredPoint.neighboringLocList.clone();
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
									timeStepCounter++;
								}
								break;
								
								case 11:
								{
									if(thisNode.isPlaced && thisNode.group==2)					
									{
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.removeAll(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList);
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList=(ArrayList<Location>)thisNode.nearestDesiredPoint.neighboringLocList.clone();
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
									timeStepCounter++;
									
								}
								break;
								
								case 12:
								{
									if(thisNode.isPlaced && thisNode.group==3)					
									{
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.removeAll(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList);
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList=(ArrayList<Location>)thisNode.nearestDesiredPoint.neighboringLocList.clone();
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
									timeStepCounter++;
								}
								break;
								case 13:
								{
									if(thisNode.isPlaced && thisNode.group==4)					
									{
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.removeAll(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList);
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList=(ArrayList<Location>)thisNode.nearestDesiredPoint.neighboringLocList.clone();
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
									timeStepCounter++;
								}
								break;
								
								case 14:
								{
									if(thisNode.isPlaced && thisNode.group==5)					
									{
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.removeAll(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList);
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList=(ArrayList<Location>)thisNode.nearestDesiredPoint.neighboringLocList.clone();
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
									timeStepCounter++;
								}
								break;
								
								case 15:
								{
									if(thisNode.isPlaced && thisNode.group==6)					
									{
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.removeAll(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList);
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList=(ArrayList<Location>)thisNode.nearestDesiredPoint.neighboringLocList.clone();
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
									timeStepCounter++;
								}
								break;
								
								case 16:
								{
									if(thisNode.isPlaced && thisNode.group==7)					
									{
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.removeAll(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList);
										thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList=(ArrayList<Location>)thisNode.nearestDesiredPoint.neighboringLocList.clone();
										BroadcastPacket(thisNode,packetNadsPhase2);
									}
									timeStepCounter++;
								}
								break;
								
								case 17:
								{
									if(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size()>0 && thisNode.group==1)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											String loc=thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).x+""+thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).y;																						
											Packet packet=getControlledFlooding_UDL_Packet(loc,base);																						
											toolBox.controlledFloodingNADSPhase3(thisNode,packet,base);
											thisNode.setSize(15, 15);
										}
									}
									timeStepCounter++;
								}
								break;
								
								case 18:
								{
									if(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size()>0 && thisNode.group==2)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											String loc=thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).x+""+thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).y;																						
											Packet packet=getControlledFlooding_UDL_Packet(loc,base);																						
											toolBox.controlledFloodingNADSPhase3(thisNode,packet,base);
											thisNode.setSize(15, 15);
										}
									}
									timeStepCounter++;
								}
								break;
								
								case 19:
								{
									if(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size()>0 && thisNode.group==3)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											String loc=thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).x+""+thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).y;																						
											Packet packet=getControlledFlooding_UDL_Packet(loc,base);																						
											toolBox.controlledFloodingNADSPhase3(thisNode,packet,base);
											thisNode.setSize(15, 15);
										}
									}
									timeStepCounter++;
								}
								break;
								
								case 20:
								{
									if(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size()>0 && thisNode.group==4)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											String loc=thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).x+""+thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).y;																						
											Packet packet=getControlledFlooding_UDL_Packet(loc,base);																						
											toolBox.controlledFloodingNADSPhase3(thisNode,packet,base);
											thisNode.setSize(15, 15);
										}
									}
									timeStepCounter++;
								}
								break;
								
								case 21:
								{
									if(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size()>0 && thisNode.group==5)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											String loc=thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).x+""+thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).y;																						
											Packet packet=getControlledFlooding_UDL_Packet(loc,base);																						
											toolBox.controlledFloodingNADSPhase3(thisNode,packet,base);
											thisNode.setSize(15, 15);
										}
									}
									timeStepCounter++;
								}
								break;
								
								case 22:
								{
									if(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size()>0 && thisNode.group==6)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											String loc=thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).x+""+thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).y;																						
											Packet packet=getControlledFlooding_UDL_Packet(loc,base);																						
											toolBox.controlledFloodingNADSPhase3(thisNode,packet,base);
											thisNode.setSize(15, 15);
										}
									}
									timeStepCounter++;
								}
								break;
								
								case 23:
								{
									if(thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size()>0 && thisNode.group==7)
									{
										for(int i=0;i<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();i++)
										{
											String loc=thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).x+""+thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(i).y;																						
											Packet packet=getControlledFlooding_UDL_Packet(loc,base);																						
											toolBox.controlledFloodingNADSPhase3(thisNode,packet,base);
											thisNode.setSize(15, 15);
										}
									}
									timeStepCounter++;
								}
								break;
								
								case 24:
								{
									thisNode.setSize(6,6);
									if(thisNode.isPlaced==false)
									{		
										Packet packet=getControlledFlooding_USN_Packet();
										thisNode.buffer.add(packet);
										for(int i=0;i<thisNode.neighborList.size();i++)
										{
											Packet pack=(Packet)packet.clone();
											thisNode.neighborList.get(i).buffer.add(packet);
										}
									}
									timeStepCounter++;
								}
								break;	
								
								case 25:
								{
									if(thisNode.isPlaced==false)
									{										
										for(int i=0;i<thisNode.receivedUnallocatedDlList.size();i++)
										{
											int intLoc=thisNode.receivedUnallocatedDlList.get(i);
											int x=intLoc/1000;
											int y=intLoc%1000;
											Location tempLoc=new Location(x,y,base);
											ArrayList <LocNodeDist> tempList=new ArrayList<LocNodeDist>();
											
											for(int j=0;j<thisNode.unplacedFriendSNList.size();j++)
											{
												VLabel unplacedFriend=thisNode.unplacedFriendSNList.get(j);
												float tempDist=toolBox.getDist(unplacedFriend, tempLoc);
												LocNodeDist tempLND=new LocNodeDist(tempLoc,unplacedFriend,tempDist,base);
												tempList.add(tempLND);
											}
											
											tempList=toolBox.vSort(tempList);
											
											
											//-----------------PRINTING THE SORTED--------------------------------- 
											
											System.out.println("----------------------------------------");
											System.out.println(thisNode.uniqueId+",    "+"Location:"+tempLoc.getXLoc()+","+tempLoc.getYLoc());
											for(int k=0;k<tempList.size();k++)
											{
												LocNodeDist a=tempList.get(k);
												System.out.println(a.getNode().uniqueId +","+a.getDist());
											}
											
											//---------------------------------------------------------------------
											
											try
											{
												LocNodeDist tempLND=tempList.get(0);
												if(tempLND.getNode()==thisNode)
												{
													Location finalLoc=tempLND.getLoc();
													thisNode.unplacedFriendSNList.remove(thisNode);
													float tempDist=tempLND.getDist();
													tempVar=tempDist;
														
													thisNode.isPlaced=true;
													base.nodesPlacedNadsPh3++;
													base.placedNodesCount++;
													base.totalMovement=base.totalMovement+(tempDist/base.scale);
													base.totalMovementNadsPh3=base.totalMovementNadsPh3+(tempDist/base.scale);
													//base.totalMovementNadsPh=base.totalMovementNadsPh2+(tempDist/base.scale);
																										
													toolBox.moveNode(thisNode,thisNode.getLoc(),finalLoc,20);
													break;
												}												
												else
												{
													thisNode.unplacedFriendSNList.remove(tempLND.getNode());
												}
											}
											catch(Exception e){e.printStackTrace();}											
										}																																																																																						
									}
									timeStepCounter++;
								}
								break;																
							}																												
					}						
					}
					
				//-------------------------------------------------------	
					//System.out.println("checking node working");
					if(buffer.size()>0)
					{
						thisNode.setBackground(Color.red);
						try
						{
						newPacket=buffer.poll();
						if(newPacket!=null)
						{
						newPacket.hopCount=newPacket.hopCount+1;
						newPacket.parentSequence.push(thisNode);
						int protocol=newPacket.protocolType;
						int hopCount=newPacket.hopCount;
						int ttl=newPacket.ttl;
						int sourceType=newPacket.sourceType;
						int queryType=newPacket.queryType;
						VLabel destNode=newPacket.destination;
						VLabel srcNode=newPacket.source;

			
						
						if(newPacket.parentSequence.size()==1 && queryType==base.GET_TEMP)
						{
							try
							{
								newPacket.pathStack=(Stack)thisNode.gradientStackPriorityQueue.get(0).clone();
							}
							catch(Exception e){ thisNode.setBackground(Color.GREEN);System.out.println(e + "as gradient stack is not available");}
						}
						
						//---------------------CODE FOR THE PROPAGATION OF INSPECTOR PACKET---------------
						
						if(newPacket.queryType==base.INSPECTOR)
						{
							Object root=newPacket.root;
							if(newPacket.movement==base.FORWARD)
							{								
								try
								{
									VLabel.class.cast(root).nodesReachable.add(thisNode);
									thisNode.setBackground(Color.GREEN);
								}catch(Exception e){}
								try
								{
									Actuator.class.cast(root).nodesReachable.add(thisNode);
									thisNode.setBackground(Color.GREEN);
								}catch(Exception e){}
								
								
								
								//thisNode.setBackground(Color.PINK);
																
								for(int i=0;i<thisNode.path_Tree_Routing_Table.size();i++)
								{
									if(root==thisNode.path_Tree_Routing_Table.get(i).root)
									{
										ArrayList <VLabel>children_List=thisNode.path_Tree_Routing_Table.get(i).childeren_List;
										if(children_List.size()==0)
										{
											//thisNode.setBackground(base.DARK_PINK);
										}
										for(int j=0;j<children_List.size();j++)
										{
											//if(children_List.get(j).isAlive && children_List.get(j).buffer.size()<children_List.get(j).defaultBufferSize)
											if(children_List.get(j).isAlive)
											{
												children_List.get(j).buffer.add(newPacket);
											}
											else
											{
										//		thisNode.setBackground(base.DARK_PINK);
											}
											
										}
										//base.alert.display("root node matched");		
									}
								}
							try
							{
								
							}
							catch(Exception e){}
							//base.alert.display("inspector packet found");						
						}
						else if(newPacket.movement==base.BACKWORD)
						{
							//base.alert.display("this packet is oriented backword");
							for(int i=0;i<thisNode.path_Tree_Routing_Table.size();i++)
							{
								Object tempRoot=thisNode.path_Tree_Routing_Table.get(i).root;
								if(newPacket.root==tempRoot)
								{
									//System.exit(0);
									thisNode.setBackground(Color.BLUE);
									VLabel tempParent=(VLabel)thisNode.path_Tree_Routing_Table.get(i).parent;
									tempParent.buffer.add(newPacket);
									tempParent.setBackground(Color.YELLOW);									
								}									
							}
						}																				
					}
						
						
						
						
						
						
						//-----------------------------------------------------------------------------------
					
						
						if(destNode==thisNode || destNode==base.BROADCAST_DEST)
						{
							
							switch(queryType)
							{
							
								case 0: 			//for queryType=base.REPLY
								{
														
									thisNode.packetQueue.add(newPacket);									
									
								}
								break;
								
								case 1:				//for queryType=base.SET_BASE_LOC 
								{						
									thisNode.baseStationId=(VLabel)newPacket.data;
									
									if(thisNode.commRange>=toolBox.getDist(thisNode,thisNode.baseStationId))
									{											
											Packet packet=(Packet)newPacket.clone();
											packet.source=thisNode;
											packet.destination=thisNode.baseStationId;
											packet.queryType=base.ADD_ME_AS_NEIGHBOR;
										
									
											packet.ttl=1;
											packet.hopCount=0;
											thisNode.setBackground(base.RED);
											//if(powerEnoughForTransmission())
											//{
												thisNode.baseStationId.buffer.add(packet);
												thisNode.isConnected=true;
											//}
												
											
									}
								}
								break;
								
								
								case 9:			//for queryType=base.MOVE 
								{
									//thisNode.baseStationId=(VLabel)newPacket.data;
										Location loc=new Location(thisNode.baseStationId.getX(),thisNode.baseStationId.getY(),base);
										Location nodeCurrentLoc=thisNode.getLoc();
										toolBox.move(thisNode,nodeCurrentLoc,loc,100);											
											
								}
								break;
								
								case 10:				//for queryType=base.SELECTED_MOVE 
								{
									String distRanges=(String)newPacket.data;
									String dist[]=distRanges.split(",");
									if(Float.parseFloat(dist[0])<=toolBox.getDist(thisNode,thisNode.baseStationId) && toolBox.getDist(thisNode,thisNode.baseStationId)<Float.parseFloat(dist[1]))
									{	
										Location loc=new Location(thisNode.baseStationId.getX(),thisNode.baseStationId.getY(),base);
										Location nodeCurrentLoc=thisNode.getLoc();
										toolBox.move(thisNode,nodeCurrentLoc,loc,100);
											
									}
								}
								break;
								
								case 11:				//for queryType=base.DUALRANGE_SELECTED_MOVE 
								{
									String distRanges=(String)newPacket.data;
									String dist[]=distRanges.split(",");
									ArrayList<Loc_Node>whereToMoveList=newPacket.locNodeList;
									if(toolBox.getDist(thisNode,thisNode.baseStationId)>Float.parseFloat(dist[0]) && toolBox.getDist(thisNode,thisNode.baseStationId)<=Float.parseFloat(dist[1]))
									{	
										//thisNode.setBackground(Color.GREEN);
										//thisNode.defaultColor=Color.GREEN;
										thisNode.isInRange=base.RANGE1;
										
										whereToMoveList=newPacket.locNodeList;
										Location loc=toolBox.getLoc(thisNode,whereToMoveList);
										thisNode.nearestDesiredPoint=loc;			
										if(loc!=null)
										{
											Location nodeCurrentLoc=thisNode.getLoc();
											thisNode.finalLocation=loc;
											if(!thisNode.isMoving)
											{
												thisNode.isMoving=true;
												toolBox.move(thisNode,nodeCurrentLoc,loc,5);
											}
										}
										
										else
										{
											System.out.println("LOCATION NOT FUNd line 209 vlABEL");
										}
											
									}
									
									else if(  toolBox.getDist(thisNode,thisNode.baseStationId)>Float.parseFloat(dist[2]) && toolBox.getDist(thisNode,thisNode.baseStationId)<=Float.parseFloat(dist[3]))
									{	
										whereToMoveList=newPacket.locNodeList;
										thisNode.setBackground(Color.PINK);
										thisNode.defaultColor=Color.pink;
										//if(newPacket.locNodeList.size()!=0)
										//{
											Location loc=toolBox.getLoc(thisNode,whereToMoveList);
											if(loc!=null)
											{
												Location nodeCurrentLoc=thisNode.getLoc();
												toolBox.move(thisNode,nodeCurrentLoc,loc,5);
											}
										
											else
											{
												System.out.println("LOCATION NOT FUNd line 209 vlABEL");
												float minDist=9999;
												//Location tempLocToMove=base.baseStationArray[0].getLoc();
												Location tempLocToMove=null;
												System.out.println(whereToMoveList.size());
												//System.exit(0);
												for(int i=0;i<whereToMoveList.size();i++)
												{
													if(toolBox.getDist(thisNode,whereToMoveList.get(i).getLoc())<minDist)
													{
														minDist=toolBox.getDist(thisNode,whereToMoveList.get(i).getLoc());
														tempLocToMove=whereToMoveList.get(i).getLoc();
													}
												}
												if(tempLocToMove!=null && toolBox.getDist(thisNode,tempLocToMove)<=base.commRangePerNode)
												{
													//thisNode.movable=false;
													thisNode.identified=true;
													
												}
											
												else
												{
													Location nodeCurrentLoc=thisNode.getLoc();
													toolBox.move(thisNode,nodeCurrentLoc,tempLocToMove,5);
												}
											}
										//}
										//else
										//{
										//		toolBox.move(thisNode,tempLocToMove,100);
												
										//}
											
										//toolBox.move(thisNode,loc,1000);
											
									}
										
								}
								break;
								
								
								case 4:				//for queryType=base.GET_TEMP
								{
									
									
									//thisNode.defaultColor=base.GREEN;
									newPacket.parentSequence.removeAll(newPacket.parentSequence);
									newPacket.data=base.centerPanel.coordinateMat[thisNode.getY()][thisNode.getX()].temperature;
									newPacket.queryType=base.REPLY;
									newPacket.sourceType=base.SENSORNODE;
									newPacket.source=thisNode;							
									/*newPacket.destination=base.baseStationArray[0];*/
									newPacket.destination=base.masterBSList.get(0);
									newPacket.ttl=13;
									newPacket.hopCount=0;
									newPacket.protocolType=base.activeImplementation;
									if(newPacket.protocolType==base.FLOODING)
									{
										algo.flooding(thisNode, newPacket);
									}
									if(newPacket.protocolType==base.DIRECTIONAL_FLOODING)
									{										
										algo.dirFlooding(thisNode, newPacket);	
									}
									
									if(newPacket.protocolType==base.DIR_DIFFUSION)
									{
										//System.exit(0);
										if(newPacket.pathStack.size()>0)
										{
											newPacket.pathStack.pop();
											algo.dirDiffusion(thisNode,newPacket);
										}
									}
									
								}
								break;
								
								case 7:					// for queryType=SET_GRADIENT;
								{
									//System.exit(0);
									//------------------MMMMMMMMMM---------------------------------
									Stack<VLabel> tempStack=(Stack<VLabel>)newPacket.parentSequence.clone();
									int i=0;

									while(gradientStackPriorityQueue.size()!=0 && i<gradientStackPriorityQueue.size() && gradientStackPriorityQueue.get(i).size()<tempStack.size())
									{
										i=i+1;
									}
									gradientStackPriorityQueue.add(i,tempStack);
									//-------------------------------------------------------------
									if(hopCount<ttl)
									{
										algo.flooding(thisNode, newPacket);
									}
									
									else
									{
										thisNode.setBackground(defaultColor);
									}
								}
								break;
								
								case 8:					//for queryType=ADD_ME_AS_NEIGHBOR;
								{
									thisNode.nodesConnectingBaseList.add(srcNode);
									srcNode.isConnected=true;										
								}
								break;
								
								case 15:			//for queryType=GOSSIP;
								{
									if(thisNode.packetIdList.indexOf(newPacket.identifier)<0)
									{
										thisNode.packetIdList.add(newPacket.identifier);
										System.out.println("cccccccccccccccccccccccccccccccc"+newPacket.identifier);
									//------------------MMMMMMMMMM---------------------------------
									Stack<VLabel> tempStack=(Stack<VLabel>)newPacket.parentSequence.clone();
									int i=0;
									//-------------------------------------------------------------
									//if(defaultHopCount>hopCount)
									//{
										//gradientStack=(Stack<VLabel>)newPacket.parentSequence.clone();	
										//defaultHopCount=hopCount;
									//}
									//--------------------MMMMMMMMMMM------------------------------
									while(gradientStackPriorityQueue.size()!=0 && i<gradientStackPriorityQueue.size() && gradientStackPriorityQueue.get(i).size()<tempStack.size())
									{
										i=i+1;
									}
									gradientStackPriorityQueue.add(i,tempStack);
									//-------------------------------------------------------------
									if(hopCount<ttl)
									{
										//System.exit(0);
										algo.gossiping(thisNode, newPacket);
									}
									
									else
									{
										thisNode.setBackground(defaultColor);
									}							
									}
									//----------------------------------------------------------
									
									//try{Thread.sleep(2000);}catch(Exception e){}																										
								}
								break;
								
								
								
								case 19:				//for queryType=base.SET_GROUP;
								{
									//System.exit(0);
									Runnable r1=new Runnable()							
									{
										public void run()
										{
											Loc_Dist tempLocDist=newPacket.loc_Dist;
											Location tempLoc=tempLocDist.getLoc();
											VLabel tempNode=newPacket.source;
											float dist=tempLocDist.getDistance();
											if(thisNode.nearestDesiredPoint==tempLoc)
											{
												groupMember_List.add(tempNode);
												
												if(iAmDeserving)
												{
													float myDist=toolBox.getDist(thisNode,thisNode.nearestDesiredPoint);
													if(myDist==dist)
													{
														//System.exit(0);
														tempNode.defaultColor=Color.red;
														if(tempNode.uniqueId<thisNode.uniqueId)
														{
															iAmDeserving=false;
														}
													}
													if(myDist>dist)
													{
														iAmDeserving=false;
													}
												}

											}
										}
									};
									thread1=new Thread(r1);
									thread1.start();
									

									
								}
								break;
								
								case 20:				//for queryType=base.SET_DESIRED_LOC;
								{
								//?? desired_Loc_List=(ArrayList<Location>)newPacket.desired_Loc_List.clone();																	
									//---------------CALCULATING THE MINUMUM DISTANCE FROM NODES----------
								//??	Location loc=thisNode.getLoc();
								//??	desired_Loc_List=toolBox.vSort(loc,desired_Loc_List);
								//?? base.tempCommRangePerNode=base.commRangePerNode;
								//?? base.commRangePerNode=(base.sensingRangePerNode*2)+10;
								//?? thisNode.commRange=(base.sensingRangePerNode*2)+10;
								//?? base.floorLinesShowable=false;									
									
									System.out.println("----------------------------------------------------------------------");
									System.out.println("--------------------NODE Id :"+thisNode.uniqueId+"--------------------");
									for(int i=0;i<desired_Loc_List.size();i++)
									{
										//System.out.println("("+desired_Loc_List.get(i).getXLoc()+", "+desired_Loc_List.get(i).getYLoc()+")    "+(int)toolBox.getDist(desired_Loc_List.get(i), loc));
									}
									System.out.println("----------------------------------------------------------------------");
									
									//------------------UPDATING THE NEIGHBOR LIST--------------------------
									toolBox.refreshConnections();
									//----------------------------------------------------------------------
/*									thisNode.nearestDesiredPoint=desired_Loc_List.get(0);
									Packet packet=new Packet(base);
									packet.queryType=base.SET_GROUP;
									packet.protocolType=base.BROADCAST;
									
									packet.source=thisNode;
									packet.destination=base.BROADCAST_DEST;
									packet.sourceType=base.SENSORNODE;
									packet.ttl=1;
									packet.hopCount=0;
									packet.identifier=7;
									packet.fromNode=thisNode;
									float dist=toolBox.getDist(thisNode,thisNode.nearestDesiredPoint);
									packet.loc_Dist=new Loc_Dist(thisNode.nearestDesiredPoint,dist,base);
									
									for(int i=0;i<thisNode.neighborList.size();i++)
									{
										thisNode.neighborList.get(i).buffer.add(packet);										
									}*/

								}
								break;
								
								case 21:		//for queryType: base.STOP_MOVING;
								{
									//System.exit(0);
									thisNode.movable=false;
									if(thisNode.isPlaced==false)
									{
										try{Thread.sleep(9000);}catch(Exception e){}
										thisNode.movable=true;
										Location loc=thisNode.getLoc();
										counter=counter+1;
										Location moveTo=desired_Loc_List.get(counter);
										nearestLoc=moveTo;
										thisNode.nearestDesiredPoint=moveTo;
										float dist=toolBox.getDist(loc,moveTo);
										//try{Thread.sleep(9000);}catch(Exception e){}										
										toolBox.moveNode(thisNode,loc, moveTo,(int)(5*dist/(counter)));
									}
								}
								break;
								
								/*case 22:               // DHDS2_PHASE2
								{
									//System.exit(0);
									if(thisNode.isPlaced)
									{
										desired_Loc_List=(ArrayList<Location>)newPacket.desired_Loc_List.clone();																	
										//---------------CALCULATING THE MINUMUM DISTANCE FROM NODES----------
										Location loc=thisNode.getLoc();
										desired_Loc_List=toolBox.vSort(loc,desired_Loc_List);									
										if(toolBox.cmpLocApprox(loc,desired_Loc_List.get(0)))
										{
											toolBox.updateNeighboringLocList(thisNode);
											thisNode.setBackground(Color.RED);
											
											//-----------------TRIAL CODE-----------------------------------
											
												for(int k=0;k<thisNode.neighborList.size();k++)											
												{
													VLabel tempNeighbor=thisNode.neighborList.get(k);
													if(!tempNeighbor.isPlaced && tempNeighbor.nodeType==base.SENSORNODE)
													{
														tempNeighbor.setBackground(Color.CYAN);
														if(thisNode.neighbouringLocList.size()>0)
														{
															//System.exit(0);
															Location tempLoc=thisNode.neighbouringLocList.get(0);
															thisNode.neighbouringLocList.remove(0);
															thisNode.setBackground(Color.yellow);
															tempNeighbor.isPlaced=true;
															base.placedNodeList.add(tempNeighbor);
															base.placedNodesCount=base.placedNodesCount+1;
															float distToMove=toolBox.getDist(tempNeighbor,tempLoc);
															base.totalMovement=base.totalMovement+distToMove;
															toolBox.moveNode(tempNeighbor,tempNeighbor.getLoc(),tempLoc,5);												
														}
													}
												}
											
											ArrayList<VLabel>unplacedNeighborList=new ArrayList<VLabel>();								
											for(int k=0;k<thisNode.neighborList.size();k++)
											{
												VLabel tempNeighbor=thisNode.neighborList.get(k);
												if(!tempNeighbor.isPlaced)
												{
													unplacedNeighborList.add(tempNeighbor);
												}												
											}
											System.out.println("neighboring nodes :"+unplacedNeighborList.size());
											System.out.println("empty locations :"+ thisNode.neighbouringLocList);
						
											
											ArrayList<Loc_Node>locNodeList=base.dhds2.getLocNodeTable(unplacedNeighborList,thisNode.neighbouringLocList);
											
											System.exit(0);
											
											for(int l=0;l<locNodeList.size();l++)
											{
												Loc_Node tempLocNode=locNodeList.get(l);
												VLabel tempNode=tempLocNode.getNode();
												Location tempLoc=tempLocNode.getLoc();																	
												toolBox.moveNode(tempNode,tempNode.getLoc(),tempLoc,5);
												
											}
											
												//--------------------------------------------------------------
											
										}
	
									}	
								}
								break;
									*/	
								
								case 23:
								{
									int side=Integer.parseInt((newPacket.data).toString());														
									thisNode.subDivisionNumber=toolBox.getNodeSubDivision(thisNode,side,base);
									thisNode.clusterNeighborList.removeAll(thisNode.clusterNeighborList);
									try
									{Thread.sleep(5000);}catch(Exception e){}
									for(int i=0;i<base.masterNodeList.size();i++)
									{
										VLabel tempNode=base.masterNodeList.get(i);
										if(tempNode!=thisNode && tempNode.subDivisionNumber==thisNode.subDivisionNumber)
										{
											thisNode.clusterNeighborList.add(tempNode);
										}
									}
								}
								break;
						
								
								case 24:  // SET_DATA
								{
									dataSt=dataSt+","+newPacket.data.toString();
								}
								break;
								
								case 25:  // GET_DATA
								{
									VLabel tempCH=thisNode;
									Packet packet=new Packet(base);
									packet.queryType=base.SEND_AGGRIGATED_DATA;
									packet.data=tempCH.dataSt;
									packet.protocolType=base.BROADCAST;
									
									packet.source=tempCH;
									//	packet.destination=base.nodeArray[counter];
									packet.destination=base.masterBSList.get(0);
									packet.sourceType=base.SENSORNODE;
									packet.ttl=1;
									packet.hopCount=0;
									packet.identifier=7;
									packet.fromNode=tempCH;				
									//	base.baseProperties.showConnection();
									base.masterBSList.get(0).buffer.add(packet);
									counter=counter+1;
									base.sendFrom=tempCH.getLoc();
									base.sendTo=base.masterBSList.get(0).getLoc();
									tempCH.setBackground(Color.GREEN);
									
									/*toolBox.sendAggrigatedDataDirectlyToBS(0);*/
								}
								break;
								
								case 26:  // GET_DATA
								{
									dataRecievedFromCHList.add(newPacket.fromNode);
								}
								break;
								
								case 28:  // 
								{
									replyPacketNadsPhase2.destination=newPacket.source;
									newPacket.source.buffer.add(replyPacketNadsPhase2);
									
								}
								break;
								
								case 29:
								{
									thisNode.defaultColor=Color.BLACK;
									if(newPacket.source.isPlaced==false)
									{
										unplacedNeighborList.add(newPacket.source);
									}
									else
									{
										Location tempLoc=newPacket.source.getLoc();
										for(int k=0;k<thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.size();k++)
										{
											Location loc=thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(k);
											if(loc.equals(tempLoc,8))
											{
												thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.remove(k);
												break;
											}
										}
									}
									
									
									
								}
								break;
								
								case 30:		//RELOCATE_NADS_PHASE2
								{
									//System.exit(0);
									Location movementLoc=newPacket.moveTo;
									if(newPacket.moveTo==null)
									{
										movementLoc=new Location(0,0,base);
									}
									thisNode.nearestDesiredPoint=movementLoc;
									Location fromLoc=thisNode.getLoc();
									float dist=toolBox.getDist(fromLoc,movementLoc);																	
									toolBox.moveNode(thisNode,fromLoc,movementLoc,20);			
								}
								break;
								
							
								case 31:			//SET_GRADIENT_NADS
								{
									VLabel sourceNode=newPacket.source;
									int unallocatedLoc=Integer.parseInt(newPacket.data.toString());
									Node n=new Node(unallocatedLoc);
									if(bst31.insertNode(n))
									{
										if(thisNode.isPlaced==false)
										{																						
												thisNode.parentSequenceStack=(Stack)newPacket.parentSequenceNADS.clone();												
												thisNode.receivedUnallocatedDlList.add(unallocatedLoc);
												thisNode.setSize(15,15);						
										}
										else if(thisNode.isPlaced)
										{
											for(int i=0;i<thisNode.neighborList.size();i++)
											{
												Packet pack=(Packet)newPacket.clone();
												pack.fromNode=thisNode;
												pack.parentSequenceNADS=(Stack)newPacket.parentSequenceNADS.clone();
												pack.parentSequenceNADS.push(thisNode);
												thisNode.neighborList.get(i).buffer.add(pack);												
											}
										}
									}
								}
								break;
								case 32:
								{
									//System.exit(0);
									VLabel sourceNode=newPacket.source;
									int uniqueId=sourceNode.uniqueId;
									Node n=new Node(uniqueId);
									if(bst32.insertNode(n))
									{
										if(thisNode.isPlaced==false)
										{
											thisNode.unplacedFriendSNList.add(sourceNode);
										}
										
										for(int i=0;i<thisNode.neighborList.size();i++)
										{
											Packet pack=(Packet)newPacket.clone();
											pack.fromNode=thisNode;
											thisNode.neighborList.get(i).buffer.add(pack);											
										}
									}
									
									
								}
								break;
								
/*								case 32:			//REPLY_DD_NADS_PHASE3
								{
									//System.exit(0);
									thisNode.setSize(25,25);
									if(!thisNode.reserved)
									{
										newPacket.fromNode=thisNode;
										thisNode.reserved=true;
										if(newPacket.destinationId==thisNode.uniqueId)
										{
											//System.exit(0);
											base.global_counter++;
											//base.alert.display("GLOBAL COUNTER:"+base.global_counter);
											thisNode.reserved=true;
											Location loc=thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.get(0);
											thisNode.nearestDesiredPoint.neighboringUnoccupiedLocList.remove(0);
											
											newPacket.queryType=base.MOVE_THROUGH_RESERVED_NADS_PHASE3;
											newPacket.fromNode=thisNode;
											VLabel vl=newPacket.childrenSequenceNADS.pop();
											vl.buffer.add(newPacket);
											
											thisNode.childrenSequenceStack=(Stack)newPacket.childrenSequenceNADS.clone();
											
											toolBox.moveNode(thisNode,thisNode.getLoc(),loc,100);
										}
										else
										{
											VLabel tempNode=newPacket.parentSequenceNADS.pop();
											newPacket.toNode=tempNode;
											newPacket.childrenSequenceNADS.push(thisNode);
											
											
											tempNode.setSize(25,25);
											tempNode.reserved=true;
											tempNode.buffer.add(newPacket);
										}
										
									}
								}
								break;*/
								
								case 33:      //MOVE_THROUGH_RESERVED_NADS_PHASE3
								{
									Location moveToLoc=newPacket.fromNode.nearestDesiredPoint;
									toolBox.moveNode(thisNode,thisNode.getLoc(),moveToLoc,100);
									newPacket.fromNode=thisNode;
									VLabel vl=newPacket.childrenSequenceNADS.pop();
									vl.buffer.add(newPacket);
									
									
								}
								break;
								case 27:  // GET_DATA
								{
									VLabel nextClusterHead;
									try
									{
										nextClusterHead=toolBox.getNextClusterHead(thisNode,newPacket.finalDestination).clusterHead;
										if(nextClusterHead!=null)
										{
											Packet packet=(Packet)newPacket.clone();
											packet.source=thisNode;
											packet.destination=nextClusterHead;
											nextClusterHead.setBackground(Color.GREEN);
											//nextClusterHead.setBounds(nextClusterHead.getX(), nextClusterHead.getY(),20,20);										
											nextClusterHead.buffer.add(packet);
										}
									}
									catch(Exception e)
									{	
										Packet packet=(Packet)newPacket.clone();
										nextClusterHead=newPacket.finalDestination;
										packet.source=thisNode;
										packet.destination=nextClusterHead;
										nextClusterHead.setBackground(Color.GREEN);
										//nextClusterHead.setBounds(nextClusterHead.getX(), nextClusterHead.getY(),20,20);										
										nextClusterHead.buffer.add(packet);
									}
								}
								break;
								
							}
						}
						else
						{
							switch(protocol)
							{
								case 0:
								{
								
								}
								break;
								case 1:
								{
									if(newPacket.ttl>newPacket.hopCount)
									{
										algo.flooding(thisNode,newPacket);
									}
									else
									{
										thisNode.setBackground(defaultColor);
									}
								}
								break;
								
								case 2:
								{
									if(newPacket.ttl>newPacket.hopCount)
									{
										algo.dirFlooding(thisNode,newPacket);
									}
									else
									{
										thisNode.setBackground(defaultColor);
									}
								}
								break;
								
								case 3:
								{
									if(newPacket.ttl>newPacket.hopCount && newPacket.pathStack.size()>0)
									{
										algo.dirDiffusion(thisNode,newPacket);
									}
									else
									{
										thisNode.setBackground(defaultColor);
									}
								}
								break;
								
								
								case 5:
								{
									if(newPacket.ttl>newPacket.hopCount)
									{
										//System.exit(0);
										algo.xyz(thisNode,newPacket);
									}
									else
									{
									
										thisNode.setBackground(defaultColor);
									}
								}
								break;
								
								case 6:
								{
									if(newPacket.ttl>newPacket.hopCount && thisNode.packetIdList.indexOf(newPacket.identifier)<0)
									{
										System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCC"+newPacket.identifier);
										thisNode.packetIdList.add(newPacket.identifier);
										algo.gossiping(thisNode,newPacket);
									}
									else
									{
										thisNode.setBackground(defaultColor);
									}
								}
								break;
								
							}
						}
						}
						}
						catch(Exception e){
							e.printStackTrace();
							//base.alert.display("MMMMMMMMMMMMMMMMMMMMMMMMM");
							}
					}
						
					
					else
					{
						if(thisNode.reserved)
						{
							thisNode.defaultColor=Color.MAGENTA;
						}
						else if(thisNode.isPlaced)
						{
							thisNode.defaultColor=Color.GREEN; //??
						}
						else
						{
							thisNode.defaultColor=Color.BLUE; //??
						}
						thisNode.setBackground(thisNode.defaultColor);
					}
					
					try{Thread.sleep(100);}catch(Exception e){}
					
					
				}
			}
		};
		communicationThread=new Thread(r1);
		communicationThread.start();
		
		//-------------------------CHECKING FOR AFFECTED REGION--------------------
		Runnable r2=new Runnable()
		{
			public void run()
			{
				while(true)
				{
					try
					{
						int x=thisNode.getX();
						int y=thisNode.getY();
						
						if(base.centerPanel.coordinateMat[x][y].affected==true)
						{
							toolBox.killNode(thisNode); 
						}
					}catch(Exception e){}
					
					try{Thread.sleep(2500);}catch(Exception e){}
				}
			}
		};
		new Thread(r2).start();
		
		
//***************************************************************************
//---------STOPPING THE SENSING THREAD AS IT IS NOT OUR CURRENT REQUIREMENT**
//sensing=false;// STOPPED --> REMOVE THIS LINE TO START AGAIN		
		
		Runnable r=new Runnable()
		{
			public void run()
			{
				while(power>0 && sensing)
				{
					int x=thisNode.getX();
					int y=thisNode.getY();
					//if(buffer.size()>0){thisNode.setBackground(base.YELLOW);}
					for(int i=0;i<2;i++)
					{
						for(int j=0;j<2;j++)
						{
							if(base.centerPanel.coordinateMat[y+i][x+j].temperature>60)
								{
									sourceNode=true;
									//thisVLabel.lineColor=Color.ORANGE;
									base.sourceNode=true;
									base.whoIsSource=thisVLabel;
									base.whoIsSink=thisNode.baseStationId;
									base.sinkNode=true;									
									toolBox.runAlgo(thisNode,20);
								
									//power=1;
									sensing=false;
								}
						}
					}
					try{Thread.sleep(2000);}catch(Exception e){e.printStackTrace();}
				}
			}
		};
		new Thread(r).start();
		
		
		
		this.setFocusable(true);
		//*************************ADDING LISTENERS*****************************
		this.addFocusListener
		(
			new FocusAdapter()
			{
				public void focusLost(FocusEvent fe)
				{
					((VLabel)fe.getSource()).setBackground(defaultColor);
				}
			}
		);
		
		
		this.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseReleased(MouseEvent me)
					{
						System.out.println("mouse have been released.. no action to be taken");
					}
				}
		);
		//----------------------------------------------------------------------
				
	}
	
	
	//*********************IMPLEMENOTING INTERFACES**************************
	
	 public int compare(VLabel obj1,VLabel obj2) {
		  Float p1 = toolBox.getDist(obj1,obj1.baseStationId);
		  Float p2 = toolBox.getDist(obj2,obj2.baseStationId);

		  if (p1 > p2 ){
		   return 1;
		  }
		  else if (p1 < p2){
		   return -1;
		  }
		  else
		   return 0;
		 }
	//-----------------------------------------------------------------------
	 
	 public boolean powerEnoughForTransmission()
	 {
		 if(this.power<criticalEnergyLevel && this.power>20)
		 {
			 //System.exit(0);
			 thisNode.defaultColor=base.BROWN;
			 thisNode.setBackground(this.defaultColor);
			 //--------FORMING INTIMATION PACKET TO INTIMATE ITS ENERGY STATUS TO BS
			 Packet packet=new Packet(base);
			 Object data;
				ArrayList<Loc_Node>locNodeList=new ArrayList<Loc_Node>();
				packet.protocolType=base.DIR_DIFFUSION;
				packet.queryType=base.CRITICAL_ENERGY_LEVEL;
				packet.ttl=15;
				packet.identifier=8;
				packet.hopCount=0;
				packet.sourceType=base.SENSORNODE;
				packet.source=this;
				//packet.destination=base.baseStationArray[0];
				packet.destination=base.masterBSList.get(0);
				packet.toNode=(VLabel)this.gradientStackPriorityQueue.get(0).peek();
				packet.fromNode=this;
				//---------------------------------------------
				Actuator tempAct=base.actuatorList.get(0);
				if(!serviceRequestSent)
				{
					for(int i=0;i<base.actuatorList.size();i++)
					{
						int distFromTempActuator=(int)toolBox.getDist(tempAct.getLoc(),thisNode.getLoc());
						int distFromActuator=(int)toolBox.getDist(base.actuatorList.get(i).getLoc(),thisNode.getLoc());
						if(base.actuatorList.get(i).distanceToTravel+distFromActuator<tempAct.distanceToTravel+distFromTempActuator)
						{
							tempAct=base.actuatorList.get(i);
						}
					}
					int tempDist=(int)toolBox.getDist(tempAct.getLoc(),thisNode.getLoc());
					Node_Dist tempNodeDist=new Node_Dist(thisNode,tempDist,base);
					serviceRequestSent=true;
					tempAct.distanceToTravel=tempAct.distanceToTravel+tempDist;
					tempAct.servicePriorityQueue.add(tempNodeDist);
				}
				//toolBox.moveActuator(tempAct,tempAct.getLoc(),this.getLoc(),20);
				//---------------------------------------------				
				//return true;			 
		 }
		 
		 if(thisNode.power<=20)
		 {
			thisNode.defaultColor=Color.RED;
			thisNode.setBackground(this.defaultColor);
			//return true;
		 }
		 
		 if(this.power==0)
		 {
			 thisNode.defaultColor=new Color(220,220,220);
			// toolBox.killNode(thisNode); 
		 }
		 
		 thisNode.power=thisNode.power-20;
		 if(thisNode.power>0)
			 return true;
		 else
		 {
			 System.out.println(thisNode.uniqueId+":   node is dead");
			 if(base.activeImplementation==1)
				 base.powerScale1=base.powerScale1-4;
			 if(base.activeImplementation==2)
				 base.powerScale2=base.powerScale2-4;
			 if(base.activeImplementation==3)
				 base.powerScale3=base.powerScale3-4;
			 
			// this.setBackground(base.PINK);
			//this.defaultColor=base.PINK;
			// System.exit(0);
			 //return false;
			 return true;
		 }
	 }
	
	//*************************FUNCTIONS*********************************
	 
	 //******************************************************************
	 //--FUNCTION TO GET THE LOCATION OF THE NODE------------------------
	 
	Location getLoc()
	{
		Location loc=new Location(thisNode.getX(),thisNode.getY(),base);
		return loc;
	}
	 
	 
	int getNodeType()
	{
		return this.nodeType;
	}
	
	//--------FUNCTION TO STOP ALL THE THREADS---------------------------
	
	void stopAllThreads()
	{
		working=false;
		sensing=false;
	}
	//-------------------------------------------------------------------
	
	//******FUNCTION TO UPDATE LOCATION**********************************
	public void updateLocation(Location newLoc)
	{
		int x=newLoc.x;
		int y=newLoc.y;
		
		int width=this.getWidth();
		int height=this.getHeight();
		
		this.setBounds(x, y, width, height);
	}
	
	//------------------------------------------------------------------
	public void updateLocation(VCoordinate newLoc)
	{
		int x=newLoc.x;
		int y=newLoc.y;
		
		int width=this.getWidth();
		int height=this.getHeight();
		
		this.setBounds(x, y, width, height);
	}
	
	//###################################################################
	
	 void BroadcastPacket(VLabel sourceNode, Packet packet)
	 {
		 VLabel sN=sourceNode;
		 toolBox.refreshConnections();
		 for(int i=0;i<sN.neighborList.size();i++)
		 {
			 //sN.neighborList.get(i).defaultColor=Color.RED;
			 sN.neighborList.get(i).buffer.add(packet);
		 }
	 }
	
/*	 void addNeighboringDL()
	 {
		 float rs=base.sensingRangePerNode;									
			float rd=(float)Math.sqrt(3)*rs;
			int x=this.nearestDesiredPoint.x;
			int y=this.nearestDesiredPoint.y;
			
			Location tempLoc2=new Location(x+(int)(1.5*rs),y+(int)(rd/2),2,base);
			Location tempLoc3=new Location(x+(int)(1.5*rs),y-(int)(rd/2),3,base);
			Location tempLoc4=new Location(x,y-(int)rd,4,base);
			Location tempLoc5=new Location(x-(int)(1.5*rs),y-(int)(rd/2),5,base);
			Location tempLoc6=new Location(x-(int)(1.5*rs),y+(int)(rd/2),6,base);
			Location tempLoc7=new Location(x,y+(int)rd,7,base);
			
			if(toolBox.liesWithinCR(tempLoc2, 0))
			{
				this.neighbouringDlList.add(tempLoc2);
			}
			
			if(toolBox.liesWithinCR(tempLoc3, 0))
			{
				this.neighbouringDlList.add(tempLoc3);
			}
			
			if(toolBox.liesWithinCR(tempLoc4, 0))
			{
				this.neighbouringDlList.add(tempLoc4);
			}
			
			if(toolBox.liesWithinCR(tempLoc5, 0))
			{
				this.neighbouringDlList.add(tempLoc5);
			}
			
			if(toolBox.liesWithinCR(tempLoc6, 0))
			{
				this.neighbouringDlList.add(tempLoc6);
			}
			
			if(toolBox.liesWithinCR(tempLoc7, 0))
			{
				this.neighbouringDlList.add(tempLoc7);
			}
	 }*/
	
	
/*	void setScanningCoordinates()
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				xScanningList.add(xPos+i);
				yScanningList.add(yPos+j);
			}
		}
		System.out.println(xScanningList);
		System.out.println(yScanningList);
	}
	*/
	//--------------------------------------------------------------------
	
	/*public int getX()
	{
		return (this.getX()-3);
	}
	
	public int getY()
	{
		return (this.getY()-3);
	}*/
	 
	 
	//-------------------GET NEW REPLY PACKET FOR NADS PHASE 2-----------
	 Packet getRelocatePacketNadsPhase2()
	 {
		 Packet relocatePacket=new Packet(base);
		 relocatePacket.queryType=base.RELOCATE_NADS_PHASE2;
		 relocatePacket.protocolType=base.BROADCAST;
			
		 relocatePacket.source=thisNode;
			//replyPacketNadsPhase2.destination=base.BROADCAST_DEST;
		 relocatePacket.sourceType=base.SENSORNODE;
		 relocatePacket.ttl=1;
		 relocatePacket.hopCount=0;
		 relocatePacket.identifier=7;
		 relocatePacket.fromNode=thisNode;
			//float dist=toolBox.getDist(thisNode,thisNode.nearestDesiredPoint);
			//packetNadsPhase2.loc_Dist=new Loc_Dist(thisNode.nearestDesiredPoint,dist,base);
		 return relocatePacket;
	 }
	 
	 //------------------------------------------------------------------
	 
	 //---------FUNCTION TO RETURN THE PACKET TO THE WAY IT CAME FROM----
/*	 public void returnPacketNADSPhase3(Packet packet)
	 {
		this.setSize(15,15);											
		Packet pack=(Packet)packet.clone();
		pack.parentSequenceNADS=(Stack)packet.parentSequenceNADS.clone();
		pack.fromNode=this;
		pack.destinationId=pack.source.uniqueId;
		//pack.source=this;
		pack.queryType=base.REPLY_DD_NADS__PHASE3;	
		
		//parentSequenceStack=pack.parentSequenceNADS;
		//pack.parentSequenceNADS.pop();
		VLabel tempNode=pack.parentSequenceNADS.pop();
		if(tempNode.reserved==false)
		{		
			pack.childrenSequenceNADS.push(this);
			tempNode.flushBuffer();
			tempNode.buffer.add(pack);
		}
	 }
	 */
	 //------------------------------------------------------------------
	 
	 //-----------------FUNCATION TO FLUSH BUFFER------------------------
	 void flushBuffer()
	 {
		 thisNode.buffer.removeAll(thisNode.buffer);
	 }
	 //------------------------------------------------------------------
	 
	 //--------------FUNCTION TO MAKE CONTROLLED FLOODING UDL PACKET---------
	 public Packet getControlledFlooding_UDL_Packet(String loc,Base base)
	 {
		Packet packet=new Packet(base);
		
		packet.data=loc;
		
		packet.identifier=2;
		packet.sourceType=base.SENSORNODE;
		packet.source=thisNode;
		//packet.fromNode=node;
		packet.protocolType=base.CONTROLLED_FLOODING;
		packet.queryType=base.CONTROLLED_FLOODING_UDL_NADS;  //32
		packet.ttl=13;
		packet.hopCount=0;
		packet.destination=base.BROADCAST_DEST;
		packet.parentSequenceNADS.push(thisNode);
		
		return packet;
	 }
	 //------------------------------------------------------------------
	 
	 //--------------FUNCTION TO MAKE CONTROLLED FLOODING USN PACKET---------
	 public Packet getControlledFlooding_USN_Packet()
	 {
		Packet packet=new Packet(base);
		
		//packet.data=loc;
		
		packet.identifier=2;
		packet.sourceType=base.SENSORNODE;
		packet.source=this;
		//packet.fromNode=node;
		packet.protocolType=base.CONTROLLED_FLOODING;
		packet.queryType=base.CONTROLLED_FLOODING_USN_NADS;  //32
		packet.ttl=13;
		packet.hopCount=0;
		packet.destination=base.BROADCAST_DEST;
		packet.parentSequenceNADS.push(thisNode);
		
		return packet;
	 }
	 //------------------------------------------------------------------

}
