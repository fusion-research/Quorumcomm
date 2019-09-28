package home;
import java.util.*;
public class NodeAndParent 
{
	ArrayList<VLabel>nodeList=new ArrayList<VLabel>();
	//VLabel node;
	VLabel parent;
	
	NodeAndParent(VLabel parent,ArrayList<VLabel>nodeList)
	{
		this.nodeList=nodeList;
		this.parent=parent;
	}
	
	ArrayList<VLabel> getNodeList()
	{
		return this.nodeList;
	}
	
	VLabel getParent()
	{
		
		return this.parent;
	}

}
