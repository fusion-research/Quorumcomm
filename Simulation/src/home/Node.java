package home;

public class Node
{
	int data;
	Node parentLink=null;
	Node rightLink=null;
	Node leftLink=null;
	VLabel sn;
	float dist;
	
	public Node(int d)
	{
		data=d;
	}
	
	public Node(VLabel sensorNode, float dist)
	{
		sn=sensorNode;
		this.dist=dist;
	}
}

