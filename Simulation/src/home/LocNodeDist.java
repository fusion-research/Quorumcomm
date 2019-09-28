package home;

public class LocNodeDist 
{
	Location loc;
	VLabel node;
	float dist;
	Base base;
	LocNodeDist(Location loc,VLabel node,float dist, Base b)
	{
		base=b;
		this.loc=loc;
		this.node=node;
		this.dist=dist;
	}
	 
	Location getLoc()
	{
		return loc;
	}

	VLabel getNode()
	{
		return node;
	}
	
	float getDist()
	{
		return dist;
	}
}