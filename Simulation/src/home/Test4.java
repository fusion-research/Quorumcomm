package home;
import java.util.*;
public class Test4 
{
	public static void main(String args[])
	{
		Random rand=new Random();
		for(int i=0;i<100;i++)
		{
			int temp=rand.nextInt(4);
			System.out.println(temp);
		}
	}
}
