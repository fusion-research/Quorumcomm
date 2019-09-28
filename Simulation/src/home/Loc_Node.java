package home;

public class Loc_Node 
{
	Location loc;
	VLabel node;
	Base base;
	Loc_Node(Location loc,VLabel node,Base b)
	{
		base=b;
		this.loc=loc;
		this.node=node;
	}
	 
	Location getLoc()
	{
		return loc;
	}

	VLabel getNode()
	{
		return node;
	}
}