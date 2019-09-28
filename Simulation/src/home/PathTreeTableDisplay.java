package home;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Event;
import java.util.*;
public class PathTreeTableDisplay extends VDialog 
{
	JTable pathTreeTable;
	JScrollPane tableSp;
	DefaultTableModel model;
	Base base;
	
	PathTreeTableDisplay(Base b, final VLabel node)
	{
		super(b,"PATH TREE TABLE");
		base=b;
		//----------------------------CENTER PANEL---------------------
		this.centerPanel.setLayout(null);
		pathTreeTable=new JTable();
		pathTreeTable.setBackground(Color.WHITE);
		tableSp=new JScrollPane(pathTreeTable);
		tableSp.setBounds(10,0,750,400);
		model=(DefaultTableModel)pathTreeTable.getModel();
		model.addColumn("sno.",new Object[]{});
		model.addColumn("root", new Object[]{});		
		model.addColumn("parent", new Object[]{});
		model.addColumn("children", new Object[]{});		
		
		this.centerPanel.add(tableSp);
		
		
		Runnable r=new Runnable()
		{
			public void run()
			{
				for(int i=0;i<node.path_Tree_Routing_Table.size();i++)
				{
					int sno=1;
					int rootId=0;
					int parentId=0;
					try{VLabel parent=VLabel.class.cast(node.path_Tree_Routing_Table.get(i).parent);parentId=parent.uniqueId;}catch(Exception e){}
					try{VLabel root=VLabel.class.cast(node.path_Tree_Routing_Table.get(i).root);rootId=root.uniqueId;}catch(Exception e){}
					try{Actuator parent=Actuator.class.cast(node.path_Tree_Routing_Table.get(i).parent);parentId=parent.uniqueId;}catch(Exception e){}
					try{Actuator root=Actuator.class.cast(node.path_Tree_Routing_Table.get(i).root);rootId=root.uniqueId;}catch(Exception e){}
					String childrenSt="";
					for(int j=0;j<node.path_Tree_Routing_Table.get(i).childeren_List.size();j++)
					{
						try
						{
						childrenSt=childrenSt+node.path_Tree_Routing_Table.get(i).childeren_List.get(j).uniqueId+", ";
						}
						catch(Exception e){}
					}
					//int children_Count=node.path_Tree_Routing_Table.get(i).childeren_List.size();
					model.addRow(new Object[]{sno,rootId,parentId,childrenSt});
				}
				
			}
		};
		new Thread(r).start();
		
	}
	
	public static void main(String args[])
	{
		PathTreeTableDisplay pttd=new PathTreeTableDisplay(null,new VLabel());
		pttd.setSize(800,500);
		pttd.setVisible(true);
		
	}
}
