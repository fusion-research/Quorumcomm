package home;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;
public class ActuatorPropertiesDialog  extends JDialog implements ActionListener
{
	JButton cancelBt,deployBt;
	JButton applyBt;
	JLabel numberLb; 		JSpinner numberSp;//JTextField numberTf;
	JLabel typeLb;	 		JTextField typeTf;
	JLabel deploymentLb;	JComboBox deploymentCb;
	String deploymentSt[]={"random","fixed"};
	
	JLabel commRangeLb;		JTextField commRangeTf;
	//JLabel sensingRangeLb;	JTextField sensingRangeTf;
	
	JLabel powerLb;			JTextField powerTf;
	JLabel bufferSizeLb;	JSpinner bufferSizeSp;
	JCheckBox solarPanelEnabled;
	JLabel panelSizeLb;		JTextField panelSizeTf;
	
	JCheckBox sourceNodeChBx;
	JCheckBox sinkNodeChBx;
	
	Base base;
	
	int x;
	int y;
	int xArray[]=new int[1000];
	int yArray[]=new int[1000];
	//Actuator actuatorPa[]=new Actuator[1000];
	ArrayList<Actuator>actList=new ArrayList<Actuator>();
	
	ImageIcon nodeIcon=new ImageIcon("images/bnode.jpg");
	int counter;
	Actuator actuatorInstance;
	int tempPower;
	
	ActuatorPropertiesDialog(Base b)
	{
		super(b, " node properties", true);
		base=b;
		//System.out.println("the name of the author is vikrant sharma");
		this.setLayout(new BorderLayout());
		
			JPanel centerPanel=new JPanel();
			centerPanel.setLayout(null);
			numberLb=new JLabel("number");				numberSp=new JSpinner(new SpinnerNumberModel(17,0, 1000,1));		
			numberLb.setBounds(10,10,100,20);			numberSp.setBounds(150,10,100,20);
			typeLb=new JLabel("type");					typeTf=new JTextField();
			typeLb.setBounds(10,40,100,20);				typeTf.setBounds(150,40,100,20);
			
			deploymentLb=new JLabel("deployment");		deploymentCb=new JComboBox(deploymentSt);
			deploymentLb.setBounds(10,70,100,20);		deploymentCb.setBounds(150,70,100,20);
			
			commRangeLb=new JLabel("communication range");	commRangeTf=new JTextField("150");
			commRangeLb.setBounds(10,100,100,20);		commRangeTf.setBounds(150,100,100,20);
			
			//sensingRangeLb=new JLabel("sensing range"); sensingRangeTf=new JTextField("40");
			//sensingRangeLb.setBounds(10,130,100,20);	sensingRangeTf.setBounds(150,130,100,20);
			
			powerLb=new JLabel("power");				powerTf=new JTextField("500");
			powerLb.setBounds(10,160,100,20);			powerTf.setBounds(150,160,100,20);
			
			bufferSizeLb=new JLabel("buffer size");		bufferSizeSp=new JSpinner(new SpinnerNumberModel(1,0,100,1));
			bufferSizeLb.setBounds(10,190,100,20);		bufferSizeSp.setBounds(150,190,100,20);
			
			solarPanelEnabled=new JCheckBox("solar panel");
			solarPanelEnabled.setEnabled(false);
			solarPanelEnabled.setBounds(10,220,100,20);
			
			panelSizeLb=new JLabel("panel size");		panelSizeTf=new JTextField();
			panelSizeLb.setBounds(10,250,100,20);		panelSizeTf.setBounds(150,250,100,20);
			panelSizeLb.setEnabled(false);				panelSizeTf.setEnabled(false);
			
			centerPanel.add(numberLb);					centerPanel.add(numberSp);
			centerPanel.add(typeLb);					centerPanel.add(typeTf);
			centerPanel.add(deploymentLb);				centerPanel.add(deploymentCb);
			centerPanel.add(commRangeLb);				centerPanel.add(commRangeTf);
			//centerPanel.add(sensingRangeLb);   			centerPanel.add(sensingRangeTf);
			centerPanel.add(powerLb);					centerPanel.add(powerTf);
			centerPanel.add(bufferSizeLb);				centerPanel.add(bufferSizeSp);
			centerPanel.add(solarPanelEnabled); 
			centerPanel.add(panelSizeLb);				centerPanel.add(panelSizeTf);
			
			
			
			
		 JPanel southPanel=new JPanel();
		// southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		 deployBt=new JButton("deploy");
		 deployBt.addActionListener(this);
		 cancelBt=new JButton("cancel");
		 cancelBt.addActionListener(this);

		 southPanel.add(deployBt);
		 southPanel.add(cancelBt);
		 
		 
		 
		 
		 this.add("South",southPanel);
		 this.add("Center",centerPanel);
	}
	
	
	
	//************************STARTING SECOND CONSTRUCTOR************************
	

	
	
	
	//*********************IMPLEMENTING THE ACTION EVENT ************************
	public void actionPerformed(ActionEvent ae)
	{
	 if(ae.getSource()==deployBt)
	 {
		 try
		 {
		 //**********INITIALIZING DEPLOYMENT PARAMETERS***************************
		  base.numberOfActuators=Integer.parseInt(numberSp.getValue().toString());
		  base.commRangePerActuator=Integer.parseInt(commRangeTf.getText());
		 // base.sensingRangePerActuator=Integer.parseInt(sensingRangeTf.getText());
		 // base.powerPerNode=Integer.parseInt(powerTf.getText());
		  //tempPower=Integer.parseInt(powerTf.getText());
		  base.actuatorDeploymentScheme=deploymentCb.getSelectedItem().toString();
		  
		 //***********************************************************************
		 }
		 catch(NumberFormatException e)
		 {
			 MsgDialog md=new MsgDialog(e.toString(),base);
			 md.setBounds(200, 200,400,200);
			 md.setVisible(true);
			 
		 }
		 deployActuators();
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
	 }
	 
 
	 if(ae.getSource()==cancelBt)
	 {
		 //System.out.println("cancel button have been pressed");
		 try
		 {
			 actuatorInstance.setBackground(actuatorInstance.defaultColor);
		 }catch(NullPointerException e){e.printStackTrace();}
		 dispose();
	 }
	 
	 if(ae.getSource()==applyBt)
	 {
		 //System.out.println("apply button have been pressed");
		 actuatorInstance.commRange=Integer.parseInt(commRangeTf.getText());
		 //actuatorInstance.sensingRange=Integer.parseInt(sensingRangeTf.getText());
		 try
		 {
			 actuatorInstance.setBackground(actuatorInstance.defaultColor);
		 }
		 catch(NullPointerException e){e.printStackTrace();}
		 dispose();
		 
	 }
	}
	
	//*************FUNCTIONS****************************************************
	
	void deployActuators()
	{
		
		 x=Integer.parseInt(base.dxTf.getText());
		 y=Integer.parseInt(base.dyTf.getText());
		 //counter=Integer.parseInt(numberSp.getValue().toString());
		 
		 counter=base.numberOfActuators;
		 
		 if(deploymentCb.getSelectedItem()=="random")
		 //if(base.deploymentScheme=="random")
		 {
			 //System.out.println("ok button is pressed");

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
							 int tempX=randX.nextInt(x);
							 int tempY=randY.nextInt(y);
							
							 
							 if(base.centerPanel.coordinateMat[tempX+100][tempY+100].obstacle)
							 {
								 i--;
								 continue;
							 }
							 
							 xArray[i]=tempX;
							 yArray[i]=tempY;
							 
							// System.out.println(xArray[i]);
							// base.nodeArray[i].defaultBufferSize=Integer.parseInt(bufferSizeSp.getValue().toString());
							 //System.out.println(base.nodeArray[i].uniqueId);
							 
						 }
						 catch(Exception e)
						 {
							 MsgDialog md=new MsgDialog("deployment area not set",base);
							 md.setBounds(300,300,300,150);
							 md.setVisible(true);
							
							 break;
						 }
						 	Actuator tempActuator=new Actuator(base,base.DARK_ORANGE);				
						 actList.add(tempActuator);
						 tempActuator.uniqueId=i;
						 
						 //----------------------------SETTING PARAMETERS OF NODE--------------------------												
						 tempActuator.commRange=base.commRangePerNode;
						 tempActuator.sensingRange=base.sensingRangePerNode;
						 //actuatorPa[i].power=tempPower;
						 //actuatorPa[i].defaultBufferSize=Integer.parseInt(bufferSizeSp.getValue().toString());
						 //--------------------------------------------------------------------------------
					 }
					 
					// System.out.println("check pt 6");
					
					 base.centerPanel.actuatorDeployer(1,counter,xArray,yArray,actList);
					 base.centerPanel.removeMouseMotionListener(base.centerPanel);
				 }
			 };
			 //System.out.println("check pt 7");
			 new Thread(r).start();
		 }
		 
		 if(base.deploymentScheme=="fixed")
		 {
			 Runnable r=new Runnable()
			 {
				 public void run()
				 {
					 for(int i=0;i<counter;i++)
					 {
						 xArray[i]=i*12;
						 yArray[i]=10;
						 Actuator tempActuator=new Actuator(base,Color.ORANGE);
						 actList.add(tempActuator);
						 
						 //----------------------------SETTING PARAMETERS OF NODE--------------------------
						 tempActuator.commRange=base.commRangePerNode;
						 tempActuator.sensingRange=base.sensingRangePerNode;
						 //--------------------------------------------------------------------------------
					 }
					 base.centerPanel.actuatorDeployer(1,counter,xArray,yArray,actList);
					 base.centerPanel.removeMouseMotionListener(base.centerPanel);
				 }
			 };
			 new Thread(r).start();
		 }
		 try
		 {
			 actuatorInstance.setBackground(actuatorInstance.defaultColor);
		 }catch(NullPointerException e){System.out.println(e);}
		
		dispose();
		base.repaint();
		//base.revalidate();
	}
	//**************************************************************************
	
	//********FUNCTIONS FOR SCRIPT ASSISTANCE***********************************
	
	void deployActuatorsFS(int noOfActuators,int commRange,String deploymentScheme)
	{
		 try
		 {
		 //**********INITIALIZING DEPLOYMENT PARAMETERS***************************
		  base.numberOfActuators=noOfActuators;
		  base.commRangePerActuator=commRange;
		  base.actuatorDeploymentScheme=deploymentScheme;
		  
		 //***********************************************************************
		 }
		 catch(NumberFormatException e)
		 {
			 MsgDialog md=new MsgDialog(e.toString(),base);
			 md.setBounds(200, 200,400,200);
			 md.setVisible(true);
			 
		 }
		 deployActuators();
	}
	
	//--------------------------------------------------------------------------
	
	
	
	//***************************************************************************
	public static void main(String args[])
	{
		PropertiesDialog pd=new PropertiesDialog(null);
		pd.setSize(300,400);
		pd.setVisible(true);
	}

}
