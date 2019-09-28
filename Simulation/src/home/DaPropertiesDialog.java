package home;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

import java.awt.event.*;
import java.util.*;
public class DaPropertiesDialog  extends JDialog implements ActionListener
{
	JButton cancelBt;
	JButton applyBt;

	JLabel normalMinTempLb;				JTextField normalMinTempTf;
	JLabel normalMaxTempLb;				JTextField normalMaxTempTf;
	JLabel criticalTempLb;				JTextField criticalTempTf;
	
	
	Base base;
	
	int x;
	int y;
	int xArray[]=new int[100];
	int yArray[]=new int[100];
	VLabel nodeLb[]=new VLabel[100];
	ImageIcon nodeIcon=new ImageIcon("images/bnode.jpg");
	int counter;
	VLabel nodeInstance;
	
	DaPropertiesDialog(Base b)
	{
		super(b, "deployment area properties", true);
		base=b;
		
		//System.out.println("the name of the author is vikrant sharma");
		this.setLayout(new BorderLayout());
		
			JPanel centerPanel=new JPanel();
			centerPanel.setLayout(null);
			
			normalMinTempLb=new JLabel("minimum temperature (normal)");			normalMinTempTf=new JTextField();
			normalMinTempLb.setBounds(10,10,200,20);							normalMinTempTf.setBounds(250,10,100,20);
			normalMaxTempLb=new JLabel("maximum temperature (normal)");			normalMaxTempTf=new JTextField();
			normalMaxTempLb.setBounds(10,40,200,20);							normalMaxTempTf.setBounds(250,40,100,20);
			criticalTempLb=new JLabel("critical temperature");					criticalTempTf=new JTextField();
			criticalTempLb.setBounds(10,70,200,20);								criticalTempTf.setBounds(250,70,100,20);
			
			
		//	maxRangeLb.setBounds(10,100,100,20);	maxRangeTf.setBounds(150,100,100,20);
			
			
			//powerLb.setBounds(10,130,100,20);		powerTf.setBounds(150,130,100,20);
			
			centerPanel.add(normalMinTempLb);	centerPanel.add(normalMinTempTf);
			centerPanel.add(normalMaxTempLb);	centerPanel.add(normalMaxTempTf);
			centerPanel.add(criticalTempLb);	centerPanel.add(criticalTempTf);
			
			
			
		 JPanel southPanel=new JPanel();
		 southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		 cancelBt=new JButton("cancel");
		 cancelBt.addActionListener(this);
		 
		 applyBt=new JButton("apply");
		 applyBt.addActionListener(this);

		 southPanel.add(cancelBt);
		 southPanel.add(applyBt);
		 
		 //--------------------------INSERTING VALUES TO FIELDS-----------------------
		 try
		 {
		 ResultSet rs=base.joint.getDataByResultSet("select * from daproperties");
		 rs.next();
		 normalMinTempTf.setText(rs.getString(1));
		 normalMaxTempTf.setText(rs.getString(2));
		 criticalTempTf.setText(rs.getString(3));
		 }
		 catch(Exception e){e.printStackTrace();}
		 
		 
		 
		 
		 this.add("South",southPanel);
		 this.add("Center",centerPanel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==applyBt)
		{
			//System.out.println("apply button have been pressed");
			int normalMinTemp=Integer.parseInt(normalMinTempTf.getText());
			int normalMaxTemp=Integer.parseInt(normalMaxTempTf.getText());
			int criticalTemp=Integer.parseInt(criticalTempTf.getText());
			base.joint.fix("delete from daproperties");
			base.joint.fix("insert into daproperties values("+normalMinTemp+","+normalMaxTemp+","+criticalTemp+")");
			
			dispose();
		}
		
		if(ae.getSource()==cancelBt)
		{
			dispose();
		}
	}
	
	//***************************************************************************
	public static void main(String args[])
	{
		DaPropertiesDialog pd=new DaPropertiesDialog(null);
		pd.setSize(400,400);
		pd.setVisible(true);
	}

}
