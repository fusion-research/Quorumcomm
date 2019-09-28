package home;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;
public class PropertiesDialog  extends JDialog implements ActionListener
{
	JButton cancelBt,deployBt,savedDeployBt;
	JButton applyBt;
	JLabel numberLb; 		JSpinner numberSp;//JTextField numberTf;
	JLabel typeLb;	 		JTextField typeTf;
	JLabel deploymentLb;	JComboBox deploymentCb;
	String deploymentSt[]={"random","fixed"};
	
	JLabel commRangeLb;		JTextField commRangeTf;
	JLabel sensingRangeLb;	JTextField sensingRangeTf;
	
	JLabel powerLb;			JTextField powerTf;
	JLabel bufferSizeLb;	JSpinner bufferSizeSp;
	JCheckBox solarPanelEnabled;
	JLabel panelSizeLb;		JTextField panelSizeTf;
	
	JCheckBox mobileNodeChBx;
	JCheckBox sourceNodeChBx;
	JCheckBox sinkNodeChBx;
	
	Base base;
	
	int x;
	int y;
	int xArray[]=new int[1000];
	int yArray[]=new int[1000];
	//VLabel nodeLb[]=new VLabel[1000];
	ArrayList<VLabel>nodeList=new ArrayList<VLabel>();
	ImageIcon nodeIcon=new ImageIcon("images/bnode.jpg");
	int counter;
	VLabel nodeInstance;
	int tempPower;
	
	Thread thread1;
	PropertiesDialog(Base b)
	{
		super(b, " node properties", true);
		base=b;
		//System.out.println("the name of the author is vikrant sharma");
		this.setLayout(new BorderLayout());
		
			JPanel centerPanel=new JPanel();
			centerPanel.setLayout(null);
			numberLb=new JLabel("number");				numberSp=new JSpinner(new SpinnerNumberModel(67,0, 1000,1));		
			numberLb.setBounds(10,10,100,20);			numberSp.setBounds(150,10,100,20);
			typeLb=new JLabel("type");					typeTf=new JTextField();
			typeLb.setBounds(10,40,100,20);				typeTf.setBounds(150,40,100,20);
			
			deploymentLb=new JLabel("deployment");		deploymentCb=new JComboBox(deploymentSt);
			deploymentLb.setBounds(10,70,100,20);		deploymentCb.setBounds(150,70,100,20);
			
			commRangeLb=new JLabel("communication range");	commRangeTf=new JTextField("70");
			commRangeLb.setBounds(10,100,100,20);		commRangeTf.setBounds(150,100,100,20);
			
			sensingRangeLb=new JLabel("sensing range"); sensingRangeTf=new JTextField("40");
			sensingRangeLb.setBounds(10,130,100,20);	sensingRangeTf.setBounds(150,130,100,20);
			
			powerLb=new JLabel("power");				powerTf=new JTextField("500");
			powerLb.setBounds(10,160,100,20);			powerTf.setBounds(150,160,100,20);
			
			bufferSizeLb=new JLabel("buffer size");		bufferSizeSp=new JSpinner(new SpinnerNumberModel(1,0,100,1));
			bufferSizeLb.setBounds(10,190,100,20);		bufferSizeSp.setBounds(150,190,100,20);
			
			mobileNodeChBx=new JCheckBox("mobile");
			mobileNodeChBx.setBounds(10,220,100,20);
			
			solarPanelEnabled=new JCheckBox("solar panel");
			solarPanelEnabled.setEnabled(false);
			solarPanelEnabled.setBounds(10,250,100,20);
			
			panelSizeLb=new JLabel("panel size");		panelSizeTf=new JTextField();
			panelSizeLb.setBounds(10,280,100,20);		panelSizeTf.setBounds(150,280,100,20);
			panelSizeLb.setEnabled(false);				panelSizeTf.setEnabled(false);
			
			centerPanel.add(numberLb);					centerPanel.add(numberSp);
			centerPanel.add(typeLb);					centerPanel.add(typeTf);
			centerPanel.add(deploymentLb);				centerPanel.add(deploymentCb);
			centerPanel.add(commRangeLb);				centerPanel.add(commRangeTf);
			centerPanel.add(sensingRangeLb);   			centerPanel.add(sensingRangeTf);
			centerPanel.add(powerLb);					centerPanel.add(powerTf);
			centerPanel.add(bufferSizeLb);				centerPanel.add(bufferSizeSp);
			centerPanel.add(mobileNodeChBx);
			centerPanel.add(solarPanelEnabled); 
			centerPanel.add(panelSizeLb);				centerPanel.add(panelSizeTf);
			
			
			
			
		 JPanel southPanel=new JPanel();
		// southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		 deployBt=new JButton("deploy");
		 deployBt.addActionListener(this);
		 cancelBt=new JButton("cancel");
		 cancelBt.addActionListener(this);
		 
		 savedDeployBt=new JButton("deployed saved");
		 savedDeployBt.addActionListener(this);

		 southPanel.add(deployBt);
		 southPanel.add(savedDeployBt);
		 southPanel.add(cancelBt);
		 
		 
		 
		 
		 this.add("South",southPanel);
		 this.add("Center",centerPanel);
	}
	
	
	
	//************************STARTING SECOND CONSTRUCTOR************************
	
	
	PropertiesDialog(Base b,VLabel nodeInstance)
	{
		super(b, "properties", true);
		base=b;
		this.nodeInstance=nodeInstance;
		//System.out.println("the name of the author is vikrant sharma");
		this.setLayout(new BorderLayout());
		
			JPanel centerPanel=new JPanel();
			centerPanel.setLayout(null);
			//numberLb=new JLabel("number");			numberSp=new JSpinner(new SpinnerNumberModel(10,0, 100,1));		
			//numberLb.setBounds(10,10,100,20);		numberSp.setBounds(150,10,100,20);
			typeLb=new JLabel("type");				typeTf=new JTextField();
			typeLb.setBounds(10,40,100,20);			typeTf.setBounds(150,40,100,20);
			
			deploymentLb=new JLabel("deployment");	deploymentCb=new JComboBox(deploymentSt);
			deploymentLb.setBounds(10,70,100,20);	deploymentCb.setBounds(150,70,100,20);
			
			commRangeLb=new JLabel("max-range");	commRangeTf=new JTextField(); 						commRangeTf.setText(Float.toString(nodeInstance.commRange));
			commRangeLb.setBounds(10,100,100,20);	commRangeTf.setBounds(150,100,100,20);
			
			sensingRangeLb=new JLabel("sensing range"); sensingRangeTf=new JTextField(); 				sensingRangeTf.setText(Float.toString(nodeInstance.sensingRange));
			sensingRangeLb.setBounds(10,130,100,20);	sensingRangeTf.setBounds(150,130,100,20);
			
			
			powerLb=new JLabel("power");			powerTf=new JTextField();							powerTf.setText(Double.toString(nodeInstance.power));
			powerLb.setBounds(10,160,100,20);		powerTf.setBounds(150,160,100,20);
			
			sourceNodeChBx=new JCheckBox("source node");
			sourceNodeChBx.setSelected(nodeInstance.sourceNode);
			sourceNodeChBx.setBounds(10,190,150,20);
			
			sinkNodeChBx=new JCheckBox("sink node");
			sinkNodeChBx.setSelected(nodeInstance.sinkNode);
			sinkNodeChBx.setBounds(10,220,150,20);
			
			//centerPanel.add(numberLb);			centerPanel.add(numberSp);
			centerPanel.add(typeLb);			centerPanel.add(typeTf);
			centerPanel.add(deploymentLb);		centerPanel.add(deploymentCb);
			centerPanel.add(commRangeLb);		centerPanel.add(commRangeTf);
			centerPanel.add(sensingRangeLb);	centerPanel.add(sensingRangeTf);
			centerPanel.add(powerLb);			centerPanel.add(powerTf);
			centerPanel.add(sourceNodeChBx);
			centerPanel.add(sinkNodeChBx);
			
			
			
			
		 JPanel southPanel=new JPanel();
		 //southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		 applyBt=new JButton("apply");
		 applyBt.addActionListener(this);
		 cancelBt=new JButton("cancel");
		 cancelBt.addActionListener(this);

		 southPanel.add(applyBt);
		 southPanel.add(cancelBt);
		 
		 
		 
		 
		 this.add("South",southPanel);
		 this.add("Center",centerPanel);
	}
	


	
	//*************************************************************************
	
	
	
	
	//*********************IMPLEMENTING THE ACTION EVENT ************************
	public void actionPerformed(ActionEvent ae)
	{
	 if(ae.getSource()==deployBt)
	 {
		 
		 try
		 {
		 //**********INITIALIZING DEPLOYMENT PARAMETERS***************************
		  base.numberOfNodes=Integer.parseInt(numberSp.getValue().toString());
		  base.commRangePerNode=Float.parseFloat(commRangeTf.getText())*base.scale;
		  base.tempCommRangePerNode=Float.parseFloat(commRangeTf.getText())*base.scale;
		  base.sensingRangePerNode=Float.parseFloat(sensingRangeTf.getText())*base.scale;
		  base.powerPerNode=Integer.parseInt(powerTf.getText());
		  tempPower=Integer.parseInt(powerTf.getText());
		  base.deploymentScheme=deploymentCb.getSelectedItem().toString();
		  
		 //***********************************************************************
		 }
		 catch(NumberFormatException e)
		 {
			 MsgDialog md=new MsgDialog(e.toString(),base);
			 md.setBounds(200, 200,400,200);
			 md.setVisible(true);
			 
		 }
		 deployNodes();
		 //mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
		 
		 
	 }
	 
	 if(ae.getSource()==savedDeployBt)
	 {	
		 
		 
		 deploySavedNodesFS(Integer.parseInt(numberSp.getValue().toString()),Integer.parseInt(commRangeTf.getText()),Integer.parseInt(sensingRangeTf.getText()),Integer.parseInt(powerTf.getText()),deploymentCb.getSelectedItem().toString());
		 
		/* try
		 {
		 //**********INITIALIZING DEPLOYMENT PARAMETERS***************************
		  base.numberOfNodes=Integer.parseInt(numberSp.getValue().toString());
		  base.commRangePerNode=Integer.parseInt(commRangeTf.getText());
		  base.sensingRangePerNode=Integer.parseInt(sensingRangeTf.getText());
		  base.powerPerNode=Integer.parseInt(powerTf.getText());
		  tempPower=Integer.parseInt(powerTf.getText());
		  base.deploymentScheme=deploymentCb.getSelectedItem().toString();
		  
		 //***********************************************************************
		 }
		 catch(NumberFormatException e)
		 {
			 MsgDialog md=new MsgDialog(e.toString(),base);
			 md.setBounds(200, 200,400,200);
			 md.setVisible(true);
			 
		 }
		 deploySavedNodes();*/
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
	 }
	 
	 
	 
	 
	 if(ae.getSource()==cancelBt)
	 {
		 //System.out.println("cancel button have been pressed");
		 try
		 {
			 nodeInstance.setBackground(nodeInstance.defaultColor);
		 }catch(NullPointerException e){}
		 dispose();
	 }
	 
	 if(ae.getSource()==applyBt)
	 {
		 //System.out.println("apply button have been pressed");
		 nodeInstance.commRange=Integer.parseInt(commRangeTf.getText());
		 nodeInstance.sensingRange=Integer.parseInt(sensingRangeTf.getText());
		 nodeInstance.power=Integer.parseInt(powerTf.getText());
		 if(nodeInstance.power<1)
		 {
			 nodeInstance.defaultColor=Color.MAGENTA;
		 }
		 try
		 {
			 nodeInstance.setBackground(nodeInstance.defaultColor);
		 }
		 catch(NullPointerException e){e.printStackTrace();}
		 dispose();
		 
	 }
	}
	
	//*************FUNCTIONS****************************************************
	
	void deployNodes()
	{
		 x=Integer.parseInt(base.dxTf.getText());
		 y=Integer.parseInt(base.dyTf.getText());
		 //counter=Integer.parseInt(numberSp.getValue().toString());
		 counter=base.numberOfNodes;
		 
		
		 
		 //if(deploymentCb.getSelectedItem()=="random")
		 
		 if(base.deploymentScheme=="random")
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
						 					
						//nodeLb[i]=new VLabel(1,Color.BLUE,base);
						 nodeList.add(new VLabel(1,Color.BLUE,base));
						 
						 //----------------------------SETTING PARAMETERS OF NODE--------------------------
						 
						 //nodeLb[i].commRange=base.commRangePerNode;
						 //nodeLb[i].sensingRange=base.sensingRangePerNode;
						 //nodeLb[i].power=tempPower;
						 //nodeLb[i].defaultBufferSize=Integer.parseInt(bufferSizeSp.getValue().toString());
						 
						 nodeList.get(i).commRange=base.commRangePerNode;
						 nodeList.get(i).sensingRange=base.sensingRangePerNode;
						 nodeList.get(i).power=tempPower;
						// nodeList.get(i).defaultBufferSize=Integer.parseInt(bufferSizeSp.getValue().toString());
						 nodeList.get(i).defaultBufferSize=20;
						 
						 //--------------------------------------------------------------------------------
					 }
					 
					// System.out.println("check pt 6");
					 base.centerPanel.nodeDeployer(1,counter,xArray,yArray,nodeList);
					 base.centerPanel.removeMouseMotionListener(base.centerPanel);
				 }
			 };
			 //System.out.println("check pt 7");
			 thread1=new Thread(r);
			 thread1.start();
			 
/*			 Runnable r2=new Runnable()
			 {
				 public void run()
				 {
					 try
					 {
						 thread1.join();??
					 }
					 catch(Exception e){e.printStackTrace();}
				 }
			 };
			 new Thread(r2).start();*/
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
						 //nodeLb[i]=new VLabel(1,base.BLACK,base);
						 nodeList.add(new VLabel(1,base.BLACK,base));
						 
						 //----------------------------SETTING PARAMETERS OF NODE--------------------------
						// nodeLb[i].commRange=base.commRangePerNode;
						//nodeLb[i].sensingRange=base.sensingRangePerNode;
						 
						 nodeList.get(i).commRange=base.commRangePerNode;
						 nodeList.get(i).sensingRange=base.sensingRangePerNode;
						//--------------------------------------------------------------------------------
					 }
					 base.centerPanel.nodeDeployer(1,counter,xArray,yArray,nodeList);
					 base.centerPanel.removeMouseMotionListener(base.centerPanel);
				 }
			 };
			 new Thread(r).start();
		 }
		 try
		 {
			 nodeInstance.setBackground(nodeInstance.defaultColor);
		 }catch(NullPointerException e){System.out.println(e);}
		
		dispose();
		base.repaint();
		//base.revalidate();
	}
	
	//**************************************************************************
	
	
	void deploySavedNodes()
	{
		 x=Integer.parseInt(base.dxTf.getText());
		 y=Integer.parseInt(base.dyTf.getText());
		 //counter=Integer.parseInt(numberSp.getValue().toString());
		 counter=base.numberOfNodes;
		 
		
		 
		 //if(deploymentCb.getSelectedItem()=="random")
		 if(base.deploymentScheme=="random")
		 {
			 //System.out.println("ok button is pressed");

			 //*****************************STARTING THE THREAD**********************
			 Runnable r=new Runnable()
			 {
				 public void run()
				 {
					 try
					 {
					 ResultSet rs=base.joint.getDataByResultSet("select * from deployment");
					
					 for(int i=0;i<counter;i++)
					 {
						 try
						 {	
							 rs.next();											 
							 xArray[i]=rs.getInt("x");
							 yArray[i]=rs.getInt("y");
							 xArray[i]=xArray[i]-100;
							 yArray[i]=yArray[i]-100;
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
						 					
						 //nodeLb[i]=new VLabel(1,Color.BLUE,base);
						 nodeList.add(new VLabel(1,Color.BLUE,base));
						 
						 //----------------------------SETTING PARAMETERS OF NODE--------------------------												
						 nodeList.get(i).commRange=base.commRangePerNode;
						 nodeList.get(i).sensingRange=base.sensingRangePerNode;
						 nodeList.get(i).power=tempPower;
						 nodeList.get(i).defaultBufferSize=Integer.parseInt(bufferSizeSp.getValue().toString());
						 //--------------------------------------------------------------------------------
					 }
					 }catch(Exception e){e.printStackTrace();}
					 
					// System.out.println("check pt 6");
					 base.centerPanel.nodeDeployer(1,counter,xArray,yArray,nodeList);
					 base.centerPanel.removeMouseMotionListener(base.centerPanel);
				 }
			 };
			 //System.out.println("check pt 7");
			 new Thread(r).start();
		 }
		 
		 try
		 {
			 nodeInstance.setBackground(nodeInstance.defaultColor);
		 }catch(NullPointerException e){System.out.println(e);}
		
		dispose();
		base.repaint();
		//base.revalidate();
	}
	
	//**************************************************************************
	
	//*******FUNCTIONS FOR SCRIPT ASSISTANCE************************************
	
	void deployNodesFS(int numberOfNodes,int commRange,int sensingRange, int energy,String deploymentScheme)
	{
		 
		 try
		 {
		 //**********INITIALIZING DEPLOYMENT PARAMETERS***************************
		  base.numberOfNodes=numberOfNodes;
		  base.commRangePerNode=(int)(commRange*base.scale);
		  base.tempCommRangePerNode=(int)(commRange*base.scale);
		  base.sensingRangePerNode=(int)(sensingRange*base.scale);
		  base.powerPerNode=energy;
		  tempPower=energy;
		  base.deploymentScheme=deploymentScheme;
		 //***********************************************************************
		 }
		 catch(NumberFormatException e)
		 {
			 MsgDialog md=new MsgDialog(e.toString(),base);
			 md.setBounds(200, 200,400,200);
			 md.setVisible(true);
			 
		 }
		 deployNodes();
	}
	
	
	//--------------------------------------------------------------------------
	
	//*******FUNCTION FOR SCRIPT ASSISTANCE*************************************
	void deploySavedNodesFS(int numberOfNodes,int commRangePerNode,int sensingRangePerNode, int powerPerNode,String deploymentScheme)
	{	
	 try
	 {
	 //**********INITIALIZING DEPLOYMENT PARAMETERS***************************
		 base.numberOfNodes=numberOfNodes;
		 base.commRangePerNode=(int)(commRangePerNode*base.scale);
		 base.sensingRangePerNode=(int)(sensingRangePerNode*base.scale);
		 base.powerPerNode=powerPerNode;
		 tempPower=powerPerNode;
		 base.deploymentScheme=deploymentScheme;	  
	 //***********************************************************************
	 }
	 catch(NumberFormatException e)
	 {
		 MsgDialog md=new MsgDialog(e.toString(),base);
		 md.setBounds(200, 200,400,200);
		 md.setVisible(true);		 
	 }
	 deploySavedNodes();
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
