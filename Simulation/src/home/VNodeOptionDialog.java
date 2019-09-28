package home;
import java .awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;
public class VNodeOptionDialog extends JDialog 
{
	Base base;
	JList optionList;
	static String option1[]={"source node","sink node","preview","path_tree_routing_table","report to root","show group","-----","gradient path","properties","test","test1","test2","multi hop cluster comm"};
	static String option2[]={"sink node","preview","path_tree_routing_table","report to root","show group","-----","gradient path","properties","test","test1","test2","multi hop cluster comm"};
	static String option3[]={"source node","preview","path_tree_routing_table","report to root","show group","-----","gradient path","properties","test","test1","test2","multi hop cluster comm"};
	static String option4[]={"preview","path_tree_routing_table","report to root","show group","-----","gradient path","properties","test","test1","test2","multi hop cluster comm"};
	final MouseEvent mouseEvent;
	VLabel thisNode;
	VToolBox vToolBox;
	VNodeOptionDialog(Base b,final MouseEvent mouseEvent)
	{
		super(b);
		//ow=this;
		setUndecorated(true);
		
		vToolBox=new VToolBox(base);
		this.mouseEvent=mouseEvent;
		base=b;
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
						thisNode=(VLabel)mouseEvent.getSource();
						
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
												
						
						if(((JList)me.getSource()).getSelectedValue()=="path_tree_routing_table")
						{
							/*String tempSt="------";
							try
							{
							tempSt="parent id :"+VLabel.class.cast(thisNode.path_Tree_Routing_Table.get(0).parent).uniqueId+"  root id :"+VLabel.class.cast(thisNode.path_Tree_Routing_Table.get(0).root).uniqueId+"  children :"+thisNode.path_Tree_Routing_Table.get(0).childeren_List.size();
							}catch(Exception e){}
							try
							{
								tempSt="parent id :"+VLabel.class.cast(thisNode.path_Tree_Routing_Table.get(0).parent).uniqueId+"  root id :"+Actuator.class.cast(thisNode.path_Tree_Routing_Table.get(0).root).uniqueId+"  children :"+thisNode.path_Tree_Routing_Table.get(0).childeren_List.size();
							}catch(Exception e){}
							base.alert.display(tempSt);*/
							
							PathTreeTableDisplay pttd=new PathTreeTableDisplay(base,thisNode);
							pttd.setSize(800,500);
							pttd.setVisible(true);
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="show group")
						{
							System.out.println("group members:"+ thisNode.groupMember_List.size());		
							for(int i=0;i<thisNode.groupMember_List.size();i++)
							{
								thisNode.groupMember_List.get(i).setBackground(Color.RED);
							}
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="report to root")
						{
							Packet packet=new Packet(base);
							packet.lastNode=thisNode;
							packet.root=base.masterBSList.get(0);
							packet.queryType=base.INSPECTOR;
							packet.movement=base.BACKWORD;
							for(int i=0;i<thisNode.path_Tree_Routing_Table.size();i++)
							{
								Object tempRoot=thisNode.path_Tree_Routing_Table.get(i).root;
								if(packet.root==tempRoot)
								{
									//System.exit(0);
									thisNode.setBackground(Color.BLUE);
									VLabel tempParent=(VLabel)thisNode.path_Tree_Routing_Table.get(i).parent;
									tempParent.buffer.add(packet);
									tempParent.setBackground(Color.YELLOW);									
								}									
							}
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="gradient path")
						{
							base.connectionBreaker(base.masterNodeList);
							VLabel tempLb=((VLabel)mouseEvent.getSource());
							//Stack tempStack=(Stack)(tempLb.gradientStack.clone());
							//for(int i=0;i<tempLb.gradientStackPriorityQueue.size();i++)
							//{
								Stack tempStack=(Stack)(tempLb.gradientStackPriorityQueue.get(0).clone());
								VLabel thisLb=(VLabel)tempStack.pop();
								System.out.println(tempStack.size());
								while(tempStack.size()>0)
								{
									VLabel a= (VLabel)tempStack.pop();
									int pos=thisLb.neighborList.indexOf(a);
									thisLb.connectionList.remove(pos);
									thisLb.connectionList.add(pos,true);
									thisLb=a;
								}
							//}
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="test")
						{
							VLabel tempNode=((VLabel)mouseEvent.getSource());
							tempNode.setBackground(Color.CYAN);				
							try{base.connectionBreaker(base.masterNodeList);}catch(Exception e){}
							//vToolBox.controlledFloodingNADSPhase3(tempNode,base);
							//tempNode.BroadcastPacket(tempNode,tempNode.packetNadsPhase2);
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="test1")
						{
							VLabel tempNode=((VLabel)mouseEvent.getSource());
							for(int i=0;i<thisNode.masterNodeDistlist.size();i++)
							{
								ArrayList <LocNodeDist> tempList=thisNode.masterNodeDistlist.get(i);
								
								for(int j=0;j<tempList.size();j++)
								{
									int nodeId=tempList.get(j).getNode().uniqueId;
									Location tempLoc=tempList.get(j).getLoc();
									float dist=tempList.get(j).getDist();
									
									System.out.println(nodeId+",  "+tempLoc.getXLoc()+","+tempLoc.getYLoc()+", "+ dist);
								}
								System.out.println("-----------------------------------------");
							}
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="test2")
						{
							VLabel tempNode=((VLabel)mouseEvent.getSource());
							base.alert.display("test2 has been clicked"+tempNode.childrenSequenceStack.size());
							vToolBox.resetNodes();
							while(tempNode.childrenSequenceStack.size()>0)
							{
								VLabel node=tempNode.childrenSequenceStack.pop();
								try{Thread.sleep(1000);}catch(Exception e){}
								node.setSize(25,25);
							}
						}
						
						
						
						if(((JList)me.getSource()).getSelectedValue()=="multi hop cluster comm")
						{
								sendClusterDataToBSbyMultiHop((VLabel)mouseEvent.getSource());
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
					//System.out.println("focus have been lost");
					dispose();
				}
			}
		);
		
		//this.setVisible(true);
		
	}
	
	//------------------------------------------------------------------------
public void sendClusterDataToBSbyMultiHop(VLabel tempNode)
{
	VLabel tempCH=tempNode;//((VLabel)mouseEvent.getSource());
	int bsSelect=tempCH.commCounter%2;
	Cluster nextCluster=vToolBox.getNextClusterHead(tempCH,base.masterBSList.get(bsSelect));
	if(nextCluster!=null)
	{
		tempCH.commCounter=tempCH.commCounter+1;
		VLabel nextClusterHead=nextCluster.clusterHead;
		Packet packet=new Packet(base);
		packet.queryType=base.SEND_AGGREGATED_DATA_MULTIHOP;
		packet.data="I LOVE YOU";
		packet.protocolType=base.BROADCAST;
		packet.finalDestination=base.masterBSList.get(bsSelect);
	
		packet.source=tempCH;
	//	packet.destination=base.nodeArray[counter];
		packet.destination=nextClusterHead;
		packet.sourceType=base.SENSORNODE;
		packet.ttl=1;
		packet.hopCount=0;
		packet.identifier=7;
		packet.fromNode=tempCH;				
		nextClusterHead.buffer.add(packet);										
		nextClusterHead.setBackground(Color.GREEN);
		//nextClusterHead.setBounds(nextClusterHead.getX(), nextClusterHead.getY(),20, 20);
	}
	
	//vToolBox.nextClusterHead(tempCH);
}
	
	//-----------------------------------------------------------------------
	public static void main(String args[])
	{
		//VOptionWindow ov=new VOptionWindow();
	}

}
