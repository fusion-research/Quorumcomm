package home;
import java .awt.*;
import javax.swing.*;
import java.awt.event.*;
public class VDaOptionDialog extends JDialog 
{
	Base base;
	JList optionList;
	VToolBox toolBox;
	static String option1[]={"refresh","redeploy","clear","lock obstacle","unlock obstacle","stop event","show gradient","MMU broadcast","cluster head select","-----","mark path","test","start leach","cluster_multihop_comm","deployment location","preview","flush_node_buffer","reset","properties"};
	//int x,y;

	//final MouseEvent mouseEvent;

	VDaOptionDialog(Base b)
	{
		super(b);
		//ow=this;
		setUndecorated(true);
		base=b;
		//this.mouseEvent=mouseEvent;
		toolBox=new VToolBox(base);
		
			optionList=new JList(option1);
		
		
		optionList.setSelectedIndex(4);
		optionList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		optionList.setBackground(new Color(245,245,245));
		

		
		optionList.addMouseListener
		(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent me)
					{
						
						if(((JList)me.getSource()).getSelectedValue()=="preview")
						{
							int x=base.locationClipBoard.getXLoc();
							int y=base.locationClipBoard.getYLoc();
						    VDaPreviewDialog vDaPreviewDialog=new VDaPreviewDialog(new Location(x,y,base),base);
						    vDaPreviewDialog.setBounds(200,200,300,400);
						    vDaPreviewDialog.setVisible(true);
						}
												
						
						if(((JList)me.getSource()).getSelectedValue()=="flush_node_buffer")
						{
							for(int i=0;i<base.masterNodeList.size();i++)
							{
								base.masterNodeList.get(i).buffer.removeAll(base.masterNodeList.get(i).buffer);
							}
						}
						
						
						if(((JList)me.getSource()).getSelectedValue()=="reset")
						{
							for(int i=0;i<base.masterNodeList.size();i++)
							{
								base.masterNodeList.get(i).setSize(6,6);
							}
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="properties")
						{
							DaPropertiesDialog pd=new DaPropertiesDialog(base);
							pd.setBounds(200,200,400,400);
							pd.setVisible(true);
						}
						
						//TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
						
						if(((JList)me.getSource()).getSelectedValue()=="test")
						{
							Runnable r=new Runnable()
							{
								public void run()
								{									
										toolBox.getNearestClusterHead();
										try
										{
											toolBox.removeConnections();
										}
										catch(Exception e){}
										base.clusterConnShowable=true;
								}
							};
							new Thread(r).start();
						}
						
						
						
						if(((JList)me.getSource()).getSelectedValue()=="start leach")
						{
							Runnable r=new Runnable()
							{
								public void run()
								{
									for(int j=0;j<1000;j++)
									for(int i=0;i<base.masterNodeList.size();i++)
									{								
										if(base.masterNodeList.get(i).power>0)
										{
											base.tempNode=base.masterNodeList.get(i);
											int dist=(int)toolBox.getDist(base.masterNodeList.get(i), base.masterBSList.get(0));
											base.masterNodeList.get(i).power=base.masterNodeList.get(i).power-(dist/50);
										}
										else
										{
											base.masterNodeList.get(i).defaultColor=Color.RED;
											base.masterNodeList.get(i).setBackground(Color.RED);
										}
										
										try{Thread.sleep(10);}catch(Exception e){}
									}
								}
							};
							new Thread(r).start();
						}
						
												
						//TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
						
						
						if(((JList)me.getSource()).getSelectedValue()=="cluster head select")
						{
							toolBox.selectRandomClusterHead(10);
						}
						
						
						if(((JList)me.getSource()).getSelectedValue()=="clear")
						{
							Runnable r=new Runnable()
							{
								public void run()
								{
									base.clearDa();
								}
							};
							new Thread(r).start();
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="redeploy")
						{
							Runnable r=new Runnable()
							{
								public void run()
								{
							
									base.clearDa();
									base.masterNodeList.removeAll(base.masterNodeList);//REMOVE ALL THE PREVIOUS ENTRIES FROM THE MASTER LIST
									base.centerPanel.spreadTemperature(100,100,600,600);
									base.toolKitDialog.propertiesDialog.deployNodes();
								}
							};
							new Thread(r).start();
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="mark path")
						{
							Runnable r=new Runnable()
							{
								public void run()
								{
									Location tempLoc=base.clipBoardLoc;
									Location loc=new Location(tempLoc.getXLoc(),tempLoc.getYLoc(),base);
									base.pathList.add(loc);
								}
							};
							new Thread(r).start();
						}
						
						
						
						if(((JList)me.getSource()).getSelectedValue()=="refresh")
						{
								toolBox.refreshConnections();
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="lock obstacle")
						{
							Runnable r=new Runnable()
							{
								public void run()
								{
									//------MARKING AREA OF OBSTACLE-----------------------------
									updateObstacles();
									//-----------------------------------------------------------
								}
							};
							new Thread(r).start();
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="unlock obstacle")
						{
							Runnable r=new Runnable()
							{
								public void run()
								{
									clearObstacles();
									for(int i=0;i<base.obstacleCount;i++)
									{
										base.obstacleArray[i].add(base.obstacleArray[i].resizeLb);
										//base.centerPanel.add(base.obstacleArray[i]);
										
									}
									base.centerPanel.revalidate();
									base.centerPanel.repaint();
								}
							};
							new Thread(r).start(); 			
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="stop event")
						{
							Runnable r=new Runnable()
							{
								public void run()
								{
									base.shouldGenerateEvents=false;
								}
							};
							new Thread(r).start(); 			
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="show gradient");
						{
							Runnable r2=new Runnable()
							{
								public void run()
								{
									//System.exit(0);
									for(int i=0;i<base.masterNodeList.size();i++)
									{
										System.out.println("\n NODE ID :"+base.masterNodeList.get(i).uniqueId);
/*										for(int j=0;j<base.masterNodeList.get(i).gradientStack.size();j++)
										{
											System.out.print(base.masterNodeList.get(i).gradientStack.get(j).uniqueId+",");
										}*/
									}
									//System.exit(0);
								}
							};
							new Thread(r2).start(); 			
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="MMU broadcast")
						{			
							broadcastMMU_FS();
						}
						
						if(((JList)me.getSource()).getSelectedValue()=="cluster_multihop_comm")
						{			
							//edsRound();
							toolBox.startEDSRoundsWithMultihop(100);
						}
												

						
						if(((JList)me.getSource()).getSelectedValue()=="deployment location")
						{	
							//base.desiredLocShowable=true;
							//Location tempLoc=new Location(me.getX(),me.getY(),base);
							if(base.clipBoardLoc!=null)
							{
								base.candidateLoc=base.clipBoardLoc;
								for(int i=0;i<base.masterBSList.size();i++)
								{
									VLabel tempBS=base.masterBSList.get(i);
									float tempDist=toolBox.getDist(tempBS,base.clipBoardLoc);									
									base.clipBoardLoc.distFromBSList.add(tempDist);
									
									System.out.println("dist :"+tempDist);
									//System.exit(0);
								}
							}
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
	
	//*************************************************************************************
	void updateObstacles()
	{
		clearObstacles();
		
		for(int i=0;i<base.obstacleCount;i++)
		{
			int startX=base.obstacleArray[i].getX();
			int startY=base.obstacleArray[i].getY();
			int x=base.obstacleArray[i].getWidth();
			int y=base.obstacleArray[i].getHeight();
			for(int j=startY;j<=startY+y;j++)
			{
				for(int k=startX;k<=startX+x;k++)
				{
					base.centerPanel.coordinateMat[k][j].obstacle=true;
					Location tempLoc=new Location(k,j,base);
					base.tempList.add(tempLoc);
				}
			}
			
			base.obstacleArray[i].remove(base.obstacleArray[i].resizeLb);
			//base.centerPanel.remove(base.obstacleArray[i]);
		}	
		
		
	}
	
	
	void clearObstacles()
	{
		base.tempList.removeAll(base.tempList);
		for(int y=100;y<600;y++)
		{
			for(int x=100;x<600;x++)
			{
				base.centerPanel.coordinateMat[y][x].obstacle=false;
			}
		}
	}
	
	//---------------------MMU BROADCAST FUNCTION-----------------------------------------
	void broadcastMMU_FS()
	{
	for(int i=0;i<base.masterNodeList.size();i++)
	{
		if(toolBox.getDist(base.actuatorList.get(0),base.masterNodeList.get(i))<=base.commRangePerActuator)
		{
			base.masterNodeList.get(i).setBackground(Color.RED);
		}
	}
	
	for(int i=0;i<base.masterNodeList.size();i++)
	{
		for(int j=0;j<base.actuatorList.size();j++)
		{
			if(toolBox.getDist(base.actuatorList.get(j),base.masterNodeList.get(i))<=base.commRangePerNode)
			{
				base.masterNodeList.get(i).clusterHead=base.actuatorList.get(j);
				base.actuatorList.get(j).nodesDirectlyConnectedActuator.add(base.masterNodeList.get(i));
				base.masterNodeList.get(i).setBackground(Color.GREEN);
			}
		}
	}
	}
	//------------------------------------------------------------------------------------
	
	//--------------FUNCTION FOR ALL THE CLUSTER HEADS TO TRANSMIT DATA-------------------
	
	void edsRoundMultihop()
	{
		Runnable r=new Runnable()
		{
			public void run()
			{
				for(int i=0;i<base.clusterHeadList.size();i++)
				{
					VLabel tempCH=base.clusterHeadList.get(i);
					base.vNodeOptionDialog.sendClusterDataToBSbyMultiHop(tempCH);
					//try{Thread.sleep(10000);}catch(Exception e){}
					
					//base.alert.display("round:"+round);
				}
			}
		};
		new Thread(r).start();
	}
	
	//------------------------------------------------------------------------------------
	
	void edsRoundDirectComm()
	{
		Runnable r=new Runnable()
		{
			public void run()
			{
				//for(int i=0;i<base.clusterHeadList.size();i++)
				//{
					//VLabel tempCH=base.clusterHeadList.get(i);
					//base.vNodeOptionDialog.sendClusterDataToBSbyMultiHop(tempCH);
					//try{Thread.sleep(10000);}catch(Exception e){}
					toolBox.sendAggrigatedDataDirectlyToBS();
					//base.alert.display("round:"+round);
				//}
			}
		};
		new Thread(r).start();
	}
	
	//*************************************************************************************
	

	
	public static void main(String args[])
	{
		//VOptionWindow ov=new VOptionWindow();
	}

}
