package threeD;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class Test1 extends JFrame implements ChangeListener
{
	JSlider slider;
	Test1()
	{
		this.setLayout(new BorderLayout());
		JPanel northPanel=new JPanel();
		JPanel centerPanel=new JPanel();
		
		JPanel southPanel=new JPanel();
		slider=new JSlider();
		slider.setValue(50);
		slider.addChangeListener(this);
		southPanel.add(slider);
		
		
		JPanel eastPanel=new JPanel();
		JPanel westPanel=new JPanel();

		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
		add("West",westPanel);
		add("East",eastPanel);
		
		//--------------------------------IMPLEMENTING LISTENERS----------------------
		
	}
	
	 public void stateChanged(ChangeEvent ce)
	 {
		  int value = slider.getValue();
		  String str = Integer.toString(value);
		  System.out.println("Value :"+str);
		  
	}
	
	
	public static void main(String ... args)
	{
		System.out.println("the name of author is vikrant sharma");
		Test1 test1=new Test1();
		test1.setSize(500,500);
		test1.setVisible(true);
	}

}
