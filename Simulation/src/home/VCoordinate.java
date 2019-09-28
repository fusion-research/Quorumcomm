package home;
import java.util.*;
import java.awt.*;
public class VCoordinate
{
	Base base;
	int temperature=0;
	int humidity=0;
	int light=0;
	boolean covered=false;
	boolean obstacle=false;
	boolean affected=false;
	boolean traversed=false;
	boolean areaOfInterest=true;
	Location location;
	int subDivisionNumber=0;
	//----------------VARIABLES USED BY AADS------------------------------
	
	//ArrayList<ThreeDimParameters>ThreeDParamList=new ArrayList<ThreeDimParameters>();
	boolean gliderTunnel=false;
	
	//--------------------------------------------------------------------
	
	//----------------USED BY GRAPH PANEL FOR PLOTTING GRAPH---------------
	int x;
	int y;
	int z;
	Color color=Color.GRAY;
	int shape;
	//---------------------------------------------------------------------
	VCoordinate(Base b)
	{
		base=b;
	}
	
	VCoordinate(int x,int y,int shape,Color color,Base b)
	{
		base=b;
		this.x=x;
		this.y=y;
		this.shape=shape;
		this.color=color;
	}
	VCoordinate(int x,int y,int z,int shape,Color color,Base b)
	{
		base=b;
		this.x=x;
		this.y=y;
		this.z=z;
		this.shape=shape;
		this.color=color;
	}

}
