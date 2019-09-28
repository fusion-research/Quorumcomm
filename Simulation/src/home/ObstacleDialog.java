package home;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
public class ObstacleDialog extends JDialog
{
	JSpinner obstacleCountSp;
	JList obstacleList;
	String optionList[]={"rectangle","circle","triangle","--------------------------------"};
	
	Base base;
	ObstacleDialog(Base b)
	{
		super(b);
		base=b;
		base.obstacleDialog=this;
		base.obstacleArray=new Obstacle[5];
		//setUndecorated(true);
		//this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//this.setLayout(new GridLayout(2,1));
		this.setLayout(new BorderLayout());
		
		JPanel northPanel=new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		obstacleCountSp=new JSpinner(new SpinnerNumberModel(2,0,5,1));
		northPanel.add(obstacleCountSp);
		
		JPanel centerPanel=new JPanel();
		
		
		obstacleList=new JList(optionList);
		centerPanel.add(obstacleList);
		
		add("North",northPanel);
		add("Center",centerPanel);
		
		this.setFocusable(true);
		
		/*this.addFocusListener
		(
			new FocusAdapter()
			{
				public void focusLost(FocusEvent fe)
				{
					System.out.println("focus have been lost");
					dispose();
				}
			}
		);*/
		
		
		obstacleList.addMouseListener
		(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent me)
					{
						base.obstacleCount=Integer.parseInt(obstacleCountSp.getValue().toString());
						if(((JList)me.getSource()).getSelectedValue()=="rectangle")
						{
							 Color transparentRed = new Color (191,96, 0, 70);
							//obstacle=new Obstacle(new Color(191,96,0),base);
							 
							 for(int i=0;i<base.obstacleCount;i++)
							 {
							 base.obstacleArray[i]=new Obstacle(transparentRed,base);
							 base.centerPanel.add(base.obstacleArray[i]);
							 }
							
						}
						

						if(((JList)me.getSource()).getSelectedValue()=="circle")
						{
							System.out.println("circle have been pressed");
						}
						

						if(((JList)me.getSource()).getSelectedValue()=="triangle")
						{
							System.out.println("triangle have been pressed");
						}
						
						base.obstacleDialog.dispose();
					}
				}
	);
		
	}
	public static void main(String args[])
	{
		ObstacleDialog od=new ObstacleDialog(null);
		od.setSize(150,250);
		od.setVisible(true);
	}

}
