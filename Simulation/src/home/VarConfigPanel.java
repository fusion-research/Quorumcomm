package home;
import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.awt.*;
public class VarConfigPanel extends JPanel implements ActionListener 
{
	Base base;
	JCheckBox varDisplayCb;
	VarConfigPanel(Base base)
	{
		this.base=base;
		setLayout(new BorderLayout());
		JPanel northPanel=new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		varDisplayCb=new JCheckBox("Display variables");
		varDisplayCb.addActionListener(this);
		northPanel.add(varDisplayCb);
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		  String data[][] =new String[2][3]; 
				    String col[] = {"Label","Variable"};
				    
				    DefaultTableModel model = new DefaultTableModel(data,col);
				    JTable table = new JTable(model);				    
							  
				  JTableHeader tableHeader=table.getTableHeader();
				  tableHeader.setForeground(Color.WHITE);
				  tableHeader.setBackground(Color.BLUE);
				  JScrollPane pane = new JScrollPane(table);
				  pane.setBounds(0,0, 500, 400);
				  centerPanel.add(pane);
		
		JPanel southPanel=new JPanel();
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==varDisplayCb)
		{
			System.out.println(varDisplayCb.isSelected());
		}
	}
}
