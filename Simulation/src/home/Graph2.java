package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Graph2 extends JFrame 
{
	Base base;
	Graph2(Base b)
	{
		base=b;
		//GraphPanel graphPanel=new GraphPanel(base);
		add(base.graphPanel2);
		
	}
	
	public static void main(String args[])
	{
		Graph2 g2=new Graph2(new Base());
		g2.setSize(1100,800);
		g2.setVisible(true);
	}
}
