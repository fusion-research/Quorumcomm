package home;
import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Location implements Comparable<Location>
{
	int x;
	int y;
	
	public float floatX;
	public float floatY;
	
	int group;
	
	Color color;
	boolean occupied=false;
	VLabel node=null;
	static Base base;
	static VToolBox toolBox;
	boolean flag=true;
	JLabel groupLb;
	
	ArrayList<Float>distFromBSList=new ArrayList<Float>();
	ArrayList<Location>neighboringLocList=new ArrayList<Location>();
	ArrayList<Location>neighboringUnoccupiedLocList=new ArrayList<Location>();
	public static class orderByDistance implements Comparator<Location>
	{
		public int compare(Location loc1,Location loc2)
		{
			  Location tempLoc=new Location(0,0,base);
			  Float p1 = toolBox.getDist(tempLoc,loc1);
			  Float p2 = toolBox.getDist(tempLoc,loc2);
			  if( p1 > p2 )  
				  return 1;  
				  else if( p1 < p2 )  
				  return -1;  
				  else  
				  return 0;
		}
	}
	
	Location(int x,int y, Base b)
	{
		this.base=b;
		toolBox=new VToolBox(base);
		this.x=x;
		this.y=y;
		this.floatX=x;
		this.floatY=y;
	}
	
	Location(float x,float y, Base b)
	{
		this.base=b;
		toolBox=new VToolBox(base);
		this.x=(int)x;
		this.y=(int)y;
		this.floatX=x;
		this.floatY=y;
	}
	
	Location(int x,int y,int group, Base b)
	{
		this.base=b;
		toolBox=new VToolBox(base);
		this.x=x;
		this.y=y;
		this.group=group;
		this.floatX=x;
		this.floatY=y;
	}
	
	public int getXLoc()
	{
		return this.x;
	}
	
	public int getYLoc()
	{
		return this.y;
	}
	  public int compareTo(Location loc) {
	        //return this.getXLoc() > o.orderId ? 1 : (this.orderId < o.orderId ? -1 : 0);
		  return 0;
	    }
	String printLoc()
	{
		System.out.println(this.x +","+this.y);
		return this.x +","+this.y;
	}
	
	VCoordinate toCoordinate()
	{		
		return new VCoordinate(this.x, this.y,1,base.RED,base);
	}

	boolean equals(Location loc, int tolerance)
	{
		if(Math.abs(this.x-loc.x)<=0+tolerance && Math.abs(this.y-loc.y)<0+tolerance)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
