package home;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class ViewDialog extends JDialog implements ItemListener
{
	JCheckBox commRangeChb,sensingRangeChb,connectionChb,interClusterConnectionChb,coverageChb,rangeChb,floorLinesChb,bsConnChb,colorizeCRChb;
	
	JCheckBox actuatorCommRangeChb;
	JCheckBox gridChb,dlNumberChb,hexagonsChb,coordinateShowableChb,turningPointChb,virtualPathChb;
	Base base;
	JTabbedPane viewTp;
	ViewDialog(Base b)
	{
		super(b, "view");
		base=b;
		viewTp=new JTabbedPane();
		this.setLayout(new BorderLayout());
		JPanel northPanel=new JPanel();
		
		
		JPanel centerPanel=new JPanel();
		//centerPanel.setLayout(null);
		
		JPanel nodeViewPanel=new JPanel();
		nodeViewPanel.setLayout(null);
		commRangeChb=new JCheckBox("communication range");
		commRangeChb.addItemListener(this);
		commRangeChb.setSelected(base.commRangeShowable);
		commRangeChb.setBounds(10,10,200,20); 
		sensingRangeChb=new JCheckBox("sensing range");
		sensingRangeChb.addItemListener(this);
		sensingRangeChb.setSelected(base.sensingRangeShowable);
		sensingRangeChb.setBounds(10,40,200,20);
		
		connectionChb =new JCheckBox("connections");
		connectionChb.addItemListener(this);
		connectionChb.setSelected(base.connectionShowable);
		connectionChb.setBounds(10,70,200,20);
		
		interClusterConnectionChb=new JCheckBox("inter cluster connection");
		interClusterConnectionChb.addItemListener(this);
		interClusterConnectionChb.setSelected(base.interClusterConnectionShowable);
		interClusterConnectionChb.setBounds(10,100,200,20);
		
		coverageChb=new JCheckBox("coverage");
		coverageChb.addItemListener(this);
		coverageChb.setSelected(false);
		coverageChb.setBounds(10,130,200,20);
		
		rangeChb=new JCheckBox("range");
		rangeChb.addItemListener(this);
		rangeChb.setSelected(true);
		rangeChb.setBounds(10,160,200,20);
		
		floorLinesChb=new JCheckBox("floor lines");
		floorLinesChb.addItemListener(this);
		floorLinesChb.setSelected(true);
		floorLinesChb.setBounds(10,190,200,20);
		
		bsConnChb=new JCheckBox("base station connection");
		bsConnChb.addItemListener(this);
		bsConnChb.setSelected(true);
		bsConnChb.setBounds(10,220,200,20);
		
		colorizeCRChb=new JCheckBox("colorize candidate region");
		colorizeCRChb.addItemListener(this);
		colorizeCRChb.setSelected(false);
		colorizeCRChb.setBounds(10,250,200,20);
		
		nodeViewPanel.add(commRangeChb);
		nodeViewPanel.add(sensingRangeChb);
		nodeViewPanel.add(connectionChb);
		nodeViewPanel.add(interClusterConnectionChb);
		nodeViewPanel.add(coverageChb);
		nodeViewPanel.add(rangeChb);
		nodeViewPanel.add(floorLinesChb);
		nodeViewPanel.add(bsConnChb);
		nodeViewPanel.add(colorizeCRChb);
		
		//---------------------------------------------------------------------------
		
		JPanel actuatorViewPanel=new JPanel();
		actuatorCommRangeChb=new JCheckBox("communication range");
		actuatorCommRangeChb.addItemListener(this);
		commRangeChb.setSelected(base.actuatorCommRangeShowable);
		actuatorCommRangeChb.setBounds(10,10,150,20);
		
		actuatorViewPanel.add(actuatorCommRangeChb);
		
		//--------------------------------------------------------
		
		JPanel simulatorViewPanel=new JPanel();
		simulatorViewPanel.setLayout(null);
		gridChb=new JCheckBox("grid");
		gridChb.addItemListener(this);
		gridChb.setSelected(base.gridShowable);
		gridChb.setBounds(10,10,150,20);
		
		hexagonsChb=new JCheckBox("desired locations (DL)");
		hexagonsChb.addItemListener(this);
		hexagonsChb.setSelected(true);
		hexagonsChb.setBounds(10,40,150,20);
		
		dlNumberChb=new JCheckBox("DL numbering");
		dlNumberChb.addItemListener(this);
		dlNumberChb.setSelected(base.gridShowable);
		dlNumberChb.setBounds(10,70,150,20);
		
		coordinateShowableChb=new JCheckBox("coordinates");
		coordinateShowableChb.addItemListener(this);
		coordinateShowableChb.setSelected(base.coordinateShowable);
		coordinateShowableChb.setBounds(10,100,150,20);
		
		turningPointChb=new JCheckBox("Path turning points");
		turningPointChb.addItemListener(this);
		turningPointChb.setSelected(base.pathTurningPointsShowable);
		turningPointChb.setBounds(10,130,150,20);
		
		virtualPathChb=new JCheckBox("Virtual Path");
		virtualPathChb.addItemListener(this);
		virtualPathChb.setSelected(base.virtualPathShowable);
		virtualPathChb.setBounds(10,160,150,20);
		
		
		simulatorViewPanel.add(gridChb);
		simulatorViewPanel.add(hexagonsChb);
		simulatorViewPanel.add(dlNumberChb);
		simulatorViewPanel.add(coordinateShowableChb);
		simulatorViewPanel.add(turningPointChb);
		simulatorViewPanel.add(virtualPathChb);
		//--------------------------------------------------------
				
		viewTp.add("node",nodeViewPanel);
		viewTp.add("actuator",actuatorViewPanel);
		viewTp.add("simulator",simulatorViewPanel);
		
		JPanel southPanel=new JPanel();
		
		this.add("North",northPanel);
		this.add("Center",viewTp);
		this.add("South",southPanel);
		
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==gridChb)
		{
			if(gridChb.isSelected())
			{
				base.gridShowable=true;
			}
			else if(gridChb.isSelected()==false)
			{
				base.gridShowable=false;
			}
		}
		
		if(ie.getSource()==dlNumberChb)
		{
			if(dlNumberChb.isSelected())
			{
				showDLNumbers();
			}
			else if(dlNumberChb.isSelected()==false)
			{
				hideDLNumbers();
			}
		}
		
		if(ie.getSource()==commRangeChb)
		{
			if(commRangeChb.isSelected())
			{
				base.commRangeShowable=true;
			}
			else if(commRangeChb.isSelected()==false)
			{
				base.commRangeShowable=false;
			}
		}
		
		if(ie.getSource()==sensingRangeChb)
		{
			if(sensingRangeChb.isSelected())
			{
				base.sensingRangeShowable=true;
			}
			
			else if(sensingRangeChb.isSelected()==false)
			{
				base.sensingRangeShowable=false;
			}				
		}
		
		if(ie.getSource()==connectionChb)
		{
			if(connectionChb.isSelected())
			{
				base.connectionShowable=true;
			}
			
			else if(connectionChb.isSelected()==false)
			{
				base.connectionShowable=false;
			}				
		}
		
		
		
		if(ie.getSource()==interClusterConnectionChb)
		{
			if(interClusterConnectionChb.isSelected())
			{
				base.interClusterConnectionShowable=true;
			}
			
			else if(interClusterConnectionChb.isSelected()==false)
			{
				base.interClusterConnectionShowable=false;
			}				
		}
		
		if(ie.getSource()==coverageChb)
		{
			if(coverageChb.isSelected())
			{
				base.coverageShowable=true;
			}
			
			else if(coverageChb.isSelected()==false)
			{
				base.coverageShowable=false;
			}				
		}
		
		if(ie.getSource()==rangeChb)
		{
			if(rangeChb.isSelected())
			{
				base.rangeShowable=true;
			}
			
			else if(rangeChb.isSelected()==false)
			{
				base.rangeShowable=false;
			}				
		}
		
		if(ie.getSource()==hexagonsChb)
		{
			if(hexagonsChb.isSelected())
			{
				base.desiredLocShowable=true;
			}
			
			else if(hexagonsChb.isSelected()==false)
			{
				base.desiredLocShowable=false;
			}				
		}
		
		if(ie.getSource()==coordinateShowableChb)
		{
			if(coordinateShowableChb.isSelected())
			{
				base.coordinateShowable=true;
			}
			else if(coordinateShowableChb.isSelected()==false)
			{
				base.coordinateShowable=false;
			}
		}
		
		if(ie.getSource()==turningPointChb)
		{
			if(turningPointChb.isSelected())
			{
				base.pathTurningPointsShowable=true;
			}
			else if(turningPointChb.isSelected()==false)
			{
				base.pathTurningPointsShowable=false;
			}
		}
		
		if(ie.getSource()==virtualPathChb)
		{
			if(virtualPathChb.isSelected())
			{
				base.virtualPathShowable=true;
			}
			else if(virtualPathChb.isSelected()==false)
			{
				base.virtualPathShowable=false;
			}
		}
		
		
		if(ie.getSource()==floorLinesChb)
		{
			if(floorLinesChb.isSelected())
			{
				base.floorLinesShowable=true;
			}
			
			else if(floorLinesChb.isSelected()==false)
			{
				base.floorLinesShowable=false;
			}				
		}
		
		if(ie.getSource()==bsConnChb)
		{
			if(bsConnChb.isSelected())
			{
				base.bsConnShowable=true;
			}
			
			else if(bsConnChb.isSelected()==false)
			{
				base.bsConnShowable=false;
			}				
		}
		
		if(ie.getSource()==colorizeCRChb)
		{
			if(colorizeCRChb.isSelected())
			{
				base.colorizeCR=true;
			}
			
			else if(colorizeCRChb.isSelected()==false)
			{
				base.colorizeCR=false;
			}				
		}
		
		//-----------------------------------------------------------------------
		
		if(ie.getSource()==actuatorCommRangeChb)
		{
			if(actuatorCommRangeChb.isSelected())
			{
				base.actuatorCommRangeShowable=true;
			}
			else if(actuatorCommRangeChb.isSelected()==false)
			{
				base.actuatorCommRangeShowable=false;
			}
		}
		
	}
	
	public void showDLNumbers()
	{
		for(int i=0;i<base.desiredLocList.size();i++)
		{
			//base.desiredLocList.get(i).groupLb=new JLabel(Integer.toString(base.desiredLocList.get(i).group));
			//base.desiredLocList.get(i).groupLb.setBounds(base.desiredLocList.get(i).getXLoc(),base.desiredLocList.get(i).getYLoc(),15,15);
			base.centerPanel.add(base.desiredLocList.get(i).groupLb);
			base.centerPanel.revalidate();
		}
	}
	
	public void hideDLNumbers()
	{
		for(int i=0;i<base.desiredLocList.size();i++)
		{
			//base.desiredLocList.get(i).groupLb=new JLabel(Integer.toString(base.desiredLocList.get(i).group));
			//base.desiredLocList.get(i).groupLb.setBounds(base.desiredLocList.get(i).getXLoc(),base.desiredLocList.get(i).getYLoc(),15,15);
			base.centerPanel.remove(base.desiredLocList.get(i).groupLb);
			base.centerPanel.revalidate();
		}
	}
	
	
	public static void main(String args[])
	{
		ViewDialog vd=new ViewDialog(null);
		vd.setSize(200,300);
		vd.setVisible(true);
	}
}
