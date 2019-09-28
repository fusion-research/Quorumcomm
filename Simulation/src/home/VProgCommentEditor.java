package home;
import java.io.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class VProgCommentEditor extends JDialog implements ActionListener
{
	Base base;
	JToolBar editorTb;
	JButton newBt,openBt,saveBt,saveAsBt, runBt;
	JTextPane editorPa;
	
	JScrollPane jsp;
	JPanel westPanel;
	JList scriptListLs;
	JDialog thisDialog;
	VToolBox vToolBox;
	//String scriptListSt[];//={"<<","ddddd","ffffff","ggggggg","kkkkkkk","llllll"};
	VBaseOptionDialog baseOptionDialog;
	String currentFile;
	VProgCommentEditor thisEditor;
	String arguement="";
	
	JTabbedPane scriptEditorTp;
	JPanel variablePanel;
	VProgCommentEditor(Base b)
	{
		
		super(b, "V-Comment Editor",false);
		base=b;
		scriptEditorTp=new JTabbedPane();
		thisEditor=this;
		String dirName="prog_comment";
		File file=new File(dirName);
		//if(file.isDirectory())
		//{
			String scriptListSt[]=scriptListSt=file.list();
		//}
		
		
		vToolBox=new VToolBox(base);
		baseOptionDialog=new VBaseOptionDialog(base,null);
		thisDialog=this;
		this.setLayout(new BorderLayout());
		JPanel northPanel=new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		editorTb=new JToolBar();
		
		newBt=new JButton("new");
		newBt.addActionListener(this);
		
		openBt=new JButton("open");
		openBt.addActionListener(this);
		
		saveBt=new JButton("save");
		saveBt.addActionListener(this);
		
		saveAsBt=new JButton("save as");
		saveAsBt.addActionListener(this);
		
		editorTb.add(newBt);
		editorTb.addSeparator();
		editorTb.add(openBt);
		editorTb.addSeparator();
		runBt=new JButton("run script");
		runBt.addActionListener(this);
		editorTb.add(saveBt);
		editorTb.addSeparator();
		editorTb.add(saveAsBt);
		editorTb.addSeparator();
		editorTb.add(runBt);
		
		northPanel.add(editorTb);
		
		
		westPanel=new JPanel();
		westPanel.setBackground(Color.WHITE);
		westPanel.setBorder(BorderFactory.createTitledBorder("select script"));
		scriptListLs=new JList(scriptListSt);
		scriptListLs.addMouseListener(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent me)
					{
						if(((JList)me.getSource()).getSelectedValue()=="<<")
						{
								thisDialog.remove(westPanel);				
							//	thisDialog.revalidate();
						}
						
						//if(((JList)me.getSource()).getSelectedValue()=="")
						//{
							if(me.getClickCount()==2)
							{
								String fileName=((JList)me.getSource()).getSelectedValue().toString();
								currentFile=fileName;	
								thisEditor.setTitle(currentFile);
								getScript(fileName,editorPa);						
							}
						//}
					}
				}
				);

		westPanel.add(scriptListLs);
		
		
		
		JPanel centerPanel=new JPanel(); 
		editorPa=new JTextPane(); 
		editorPa.setBounds(20,170,300,300);
		editorPa.setText("");
		jsp=new JScrollPane(editorPa);
		jsp.setBounds(0, 0,400,400);
		//centerPanel.add(jsp);
				
		JPanel eastPanel=new JPanel();
		
		JPanel southPanel=new JPanel();
		
		//----------------------------------------------------------------
		//ScriptConfigPanel scPanel=new ScriptConfigPanel(base);
		//VarConfigPanel varPanel=new VarConfigPanel(base);
		scriptEditorTp.add("editor",jsp);
		//scriptEditorTp.add("configuration",scPanel);
		//scriptEditorTp.add("variable configuration",varPanel);
				
		//---------------------------------------------------------------
		add("North",northPanel);
		add("West",westPanel);
		add("Center",scriptEditorTp);		
		add("South",southPanel);
	}

	/**
	 * @param args
	 */
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==openBt)
		{
			thisDialog.add("West",westPanel);
			//thisDialog.revalidate();
		}
		
		if(ae.getSource()==runBt)
		{
			Runnable r=new Runnable()
			{
				public void run()
				{
			
			System.out.println("run button have been pressed");
			String st=editorPa.getText();
			boolean methodReadable=false;
			boolean tagReadable=false;
			boolean argsReadable=false; 
			//System.out.println(st);
			String tempSt="";
			String method="";
			arguement="";
			for(int i=0;i<st.length();i++)
			{
				char c=st.charAt(i);
				switch(c)
				{
				case '<':
				{	
					System.out.println(method);
					method="";
					arguement="";
					System.out.println("open tag");
					methodReadable=false;
					tagReadable=true;
				}
				break;
				
				case '>':
				{
					System.out.println(tempSt);
					System.out.println("close tag");
					methodReadable=true;
					tagReadable=false;
					tempSt="";
				}	
				break;
				
				case '(':
				{
					argsReadable=true;
					methodReadable=false;					
					System.out.println("start arguement");
				}
				break;
				
				case ')':
				{
					argsReadable=false;				
					System.out.println("stop arguement");
				}
				break;
				
				default:
				{
					if(methodReadable)
					{
						method=method+c;
					}
					if(tagReadable)
					{
						tempSt=tempSt+c;
					}
					if(argsReadable)
					{
						arguement=arguement+c;
					}
				}
								
				};
				
				if(method.length()>0 && argsReadable==false)
				{
					System.out.println("method need to be called");
					if(method.equals("testfun"))
					{
						testFun();
					}
					if(method.equals("delay"))
					{
						try
						{
							int args=Integer.parseInt(arguement);
							System.out.println(args);							
							delay(args);
						}
						catch(Exception e){}
					}
					if(method.equals("deploy nodes"))
					{
						try
						{
							String tempArgs[]=arguement.split(",");
							int numberOfNodes=Integer.parseInt(tempArgs[0]);
							int commRange=Integer.parseInt(tempArgs[1]);
							int sensingRange=Integer.parseInt(tempArgs[2]);						
							base.toolKitDialog.propertiesDialog.deployNodesFS(numberOfNodes,commRange,sensingRange,500,base.RANDOM);
							//base.timeStamp=System.currentTimeMillis();
							
							Thread.sleep(3000);
							vToolBox.setTimeLine();
						}
						catch(Exception e){}						
					}
					
					if(method.equals("deploy actuators"))
					{
						try
						{
							String argsArr[]=arguement.split(",");
							int args1=Integer.parseInt(argsArr[0]);
							int args2=Integer.parseInt(argsArr[1]);
							
							System.out.println(args1);
							System.out.println(args2);
							base.toolKitDialog.actuatorPropertiesDialog.deployActuatorsFS(args1,args2,base.RANDOM);
						}catch(Exception e){}
					}
					
					if(method.equals("set_dropping_height"))
					{
						try
						{
							float args1=Float.parseFloat(arguement);
							base.alert.display("" + args1);	
							base.currentHeight=args1;
							
						}catch(Exception e){}
					}
					
					if(method.equals("set_dropping_speed"))
					{
						try
						{
							float args1=Float.parseFloat(arguement);
							base.alert.display("" + args1);	
							base.droppingSpeed=args1;
							
						}catch(Exception e){}
					}
					
					if(method.equals("deploy bs"))
					{
						try
						{
							String tempArgs[]=arguement.split(",");
							int x=Integer.parseInt(tempArgs[0]);
							int y=Integer.parseInt(tempArgs[1]);
							base.toolKitDialog.basePropertiesDialog.deployBaseStation(x,y,50,base.FIXED);						
						}
						catch(Exception e){}						
					}
					
					//--------------------------------------------------------------------
					
					if(method.equals("loadForegroundFig"))
					{
						try
						{
							String fileName=arguement;
							base.figEditorDialog.loadFigure(fileName,base.foregroundFigureList);
						}catch(Exception e){e.printStackTrace();}
					}
					
					if(method.equals("moveOnPath"))
					{
						try
						{
							//int speed=Integer.parseInt(arguement);
							
							base.deploymentHelicopterSpeed=Float.parseFloat(arguement);
							System.out.println("deployment speed :XXXXXXX"+ base.deploymentHelicopterSpeed);
							//System.exit(0);
							try{Thread.sleep(2000);}catch(Exception e){}
							vToolBox.moveOnPath(base.deploymentHelicopterSpeed);
							
						}catch(Exception e){e.printStackTrace();}
					}
					
					if(method.equals("setNodeCommRange"))
					{
						try
						{
							int commRange=Integer.parseInt(arguement);
							base.commRangePerNode=(int)(commRange*base.scale);
							JLabel commRangeLb=new JLabel("Communication range -----------------: "+commRange+"    meters");
							base.varTestDialog.centerPanel.add(commRangeLb);
							base.varTestDialog.centerPanel.revalidate();
							
							
						}catch(Exception e){e.printStackTrace();}
					}
					
					if(method.equals("setNodeSensingRange"))
					{
						try
						{
							int sensingRange=Integer.parseInt(arguement);
							base.sensingRangePerNode=(int)(sensingRange*base.scale);
							JLabel commRangeLb=new JLabel("Sensing range ----------------- : "+sensingRange+"    meters");
							base.varTestDialog.centerPanel.add(commRangeLb);
							base.varTestDialog.centerPanel.revalidate();
							
						}catch(Exception e){e.printStackTrace();}
					}
					
					if(method.equals("startCentrifugalDeployer"))
					{
						try
						{
							//float speed=Float.parseFloat(arguement);
							//Runnable r=new Runnable()
							//{
								//public void run()
								//{
									vToolBox.startCentrifugalDeployer();
								//}
							//};
							//new Thread(r).start();
						}catch(Exception e){e.printStackTrace();}
					}
					
					if(method.equals("startConventionalDeployer"))
					{
						try
						{
							//float speed=Float.parseFloat(arguement);
							//Runnable r=new Runnable()
							//{
								//public void run()
								//{
									vToolBox.startConventionalDeployer();
								//}
							//};
							//new Thread(r).start();
						}catch(Exception e){e.printStackTrace();}
					}
					
					if(method.equals("setDeploymentError"))
					{
						try
						{
							int deploymentError=Integer.parseInt(arguement);
							base.deploymentError=deploymentError;
							JLabel depErrLb=new JLabel("Deployment error ----------------- : "+deploymentError+"    %");
							base.varTestDialog.centerPanel.add(depErrLb);
							base.varTestDialog.centerPanel.revalidate();
						}catch(Exception e){e.printStackTrace();}
					}
					
				
					if(method.equals("setScale"))
					{
						try
						{
							float scale=Float.parseFloat(arguement);
							base.scaleTf.setText(Float.toString(scale));
							base.scale=scale;	
							JLabel scaleLb=new JLabel("Scale ----------------- : "+scale);
							base.varTestDialog.centerPanel.add(scaleLb);
							base.varTestDialog.centerPanel.revalidate();
						}catch(Exception e){e.printStackTrace();}
					}
					
					if(method.equals("setDroppingRate"))
					{
						try
						{
							float droppingRate=Float.parseFloat(arguement);
							base.droppingRate=droppingRate;	
							JLabel droppingRateLb=new JLabel("Dropping rate ----------------- : "+droppingRate+"    nodes/second");
							base.varTestDialog.centerPanel.add(droppingRateLb);
							base.varTestDialog.centerPanel.revalidate();
						}catch(Exception e){e.printStackTrace();}
					}
					
					if(method.equals("setNodeMass"))
					{
						try
						{
							float nodeMass=Float.parseFloat(arguement);					
							base.nodeMass=nodeMass;	
							JLabel massLb=new JLabel("Mass -----------------: "+nodeMass+"    Kg.");
							base.varTestDialog.centerPanel.add(massLb);
							base.varTestDialog.centerPanel.revalidate();
						}catch(Exception e){e.printStackTrace();}
					}
					
					
					if(method.equals("setDroppingHeight"))
					{
						try
						{
							double droppingHeight=Double.parseDouble(arguement);
							base.droppingHeight=droppingHeight;
							
							JLabel dropHeightLb=new JLabel("Dropping Height ----------------- : "+droppingHeight+"    meters");
							base.varTestDialog.centerPanel.add(dropHeightLb);
							base.varTestDialog.centerPanel.revalidate();
						}catch(Exception e){e.printStackTrace();}
					}
					
					
					
					if(method.equals("deployNodesFromAir"))
					{
						try
						{
							//System.exit(0);
							//float speed=Float.parseFloat(arguement);
							String tempArgs[]=arguement.split(",");
							int commRange=Integer.parseInt(tempArgs[0]);
							int sensingRange=Integer.parseInt(tempArgs[1]);	
							base.commRangePerNode=(int)(commRange*base.scale);
							base.sensingRangePerNode=(int)(sensingRange*base.scale);
							vToolBox.deployNodesExtended();
							
						}catch(Exception e){e.printStackTrace();}
					}
					
					if(method.equals("loadPath"))
					{
						try
						{
							String fileName=arguement;
							base.pathEditorDialog.loadPath(fileName,base.pathList);
						}catch(Exception e){e.printStackTrace();}
					}
					
					
					
					if(method.equals("rotate"))
					{
						try
						{
							String fileName=arguement;
							vToolBox.rotate();
							
						}catch(Exception e){e.printStackTrace();}
					}
					
					//setScatteringRange(int height,float radius, float mass, int rpm )
					if(method.equals("setScatteringRange"))
					{
						try
						{
							//String tempArgs[]=arguement.split(",");
							double rpm=Double.parseDouble(arguement);													
							vToolBox.setScatteringRange(rpm);
							
							JLabel rpmLb=new JLabel("RPM ----------------- : "+rpm);
							base.varTestDialog.centerPanel.add(rpmLb);
							base.varTestDialog.centerPanel.revalidate();
							
							//System.exit(0);
						}catch(Exception e){}
						
					}
					
					//--------------------------------------------------------------------
					if(method.equals("broadcast bs"))
					{
						
						try
						{
							int args=Integer.parseInt(arguement);
							System.out.println(args);		
							vToolBox.broadcast(base.masterBSList.get(args));					
						}
						catch(Exception e){}
					}
					
					if(method.equals("setNodeInitialEnergy"))
					{
						
						try
						{
							double args=Double.parseDouble(arguement);
							System.out.println(args);	
							vToolBox.setNodeInitialEnergy(args);					
						}
						catch(Exception e){}
					}
					
					
					if(method.equals("xxx"))
					{
						
						try
						{	

						}
						catch(Exception e){}
					}
					
					
					
					
					if(method.equals("broadcastForClustering"))
					{
						
						try
						{
							String args[]=arguement.split(",");
							int bsNo=Integer.parseInt(args[0]);
							int subDivisionSide=Integer.parseInt(args[1]);
							baseOptionDialog.broadcastForClusterng(base.masterBSList.get(bsNo), subDivisionSide);							
						}
						catch(Exception e){}
					}
					
					
					

					
					if(method.equals("hexagons mmu"))
					{
						Runnable r=new Runnable()
						{
							public void run()
							{
								vToolBox.generateHexagonsForActuators();
							}
						};
						new Thread(r).start();
					}
					
					if(method.equals("hexagons sn"))
					{
						try
						{
							    String args[]=arguement.split(",");
							    float rs=Float.parseFloat(args[0]);							 
							    rs=rs*base.scale;
							    Location startLoc=new Location(base.xMin+(int)(rs/2),base.yMin+(int)(rs/2),1,base);
							    //Location startLoc=new Location(110,110,1,base);
								vToolBox.generateHexagonsForSN(startLoc,rs);
						}catch(Exception e){}
			
					}
					
					if(method.equals("uniform distribute mmu"))
					{
						base.toolKitDialog.uniformDistributeMMU();
					}
					
					if(method.equals("broadcast mmu"))
					{
						base.centerPanel.daOptionWindow.broadcastMMU_FS();
					}
					
					if(method.equals("broadcast desired loc"))
					{
						baseOptionDialog.broadcastDesiredLoc(base.masterBSList.get(0));
					}

					if(method.equals("create path tree"))
					{
						
						baseOptionDialog.createPathTree_FS();
					}
					
					if(method.equals("open_deployment_monitor_panel"))
					{
						GadsDeploymentControlDialog dmp=new GadsDeploymentControlDialog(base);
						dmp.setBounds(800,200,600,500);
						dmp.setVisible(true);
					}
					
					if(method.equals("open_nads_results_dialog"))
					{
						NADSResults dhds2=new NADSResults(base);
						dhds2.setBounds(200,100,800,600);
						dhds2.setVisible(true);
					}
					
					if(method.equals("close_script_editor"))
					{
						dispose();
					}
					if(method.equals("even partition"))
					{
						try
						{					
							PartitionDialog pd=new PartitionDialog(base);
							int args=Integer.parseInt(arguement);
							pd.createEvenPartition(args);							
						}
						catch(Exception e){}
					}

					if(method.equals("path tree inspection"))
					{
						
						baseOptionDialog.pathTreeInspection();
					}
					
					if(method.equals("check interference"))
					{
						
						baseOptionDialog.checkInterference();
					}
					
					if(method.equals("place mmu"))
					{						
						baseOptionDialog.placeMMU();
					}
					
					if(method.equals("set_cluster_head"))
					{						
						vToolBox.setClusterHeads();
					}
					
					if(method.equals("broadcastClsuterHeadStatus"))
					{									
						vToolBox.broadcastClsuterHeadStatus();
					}
					
					if(method.equals("send_data_to_cluster_head"))
					{									
						vToolBox.sendDataToClusterHead();
					}
					
					if(method.equals("plot_location"))
					{
						base.candidateLoc=new Location(200,200,base);
					}
					
					if(method.equals("get_data_directly_from_cluster_head"))
					{									
						vToolBox.getAggrigatedDataDirectly(0);
					}
					
					if(method.equals("send_data_directly_from_cluster_head"))
					{									
						vToolBox.sendAggrigatedDataDirectlyToBS();
					}
					
/*					if(method.equals("update_bs_cluster_list"))
					{			
						//String args[]=arguement.split(",");
						int baseStationId=Integer.parseInt(arguement);
						vToolBox.updateBS_Cluster_List(baseStationId);
					}*/
					
					if(method.equals("update_bs_cluster_list"))
					{						
							for(int k=0;k<base.masterBSList.size();k++)
							{
								vToolBox.updateBS_Cluster_List(k);
							}
					}
					
					//---------------SHOWING/HIDING CONNECTIONS-----------------
					if(method.equals("interClusterConnectionShowable"))
					{						
						try
						{
							boolean args=Boolean.parseBoolean(arguement);
							System.out.println(args);		
							base.interClusterConnectionShowable=args;					
						}
						catch(Exception e){}
					}
					
					if(method.equals("connectionShowable"))
					{						
						try
						{
							boolean args=Boolean.parseBoolean(arguement);
							System.out.println(args);		
							base.connectionShowable=args;					
						}
						catch(Exception e){}
					}
					//----------------------------------------------------------
					if(method.equals("startEDSRoundsWithMultihop"))
					{	
						//int args=Integer.parseInt(arguement);
						//System.out.println("ARGUEMENT :"+ args);
						//System.exit(0);
						vToolBox.startEDSRoundsWithMultihop(100);
					}		
					
					if(method.equals("startEDSRoundsWithDirectComm"))
					{	
						//int args=Integer.parseInt(arguement);
						//System.out.println("ARGUEMENT :"+ args);
						//System.exit(0);
						vToolBox.startEDSRoundsWithDirectComm(5000);
					}
					
					if(method.equals("test_bt"))
					{
						new Controller(base);
					}
					
					if(method.equals("getHDistInAir"))
					{
						System.out.println(arguement);
						System.out.println(vToolBox.getHorizontalDistWithAir(1000,0.05f,0.250f,1.255f,0.5f,4.0f,500));
						System.out.println(0);
					}
					
					if(method.equals("setOptimalNodeCount"))
					{	
						base.optimalNodeCount=vToolBox.getOptimalNodeCount();
						JLabel optimalNodeCountLb=new JLabel("Optimal node count -----------------: "+base.optimalNodeCount);
						base.varTestDialog.centerPanel.add(optimalNodeCountLb);
						base.varTestDialog.centerPanel.revalidate();
					}
					
					if(method.equals("setOptimalNodeDeploymentRate"))
					{	
						try
						{
							//String tempArgs[]=arguement.split(",");
							float pathLength=Float.parseFloat(arguement);
							//float extraPercentage=Float.parseFloat(tempArgs[1]);
							base.droppingRate=(float)vToolBox.getOptimalDeploymentRate(pathLength,10);
							JLabel optimalDeploymentRateLb=new JLabel("Optimal dropping rate -----------------: "+base.droppingRate);
							base.varTestDialog.centerPanel.add(optimalDeploymentRateLb);
							base.varTestDialog.centerPanel.revalidate();
							//base.droppingRate=1.6;
						}
						catch(Exception e){e.printStackTrace();}
					}
					
					if(method.equals("setDeploymentHelicopterSpeed"))
					{
						try
						{
							float deploymentHelicopterSpeed=Float.parseFloat(arguement);
							base.deploymentHelicopterSpeed=deploymentHelicopterSpeed;
							JLabel helicopterSpeedLb=new JLabel("deployment helicopter speed -----------------: "+base.deploymentHelicopterSpeed+"    m/s");
							base.varTestDialog.centerPanel.add(helicopterSpeedLb);
							base.varTestDialog.centerPanel.revalidate();
						}catch(Exception e){e.printStackTrace();}
					}
				}				
			}
		}
	};
	new Thread(r).start();
	}
		
		if(ae.getSource()==saveAsBt)
		{
			//System.out.println("save as button have been pressed");
			//String st=editorTa.getText();
			//saveScript("test1",st);
			SaveCommentAsDialog sd=new SaveCommentAsDialog(base,this);
			sd.setBounds(900,200,300,150);
			sd.setVisible(true);						
		}
		
		if(ae.getSource()==saveBt)
		{
			if(!currentFile.isEmpty())
			{
				String st=editorPa.getText();
				saveScript(currentFile,st);
			}
			else
			{
				SaveCommentAsDialog sd=new SaveCommentAsDialog(base,this);
				sd.setBounds(900,200,300,150);
				sd.setVisible(true);
			}
		}
		
		
		
		if(ae.getSource()==newBt)
		{
			editorPa.setText("");
			currentFile="";
			thisEditor.setTitle("new script");
		}
	}
	
	public void delay(int time)
	{
		try
		{
			Thread.sleep(time);
		}catch(Exception e){}
	}
	
	public void testFun()
	{
		JDialog testDialog=new JDialog();
		testDialog.setBounds(200,300,300,300);
		testDialog.setVisible(true);				
	}
	
	
	//---------------FUNCTION TO SAVE THE SCRIPT------------------------------
	
	void saveScript(String fileName,String scriptSt)
	{
		   
		   try{
		      byte bWrite [] =scriptSt.getBytes();
		      OutputStream os = new FileOutputStream("prog_comment/"+fileName);
		      for(int x=0; x < bWrite.length ; x++)
		      {
		         os.write( bWrite[x] ); // writes the bytes
		      }		
		      os.close();
		   }catch(Exception e){base.alert.display(e+"");}
	}
	
	//------------------------------------------------------------------------
	//---------------FUNCTION TO READ THE SCRIPT------------------------------
	void getScript(String fileName, JTextPane scriptPa)
	{
		try
		{
	      InputStream is = new FileInputStream("prog_comment/"+fileName);
	      int size = is.available();
	      String st="";
	      for(int i=0; i< size; i++)
	      {
	    	  st=st+(char)is.read();
	         //System.out.print((char)is.read() + "  ");
	      }
	      is.close();
	      scriptPa.setText(st);
	   }catch(IOException e){base.alert.display(e+"");}
	}
	//------------------------------------------------------------------------
	
	//--------------
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		VProgCommentEditor ve=new VProgCommentEditor (null);
		ve.setSize(900,600);
		ve.setVisible(true);
	}
}
