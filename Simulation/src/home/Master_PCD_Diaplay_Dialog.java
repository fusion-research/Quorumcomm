package home;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
public class Master_PCD_Diaplay_Dialog extends VDialog implements ActionListener
{
	JTable pcdTable;
	JScrollPane tableSp;
	DefaultTableModel model;
	Base base;
	
	Master_PCD_Diaplay_Dialog(Base b)
	{
		super(b,"PCD LIST");
		base=b;
		//----------------------------CENTER PANEL---------------------
		this.centerPanel.setLayout(null);
		pcdTable=new JTable();
		pcdTable.setBackground(Color.WHITE);
		tableSp=new JScrollPane(pcdTable);
		tableSp.setBounds(10,0,750,400);
		model=(DefaultTableModel)pcdTable.getModel();
		model.addColumn("sno.",new Object[]{});
		model.addColumn("parent id", new Object[]{});		
		model.addColumn("child id", new Object[]{});
		model.addColumn("distance", new Object[]{});
		model.addColumn("connection", new Object[]{});
		
		
		
		
		this.centerPanel.add(tableSp);
		//--------------------------------------------------------------
		Runnable r=new Runnable()
		{
			public void run()
			{

				for(int l=0;l<base.masterBSList.get(0).master_Pcd_List.size();l++)
				{
					Parent_Child_Dist tempPcd=base.masterBSList.get(0).master_Pcd_List.get(l);
					//System.out.println("parent id :"+tempPcd.parent.uniqueId+"      child id :"+tempPcd.child.uniqueId+"     distance :"+tempPcd.dist);
					int sno=l;
					VLabel parent=VLabel.class.cast(tempPcd.parent);
					int parentId=parent.uniqueId;
					
					int childId=tempPcd.child.uniqueId;
					float dist=tempPcd.dist;
					boolean connection=tempPcd.connection;
					
					model.addRow(new Object[]{sno,parentId,childId,dist,connection});
					
				}
			}
		};
		new Thread(r).start();
		
		
		//---------------------SOUTH PANEL------------------------
		this.okBt.addActionListener(this);
		this.cancelBt.addActionListener(this);
		//---------------------------------------------------------
	}
//****************************************************************************************************************
//***************MASTER PCD DISPLAY FOR MMUs**********************************************************************
	Master_PCD_Diaplay_Dialog(Base b,final Actuator actuator)
	{
		super(b,"PCD LIST");
		base=b;
		//----------------------------CENTER PANEL---------------------
		this.centerPanel.setLayout(null);
		pcdTable=new JTable();
		pcdTable.setBackground(Color.WHITE);
		tableSp=new JScrollPane(pcdTable);
		tableSp.setBounds(10,0,750,400);
		model=(DefaultTableModel)pcdTable.getModel();
		model.addColumn("sno.",new Object[]{});
		model.addColumn("parent id", new Object[]{});		
		model.addColumn("child id", new Object[]{});
		model.addColumn("distance", new Object[]{});
		model.addColumn("connection", new Object[]{});
		
		
		
		
		this.centerPanel.add(tableSp);
		//--------------------------------------------------------------
		Runnable r=new Runnable()
		{
			public void run()
			{

				for(int l=0;l<actuator.master_Pcd_List.size();l++)
				{
					Parent_Child_Dist tempPcd=actuator.master_Pcd_List.get(l);
					//System.out.println("parent id :"+tempPcd.parent.uniqueId+"      child id :"+tempPcd.child.uniqueId+"     distance :"+tempPcd.dist);
					int sno=l;
					int parentId=0;
					try
					{
						Actuator parent=Actuator.class.cast(tempPcd.parent);
						parentId=parent.uniqueId;
					}
					catch(Exception e){}
					
					try
					{
						VLabel parent=VLabel.class.cast(tempPcd.parent);
						parentId=parent.uniqueId;
					}
					catch(Exception e){}
					
					int childId=tempPcd.child.uniqueId;
					float dist=tempPcd.dist;
					boolean connection=tempPcd.connection;
					
					model.addRow(new Object[]{sno,parentId,childId,dist,connection});
					
				}
			}
		};
		new Thread(r).start();
		
		
		//---------------------SOUTH PANEL------------------------
		this.okBt.addActionListener(this);
		this.cancelBt.addActionListener(this);
		//---------------------------------------------------------
	}
//****************************************************************************************************************
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==cancelBt)
		{
			this.dispose();
		}
		if(ae.getSource()==okBt)
		{
			this.dispose();
		}
	}
	public static void main(String args[])
	{
	
		Master_PCD_Diaplay_Dialog mpcd=new Master_PCD_Diaplay_Dialog(null);
		mpcd.setSize(800,500);
		mpcd.setVisible(true);
	}

}
