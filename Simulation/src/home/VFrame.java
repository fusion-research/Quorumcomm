package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class VFrame extends JFrame
{
	JButton okBt,cancelBt;
	JPanel centerPanel,southPanel,northPanel;
	Base base;
	VFrame(Base b,String title)
	{
		super(title);
		this.base=b;
		this.setLayout(new BorderLayout());
		
		northPanel=new JPanel();
		
		centerPanel=new JPanel();
		
		southPanel=new JPanel();
		okBt=new JButton("ok");
		cancelBt=new JButton("cancel");
		southPanel.add(okBt);
		southPanel.add(cancelBt);

		add("North",northPanel);
		add("Cemter",centerPanel);
		add("South",southPanel);
	
	}

}
