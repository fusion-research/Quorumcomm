package home;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.JLabel;

import org.apache.poi.hssf.util.HSSFColor.TURQUOISE;

import threeD.ThreeDCoordinate;
public class VToolBox
{
	Base base;
	
	int wall=0;
	VToolBox(Base b)
	{
		this.base=b;
	}
	public float getDist(int x1,int y1,int x2,int y2)
	{
		int x=x1-x2;
		int y=y1-y2;
		float z=(float)Math.sqrt(x*x+y*y);
		return z;	
	}
	
	public float getDist(float x1,float y1,int x2,int y2)
	{
		float x=x1-x2;
		float y=y1-y2;
		float z=(float)Math.sqrt(x*x+y*y);
		return z;	
	}
	
	public float getDist(VLabel node1,VLabel node2)
	{       
		float z=0;
		if(node1==null)
		{
			z=0;
		}
		
		else
		{
			int x=node1.getX()-node2.getX();
			int y=node1.getY()-node2.getY();
			z=(float)Math.sqrt(x*x+y*y);
		}
			return z;
	}
	public float getDist(Actuator actuator,VLabel node)
	{       
		float z=0;
		if(actuator==null)
		{
			z=0;
		}
		
		else
		{
			int x=actuator.getX()-node.getX();
			int y=actuator.getY()-node.getY();
			z=(float)Math.sqrt(x*x+y*y);
		}
			return z;
	}
	
	public float getDist(Location loc1,Location loc2)
	{       
		float z=0;
		if(loc1==null)
		{
			z=0;
		}
		
		else
		{
			int x=loc1.getXLoc()-loc2.getXLoc();
			int y=loc1.getYLoc()-loc2.getYLoc();
			z=(float)Math.sqrt(x*x+y*y);
		}
			return z;
	}
	
	
	public float getDist(VLabel node,Location loc2)
	{       
		float z=0;
		Location loc1=new Location(node.getX(),node.getY(),base);
		if(loc1==null)
		{
			z=0;
		}
		
		else
		{
			int x=loc1.getXLoc()-loc2.getXLoc();
			int y=loc1.getYLoc()-loc2.getYLoc();
			z=(float)Math.sqrt(x*x+y*y);
		}
			return z;
	}
	
	public double getDist(VCoordinate start,VCoordinate end)
	{
		double z=0.0;
		int x=end.x-start.x;
		int y=end.y-start.y;
		if(start==null || end==null)
		{
			z=0;
		}
		else
		{
			z=(float)Math.sqrt(x*x+y*y);
		}
		return z;
	}
	
	
	//*************FUNCTION TO GET ANGLE***************************
	
	public double getAngle(int xc, int yc, int x1, int y1)
	{
		int x=x1-xc;
		int y=y1-yc;
		if(x==0)
		{
			x=1;
		}
		double angle =Math.atan(y/x);
		return angle;
	}
	
	//*************FUNCTION TO DRAW CIRCLE***************************
	
	public void circle(ArrayList<Integer>xEventPixelList,ArrayList<Integer>yEventPixelList,int xc,int yc,int radius)
	{
		if(radius<0)
		{
			return;
		}
		int x=0;
		int y=0;
		
		  y=radius;
		  x=0;
	  int  p=3-2*radius;
     // xEventPixelList.removeAll(xEventPixelList);
      //yEventPixelList.removeAll(yEventPixelList);
   while(x<=y+1)
   {
      x++;
      if(p<=-1) p+=4*x+6;
      else
      {
            y--;
            p+=4*(x-y)+10;
      }
//   try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}

      xEventPixelList.add(xc+x);	yEventPixelList.add(yc+y);
      xEventPixelList.add(xc-x);	yEventPixelList.add(yc+y);
      xEventPixelList.add(xc+x);	yEventPixelList.add(yc-y);
      xEventPixelList.add(xc-x);	yEventPixelList.add(yc-y);
      xEventPixelList.add(xc+y);	yEventPixelList.add(yc+x);
      xEventPixelList.add(xc-y);	yEventPixelList.add(yc+x);
      xEventPixelList.add(xc+y);	yEventPixelList.add(yc-x);
      xEventPixelList.add(xc-y);	yEventPixelList.add(yc-x);
      
      base.centerPanel.coordinateMat[yc+y][xc+x].temperature=70;
      base.centerPanel.coordinateMat[yc+y][xc-x].temperature=70;
      base.centerPanel.coordinateMat[yc-y][xc+x].temperature=70;
      base.centerPanel.coordinateMat[yc-y][xc-x].temperature=70;
      base.centerPanel.coordinateMat[yc+x][xc+y].temperature=70;
      base.centerPanel.coordinateMat[yc+x][xc-y].temperature=70;
      base.centerPanel.coordinateMat[yc-x][xc+y].temperature=70;
      base.centerPanel.coordinateMat[yc-x][xc-y].temperature=70;
      
      
      base.centerPanel.coordinateMat[yc+y+1][xc+x].temperature=70;
      base.centerPanel.coordinateMat[yc+y+1][xc-x].temperature=70;
      base.centerPanel.coordinateMat[yc-y+1][xc+x].temperature=70;
      base.centerPanel.coordinateMat[yc-y+1][xc-x].temperature=70;
      base.centerPanel.coordinateMat[yc+x+1][xc+y].temperature=70;
      base.centerPanel.coordinateMat[yc+x+1][xc-y].temperature=70;
      base.centerPanel.coordinateMat[yc-x+1][xc+y].temperature=70;
      base.centerPanel.coordinateMat[yc-x+1][xc-y].temperature=70;
       
   }
}
	
//***************FUNCTION TO GENERATE HEXAGON POINTS FOR NODES************
	/*void generateHexagonsForSN()
	{
		int width=Integer.parseInt(base.dxTf.getText());
		int height=Integer.parseInt(base.dyTf.getText());
		//*************INITIALIZING THE VALUE OF DEPLOYMENT AREA DIVISION RANGE***************
		base.deploymentAreaDivisionRange=base.commRangePerNode;
		System.out.println("DEPLOYMENT AREA DIVISION RANGE  :"+ base.deploymentAreaDivisionRange);
		
		//System.out.println("SENSING RANGE"+sensingRange);
		//double interFloorSpace=((sensingRange/2)*(Math.sqrt(3)));
		double interFloorSpace=(base.commRangePerNode-1)/2;
		System.out.println("INTER FLOOR SPACE  :"+interFloorSpace);
		//double communicationRange=(sensingRange*Math.sqrt(3));
		double communicationRange=base.commRangePerNode;
		System.out.println("COMMUNICATION RANGE :"+communicationRange);
		base.desiredLocList.removeAll(base.desiredLocList);
		base.availDesiredLocListForNodes.removeAll(base.availDesiredLocListForNodes);

		int counter=1;
		//for(float k=0;k<height;k=k+(68.0F/2))
		for(double k=0;k<height;k=k+interFloorSpace)
		{
			//g.setColor(new Color(200,200,200));
			//g.drawLine(100,(int)(100+k),100+width,(int)(100+k));
			for(int l=0;l<width;l=l+(int)(3*base.sensingRangePerNode))
			{
				//g.setColor(new Color(50,50,50));
				if(counter%2==0)
				{
					//g.drawOval(100+l,(int)(100+k),3,3);
					if(l+100<=width+100)
					{
						base.desiredLocList.add(new Location(100+l,(int)(100+Math.floor(k)),base));
						base.availDesiredLocListForNodes.add(new Location(100+l,(int)(100+Math.floor(k)),base));
					}
				}
				else
				{
					//g.drawOval(150+l,(int)(100+k),3,3);
					if(l+100+(base.sensingRangePerNode*3)/2<=width+100)
					{
						base.desiredLocList.add(new Location(100+(int)(base.sensingRangePerNode*3)/2+l,(int)(100+Math.floor(k)),base));
						base.availDesiredLocListForNodes.add(new Location(100+(int)(base.sensingRangePerNode*3)/2+l,(int)(100+Math.floor(k)),base));
					}
				}
			}
			counter=counter+1;
		}
		
		try
		{
			this.vSort(base.masterBSList.get(0).getLoc(),base.availDesiredLocListForNodes);
		}
		catch(Exception e)
		{
			base.alert.display("base station not deployed");
			base.alert.setBounds(300,200,400,130);
		}
		
		
		for(int i=0;i<base.availDesiredLocListForNodes.size();i++)
		{
			System.out.println(this.getDist(base.masterBSList.get(0).getLoc(),base.availDesiredLocListForNodes.get(i)));
		}
		
		
		base.plotHexagonForNodes=true;
		
		//--------ADDING THE DESIRED LOCATIONS TO THE RANGE LIST------------------------------
		int startRange=0;
		int radius=(int)base.commRangePerNode/2;
		int endRange=radius;
		int loopValue=(int)Math.ceil(710/radius);
		for(int i=0;i<=loopValue+1;i++)
		{
			Range range=new Range();
			range.from=startRange;
			range.to=endRange;
			range.desiredLocInRangeList=getDesiredLocInRange(startRange,endRange);
			base.rangeListForNodes.add(range);
			startRange=endRange;
			endRange=endRange+radius;
		}
		//-------------------------------------------------------------------------------------
		
	}*/
	ArrayList <Location> queue=new ArrayList<Location>();
	void generateHexagonsForSN(Location loc, final float rs)
	{
		
		Location startingLoc=loc;
		startingLoc.group=1;
		base.desiredLocList.add(startingLoc);
		queue.add(startingLoc);
		
		Runnable r=new Runnable()
		{
			public void run()
			{
				float rd=(float)(rs*Math.sqrt(3));
				int qCounter=0;
				while(queue.size()!=qCounter)
				{
					int counter=0;
				//	System.out.println("ueue");
					Location tempLoc=queue.get(qCounter);
					//queue.remove(0);
					qCounter++;
					int x=tempLoc.getXLoc();
					int y=tempLoc.getYLoc();
					
					Location tempLoc2=new Location(x+(int)(1.5*rs),y+(int)(rd/2),2,base);
					Location tempLoc3=new Location(x+(int)(1.5*rs),y-(int)(rd/2),3,base);
					Location tempLoc4=new Location(x,y-(int)rd,4,base);
					Location tempLoc5=new Location(x-(int)(1.5*rs),y-(int)(rd/2),5,base);
					Location tempLoc6=new Location(x-(int)(1.5*rs),y+(int)(rd/2),6,base);
					Location tempLoc7=new Location(x,y+(int)rd,7,base);
					
					if(liesWithinCR(tempLoc2,0))
					{
						base.desiredLocList.add(tempLoc2);
						counter++;
					}
					
					if(liesWithinCR(tempLoc3,0))
					{
						base.desiredLocList.add(tempLoc3);
						counter++;
					}
					
					if(liesWithinCR(tempLoc4,0))
					{
						base.desiredLocList.add(tempLoc4);
						counter++;
					}
					
					if(liesWithinCR(tempLoc5,0))
					{
						base.desiredLocList.add(tempLoc5);
						counter++;
					}
					
					if(liesWithinCR(tempLoc6,0))
					{
						base.desiredLocList.add(tempLoc6);
						counter++;
					}
					
					if(liesWithinCR(tempLoc7,0))
					{
						base.desiredLocList.add(tempLoc7);
						counter++;
					}
					int ds=base.desiredLocList.size();
					System.out.println("counter=" +counter+" queue size"+queue.size()+"dll ="+ds+"coordinates :"+base.desiredLocList.get(ds-1).x +","+base.desiredLocList.get(ds-1).y);
					
					if(counter==0)
					{
						//queue.remove(0);
						continue;
					}
					
					Location conLoc=new Location(x+(int)(4.5*rs), y+(int)(rd/2),1,base);
					if(!contains(queue,conLoc) && !contains(base.desiredLocList,conLoc))
					{
							if(liesWithinCR(conLoc,0))
							{
								base.desiredLocList.add(conLoc);
							}
							queue.add(conLoc);
					}
					
					conLoc=new Location(x+(int)(3*rs), y-(int)(2*rd),1,base);
					if(!contains(queue,conLoc) && !contains(base.desiredLocList,conLoc))
					{
							if(liesWithinCR(conLoc,0))
							{
								base.desiredLocList.add(conLoc);
							}
						    queue.add(conLoc);
					}
					
					conLoc=new Location(x-(int)(1.5*rs), y-(int)(2.5*rd),1,base);
					if(!contains(queue,conLoc) && !contains(base.desiredLocList,conLoc))
					{
						if(liesWithinCR(conLoc,0))
						{
							base.desiredLocList.add(conLoc);
						}
						queue.add(conLoc);
					}
					
					conLoc=new Location(x-(int)(4.5*rs), y-(int)(rd/2),1,base);
					if(!contains(queue,conLoc) && !contains(base.desiredLocList,conLoc))
					{
						if(liesWithinCR(conLoc,0))
						{
							base.desiredLocList.add(conLoc);
						}
						queue.add(conLoc);
					}
					
					conLoc=new Location(x-(int)(3*rs), y+(int)(2*rd),1,base);
					if(!contains(queue,conLoc) && !contains(base.desiredLocList,conLoc))
					{
						if(liesWithinCR(conLoc,0))
						{
							base.desiredLocList.add(conLoc);
						}
						queue.add(conLoc);
					}
					
					conLoc=new Location(x+(int)(1.5*rs), y+(int)(2.5*rd),1,base);
					if(!contains(queue,conLoc) && !contains(base.desiredLocList,conLoc))
					{	
						if(liesWithinCR(conLoc,0))
						{
							base.desiredLocList.add(conLoc);
						}
						queue.add(conLoc);
					}
				//System.out.println("kya scene");	
/*					try{
						Thread.sleep(100);
					}catch(Exception e){}*/
				}
				
				try{
					Thread.sleep(2000);
				}catch(Exception e){}
				
				for(int i=0;i<base.desiredLocList.size();i++)
				{
					base.desiredLocList.get(i).groupLb=new JLabel(Integer.toString(base.desiredLocList.get(i).group));
					base.desiredLocList.get(i).groupLb.setBounds(base.desiredLocList.get(i).getXLoc(),base.desiredLocList.get(i).getYLoc(),15,15);
					base.centerPanel.add(base.desiredLocList.get(i).groupLb);
					base.centerPanel.revalidate();
				}
				
				populateNeighboringLocs();

			}
		};
		new Thread(r).start();
	}
	
	//---------FUNCTION TO POPULATE NEIGHBORING LOCS-----------------------------
	
	void populateNeighboringLocs()
	{
		for(int i=0;i<base.desiredLocList.size();i++)
		{
			for(int j=0;j<base.desiredLocList.size();j++)
			{
				if(i!=j && getDist(base.desiredLocList.get(i),base.desiredLocList.get(j))<=(base.commRangePerNode))
				{
					base.desiredLocList.get(i).neighboringLocList.add(base.desiredLocList.get(j));
					base.desiredLocList.get(i).neighboringUnoccupiedLocList.add(base.desiredLocList.get(j));
				}
			}			
		}
	}
	
	//---------------------------------------------------------------------------
	
	
	//#########FUNCTION TO CHECK IF LOCATION LIES WITHIN A CANDIDATE REGION#######
	
	boolean liesWithinCR(Location loc, int tolerance)
	{
		int x=loc.getXLoc();
		int y=loc.getYLoc();
		int xMax=Integer.parseInt(base.dxTf.getText());
		int yMax=Integer.parseInt(base.dyTf.getText());
		if(base.dAShapeCb.getSelectedItem()=="square")
		{
			if((x>100-tolerance & x<(100+xMax+tolerance)) && (y>100-tolerance & y<(100+yMax+tolerance)))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(base.dAShapeCb.getSelectedItem()=="circle")
		{
			Location tempLoc=new Location(base.dxCenter, base.dyCenter, base);
			float dist=getDist(tempLoc, loc);
			if(dist<=Integer.parseInt(base.dRadiusTf.getText()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	//****************************************************************************
	
	//-----FUNCTION TO CHECK IF PARTICULAR LOCATION LIES WITHIN A LIST------------
	
	boolean contains(ArrayList<Location>locList,Location locc)
	{
		for(int l=0;l<locList.size();l++)
		{
			//System.out.println(i);
			if(locc.equals(locList.get(l),10))
			{
				return true;
			}
		}
		return false;
	}
	
	//----------------------------------------------------------------------------
	
//*********************************************************************************
	
	//***************FUNCTION TO GENERATE HEXAGON POINTS FOR ACTUATORS************
	void generateHexagonsForActuators()
	{
		int width=Integer.parseInt(base.dxTf.getText());
		int height=Integer.parseInt(base.dyTf.getText());
		//*************INITIALIZING THE VALUE OF DEPLOYMENT AREA DIVISION RANGE***************
		//base.deploymentAreaDivisionRange=base.commRangePerActuator;
		//System.out.println("DEPLOYMENT AREA DIVISION RANGE  :"+ base.deploymentAreaDivisionRange);
		
		//System.out.println("SENSING RANGE"+sensingRange);
		//double interFloorSpace=((sensingRange/2)*(Math.sqrt(3)));
		double interFloorSpace=(base.commRangePerActuator-1)/2;
		System.out.println("INTER FLOOR SPACE  :"+interFloorSpace);
		//double communicationRange=(sensingRange*Math.sqrt(3));
		double communicationRange=base.commRangePerActuator;
		System.out.println("COMMUNICATION RANGE :"+communicationRange);
		base.actuatorDesiredLocList.removeAll(base.actuatorDesiredLocList);
		base.availDesiredLocListForActuators.removeAll(base.availDesiredLocListForActuators);

		int counter=1;
		//for(float k=0;k<height;k=k+(68.0F/2))
		for(double k=0;k<height;k=k+interFloorSpace)
		{
			//g.setColor(new Color(200,200,200));
			//g.drawLine(100,(int)(100+k),100+width,(int)(100+k));
			
			int expectedSensingRange=(int)Math.floor(base.commRangePerActuator/Math.sqrt(3.0));
			
			for(int l=0;l<width;l=l+(3*expectedSensingRange))
			{
				//g.setColor(new Color(50,50,50));
				if(counter%2==0)
				{
					//g.drawOval(100+l,(int)(100+k),3,3);
					if(l+100<=width+100)
					{
						base.actuatorDesiredLocList.add(new Location(100+l,(int)(100+Math.floor(k)),base));
						base.availDesiredLocListForActuators.add(new Location(100+l,(int)(100+Math.floor(k)),base));
					}
				}
				else
				{
					//g.drawOval(150+l,(int)(100+k),3,3);
					if(l+100+(expectedSensingRange*3)/2<=width+100)
					{
						base.actuatorDesiredLocList.add(new Location(100+(expectedSensingRange*3)/2+l,(int)(100+Math.floor(k)),base));
						base.availDesiredLocListForActuators.add(new Location(100+(expectedSensingRange*3)/2+l,(int)(100+Math.floor(k)),base));
					}
				}
			}
			counter=counter+1;
		}
		
		try
		{
			this.vSort(base.masterBSList.get(0).getLoc(),base.availDesiredLocListForActuators);
		}
		catch(Exception e)
		{
			base.alert.display("base station not deployed");
			base.alert.setBounds(300,200,400,130);
		}
		
		
		for(int i=0;i<base.availDesiredLocListForActuators.size();i++)
		{
			System.out.println(this.getDist(base.masterBSList.get(0).getLoc(),base.availDesiredLocListForActuators.get(i)));
		}
		
		
		base.plotHexagonForActuators=true;
		
		//--------ADDING THE DESIRED LOCATIONS TO THE RANGE LIST------------------------------
		int startRange=0;
		int radius=(int)base.commRangePerActuator/2;
		int endRange=radius;
		int loopValue=(int)Math.ceil(710/radius);
		for(int i=0;i<=loopValue+1;i++)
		{
			Range range=new Range();
			range.from=startRange;
			range.to=endRange;
			range.desiredLocInRangeList=getDesiredLocInRange(startRange,endRange);
			base.rangeListForActuators.add(range);
			startRange=endRange;
			endRange=endRange+radius;
		}
		//-------------------------------------------------------------------------------------
		
	}
//*********************************************************************************

	
	
	
//GETTING REQUIRED NUMBER OF POINTS IN SORTED ORDER********************************
	
	ArrayList<Location>getRequiredPoints(ArrayList<Location>availLocList,int count)
	{
		ArrayList<Location>tempLocList=new ArrayList<Location>();
		for(int i=0;i<count;i++)
		{
			if(availLocList.size()>0)
			{
				tempLocList.add(availLocList.get(0));
				availLocList.remove(0);
			}
		}
		return tempLocList;
	}

	
	
//*********************************************************************************
//GETTING DESIRED POINTS IN THE GIVEN RANGE IN SORTED ORDER
	
	ArrayList <Location> getDesiredPoints(Location loc,ArrayList<Location>desiredLocList,int fromRange,int toRange)  
	{
		ArrayList<Location>tempLocList=new ArrayList<Location>();
		for(int i=0;i<desiredLocList.size();i++)
		{
			//System.out.println(getDist(loc,desiredLocList.get(i)));
			//if(getDist(loc,desiredLocList.get(i))<=range)
			if(getDist(loc,desiredLocList.get(i))>fromRange && getDist(loc,desiredLocList.get(i))<=toRange)
			{
				tempLocList.add(desiredLocList.get(i));
				//System.exit(0);
			}
		}
		//Collections.sort(tempLocList,new Location.orderByDistance());  // SORTING THE DESIRED POINTS IN THE RANGE GIVEN
		tempLocList=vSort(loc,tempLocList);
		//System.out.println(tempLocList);
		for(int j=0;j<tempLocList.size();j++)
		{
			//System.out.println(getDist(loc,tempLocList.get(j)));
		}
		return tempLocList;
	}
	
//---------------------------------------------------------------
	
	
	
	
	
//GETTING DESIRED POINTS IN THE GIVEN RANGE IN SORTED ORDER
	
	ArrayList <Loc_Dist> getDesiredPointsWithDist(Location loc,ArrayList<Location>desiredLocList,int fromRange,int toRange)  
	{
		ArrayList<Location>tempLocList=new ArrayList<Location>();
		ArrayList<Loc_Dist>locDistList=new ArrayList<Loc_Dist>();
		for(int i=0;i<desiredLocList.size();i++)
		{
				tempLocList.add(desiredLocList.get(i));
		}
		//Collections.sort(tempLocList,new Location.orderByDistance());  // SORTING THE DESIRED POINTS IN THE RANGE GIVEN
		//System.out.println("TEMP LOCATIONS LIST SIZE  "+tempLocList.size());
		tempLocList=vSort(loc,tempLocList);
		
		for(int j=0;j<tempLocList.size();j++)
		{
			Loc_Dist locDist=new Loc_Dist(tempLocList.get(j),getDist(loc,tempLocList.get(j)),base);
			locDistList.add(locDist);
		}
		//return tempLocList;
		return locDistList;
	}
	
//---------------------------------------------------------------
	
	//*****************************************************************************
	//FUNCTION TO HANDLE OBSTACLE -------------------------------------------------
	void obstacleHandler(VLabel node,int x2,int y2)
	{
		try{Thread.sleep(base.moveDelay);}catch(Exception e){}//????????????MODIFIED------
		int currentX=node.getX();
		int currentY=node.getY();
		int finalX=node.finalLocation.getXLoc();
		int finalY=node.finalLocation.getYLoc();
		int xMov=0;
		int yMov=0;
		//if(! (node.obstacleShapeList.contains(base.centerPanel.coordinateMat[currentX][currentY].location)))
		//{
			if(finalX>currentX && finalY<currentY)
			{
				if(base.centerPanel.coordinateMat[x2][currentY].obstacle)
				{
					yMov=-1;
					xMov=0;
					wall=1;
				}
				else if(base.centerPanel.coordinateMat[currentX][y2].obstacle)
				{
					xMov=-1;
					yMov=0;
					wall=4;
				}
				else
				{
					yMov=-1;
					xMov=0;
				}
			}
			else if(finalX>currentX && finalY>currentY)
			{
				
				if(base.centerPanel.coordinateMat[x2][currentY].obstacle)
				{
					yMov=-1;
					xMov=0;
					wall=1;
				}
				else if(base.centerPanel.coordinateMat[currentX][y2].obstacle)
				{
					xMov=1;
					yMov=0;
					wall=2;
				}
				else
				{
					xMov=1;
					yMov=0;
				}
			}
			else if(finalX>currentX && finalY==currentY)
			{
				if(base.centerPanel.coordinateMat[x2][currentY].obstacle)
				{
					yMov=-1;
					xMov=0;
					wall=1;
				}
			}
			else if(finalX<currentX && finalY>currentY)
			{
				if(base.centerPanel.coordinateMat[x2][currentY].obstacle)
				{
					yMov=1;
					xMov=0;
					wall=3;
				}
				else if(base.centerPanel.coordinateMat[currentX][y2].obstacle)
				{
					xMov=1;
					yMov=0;
					wall=2;
				}
				else
				{
					xMov=0;
					yMov=1;
				}
			}
			else if(finalX<currentX && finalY<currentY)
			{
				if(base.centerPanel.coordinateMat[x2][currentY].obstacle)
				{
					yMov=1;
					xMov=0;
					wall=3;
				}
				else if(base.centerPanel.coordinateMat[currentX][y2].obstacle)
				{
					xMov=-1;
					yMov=0;
					wall=4;
				}
				else
				{
					xMov=-1;
					yMov=0;
				}
				
			}
			else if(finalX<currentX && finalY==currentY)
			{
				if(base.centerPanel.coordinateMat[x2][currentY].obstacle)
				{
					xMov=0;
					yMov=1;
					wall=3;
				}
			}
			else if(finalX==currentX && finalY>currentY)
			{
				if(base.centerPanel.coordinateMat[currentX][y2].obstacle)
				{
					xMov=1;
					yMov=0;
					wall=2;
				}
				
			}
			else if(finalX==currentX && finalY<currentY)
			{
				if(base.centerPanel.coordinateMat[currentX][y2].obstacle)
				{
					xMov=-1;
					yMov=0;
					wall=4;
				}
			}
			try{Thread.sleep(5);}catch(Exception e){}
			
			if(base.centerPanel.coordinateMat[currentX+xMov][currentY+yMov].obstacle==false)
			{
				if(! (node.obstacleShapeList.contains(base.centerPanel.coordinateMat[currentX+xMov][currentY+yMov].location)))
				{
					Location tempLoc=base.centerPanel.coordinateMat[currentX+xMov][currentY+yMov].location;
					tempLoc.color=Color.ORANGE;
					node.obstacleShapeList.add(tempLoc);
					node.setBounds(currentX+xMov,currentY+yMov,6,6);
				}
			}
			else
			{
				switch(wall)
				{
				case 1:
				{
					if(xMov==1)
					{
						xMov=0;
						yMov=-1;
					}
				}
				break;
				case 2:
				{
					if(yMov==1)
					{
						xMov=1;
						yMov=0;
					}
				}
				break;
				case 3:
				{
					if(xMov==-1)
					{
						xMov=0;
						yMov=1;
					}
				}
				break;
				
				case 4:
				{
					if(yMov==-1)
					{
						xMov=-1;
						yMov=0;
					}
				}
				break;
				
				default :
				{
					xMov=1;
					yMov=0;
				}
				}
				
			
				//-----------------ADDING AND MOVING TO NEXT LOCATION IF LOCATION IS NOT TRAVERSED ALREADY-----------
				
				if(! (node.obstacleShapeList.contains(base.centerPanel.coordinateMat[currentX+xMov][currentY+yMov].location)))
				{
					Location tempLoc=base.centerPanel.coordinateMat[currentX+xMov][currentY+yMov].location;
					tempLoc.color=Color.GREEN;
					node.obstacleShapeList.add(tempLoc);
					node.setBounds(currentX+xMov,currentY+yMov,6,6);
				}
			}
		
			
			//if(base.centerPanel.coordinateMat[currentX+xMov][currentY+yMov].obstacle)
				//	{
					//	System.out.println("xMov :"+xMov);
					//	System.out.println("yMov :"+yMov);
					//	System.exit(0);
			//}
		//	else
			//{
			//node.setBounds(currentX+yMov,currentY+xMov,6,6);
		//	}
		//	Location currentLoc=node.getLoc();
		//		Location nextLoc=new Location(currentX+xMov,currentY+yMov,base);
		//	move(node,currentLoc,nextLoc,50);
			Location currentLoc=node.getLoc();
			Location finalLoc=node.finalLocation;
			move(node,currentLoc,finalLoc,50);
		//}
		
		
	}
	
	
	
	//-----------------------------------------------------------------------------
	
	
	//*****************************************************************************
	//FUNCTION TO MOVE THE NODES TOWARDS THE BASE STATION
	
	public void move(final VLabel node,final Location moveFrom,final Location moveTo,final int delay)
	{
		base.moveDelay=delay; // CAN BE USED AT OTHER PLACES
		Runnable r =new Runnable()
		{
			boolean condition=true;
			float x1=(float)moveTo.getXLoc();
			float y1=(float)moveTo.getYLoc();
			
			float x2=(float)node.getX();
			float y2=(float)node.getY();
			
			int stepCount;
			
			float xSlope;//=-1;
			float ySlope;
			
			public void run()
			{
			
			//=-((y0-y1)/(x0-x1));
			
			float dx=(float)Math.sqrt((x2-x1)*(x2-x1));
			float dy=(float)Math.sqrt((y2-y1)*(y2-y1));
			if(dx>dy)
			{
				stepCount=(int)dx;
			}
			else
			{
				stepCount=(int)dy;
			}
			
			if(x2>=x1 && y2>=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=-1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=-1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=-(dx/dx);
					ySlope=-(dy/dx);
				}
				
				else
				{
					ySlope=-(dy/dy);
					xSlope=-(dx/dy);
				}	
				
			}
			
			if(x2>=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=-1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=-(dx/dx);
					ySlope=(dy/dx);
				}
				
				else
				{
					ySlope=(dy/dy);
					xSlope=-(dx/dy);
				}		
			}
			
			if(x2<=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=(dx/dx);
					ySlope=(dy/dx);
				}
				
				else
				{
					ySlope=(dy/dy);
					xSlope=(dx/dy);
				}		
			}
			
			if(x2<x1 && y2>y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=-1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=(dx/dx);
					ySlope=-(dy/dx);
				}
				
				else
				{
					ySlope=-(dy/dy);
					xSlope=(dx/dy);
				}	
			}
			
							
				for(int i=0;i<stepCount && node.movable;i++)
				{
					x2=x2+xSlope;
					y2=y2+ySlope;
					
					if(base.centerPanel.coordinateMat[(int)Math.ceil(x2)][(int)Math.ceil(y2)].obstacle)
					{
						condition=false;
						
						obstacleHandler(node,(int)x2,(int)y2);
						break;
					}
					else if(base.centerPanel.coordinateMat[(int)(x2)][(int)(y2)].obstacle)
					{
						condition=false;
						obstacleHandler(node,(int)x2,(int)y2);
						break;
					}
					else if(base.centerPanel.coordinateMat[(int)Math.ceil(x2)][(int)(y2)].obstacle)
					{
						condition=false;
						obstacleHandler(node,(int)x2,(int)y2);
						break;
					}
					else if(base.centerPanel.coordinateMat[(int)(x2)][(int)Math.ceil(y2)].obstacle)
					{
						condition=false;
						obstacleHandler(node,(int)x2,(int)y2);
						break;
					}
					
					node.setBounds((int)x2, (int)y2,base.nodeSize,base.nodeSize);
					node.xPos=(int)x2;
					node.yPos=(int)y2;
					
					if(node.isInRange!=base.RANGE1)
					{
						if(Math.ceil(getDist(node,moveTo))<base.commRangePerNode)
						{
							//node.movable=false;
							condition=false;
							node.identified=true;
							node.isInRange=base.RANGE1;
							
							//-------UPDATING THE MOVEMENT PARAMETERS OF THE NODE----------------------------
							node.nodeMovement=getDist(moveFrom,node.getLoc());
							System.out.println("NODE :"+node.uniqueId +" MOVEMENT :"+node.nodeMovement);
							base.totalMovement=base.totalMovement+(node.nodeMovement/base.scale);
							base.avgMovement=base.totalMovement/base.masterNodeList.size();
							//-------------------------------------------------------------------------------
							
							//node.defaultColor=Color.BLACK;
							//node.setBackground(Color.BLACK);
							
							//IF BASE STATION COMES IN RANGE OF NODE THEN NODE SEND PACKET TO BASE STATION TO ADD IT AS NEIGHBOR
							if(node.commRange>=getDist(node,node.baseStationId))
							{		
									Packet packet=new Packet(base);
									//Packet packet=(Packet)newPacket.clone();
									packet.source=node;
									packet.destination=node.baseStationId;
									packet.queryType=base.ADD_ME_AS_NEIGHBOR;
								
							
									packet.ttl=1;
									packet.hopCount=0;
									node.setBackground(base.RED);
									if(node.powerEnoughForTransmission())
									{
										node.baseStationId.buffer.add(packet);
										node.isConnected=true;
									}										
							}
							
							break;
						}
					}
					
					refreshConnections();
					try
					{
						Thread.sleep(delay);
					}
					catch(Exception e){System.out.println(e);}
				}
				//if(node.movable!=false && condition==true)
				if(condition==true)
				{
				node.setBounds((int)x1, (int)y1,base.nodeSize,base.nodeSize);
					if(node.isInRange==base.RANGE1)
					{
						node.isPlaced=true;
						markCoverage(node);
						node.defaultColor=Color.GREEN;
						node.setBackground(Color.GREEN);
						
						//-------UPDATING THE MOVEMENT PARAMETERS OF THE NODE----------------------------
						node.nodeMovement=getDist(moveFrom,node.getLoc());
						System.out.println("NODE :"+node.uniqueId +" MOVEMENT :"+node.nodeMovement);
						base.totalMovement=base.totalMovement+node.nodeMovement;
						base.avgMovement=base.totalMovement/base.masterNodeList.size();
						//-------------------------------------------------------------------------------
						
						int indexOfNode=base.masterBSList.get(0).activeNodeList.indexOf(node);
						//base.baseStationArray[0].activeNodeList.remove(indexOfNode);??????????????????????????????????????????/
						Location locOfNode=node.nearestDesiredPoint;
						int indexOfLoc=base.availDesiredLocListForNodes.indexOf(locOfNode);
						//System.out.println(base.desiredLocList.size());
						//base.availDesiredLocList.remove(indexOfLoc);???????????????????????????????????????
						//node.isPlaced=true;
						
						//System.out.println(base.desiredLocList.size());
						
					}
				}
			}
		};
		
		new Thread(r).start();
	}
//-------------------------------------------------------------------------
	
	
//#################################################################
	int k=0;
	public void moveOnPath(final VFigure figure,final Location moveFrom,final Location moveTo,final int delay)
	{
		base.droppingFlag=moveFrom.flag;
		base.conventionalDeployerFlag=moveFrom.flag;
		base.deployerTotalMovement=base.deployerTotalMovement+(getDist(moveFrom,moveTo)/base.scale);
		if(base.droppingFlag)
		{
			base.deployerDroppingMovement=base.deployerDroppingMovement+(getDist(moveFrom,moveTo)/base.scale);
		}
		base.moveDelay=delay; // CAN BE USED AT OTHER PLACES
		//Runnable r =new Runnable()
		//{
			boolean condition=true;
			float x1=(float)moveTo.getXLoc();
			float y1=(float)moveTo.getYLoc();
			
			float x2=(float)moveFrom.getXLoc();
			float y2=(float)moveFrom.getYLoc();
			
			int stepCount;
			
			float xSlope=0;//=-1;
			float ySlope=0;
			
			Location startLoc=moveFrom;
			Location endLoc=moveTo;
					
			
			//public void run()
			//{
			
			//=-((y0-y1)/(x0-x1));
			
			float dx=(float)Math.sqrt((x2-x1)*(x2-x1));
			float dy=(float)Math.sqrt((y2-y1)*(y2-y1));
			if(dx>dy)
			{
				stepCount=(int)dx;
			}
			else
			{
				stepCount=(int)dy;
			}
			
			if(x2>=x1 && y2>=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=-1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=-1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=-(dx/dx);
					ySlope=-(dy/dx);
				}
				
				else
				{
					ySlope=-(dy/dy);
					xSlope=-(dx/dy);
				}	
				
			}
			
			if(x2>=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=-1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=-(dx/dx);
					ySlope=(dy/dx);
				}
				
				else
				{
					ySlope=(dy/dy);
					xSlope=-(dx/dy);
				}		
			}
			
			if(x2<=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=(dx/dx);
					ySlope=(dy/dx);
				}
				
				else
				{
					ySlope=(dy/dy);
					xSlope=(dx/dy);
				}		
			}
			
			if(x2<x1 && y2>y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=-1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=(dx/dx);
					ySlope=-(dy/dx);
				}
				
				else
				{
					ySlope=-(dy/dy);
					xSlope=(dx/dy);
				}	
			}
			
			ArrayList<LineSegment>lineList=figure.lineList;		
				for(int i=0;i<stepCount;i++)
				{
					x2=x2+xSlope;
					y2=y2+ySlope;
					for(int j=0;j<lineList.size();j++)
					{
						LineSegment ls=lineList.get(j);
						ls.x1=ls.x1+xSlope;
						ls.y1=ls.y1+ySlope;
					}
					try
					{
						System.out.println(delay);
						Thread.sleep(delay);						
					}
					catch(Exception e){System.out.println(e);}
				}
				//base.alert.display(""+k);
				k=k+1;	
				try{
				moveOnPath(figure, base.pathList.get(k),base.pathList.get(k+1),delay);
				}catch(Exception e){base.conventionalDeployerFlag=false;base.foregroundFigureList.removeAll(base.foregroundFigureList); deployNodesExtended();}
			//}
		//};
		//new Thread(r).start();
	}
//#################################################################	
	
//*************************************************************************
//FUNCTION TO SORT THE LOCATIONSBASE ON THEIR DISTANCE FROM REFERENCE POINT
	public ArrayList<Location>vSort(Location referenceLoc,ArrayList<Location>desiredLocations)
	{
		//vToolBox=new VToolBox(null);
		Location locArr[]=new Location[100];
		locArr=(desiredLocations.toArray(new Location[desiredLocations.size()]));
		ArrayList <Float> tempList=new ArrayList<Float>();
		for(int i=0;i<desiredLocations.size();i++)
		{
			tempList.add(getDist(referenceLoc,desiredLocations.get(i)));
		}
		Float distArray[]=(tempList.toArray(new Float[tempList.size()]));
		for(int i=0;i<distArray.length;i++)
		{
			//System.out.println(distArray[i]);
		}
		
		for(int i=0;i<distArray.length;i++)
		{
			for(int j=0;j<distArray.length-i-1;j++)
			{
				if(distArray[j]>distArray[j+1])
				{
					float temp=distArray[j];
					Location tempLoc=locArr[j];
					distArray[j]=distArray[j+1];
					locArr[j]=locArr[j+1];
					distArray[j+1]=temp;
					locArr[j+1]=tempLoc;
				}
			}
		}
		desiredLocations.removeAll(desiredLocations);
		for(int i=0;i<locArr.length;i++)
		{
			desiredLocations.add(locArr[i]);
		}
	return desiredLocations;
		
	}
	//-----------------------------------------------------------------------------
	
	public ArrayList<LocNodeDist>vSort(ArrayList<LocNodeDist>locNodeDistList)
	{
		LocNodeDist lndArr[]=new LocNodeDist[100];
		lndArr=(locNodeDistList.toArray(new LocNodeDist[locNodeDistList.size()]));
		int arrLength=locNodeDistList.size();
		
		for(int i=0;i<arrLength;i++)
		{
			for(int j=0;j<arrLength-i-1;j++)
			{
				if(lndArr[j].dist>lndArr[j+1].dist)
				{
					LocNodeDist temp=lndArr[j];
					lndArr[j]=lndArr[j+1];
					lndArr[j+1]=temp;
				}
			}
		}
		locNodeDistList.removeAll(locNodeDistList);
		for(int i=0;i<arrLength;i++)
		{
			locNodeDistList.add(lndArr[i]);
		}
	return locNodeDistList;		
	}
		
	
	//FUNCTION TO SORT THE LOCATIONSBASE ON THEIR DISTANCE FROM REFERENCE POINT
	public void getNearestClusterHead()
	{
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			VLabel nearestCH=null;
			float nearestDist=99999;
			VLabel tempNode=base.masterNodeList.get(i);
			if(tempNode.isClusterHead==false)
			{
				for(int j=0;j<base.clusterHeadList.size();j++)
				{
					float dist=getDist(tempNode,base.clusterHeadList.get(j));
					if(dist<nearestDist)
					{
						nearestDist=dist;
						nearestCH=base.clusterHeadList.get(j);						
					}
				}
				
				tempNode.nodeClusterHead=nearestCH;			
			}
		}
	}
	//-----------------------------------------------------------------------------
	

	
//*******************************************************************
//FUNCTION TO REFRESH THE CONNECTION LINES BETWEEN NODES WITHIN RANGE
	
	public void refreshConnections()
	{
		Runnable r=new Runnable()
		{
			public void run()
			{
				try
				{
					base.masterBSList.get(0).setBackground(base.masterBSList.get(0).defaultColor);
					for(int i=0;i<base.masterNodeList.size();i++)
					{
						VLabel temp=base.masterNodeList.get(i);
						temp.neighborList.removeAll(temp.neighborList);
						temp.connectionList.removeAll(temp.connectionList);
						temp.setBackground(temp.defaultColor);
						for(int j=0;j<base.masterNodeList.size();j++)
						{
							if(i!=j && temp.commRange> getDist(temp,base.masterNodeList.get(j)))
							{
								temp.neighborList.add(base.masterNodeList.get(j));
								temp.connectionList.add(true);										
							}
						}
				}
				}catch(Exception e){}
			}
		};
		
		new Thread(r).start();
	}
	
	//****************************************************************
	//FUNCTION TO GET THE LOCATION OF THE NODE
	Location getLoc(VLabel node,ArrayList<Loc_Node> locNodeList)
	{
		Location loc=null;
		for(int i=0;i<locNodeList.size();i++)
		{
			VLabel tempNode=locNodeList.get(i).getNode();
			Location tempLoc=locNodeList.get(i).getLoc();
			if(node==tempNode)
			{
				loc=tempLoc;
				break;
			}
		}
		return loc;
	}
	
	//*********************************************************************
	//FUNCTION TO GET DESIRED LOCATIONS IN SPECIFIED RANGE-----------------
	ArrayList<Location>getDesiredLocInRange(int startRange,int endRange)
	{
		ArrayList<Location> tempList=new ArrayList<Location>();
		Location baseLoc=base.masterBSList.get(0).getLoc();
		for(int i=0;i<base.desiredLocList.size();i++)
		{
			if(getDist(baseLoc,base.desiredLocList.get(i))>=startRange && getDist(baseLoc,base.desiredLocList.get(i))<endRange)
			{
				tempList.add(base.desiredLocList.get(i));
			}
		}
		return (ArrayList<Location>)tempList.clone();
	}
	
	//*********************************************************************
	//FUNCTION TO GET NODES IN SPECIFIED RANGE-----------------------------
	ArrayList<VLabel> getNodesInRange(int startRange,int endRange)
	{
		ArrayList<VLabel>tempList=new ArrayList<VLabel>();
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			if(getDist(base.masterBSList.get(0),base.masterNodeList.get(i))>startRange && getDist(base.masterBSList.get(0),base.masterNodeList.get(i))<=endRange)
			{
				if(base.masterNodeList.get(i).isPlaced==false)
				{
					tempList.add(base.masterNodeList.get(i));
					base.masterNodeList.get(i).movable=true;
				}
			}
		}
		return (ArrayList<VLabel>)tempList.clone();
	}
	
	
	//*******************************************************************
	//FUNCTION TO GET NODES IN SPECIFIED RANGE+ IDENTIFIED NODES---------
	ArrayList<VLabel> getNodesInRangeAndIdentified(int startRange,int endRange)
	{
		ArrayList<VLabel>tempList=new ArrayList<VLabel>();
		for(int i=0;i<base.masterNodeList.size();i++)
		{
				if(base.masterNodeList.get(i).isPlaced==false)
				{
					if((getDist(base.masterBSList.get(0),base.masterNodeList.get(i))>startRange && getDist(base.masterBSList.get(0),base.masterNodeList.get(i))<=endRange)||base.masterNodeList.get(i).identified==true )		
					{
						tempList.add(base.masterNodeList.get(i));
						base.masterNodeList.get(i).movable=true;
					}
				}
			
		}
		return (ArrayList<VLabel>)tempList.clone();
	}
	
	
	//********************************************************************
	//NEW FUNCTION TO GENERATE LOC-NODE LIST FOR THE GIVEN NODELIST AND LOCATIONS
	
	
	
/*	ArrayList<Loc_Node> getLocNodeTable(ArrayList<VLabel> nodeList,ArrayList <Location> desiredLocList,int fromRange,int toRange)
	{
		ArrayList <Loc_Node>locNodeList=new ArrayList<Loc_Node>();
		//********************************************************************
		//FOR EVERY NODE CREATE SORTED LIST OF LOC-DIST-LIST
		for(int i=0;i<nodeList.size();i++)
		{
			Location loc=new Location(nodeList.get(i).getX(),nodeList.get(i).getY(),base);
			nodeList.get(i).locDistList=getDesiredPointsWithDist(loc, desiredLocList,fromRange,toRange);
			//System.out.println("LOC DIST LIST SIZE OF NODES : "+nodeList.get(i).locDistList.size());
		}
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		for(int i=0;i<nodeList.size();i++)
		{
			System.out.println("------------------------------------------------");
			System.out.println("NODE :"+nodeList.get(i).uniqueId);
			for(int j=0;j<nodeList.get(i).locDistList.size();j++)
			{
				System.out.println("("+nodeList.get(i).locDistList.get(j).getLoc().getXLoc()+","+nodeList.get(i).locDistList.get(j).getLoc().getYLoc()+")   ,  "+nodeList.get(i).locDistList.get(j).distance);
			}
		}
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		//System.exit(0);
		//*********************************************************************
		for(int i=0;i<nodeList.size();i++)
		{
			//base.alert.display("1");
			if(locNodeList.size()==0)
			{
				try
				{
					nodeList.get(i).locDistList.get(0);
					Loc_Node locNode=new Loc_Node(nodeList.get(i).locDistList.get(0).getLoc(),nodeList.get(i),base);
					locNodeList.add(locNode);	
					printLocNode(locNodeList);
				}
				catch(Exception e){ continue;}
				
			}
						
			else
			{
				//nodeList.get(i).locDistList.get(0);
				addToLocNodeList(locNodeList,nodeList.get(i),nodeList);
				
				
			}
			
		}
		
		//
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
	
		printLocNode(locNodeList);
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		return locNodeList;
	}*/
	
	//???????????????????????????????????????????????????????????????????????????????????????
	
	ArrayList<Loc_Node> getLocNodeTable(ArrayList<VLabel> nodeList,ArrayList <Location> desiredLocList,int fromRange,int toRange)
	{
		ArrayList <Loc_Node>locNodeList=new ArrayList<Loc_Node>();
		//********************************************************************
		//FOR EVERY NODE CREATE SORTED LIST OF LOC-DIST-LIST
		for(int i=0;i<nodeList.size();i++)
		{
			Location loc=new Location(nodeList.get(i).getX(),nodeList.get(i).getY(),base);
			nodeList.get(i).locDistList=getDesiredPointsWithDist(loc, desiredLocList,fromRange,toRange);
			//System.out.println("LOC DIST LIST SIZE OF NODES : "+nodeList.get(i).locDistList.size());
		}
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		for(int i=0;i<nodeList.size();i++)
		{
			System.out.println("------------------------------------------------");
			System.out.println("NODE :"+nodeList.get(i).uniqueId);
			for(int j=0;j<nodeList.get(i).locDistList.size();j++)
			{
				System.out.println("("+nodeList.get(i).locDistList.get(j).getLoc().getXLoc()+","+nodeList.get(i).locDistList.get(j).getLoc().getYLoc()+")   ,  "+nodeList.get(i).locDistList.get(j).distance);
			}
		}
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		//System.exit(0);
		int nodeListInitialSize=nodeList.size();
		//*********************************************************************
		ArrayList <Location>tempLocList=new ArrayList<Location>();
		for(int j=0;j<desiredLocList.size() && j<nodeListInitialSize;j++)
		{
			
			//nodeList=refineNodeList(nodeList,tempLocList);
			
			System.out.println("bbbbbbbbbbbbbbbbbbb"+nodeList.size());
			int index=0;
			Location location=nodeList.get(0).locDistList.get(0).getLoc();
			float dist=nodeList.get(0).locDistList.get(0).getDistance();
			Loc_Node locNode=new Loc_Node(nodeList.get(index).locDistList.get(0).getLoc(),nodeList.get(index),base);
			

			
			for(int i=0;i<nodeList.size();i++)
			{
				if(location==nodeList.get(i).locDistList.get(0).getLoc())
				{
					if(nodeList.get(i).locDistList.get(0).getDistance()==dist)
					{
						dist=nodeList.get(i).locDistList.get(0).getDistance();
						index=i;
						locNode=new Loc_Node(nodeList.get(index).locDistList.get(0).getLoc(),nodeList.get(index),base);	
					}
					else if(nodeList.get(i).locDistList.get(0).getDistance()<dist)
					{
						nodeList.get(index).locDistList.remove(0);
						dist=nodeList.get(i).locDistList.get(0).getDistance();
						index=i;
						locNode=new Loc_Node(nodeList.get(index).locDistList.get(0).getLoc(),nodeList.get(index),base);
					}
					else
					{
						nodeList.get(i).locDistList.remove(0);
					}
				}
				else
				{
					
				}
			
			}

			locNodeList.add(locNode);
			locNode.getLoc().occupied=true;
			//removeLocationEntry(nodeList,locNode.getLoc());
			if(canBeAdded(locNodeList,locNode))
			{
				tempLocList.add(locNode.getLoc());
				nodeList.remove(nodeList.indexOf(locNode.getNode()));
			}
	
		}
		
		//
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
	
		printLocNode(locNodeList);
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		return locNodeList;
	}
	
	/*void removeLocationEntry(ArrayList<VLabel>nodeList,Location location)
	{
		for(int i=0;i<nodeList.size();i++)
		{
			for(int j=0;j<nodeList.get(i).locDistList.size();j++)
			{
				Location tempLoc=nodeList.get(i).locDistList.get(j).getLoc();
				if(tempLoc==location)
				{
					nodeList.get(i).locDistList.remove(j);
					if(nodeList.get(i).locDistList.size()==0)
					{
						nodeList.remove(i);
					}
					break;
				}
			}
		}
	}*/
	
	
	/*ArrayList<VLabel>refineNodeList(ArrayList<VLabel>nodeList,ArrayList<Location>tempLocList)
	{
		if(nodeList.size()>0)
		{
			Location location=nodeList.get(0).locDistList.get(0).getLoc();
			while(tempLocList.contains(location))
			{
				nodeList.get(0).locDistList.remove(0);
				if(nodeList.get(0).locDistList.size()==0)
				{
					nodeList.remove(0);
					//	System.exit(0);
					refineNodeList(nodeList,tempLocList);
					break;
				}
			}
			return nodeList;
		}
		else
		{
			return null;
		}
		
	}*/
	
	
	boolean canBeAdded(ArrayList<Loc_Node>locNodeList,Loc_Node locNode)
	{
		Location tempLoc=locNode.getLoc();
		boolean flag=true;
		for(int m=0;m<locNodeList.size();m++)
		{
			//if(tempLoc==locNodeList.get(m).getLoc())
			if(cmpLoc(tempLoc,locNodeList.get(m).getLoc()))
			{
				//System.exit(0);
				flag=false;
				break;
				
			}
		}
		return flag;
	}
	//??????????????????????????????????????????????????????????????????????????????????????
	/*int getAmbiguousLoc(ArrayList<Loc_Node> locNodeList,VLabel node)
	{
		
		int ambiguousLocIndex=-1;
		for(int i=0;i<locNodeList.size();i++)
		{
			Location tempLoc=node.locDistList.get(0).getLoc();
			if(cmpLoc(tempLoc,locNodeList.get(i).getLoc()))
			{
				ambiguousLocIndex=i;
				break;
			}

		}
		return ambiguousLocIndex;
	}
	
	void addToLocNodeList(ArrayList<Loc_Node> locNodeList,VLabel node,ArrayList<VLabel>nodeList)
	{
		
		if(locNodeList.size()==0)
		{
			Loc_Node locNode=new Loc_Node(node.locDistList.get(0).getLoc(),node,base);
			locNodeList.add(locNode);
		}
		
		if(locNodeList.size()>0)
		{
			int ambiLoc=getAmbiguousLoc(locNodeList,node);
			//base.alert.display("mile stone 1");
			if(ambiLoc==-1)
			{
				Loc_Node locNode=new Loc_Node(node.locDistList.get(0).getLoc(),node,base);
				locNodeList.add(locNode);
			}
			
			if(ambiLoc>=0)
			{
				String st=ambiLoc+", "+locNodeList.size();
				//base.alert.display(st);
				VLabel matchedNode=locNodeList.get(ambiLoc).getNode();
				float a;
				float b;
				if(matchedNode.locDistList.size()>=2 && node.locDistList.size()>=2)
				{
					a=matchedNode.locDistList.get(0).getDistance()+node.locDistList.get(1).getDistance();
					b=matchedNode.locDistList.get(1).getDistance()+node.locDistList.get(0).getDistance();
				}
				else
				{
					a=matchedNode.locDistList.get(0).getDistance();
					b=node.locDistList.get(0).getDistance();
				}
				if(a>b)
				{
					locNodeList.remove(ambiLoc);
					matchedNode.locDistList.remove(0);
					Loc_Node locNode=new Loc_Node(node.locDistList.get(0).getLoc(),node,base);
					locNodeList.add(ambiLoc,locNode);//???????
					
					if(matchedNode.locDistList.size()>0)
					{
						addToLocNodeList(locNodeList,matchedNode,nodeList);
					}
					else
					{	
						nodeList.remove(nodeList.indexOf(matchedNode));
						return;
					}
				}
				else
				{
					node.locDistList.remove(0);
					if(node.locDistList.size()>0)
					{
						addToLocNodeList(locNodeList,node,nodeList);
					}
					else
					{
						nodeList.remove(nodeList.indexOf(node));
						return;
					}
					
				}
				
				
			}
		}
	}*/
	//------------------------FUNCTION TO FIND LOCATION---------------------------
	
	boolean findLoc(ArrayList<Location>locList,Location loc)
	{
		boolean flag=false;
		for(int i=0;i<locList.size();i++)
		{
			if(cmpLoc(locList.get(i),loc)==true)
			{
				flag=true;
				return true;
			}
		}
		return flag;
	}
	
	//----------------------------------------------------------------------------
	
	void setIsPlacedVariableTrue(ArrayList<VLabel>tempNodeList)
	{
		for(int i=0;i<tempNodeList.size();i++)
		{
			tempNodeList.get(i).isPlaced=true;
		}
	}
	
	
	void printLocNode(ArrayList<Loc_Node>locNodeList)
	{
		System.out.println("--------------------------------------------------");
		for(int k=0;k<locNodeList.size();k++)
		{
			System.out.println("("+locNodeList.get(k).getLoc().getXLoc()+","+locNodeList.get(k).getLoc().getYLoc()+")   ,   "+locNodeList.get(k).node.uniqueId);
		}
	}
	
	boolean cmpLoc(Location loc1,Location loc2)
	{
		if(loc1.getXLoc()==loc2.getXLoc() &&  loc1.getYLoc()==loc2.getYLoc())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	boolean cmpLocApprox(Location loc1,Location loc2)
	{
		if(loc1.getXLoc()==loc2.getXLoc() &&  loc1.getYLoc()==loc2.getYLoc())
		{
			return true;
		}
		else if(loc1.getXLoc()==loc2.getXLoc()+1 &&  loc1.getYLoc()==loc2.getYLoc()+1)
		{
			return true;
		}
		else if(loc1.getXLoc()==loc2.getXLoc()-1 &&  loc1.getYLoc()==loc2.getYLoc()-1)
		{
			return true;
		}
		
		else if(loc1.getXLoc()==loc2.getXLoc()+1 &&  loc1.getYLoc()==loc2.getYLoc()-1)
		{
			return true;
		}
		else if(loc1.getXLoc()==loc2.getXLoc()-1 &&  loc1.getYLoc()==loc2.getYLoc()+1)
		{
			return true;
		}
		
		else if(loc1.getXLoc()==loc2.getXLoc()+1 &&  loc1.getYLoc()==loc2.getYLoc())
		{
			return true;
		}
		else if(loc1.getXLoc()==loc2.getXLoc() &&  loc1.getYLoc()==loc2.getYLoc()+1)
		{
			return true;
		}
		
		else if(loc1.getXLoc()==loc2.getXLoc()-1 &&  loc1.getYLoc()==loc2.getYLoc())
		{
			return true;
		}
		else if(loc1.getXLoc()==loc2.getXLoc() &&  loc1.getYLoc()==loc2.getYLoc()-1)
		{
			return true;
		}
		
		else
		{
			return false;
		}
		
	}
	
	
	
	//***************************************************************************
	//FUNCTION TO MARK THE AREA AS COVERED---------------------------------------
	public void markCoverage(VLabel node)
	{
			int x=node.getX();
			int y=node.getY();
			
				//vToolBox.circle(base.xEventOuterList,base.yEventOuterList,x,y,j);
			int rs=(int)base.sensingRangePerNode;
				for(int yLoc=y-rs;yLoc<=y+rs;yLoc++)
				{
					for(int xLoc=x-rs;xLoc<=x+rs;xLoc++)
					{
							double distX=Math.sqrt((x-xLoc)*(x-xLoc));
							double distY=Math.sqrt((y-yLoc)*(y-yLoc));
							double dist=Math.sqrt((distX*distX)+(distY*distY));
							if(dist<=base.sensingRangePerNode)
							{
								base.xEventOuterList.add(xLoc);
								base.yEventOuterList.add(yLoc);
								base.centerPanel.coordinateMat[yLoc][xLoc].covered=true;
								
							}
					}
				}
	}
	
	//---------------------------------------------------------------------------
	
	
	//FUNCTION TO MOVE NODES IN DEPLOYMENT SCHEME 2-------------------------------------------------
	public void moveNode(final VLabel node,final Location moveFrom,final Location moveTo,final int delay)
	{
		//base.moveDelay=delay; // CAN BE USED AT OTHER PLACES
		Runnable r =new Runnable()
		{
			boolean condition=true;
			float x1=(float)moveTo.getXLoc();
			float y1=(float)moveTo.getYLoc();
			
			float x2=(float)node.getX();
			float y2=(float)node.getY();
			
			int stepCount;
			
			float xSlope;//=-1;
			float ySlope;
			
			public void run()
			{
			
			//=-((y0-y1)/(x0-x1));
			
			float dx=(float)Math.sqrt((x2-x1)*(x2-x1));
			float dy=(float)Math.sqrt((y2-y1)*(y2-y1));
			if(dx>dy)
			{
				stepCount=(int)dx;
			}
			else
			{
				stepCount=(int)dy;
			}
			
			if(x2>=x1 && y2>=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=-1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=-1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=-(dx/dx);
					ySlope=-(dy/dx);
				}
				
				else
				{
					ySlope=-(dy/dy);
					xSlope=-(dx/dy);
				}	
				
			}
			
			if(x2>=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=-1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=-(dx/dx);
					ySlope=(dy/dx);
				}
				
				else
				{
					ySlope=(dy/dy);
					xSlope=-(dx/dy);
				}		
			}
			
			if(x2<=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=(dx/dx);
					ySlope=(dy/dx);
				}
				
				else
				{
					ySlope=(dy/dy);
					xSlope=(dx/dy);
				}		
			}
			
			if(x2<x1 && y2>y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=-1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=(dx/dx);
					ySlope=-(dy/dx);
				}
				
				else
				{
					ySlope=-(dy/dy);
					xSlope=(dx/dy);
				}	
			}
			
			
			

				
				for(int i=0;i<stepCount && node.movable;i++)
				{
					x2=x2+xSlope;
					y2=y2+ySlope;
					

					node.setBounds((int)x2, (int)y2,node.getWidth(),node.getHeight());
					node.xPos=(int)x2;
					node.yPos=(int)y2;
					
					/*for(int j=0;j<base.masterNodeList.size();j++)
					{
						if(node.nearestLoc==base.masterNodeList.get(i).nearestLoc && base.masterNodeList.get(i).isPlaced==true)
						{
							node.movable=false;
							try{Thread.sleep(9000);}catch(Exception e){}
							node.counter=node.counter+1;
							node.movable=true;
							Location loc=node.getLoc();
							Location moveTo=node.desired_Loc_List.get(node.counter);
							node.nearestLoc=moveTo;
							float dist=getDist(loc,moveTo);
							try{Thread.sleep(9000);}catch(Exception e){}
							
							moveNode(node,loc, moveTo,(int)(5*dist/(node.counter)));
						}
					}*/
					//actuator.xPos=(int)x2;
					//actuator.yPos=(int)y2;
					//refreshConnections();
					try
					{
						Thread.sleep(delay);
					}
					catch(Exception e){System.out.println(e);}
				}
				//markCoverage(node);
				refreshConnections();
				
				//---------------BROADCAST TO STOP OTHER NODES FROM MOVING------------------
				/*if(node.movable)
				{
					node.isPlaced=true;
					node.setBackground(Color.BLUE);
				}
				Packet packet=new Packet(base);
				packet.queryType=base.STOP_MOVING;
				packet.destination=base.BROADCAST_DEST;
				for(int i=0;i<base.masterNodeList.size();i++)
				{
					if(node.nearestLoc==base.masterNodeList.get(i).nearestLoc)
					{
						base.masterNodeList.get(i).buffer.add(packet);
					}
				}*/
				
				//--------------------------------------------------------------------------
/*				try
				{
					Thread.sleep(10000);
					node.power=500;
					node.defaultColor=Color.BLUE;
					node.setBackground(node.defaultColor);
					//node.serviceRequestSent=false;
					actuator.moving=false;
				}catch(Exception e){}*/
			}
		};
		
		new Thread(r).start();
	}

	//----------FUNCTION TO MOVE NODES-------------------------------------------
	//FUNCTION TO MOVE NODES IN DEPLOYMENT SCHEME 2-------------------------------------------------
	public void moveNode(final VLabel node,final Location moveFrom,final Location moveTo,final int moveUptoDist,final int delay)
	{
		//base.moveDelay=delay; // CAN BE USED AT OTHER PLACES
		Runnable r =new Runnable()
		{
			boolean condition=true;
			float x1=(float)moveTo.getXLoc();
			float y1=(float)moveTo.getYLoc();
			
			float x2=(float)node.getX();
			float y2=(float)node.getY();
			
			int stepCount;
			
			float xSlope;//=-1;
			float ySlope;
			
			public void run()
			{
			
			//=-((y0-y1)/(x0-x1));
			
			float dx=(float)Math.sqrt((x2-x1)*(x2-x1));
			float dy=(float)Math.sqrt((y2-y1)*(y2-y1));
			if(dx>dy)
			{
				stepCount=(int)dx;
			}
			else
			{
				stepCount=(int)dy;
			}
			
			if(x2>=x1 && y2>=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=-1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=-1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=-(dx/dx);
					ySlope=-(dy/dx);
				}
				
				else
				{
					ySlope=-(dy/dy);
					xSlope=-(dx/dy);
				}	
				
			}
			
			if(x2>=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=-1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=-(dx/dx);
					ySlope=(dy/dx);
				}
				
				else
				{
					ySlope=(dy/dy);
					xSlope=-(dx/dy);
				}		
			}
			
			if(x2<=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=(dx/dx);
					ySlope=(dy/dx);
				}
				
				else
				{
					ySlope=(dy/dy);
					xSlope=(dx/dy);
				}		
			}
			
			if(x2<x1 && y2>y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=-1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=(dx/dx);
					ySlope=-(dy/dx);
				}
				
				else
				{
					ySlope=-(dy/dy);
					xSlope=(dx/dy);
				}	
			}
			
			
			

				
				for(int i=0;i<stepCount && node.movable;i++)
				{
					x2=x2+xSlope;
					y2=y2+ySlope;
					

					node.setBounds((int)x2, (int)y2,node.getWidth(),node.getHeight());
					node.xPos=(int)x2;
					node.yPos=(int)y2;
					
					if(getDist(node,moveTo)<moveUptoDist)
					{
						refreshConnections();
						break;
					}
					
					/*for(int j=0;j<base.masterNodeList.size();j++)
					{
						if(node.nearestLoc==base.masterNodeList.get(i).nearestLoc && base.masterNodeList.get(i).isPlaced==true)
						{
							node.movable=false;
							try{Thread.sleep(9000);}catch(Exception e){}
							node.counter=node.counter+1;
							node.movable=true;
							Location loc=node.getLoc();
							Location moveTo=node.desired_Loc_List.get(node.counter);
							node.nearestLoc=moveTo;
							float dist=getDist(loc,moveTo);
							try{Thread.sleep(9000);}catch(Exception e){}
							
							moveNode(node,loc, moveTo,(int)(5*dist/(node.counter)));
						}
					}*/
					//actuator.xPos=(int)x2;
					//actuator.yPos=(int)y2;
					//refreshConnections();
					try
					{
						Thread.sleep(delay);
					}
					catch(Exception e){System.out.println(e);}
				}
				//---------------BROADCAST TO STOP OTHER NODES FROM MOVING------------------
				/*if(node.movable)
				{
					node.isPlaced=true;
					node.setBackground(Color.BLUE);
				}
				Packet packet=new Packet(base);
				packet.queryType=base.STOP_MOVING;
				packet.destination=base.BROADCAST_DEST;
				for(int i=0;i<base.masterNodeList.size();i++)
				{
					if(node.nearestLoc==base.masterNodeList.get(i).nearestLoc)
					{
						base.masterNodeList.get(i).buffer.add(packet);
					}
				}*/
				
				//--------------------------------------------------------------------------
/*				try
				{
					Thread.sleep(10000);
					node.power=500;
					node.defaultColor=Color.BLUE;
					node.setBackground(node.defaultColor);
					//node.serviceRequestSent=false;
					actuator.moving=false;
				}catch(Exception e){}*/
			}
		};
		
		new Thread(r).start();
	}

	
	//---------------------------------------------------------------------------
	//FUNCTION TO MOVE ACTUATORS-------------------------------------------------
	public void moveActuator(final Actuator actuator,final VLabel node,final int objective,final Location moveFrom,final Location moveTo,final int delay)
	{
		//base.moveDelay=delay; // CAN BE USED AT OTHER PLACES
		Runnable r =new Runnable()
		{
			boolean condition=true;
			float x1=(float)moveTo.getXLoc();
			float y1=(float)moveTo.getYLoc();
			
			float x2=(float)actuator.getX();
			float y2=(float)actuator.getY();
			
			int stepCount;
			
			float xSlope;//=-1;
			float ySlope;
			
			public void run()
			{
			
			//=-((y0-y1)/(x0-x1));
			
			float dx=(float)Math.sqrt((x2-x1)*(x2-x1));
			float dy=(float)Math.sqrt((y2-y1)*(y2-y1));
			if(dx>dy)
			{
				stepCount=(int)dx;
			}
			else
			{
				stepCount=(int)dy;
			}
			
			if(x2>=x1 && y2>=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=-1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=-1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=-(dx/dx);
					ySlope=-(dy/dx);
				}
				
				else
				{
					ySlope=-(dy/dy);
					xSlope=-(dx/dy);
				}	
				
			}
			
			if(x2>=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=-1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=-(dx/dx);
					ySlope=(dy/dx);
				}
				
				else
				{
					ySlope=(dy/dy);
					xSlope=-(dx/dy);
				}		
			}
			
			if(x2<=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=(dx/dx);
					ySlope=(dy/dx);
				}
				
				else
				{
					ySlope=(dy/dy);
					xSlope=(dx/dy);
				}		
			}
			
			if(x2<x1 && y2>y1)
			{
				if(dy==0 && dx !=0)
				{
					xSlope=1;
					ySlope=0;
				}
				
				if(dx==0 && dy!=0)
				{
					ySlope=-1;
					xSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					xSlope=0;
					ySlope=0;
				}
				if(dx>dy)
				{
					xSlope=(dx/dx);
					ySlope=-(dy/dx);
				}
				
				else
				{
					ySlope=-(dy/dy);
					xSlope=(dx/dy);
				}	
			}
			
			
			

				
				for(int i=0;i<stepCount && actuator.movable;i++)
				{
					x2=x2+xSlope;
					y2=y2+ySlope;
					
					//if(base.centerPanel.coordinateMat[(int)Math.ceil(x2)][(int)Math.ceil(y2)].obstacle)
					//{
						//condition=false;
						
						//obstacleHandler(node,(int)x2,(int)y2);
						//break;
					//}
					//else if(base.centerPanel.coordinateMat[(int)(x2)][(int)(y2)].obstacle)
					//{
					//condition=false;
					//	obstacleHandler(node,(int)x2,(int)y2);
//						break;
					//}
					//else if(base.centerPanel.coordinateMat[(int)Math.ceil(x2)][(int)(y2)].obstacle)
					//{
//						condition=false;
						//obstacleHandler(node,(int)x2,(int)y2);
						//break;
					//}
					//else if(base.centerPanel.coordinateMat[(int)(x2)][(int)Math.ceil(y2)].obstacle)
					//{
//						condition=false;
						//obstacleHandler(node,(int)x2,(int)y2);
						//break;
					//}
					
					actuator.setBounds((int)x2, (int)y2,actuator.getWidth(),actuator.getHeight());
					//actuator.xPos=(int)x2;
					//actuator.yPos=(int)y2;
					//refreshConnections();
					try
					{
						Thread.sleep(delay);
					}
					catch(Exception e){System.out.println(e);}
				}
				if(objective==base.CHARGING)
				{
					try
					{
						Thread.sleep(10000);
						node.power=500;
						node.defaultColor=Color.BLUE;
						node.setBackground(node.defaultColor);
						node.serviceRequestSent=false;
						actuator.moving=false;
					}catch(Exception e){}
				}
			}
		};
		
		new Thread(r).start();
	}
//-------------------------------------------------------------------------

//FUNCTION TO PERMANENTLY  KILL THE NODE-----------------------------------
	
	void killNode(VLabel node)
	{
		for(int i=0;i<node.neighborList.size();i++)
		{
			VLabel tempNode=node.neighborList.get(i);
			int index=tempNode.neighborList.indexOf(node);
			if(index>=0)
			{
				tempNode.neighborList.remove(index);
				tempNode.connectionList.remove(index);
			}
		}
		node.isAlive=false;
		node.neighborList.removeAll(node.neighborList);
		node.connectionList.removeAll(node.connectionList);
		base.masterNodeList.remove(base.masterNodeList.indexOf(node));
		base.centerPanel.remove(node);
		
		for(int i=0;i<base.masterBSList.get(0).master_Pcd_List.size();i++)
		{
			if(base.masterBSList.get(0).master_Pcd_List.get(i).child==node ||base.masterBSList.get(0).master_Pcd_List.get(i).parent==node)
			{
				base.masterBSList.get(0).master_Pcd_List.get(i).connection=false;	
			}
		}
		
		for(int i=0;i<base.actuatorList.size();i++)
		{
			Actuator tempActuator=base.actuatorList.get(i);
			for(int j=0;j<tempActuator.master_Pcd_List.size();j++)
			{
				if(tempActuator.master_Pcd_List.get(j).child==node || tempActuator.master_Pcd_List.get(j).parent==node)
				{
					tempActuator.master_Pcd_List.get(j).connection=false;
				}
			}
		}
		
		for(int i=0;i<base.actuatorList.size();i++)
		{
			Actuator tempActuator=base.actuatorList.get(i);
			{
				for(int j=0;j<tempActuator.nodesDirectlyConnectedActuator.size();j++)
				{
					if(node==tempActuator.nodesDirectlyConnectedActuator.get(j))
					{
						tempActuator.nodesDirectlyConnectedActuator.remove(j);
					}
				}
			}
		}
	}
	
	
//---------------------------------------------------------------------------
	
	//FUNCTION TO REMOVE ALL CONNECTIONS-------------------------------------
	void removeConnections()
	{
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			base.masterNodeList.get(i).connectionList.removeAll(base.masterNodeList.get(i).connectionList);
			for(int j=0;j<base.masterNodeList.get(i).neighborList.size();j++)
			{
				base.masterNodeList.get(i).connectionList.add(false);
			}
		}
		
		for(int i=0;i<base.masterBSList.get(0).master_Pcd_List.size();i++)
		{
			base.masterBSList.get(0).master_Pcd_List.get(i).connection=false;
		}
		
		for(int i=0;i<base.actuatorList.size();i++)
		{
			Actuator tempActuator=base.actuatorList.get(i);
			for(int j=0;j<tempActuator.master_Pcd_List.size();j++)
			{
				tempActuator.master_Pcd_List.get(j).connection=false;
			}
		}
	}
	//-----------------------------------------------------------------------
	
//FUNCTION TO PERMANENTLY KILL ACTUATOR--------------------------------------
	void killActuator(Actuator actuator)
	{
		actuator.isAlive=false;
	}
//---------------------------------------------------------------------------
	
//FUNCTION TO CHECK THE LOOP WHILE FORMING MST-------------------
	boolean loopCheckForMST(VLabel startNode,VLabel endNode)
	{
		ArrayList<VLabel>tempList=new ArrayList<VLabel>();
		tempList.add(endNode);
		boolean isLoop=false;
		for(int i=0;i<tempList.size();i++)
		{
			if(tempList.get(i).mstNeighborList.size()>0)
			{
				for(int j=0;j<tempList.get(i).mstNeighborList.size();j++)
				{
					if(tempList.get(i).mstNeighborList.get(j)==startNode)
					{
						isLoop=true;
					}
					else if(!tempList.contains(tempList.get(i).mstNeighborList.get(j)))
					{
						tempList.add(tempList.get(i).mstNeighborList.get(j));
					}
				}
			}
		}
		return isLoop;
	}
//---------------------------------------------------------------
	
//---------------------FUNCTION TO CREATE PATH TREE--------------------
	
	void createPathTree(Object root,Queue<VLabel>tempQueue)
	{
		//try{Thread.sleep(200);}catch(Exception e){}
		ArrayList<Parent_Child_Dist> pcd_List=new ArrayList<Parent_Child_Dist>();
		Queue<VLabel>childrenQueue=new LinkedList<VLabel>();
		boolean found_replaced=false;
		while(tempQueue.size()>0)
		{
			VLabel tempLb=tempQueue.poll();
			tempLb.setBackground(Color.BLUE);
			System.out.println("node id ;"+tempLb.uniqueId);
			
			
			for(int j=0;j<tempLb.neighborList.size();j++)
			{
				if(tempLb.neighborList.get(j).traversed_for_tree==false)
				{
					VLabel parent=tempLb;
					VLabel child=tempLb.neighborList.get(j);			
					float dist=getDist(tempLb,tempLb.neighborList.get(j));
					found_replaced=false;
					for(int k=0;k<pcd_List.size();k++)
					{
						
						if(pcd_List.get(k).child==child)
						{
							found_replaced=true;
							if(dist<pcd_List.get(k).dist )
							{
								pcd_List.remove(k);
								Parent_Child_Dist temp_pcd=new Parent_Child_Dist(parent,child,dist,base);
								temp_pcd.connection=true;
								pcd_List.add(temp_pcd);
								child.traversed_for_tree=true;
								childrenQueue.add(child);
							}
						}					
					}
					if(!found_replaced)
					{
						Parent_Child_Dist temp_pcd=new Parent_Child_Dist(parent,child,dist,base);
						temp_pcd.connection=true;
						pcd_List.add(temp_pcd);
						child.traversed_for_tree=true;
						childrenQueue.add(child);
					}
					
				}
			}
		}
		
		//base.baseStationArray[0].master_Pcd_List.addAll(pcd_List);
		
		//------------ADDING PARENT AND ROOT TO ROUTING PATH----------------------
		for(int i=0;i<pcd_List.size();i++)
		{
			VLabel tempChild=pcd_List.get(i).child;
			Object rt=root;
			Object p=pcd_List.get(i).parent;
			Root_Parent_Children rpc=new Root_Parent_Children(rt,p);
			tempChild.path_Tree_Routing_Table.add(rpc);
		}
		//------------------------------------------------------------------------
		
		
		try
		{
			Actuator actuator=Actuator.class.cast(root);
			System.out.println(actuator.uniqueId);
			System.out.println("MASTER PCD LIST SIZE"+actuator.master_Pcd_List.size());
			System.out.println("PCD LIST SIZE:"+pcd_List.size());
			//base.alert.display("PCD LIST SIZE:"+pcd_List.size());
			actuator.master_Pcd_List.addAll(pcd_List);
			//System.exit(0);
		}
		catch(Exception e){}
		
		try
		{
			VLabel baseStation=VLabel.class.cast(root);
			baseStation.master_Pcd_List.addAll(pcd_List);
		}catch(Exception e){}
		

		
		//root.master_Pcd_List.addAll(pcd_List);
		//System.out.println("LIST SIZE :"+pcd_List.size());
		base.drawPathTree=true;
	//	System.out.println("Master pcd list"+base.baseStationArray[0].master_Pcd_List.size());
	//for(int l=0;l<base.baseStationArray[0].master_Pcd_List.size();l++)
	//	{
	//		Parent_Child_Dist tempPcd=base.baseStationArray[0].master_Pcd_List.get(l);
	//		System.out.println("parent id :"+tempPcd.parent.uniqueId+"      child id :"+tempPcd.child.uniqueId+"     distance :"+tempPcd.dist);
	//	}
		//System.exit(0);
		
		try
		{
		if(childrenQueue.size()>0)
			{
				createPathTree(root,childrenQueue);
			}
		}catch(Exception e){}

	}	
//------------------------------------------------------------------------
	//---------------------FUNCTION TO INITIALIZE NODE STATE---------------
	void initNodeState()
	{
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			base.masterNodeList.get(i).traversed_for_tree=false;
		}
	}
	//---------------------------------------------------------------------
	//----------------FUNCTION TO CREATE ROUTING TABLE----------------------
	void completePathTreeTable(Object root,ArrayList<Parent_Child_Dist>pcd)
	{
		for(int i=0;i<pcd.size();i++)
		{
			try
			{
				VLabel node=VLabel.class.cast(pcd.get(i).parent);
				VLabel child=pcd.get(i).child;
				int index=-1;
				for(int j=0;j<node.path_Tree_Routing_Table.size();j++)
				{
					if(root==node.path_Tree_Routing_Table.get(j).root)
					{
						if(!node.path_Tree_Routing_Table.get(j).childeren_List.contains(child))
						{
							node.path_Tree_Routing_Table.get(j).childeren_List.add(child);
						}
						break;
					}
				}
				
				
				
			}catch(Exception e){}
		}
	}
	//----------------------------------------------------------------------
	
	boolean interferance(ArrayList<VLabel>list1, ArrayList<VLabel>list2)
	{

		boolean returnValue=false;
		try
		{
			for(int i=0;i<list1.size();i++)
			{
				VLabel temp1=list1.get(i);
				for(int j=0;j<list2.size();j++)
				{
					VLabel temp2=list2.get(j);
					if(temp1==temp2)
					{
						returnValue=true;
						//base.alert.display("intersected...........");
						break;
					}
				}
			}
		}
		catch(Exception e){}
		return returnValue;
	}
	
	//----------------------------------------------------------------------
	
	//---------------FUNCTION TO UPDTAE NEIGHBORING LOClIST-----------------  // baad me naam or prototype dono change kar diyo..
/*	void updateNeighboringLocList(VLabel thisNode)
	{
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			VLabel tempNode=base.masterNodeList.get(i);
			if(tempNode.isPlaced && getDist(thisNode,tempNode)<=base.commRangePerNode+2)
			{
				int index=-1;
				for(int j=0;j<thisNode.neighbouringLocList.size();j++)
				{
					if(cmpLocApprox(tempNode.getLoc(),thisNode.neighbouringLocList.get(j)))
					{
						index=j;
						break;
					}
				}
				if(index>=0)
				{
					//System.exit(0);
					thisNode.neighbouringLocList.remove(index);
					//tempNode.setBackground(Color.MAGENTA);
				}	
			}
		}
	}
	*/
	
	//----------------------------------------------------------------------
	
	
	
	//----------------FUNCTION TO BROADCAST BY BASE STATION-----------------
    // BROADCAST TO SUPPLY THE NODES WITH THE LOCATION OF BASE STATION-------
	void broadcast(VLabel sourceNode)
	{
		//Location location=new Location(sourceNode.getX(),sourceNode.yPos);
		Packet packet=new Packet(base);
		packet.queryType=base.SET_BASE_INFO;
		packet.data=sourceNode;
		packet.protocolType=base.BROADCAST;
		
		packet.source=sourceNode;
		packet.destination=base.BROADCAST_DEST;
		packet.sourceType=base.BASESTATION;
		packet.ttl=1;
		packet.hopCount=0;
		packet.identifier=7;
		packet.fromNode=sourceNode;
		
		base.baseProperties.showConnection();
		sourceNode.setBackground(base.BLACK);
		for(int i=0;i<sourceNode.neighborList.size();i++)
		{
			packet.toNode=sourceNode.neighborList.get(i);
			if(sourceNode.neighborList.get(i).buffer.size()<sourceNode.neighborList.get(i).defaultBufferSize)
				sourceNode.neighborList.get(i).buffer.add(packet);
				sourceNode.neighborList.get(i).setBackground(base.VOILET);			
		}
			
	}
//-------------------------------------------------------------------------
	
//--------FUNCTION FOR DIVIDING THE DEPLOYMENT AREA FOR CLUSTERS-----------
	
	public void daSubDivider(final int side)
	{		
				base.daSubdivisionList.removeAll(base.daSubdivisionList);
				for(int x=0;x<500;x=x+side)
				{
					for(int y=0;y<500;y=y+side)
					{				
						ArrayList <Location> tempLocList=new ArrayList<Location>();						
						for(int xInner=x;xInner<x+side-2;xInner++)
						{
							for(int yInner=y;yInner<y+side-2;yInner++)
							{								
								Location tempLoc=new Location(xInner+100,yInner+100,base);
								tempLocList.add(tempLoc);					
							}
						}
						base.daSubdivisionList.add(tempLocList);										
					}
				}		
	}
	
	//----FUNCTION TO GET THE SUB DIVISION TO WHICH THE NODE BELONGS------------
	
	public int getNodeSubDivision(VLabel node, int subDivisionSide,Base base)
	{
		int x=node.getX();
		int y=node.getY();
		
		int widthDa=Integer.parseInt(base.dxTf.getText());
		int heightDa=Integer.parseInt(base.dyTf.getText());
		
		int totalX=widthDa/subDivisionSide;
		int totalY=heightDa/subDivisionSide;
		
		int xBlock=x/subDivisionSide;
		int yBlock=y/subDivisionSide;
		int subDivisionNumber=(xBlock*yBlock)+((totalX-xBlock)*(yBlock-1));		
		//System.out.println("Block number : "+subDivisionNumber);
		return subDivisionNumber; 
	}
	//----------------------------------------------------------------------
	
	//----FUNCTION TO ASSIGN SUB DIVISIONS TO NODES-------------------------
	
	public void assignSubDivision(int subDivisionSide,Base base)
	{
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			VLabel tempNode=base.masterNodeList.get(i);
			//System.out.println(i);
			tempNode.setBackground(Color.RED);
			tempNode.subDivisionNumber=getNodeSubDivision(tempNode, subDivisionSide, base);
		}
		//System.exit(0);
	}
	
	
	//----------------------------------------------------------------------
	
	//------------------SELECT THE CLUSTER HEAD RANDOMLY----------------------------------
	void selectRandomClusterHead(int percentage)
	{
		base.clusterHeadList.removeAll(base.clusterHeadList);
		int counter=0;
		//int divider=(percentage/100)*base.masterNodeList.size();
		int divider=100/percentage;
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			VLabel tempNode=base.masterNodeList.get(i);
			if(counter%divider==0)
			{
				tempNode.isClusterHead=true;
				base.clusterHeadList.add(tempNode);
				tempNode.defaultColor=Color.RED;
				tempNode.setBackground(Color.RED);
				tempNode.setBounds(tempNode.getX(),tempNode.getY(),15,15);
			}
			counter=counter+1;
		}
	}
	
	//------------------------------------------------------------------------------------
	//====================================================================================
	//---------------FUNCTION TO FORM CLUSTER HEADS IN STATIC CLUSTERING------------------
	
	public void setClusterHeads()
	{
		int counter=Integer.parseInt(base.dyTf.getText())/100;
		counter=counter*counter;
		for(int j=1;j<=counter;j++)
		{
			Cluster tempCluster=new Cluster();
			tempCluster.clusterId=j;
			ArrayList<VLabel>tempList=new ArrayList<VLabel>();
			for(int i=0;i<base.masterNodeList.size();i++)
			{			
				VLabel tempNode=base.masterNodeList.get(i);			
				if(tempNode.subDivisionNumber==j)
				{
					tempNode.memberOfCluster=tempCluster;
					tempCluster.memberList.add(tempNode);
				}
			}
			tempCluster.clusterHead=tempCluster.memberList.get(0);
			
			base.clusterList.add(tempCluster);
		}
		
		base.alert.display(""+ base.clusterList.size());
		
		for(int i=0;i<base.clusterList.size();i++)
		{
			base.clusterList.get(i).clusterHead.defaultColor=Color.ORANGE;
			base.clusterHeadList.add(base.clusterList.get(i).clusterHead);
			base.clusterList.get(i).clusterHead.isClusterHead=true;
		}
	}
	
	//-----------------------------------------------------------------------------------
	
	//------------FUNCTION TO CHANGE THE CLUSTER HEAD------------------------------------
	
	public void nextClusterHead(VLabel clusterHead)
	{
		Cluster cluster=clusterHead.memberOfCluster;
		clusterHead.defaultColor=Color.BLUE;
		int index=cluster.memberList.indexOf(clusterHead);
		int index2=base.clusterHeadList.indexOf(clusterHead);
		base.clusterHeadList.remove(index2);
		index=index+1;
		if(index>=cluster.memberList.size())
		{
			index=0;
		}
		cluster.clusterHead=cluster.memberList.get(index);
		cluster.clusterHead.defaultColor=Color.ORANGE;	
		base.clusterHeadList.add(index2,cluster.clusterHead);
		//System.exit(0);
	}
	//-----------------------------------------------------------------------------------
	
	public void startEDSRoundsWithMultihop(int rounds)
	{
		for(int i=0;i<rounds;i++)
		{
			base.vDaOptionDialog.edsRoundMultihop();
			base.alert.display(" round : "+i);
			try{Thread.sleep(5000);}catch(Exception e){}
			for(int j=0;j<base.clusterHeadList.size();j++)
			{
				VLabel ch=base.clusterHeadList.get(j);
				nextClusterHead(ch);
			}
			try{Thread.sleep(5000);}catch(Exception e){}
			broadcastClsuterHeadStatus();
			try{Thread.sleep(5000);}catch(Exception e){}
			sendDataToClusterHead();
			try{Thread.sleep(5000);}catch(Exception e){}
		}
	
	}
	
	
	public void startEDSRoundsWithDirectComm(int round)
	{
		for(int i=0;i<round;i++)
		{
			//base.vDaOptionDialog.edsRoundDirectComm();
			base.round=round;
			sendAggrigatedDataDirectlyToBS();
			base.alert.display(" round : "+i);
			try{Thread.sleep(1000);}catch(Exception e){}
			for(int j=0;j<base.clusterHeadList.size();j++)
			{
				VLabel ch=base.clusterHeadList.get(j);
				nextClusterHead(ch);
			}
			try{Thread.sleep(1000);}catch(Exception e){}
			broadcastClsuterHeadStatus();
			try{Thread.sleep(1000);}catch(Exception e){}
			sendDataToClusterHead();
			try{Thread.sleep(1000);}catch(Exception e){}
		}
	
	}
	
	//------------FUNCTION TO CALCULATE THE LOCATION OF A CLUSTER------------------------
	public void calculateClusterLocation()
	{
		for(int i=0;i<25;i++)
		{
			Cluster tempCluster=base.clusterList.get(i);
			int x=0;
			int y=0;
			for(int j=0;j<tempCluster.memberList.size();j++)
			{
				VLabel tempNode=tempCluster.memberList.get(j);
				x=x+tempNode.getX();
				y=y+tempNode.getY();
			}
			int averageX=x/tempCluster.memberList.size();
			int averageY=y/tempCluster.memberList.size();
			Location loc=new Location(averageX,averageY,base);
			tempCluster.clusterLoc=loc;
		}
	}
	
	//----------------------------------------------------------------------------------
	
	//-------------FUNCTION TO CREATE BS-CLUSTER LIST------------------------------------
	void updateBS_Cluster_List(int baseStationId)
	{
		VLabel baseStation=base.masterBSList.get(baseStationId);
		int value=500/100;
		calculateClusterLocation();
		for(int i=0;i<25;i++)
		{		Cluster tempCluster=base.clusterList.get(i);
				int currentClusterId=tempCluster.clusterId;
				float minDist=999999;
				Cluster nearestCluster=null;
				
				
				
				Location baseLoc=baseStation.getLoc();
				
				float diagonal=(float)Math.sqrt((100*100)+(100*100));
				
				BS_Cluster bs_Cluster=new BS_Cluster();
				
				if(getDist(baseLoc,tempCluster.clusterLoc)<=100)
					{
						//base.alert.display("cluster ID :"+tempCluster);
						base.zeroCluster.clusterId=-1;
						bs_Cluster.BaseStation=baseStation;
						bs_Cluster.cluster=base.zeroCluster;
						
					}
				else
				{
				
				
				
				
				
				
				
				
			//......................................................................................
				int index=currentClusterId-6-1;
				
				if(index>=0 && index<25 && (tempCluster.clusterId-1)%value!=0 )
				{
					Location tempClusterLoc=base.clusterList.get(index).clusterLoc;
					Location currentClusterLoc=tempCluster.clusterLoc;
					
					float totalDist=getDist(baseLoc,tempClusterLoc);
					if(totalDist<minDist)
					{
						minDist=totalDist;
						nearestCluster=base.clusterList.get(index);
					}
				}
			//...................................................................................
				index=currentClusterId-5-1;
				
				if(index>=0 && index<25)
				{
					Location tempClusterLoc=base.clusterList.get(index).clusterLoc;
					Location currentClusterLoc=tempCluster.clusterLoc;
					
					float totalDist=getDist(baseLoc,tempClusterLoc);
					if(totalDist<minDist)
					{
						minDist=totalDist;
						nearestCluster=base.clusterList.get(index);
					}
				}
			//...................................................................................
				index=currentClusterId-4-1;
				
				if(index>=0 && index<25 && tempCluster.clusterId%value!=0)
				{
					Location tempClusterLoc=base.clusterList.get(index).clusterLoc;
					Location currentClusterLoc=tempCluster.clusterLoc;
					
					float totalDist=getDist(baseLoc,tempClusterLoc);
					if(totalDist<minDist)
					{
						minDist=totalDist;
						nearestCluster=base.clusterList.get(index);
					}
				}
			//..................................................................................
				index=currentClusterId-1-1;
				
				if(index>=0 && index<25 && (tempCluster.clusterId-1)%value!=0)
				{
					Location tempClusterLoc=base.clusterList.get(index).clusterLoc;
					Location currentClusterLoc=tempCluster.clusterLoc;
					
					float totalDist=getDist(baseLoc,tempClusterLoc);
					if(totalDist<minDist)
					{
						minDist=totalDist;
						nearestCluster=base.clusterList.get(index);
					}
				}
			//.................................................................................
				index=currentClusterId+1-1;
				
				if(index>=0 && index<25  && tempCluster.clusterId%value!=0)
				{
					Location tempClusterLoc=base.clusterList.get(index).clusterLoc;
					Location currentClusterLoc=tempCluster.clusterLoc;
					
					float totalDist=getDist(baseLoc,tempClusterLoc);
					if(totalDist<minDist)
					{
						minDist=totalDist;
						nearestCluster=base.clusterList.get(index);
					}
				}
			//...............................................................................
				index=currentClusterId+4-1;
				
				if(index>=0 && tempCluster.clusterId<=20  && tempCluster.clusterId%value!=0 && (tempCluster.clusterId-1)%value!=0)
				{
					Location tempClusterLoc=base.clusterList.get(index).clusterLoc;
					Location currentClusterLoc=tempCluster.clusterLoc;
					
					float totalDist=getDist(baseLoc,tempClusterLoc);
					if(totalDist<minDist)
					{
						minDist=totalDist;
						nearestCluster=base.clusterList.get(index);
					}
				}
			//..........................................................................
				index=currentClusterId+5-1;
				
				if(index>=0 && tempCluster.clusterId<=20)
				{
					Location tempClusterLoc=base.clusterList.get(index).clusterLoc;
					Location currentClusterLoc=tempCluster.clusterLoc;
					
					float totalDist=getDist(baseLoc,tempClusterLoc);
					if(totalDist<minDist)
					{
						minDist=totalDist;
						nearestCluster=base.clusterList.get(index);
					}
				}
			//..........................................................................
				index=currentClusterId+6-1;
				
				if(index>=0 && tempCluster.clusterId<=20  && tempCluster.clusterId%value!=0)
				{
					Location tempClusterLoc=base.clusterList.get(index).clusterLoc;
					Location currentClusterLoc=tempCluster.clusterLoc;
					
					float totalDist=getDist(baseLoc,tempClusterLoc);
					if(totalDist<minDist)
					{
						minDist=totalDist;
						nearestCluster=base.clusterList.get(index);
					}
				}
			//............................................................................
			}
				

			//............................................................................
					bs_Cluster.BaseStation=baseStation;
					bs_Cluster.cluster=nearestCluster;						
			
			for(int k=0;k<tempCluster.memberList.size();k++)
			{
				tempCluster.memberList.get(k).BS_Cluster_List.add(bs_Cluster);
			}						
		}
	}
	//-----------------------------------------------------------------------------------
	
	//--------------FUNCTION FOR BORADCASTING CLUSTER HEAD STATUS------------------------
	public void broadcastClsuterHeadStatus()
	{
		for(int i=0;i<base.clusterHeadList.size();i++)
		{
			VLabel tempCH=base.clusterHeadList.get(i);
			ArrayList<VLabel>memberList=tempCH.clusterNeighborList;
			for(int j=0;j<memberList.size();j++)
			{
				VLabel tempNode=memberList.get(j);
				if(tempNode!=tempCH)
				{
					tempNode.setBackground(Color.RED);
					tempNode.nodeClusterHead=tempCH;
				}
			}
		}
	}
	
	//-----------------------------------------------------------------------------------
	
	//------------FUNCTION TO SEND DATA TO CLUSTER HEAD----------------------------------
	
	public void sendDataToClusterHead()
	{
		for(int i=0;i<base.clusterHeadList.size();i++)
		{
			final VLabel tempCH=base.clusterHeadList.get(i);
			Runnable r=new Runnable()
			{
				public void run()
				{
			
					for(int j=0;j<tempCH.clusterNeighborList.size();j++ )
					{
						VLabel tempNode=tempCH.clusterNeighborList.get(j);
						
						Packet packet=new Packet(base);
						packet.queryType=base.SET_DATA;
						packet.data=tempNode.uniqueId;
						packet.protocolType=base.BROADCAST;
						
						packet.source=tempNode;
						packet.destination=tempCH;
						packet.sourceType=base.SENSORNODE;
						packet.ttl=1;
						packet.hopCount=0;
						packet.identifier=7;
						packet.fromNode=tempNode;
						//------------------add code to decrease the energy level the node------------
						
						//----------------------------------------------------------------------------
						if(energyEnoughForTransmission(tempNode,tempCH))
						{
							tempCH.buffer.add(packet);
							tempCH.setBackground(Color.RED);
						}
						//try{Thread.sleep(100);}catch(Exception e){}
					}
				}				
			};
			new Thread(r).start();
		}
	}
	
	//-------------------------------------------------------------------------------------
	
	//----------FUNCTION USED BY BASE STATION TO GET AGGRIGATED DATA FROM CLUSTER HEAD-----
	
	public void getAggrigatedDataDirectly(int baseStationNumber)
	{
		try
		{
			for(int i=0;i<base.clusterHeadList.size();i++)
			{
				VLabel tempCH=base.clusterHeadList.get(i);
				//int baseStationNumber=tempCH.commCounter%base.masterBSList.size();
				
				Packet packet=new Packet(base);
				packet.queryType=base.GET_DATA;
				packet.data=null;
				packet.protocolType=base.BROADCAST;
				
				packet.source=base.masterBSList.get(baseStationNumber);
				//	packet.destination=base.nodeArray[counter];
				packet.destination=tempCH;
				packet.sourceType=base.BASESTATION;
				packet.ttl=1;
				packet.hopCount=0;
				packet.identifier=7;
				packet.fromNode=base.masterBSList.get(baseStationNumber);				
				//	base.baseProperties.showConnection();
				tempCH.buffer.add(packet);
				base.masterBSList.get(baseStationNumber).setBackground(base.BLACK);									
			}
		}
		catch(Exception e){base.alert.display("base station not deployed");}
		
	}
	//-------------------------------------------------------------------------------------
	
	//----------FUNCTION USED BY BASE STATION TO GET AGGRIGATED DATA FROM CLUSTER HEAD-----
	int counter=0;
	public void sendAggrigatedDataDirectlyToBS()
	{		
		try
		{
			for(int i=0;i<base.clusterHeadList.size();i++)
			{
				VLabel tempCH=base.clusterHeadList.get(i);
				int baseStationNumber=tempCH.commCounter%base.masterBSList.size();
				tempCH.commCounter=tempCH.commCounter+1;
				Packet packet=new Packet(base);
				packet.queryType=base.SEND_AGGRIGATED_DATA;
				packet.data=tempCH.dataSt;
				packet.protocolType=base.BROADCAST;
				
				packet.source=tempCH;
				//	packet.destination=base.nodeArray[counter];
				packet.destination=base.masterBSList.get(baseStationNumber);
				packet.sourceType=base.SENSORNODE;
				packet.ttl=1;
				packet.hopCount=0;
				packet.identifier=7;
				packet.fromNode=tempCH;				
				//	base.baseProperties.showConnection();
				if(energyEnoughForTransmission(tempCH, base.masterBSList.get(baseStationNumber)))
				{
					base.masterBSList.get(baseStationNumber).buffer.add(packet);
				}
				counter=counter+1;
				//base.sendFrom=tempCH.getLoc();
				//base.sendTo=base.masterBSList.get(baseStationNumber).getLoc();
				tempCH.setBackground(Color.GREEN);
				//base.alert.display("counter : "+counter);
				base.masterBSList.get(baseStationNumber).setBackground(base.BLACK);
				try
				{
					//Thread.sleep(10);
				}catch(Exception e){}
			}
		}
		catch(Exception e){base.alert.display("base station not deployed");}
		
	}
	//-------------------------------------------------------------------------------------
	
	//------------FUNCTION FOR MULTI HOP CLUSTER COMMUNICATION------------------------
	
	public Cluster getNextClusterHead(VLabel node,VLabel baseStation)
	{
		ArrayList<BS_Cluster>bc_List=node.BS_Cluster_List;
		Cluster cluster=null;
		for(int i=0;i<bc_List.size();i++)
		{
			VLabel bs=bc_List.get(i).BaseStation;
			if(bs.equals(baseStation))
			{
				cluster=bc_List.get(i).cluster;
				break;
			}
		}
		return cluster;
	}		
	//-------------------------------------------------------------------------------------
	
	//--------------SETTING INITIAL ENERGY OF NODES----------------------------------------
	
	void setNodeInitialEnergy(Double initialEnergy)
	{
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			VLabel tempNode=base.masterNodeList.get(i);
			tempNode.power=initialEnergy;			
		}
	}
	//-------------------------------------------------------------------------------------
	
	
	//------------FUNCTION FOR ENERGY MANAGEMENT------------------------------------------
	
	public boolean energyEnoughForTransmission(VLabel senderNode, VLabel receiverNode)
	{
		float dist=getDist(senderNode,receiverNode);
		//dist=10;
		dist=dist/5;
		System.out.println("the name of the author is vikrant sharma");
		double recievingEnergy=base.energyForReceiving*2000;

		if(dist>base.vdo)
		{
			//****************for long distance energy**************************
			double transmissionEnergyForLongRnge=(base.energyForTransfer+base.eda)*(4000.0)+base.emp*4000.0*(dist*dist*dist*dist);
			//double transmissionEnergyForLongRnge=(base.energyForTransfer+base.eda)*(2000)+base.emp*2000*(dist*dist);
			if(senderNode.power>transmissionEnergyForLongRnge)
			{
				senderNode.power=senderNode.power-transmissionEnergyForLongRnge;
				receiverNode.power=receiverNode.power-recievingEnergy;
				return true;
			}
			else
			{
				System.out.println("long range transmission energy : "+ transmissionEnergyForLongRnge+" , Energy :"+senderNode.power);
				//System.exit(0);
				senderNode.defaultColor=base.LGHT_PINK;
				Node_Rounds nr=new Node_Rounds();
				nr.node=senderNode;
				nr.lastRound=base.round;
				base.nodeRoundList.add(nr);
				senderNode.lastRound=base.round;
				return false;
				
			}
		}
		else
		{
			double transmissionEnergyForShortRange=(base.energyForTransfer+base.eda)*(4000)+base.emp*4000*(dist*dist);
			if(senderNode.power>transmissionEnergyForShortRange)
			{
				senderNode.power=senderNode.power-transmissionEnergyForShortRange;
				receiverNode.power=receiverNode.power-recievingEnergy;
				return true;
			}
			else
			{
				senderNode.defaultColor=base.LGHT_PINK;
				Node_Rounds nr=new Node_Rounds();
				nr.node=senderNode;
				nr.lastRound=base.round;
				base.nodeRoundList.add(nr);
				senderNode.lastRound=base.round;
				return false;
			}
		}		
		//ALSO REDUCE THE RECEIVING ENERGY FROM THE RECIEVING NODE.
	}
	
	//------------------------------------------------------------------------------------
	
	//************FUNCTION TO GET THE ANGLE BETWEEN GIVEN THREE COORDINATES***************
	
	public double getAngle(VCoordinate locA, VCoordinate locB, VCoordinate locC)
	{
		double s1=(double)(locA.y-locB.y)/(double)(locA.x-locB.x);
		System.out.println(s1);
		
		double s2=(double)(locB.y-locC.y)/(double)(locB.x-locC.x);
		System.out.println(s2);
		
		double a1=Math.toDegrees(Math.atan(s1));
		System.out.println(a1);
		
		double a2=Math.toDegrees(Math.atan(s2));
		System.out.println(a2);
		
		double angle;
		
		if(a1<0 && a2<0)
		{
			angle=Math.abs(a1-a2);
		}
		else if(a1>0 && a2>0)
		{
			angle=Math.abs(a1-a2);
		}
		else
		{
			angle=180-(Math.abs(a1)+Math.abs(a2));
		}
		System.out.println("angle  : "+angle);
		return angle;
	}
	//#####################################################################################
	
	//*****************FUNCTION TO ARIALLY POSITION THE NODE*******************************
	
	public void arialPositioning(final VLabel node)
	{
		Runnable r=new Runnable()
		{
			public void run()
			{
				
			}
		};
		new Thread(r).start();
	}
	
	//####################################################################################
	
	
	//------------FUNCTION TO PLOT 3-D PARAMATERS----------------------------------------
	
	public void plot3DParameters(int x,int y,int height)
	{
		for(int i=0;i<y;i++)
		{
			for(int j=0;j<x;j++)
			{
				base.centerPanel.coordinateMat[i][j]=new VCoordinate(base);
				base.centerPanel.coordinateMat[i][j].x=i;
				base.centerPanel.coordinateMat[i][j].y=j;
				Location tempLoc=new Location(i,j,base);
				base.centerPanel.coordinateMat[i][j].location=tempLoc;
				
				VCoordinate tempCoordinate=base.centerPanel.coordinateMat[i][j];
				for(int k=0;k<height;k++)
				{
					//??????????????
				}
			}
		}
	}
	
	//###################################################################################
	
	//---------FUNCTION TO BLOW WIND-----------------------------------------------------
	
	public void blowWind(int speed,int fromDirection, int toDirection)
	{
		
	}
	
	//###################################################################################
	
	//-------------FUNCTION TO MOVE THE NODE IN THE DIRECTION POINTED BY ITS NOSE---------
	
	
	//FUNCTION TO MOVE THE NODES TOWARDS THE BASE STATION
	
	
	public void setSlope(final Location centerLoc,final Location pointingLoc, VSlope slope)
	{
		//base.moveDelay=delay; // CAN BE USED AT OTHER PLACES
					
					float x1=(float)pointingLoc.getXLoc();
					float y1=(float)pointingLoc.getYLoc();
					
					float x2=centerLoc.floatX;
					float y2=centerLoc.floatY;
					
					int stepCount;
					
			float dx=(float)Math.sqrt((x2-x1)*(x2-x1));
			float dy=(float)Math.sqrt((y2-y1)*(y2-y1));
			
			slope.angle=(float)Math.atan(dy/dx);
			
			//System.out.println("slope angle : " +Math.toDegrees(slope.angle));
			
			if(dx>dy)
			{
				slope.stepCount=(int)dx;
			}
			else
			{
				slope.stepCount=(int)dy;
			}
			
			if(x2>=x1 && y2>=y1)
			{
				if(dy==0 && dx !=0)
				{
					slope.xSlope=-1;
					slope.ySlope=0;
					
					slope.pXSlope=0;
					slope.pYSlope=1;
				}
				
				if(dx==0 && dy!=0)
				{
					slope.ySlope=-1;
					slope.xSlope=0;
					
					slope.pYSlope=0;
					slope.xSlope=-1;
				}
				
				if(dx==0 && dy==0)
				{
					slope.xSlope=0;
					slope.ySlope=0;
					
					slope.pXSlope=0;
					slope.pYSlope=0;
				}
				if(dx>dy)
				{
					slope.xSlope=-(dx/dx);
					slope.ySlope=-(dy/dx);
					
					slope.pXSlope=-(dy/dx);
					slope.pYSlope=(dx/dx);
					
					//System.exit(0);
					
				}
				
				else
				{
					slope.ySlope=-(dy/dy);
					slope.xSlope=-(dx/dy);
					
					slope.pYSlope=(dx/dy);
					slope.pXSlope=-(dy/dy);
					
					//System.exit(0);
				}
				
				//--------------------------------------------
				
				//--------------------------------------------
			}
			
			if(x2>=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					slope.xSlope=-1;
					slope.ySlope=0;
					
					slope.pXSlope=0;
					slope.pYSlope=1;
				}
				
				if(dx==0 && dy!=0)
				{
					slope.ySlope=1;
					slope.xSlope=0;
					
					slope.pYSlope=0;
					slope.pXSlope=1;
				}
				
				if(dx==0 && dy==0)
				{
					slope.xSlope=0;
					slope.ySlope=0;
					
					slope.pXSlope=0;
					slope.pYSlope=0;
				}
				if(dx>dy)
				{
					slope.xSlope=-(dx/dx);
					slope.ySlope=(dy/dx);
					
					slope.pXSlope=(dy/dx);
					slope.pYSlope=dx/dx;
					
					//System.exit(0);
				}
				
				else
				{
					slope.ySlope=(dy/dy);
					slope.xSlope=-(dx/dy);
					
					slope.pYSlope=(dx/dy);
					slope.pXSlope=(dy/dy);
					
				//	System.exit(0);
				}		
			}
			
			if(x2<=x1 && y2<=y1)
			{
				if(dy==0 && dx !=0)
				{
					slope.xSlope=1;
					slope.ySlope=0;
					
					slope.pXSlope=0;
					slope.pYSlope=-1;
				}
				
				if(dx==0 && dy!=0)
				{
					slope.ySlope=1;
					slope.xSlope=0;
					
					slope.pXSlope=1;
					slope.pYSlope=0;
				}
				
				if(dx==0 && dy==0)
				{
					slope.xSlope=0;
					slope.ySlope=0;
					
					slope.pXSlope=0;
					slope.pYSlope=0;
				}
				if(dx>dy)
				{
					slope.xSlope=(dx/dx);
					slope.ySlope=(dy/dx);
					
					slope.pXSlope=(dy/dx);
					slope.pYSlope=-(dx/dx);
					
					//System.exit(0);
				}
				
				else
				{
					slope.ySlope=(dy/dy);
					slope.xSlope=(dx/dy);
					
					slope.pYSlope=-(dx/dy);
					slope.pXSlope=(dy/dy);
					
					//System.exit(0);
				}		
			}
			
			if(x2<x1 && y2>y1)
			{
				if(dy==0 && dx !=0)
				{
					slope.xSlope=1;
					slope.ySlope=0;
					
					slope.pXSlope=0;
					slope.pYSlope=-1;
				}
				
				if(dx==0 && dy!=0)
				{
					slope.ySlope=-1;
					slope.xSlope=0;
					
					slope.ySlope=0;
					slope.pXSlope=-1;
				}
				
				if(dx==0 && dy==0)
				{
					slope.xSlope=0;
					slope.ySlope=0;
					
					slope.pXSlope=0;
					slope.pYSlope=0;
				}
				if(dx>dy)
				{
					slope.xSlope=(dx/dx);
					slope.ySlope=-(dy/dx);
					
					slope.pXSlope=-(dy/dx);
					slope.pYSlope=-(dx/dx);
					
					//System.exit(0);
				}
				
				else
				{
					slope.ySlope=-(dy/dy);
					slope.xSlope=(dx/dy);
					
					slope.pYSlope=-(dx/dy);
					slope.pXSlope=-(dy/dy);
					
					//System.exit(0);
				}	
			}
			
/*			slope.pXSlope=(slope.xSlope);
			slope.pYSlope=(1/slope.ySlope);
			
			if(slope.pXSlope>slope.pYSlope)
			{
				slope.pXSlope=1;
				slope.pYSlope=-(slope.pYSlope/slope.pXSlope);				
			}
			if(slope.pYSlope>slope.pXSlope)
			{
				slope.pXSlope=slope.pXSlope/slope.pYSlope;
				slope.pYSlope=-1;
			}*/
	}
	
	//---------------------------------------------------------------
	
	//---------------------------------------------------------------
	float oldRefDiff=100,newRefDiff=100;
	float oldTotalDiff=0,newTotalDiff=100;
	float totalDiff=0;
							
		public void moveAhead()
		{
			Runnable r=new Runnable()
			{		
				VFigure figure=base.foregroundFigureList.get(0);
				public void run()
				{
					//for(;;)
					while(base.currentHeight>0)
					{	
						base.currentHeight=base.currentHeight-(Math.sin(Math.toRadians(base.angleOfGlide)));
						if (base.currentHeight<=0)
						{
							base.grounded=true;
						}
						
						setSlope(base.candidateLoc,base.candidateNode.getLoc(),base.pathSlope);
						
						System.out.println("path slope angle :" + Math.toDegrees(base.pathSlope.angle));
						
						float x2=base.gliderCenterLoc.floatX;
						float y2=base.gliderCenterLoc.floatY;
						
						x2=x2+(float)(base.glideSlope.xSlope)+base.windDxFloat;
						y2=y2+(float)(base.glideSlope.ySlope)+base.windDyFloat;
						
						//	node.setBounds((int)x2, (int)y2,base.nodeSize,base.nodeSize);

						
						//--------------------------------------------------------------
						
						//base.windStepCounterFloatX +=base.windDxFloat;
						//base.windStepCounterFloatX +=base.windDyFloat;
						
						//base.windStepX=(int)base.windStepCounterFloatX;
						//base.windStepY=(int)base.windStepCounterFloatY;
						
						//base.windStepCounterFloatX =base.windStepCounterFloatX-base.windStepX;
						//base.windStepCounterFloatX =base.windStepCounterFloatY-base.windStepY;
						
						//x2=x2+base;
						//y2=y2+(float)(base.glideSlope.ySlope);
										
						//--------------------------------------------------------------
						
						base.gliderCenterLoc.floatX=x2;
						base.gliderCenterLoc.floatY=y2;
						
						
						ArrayList<LineSegment>lineList=figure.lineList;		
						
							for(int j=0;j<lineList.size();j++)
							{
								LineSegment ls=lineList.get(j);
								ls.x1=x2;
								ls.y1=y2;
							}
		
							followPath();
							
/*						if(base.virtualPathUpdateEnabled && base.nodeTrackList.size()%(int)(base.maxVirtualPathUpdateDist-1)==0)
						{
							updateVirtualPath();
						}*/

						if(base.virtualPathUpdateEnabled && base.pathUpdationCounter>=(int)(base.maxVirtualPathUpdateDist)+3)
						{
							base.testCounter++;
							base.pathUpdationCounter=0;
							base.alert.display(base.testCounter+"");
							updateVirtualPath();
						}
							
							VCoordinate tempCoordinate=new VCoordinate((int)base.gliderCenterLoc.floatX,(int)base.gliderCenterLoc.floatY,(int)base.currentHeight,2,Color.RED,base);
							base.nodeTrackList.add(tempCoordinate);
							base.pathUpdationCounter=base.pathUpdationCounter+1;							
							base.candidateNode.setBounds((int)base.gliderCenterLoc.floatX-2,(int)base.gliderCenterLoc.floatY-2,4,4);
						
						updateAADSNodeLocation(base.candidateNode);
						
						try
						{
							int milliDelay=(int)base.glidingStepDelay;
							int nanoDelay=(int)(1000000*(base.glidingStepDelay-(int)base.glidingStepDelay));
							//base.alert.display("gliding delay :"+ base.glidingDelay +"  milli delay:  "+milliDelay +"  nano delay:  "+nanoDelay);
							Thread.sleep(milliDelay,nanoDelay);
						}
						catch(Exception e){System.out.println(e);}
						
					}
				}
			};		
			new Thread(r).start();
		}
		
		
		//float furthestDistOfCircle=0;
		//boolean isWindy=false;
		public void followPath()
		{
			int currentDist;

						boolean temp=checkForPath(base.candidateNode.getX(),base.candidateNode.getY(),base.candidateLoc.getXLoc(), base.candidateLoc.getYLoc(),(int) base.px1,(int) base.py1);
						
						if(temp!=base.inRegion)
						{
							base.pathUpdationCounter=0;
							base.oldMark=base.newMark;
							base.newMark=base.candidateNode.getLoc();
							
							base.oldDist=base.newDist;
							base.newDist=getDist(base.candidateLoc,base.newMark);
																																							
							currentDist=(int)getDist(base.candidateLoc,base.candidateNode.getLoc());
							
							if(base.newDist<base.oldDist)			
							{
								if(currentDist<45)
								{
									base.intGraphSig1=true;
								}
								
								if(base.radiusReductionEnabled && currentDist<base.gadsEffectiveDist)
							//	if(currentDist<base.gadsEffectiveDist)
								{
										base.turnWithReducedRadius++;
										base.turningRadius=base.reducedTurningRadius;
										base.maxVirtualPathUpdateDist=(float)(Math.PI*base.turningRadius);				
										base.gliderTotalStepsForARotation=(float)(Math.PI*base.turningRadius);
										base.gliderRotationAnglePerStep=(float)Math.PI/base.gliderTotalStepsForARotation;						
								}
								else
								{				
									    base.turningRadius=base.tempTurningRadius;
									    base.maxVirtualPathUpdateDist=(float)(Math.PI*base.turningRadius);
									    base.gliderTotalStepsForARotation=(float)(Math.PI*base.turningRadius);
										base.gliderRotationAnglePerStep=(float)Math.PI/base.gliderTotalStepsForARotation;
										base.turnWithNmlRadius++;
								}
								
								base.turnCounter=base.turnCounter+1;
							}

							//base.inRegion=temp;
							base.inRegion=checkForPath(base.candidateNode.getX(),base.candidateNode.getY(),base.candidateLoc.getXLoc(), base.candidateLoc.getYLoc(),(int) base.px1,(int) base.py1);
							
							//if(base.intGraphSig1==true)
							if(base.currentHeight<=100)
							{
								int distFromDL=(int)currentDist;
								base.intGraphArray1[distFromDL]=base.intGraphArray1[distFromDL]+1;
							}
							takeTurn(base.turnCounter%2);
						}
						else
						{	
							currentDist=(int)getDist(base.candidateLoc,base.candidateNode.getLoc());
							//if(base.intGraphSig1==true)
							if(base.currentHeight<=100)
							{
								int distFromDL=(int)currentDist;
								base.intGraphArray1[distFromDL]=base.intGraphArray1[distFromDL]+1;
							}														
							takeTurn(base.turnCounter%2);						
						}
						try{Thread.sleep(100);}catch(Exception e){}						
		}
		
        //-------------------------------------------------------------------------

		//**********FUNCTION TO UPDATE THE LOCATION OF NODE***********************************
		public void updateAADSNodeLocation(VLabel node)
		{
			Location tempLoc=base.candidateLoc;
			if(base.masterBSList.size()>=4)
			{
				try
				{
					base.candidateNode.distFromBS0=getDist(base.masterBSList.get(0),base.candidateNode);
					base.candidateNode.distFromBS1=getDist(base.masterBSList.get(1),base.candidateNode);
					base.candidateNode.distFromBS2=getDist(base.masterBSList.get(2),base.candidateNode);
					base.candidateNode.distFromBS3=getDist(base.masterBSList.get(3),base.candidateNode);					
					if(base.referenceBS!=null)
					{
						base.candidateNode.distFromRef=getDist(base.referenceBS,base.candidateNode);
					}
				}catch(Exception e){}
			}
			try
			{
				
				base.candidateNode.diff0=Math.abs(base.candidateNode.distFromBS0-tempLoc.distFromBSList.get(0));
				base.candidateNode.diff1=Math.abs(base.candidateNode.distFromBS1-tempLoc.distFromBSList.get(1));
				base.candidateNode.diff2=Math.abs(base.candidateNode.distFromBS2-tempLoc.distFromBSList.get(2));
				base.candidateNode.diff3=Math.abs(base.candidateNode.distFromBS3-tempLoc.distFromBSList.get(3));
				//oldTotalDiff=totalDiff;
				totalDiff=base.candidateNode.diff0+base.candidateNode.diff1+base.candidateNode.diff2+base.candidateNode.diff3;
				//newTotalDiff=totalDiff;
				if(base.referenceBS!=null)
				{
					oldRefDiff=base.candidateNode.diffRef;
					base.candidateNode.diffRef=Math.abs(base.candidateNode.distFromRef-getDist(base.referenceBS,tempLoc));
					newRefDiff=base.candidateNode.diffRef;
				}
			}catch(Exception e){}
		}	
		
		
	//------------------------------------------------------------------------------------	
	//************FUNCTION TO ROTATE CANNON************************************************
/*		public void rotateCannon(int step)
		{
			VFigure f=base.foregroundFigureList.get(0);
		//	LineSegment line
			base.s0=(base.s0-step)%60;
			base.s1=(base.s1-step)%60;
			base.s2=(base.s2-step)%60;
		}*/
	//------------------------------------------------------------------------------------
		
	//************FUNCTIONS TO TURN LEFT AND RIGHT*****************************************
	public void takeTurn(int direction)
	{
		VFigure figure=base.foregroundFigureList.get(0);
		if(direction==base.LEFTTURN)
		{
			//base.s0=(base.s0-step)%60;
			//base.s1=(base.s1-step)%60;
			//base.s2=(base.s2-step)%60;
			//VFigure figure=base.foregroundFigureList.get(0);

				for(int i=0;i<figure.lineList.size();i++)
				{
					figure.lineList.get(i).angle=(figure.lineList.get(i).angle+base.gliderRotationAnglePerStep)%(2*Math.PI);
				}								
		}
		
		if (direction==base.RIGHTTURN)
		{
			//base.s0=(base.s0+step)%60;
			//base.s1=(base.s1+step)%60;
			//base.s2=(base.s2+step)%60;
			
			for(int i=0;i<figure.lineList.size();i++)
			{
				//figure.lineList.get(i).angle=(figure.lineList.get(i).angle-(Math.PI/10))%(2*Math.PI);
				figure.lineList.get(i).angle=(figure.lineList.get(i).angle-base.gliderRotationAnglePerStep)%(2*Math.PI);
			}	
		}
	}
		//------------------------------------------------------------------------------------
	
/*	//**********FUNCTIONS TO BLOW WIND********************************************************
	public void blowWind()
	{
		Runnable r=new Runnable()
		{
			
			public void run()
			{
					//.....................................................................
							
								while(!base.grounded)								
								{		
									float x2=base.gliderCenterLoc.floatX;
									float y2=base.gliderCenterLoc.floatY;
									
									x2=x2+base.windX;
									y2=y2+base.windY;
									//y2=y2+1;
									//????????
									//	node.setBounds((int)x2, (int)y2,base.nodeSize,base.nodeSize);
									base.gliderCenterLoc.floatX=x2;
									base.gliderCenterLoc.floatY=y2;

									
									if(base.nodeTrackList.size()==100)
									{

										
										try{Thread.sleep(5000);}catch(Exception e){}
									}
									
									if(base.nodeTrackList.size()%100==0)
									{
										VCoordinate tempCoordinate=new VCoordinate((int)base.gliderCenterLoc.floatX,(int)base.gliderCenterLoc.floatY,1,Color.BLUE,base);
										base.nodeTrackList.add(tempCoordinate);	
										base.s0=(base.s0+1)%60;
							        	base.s1=(base.s1+1)%60;
							        	base.s2=(base.s2+1)%60;
									}
									else
									{	
										VCoordinate tempCoordinate=new VCoordinate((int)base.gliderCenterLoc.floatX,(int)base.gliderCenterLoc.floatY,2,Color.RED,base);
										base.nodeTrackList.add(tempCoordinate);
									}
									base.candidateNode.setBounds((int)base.gliderCenterLoc.floatX-2,(int)base.gliderCenterLoc.floatY-2,4,4);
									
									updateAADSNodeLocation(base.candidateNode);
									try
									{
										int milliDelay=(int)base.windDelay;
										int nanoDelay=(int)(1000000*(base.windDelay-(int)base.windDelay));
										//base.alert.display(" milli delay:  "+milliDelay +"  nano delay:  "+nanoDelay);
										Thread.sleep(milliDelay,nanoDelay);
									}
									catch(Exception e){System.out.println(e);}
								}
					
					//.....................................................................
				//	try{Thread.sleep(1000);}catch(Exception e){}										
				//}
			}
		};
		new Thread(r).start();
	}*/
	
//----------------------------------------------------------------------------------------
		void startAADSController()
		{
		Runnable r=new Runnable()
		{
			public void run()
			{
				try
				{
					int delay=5000;
					int turn=base.LEFTTURN;
					int step=1;
					//------------------------------------------------------------
					Thread.sleep(delay);
					//Thread.sleep((int)(base.glidingDelay*100.0f));
					int startX=base.startPoint.x;
					int startY=base.startPoint.y;
					Location currentLoc=base.candidateNode.getLoc();
					int currentX=currentLoc.x;
					int currentY=currentLoc.y;
					
					int distX=currentX-startX;
					int distY=currentY-startY;
					
					base.alert.display("dist x :"+ distX + " dist y :"+distY);
					
					//Location currentLoc=new Location((int)base.gliderCenterLoc.floatX,(int)base.gliderCenterLoc.floatY,base);
				   // Location currentLoc=base.candidateNode.getLoc();
/*					int dx=currentLoc.x-base.startPoint.x;
					int dy=currentLoc.y-base.startPoint.y;
					
					int dx2=currentLoc.x-base.candidateLoc.x;
					int dy2=currentLoc.y-base.candidateLoc.y;*/
					
					Location checkPoint=new Location(currentLoc.x,currentLoc.y,base);
					base.checkPointList.add(checkPoint);
					
					int daWidth=Integer.parseInt(base.dxTf.getText());
					int daHeight=Integer.parseInt(base.dyTf.getText());
					base.centerPanel.clearAreaOfInterest(0,0,daHeight,daWidth);
					
					if(currentLoc.x < base.candidateLoc.x  && currentLoc.y > base.candidateLoc.y)
					{
						
						//	base.alert.display("desired location is in 1st quadrant");						
						base.areaOfInterest.startX=currentLoc.x;
						base.areaOfInterest.startY=base.areaOfInterest.startY;
						base.areaOfInterest.endX=base.areaOfInterest.endX;
						base.areaOfInterest.endY=currentLoc.y;
					}
					
					if(currentLoc.x > base.candidateLoc.x  && currentLoc.y > base.candidateLoc.y)
					{
						//	base.alert.display("desired location is in 2nd quadrant");						
						base.areaOfInterest.startX=base.areaOfInterest.startX;
						base.areaOfInterest.startY=base.areaOfInterest.startY;
						base.areaOfInterest.endX=currentLoc.x;
						base.areaOfInterest.endY=currentLoc.y;												
					}
					
					
					if(currentLoc.x > base.candidateLoc.x  && currentLoc.y < base.candidateLoc.y)
					{
						//	base.alert.display("desired location is in 3rd quadrant");
						
						base.areaOfInterest.startX=base.areaOfInterest.startX;
						base.areaOfInterest.startY=currentLoc.y;
						base.areaOfInterest.endX=currentLoc.x;
						base.areaOfInterest.endY=base.areaOfInterest.endY;
						
					}
					
					if(currentLoc.x < base.candidateLoc.x  && currentLoc.y < base.candidateLoc.y)
					{
						//	base.alert.display("desired location is in 4th quadrant");
						
						base.areaOfInterest.startX=currentLoc.x;
						base.areaOfInterest.startY=currentLoc.y;
						base.areaOfInterest.endX=base.areaOfInterest.endX;
						base.areaOfInterest.endY=base.areaOfInterest.endY;
					}
					
					base.centerPanel.updateAreaOfInterest(base.areaOfInterest.startX,base.areaOfInterest.startY, base.areaOfInterest.endX, base.areaOfInterest.endY);
					
					//	------------------------------------------------------------
					//while(true)
					//{
						Thread.sleep(100);
						
						//System.out.println(" sx : "+base.pathSlope.xSlope);
						//System.out.println(" sy : "+base.pathSlope.ySlope);
						
						//System.exit(0);
						
					//}
						
				}catch(Exception e){}
			}
		};
		new Thread(r).start();
	}
/*	void startAADSController()
	{
		Runnable r=new Runnable()
		{
			public void run()
			{
				try
				{
					int delay=5000;
					int turn=base.LEFTTURN;
					int step=1;
					while(true)
					{
						Thread.sleep(delay);
											Thread.sleep((int)(base.glidingDelay*100.0f));
						int startX=base.startPoint.x;
						int startY=base.startPoint.y;
						Location currentLoc=base.candidateNode.getLoc();
						int currentX=currentLoc.x;
						int currentY=currentLoc.y;
					
						int distX=currentX-startX;
						int distY=currentY-startY;
					
						base.alert.display("dist x :"+ distX + " dist y :"+distY);
						
						//Location currentLoc=new Location((int)base.gliderCenterLoc.floatX,(int)base.gliderCenterLoc.floatY,base);
						Location currentLoc=base.candidateNode.getLoc();
						int dx=currentLoc.x-base.startPoint.x;
						int dy=currentLoc.y-base.startPoint.y;
						
						int dx2=currentLoc.x-base.candidateLoc.x;
						int dy2=currentLoc.y-base.candidateLoc.y;
						
						Location checkPoint=new Location(currentLoc.x,currentLoc.y,base);
						base.checkPointList.add(checkPoint);
						
						int daWidth=Integer.parseInt(base.dxTf.getText());
						int daHeight=Integer.parseInt(base.dyTf.getText());
						base.centerPanel.clearAreaOfInterest(0,0,daHeight,daWidth);
						
						if(currentLoc.x < base.candidateLoc.x  && currentLoc.y > base.candidateLoc.y)
						{
							
							//base.alert.display("desired location is in 1st quadrant");						
							base.areaOfInterest.startX=currentLoc.x;
							base.areaOfInterest.startY=base.areaOfInterest.startY;
							base.areaOfInterest.endX=base.areaOfInterest.endX;
							base.areaOfInterest.endY=currentLoc.y;
						}
						
						if(currentLoc.x > base.candidateLoc.x  && currentLoc.y > base.candidateLoc.y)
						{
							//base.alert.display("desired location is in 2nd quadrant");						
							base.areaOfInterest.startX=base.areaOfInterest.startX;
							base.areaOfInterest.startY=base.areaOfInterest.startY;
							base.areaOfInterest.endX=currentLoc.x;
							base.areaOfInterest.endY=currentLoc.y;												
						}
											
						
						if(currentLoc.x > base.candidateLoc.x  && currentLoc.y < base.candidateLoc.y)
						{
							//base.alert.display("desired location is in 3rd quadrant");
							
							base.areaOfInterest.startX=base.areaOfInterest.startX;
							base.areaOfInterest.startY=currentLoc.y;
							base.areaOfInterest.endX=currentLoc.x;
							base.areaOfInterest.endY=base.areaOfInterest.endY;
							
						}
						
						if(currentLoc.x < base.candidateLoc.x  && currentLoc.y < base.candidateLoc.y)
						{
							//base.alert.display("desired location is in 4th quadrant");
							
							base.areaOfInterest.startX=currentLoc.x;
							base.areaOfInterest.startY=currentLoc.y;
							base.areaOfInterest.endX=base.areaOfInterest.endX;
							base.areaOfInterest.endY=base.areaOfInterest.endY;
						}
						
						base.centerPanel.updateAreaOfInterest(base.areaOfInterest.startX,base.areaOfInterest.startY, base.areaOfInterest.endX, base.areaOfInterest.endY);
						
						base.currentSlope=(float)(currentLoc.y-base.turningPoint.y)/(float)(currentLoc.x-base.turningPoint.x);
						base.desiredSlope=(float)(base.candidateLoc.y-currentLoc.y)/(float)(base.candidateLoc.x-currentLoc.y);
						
						
						
						int currentIntSlope=(int)(base.currentSlope*100.0);
						int desiredIntSlope=(int)(base.desiredSlope*100.0);
						
						base.newSlopeDiff=desiredIntSlope-currentIntSlope;
						
						base.alert.display("turn : "+ turn +" current slope: "+currentIntSlope +" ; desired slope: "+desiredIntSlope);
						
						System.out.print(" desired slope : "+ desiredIntSlope);
						
						System.out.print(" current slope: "+ currentIntSlope);
						
						System.out.print(" osd : "+base.oldSlopeDiff);
						
						System.out.print(" nsd: "+ base.newSlopeDiff);
						
						System.out.println();
						
						if(base.newSlopeDiff<base.oldSlopeDiff)
						{
							//turn=(turn+1)%2;
							takeTurn(turn, step);
						}
						
						else if(base.newSlopeDiff>base.oldSlopeDiff)						
						{
							//System.exit(0);
							turn=(turn+1)%2;
							takeTurn(turn, step);
							
							base.glidingDelay=100000;
							base.windDelay=100000;	
							turn=0;
							step=0;
						}
						
						delay=500;
						
						base.testPoints.add(checkPoint);
						base.turningPoint=checkPoint;
						base.oldSlopeDiff=base.newSlopeDiff;
						
						
						base.glidingDelay=100000;
						base.windDelay=100000;
					}
						
				}catch(Exception e){}
			}
		};
		new Thread(r).start();
	}*/

		//------------FUNCTION TO CHECK THE PATH--------------------------------------------		
		boolean checkForPath(int xLoc,int yLoc,int x1,int y1, int x2,int y2)
		{
			int a=-(int)(y2-y1);
			int b=(int)(x2-x1);
			int c = -(int)(a * x1 + b * y1);
			int d = a * xLoc + b * yLoc + c;
			if(d>0){return false;}
			
			return true;
		}
		
		//-----------------------------------------------------------------------------------
		
/*	//------------------FUNCTION TO CHECK THE TUNNEL-----------------------------------
		boolean checkForTunnel(int xLoc,int yLoc)
		{
			if(tunnelFun(xLoc,yLoc,(int)base.tx1,(int)base.ty1,(int)base.tx2,(int)base.ty2))
			{
				if(tunnelFun(xLoc,yLoc,(int)base.tx2,(int)base.ty2,(int)base.tx3,(int)base.ty3))
				{
					if(tunnelFun(xLoc,yLoc,(int)base.tx3,(int)base.ty3,(int)base.tx4,(int)base.ty4))
					{
						if(tunnelFun(xLoc,yLoc,(int)base.tx4,(int)base.ty4,(int)base.tx1,(int)base.ty1))
						{
							return true;
						}
						else
						{
							return false;
						}
					}
					else
					{
						return false;
					}
				}
			}
			else
			{
				return false;
			}
			return false;

		}
		
		boolean tunnelFun(int xLoc,int yLoc,int x1,int y1, int x2,int y2)
		{
			int a=-(int)(y2-y1);
			int b=(int)(x2-x1);
			int c = -(int)(a * x1 + b * y1);
			int d = a * xLoc + b * yLoc + c;
			if(d>0){return false;}
			
			return true;
		}	
		
	//---------------------------------------------------------------------------------
*/	//-----------------FUNCTION TO FREEZE THE TUNNEL-----------------------------------
	public void freezePath()
	{
		base.pathFreezed=true;
		base.pathUpdater=false;
	}
	
	public void unFreezePath()
	{
		base.pathFreezed=false;
		base.pathUpdater=true;
		
	}
	
	public void updatePath(final int delay)
	{
		Runnable r=new Runnable()
		{
			public void run()
			{
				for(;;)
				{
					unFreezePath();
					//System.exit(0);
					try{Thread.sleep(100);}catch(Exception e){}
					freezePath();
					try{Thread.sleep(delay);}catch(Exception e){}
				}
			}
		};
		new Thread(r).start();
	}
	
	public void updateVirtualPath()
	{
		try
		{
		//base.px1=(base.gliderCenterLoc.floatX+base.gliderAdvLoc.x)/2;
		//base.py1=(base.gliderCenterLoc.floatY+base.gliderAdvLoc.y)/2;	
			
			base.px1=base.gliderCenterLoc.floatX;
			base.py1=base.gliderCenterLoc.floatY;
			
				for(int i=100;i<600;i++)
				{
					for(int j=100;j<600;j++)
					{
						if(checkForPath(i,j,base.candidateLoc.getXLoc(), base.candidateLoc.getYLoc(),(int) base.px1,(int) base.py1))
						{
							base.centerPanel.coordinateMat[j][i].color=Color.RED;
						}
						else
						{
							base.centerPanel.coordinateMat[j][i].color=Color.blue;
						}
					}
				}								
			
			
		}catch(Exception ne){}
	}
	//---------------------------------------------------------------------------------
	
	
	//-------------------FUNCTION TO MOVE THE FIGURE----------------------------------
	public void moveOnPath(final float speed)
	{

		Runnable r=new Runnable()
		  {
			public void run()
			{
				VFigure figure=base.foregroundFigureList.get(0);
				int i=0;
				float dist=999;
				//int delay=(int)(200.0/(speed/10.0));
				int delay=(int)(100.0/((speed*base.scale)/10.0));
				//System.out.println("delay :"+delay);
				//System.exit(0);
				if(delay<10)
				{
					System.out.println("overspeed");
					delay=10;
				}
				
				Location startLoc=base.pathList.get(i);
				Location endLoc=base.pathList.get(i+1);
				
				//System.exit(0);
				moveOnPath(figure,startLoc,endLoc, delay);
/*				for(;;)
				{
					for(int i=0;i<figure.lineList.size();i++)
					{
						figure.lineList.get(i).x1=figure.lineList.get(i).x1+1;				
					}
					try{Thread.sleep(delay);}
					catch(Exception e){}
				}*/
			}
		};
		new Thread(r).start();
	}
	//--------------------------------------------------------------------------------
	
	public void rotate()
	{
		Runnable r=new Runnable()
		{
			public void run()
			{
				VFigure figure=base.foregroundFigureList.get(0);
				for(;;)
				{
					for(int i=0;i<figure.lineList.size();i++)
					{
						figure.lineList.get(i).angle=(figure.lineList.get(i).angle+(Math.PI/10))%(2*Math.PI);
					}					
					try{Thread.sleep(1);}catch(Exception e){}
				}
			}
		};
		new Thread(r).start();
	}
	
	
	//----------------FUNCTION TO LOOAD THE FIGURES---------------------------------------
	
	public void startConventionalDeployer()
	{
		//Runnable r=new Runnable()
		//{
			Location startPoint=new Location(0,0,base);
			Random rand=new Random();
			LineSegment lineSegment=base.foregroundFigureList.get(0).lineList.get(0);
			int randVal=0;
			//public void run()
			//{
				if(base.pathList.size()>0)
					startPoint=base.pathList.get(0);
				
				for(;;)
				{
					try{Thread.sleep((int)(1000/base.droppingRate));}catch(Exception e){}
					if(base.conventionalDeployerFlag)
					{
						randVal=rand.nextInt(8);
						Location loc=new Location(startPoint.x+(int)lineSegment.x1,startPoint.y+(int)lineSegment.y1,base);
						int radius=(int)((base.deploymentError/100)*base.droppingHeight);
						radius=(int)(radius*base.scale);
						Location randLoc=getRandomLocInRadius(loc,radius);
						base.deploymentLocList.add(randLoc);
					//try{Thread.sleep(1000);}catch(Exception e){}
					}
				}
			//}
		//};
		
		//new Thread(r).start();
	}
	
	public void aadsDeployment()
	{
		Random rand=new Random();
		int randValx=0;
		int randValy=0;
		int quad;
		for(int i=0;i<base.desiredLocList.size();i++)
		{
			quad=1;
			Location tempLoc=base.desiredLocList.get(i);
			int x=tempLoc.x;
			int y=tempLoc.y; 			
			quad=quad+rand.nextInt(4);
			int oldX=x;
			int oldY=y;			
			
			System.out.println("max deployment error :"+ base.maxDeploymentError);
			System.out.println("scale:"+ base.scale);
			//System.out.println("")
			//System.exit(0);
			
			
			//randValx=rand.nextInt((int)base.turningRadius*2);
			//randValy=rand.nextInt((int)base.turningRadius*2);
			
			randValx=rand.nextInt((int)base.maxDeploymentError);
			randValy=rand.nextInt((int)base.maxDeploymentError);
			
			
			
			
			
			System.out.println(base.turningRadius);
			//System.exit(0);
			
			if(quad==1)
			{
				x=x-randValx;
				y=y-randValy;
			}
			if(quad==2)
			{
				x=x-randValx;
				y=y+randValy;
			}
			if(quad==3)
			{
				x=x+randValx;
				y=y-randValy;
			}
			if(quad==4)
			{
				x=x+randValx;
				y=y+randValy;
			}
			
			//System.out.println("(X:"+x+",Y:"+y+")");
			VLabel node=base.masterNodeList.get(i);
			node.setBounds(x,y,10,10);
			node.xPos=x;
			node.yPos=y;	
			
			float tempDist=getDist(oldX, oldY, x, y);
			base.totalError=base.totalError+tempDist;
						
		}
	}
	
	public void startCentrifugalDeployer()
	{
		//Runnable r=new Runnable()
		//{
			Location startPoint=new Location(0,0,base);
			Random rand=new Random();
			LineSegment lineSegment=base.foregroundFigureList.get(0).lineList.get(0);
			int randVal=0;
			double angle=0;
			double angularVelocity=(base.rpm/60)*2*Math.PI;
			double hDist=0.0;
			//public void run()
			//{
				if(base.pathList.size()>0)
					startPoint=base.pathList.get(0);
				
				for(;;)
				{
					//System.out.println("the name of author is vikrant sharma");
					try{Thread.sleep((int)(1000/base.droppingRate));}catch(Exception e){}
					if(base.droppingFlag)
					{
						//System.out.println("the name of author is vikrant sharma");
						randVal=rand.nextInt(8);
						
						angle=base.foregroundFigureList.get(0).lineList.get(randVal).angle;
					//	hDist=(Math.sqrt(2*base.droppingHeight/9.8))*angularVelocity*base.foregroundFigureList.get(0).lineList.get(randVal).length;
						hDist=base.scatteringRangeList.get(randVal).horizontalDistWithAir;
						hDist=hDist*base.scale;
					
						int x1=(int)(startPoint.getXLoc()+base.foregroundFigureList.get(0).lineList.get(randVal).x1);
						int y1=(int)(startPoint.getYLoc()+base.foregroundFigureList.get(0).lineList.get(randVal).y1);
//						int x2=x1+(int)(100*Math.sin(angle));
//						int y2=y1+(int)(100*Math.cos(angle));
					
						int x2=x1+(int)(hDist*Math.sin(angle));
						int y2=y1+(int)(hDist*Math.cos(angle));
					
					
						//base.alert.display(""+randVal);
					
										
						//Location loc=new Location(startPoint.x+(int)lineSegment.x1,startPoint.y+(int)lineSegment.y1,base);
						Location loc=new Location(x2, y2, base);
						int radius=(int)((base.deploymentError/100)*base.droppingHeight);
						radius=(int)(radius*base.scale);
						Location randLoc=getRandomLocInRadius(loc,radius);
						base.deploymentLocList.add(randLoc);
					}
				}
			//}
		//};
		
		//new Thread(r).start();
	}
	
	
	
	public Location getRandomLocInRadius(Location loc,int radius)
	{
		Random rand=new Random();
		int quad=rand.nextInt(4);
		int randX=rand.nextInt(radius);
		int randY=rand.nextInt(radius);
		int x=loc.getXLoc();
		int y=loc.getYLoc();
		Location finalLoc=null;
		
		//base.alert.display(""+quad);
		switch(quad)
		{
			case 0:
				finalLoc=new Location(x+randX,y+randY,base);
			break;
		
			case 1:
				finalLoc=new Location(x-randX,y+randY,base);
			break;
		
			case 2:
				finalLoc=new Location(x-randX,y-randY,base);
			break;
		
			case 3:
				finalLoc=new Location(x+randX,y-randY,base);
			break;
		}
		return finalLoc;
	}
	
	
	void deployNodesExtended()
	{
		ArrayList<VLabel>nodeList=new ArrayList<VLabel>();
		for(int i=0;i<base.deploymentLocList.size();i++)
		{
			 nodeList.add(new VLabel(1,Color.BLUE,base));
			 nodeList.get(i).commRange=base.commRangePerNode;
			 nodeList.get(i).sensingRange=base.sensingRangePerNode;		
			 nodeList.get(i).defaultBufferSize=20;
		}
		
		base.centerPanel.nodeDeployer(1,base.deploymentLocList,nodeList);
		
		for(int j=0;j<base.masterNodeList.size();j++)
		{
			try
			{
				markCoverage(base.masterNodeList.get(j));
			}
			catch(Exception e){}
		}
	}
	
	//-----------------------FUNCTION TO GET TERMINAL VELOCITY---------------
	
	public double getTerminalVelocity(float height, float mass, float radius)
	{
		double a=Math.PI*radius*radius;
		float cd=0.5f;
		float p=1.225f;
		double terminalVelocity=Math.sqrt((2*mass*9.8)/(p*cd*a));
		//System.out.println("terminal velocity:"+terminalVelocity);
		return terminalVelocity;		
	}
	
	
	//-----------FUNCTION TO GET HORIZONTAL DISTANCE WITH AIR----------------
	
	public double getHorizontalDistWithAir(double height, double radius,double mass, double p, double cd,double length, double rpm)
	{
		counter=0;
		double vdist=0.0;
		double vvl=0;
		//double hvi2=velocity;
		double hdist=0;
		float step=0.001f;
		float upto=33.92f;
		double fdv=0.0;
		double A=Math.PI*radius*radius;
		double av=0.0;
		double M=mass;
		float g=9.8f;
		double fdh=0.0;
		double ah=0.0;
		
		double rps=rpm/60.0;
		double w=rps*2*Math.PI;
		double hvi2=w*length;
		
		//System.out.println(" W = " + w+ " horizontal velocity "+ hvi2);
		
		
		for(double t=0;t<upto;t=t+step)
		{	
			//System.out.println("t="+t);
		   //counter=counter+1;		   
		   fdv=(p*A*cd*vvl*vvl)/2;
		   av=fdv/M;   
		   vvl=vvl+(g-av)*step;
		   vdist=vdist+step*vvl;
		   //y2(counter)=height-vdist;
		   
		   
		   //??????????????????????????????????? 
		   //-----------------------------------------
		   
		   fdh=(p*A*cd*hvi2*hvi2)/2;
		   ah=fdh/M;
		   hvi2=hvi2-(ah*step);
		   hdist=hdist+(step*hvi2);
		   
		   if(vdist>=height)
		   {
			   break;
		   }
		   //x2(counter)=hdist; 	
		   //System.out.println(t+ "With air :"+hdist);
		}
		
		//System.out.println("With air :"+hdist);
		//System.exit(0);
		return hdist;
	}
	
	//-----------FUNCTION TO GET HORIZONTAL DISTANCE WITHOUT AIR-------------
	
	public double getHorizontalDistWithoutAir(double height, double length, double rpm)
	{
		double timeOfFlight=Math.sqrt((2*height)/9.8);
		double rps=rpm/60;
		double w=rps*2*Math.PI;
		double velocity=w*length;
		double hdist=velocity*timeOfFlight;
		System.out.println("without air :"+hdist);
		return hdist;		
	}
	
	//--------FUNCTION TO SET HORIZONTAL DISTANCE FOR PARTICULAR CANNON--------
	
	public void setScatteringRange(double rpm)
	{
		//System.exit(0);
/*		for(int i=0;i<base.foregroundFigureList.get(0).lineList.size();i++)
		{
			double length=base.foregroundFigureList.get(0).lineList.get(i).length/10.0;
			System.out.println("length : "+length);
			System.out.println("mass : "+mass);
			System.out.println("height :"+height);
			System.out.println("radius :"+radius);
			System.out.println("rpm :"+rpm);
			double distWithoutAir=getHorizontalDistWithoutAir(height,length,rpm);
			double distWithAir=getHorizontalDistWithAir(height,radius,mass,1.255,0.5,length,rpm);
			ScatteringRange sr=new ScatteringRange(distWithoutAir, distWithAir);
			base.scatteringRangeList.add(sr);
			
			try{Thread.sleep(10000);}catch(Exception e){}
		}
		System.out.println("scattering list size"+ base.scatteringRangeList.size());
		System.exit(0);*/
		
		double height=base.droppingHeight;
		//double height=1000;
		double radius=0.05;
		double mass=0.250;
		//double rpm=500.0; 
		
		
		double dwa1 =getHorizontalDistWithAir(height,radius,mass,1.255,0.5,0.5,rpm);
		double dwoa1=getHorizontalDistWithoutAir(height,0.5,rpm);
		ScatteringRange sr1=new ScatteringRange(dwoa1,dwa1);
		base.scatteringRangeList.add(sr1);
		
		System.out.println("-----------------------------------");
		
		double dwa2=getHorizontalDistWithAir(height,radius,mass,1.255,0.5,1,rpm);
		double dwoa2=getHorizontalDistWithoutAir(height,1,rpm);
		ScatteringRange sr2=new ScatteringRange(dwoa2,dwa2);
		base.scatteringRangeList.add(sr2);
		
		System.out.println("-----------------------------------");		
		
		double dwa3=getHorizontalDistWithAir(height,radius,mass,1.255,0.5,1.5,rpm);
		double dwoa3=getHorizontalDistWithoutAir(height,1.5,rpm);
		ScatteringRange sr3=new ScatteringRange(dwoa3,dwa3);
		base.scatteringRangeList.add(sr3);
		
		System.out.println("-----------------------------------");		
		
		double dwa4=getHorizontalDistWithAir(height,radius,mass,1.255,0.5,2,rpm);
		double dwoa4=getHorizontalDistWithoutAir(height,2,rpm);
		ScatteringRange sr4=new ScatteringRange(dwoa4,dwa4);
		base.scatteringRangeList.add(sr4);
		
		System.out.println("-----------------------------------");
		
		double dwa5=getHorizontalDistWithAir(height,radius,mass,1.255,0.5,2.5,rpm);
		double dwoa5=getHorizontalDistWithoutAir(height,2.5,rpm);
		ScatteringRange sr5=new ScatteringRange(dwoa5,dwa5);
		base.scatteringRangeList.add(sr5);
		
		System.out.println("-----------------------------------");
		
		double dwa6=getHorizontalDistWithAir(height,radius,mass,1.255,0.5,3,rpm);
		double dwoa6=getHorizontalDistWithoutAir(height,3,rpm);
		ScatteringRange sr6=new ScatteringRange(dwoa6,dwa6);
		base.scatteringRangeList.add(sr6);
		
		System.out.println("-----------------------------------");
		
		double dwa7=getHorizontalDistWithAir(height,radius,mass,1.255,0.5,3.5,rpm);
		double dwoa7=getHorizontalDistWithoutAir(height,3.5,rpm);
		ScatteringRange sr7=new ScatteringRange(dwoa7,dwa7);
		base.scatteringRangeList.add(sr7);
		
		System.out.println("-----------------------------------");
				
		double dwa8=getHorizontalDistWithAir(height,radius,mass,1.255,0.5,4,rpm);
		double dwoa8=getHorizontalDistWithoutAir(height,4,rpm);
		ScatteringRange sr8=new ScatteringRange(dwoa8,dwa8);
		base.scatteringRangeList.add(sr8);
		
		System.out.println("-----------------------------------");
		
		System.out.println("Scattering Range "+base.scatteringRangeList.size());			
		
	}
	
	//-------------------------------------------------------------------------
	
	//-----------------FUNCTION TO GET OPTIMAL NODE COUNT----------------------
	
	public int getOptimalNodeCount()
	{
		double x=Float.parseFloat(base.dxTf.getText());
		double y=Float.parseFloat(base.dyTf.getText());
		double area=(x/base.scale)*(y/base.scale);
		//System.out.println("area of deployment region :"+area);
		//System.exit
		
		JLabel deploymentAreaLb=new JLabel("Deployment area -----------------: "+area+"    sq. m.");
		base.varTestDialog.centerPanel.add(deploymentAreaLb);
		base.varTestDialog.centerPanel.revalidate();
		
		int nodeCount=(int)(Math.ceil(area/(((3*Math.sqrt(3))/2)*((base.sensingRangePerNode/base.scale)*(base.sensingRangePerNode/base.scale)))));
		//System.out.println("optimalNodeCount"+nodeCount);
		//System.exit(0);
		return nodeCount;		
	}
	//-------------------------------------------------------------------------
	
	
	public double getOptimalDeploymentRate(float pathLength, float extraPercentage)
	{
		double deploymentTime=pathLength/base.deploymentHelicopterSpeed;
		double deploymentRate=base.optimalNodeCount/deploymentTime;
		//System.out.println("path length:"+pathLength);
		//System.out.println("deployment helicopter speed:"+ base.deploymentHelicopterSpeed);
		//System.out.println("deployment time:"+deploymentTime);
		//System.out.println("deployment rate :"+deploymentRate);
		//System.exit(0);
		return deploymentRate;
	}

	
	//-----------------------------------------------------------------------
	
	//--------------------GADS FUNCTION FOR TIME LINE------------------------
	
	void setTimeLine()
	{
		long currentTime=System.currentTimeMillis();
		long newTimeStamp=0;
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			newTimeStamp=currentTime+10000;
			
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 0
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+15000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 1
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 2
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 3
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 4
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 5
			base.masterTimeLineList.add(newTimeStamp);
		
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 6
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 7
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 8
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 9
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 10
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 11
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 12
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 13
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 14
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 15
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+10000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 16
			base.masterTimeLineList.add(newTimeStamp);			
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 17
			base.masterTimeLineList.add(newTimeStamp);	
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 18
			base.masterTimeLineList.add(newTimeStamp);	
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 19
			base.masterTimeLineList.add(newTimeStamp);	
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 20
			base.masterTimeLineList.add(newTimeStamp);	
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 21
			base.masterTimeLineList.add(newTimeStamp);	
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 22
			base.masterTimeLineList.add(newTimeStamp);	
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 23
			base.masterTimeLineList.add(newTimeStamp);	
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 24
			base.masterTimeLineList.add(newTimeStamp);	
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 25
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 26
			base.masterTimeLineList.add(newTimeStamp);
			
			newTimeStamp=newTimeStamp+30000;
			base.masterNodeList.get(i).timeLine.add(newTimeStamp); // TimeStamp 27
			base.masterTimeLineList.add(newTimeStamp);
			
			base.masterNodeList.get(i).defaultColor=Color.BLACK;
		}
	}
	//----------------------------------------------------------------------
	
	//------------FUNCTION TO GET NEIGHBORING DLS---------------------------
	
	ArrayList<Location>getNeighboringDL(ArrayList<Location> locations)
	{
		for(int i=0;i<base.desiredLocList.size();i++)
		{
			
		}
		return null;
	}
	
	//----------------------------------------------------------------------
	
	//***************************FUNCTIONS*******************************************
	
	public void runAlgo(final VLabel sourceNode,final int timeToLive)
	{
		//timeToLive=Integer.parseInt(timeToLiveSp.getValue().toString());	
		Runnable r=new Runnable()
		{
				public void run()
				{	
					//dispose();
					

					
					//System.out.println("run button have been pressed");
					try
					{
						Packet packet=new Packet(base);
						packet.fromNode=sourceNode;
						packet.source=sourceNode;
						packet.destination=base.whoIsSink;
						packet.protocolType=base.activeImplementation;
						packet.sourceType=sourceNode.nodeType;
						packet.data=base.centerPanel.coordinateMat[sourceNode.getY()][sourceNode.getX()].temperature;
						packet.ttl=timeToLive;
						
						if(packet.protocolType==base.DIR_DIFFUSION)
						{
							//packet.pathStack=sourceNode.gradientStack;
							packet.pathStack.pop();
						}
						
//						base.whoIsSource.buffer.add(packet);
						
						sourceNode.buffer.add(packet);
						
						//algo.flooding();
					}
					catch(NullPointerException e)
					{
						e.printStackTrace();
						MsgDialog md=new MsgDialog("source/sink not selected",base);
						md.setSize(300,150);
						md.setVisible(true);
						System.out.println("null pointer exception have been caught");
					}					
				}			
		};
		new Thread(r).start();
	}
	
	//--------FUNCTION TO RESET THE SIZE OF NODES----------------------------
	public void resetNodes()
	{
		try
	{
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			base.masterNodeList.get(i).setSize(6,6);
		}
	}catch(Exception e){}
	}
	//-----------------------------------------------------------------------
	
	/*//-------FUNCTION TO FLOOD PACKET IN PARTICULAR ANGLE------------------
	public void angularFloodingForGradient( final VLabel node, final double normal,double angle,final Base base)
	{

		Runnable r=new Runnable()
		{
			public void run()
			{		
				System.out.println("flooding is selected");
				//System.exit(0);
				
				if(base==null)
				{
					System.out.println("base is null darling");
					System.exit(0);
				}
				
				Packet packet=new Packet(base);								
				packet.identifier=2;
				packet.sourceType=base.SENSORNODE;
				packet.source=node;
				//packet.fromNode=node;
				packet.protocolType=base.ANGULAR_FLOODING;
				packet.queryType=base.SET_GRADIENT;
				packet.ttl=13;
				packet.hopCount=0;
				packet.destination=base.BROADCAST_DEST;
				packet.parentSequence.push(node);
				
				for(int i=0;i<node.neighborList.size();i++)
				{
					Packet newPacket=(Packet)packet.clone();
					newPacket.parentSequence=(Stack)packet.parentSequence.clone();
					System.out.println("clone  "+i);
					if(node.neighborList.get(i).buffer.size()<node.neighborList.get(i).defaultBufferSize)
					{
						node.neighborList.get(i).buffer.add(newPacket);
						node.neighborList.get(i).setBackground(base.YELLOW);
						System.out.println(node.neighborList.get(i).uniqueId);
					}	
				}
			}
		};
		new Thread(r).start();
	}	
	//---------------------------------------------------------------------
*/	
/*	//-------FUNCTION TO FLOOD PACKET IN PARTICULAR ANGLE------------------
	public void controlledFloodingForGradientNADS( final VLabel node, final double normal,double angle,final Base base)
	{
		Runnable r=new Runnable()
		{
			public void run()
			{		
				System.out.println("flooding is selected");
				//System.exit(0);
				
				if(base==null)
				{
					System.out.println("base is null darling");
					System.exit(0);
				}
				
				Packet packet=new Packet(base);								
				packet.identifier=2;
				packet.sourceType=base.SENSORNODE;
				packet.source=node;
				//packet.fromNode=node;
				packet.protocolType=base.CONTROLLED_FLOODING;
				packet.queryType=base.SET_GRADIENT_NADS;
				packet.ttl=13;
				packet.hopCount=0;
				packet.destination=base.BROADCAST_DEST;
				packet.parentSequenceNADS.push(node);
				
				for(int i=0;i<node.neighborList.size();i++)
				{
					Packet newPacket=(Packet)packet.clone();
					newPacket.parentSequenceNADS=(Stack)packet.parentSequenceNADS.clone();
					System.out.println("clone  "+i);
					//if(node.neighborList.get(i).buffer.size()<node.neighborList.get(i).defaultBufferSize)
					//considering infinite size for buffer
						node.neighborList.get(i).buffer.add(newPacket);
						node.neighborList.get(i).setBackground(base.YELLOW);
						System.out.println(node.neighborList.get(i).uniqueId);	
				}
			}
		};
		new Thread(r).start();
	}
	
	//-----------CONTROLLED FLOODING FOR NADS PHASE-3----------------------
*/	
	public void controlledFloodingNADSPhase3( final VLabel node,final Packet packet,final Base base)
	{
		Runnable r=new Runnable()
		{
			public void run()
			{		
				//System.out.println("flooding is selected");
				//System.exit(0);
				
				if(base==null)
				{
					//System.out.println("base is null darling");
					System.exit(0);
				}
				

				
				for(int i=0;i<node.neighborList.size();i++)
				{
					Packet newPacket=(Packet)packet.clone();
					//newPacket.parentSequenceNADS=(Stack)packet.parentSequenceNADS.clone();
					//System.out.println("clone "+i);
					//if(node.neighborList.get(i).buffer.size()<node.neighborList.get(i).defaultBufferSize)
					//considering infinite size for buffer
						node.neighborList.get(i).buffer.add(newPacket);
						node.neighborList.get(i).setBackground(base.YELLOW);
						//System.out.println(node.neighborList.get(i).uniqueId);	
				}
			}
		};
		new Thread(r).start();
	}
	
	//--------------------------------------------------------------------
	
	public static void main(String args[])
	{
		VToolBox b=new VToolBox(null);
		//System.out.println(b.getDist(100,100,200,200));
	}
}
