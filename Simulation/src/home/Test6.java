package home;
import java.util.*;
public class Test6 
{
	public void fun()
	{
	for(int i=0;i<1000;i++)
	{
		System.out.print(i);
	}
	}
public static void main(String args[])
{
	for(int j=0;j<20;j++)
	{
	long startTime=System.currentTimeMillis();
	Test6 t=new Test6();
	t.fun();
	long endTime=System.currentTimeMillis();
	long timeTaken=endTime-startTime;
	System.out.println("----------------------------------");
	System.out.println(j+"  time taken  :"+timeTaken);
	}
}
}
