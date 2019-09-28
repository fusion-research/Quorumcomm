package home;
import java.util.*;
public class Loc_Dist
{
	Location loc;
	float distance;
	Base base;
	Loc_Dist(Location loc,float distance,Base b)
	{
		base=b;
		this.loc=loc;
		this.distance=distance;
	}
	
	Location getLoc()
	{
		return loc;
	}
	
	float getDistance()
	{
		return distance;
	}
}
