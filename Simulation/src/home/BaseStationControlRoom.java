package home;
import java.awt.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.*;

import java.util.*;
import java.awt.event.*;
public class BaseStationControlRoom extends JFrame implements ActionListener,ItemListener
{
	VLabel baseTemp;
	int counter=0;
	int highestTemp=0;
	int criticalTemp;
	String criticalLoc="";
	
	int xArray[]=new int[1000];
	int yArray[]=new int[1000];
	
	JLabel baseSelectLb;			JComboBox baseSelectCb;
	
	
	JLabel criticalTemLb;			JSpinner ctriticalTempSp;
	JLabel packetSentLb;			JTextField packetSentTf;
	JLabel packetRecvLb;			JTextField packetRecvTf;
	JLabel criticalTempLb;			JTextField criticalTempTf;
	JLabel criticalLocLb;			JTextField criticalLocTf;
	JLabel highestTempLb;			JTextField highestTempTf;
	
	JTable packetInfoTable;
	JScrollPane tableSp;
	
	
	JLabel baseLocLb;				JTextField baseLocTf;
	
	//-------------------------------------------------------------
	
	JLabel setCriticalTempLb;		JSpinner setCriticalTempSp;
	
	//-------------------------------------------------------------
	
	HashSet <VLabel> hashSet=new HashSet<VLabel>();
	
	boolean carryOn=true;
	JButton closeBt;
	JButton applyBt;
	
	Base base;
	DefaultTableModel model;
	//--------------INITIALIZING MISC OBJECTS--------------------
	JPanel monitorPanel;
	//----------------------------------------------------------
	BaseStationControlRoom(Base b)
	{
		super("base station control room");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/titlelogo.png"));
		this.base=b;
		this.setBounds(100,100,800,600);
		//String baseSelectSt="";
		this.setLayout(new BorderLayout());
		this.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){carryOn=false;}});
		JPanel northPanel=new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		baseSelectLb=new JLabel("select base station");				baseSelectCb=new JComboBox();
		baseSelectLb.setBounds(10, 10,100,10);						baseSelectCb.setBounds(150,10,100,10);
																	for(int i=0;i<base.masterBSList.size();i++,baseSelectCb.addItem(Integer.toString(i-1)));
																	
																	
		northPanel.add(baseSelectLb);								northPanel.add(baseSelectCb);
		
		
		JPanel centerPanel=new JPanel();
		centerPanel.setBackground(Color.white);
		
		centerPanel.setLayout(null);
		
		JPanel infoPanel=new JPanel();
		infoPanel.setBorder(BorderFactory.createTitledBorder("information"));
		infoPanel.setBounds(10,10,770,250);
		infoPanel.setBackground(Color.white);
		infoPanel.setLayout(null);
		
		baseLocLb=new JLabel("base station location :");			baseLocTf=new JTextField();
		baseLocLb.setBounds(10,20,150,15);							baseLocTf.setBounds(200,20,150,16);
																	baseLocTf.setFont(new Font("Arial Narrow",Font.BOLD,11));
																	baseLocTf.setBackground(Color.white);
																	baseLocTf.setEditable(false);
	
		packetSentLb=new JLabel("packe sent :");					packetSentTf=new JTextField();
		packetSentLb.setBounds(10,40,150,15);						packetSentTf.setBounds(200,40,150,16);
																	packetSentTf.setFont(new Font("Arial",Font.BOLD,11));
																	packetSentTf.setBackground(Color.white);
																	packetSentTf.setEditable(false);
		
		packetRecvLb=new JLabel("packet recieved :");				packetRecvTf=new JTextField();
		packetRecvLb.setBounds(10,60,150,15);						packetRecvTf.setBounds(200,60,150,16);
																	packetRecvTf.setFont(new Font("Arial",Font.BOLD,11));
																	packetRecvTf.setBackground(Color.WHITE);
																	packetRecvTf.setEditable(false);
		
		criticalTempLb=new JLabel("critical temperture :");			criticalTempTf=new JTextField();
		criticalTempLb.setBounds(420,20,150,15);					criticalTempTf.setBounds(600,20,150,16);
																	criticalTempTf.setFont(new Font("Arial",Font.BOLD,11));
																	criticalTempTf.setBackground(Color.WHITE);
																	criticalTempTf.setEditable(false);
		
		criticalLocLb=new JLabel("critical location :");			criticalLocTf=new JTextField();
		criticalLocLb.setBounds(420,40,150,15);						criticalLocTf.setBounds(600,40,150,16);
																	criticalLocTf.setFont(new Font("Arial",Font.BOLD,11));
																	criticalLocTf.setBackground(Color.WHITE);
																	criticalLocTf.setEditable(false);
		
		highestTempLb=new JLabel("highest temperature :");			highestTempTf=new JTextField();
		highestTempLb.setBounds(420,60,150,15);						highestTempTf.setBounds(600,60,150,16);
																	highestTempTf.setFont(new Font("Arial",Font.BOLD,11));
																	highestTempTf.setBackground(Color.WHITE);
																	highestTempTf.setEditable(false);
		
		packetInfoTable=new JTable();
		packetInfoTable.setBackground(Color.WHITE);
		tableSp=new JScrollPane(packetInfoTable);
		tableSp.setBounds(10,90,750,150);
		
		
		model=(DefaultTableModel)packetInfoTable.getModel();
		model.addColumn("pack id", new Object[]{});		
		model.addColumn("protocol", new Object[]{});
		model.addColumn("hop count", new Object[]{});
		model.addColumn("source", new Object[]{});
		model.addColumn("loc", new Object[]{});
		model.addColumn("temp", new Object[]{});
		
		
		
		
		
		
		
		
		infoPanel.add(baseLocLb);									infoPanel.add(baseLocTf);
		infoPanel.add(packetSentLb);								infoPanel.add(packetSentTf);
		infoPanel.add(packetRecvLb);								infoPanel.add(packetRecvTf);
		infoPanel.add(criticalTempLb);								infoPanel.add(criticalTempTf);
		infoPanel.add(criticalLocLb);								infoPanel.add(criticalLocTf);
		infoPanel.add(highestTempLb);								infoPanel.add(highestTempTf);
		infoPanel.add(tableSp);
		
		
		
		JPanel	settingPanel=new JPanel();
		settingPanel.setBorder(BorderFactory.createTitledBorder("settings"));
		settingPanel.setBounds(780,10,300,250);
		settingPanel.setBackground(Color.white);
		settingPanel.setLayout(null);
		
		setCriticalTempLb=new JLabel("crtical temperature");		setCriticalTempSp=new JSpinner(new SpinnerNumberModel(70,0,200,1));
		setCriticalTempLb.setBounds(10,20,150,15); 					setCriticalTempSp.setBounds(180,20,100,15);
		
		applyBt=new JButton("apply");
		applyBt.setBounds(200,220,80,19);
		applyBt.addActionListener(this);
		
		settingPanel.add(setCriticalTempLb);		settingPanel.add(setCriticalTempSp);
		
		settingPanel.add(applyBt);
		
		//---------------------VISUAL DISPLAY PANEL-------------------
		
		monitorPanel=new MonitorPanel(base);
		monitorPanel.setBorder(BorderFactory.createTitledBorder("monitor"));
		monitorPanel.setLayout(null);
		monitorPanel.setBackground(Color.WHITE);
		monitorPanel.setBackground(new Color(245,250,250));
		monitorPanel.setBounds(10,260,1070,400);
		
		
		centerPanel.add(infoPanel);
		centerPanel.add(settingPanel);
		centerPanel.add(monitorPanel);
		
		
		JPanel southPanel=new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		closeBt=new JButton("close");
		closeBt.addActionListener(this);
		southPanel.add(closeBt);
		
		
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
		
		baseTemp=base.masterBSList.get(Integer.parseInt((String)baseSelectCb.getSelectedItem()));
		//baseTemp=base.masterBSList.get(0);
		Runnable r=new Runnable()
		{
			public void run()
			{
				while(true && carryOn)
				{
					try
					{
						ResultSet rs=base.joint.getDataByResultSet("select * from daproperties");
						rs.next();
						criticalTemp=rs.getInt("criticaltemp");
						criticalTempTf.setText(Integer.toString(criticalTemp));
						
						
					}
					catch(SQLException ex){}
					
					
					
					String baseLoc=baseTemp.getX()+","+baseTemp.getY();
					baseLocTf.setText(baseLoc);
					
					int packetQueueSize=baseTemp.packetQueue.size();
					packetRecvTf.setText(Integer.toString(packetQueueSize));
					
				
					
					if(packetQueueSize>counter)
					{
							for(int i=counter;i<packetQueueSize;i++)
							{
								try
								{
									Packet packet=baseTemp.packetQueue.get(i);
									System.out.println("packet Source= "+packet.source.uniqueId+"  packet source type:  "+packet.sourceType);
								
									int packetId=packet.identifier;
									int protocol=packet.protocolType;
									int hopCount=packet.hopCount;
									VLabel sourceNode=packet.source;
									int sourceId=sourceNode.uniqueId;
									String loc=sourceNode.getX()+","+sourceNode.getY();
									int temp=(Integer)packet.data;
									
									if(temp>criticalTemp)
									{
										if(hashSet.add(sourceNode))
										{
											xArray[hashSet.size()-1]=sourceNode.getX();
											yArray[hashSet.size()-1]=sourceNode.getY();
											JLabel tempLb=new JLabel(new ImageIcon("images/fire.gif"));
											tempLb.setBounds(sourceNode.getX()-100,sourceNode.getY()-100,14,23);
											monitorPanel.add(tempLb);
											monitorPanel.repaint();
											monitorPanel.revalidate();
											//monitorPanel.repaintMonitor();
										}
									}
									if(temp>highestTemp)
									{
										highestTemp=temp;
										criticalLoc=loc;
										
										highestTempTf.setText(Integer.toString(highestTemp));
										criticalLocTf.setText(criticalLoc);									
									}
								
									model.addRow(new Object[]{packetId,protocol,hopCount,sourceId,loc,temp});
								}
								catch(Exception e){e.printStackTrace();}
								
							}
							counter=packetQueueSize;
					}
					
					
					try{Thread.sleep(5000);}catch(Exception e){}
						
				}
			}
		};
		new Thread(r).start();
		
	}
	//*************************IMPLEMENTING ACTION EVENTS********************
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==applyBt)
		{
			System.out.println("apply button have been pressed by vikrant sharma");
			int criticalTemp=Integer.parseInt(setCriticalTempSp.getValue().toString());
			base.joint.fix("update daproperties set criticaltemp="+criticalTemp);
		}
		
		if(ae.getSource()==closeBt)
		{
			dispose();
			carryOn=false;
			System.out.println("cancel button have been pressed by vikrant sharma");
		}
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		
	}
	//***********************************************************************
	public static void main(String args[])
	{
		System.out.println("the name of the auhtor is vikrant sharma");
		BaseStationControlRoom bscr=new 	BaseStationControlRoom(new Base());
		bscr.setSize(500,400);
		bscr.setVisible(true);
		
	}

}



