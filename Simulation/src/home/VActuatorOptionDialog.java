package home;
import java .awt.*;
import java.util.*;
import java.util.Queue;

import javax.swing.*;

import java.awt.event.*;
import java.util.Stack;
public class VActuatorOptionDialog extends JDialog 
{
	Base base;
	JList optionList;
	static String option1[]={"move","pick","drop","charge","path tree","nodes reachable","------------","inspection","preview","master pcd"};
	VToolBox toolBox;
	//final MouseEvent mouseEvent;
	Actuator thisActuator;
	Thread pathTreeTh;
	Thread inspectionTh;
VActuatorOptionDialog(Actuator actuator)
{
	this.thisActuator=actuator;	
}
VActuatorOptionDialog(Base b,final MouseEvent mouseEvent)
	{
	super(b);
	base=b;
	setUndecorated(true);
	toolBox=new VToolBox(base);
		//ow=this;
	//	this.mouseEvent=mouseEvent;
		base=b;
		//this.mouseEvent=mouseEvent;
		optionList=new JList(option1);
		//optionList.setSelectedIndex(4);
		optionList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		optionList.setBackground(new Color(245,245,245));
		

		optionList.addMouseListener
		(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent me)
					{
						thisActuator=(Actuator)mouseEvent.getSource();
						if(((JList)me.getSource()).getSelectedValue()=="move")
						{
							Actuator tempActuator=(Actuator)mouseEvent.getSource();
							Location fromLoc=tempActuator.getLoc();
							Location toLoc=base.masterNodeList.get(0).getLoc();
							toolBox.moveActuator(tempActuator,base.masterNodeList.get(0),base.ROAMING,fromLoc,toLoc,20);
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="pick")
						{
							Actuator tempActuator=(Actuator)mouseEvent.getSource();
							tempActuator.pick(base.masterNodeList.get(0));
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="drop")
						{
							Actuator tempActuator=(Actuator)mouseEvent.getSource();
							tempActuator.drop();
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="charge")
						{
							Actuator tempActuator=(Actuator)mouseEvent.getSource();
							tempActuator.charge(base.masterNodeList.get(0));
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="master pcd")
						{
							Master_PCD_Diaplay_Dialog mpcd=new Master_PCD_Diaplay_Dialog(base,thisActuator);
							mpcd.setSize(800,500);
							mpcd.setVisible(true);
						}
						
						

						if(((JList)me.getSource()).getSelectedValue()=="path tree")
						{
							/*Runnable r=new Runnable()
							{
								public void run()
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
							*/

							thisActuator.createPathTree();
						}
															
						
						
						if(((JList)me.getSource()).getSelectedValue()=="nodes reachable")
						{
							int reachableNodeCount=thisActuator.nodesReachable.size();
							base.alert.display("nodes reachable :"+reachableNodeCount);
						}
						
						
						if(((JList)me.getSource()).getSelectedValue()=="inspection")
						{		
							thisActuator.inspect();
							/*Runnable r=new Runnable()
							{
								public void run()
								{							
									Packet packet=new Packet(base);
									packet.queryType=base.INSPECTOR;
									packet.root=thisActuator;
							
									for(int i=0;i<thisActuator.nodesDirectlyConnectedActuator.size();i++)
									{
										if(thisActuator.nodesDirectlyConnectedActuator.get(i).buffer.size()<thisActuator.nodesDirectlyConnectedActuator.get(i).defaultBufferSize)
										{
											thisActuator.nodesDirectlyConnectedActuator.get(i).buffer.add(packet);
										}
									}
									//try{Thread.sleep(10000);}catch(Exception e){}
								}
							};
							inspectionTh=new Thread(r);
							inspectionTh.start();*/
							
							/*Runnable r1=new Runnable()
							{
								int counter=0;
								public void run()
								{
									try
									{										
										inspectionTh.join();										
										Iterator<VLabel>it=thisActuator.nodesReachable.iterator();
										//System.exit(0);
										base.alert.display("WORKING TESTED");
										while(it.hasNext())
										{
											it.next().setBackground(base.DARK_PINK);
											counter=counter+1;
											
											//System.out.println(it.next().uniqueId);
											base.alert.display(""+counter);
											Thread.sleep(5);
										}
										//System.exit(0);
									
									}
									catch(Exception e){}
								}
							};
							new Thread(r1).start();	*/						
							
						}
						
						
						if(((JList)me.getSource()).getSelectedValue()=="preview")
						{
							ActuatorPreviewDialog apd=new ActuatorPreviewDialog((Actuator)mouseEvent.getSource(),base);
							apd.setBounds(200,100,400,300);
							apd.setVisible(true);
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
	


	
	//------------------------------------------------------------------------------
	
	



	
	//-----------------------------------------------------------------------
	public static void main(String args[])
	{
		VActuatorOptionDialog ov=new VActuatorOptionDialog(null,null);
		ov.setBounds(10,10,100,150);
		ov.setVisible(true);
	}

}
