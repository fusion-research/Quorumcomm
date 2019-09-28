package home;
import java.util.*;
public class Test5 
{
	public void aadsNodePlacement(int range)	
	{
		int x=0;
		int y=0;
			Random rand=new Random();
			int randVal=0;						
			for(;;)
			{
				randVal=rand.nextInt(range);
				System.out.println("random value="+randVal);
				try
				{
					Thread.sleep(1000);
				}	catch(Exception e){}
			}
	}
	public static void main(String args[])
	{
		
	}
}