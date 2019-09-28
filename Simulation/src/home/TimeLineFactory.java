package home;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
public class TimeLineFactory 
{
	Base base;
	TimeLineFactory(Base b)
	{
		base=b;
		try
		{
		ResultSet rs=base.joint.getDataByResultSet("show tables");
		{}
		
		}
		catch(SQLException e){e.printStackTrace();}
	}
	public static void main(String args[])
	{
		System.out.println("the name of author is vikrant sharma");
	}

}
