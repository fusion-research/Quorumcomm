package home;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
public class Obstacle extends JPanel implements ActionListener
{

	JLabel posLb;
	int xLen;
	int yLen;
	
	Color defaultColor;
	
	boolean obstacleResizable=false;
	boolean obstacleMovable=false;
	Base base;
	JLabel resizeLb;
	Obstacle thisObstacle;
	
	Obstacle()
	{}
	
	Obstacle(int nodeType,String value,Base b)
	{
		//super(value);
		this.base=b;
		thisObstacle=this;

	}
	
	
	
	Obstacle(Color color,Base b)
	{
		//this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.setLayout(null);
		this.setBackground(color);
		this.defaultColor=color;
		this.base=b;
		thisObstacle=this;
		this.setBounds(200,200,150,50);
		xLen=this.getWidth();
		yLen=this.getHeight();
		
		posLb=new JLabel(xLen+" X "+yLen);
		posLb.setForeground(Color.WHITE);
		posLb.setBounds(2,10,100,20);
		
		resizeLb=new JLabel("||||");
		resizeLb.setForeground(Color.WHITE);
		resizeLb.setBounds(this.getWidth()-15,this.getHeight()-17,15,15);
		resizeLb.addMouseListener
		(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent me)
					{
						
						base.candidateObstacle=thisObstacle;
						obstacleResizable=true;
						obstacleMovable=false;
						//System.exit(0);
					}
					
				}
		);
		
		this.addMouseListener
		(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent me)
					{
						//System.exit(0);
						base.candidateObstacle=(Obstacle)me.getSource();
						obstacleMovable=true;
						obstacleResizable=false;
					}
					
				}
		);
		
		this.add(posLb);
		this.add(resizeLb);
		
		
		

		
		
		this.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseReleased(MouseEvent me)
					{
						System.out.println("mouse have been released.. no action to be taken");
					}
				}
		);
		//----------------------------------------------------------------------
				
	}
	
	//**********IMPLEMENTING ACTION EVENTS*********************
	
	public void actionPerformed(ActionEvent ae)
	{
		
	}
	//---------------------------------------------------------
	



}
