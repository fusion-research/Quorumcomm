package home;

public class NeighborInfo implements Comparable<NeighborInfo>
{
	VLabel startNode;
	VLabel endNode;
	float dist=0;
	
	NeighborInfo(VLabel startNode,VLabel endNode,float dist)
	{
		this.startNode=startNode;
		this.endNode=endNode;
		this.dist=dist;
	}
	public int compareTo(NeighborInfo info) {
		 
		float compareDist = ((NeighborInfo) info).dist; 
 
		//ascending order
		return (int)(this.dist*100 - compareDist*100);
 
		//descending order
		//return compareQuantity - this.quantity;
 
	}
}
