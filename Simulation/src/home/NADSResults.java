package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class NADSResults extends JDialog implements ActionListener
{
	JButton cancelBt,saveBt;
	Base base;
	JLabel placedMSNLb2;
	JLabel totalMovementLb2;
	JLabel avgMovementLb2;
	
	JLabel totalMovementPh1Lb2;
	
	JLabel totalMovementPh2Lb2;
	JLabel totalMovementPh2_1Lb2;
	JLabel totalMovementPh2_2Lb2;
	JLabel totalMovementPh2_3Lb2;
	JLabel totalMovementPh2_4Lb2;
	JLabel totalMovementPh2_5Lb2;
	JLabel totalMovementPh2_6Lb2;
	JLabel totalMovementPh2_7Lb2;
	JLabel totalMovementPh3Lb2;
	
	
	JLabel avgMovementPh1Lb2;
	
	JLabel avgMovementPh2Lb2;
	JLabel avgMovementPh2_1Lb2;
	JLabel avgMovementPh2_2Lb2;
	JLabel avgMovementPh2_3Lb2;
	JLabel avgMovementPh2_4Lb2;
	JLabel avgMovementPh2_5Lb2;
	JLabel avgMovementPh2_6Lb2;
	JLabel avgMovementPh2_7Lb2;
	JLabel avgMovementPh3Lb2;

	JLabel msnMovedPh1Lb2;
	JLabel msnMovedPh2Lb2;
	JLabel msnMovedPh2_1Lb2;
	JLabel msnMovedPh2_2Lb2;
	JLabel msnMovedPh2_3Lb2;
	JLabel msnMovedPh2_4Lb2;
	JLabel msnMovedPh2_5Lb2;
	JLabel msnMovedPh2_6Lb2;
	JLabel msnMovedPh2_7Lb2;
	JLabel msnMovedPh3Lb2;

	NADSResults(Base b)
	{
		super(b, "NADS results",false);
		base=b;
		JPanel northPanel=new JPanel();
		
		JPanel centerPanel=new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(null);
		JLabel placedMSNLb=new JLabel("placed MSN :");			placedMSNLb2=new JLabel(base.placedNodesCount+"");
		JLabel totalMovementLb=new JLabel("total movement :"); 	totalMovementLb2=new JLabel(base.totalMovement+"");
		base.avgMovement=base.totalMovement/base.masterNodeList.size();
		JLabel avgMovementLb=new JLabel("average movement :"); 	avgMovementLb2=new JLabel(base.avgMovement+"");
		
		JLabel totalMovementPh1Lb=new JLabel("Total mov: Ph 1:");			totalMovementPh1Lb2=new JLabel(base.totalMovementNadsPh1+"");
		JLabel totalMovementPh2_1Lb=new JLabel("Total mov: Ph2_1:"); 		totalMovementPh2_1Lb2=new JLabel(base.totalMovementNadsPh2_1+"");
		JLabel totalMovementPh2_2Lb=new JLabel("Total mov: Ph2_2:"); 		totalMovementPh2_2Lb2=new JLabel(base.totalMovementNadsPh2_2+"");
		JLabel totalMovementPh2_3Lb=new JLabel("Total mov: Ph2_3:"); 		totalMovementPh2_3Lb2=new JLabel(base.totalMovementNadsPh2_3+"");
		JLabel totalMovementPh2_4Lb=new JLabel("Total mov: Ph2_4:"); 		totalMovementPh2_4Lb2=new JLabel(base.totalMovementNadsPh2_4+"");
		JLabel totalMovementPh2_5Lb=new JLabel("Total mov: Ph2_5:"); 		totalMovementPh2_5Lb2=new JLabel(base.totalMovementNadsPh2_5+"");
		JLabel totalMovementPh2_6Lb=new JLabel("Total mov: Ph2_6:"); 		totalMovementPh2_6Lb2=new JLabel(base.totalMovementNadsPh2_6+"");
		JLabel totalMovementPh2_7Lb=new JLabel("Total mov: Ph2_7:"); 		totalMovementPh2_7Lb2=new JLabel(base.totalMovementNadsPh2_7+"");
		
		JLabel totalMovementPh2Lb=new JLabel("Total mov: Ph 2:");			totalMovementPh2Lb2=new JLabel(base.totalMovementNadsPh2+"");
		JLabel totalMovementPh3Lb=new JLabel("Total mov: Ph 3:");			totalMovementPh3Lb2=new JLabel(base.totalMovementNadsPh3+"");
		
		
		JLabel avgMovementPh1Lb=new JLabel("avg mov: Ph 1:");			avgMovementPh1Lb2=new JLabel(base.avgMovementNadsPh1+"");
		JLabel avgMovementPh2_1Lb=new JLabel("avg mov: Ph2_1:"); 		avgMovementPh2_1Lb2=new JLabel(base.avgMovementNadsPh2_1+"");
		JLabel avgMovementPh2_2Lb=new JLabel("avg mov: Ph2_2:"); 		avgMovementPh2_2Lb2=new JLabel(base.avgMovementNadsPh2_2+"");
		JLabel avgMovementPh2_3Lb=new JLabel("avg mov: Ph2_3:"); 		avgMovementPh2_3Lb2=new JLabel(base.avgMovementNadsPh2_3+"");
		JLabel avgMovementPh2_4Lb=new JLabel("avg mov: Ph2_4:"); 		avgMovementPh2_4Lb2=new JLabel(base.avgMovementNadsPh2_4+"");
		JLabel avgMovementPh2_5Lb=new JLabel("avg mov: Ph2_5:"); 		avgMovementPh2_5Lb2=new JLabel(base.avgMovementNadsPh2_5+"");
		JLabel avgMovementPh2_6Lb=new JLabel("avg mov: Ph2_6:"); 		avgMovementPh2_6Lb2=new JLabel(base.avgMovementNadsPh2_6+"");
		JLabel avgMovementPh2_7Lb=new JLabel("avg mov: Ph2_7:"); 		avgMovementPh2_7Lb2=new JLabel(base.avgMovementNadsPh2_7+"");
		
		JLabel avgMovementPh2Lb=new JLabel("avg mov: Ph 2:");			avgMovementPh2Lb2=new JLabel(base.avgMovementNadsPh2+"");
		
		JLabel avgMovementPh3Lb=new JLabel("avg mov: Ph 3:");			avgMovementPh3Lb2=new JLabel(base.avgMovementNadsPh3+"");
		
		
		msnMovedPh1Lb2=new JLabel(base.nodesPlacedNadsPh1+"");
		msnMovedPh2_1Lb2=new JLabel(base.nodesPlacedNadsPh2_1+"");
		msnMovedPh2_2Lb2=new JLabel(base.nodesPlacedNadsPh2_2+"");
		msnMovedPh2_3Lb2=new JLabel(base.nodesPlacedNadsPh2_3+"");
		msnMovedPh2_4Lb2=new JLabel(base.nodesPlacedNadsPh2_4+"");
		msnMovedPh2_5Lb2=new JLabel(base.nodesPlacedNadsPh2_5+"");
		msnMovedPh2_6Lb2=new JLabel(base.nodesPlacedNadsPh2_6+"");
		msnMovedPh2_7Lb2=new JLabel(base.nodesPlacedNadsPh2_7+"");
		msnMovedPh2Lb2=new JLabel(base.nodesPlacedNadsPh2+"");
		msnMovedPh3Lb2=new JLabel(base.nodesPlacedNadsPh3+"");
		
		
		placedMSNLb.setBounds(10,10,80,20);						placedMSNLb2.setBounds(120,10,80,20);
		totalMovementLb.setBounds(10,40,80,20);					totalMovementLb2.setBounds(120,40,80,20);
		avgMovementLb.setBounds(10,70,80,20);					avgMovementLb2.setBounds(120,70,80,20);
		
		totalMovementPh1Lb.setBounds(10,100,150,20);		totalMovementPh1Lb2.setBounds(170,100,80,20);		avgMovementPh1Lb.setBounds(270,100,150,20);			avgMovementPh1Lb2.setBounds(430,100,80,20);			msnMovedPh1Lb2.setBounds(530,100,80,20);
		totalMovementPh2_1Lb.setBounds(10,130,150,20); 		totalMovementPh2_1Lb2.setBounds(170,130,80,20);		avgMovementPh2_1Lb.setBounds(270,130,150,20); 		avgMovementPh2_1Lb2.setBounds(430,130,80,20);		msnMovedPh2_1Lb2.setBounds(530,130,80,20);
		totalMovementPh2_2Lb.setBounds(10,160,150,20); 		totalMovementPh2_2Lb2.setBounds(170,160,80,20);		avgMovementPh2_2Lb.setBounds(270,160,150,20); 		avgMovementPh2_2Lb2.setBounds(430,160,80,20);		msnMovedPh2_2Lb2.setBounds(530,160,80,20);
		totalMovementPh2_3Lb.setBounds(10,190,150,20); 		totalMovementPh2_3Lb2.setBounds(170,190,80,20);		avgMovementPh2_3Lb.setBounds(270,190,150,20); 		avgMovementPh2_3Lb2.setBounds(430,190,80,20);		msnMovedPh2_3Lb2.setBounds(530,190,80,20);
		totalMovementPh2_4Lb.setBounds(10,220,150,20); 		totalMovementPh2_4Lb2.setBounds(170,220,80,20);		avgMovementPh2_4Lb.setBounds(270,220,150,20); 		avgMovementPh2_4Lb2.setBounds(430,220,80,20);		msnMovedPh2_4Lb2.setBounds(530,220,80,20);
		totalMovementPh2_5Lb.setBounds(10,250,150,20); 		totalMovementPh2_5Lb2.setBounds(170,250,80,20);		avgMovementPh2_5Lb.setBounds(270,250,150,20); 		avgMovementPh2_5Lb2.setBounds(430,250,80,20);		msnMovedPh2_5Lb2.setBounds(530,250,80,20);
		totalMovementPh2_6Lb.setBounds(10,280,150,20); 		totalMovementPh2_6Lb2.setBounds(170,280,80,20);		avgMovementPh2_6Lb.setBounds(270,280,150,20); 		avgMovementPh2_6Lb2.setBounds(430,280,80,20);		msnMovedPh2_6Lb2.setBounds(530,280,80,20);
		totalMovementPh2_7Lb.setBounds(10,310,150,20); 		totalMovementPh2_7Lb2.setBounds(170,310,80,20);		avgMovementPh2_7Lb.setBounds(270,310,150,20); 		avgMovementPh2_7Lb2.setBounds(430,310,80,20);		msnMovedPh2_7Lb2.setBounds(530,310,80,20);
		
		totalMovementPh2Lb.setBounds(10,340,150,20);		totalMovementPh2Lb2.setBounds(170,340,80,20);		avgMovementPh2Lb.setBounds(270,340,150,20);			avgMovementPh2Lb2.setBounds(430,340,80,20);			msnMovedPh2Lb2.setBounds(530,340,80,20);
		totalMovementPh3Lb.setBounds(10,370,150,20);		totalMovementPh3Lb2.setBounds(170,370,80,20);		avgMovementPh3Lb.setBounds(270,370,150,20);			avgMovementPh3Lb2.setBounds(430,370,80,20);			msnMovedPh3Lb2.setBounds(530,370,80,20);
		
		
		centerPanel.add(placedMSNLb);					centerPanel.add(placedMSNLb2);
		centerPanel.add(totalMovementLb);				centerPanel.add(totalMovementLb2);
		centerPanel.add(avgMovementLb);					centerPanel.add(avgMovementLb2);
		
		centerPanel.add(totalMovementPh1Lb);		centerPanel.add(totalMovementPh1Lb2);		centerPanel.add(avgMovementPh1Lb);			centerPanel.add(avgMovementPh1Lb2);			centerPanel.add(msnMovedPh1Lb2);
		centerPanel.add(totalMovementPh2_1Lb);		centerPanel.add(totalMovementPh2_1Lb2);		centerPanel.add(avgMovementPh2_1Lb);		centerPanel.add(avgMovementPh2_1Lb2);		centerPanel.add(msnMovedPh2_1Lb2);
		centerPanel.add(totalMovementPh2_2Lb);		centerPanel.add(totalMovementPh2_2Lb2);		centerPanel.add(avgMovementPh2_2Lb);		centerPanel.add(avgMovementPh2_2Lb2);		centerPanel.add(msnMovedPh2_2Lb2);
		centerPanel.add(totalMovementPh2_3Lb);		centerPanel.add(totalMovementPh2_3Lb2);		centerPanel.add(avgMovementPh2_3Lb);		centerPanel.add(avgMovementPh2_3Lb2);		centerPanel.add(msnMovedPh2_3Lb2);
		centerPanel.add(totalMovementPh2_4Lb);		centerPanel.add(totalMovementPh2_4Lb2);		centerPanel.add(avgMovementPh2_4Lb);		centerPanel.add(avgMovementPh2_4Lb2);		centerPanel.add(msnMovedPh2_4Lb2);
		centerPanel.add(totalMovementPh2_5Lb);		centerPanel.add(totalMovementPh2_5Lb2);		centerPanel.add(avgMovementPh2_5Lb);		centerPanel.add(avgMovementPh2_5Lb2);		centerPanel.add(msnMovedPh2_5Lb2);
		centerPanel.add(totalMovementPh2_6Lb);		centerPanel.add(totalMovementPh2_6Lb2);		centerPanel.add(avgMovementPh2_6Lb);		centerPanel.add(avgMovementPh2_6Lb2);		centerPanel.add(msnMovedPh2_6Lb2);
		centerPanel.add(totalMovementPh2_7Lb);		centerPanel.add(totalMovementPh2_7Lb2);		centerPanel.add(avgMovementPh2_7Lb);		centerPanel.add(avgMovementPh2_7Lb2);		centerPanel.add(msnMovedPh2_7Lb2);
		
		centerPanel.add(totalMovementPh2Lb);		centerPanel.add(totalMovementPh2Lb2);		centerPanel.add(avgMovementPh2Lb);			centerPanel.add(avgMovementPh2Lb2);			centerPanel.add(msnMovedPh2Lb2);
		centerPanel.add(totalMovementPh3Lb);		centerPanel.add(totalMovementPh3Lb2);		centerPanel.add(avgMovementPh3Lb);			centerPanel.add(avgMovementPh3Lb2);			centerPanel.add(msnMovedPh3Lb2);
		
		JPanel southPanel=new JPanel();
		saveBt=new JButton("save");
		saveBt.addActionListener(this);
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);
		southPanel.add(saveBt);
		southPanel.add(cancelBt);
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
		
		
		//---------------INFINITE LOOP TO SHOW THE UPDATED RESULTS------------------
		Runnable r=new Runnable()
		{
			public void run()
			{
				while(true)
				{
					placedMSNLb2.setText(base.placedNodesCount+"");
					totalMovementLb2.setText(base.totalMovement+"");
					base.avgMovement=base.totalMovement/base.masterNodeList.size();
					avgMovementLb2.setText(base.avgMovement+"");
										
					totalMovementPh1Lb2.setText(base.totalMovementNadsPh1+"");
					totalMovementPh2_1Lb2.setText(base.totalMovementNadsPh2_1+"");
					totalMovementPh2_2Lb2.setText(base.totalMovementNadsPh2_2+"");
					totalMovementPh2_3Lb2.setText(base.totalMovementNadsPh2_3+"");
					totalMovementPh2_4Lb2.setText(base.totalMovementNadsPh2_4+"");
					totalMovementPh2_5Lb2.setText(base.totalMovementNadsPh2_5+"");
					totalMovementPh2_6Lb2.setText(base.totalMovementNadsPh2_6+"");
					totalMovementPh2_7Lb2.setText(base.totalMovementNadsPh2_7+"");
					totalMovementPh2Lb2.setText(base.totalMovementNadsPh2+"");
					totalMovementPh3Lb2.setText(base.totalMovementNadsPh3+"");
					
					
					avgMovementPh1Lb2.setText(base.totalMovementNadsPh1/base.nodesPlacedNadsPh1+"");
					avgMovementPh2_1Lb2.setText(base.totalMovementNadsPh2_1/base.nodesPlacedNadsPh2_1+"");
					avgMovementPh2_2Lb2.setText(base.totalMovementNadsPh2_2/base.nodesPlacedNadsPh2_2+"");
					avgMovementPh2_3Lb2.setText(base.totalMovementNadsPh2_3/base.nodesPlacedNadsPh2_3+"");
					avgMovementPh2_4Lb2.setText(base.totalMovementNadsPh2_4/base.nodesPlacedNadsPh2_4+"");
					avgMovementPh2_5Lb2.setText(base.totalMovementNadsPh2_5/base.nodesPlacedNadsPh2_5+"");
					avgMovementPh2_6Lb2.setText(base.totalMovementNadsPh2_6/base.nodesPlacedNadsPh2_6+"");
					avgMovementPh2_7Lb2.setText(base.totalMovementNadsPh2_7/base.nodesPlacedNadsPh2_7+"");
					avgMovementPh2Lb2.setText(base.totalMovementNadsPh2/base.nodesPlacedNadsPh2+"");
					avgMovementPh3Lb2.setText(base.totalMovementNadsPh3/base.nodesPlacedNadsPh3+"");
					
					
					msnMovedPh1Lb2.setText(base.nodesPlacedNadsPh1+"");
					msnMovedPh2_1Lb2.setText(base.nodesPlacedNadsPh2_1+"");
					msnMovedPh2_2Lb2.setText(base.nodesPlacedNadsPh2_2+"");
					msnMovedPh2_3Lb2.setText(base.nodesPlacedNadsPh2_3+"");
					msnMovedPh2_4Lb2.setText(base.nodesPlacedNadsPh2_4+"");
					msnMovedPh2_5Lb2.setText(base.nodesPlacedNadsPh2_5+"");
					msnMovedPh2_6Lb2.setText(base.nodesPlacedNadsPh2_6+"");
					msnMovedPh2_7Lb2.setText(base.nodesPlacedNadsPh2_7+"");
					msnMovedPh2Lb2.setText(base.nodesPlacedNadsPh2+"");
					msnMovedPh3Lb2.setText(base.nodesPlacedNadsPh3+"");
																								
					try
					{
						Thread.sleep(500);
					}catch(Exception e){}
				}
			}
		};
		new Thread(r).start();
		
		//--------------------------------------------------------------------------
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==cancelBt)
		{
			dispose();
		}
		if(ae.getSource()==saveBt)
		{
			dispose();
		}
	}
	
	public static void main(String args[])
	{
		System.out.println("the name of the author is vikrant sharma");
	}

}
