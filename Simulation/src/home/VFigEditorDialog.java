package home;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class VFigEditorDialog extends JDialog implements ActionListener
{
	Base base;
	VFigure vFigure;
	JToolBar editorTb;
	JButton newBt,openBt,saveBt,saveAsBt, loadAsForegroundBt,loadAsBackgroundBt,clearForegroundBt, clearBackgroundBt;
	JTextPane editorPa;
	
	JScrollPane jsp;
	JPanel westPanel;
	JList scriptListLs;
	JDialog thisDialog;
	VToolBox vToolBox;
	//String scriptListSt[];//={"<<","ddddd","ffffff","ggggggg","kkkkkkk","llllll"};
	VBaseOptionDialog baseOptionDialog;
	String currentFile;
	VFigEditorDialog thisEditor;
	String arguement="";
	
	JTabbedPane scriptEditorTp;
	
	VFigEditorDialog(Base b)
	{
		super(b, "V-Figure Editor",false);
		base=b;
		scriptEditorTp=new JTabbedPane();
		thisEditor=this;
		String dirName="figure";
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
		loadAsForegroundBt=new JButton("load foreground figure");
		loadAsForegroundBt.addActionListener(this);
		
		loadAsBackgroundBt=new JButton("load background figure");
		loadAsBackgroundBt.addActionListener(this);
		
		clearForegroundBt=new JButton("clear");
		clearForegroundBt.addActionListener(this);
		
		clearBackgroundBt=new JButton("clear");
		clearBackgroundBt.addActionListener(this);
		
		editorTb.add(saveBt);
		editorTb.addSeparator();
		editorTb.add(saveAsBt);
		editorTb.addSeparator();
		editorTb.add(loadAsForegroundBt);
		editorTb.addSeparator();
		editorTb.add(loadAsBackgroundBt);
		editorTb.addSeparator();
		editorTb.add(clearForegroundBt);
		editorTb.addSeparator();
		editorTb.add(clearBackgroundBt);
		
		
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
		
		
		
		JPanel figEditorPanel=new JPanel();
		JPanel upperFEPanel=new JPanel();
		JPanel lowerFEPanel=new JPanel();
		editorPa=new JTextPane();
		editorPa.setBounds(20,170,300,300);
		editorPa.setText("()\n()");
		jsp=new JScrollPane(editorPa);
		jsp.setBounds(0, 0,400,400);
		//centerPanel.add(jsp);
				
		JPanel eastPanel=new JPanel();
		
		JPanel southPanel=new JPanel();
		
		//----------------------------------------------------------------
		ScriptConfigPanel scPanel=new ScriptConfigPanel(base);
		scriptEditorTp.add("editor",jsp);
		scriptEditorTp.add("configuration",scPanel);
		
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
		
		if(ae.getSource()==clearForegroundBt)
		{
			base.foregroundFigureList.removeAll(base.foregroundFigureList);
		}
		
		if(ae.getSource()==clearBackgroundBt)
		{
			base.base.backgroundFigureList.removeAll(base.backgroundFigureList);
		}
		
		if(ae.getSource()==loadAsForegroundBt)
		{
			this.loadFigure(currentFile,base.foregroundFigureList);		
		}
		
		if(ae.getSource()==loadAsBackgroundBt)
		{
			this.loadFigure(currentFile,base.backgroundFigureList);		
		}
		
		if(ae.getSource()==saveAsBt)
		{
			//System.out.println("save as button have been pressed");
			//String st=editorTa.getText();
			//saveScript("test1",st);
			SaveFigAsDialog sd=new SaveFigAsDialog(base,this);
			sd.setBounds(900,200,300,150);
			sd.setVisible(true);						
		}
		
		if(ae.getSource()==saveBt)
		{
			if(!currentFile.isEmpty())
			{
				String st=editorPa.getText();
				saveFig(currentFile,st);
			}
			else
			{
				SaveFigAsDialog sd=new SaveFigAsDialog(base,this);
				sd.setBounds(900,200,300,150);
				sd.setVisible(true);
			}
		}
		
		
		
		if(ae.getSource()==newBt)
		{
			editorPa.setText("(,) \n \n (,)");
			currentFile="";
			thisEditor.setTitle("new figure");
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
	
	public void loadFigure(final String figureFile,final ArrayList<VFigure>figureList)
	{
		vFigure=new VFigure();
		Runnable r=new Runnable()
		{
			public void run()
			{
		
		System.out.println("run button have been pressed");
		//String st=editorPa.getText();
		String st=getScript(figureFile);
		System.out.println(figureFile);
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
				if(method.equals("line"))
				{
					try
					{
						String tempArgs[]=arguement.split(",");
						int x1=Integer.parseInt(tempArgs[0]);
						int y1=Integer.parseInt(tempArgs[1]);
						int length=Integer.parseInt(tempArgs[2]);
						double angle=Double.parseDouble(tempArgs[3]);
						int r=Integer.parseInt(tempArgs[4]);
						int g=Integer.parseInt(tempArgs[5]);
						int b=Integer.parseInt(tempArgs[6]);
						
						LineSegment lineSegment=new LineSegment(x1,y1,length,angle,r,g,b);
						vFigure.lineList.add(lineSegment);
					}
					catch(Exception e){}						
				}
				
				if(method.equals("center"))
				{					
					try
					{
						String tempArgs[]=arguement.split(",");
						
						System.out.println(arguement);
						//System.exit(0);
						int xc=Integer.parseInt(tempArgs[0]);
						int yc=Integer.parseInt(tempArgs[1]);
						
						vFigure.xc=xc;
						vFigure.yc=yc;
						
/*						for(int k=0;k<vFigure.lineList.size();k++)
						{							
							vFigure.lineList.get(k).distFromCenterStart=(int)vToolBox.getDist(xc, yc, vFigure.lineList.get(k).x1, vFigure.lineList.get(k).y1);
							vFigure.lineList.get(k).distFromCenterEnd=(int)vToolBox.getDist(xc, yc, vFigure.lineList.get(k).x2, vFigure.lineList.get(k).y2);
							
							vFigure.lineList.get(k).angleFromCenterStart=(int)vToolBox.getAngle(xc, yc, vFigure.lineList.get(k).x1, vFigure.lineList.get(k).y1);
							vFigure.lineList.get(k).angleFromCenterEnd=(int)vToolBox.getAngle(xc, yc, vFigure.lineList.get(k).x2, vFigure.lineList.get(k).y2);
														
							System.out.println("start angle :"+ vFigure.lineList.get(k).angleFromCenterStart);
							System.out.println("end angle :"+ vFigure.lineList.get(k).angleFromCenterEnd);
						}*/
						//System.exit(0);
					}
					catch(Exception e){e.printStackTrace();}						
				}
				
			}
			
		}
		
		figureList.add(vFigure);
		System.out.println(figureList.size());
		//System.exit(0);
			}
			};
			new Thread(r).start();		
	}
	
	
	//---------------FUNCTION TO SAVE THE SCRIPT------------------------------
	
	void saveFig(String fileName,String figSt)
	{
		   
		   try{
		      byte bWrite [] =figSt.getBytes();
		      OutputStream os = new FileOutputStream("figure/"+fileName);
		      for(int x=0; x < bWrite.length ; x++)
		      {
		         os.write( bWrite[x] ); // writes the bytes
		      }		
		      os.close();
		   }catch(Exception e){e.printStackTrace();}
	}
	
	//------------------------------------------------------------------------
	//---------------FUNCTION TO READ THE SCRIPT------------------------------
	void getScript(String fileName, JTextPane scriptPa)
	{
		try
		{
	      InputStream is = new FileInputStream("figure/"+fileName);
	      int size = is.available();
	      String st="";
	      for(int i=0; i< size; i++)
	      {
	    	  st=st+(char)is.read();
	         //System.out.print((char)is.read() + "  ");
	      }
	      is.close();
	      scriptPa.setText(st);
	   }catch(IOException e){e.printStackTrace();}
	}
	
	
	String getScript(String fileName)
	{
		String st="";
		try
		{
	      InputStream is = new FileInputStream("figure/"+fileName);
	      int size = is.available();
	      for(int i=0; i< size; i++)
	      {
	    	  st=st+(char)is.read();
	         //System.out.print((char)is.read() + "  ");
	      }
	      is.close();
	      //scriptPa.setText(st);
	   }catch(IOException e){e.printStackTrace();}
		return st;
	}
	
	//------------------------------------------------------------------------
	
	//--------------
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		VScriptEditor ve=new VScriptEditor (null);
		ve.setSize(900,600);
		ve.setVisible(true);
	}
}
