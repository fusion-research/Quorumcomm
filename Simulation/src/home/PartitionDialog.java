package home;
import java.awt.*;
import java.util.*;
import javax.swing.*;

import java.awt.event.*;
public class PartitionDialog extends JDialog implements ActionListener
{
	JButton okBt,cancelBt;
	JComboBox typeCb;
	JLabel typeLb,widthLb;
	JSpinner widthSp;
	String partitionTypeAr[]={"regular","irregular"};
	Base base;
	PartitionDialog(Base b)
	{
		base=b;
		setLayout(new BorderLayout());
		JPanel northPanel=new JPanel();
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		typeLb=new JLabel("partition type");		typeCb=new JComboBox(partitionTypeAr);
		widthLb=new JLabel("partition width");		widthSp=new JSpinner(new SpinnerNumberModel(70,0, 1000,1));
		
		typeLb.setBounds(10,15,100,20);				typeCb.setBounds(150,15,100,20);
		widthLb.setBounds(10,45,100,15);			widthSp.setBounds(150,45,100,20);
		
		centerPanel.add(typeLb);					centerPanel.add(typeCb);
		centerPanel.add(widthLb);					centerPanel.add(widthSp);
		
		JPanel southPanel=new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		okBt=new JButton("ok");
		okBt.addActionListener(this);
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);
		southPanel.add(okBt);
		southPanel.add(cancelBt);
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==okBt)
		{
			dispose();
			String type=typeCb.getSelectedItem().toString();
			base.partitionWidth=Integer.parseInt(widthSp.getValue().toString());
			if(type.equals("regular"))
			{
				createEvenPartition(base.partitionWidth);
			}
			
			if(type.equals("irregular"))
			{
				//-----------------------------------------------
			}
			
		}
		
		if(ae.getSource()==cancelBt)
		{
			dispose();
		}
	}
	
	
	//---------------------FUNCTION TO CREATE EVEN PARTITION------------------------
	void createEvenPartition(final int partitionWidth)
	{
		
		base.partitionWidth=partitionWidth;
			Runnable r=new Runnable()
			{
				int halfOfPartiton=partitionWidth/2;
				Random randX=new Random();
				int tempX=randX.nextInt(500);
				
				Random randY=new Random();
				int tempY=randX.nextInt(500);
				
				//int horizontalY=((Integer.parseInt(base.dyTf.getText()))/2+100)-halfOfPartiton;
//				int verticalX=(Integer.parseInt(base.dxTf.getText())/2)+100-halfOfPartiton;
				int horizontalY=(tempY+100)-halfOfPartiton;
				int verticalX=tempX+100-halfOfPartiton;
				public void run()
				{
					base.randomX=tempX;
					base.randomY=tempY;
					
					base.partitionCounterX=0;
					base.partitionCounterY=0;
					for(int i=0;i<500;i++)
					{
						try{Thread.sleep(5);}catch(Exception e){}
						base.partitionCounterX=base.partitionCounterX+1;
						base.partitionCounterY=base.partitionCounterY+1;
						
						for(int j=0;j<base.partitionWidth;j++)
						{
							base.centerPanel.coordinateMat[i+100][horizontalY+j].affected=true;
							//Location tempLoc=new Location(i+100,horizontalY+j,base);     // TESTING PURPOSE
							//base.tempList.add(tempLoc);                                  //TESTING PURPOSE
							
							base.centerPanel.coordinateMat[verticalX+j][i+100].affected=true;
							//Location tempLoc1=new Location(verticalX+j,i+100,base);		//TESTING PURPOSE
							//base.tempList.add(tempLoc1);									//TESTING PURPOSE
						}
					}
				}
			};
			new Thread(r).start();
		

	}
	//------------------------------------------------------------------------------
	public static void main(String args[])
	{
		System.out.println("starting main method");
		PartitionDialog pd=new PartitionDialog(null);
		pd.setSize(300,200);
		pd.setVisible(true);
	}

}
