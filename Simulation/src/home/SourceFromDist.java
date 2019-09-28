package home;

public class SourceFromDist 
{
	VLabel sourceNode;
	VLabel fromNode;
	float  dist;
	
	SourceFromDist parentLink=null;
	SourceFromDist rightLink=null;
	SourceFromDist leftLink=null;
	
	SourceFromDist(VLabel sourceNode, VLabel fromNode, float dist)
	{
		this.sourceNode=sourceNode;
		this.fromNode=fromNode;
		this.dist=dist;
	}
	
	float getDist()
	{
		return this.dist;
	}
	
	VLabel getFromNode()
	{
		return fromNode;
	}
	
	VLabel sourceNode()
	{
		return sourceNode;
	}

}
