package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class GraphPanel extends JPanel 
{
	int graphFactor=1;
	int imageFactor=1;
	JLabel horizontalLb,verticalLb;
	JButton closeBt,minBt,popOutBt;
	//JLabel closeLb,minLb;
	ArrayList<ArrayList<VCoordinate>>graphList=new ArrayList<ArrayList<VCoordinate>>();
	
	
	Base base;
	int xList[];
	int yList[];
	
	ArrayList<Integer>xGraphLabel=new ArrayList<Integer>();
	ArrayList<Integer>yGraphLabel=new ArrayList<Integer>();
	
	ImageIcon minIcon=new ImageIcon("images/minimize.jpg");
	ImageIcon closeIcon=new ImageIcon("images/close.jpg");
	ImageIcon popOutIcon=new ImageIcon("images/popout.jpg");
	
	//-----------------------INITIALIZING ARRAYS-----------------
	
	int xArray1[];
	int yArray1[];
	
	int xArray2[]=new int[1000];
	int yArray2[]=new int[1000];
	
	int xArray3[]=new int[1000];
	int yArray3[]=new int[1000];
	
	//-----------------------------------------------------------
	//-----------------INITIALIZING COUNTERS---------------------
	int counter;
	int counter1;
	int counter2;
	int counter3;
	
	//-----------------------------------------------------------
	
	//----------------INITIALIZING GRAPH PLOTTER----------------
	boolean canMakeGraph=false; 
	boolean canMakeGraph1=false;
	boolean canMakeGraph2=false;
	boolean canMakeGraph3=false;
	//----------------------------------------------------------
	
	//----------------INITIALIZING CHECK COLOR-------------------
	Color checkColor=Color.red;
	Color checkColor1=Color.red;
	Color checkColor2=Color.red;
	Color checkColor3=Color.red;
	//-----------------------------------------------------------
	
	
	GraphPanel(Base b)
	{
		base=b;
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		closeBt=new JButton(closeIcon);
		closeBt.setBounds(5,3,17,15);
		closeBt.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent me)
					{

						base.remove(base.graphPanel);
						base.add("Center",base.centerPanel);
						base.upperSouthPanel.remove(base.graphPanelBt[0]);
						base.repaint();
//						base.revalidate();

					}
					
					public void  mouseEntered(MouseEvent me)
					{
						closeBt.setForeground(Color.RED);
					}
					
					public void  mouseExited(MouseEvent me)
					{
						closeBt.setForeground(Color.BLACK);
					}
				}
		);
		
		minBt=new JButton(minIcon);
		minBt.setBounds(24,3,17,15);
		minBt.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent me)
					{
						base.remove(base.graphPanel);
						base.add("Center",base.centerPanel);
						base.repaint();
						//base.revalidate();
					}
					
					public void  mouseEntered(MouseEvent me)
					{
						minBt.setForeground(Color.RED);
					}
					
					public void  mouseExited(MouseEvent me)
					{
						minBt.setForeground(Color.BLACK);
					}
				}
		);
		
		
		popOutBt=new JButton(popOutIcon);
		popOutBt.setBounds(43,3,17,15);
		popOutBt.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent me)
					{
						base.remove(base.graphPanel);
						base.add("Center",base.centerPanel);
						base.repaint();
						JFrame graphFrame=new JFrame("graph");
						graphFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/titlelogo.png"));
						base.graphPanel.remove(minBt);
						base.graphPanel.remove(closeBt);
						base.graphPanel.remove(popOutBt);
						graphFrame.add(base.graphPanel);
						graphFrame.setSize(1000,800);
						graphFrame.setVisible(true);
						//base.revalidate();
					}
					
					public void  mouseEntered(MouseEvent me)
					{
						popOutBt.setForeground(Color.RED);
					}
					
					public void  mouseExited(MouseEvent me)
					{
						popOutBt.setForeground(Color.BLACK);
					}
				}
		);
		
		verticalLb=new JLabel("nodes alive ^");
		verticalLb.setBounds(5,300,120,20);
		horizontalLb=new JLabel("base station requests------>>");
		horizontalLb.setBounds(250,585,200,20);
		
		add(closeBt);
		add(minBt);
		add(popOutBt);
		add(verticalLb);
		add(horizontalLb);

	}
	

//*******************************************************************************************************	
	public void plotGraph(ArrayList<VCoordinate> coordinateList,Color checkColor,float xMultiplier, float yMultiplier)
	{
		
		for(int j=0;j<coordinateList.size();j++)
		{
			//coordinateList.get(j).x=(coordinateList.get(j).x)*2-20;
			//coordinateList.get(j).y=575-(coordinateList.get(j).y*4);
			coordinateList.get(j).x=((int)(((float)coordinateList.get(j).x)*xMultiplier)-20)/graphFactor;
			coordinateList.get(j).y=(575-(int)(((float)coordinateList.get(j).y*yMultiplier)))/graphFactor;
			
			coordinateList.get(j).color=checkColor;
		}

		graphList.add(coordinateList);
	}
	
	//****************************************************************************************************
	
	public void plotGraph1(int xList[],int yList[],int counter,Color checkColor)
	{
		this.counter1=counter;
		this.checkColor1=checkColor;
		this.xArray1=xList;
		this.yArray1=yList;
		for(int i=0;i<xArray1.length;i++)
		{
			yArray1[i]=500-yArray1[i];
			xArray1[i]=150+xArray1[i];
		}
	}
	
	public void plotGraph2(int xList[],int yList[],int counter,Color checkColor)
	{
		this.counter2=counter;
		this.checkColor2=checkColor;
		this.xArray2=xList;
		this.yArray2=yList;
	}
	
	
	public void plotGraph3(int xList[],int yList[],int counter,Color checkColor)
	{
		this.counter3=counter;
		this.checkColor3=checkColor;
		this.xArray3=xList;
		this.yArray3=yList;
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//-------------DRAWING THE GRAPH BASE AND GRID LINES----------------------------
		
		g.drawLine(100/graphFactor,25/graphFactor,100/graphFactor,575/graphFactor);
		
		g.setColor(new Color(233,233,233));
		for(int i=20;i<=900;i=i+20)
		//g.drawLine(100+i,25,100+i,575); // TEMP DISABLED
		
		g.setColor(Color.BLACK);
		g.drawLine(100/graphFactor,575/graphFactor,1000/graphFactor,575/graphFactor);
		
		g.setColor(new Color(244,244,244));
		for(int i=20;i<=550;i=i+20)
		//g.drawLine(101,575-i,1000,575-i); //TEMP DISABLED
		//------------------------------------------------------------------------------
		
		g.setColor(checkColor);
		g.fillOval(100-2,575-2,4,4);
		
		for(int i=0;i<graphList.size();i++)
		{
			ArrayList<VCoordinate>coordinateList=graphList.get(i);
			int x[]=new int[100];
			int y[]=new int[100];
			for(int j=0;j<coordinateList.size();j++)
			{
				x[j]=coordinateList.get(j).x;
				y[j]=coordinateList.get(j).y;
				g.setColor(coordinateList.get(j).color);
				int shape=coordinateList.get(j).shape;
				switch(shape)
				{
				case 1:
					{
						   Image img1 = Toolkit.getDefaultToolkit().getImage("images/blacktriangle.png");
						   g.drawImage(img1,x[j]-5/imageFactor,y[j]-5/imageFactor,10/imageFactor,10/imageFactor, this);
					}
					break;
				case 2:
				{
					   Image img1 = Toolkit.getDefaultToolkit().getImage("images/graytriangle.png");
					    g.drawImage(img1,x[j]-5/imageFactor,y[j]-5/imageFactor,10/imageFactor,10/imageFactor, this);
				}
				break;
				
				case 3:
				{
					   Image img1 = Toolkit.getDefaultToolkit().getImage("images/blackcircle.png");
					    g.drawImage(img1,x[j]-5/imageFactor,y[j]-5/imageFactor,9/imageFactor,9/imageFactor, this);
				}
				break;
				
				case 4:
				{
					   Image img1 = Toolkit.getDefaultToolkit().getImage("images/graycircle.png");
					    g.drawImage(img1,x[j]-5/imageFactor,y[j]-5/imageFactor,9/imageFactor,9/imageFactor, this);
				}
				break;
				case 5:
				{
					   Image img1 = Toolkit.getDefaultToolkit().getImage("images/redsquare.png");
					    g.drawImage(img1,x[j]-5/imageFactor,y[j]-5/imageFactor,9/imageFactor,9/imageFactor, this);
				}
				break;
				
				case 6:
				{
					   Image img1 = Toolkit.getDefaultToolkit().getImage("images/reddiamond.png");
					    g.drawImage(img1,x[j]-5/imageFactor,y[j]-5/imageFactor,11/imageFactor,11/imageFactor, this);
				}
				break;
				
				case 7:
				{
					   Image img1 = Toolkit.getDefaultToolkit().getImage("images/graydiamond.png");
					    g.drawImage(img1,x[j]-5/imageFactor,y[j]-5/imageFactor,11/imageFactor,11/imageFactor, this);
				}
				break;
				}
				//g.fillOval(x[j]-3,y[j]-3,7,7);
				//g.fillRect(x[j]-3, y[j]-3,7,7);
				
			}
			g.setColor(Color.BLACK);
			g.drawPolyline(x,y,coordinateList.size());

		}
		
		
		if(canMakeGraph)
		{
			for(int i=0;i<counter;i++)
			{
				g.setColor(checkColor);
				g.fillOval(xList[i]-2,yList[i]-2,4,4);
			}	
			g.setColor(Color.BLACK);
			g.drawPolyline(xList,yList,counter);
		}
		
		if(canMakeGraph1)
		{
			for(int i=0;i<counter1;i++)
			{
				g.setColor(checkColor1);
				g.fillOval(xArray1[i]-2,yArray1[i]-2,4,4);
			}
			g.setColor(Color.BLACK);
			g.drawPolyline(xArray1,yArray1,counter1);
		}
		
		
		if(canMakeGraph2)
		{
			for(int i=0;i<counter2;i++)
			{
				g.setColor(checkColor2);
				g.fillOval(xArray2[i]-2,yArray2[i]-2,4,4);
			}
			g.setColor(Color.BLACK);
			g.drawPolyline(xArray2,yArray2,counter2);
		}
		
		
		if(canMakeGraph3)
		{
			for(int i=0;i<counter3;i++)
			{
				g.setColor(checkColor3);
				g.fillOval(xArray3[i]-2,yArray3[i]-2,4,4);
			}
			g.setColor(Color.BLACK);
			g.drawPolyline(xArray3,yArray3,counter3);
		}
			
		
		repaint();
		revalidate();
	}
	
	
}
