package home;
import java.util.*;
class Test7
{
	VToolBox vToolBox;
	public static void main(String args[])
	{
		int arr[]={9,8,7,6,5,4,3,2,1};
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr.length-i-1;j++)
			{
				if(arr[j]>arr[j+1])
				{
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		for(int i=0;i<arr.length;i++)
		{
			System.out.println(arr[i]);
		}
	}
	
	public void vSort(Location referenceLoc,ArrayList<Location>desiredLocations)
	{
		vToolBox=new VToolBox(null);
		Location locArr[]=(Location[])(desiredLocations.toArray());
		ArrayList <Float> tempList=new ArrayList<Float>();
		for(int i=0;i<desiredLocations.size();i++)
		{
			tempList.add(vToolBox.getDist(referenceLoc,desiredLocations.get(i)));
		}
		Float distArray[]=((Float[])(tempList.toArray()));
		for(int i=0;i<distArray.length;i++)
		{
			System.out.println(distArray[i]);
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
		
		
	}
}