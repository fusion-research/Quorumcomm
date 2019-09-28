package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class VInfoDialog extends JDialog implements ActionListener
{
	Base base;
	
	JTabbedPane viewTp;
	JLabel rcLb,rsLb;
	JLabel totalDesiredLocLb,availableDesiredLocLb;
	JLabel totalNodesLb,availableNodesLb,reserveNodesLb;
	JLabel nodesDirConnectingBSLb;
	JLabel totalMovementLb,avgMovementLb,maxActMovementLb;
	JLabel percentageCoverageLb;
	JLabel deployerTotalMovementLb;
	JLabel deployerDroppingMovementLb;
	
	JLabel actuatorTotalMovementLb;
	JLabel actuatorAvgMovementLb;
	
	JLabel averageConnectivityLb;
	JLabel averageErrorLb;
	
	JPanel centerPanel;
	JButton saveBt;
	JButton cancelBt;
	VToolBox vToolBox;
	int index;
	float maxMovement;
	VInfoDialog(Base b)
	{
		super(b);
		base=b;
		vToolBox=new VToolBox(base);
		this.setLayout(new BorderLayout());
		this.setBounds(800,80,400,650);
		this.setVisible(true);
		JPanel northPanel=new JPanel();
		northPanel.setBackground(Color.WHITE);
		
		centerPanel=new JPanel();
		centerPanel=new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new GridLayout(20,1));
		rcLb=						new JLabel("communication range (rc)-------------:"+base.commRangePerNode);
		rsLb=                       new JLabel("sensing range (rs)-------------------:"+base.sensingRangePerNode);
		totalDesiredLocLb=		    new JLabel("total desired loc--------------------: "+base.desiredLocList.size());
		availableDesiredLocLb=	    new JLabel("available desired loc----------------: "+base.availDesiredLocListForNodes.size());//?????
		totalNodesLb=               new JLabel("total nodes--------------------------: "+base.masterNodeList.size());
		availableNodesLb=		    new JLabel("available nodes----------------------: _______");
		/*try{availableNodesLb.setText(          "available nodes----------------------: "+base.baseStationArray[0].activeNodeList.size());}catch(Exception e){}*/
		try{availableNodesLb.setText(          "available nodes----------------------: "+base.masterBSList.get(0).activeNodeList.size());}catch(Exception e){}
		reserveNodesLb=				new JLabel("reserve nodes------------------------: "+base.spareList.size());
		nodesDirConnectingBSLb=     new JLabel("nodes dir connecting base station----: _______");
		/*try{nodesDirConnectingBSLb.setText    ("nodes dir connecting base station----: "+base.baseStationArray[0].nodesConnectingBaseList.size());}catch(Exception e){}*/
		try{nodesDirConnectingBSLb.setText    ("nodes dir connecting base station----: "+base.masterBSList.get(0).nodesConnectingBaseList.size());}catch(Exception e){}
		totalMovementLb=            new JLabel("total movement-----------------------: "+base.totalMovement);
		avgMovementLb=              new JLabel("average movement---------------------: "+base.avgMovement);
		percentageCoverageLb=       new JLabel("percentage coverage------------------: "+base.percentageCoverage);
		averageConnectivityLb=      new JLabel("Average connectivity-----------------: "+base.averageConnectivity);
		deployerTotalMovementLb=    new JLabel("total deployer movement--------------: "+base.deployerTotalMovement);
		deployerDroppingMovementLb= new JLabel("dropping movement--------------: "+base.deployerDroppingMovement);
		averageErrorLb=new JLabel("average error ------------------:"+base.totalError/base.masterNodeList.size());
		/*
		actuatorTotalMovementLb=	new JLabel("total actuator movement--------------: "+base.totalActuatorMovement);
		actuatorAvgMovementLb=		new JLabel("average actuator movement------------: "+base.avgActuatorMovement);
		Collections.sort(base.actuatorMovementList);
		index=base.actuatorMovementList.size()-1;
		maxMovement=base.actuatorMovementList.get(index);
		maxActMovementLb=			new JLabel("maximum actuator movement------------: "+maxMovement);
		//totalDesiredLocLb1=new JLabel("----");
		*/
		
		saveBt=new JButton("save");
		saveBt.addActionListener(this);
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);
		
		centerPanel.add(totalDesiredLocLb);
		centerPanel.add(availableDesiredLocLb);
		centerPanel.add(totalNodesLb);
		centerPanel.add(availableNodesLb);
		centerPanel.add(reserveNodesLb);
		centerPanel.add(nodesDirConnectingBSLb);
		centerPanel.add(totalMovementLb);
		centerPanel.add(avgMovementLb);
		centerPanel.add(averageConnectivityLb);
		centerPanel.add(percentageCoverageLb);
		centerPanel.add(deployerTotalMovementLb);
		centerPanel.add(deployerDroppingMovementLb);
		centerPanel.add(averageErrorLb);
				
		//centerPanel.add(actuatorTotalMovementLb);
		//centerPanel.add(actuatorAvgMovementLb);
		//centerPanel.add(maxActMovementLb);
		centerPanel.add(saveBt);
		//centerPanel.add(cancelBt);
		
		JPanel southPanel=new JPanel();
		southPanel.setBackground(Color.WHITE);
		
		add("Center",centerPanel);
		add("South",southPanel);
		add("North",northPanel);
		
		
		Runnable r=new Runnable()
		{
			public void run()
			{
				while(true)
				{
					totalDesiredLocLb.setText     ("total desired loc--------------------: "+base.desiredLocList.size());
					availableDesiredLocLb.setText ("available desired loc----------------: "+base.availDesiredLocListForNodes.size());//?????
					totalNodesLb.setText          ("total nodes--------------------------: "+base.masterNodeList.size());
				/*try{availableNodesLb.setText      ("available nodes----------------------: "+base.baseStationArray[0].activeNodeList.size());}catch(Exception e){}*/
					try{availableNodesLb.setText      ("available nodes----------------------: "+base.masterBSList.get(0).activeNodeList.size());}catch(Exception e){}
				reserveNodesLb.setText			  ("reserve nodes------------------------: "+base.spareList.size());
				/*try{nodesDirConnectingBSLb.setText("nodes dir connecting base station----: "+base.baseStationArray[0].nodesConnectingBaseList.size());}catch(Exception e){}*/
				try{nodesDirConnectingBSLb.setText("nodes dir connecting base station----: "+base.masterBSList.get(0).nodesConnectingBaseList.size());}catch(Exception e){}
				    totalMovementLb.setText       ("total movement-----------------------: "+base.totalMovement+"    meters");
				    avgMovementLb.setText         ("average movement---------------------: "+base.avgMovement+"    meters");
				    averageConnectivityLb.setText ("Average connectivity-----------------: "+getAverageConnectivity());
				    percentageCoverageLb.setText  ("percentage coverage------------------: "+getPercentageCoverage()+"    %");				    
				    deployerTotalMovementLb.setText("total deployer movement--------------: "+base.deployerTotalMovement+"    meters");
				    deployerDroppingMovementLb.setText("dropping movement--------------: "+base.deployerDroppingMovement);
				    /*actuatorTotalMovementLb.setText("total actuator movement--------------: "+base.totalActuatorMovement);
				    base.avgActuatorMovement=base.totalActuatorMovement/base.actuatorList.size();
					actuatorAvgMovementLb.setText  ("average actuator movement------------: "+base.avgActuatorMovement);
					maxMovement=base.actuatorMovementList.get(index);
					maxActMovementLb.setText		("maximum actuator movement------------: "+maxMovement);*/
				    averageErrorLb=new JLabel("average error ------------------:"+base.totalError/base.masterNodeList.size());
				 				
					centerPanel.revalidate();
					try{Thread.sleep(2000);}catch(Exception e){}
				}
			}
		};
		new Thread(r).start();
		
		
	}
	
	//*********************************************************************
	//FUNCTION TO GET PPERCENTAGE COVERAGE --------------------------------
	double getPercentageCoverage()
	{
		int coveredCounter=0;
		int uncoveredCounter=0;
		int totalArea=0;
		Location centerLoc=new Location(base.dxCenter, base.dyCenter, base);
		int dARadius=Integer.parseInt(base.dRadiusTf.getText());
		
		if(base.dAShapeCb.getSelectedItem()=="square")
		{
			int xLimit=Integer.parseInt(base.dxTf.getText());
			int yLimit=Integer.parseInt(base.dyTf.getText());
			for(int y=100;y<yLimit;y++)
			{
				for(int x=100;x<xLimit;x++)
				{
					if(base.centerPanel.coordinateMat[y][x].obstacle)
					{
						continue;
					}
					if((base.centerPanel.coordinateMat[y][x]).covered)
					{
						coveredCounter=coveredCounter+1;
					}
					else
					{
						uncoveredCounter=uncoveredCounter+1;
					}
					totalArea=totalArea+1;
				}
			}
		}
		
		if(base.dAShapeCb.getSelectedItem()=="circle")
		{
			int xLimit=dARadius*2;
			int yLimit=dARadius*2;
			Location currentLoc;
			for(int y=100;y<yLimit;y++)
			{
				for(int x=100;x<xLimit;x++)
				{
					currentLoc=new Location(x, y, base);
					if(vToolBox.getDist(currentLoc, centerLoc)<=dARadius)
					{
						if(base.centerPanel.coordinateMat[y][x].obstacle)
						{
							continue;
						}
						if((base.centerPanel.coordinateMat[y][x]).covered)
						{
							coveredCounter=coveredCounter+1;
						}
						else
						{
							uncoveredCounter=uncoveredCounter+1;
						}
						totalArea=totalArea+1;
					}
				}
			}
		}
		
		int width=Integer.parseInt(base.dxTf.getText());
		int height=Integer.parseInt(base.dyTf.getText());
		//double percentageCoverage=((double)coveredCounter/((double)height*(double)width))*100;
		double percentageCoverage=((double)coveredCounter/totalArea)*100;
		return percentageCoverage;
	}
	
	//**************FUNCTION TO GET AVERAGE CONNECTIVITY***********************
	
	float getAverageConnectivity()
	{
		VLabel tempNode;
		float neighborCounter=0;
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			tempNode=base.masterNodeList.get(i);
			neighborCounter=neighborCounter+tempNode.neighborList.size();			
		}		
		return (neighborCounter/base.masterNodeList.size());
	}
	
	//-------------------------------------------------------------------------
	
	//---------------HANDELING ACTION EVENT------------------------------------
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==saveBt)
		{
			Runnable r=new Runnable()
			{
				public void run()
				{
					int coverage=(int)Math.ceil(getPercentageCoverage());
					int avgMovement=(int)Math.floor(base.avgMovement);
					try
					{
						base.joint.putData("insert into proposedmodel values('hexagon',"+base.masterNodeList.size()+","+coverage+","+avgMovement+","+base.commRangePerNode+","+base.sensingRangePerNode+")");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			};
			new Thread(r).start();
		}
	}
	//---------------------------------------------------------------------
	
	public static void main(String ... args)
	{
		System.out.println("starting program");
		VInfoDialog vid=new VInfoDialog(null);
		vid.setVisible(true);
		
	}

}
