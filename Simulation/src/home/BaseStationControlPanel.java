package home;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
public class BaseStationControlPanel extends JPanel 
{
	JLabel baseSelectLb;			JComboBox baseSelectCb;
	Base base;
	BaseStationControlPanel(Base b)
	{
		//super(b, "run configuration", true);
		this.base=b;
		//String baseSelectSt="";
		this.setLayout(new BorderLayout());
		
		JPanel northPanel=new JPanel();
		northPanel.setLayout(null);
		baseSelectLb=new JLabel("select base station");				baseSelectCb=new JComboBox();
		baseSelectLb.setBounds(10, 10,100,10);						baseSelectCb.setBounds(150,10,100,10);
																	for(int i=0;i<base.masterBSList.size();i++,baseSelectCb.addItem(Integer.toString(i)));
		northPanel.add(baseSelectLb);								northPanel.add(baseSelectCb);
		
		
		JPanel centerPanel=new JPanel();
		
		JPanel southPanel=new JPanel();
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
		
	
		
	}	

}
