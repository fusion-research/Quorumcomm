package home;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;
public class SinkPropertiesDialog  extends JDialog implements ActionListener
{
	JButton cancelBt,deployBt,reDeployBt;
	JLabel numberLb; 		JSpinner numberSp;
	JLabel typeLb;	 		JTextField typeTf;
	JLabel deploymentLb;	JComboBox deploymentCb;
	String deploymentSt[]={"random","fixed"};
	
	JLabel maxRangeLb;		JTextField maxRangeTf;
	JLabel powerLb;			JTextField powerTf;
	
	Base base;
	
	int x;
	int y;
	int xArray[]=new int[100];
	int yArray[]=new int[100];
	VLabel nodeLb[]=new VLabel[100];
	ImageIcon nodeIcon=new ImageIcon("images/gnode.jpg");
	int counter;
	
	SinkPropertiesDialog(Base b)
	{
		super(b, "sink properties", true);
		base=b;
		System.out.println("the name of the author is vikrant sharma");
		this.setLayout(new BorderLayout());
		
			JPanel centerPanel=new JPanel();
			centerPanel.setLayout(null);
			numberLb=new JLabel("number");			numberSp=new JSpinner(new SpinnerNumberModel(10,0, 20,1));		
			numberLb.setBounds(10,10,100,20);		numberSp.setBounds(150,10,100,20);
			typeLb=new JLabel("type");				typeTf=new JTextField();
			typeLb.setBounds(10,40,100,20);			typeTf.setBounds(150,40,100,20);
			
			deploymentLb=new JLabel("deployment");	deploymentCb=new JComboBox(deploymentSt);
			deploymentLb.setBounds(10,70,100,20);	deploymentCb.setBounds(150,70,100,20);
			
			maxRangeLb=new JLabel("max-range");		maxRangeTf=new JTextField();
			maxRangeLb.setBounds(10,100,100,20);	maxRangeTf.setBounds(150,100,100,20);
			
			powerLb=new JLabel("power");			powerTf=new JTextField();
			powerLb.setBounds(10,130,100,20);		powerTf.setBounds(150,130,100,20);
			
			centerPanel.add(numberLb);			centerPanel.add(numberSp);
			centerPanel.add(typeLb);			centerPanel.add(typeTf);
			centerPanel.add(deploymentLb);		centerPanel.add(deploymentCb);
			centerPanel.add(maxRangeLb);		centerPanel.add(maxRangeTf);
			centerPanel.add(powerLb);			centerPanel.add(powerTf);
			
			
			
		 JPanel southPanel=new JPanel();
		 southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		 deployBt=new JButton("deploy");
		 deployBt.addActionListener(this);
		 cancelBt=new JButton("cancel");
		 cancelBt.addActionListener(this);
		 reDeployBt=new JButton("re-deploy");
		 reDeployBt.addActionListener(this);
		 
		 southPanel.add(reDeployBt);
		 southPanel.add(deployBt);
		 southPanel.add(cancelBt);
		 
		 
		 
		 
		 this.add("South",southPanel);
		 this.add("Center",centerPanel);
	}
	
	//*********************IMPLEMENTING THE ACTION EVENT ************************
	public void actionPerformed(ActionEvent ae)
	{
	 if(ae.getSource()==deployBt)
	 {
		 x=Integer.parseInt(base.dxTf.getText());
		 y=Integer.parseInt(base.dyTf.getText());
		 counter=Integer.parseInt(numberSp.getValue().toString());
		 
		 if(deploymentCb.getSelectedItem()=="random")
		 {
			 System.out.println("ok button is pressed");

			 //*****************************STARTING THE THREAD**********************
			 Runnable r=new Runnable()
			 {
				 public void run()
				 {
					 Random randX=new Random();
					 Random randY=new Random();
					 for(int i=0;i<counter;i++)
					 {
						 try
						 {
							 xArray[i]=randX.nextInt(x);
							 yArray[i]=randY.nextInt(y);
							 System.out.println(xArray[i]);
						 }
						 catch(Exception e)
						 {
							 MsgDialog md=new MsgDialog("deployment area not set",base);
							 md.setBounds(300,300,300,150);
							 md.setVisible(true);
							
							 break;
						 }
						 nodeLb[i]=new VLabel(2,base.BLUE,base);
					 }
					 base.centerPanel.sinkDeployer(2,counter,xArray,yArray,nodeLb);
					 base.centerPanel.removeMouseMotionListener(base.centerPanel);
				 }
			 };
			 new Thread(r).start();
		 }
		 
		 if(deploymentCb.getSelectedItem()=="fixed")
		 {
			 Runnable r=new Runnable()
			 {
				 public void run()
				 {
					 for(int i=0;i<counter;i++)
					 {
						 xArray[i]=20;
						 yArray[i]=i*15;
						 nodeLb[i]=new VLabel(2,Color.BLUE,base);
						 
					 }
					 base.centerPanel.sinkDeployer(2,counter,xArray,yArray,nodeLb);
					 base.centerPanel.removeMouseMotionListener(base.centerPanel);
				 }
			 };
			 new Thread(r).start();
		 }
		dispose();
	 }
	 
	 if(ae.getSource()==reDeployBt)
	 {
		 
		 System.out.println("re- deploy beutton have been pressed");
	 }
	 
	 if(ae.getSource()==cancelBt)
	 {
		 System.out.println("cancel button have been pressed");
		 dispose();
	 }
	}
	
	
	//***************************************************************************
	public static void main(String args[])
	{
		SinkPropertiesDialog pd=new SinkPropertiesDialog(null);
		pd.setSize(300,400);
		pd.setVisible(true);
	}

}
