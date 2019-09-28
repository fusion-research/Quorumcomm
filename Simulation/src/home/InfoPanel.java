package home;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
public class InfoPanel extends JPanel
{
JLabel nameLb1,nameLb2,nameLb3,nameLb4,nameLb5,nameLb6;
JPanel symbolPn1,symbolPn2,symbolPn3,symbolPn4,symbolPn5,symbolPn6;

JLabel timeStampLb, timeStampLb2;
Base base;
	InfoPanel(Base b)
	{
		this.base=b;
		//setLayout(null);
		setLayout(new BorderLayout());
		
		
		
		JPanel westPanel=new JPanel();
		westPanel.setLayout(new BorderLayout());
		westPanel.setBackground(new Color(250,250,250));
		JLabel minMaxLb=new JLabel("Δ");
		westPanel.add("South",minMaxLb);
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//setBorder(BorderFactory.createLineBorder(Color.GRAY));
		centerPanel.setBackground(new Color(250,250,250));
		this.setBorder(BorderFactory.createLineBorder(new Color(230,230,230)));
		
		timeStampLb=new JLabel("Time Stamp:");
				
		timeStampLb2=new JLabel("_");
		//timeStampLb2.setBounds(130,10,30,15);		
		centerPanel.add(timeStampLb); centerPanel.add(timeStampLb2);
		
		this.add("Center",centerPanel);
		this.add("West",westPanel);
		
		
		Runnable r=new Runnable()
		{
			public void run()
			{
				while(true)
				{
					try{
						if(base.masterNodeList.get(0).timeStepCounter-1>0)
						{
							timeStampLb2.setText(base.masterNodeList.get(0).timeStepCounter-1+"");
						}
						Thread.sleep(1000);
					}catch(Exception e){}
				}
			}
			
		};
		new Thread(r).start();
			
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
	}

}
