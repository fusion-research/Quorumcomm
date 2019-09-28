package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import threeD.*;
public class ContainerDialog extends JDialog 
{
	Base base;
	public ContainerDialog(JPanel panel)
	{
		this.base=base;
		this.setLayout(new BorderLayout());
		
		JPanel northPanel=new JPanel();
		
		
		JPanel centerPanel=new JPanel();
		centerPanel.add(panel);
		
		
		JPanel southPanel=new JPanel();
		
		JPanel eastPanel=new JPanel();
		
		
		JPanel westPanel=new JPanel();
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
		add("East",eastPanel);
		add("West",westPanel);
		
	}
	
	public static void main(String...args)
	{
		System.out.println("the name of auhtor is vikrant sharma");
		ContainerDialog cd=new ContainerDialog(new ThreeDWorkAreaPanel(new Base()));
		cd.setBounds(100,100,1000,700);
		cd.setVisible(true);	
		for(int i=0;i<100;i++)
		{
			try{Thread.sleep(100);}catch(Exception e){}
			cd.revalidate();
		}
	}
}
