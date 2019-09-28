package analyst;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
public class WhiteWall extends JDialog 
{
	JLabel noOfChannelsLb;									JSpinner noOfChannelsSp;
	
	
	public WhiteWall()
	{
		this.setLayout(new BorderLayout());
		
		JPanel northPanel=new JPanel();
		
		JPanel southPanel=new JPanel();
		
		JPanel centerPanel=new JPanel();
		
		JPanel eastPanel=new JPanel();
		eastPanel.setBackground(Color.WHITE);
		noOfChannelsLb=new JLabel("Channels");				noOfChannelsSp=new JSpinner(new SpinnerNumberModel(2,0, 20,1));
		
		eastPanel.add(noOfChannelsLb);						eastPanel.add(noOfChannelsSp);
		
		
		
		
		JPanel westPanel=new JPanel();
		
		add("North",northPanel);
		add("South",southPanel);
		add("Center",centerPanel);
		add("West",westPanel);
		add("East",eastPanel);
		
	}
	
	public static void main(String args[])
	{
		WhiteWall whiteWall=new WhiteWall();
		whiteWall.setSize(700,500);
		whiteWall.setVisible(true);
		
		System.out.println("the name of the author s vikrant sharma");
	}
}
