package home;
import java.util.*;
class Test9
{
	double rpm=1000;
	double rps=rpm/60;
	double w=2*Math.PI*rps;
	double step=0.001;
	double ds=step;
	
	public static void main(String ...args)
	{

		double ls=0.04;
		double le=1;
		double t=0;
		Test9 t9=new Test9();
		t9.getTime(ls,le,t);
		
	}
	void getTime(double ls, double le, double t)
	{
		
		if(ls<le)
		{
			
			//System.out.println(t);
			getTime(ls+ds,le,t+ds/(w*ls));
		}
		System.out.println(t);
		//return t;
	}
}