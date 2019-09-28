package home;
import java.util.*;
public class VFormule 
{
	public double getCentrifugalForce(int rpm, float length, float mass)
	{
		double w=(rpm*2*Math.PI)/60.0;
		return mass*Math.pow(w,2)*length;
	}
	
	public static void main(String args[])
	{
		VFormule vf=new VFormule();
		System.out.println("angular velocity :"+vf.getCentrifugalForce(100,4,1));
	}

}
