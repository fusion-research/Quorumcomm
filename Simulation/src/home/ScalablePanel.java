package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScalablePanel extends JDialog
{
	ScalablePanel()
	{
		setLayout(new BorderLayout());
		
		HorizontalScale northPanel=new HorizontalScale();
		
		
		JPanel centerPanel=new JPanel();
		
		JPanel southPanel=new JPanel();
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
	}
	
	public static void main(String[] args) 
	{
		System.out.println("starting the program");
		ScalablePanel sp=new ScalablePanel();
		sp.setSize(300,300);
		sp.setVisible(true);
	}

}
