package home;
import javax.swing.*;

import java.awt.*;
public class LineSegment implements Cloneable
{
	float x1;
	float y1;
	int length;
	double angle;
	int r;
	int g;
	int b;
	Color color;
	
	LineSegment(float x1,float y1, int length, double angle, int r, int g, int b)
	{
		this.x1=x1;
		this.y1=y1;
		this.length=length;
		this.angle=angle;
		this.color=new Color(r,g,b);
	}
	
	public Object clone()
	{
		try
		{
			LineSegment cloned=(LineSegment)super.clone();
			return cloned;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}

}
