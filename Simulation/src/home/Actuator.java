package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.Queue;
public class Actuator extends JPanel
{
	Base base;
	int uniqueId;
	float commRange;
	float sensingRange;
	int defaultBufferSize=50;
	int power=9999999;
	
	int distanceToTravel=0;
	float distMoved=0;
	Color defaultColor;
	boolean movable=true;
	boolean moving=false;
	boolean working=true;
	boolean traversed_for_tree=false;
	boolean isAlive=true;
	boolean isPlaced=false;
	
	
	Actuator thisActuator;
	VToolBox toolBox;
	Queue<Node_Dist>servicePriorityQueue=new LinkedList<Node_Dist>();
	Queue<Packet>buffer=new LinkedList<Packet>();
	ArrayList<VLabel>nodesCarriedList=new ArrayList<VLabel>();
	ArrayList<VLabel>nodesDirectlyConnectedActuator=new ArrayList<VLabel>();
	
	ArrayList<VLabel>pathTreeNeighborList=new ArrayList<VLabel>();
	
	//-----------------------STORING TREE RECORDS FOR BASE STATION-----------------------------
	ArrayList<Parent_Child_Dist >master_Pcd_List=new ArrayList<Parent_Child_Dist>();
	ArrayList<VLabel> nodesReachable = new ArrayList<VLabel>();
	Thread pathTreeTh;
	//------------------------------------------------------------------------------------------
	
	Actuator(Base b,Color color)
	{
		this.base=b;
		thisActuator=this;
		this.setLayout(null);
		this.setBackground(color);
		toolBox=new VToolBox(base);
		defaultColor=color;
		
		
		//t11.fun(this);
		
		//----------------STARTING THE  SERVICE REQUEST BUFFER READING THREAD---------------
		Runnable r=new Runnable()
		{
			public void run()
			{
				while(power>0 && working)
				{
					try{Thread.sleep(1000);}catch(Exception e){}
					if(servicePriorityQueue.size()>0 && !thisActuator.moving)
					{
						System.out.println("PRIORITY QUEUE");
						Node_Dist tempNodeDist=servicePriorityQueue.poll();
						if(tempNodeDist!=null)
						{
							int tempDist=tempNodeDist.getDist();
							thisActuator.distanceToTravel=thisActuator.distanceToTravel-tempDist;
							VLabel tempNode=tempNodeDist.getNode();
							
							thisActuator.moving=true;
							toolBox.moveActuator(thisActuator,tempNode,base.CHARGING,thisActuator.getLoc(),tempNode.getLoc(),20);
						}
					}
				}
			}
		};
		new Thread(r).start();
		//-----------------------------------------------------------------
		
		//------------STARTING PACKET BUFFER READING THREAD----------------

		Runnable r1=new Runnable()
		{
			public void run()
			{
				while(power>0 && working)
				{
					if(buffer.size()>0)
					{
						Packet newPacket=buffer.poll();
						newPacket.hopCount=newPacket.hopCount+1;
						int protocol=newPacket.protocolType;
						int hopCount=newPacket.hopCount;
						int ttl=newPacket.ttl;
						int sourceType=newPacket.sourceType;
						int queryType=newPacket.queryType;
						VLabel destNode=newPacket.destination;
						
						Object data=newPacket.data;
						
						
						if(destNode==base.BROADCAST_DEST)
						{							
							switch(queryType)
							{
								
							}
						}
						
					}
					try{Thread.sleep(1000);}catch(Exception e){}
					
				}
			}
		};
		new Thread(r1).start();
		//-----------------------------------------------------------------
		
		
		//---------STARTING THREAD TO SENSE AFFECTED AREA --------------
		Runnable r2=new Runnable()
		{
			public void run()
			{
				while(true)
				{
					int x=thisActuator.getX();
					int y=thisActuator.getY();
					
					if(base.centerPanel.coordinateMat[x][y].affected==true)
					{
						toolBox.killActuator(thisActuator); 
						thisActuator.setBackground(Color.red);
						thisActuator.defaultColor=Color.RED;
						//base.actuatorList.remove(thisActuator);
						base.centerPanel.remove(thisActuator);
					}
					try{Thread.sleep(2500);}catch(Exception e){}
				}
			}
		};
		new Thread(r2).start();
		//--------------------------------------------------------------
		
	}
	//***************FUNATIONS OF ACTUATOR****************************
	
	void pick(VLabel node)
	{
		node.setBounds(0,0,2,2);
		nodesCarriedList.add(node);
		thisActuator.add(node);
		base.centerPanel.remove(node);
		base.centerPanel.revalidate();
		thisActuator.revalidate();
	}
	
	void drop()
	{
		if(thisActuator.nodesCarriedList.size()>0)
		{
			int x=thisActuator.getX();
			int y=thisActuator.getY();
			VLabel sensor=thisActuator.nodesCarriedList.get(0);
			thisActuator.nodesCarriedList.remove(0);
			thisActuator.remove(sensor);
			sensor.setBounds(x, y,15,15);
			base.centerPanel.add(sensor);
			base.centerPanel.revalidate();
			thisActuator.revalidate();
		}
		
	}
	
	void charge( final VLabel node)
	{
		/*Runnable r=new Runnable()
		{
			public void run()
			{
		toolBox.moveActuator(thisActuator,node,thisActuator.getLoc(), node.getLoc(),20);
		//System.exit(0);
		try
		{
			Thread.sleep(10000);
		}
		catch(Exception e){e.printStackTrace();}
		toolBox.moveActuator(thisActuator,thisActuator.getLoc(),base.masterNodeList.get(1).getLoc(),20);
		
		try
		{
			Thread.sleep(10000);
		}
		catch(Exception e){e.printStackTrace();}
		toolBox.moveActuator(thisActuator,thisActuator.getLoc(),base.masterNodeList.get(2).getLoc(),20);
			}
		};
		new Thread(r).start();*/
	}
	
	//----------------------------------------------------------------
	Location getLoc()
	{
		return new Location(thisActuator.getX(),thisActuator.getY(),base);
	}
	
	
	//---------------------FUNCTION TO CREATE PATH TREE-----------------------------

	void createPathTree()
	{
		Runnable r=new Runnable()
		{
			public void run()
			{	
				try
				{
					toolBox.initNodeState();		
					thisActuator.master_Pcd_List.removeAll(thisActuator.master_Pcd_List);
					System.out.println("number of neighbours :"+ thisActuator.nodesDirectlyConnectedActuator.size());									
					Queue <VLabel>tempQueue=new LinkedList<VLabel>();
					thisActuator.traversed_for_tree=true;
				//	System.exit(0);	
					toolBox.removeConnections();									
					for(int i=0;i<thisActuator.nodesDirectlyConnectedActuator.size();i++)
					{
						thisActuator.pathTreeNeighborList.add(thisActuator.nodesDirectlyConnectedActuator.get(i));					
						tempQueue.add(thisActuator.nodesDirectlyConnectedActuator.get(i));
						float dist=toolBox.getDist(thisActuator,thisActuator.nodesDirectlyConnectedActuator.get(i));
						Parent_Child_Dist temp_Pcd=new Parent_Child_Dist(thisActuator,thisActuator.nodesDirectlyConnectedActuator.get(i),dist,base); 
						thisActuator.master_Pcd_List.add(temp_Pcd);
						thisActuator.nodesDirectlyConnectedActuator.get(i).traversed_for_tree=true;
					}
					
				//	------------ADDING PARENT AND ROOT TO ROUTING PATH----------------------
					for(int i=0;i<thisActuator.master_Pcd_List.size();i++)
					{
						VLabel tempChild=thisActuator.master_Pcd_List.get(i).child;
						Object rt=thisActuator;
						Object p=thisActuator;
						Root_Parent_Children rpc=new Root_Parent_Children(rt,p);
						tempChild.path_Tree_Routing_Table.add(rpc);
					}
				//	------------------------------------------------------------------------
					
					toolBox.createPathTree(thisActuator,tempQueue);
				}catch(Exception e){}
			}			
		};
		pathTreeTh =new Thread(r);
		pathTreeTh.start();
		Runnable r1=new Runnable()
		{								
			public void run()
			{							
				try
				{
					pathTreeTh.join();
					//base.alert.display("active node");
					toolBox.completePathTreeTable(thisActuator,thisActuator.master_Pcd_List);
				}
				catch(Exception e){}
			}
		};
		new Thread(r1).start();
		

	}
	//--------------------------------------------------------------------------------------------------------
	
	
	//--------------FUNCTION TO PERFORM INSPECTION BY INJECTION INSPECTOR PACKETS-----------------------------
	void inspect()
	{
		Runnable r=new Runnable()
		{
			public void run()
			{
				thisActuator.nodesReachable.removeAll(thisActuator.nodesReachable);
				Packet packet=new Packet(base);
				packet.queryType=base.INSPECTOR;
				packet.root=thisActuator;
				
				for(int i=0;i<thisActuator.nodesDirectlyConnectedActuator.size();i++)
				{
					//if(thisActuator.nodesDirectlyConnectedActuator.get(i).buffer.size()<thisActuator.nodesDirectlyConnectedActuator.get(i).defaultBufferSize)
					//{
						thisActuator.nodesDirectlyConnectedActuator.get(i).buffer.add(packet);
					//}
				}
			}
		};
		new Thread(r).start();
	
	}
	//--------------------------------------------------------------------------------------------------------
	
	public static void main(String args[])
	{
		Actuator act=new Actuator(null,Color.ORANGE);
		
		
	}

}
