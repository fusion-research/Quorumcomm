package home;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class Controller extends JDialog implements ActionListener
{
	Base base;
	JButton oneBt;
	Controller(Base base)
	{
		this.base=base;
		oneBt=new JButton("click");
		oneBt.addActionListener(this);
		add(oneBt);		
		this.setSize(100,70);
		this.setVisible(true);
	}
	
	public static void main(String...args)
	{
		System.out.println("starting the main function");
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==oneBt)
		{
			System.out.println("one button");
		//	base.theta=base.theta+1;
			VFigure figure=base.foregroundFigureList.get(1);
			for(int i=0;i<figure.lineList.size();i++)
			{
				//figure.lineList.get(i).x1=figure.xc+(int)(figure.lineList.get(i).distFromCenterStart*Math.sin(((base.theta+(int)figure.lineList.get(i).angleFromCenterStart)%360)*Math.PI/180));
				//figure.lineList.get(i).y1=figure.yc+(int)(figure.lineList.get(i).distFromCenterStart*Math.cos(((base.theta+(int)figure.lineList.get(i).angleFromCenterStart)%360)*Math.PI/180));
			
				//figure.lineList.get(i).x2=figure.xc-(int)(figure.lineList.get(i).distFromCenterEnd*Math.sin(((base.theta+(int)figure.lineList.get(i).angleFromCenterEnd)%360)*Math.PI/180));
				//figure.lineList.get(i).y2=figure.yc-(int)(figure.lineList.get(i).distFromCenterEnd*Math.cos(((base.theta+(int)figure.lineList.get(i).angleFromCenterEnd)%360)*Math.PI/180));
			}
			
		}
	}

}
