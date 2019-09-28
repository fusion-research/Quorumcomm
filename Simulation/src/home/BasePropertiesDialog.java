package home;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;
public class BasePropertiesDialog  extends JDialog implements ActionListener
{
	JButton cancelBt,deployBt;
	JLabel numberLb; 		JSpinner numberSp;
	JLabel typeLb;	 		JTextField typeTf;
	JLabel deploymentLb;	JComboBox deploymentCb;
	String deploymentSt[]={"fixed","random"};
	JLabel bufferSizeLb;	JSpinner bufferSizeSp;
	JLabel maxRangeLb;		JTextField maxRangeTf;
	JLabel powerLb;			JTextField powerTf;
	
	Base base;
	
	int x;
	int y;
	int xArray[]=new int[100];
	int yArray[]=new int[100];
	//VLabel baseLb[]=new VLabel[100];
	ArrayList<VLabel> baseList=new ArrayList<VLabel>();
	ImageIcon baseIcon=new ImageIcon("images/black.png");
	int counter;
	BasePropertiesDialog(){}
	BasePropertiesDialog(Base b)
	{
		super(b, " base station properties", true);
		base=b;
		//System.out.println("the name of the author is vikrant sharma");
		this.setLayout(new BorderLayout());
		
			JPanel centerPanel=new JPanel();
			centerPanel.setLayout(null);
			numberLb=new JLabel("number");			numberSp=new JSpinner(new SpinnerNumberModel(1,0, 20,1));			
			numberLb.setBounds(10,10,100,20);		numberSp.setBounds(150,10,100,20);
			typeLb=new JLabel("type");				typeTf=new JTextField();
			typeLb.setBounds(10,40,100,20);			typeTf.setBounds(150,40,100,20);
			
			deploymentLb=new JLabel("deployment");	deploymentCb=new JComboBox(deploymentSt);
			deploymentLb.setBounds(10,70,100,20);	deploymentCb.setBounds(150,70,100,20);
			
			maxRangeLb=new JLabel("communication range");		maxRangeTf=new JTextField("1500");
			maxRangeLb.setBounds(10,100,100,20);	maxRangeTf.setBounds(150,100,100,20);
			
			powerLb=new JLabel("power");			powerTf=new JTextField("99999999");
			powerLb.setBounds(10,130,100,20);		powerTf.setBounds(150,130,100,20);
;			
			bufferSizeLb=new JLabel("buffer size");	bufferSizeSp=new JSpinner(new SpinnerNumberModel(10,0,100,1));
			bufferSizeLb.setBounds(10,160,100,20);	bufferSizeSp.setBounds(150,160,100,20);
			
			centerPanel.add(numberLb);			centerPanel.add(numberSp);
			centerPanel.add(typeLb);			centerPanel.add(typeTf);
			centerPanel.add(deploymentLb);		centerPanel.add(deploymentCb);
			centerPanel.add(maxRangeLb);		centerPanel.add(maxRangeTf);
			centerPanel.add(powerLb);			centerPanel.add(powerTf);
			centerPanel.add(bufferSizeLb);		centerPanel.add(bufferSizeSp);
			
			
			
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
	
	//*********************IMPLEMENTING THE ACTION EVENT ************************
	public void actionPerformed(ActionEvent ae)
	{
	 if(ae.getSource()==deployBt)
	 {
		deployBaseStation(100,100,Integer.parseInt((bufferSizeSp.getValue()).toString()),deploymentCb.getSelectedItem().toString());
		dispose();
	 }
	 

	 
	 if(ae.getSource()==cancelBt)
	 {
		// System.out.println("cancel button have been pressed");
		 dispose();
	 }
	}
	
	
	//************************FUNCTIONS******************************************
	public void hideConnection()
	{
		for(int i=0;i<base.masterBSList.size();i++)
		{
			base.masterBSList.get(i).connectionList.removeAll(base.masterBSList.get(i).connectionList);
			for(int j=0;j<base.masterBSList.get(i).neighborList.size();j++)
			{
				base.masterBSList.get(i).connectionList.add(false);
			}
		}
	}
	//---------------------------------------------------------------------------
	
	public void showConnection()
	{
		for(int i=0;i<base.masterBSList.size();i++)
		{
			/*base.baseStationArray[i].connectionList.removeAll(base.baseStationArray[i].connectionList);*/
			base.masterBSList.get(0).connectionList.removeAll(base.masterBSList.get(0).connectionList);
			for(int j=0;j<base.masterBSList.get(i).neighborList.size();j++)
			{
				base.masterBSList.get(i).connectionList.add(true);
			}
		}
	}
	
	//***************************************************************************
	
	//----------------FUNCTION TO DEPLOY BASE STATION----------------------------
	
		void deployBaseStation(final int x,final int y,final int bufferSize,String deploymentType)
		{			
			 if(deploymentType=="fixed")
			 {
				 Runnable r=new Runnable()
				 {
					 public void run()
					 {

							/* xArray[i]=10;
							 yArray[i]=i*50;*/
							 VLabel tempBaseStation=new VLabel(3,base.BLACK,base);
							// baseList.add(tempBaseStation);
							 tempBaseStation.defaultBufferSize=bufferSize;
							 tempBaseStation.baseStationId=tempBaseStation;
							 tempBaseStation.lineColor=new Color(255,255,190);
							 //?????????????????????????????????????????????????
							 for(int j=0;j<base.masterNodeList.size();j++)
							 {
//								baseLb[i].neighborList.add(base.nodeArray[j]);
								tempBaseStation.neighborList.add(base.masterNodeList.get(j));
								tempBaseStation.connectionList.add(false);
							 }
							 tempBaseStation.isConnected=true;
							 
						 //System.exit(0);
						 base.centerPanel.baseDeployer(3,x,y,tempBaseStation);
						 base.centerPanel.removeMouseMotionListener(base.centerPanel);
					 }
				 };
				 new Thread(r).start();
			 }
		}
	
	
	//----------------------------------------------------------------------------
	public static void main(String args[])
	{
		BasePropertiesDialog pd=new BasePropertiesDialog(null);
		pd.setSize(300,400);
		pd.setVisible(true);
	}

}
