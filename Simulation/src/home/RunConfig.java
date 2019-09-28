package home;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RunConfig extends JDialog implements ActionListener,ItemListener
{
	VToolBox vToolBox;
	Algorithms algo;
	int x3,x4,y3,y4;
	
	String implementationAr[]={"flooding","directional flooding","directed diffusion","--------"};
	String runTypeAr[]={"single","multiple","sequential"};
	
	JLabel runTypeLb;		JComboBox runTypeCb;
	JLabel roundsLb;		JTextField roundsTf;
	JLabel implementLb;		JComboBox implementCb;
	JLabel timeToLiveLb;	JSpinner timeToLiveSp;
	
	
	JButton nextBt;
	JButton runBt;
	JButton cancelBt;
	Base base;
	RunConfig runConfig;
	
	int timeToLive=0;
	
	//------------INITIALIZING THE ARRAY-----------------------
	
	int xArray1[]=new int[1000];
	int yArray1[]=new int[1000];
	
	int xArray2[]=new int[1000];
	int yArray2[]=new int[1000];
	
	int xArray3[]=new int[1000];
	int yArray3[]=new int[1000];
	
	//---------------------------------------------------------
	
	
	
	
	
	RunConfig()
	{
		//default constructor
	}
	
	RunConfig(Base b)
	{
		super(b, "run configuration", true);
		this.base=b;
		runConfig=this;
		//base.runConfig=this;
		algo=new Algorithms(base);
		vToolBox=new VToolBox(base);
		JPanel northPanel=new JPanel();
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		runTypeLb=new JLabel("run type");			runTypeCb=new JComboBox(runTypeAr) ;
		runTypeLb.setBounds(10,10,100,20);			runTypeCb.setBounds(130,10,150,20);
													runTypeCb.addItemListener(this);
													
		
		roundsLb=new JLabel("rounds");				roundsTf=new JTextField("99");
		roundsLb.setBounds(10,40,100,20);			roundsTf.setBounds(130,40,150,20);
													roundsTf.setEnabled(false);
		
		implementLb=new JLabel("implement");		implementCb=new JComboBox(implementationAr) ;
		implementLb.setBounds(10,70,100,20);		implementCb.setBounds(130,70,150,20);
		
		timeToLiveLb=new JLabel("time to live");	timeToLiveSp=new JSpinner(new SpinnerNumberModel(13,0, 100,1));
		timeToLiveLb.setBounds(10,100,100,20);		timeToLiveSp.setBounds(130,100,150,20);
				
		
		
		
		centerPanel.add(runTypeLb);				centerPanel.add(runTypeCb);
		centerPanel.add(roundsLb);				centerPanel.add(roundsTf);
		centerPanel.add(implementLb);			centerPanel.add(implementCb);
		centerPanel.add(timeToLiveLb);			centerPanel.add(timeToLiveSp);
		
		JPanel southPanel=new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		nextBt=new JButton("next >");
		nextBt.setEnabled(false);
		runBt=new JButton("run");
		cancelBt=new JButton("cancel");
		
		nextBt.addActionListener(this);
		runBt.addActionListener(this);
		cancelBt.addActionListener(this);
		
		southPanel.add(cancelBt);
		southPanel.add(nextBt);
		southPanel.add(runBt);
		
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
	}
	
	//**************************IMPLEMENTING THE ACTION EVENT************************
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==nextBt)
		{
			//System.out.println("next button have been pressed");
		}
		if(ae.getSource()==runBt)
		{
			base.activeImplementation=new Integer(implementCb.getSelectedIndex()+1);
			base.implementationCb.setSelectedIndex(implementCb.getSelectedIndex());
			if(runTypeCb.getSelectedItem().toString()=="single" && implementCb.getSelectedItem()=="flooding")
			{
				dispose();
				base.connectionBreaker(base.masterNodeList);
				timeToLive=Integer.parseInt(timeToLiveSp.getValue().toString());
				vToolBox.runAlgo(base.whoIsSource,timeToLive);
			}
			
			if(runTypeCb.getSelectedItem().toString()=="multiple" && implementCb.getSelectedItem()=="flooding")
			{
				dispose();
				final int rounds=Integer.parseInt(roundsTf.getText());
				//base.vBaseOptionDialog.broadcast(base.baseStationArray[0]);
				vToolBox.broadcast(base.masterBSList.get(0));
				Runnable r=new Runnable()
				{
					public void run()
					{
						for(int i=0;i<rounds;i++)
						{
							//System.out.println(i);
							
							xArray1[i]=100+(i+1)*10;
							System.out.println(xArray1[i]);
							yArray1[i]=500-base.powerScale1;
							
							//try{Thread.sleep(20000);}catch(Exception e){}
							try{Thread.sleep(2000);}catch(Exception e){}
							synchronized(this)
							{
								try
								{
									base.connectionBreaker(base.masterNodeList);
									base.vBaseOptionDialog.getTemp(base.masterBSList.get(0),i%base.masterNodeList.size());
									System.out.println(yArray1[i]);
									
									base.graphPanel.plotGraph1(xArray1, yArray1,i,Color.BLUE);
									base.graphPanel.canMakeGraph1=true;
									base.graphPanel.repaint();
									base.graphPanel.revalidate();
							
								}
								catch(Exception e){System.out.println(e);}
							}
						}
						
						//for(int i=0;i<rounds;System.out.println(xArray1[i]),i++);


					}
				};
				new Thread(r).start();
			}
		}
		
		if(runTypeCb.getSelectedItem().toString()=="multiple" && implementCb.getSelectedItem()=="directional flooding")
		{
			//System.exit(0);///////////OK REPORT
			dispose();
			final int rounds=Integer.parseInt(roundsTf.getText());
			//base.vBaseOptionDialog.broadcast(base.baseStationArray[0]);
			vToolBox.broadcast(base.masterBSList.get(0));
			Runnable r=new Runnable()
			{
				public void run()
				{

					int counter=0;
					
					for(int i=0;i<rounds;i++)
					{
						//System.out.println(i);
						xArray2[i]=100+(i+1)*10;
						System.out.println(xArray2[i]);
						yArray2[i]=500-base.powerScale2;
						
						try{Thread.sleep(10000);}catch(Exception e){}
						synchronized(this)
						{
							base.connectionBreaker(base.masterNodeList);
							base.vBaseOptionDialog.getTemp(base.masterBSList.get(0),i);
							
							base.graphPanel.plotGraph2(xArray2, yArray2,i,Color.RED);
							base.graphPanel.canMakeGraph2=true;
							base.graphPanel.repaint();
							base.graphPanel.revalidate();
					
						}
						
					}
					

					//int 
				}
			};
			new Thread(r).start();
		}
		
		if(runTypeCb.getSelectedItem().toString()=="multiple" && implementCb.getSelectedItem()=="directed diffusion")
		{
			dispose();
			final int rounds=Integer.parseInt(roundsTf.getText());
			//.vBaseOptionDialog.broadcast(base.baseStationArray[0]);
			vToolBox.broadcast(base.masterBSList.get(0));
			Runnable r=new Runnable()
			{
				public void run()
				{
					try{Thread.sleep(20000);}catch(Exception e){}
					base.vBaseOptionDialog.floodForGradient(base.masterBSList.get(0));
					
					try{Thread.sleep(10000);}catch(Exception e){}
					
					for(int i=0;i<rounds;i++)
					{
						//System.out.println(i);
						
						xArray3[i]=100+(i+1)*10;
						System.out.println(xArray3[i]);
						yArray3[i]=500-base.powerScale3;
						
						try{Thread.sleep(15000);}catch(Exception e){}
						synchronized(this)
						{
							base.connectionBreaker(base.masterNodeList);							
							base.vBaseOptionDialog.getTemp(base.masterBSList.get(0),i);
							
							base.graphPanel.plotGraph3(xArray3, yArray3,i,base.GREEN);
							base.graphPanel.canMakeGraph3=true;
							base.graphPanel.repaint();
							base.graphPanel.revalidate();	
						}
					}
				}
			};
			new Thread(r).start();
		}
		
		if(ae.getSource()==cancelBt)
		{
			dispose();
		}
	}
	
	
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==runTypeCb)
		{
			if(runTypeCb.getSelectedItem()=="multiple")
			{
				roundsTf.setEnabled(true);
				nextBt.setEnabled(false);
			}
			if(runTypeCb.getSelectedItem()=="single")
			{
				roundsTf.setEnabled(false);
				nextBt.setEnabled(false);
			}
			
			if(runTypeCb.getSelectedItem()=="sequential")
			{
				roundsTf.setEnabled(true);
				nextBt.setEnabled(true);
			}
		}
		
		if(ie.getSource()==implementCb)
		{
			if(implementCb.getSelectedItem()=="flooding")
			{
				base.activeImplementation=base.FLOODING;
			}
			if(implementCb.getSelectedItem()=="directional flooding")
			{
				base.activeImplementation=base.DIRECTIONAL_FLOODING;
			}
			
			if(implementCb.getSelectedItem()=="directed diffusion")
			{
				base.activeImplementation=base.DIR_DIFFUSION;
			}
		}
	}
	//*******************************************************************************
	//-------------------------------------------------------------------------------

	public static void main(String[] args) 
	{
		//System.out.println("the name of the author is vikrant sharma");
	}

}
