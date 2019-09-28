package home;
import java.util.*;
public class Node_Dist 
{
	VLabel node;
	int distance;
	Base base;
	
	Node_Dist(VLabel node,int distance,Base b)
	{
		base=b;
		this.node=node;
		this.distance=distance;
		
	}
	
	VLabel getNode()
	{
		return node;
	}
	
	int getDist()
	{
		return distance;
	}
	
	
}
