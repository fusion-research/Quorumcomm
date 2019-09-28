package home;
import java.util.*;
public class Range 
{
	int from;
	int to;
	
	ArrayList<Location> desiredLocInRangeList=new ArrayList<Location>();
	ArrayList<Location> getUnoccupiedLocInRangeList()
	{
		ArrayList<Location>tempLocList=new ArrayList<Location>();
		
		for(int i=0;i<desiredLocInRangeList.size();i++)
		{
			if(desiredLocInRangeList.get(i).occupied==false)
			{
				tempLocList.add(desiredLocInRangeList.get(i));
			}
		}
		return tempLocList;
	}

}
