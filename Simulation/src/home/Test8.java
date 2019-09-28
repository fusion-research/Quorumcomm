package home;
import java.util.*;

public class Test8 
{
	VToolBox vToolBox;
	Base  base;
	
	
	Test8(Base b)
	{
		base=b;
		vToolBox=new VToolBox(base);
	}
	ArrayList<Loc_Node> getLocNodeTable(ArrayList<VLabel> nodeList,ArrayList <Location> desiredLocList,int fromRange,int toRange)
	{
		ArrayList <Loc_Node>locNodeList=new ArrayList<Loc_Node>();
		//********************************************************************
		//FOR EVERY NODE CREATE SORTED LIST OF LOC-DIST-LIST
		for(int i=0;i<nodeList.size();i++)
		{
			Location loc=new Location(nodeList.get(i).getX(),nodeList.get(i).getY(),base);
			nodeList.get(i).locDistList=vToolBox.getDesiredPointsWithDist(loc, desiredLocList,fromRange,toRange);
		}
		
		//*********************************************************************
		for(int i=0;i<nodeList.size();i++)
		{
			if(locNodeList.size()==0)
			{
				nodeList.get(i).locDistList.get(0);
				Loc_Node locNode=new Loc_Node(nodeList.get(i).locDistList.get(0).getLoc(),nodeList.get(i),base);
				locNodeList.add(locNode);
			}
			else
			{
				nodeList.get(i).locDistList.get(0);
				checkRemoveAmbiguousLoc(locNodeList,nodeList.get(i));
				
			}
			
		}
		
		//
		
		return locNodeList;
	}
	
	void checkRemoveAmbiguousLoc(ArrayList<Loc_Node> locNodeList,VLabel node)
	{
		boolean flag=false;
		for(int i=0;i<locNodeList.size();i++)
		{
			Location loc=node.locDistList.get(0).getLoc();
			if(loc==locNodeList.get(i).getLoc())
			{
				flag=true;
				VLabel matchedNode=locNodeList.get(i).getNode();
				if(matchedNode.locDistList.size()>=2 && node.locDistList.size()>=2)
				{
					float a=matchedNode.locDistList.get(0).getDistance()+node.locDistList.get(1).getDistance();
					float b=matchedNode.locDistList.get(1).getDistance()+node.locDistList.get(0).getDistance();
					
					if(a>b)
					{
						matchedNode.locDistList.remove(0);
						locNodeList.remove(i);// REMOVING MATCHED NODE LOC ENTRY
						Loc_Node locNode=new Loc_Node(node.locDistList.get(0).getLoc(),node,base);
						locNodeList.add(locNode);
						checkRemoveAmbiguousLoc(locNodeList,matchedNode);	
					}
					
					else
					{
						node.locDistList.remove(0);
						checkRemoveAmbiguousLoc(locNodeList,node);
					}
				}
				else
				{
					float a=matchedNode.locDistList.get(0).getDistance();
					float b=node.locDistList.get(0).getDistance();
					if(a>b)
					{
						matchedNode.locDistList.remove(0);
						locNodeList.remove(i);
						Loc_Node locNode=new Loc_Node(node.locDistList.get(0).getLoc(),node,base);
						locNodeList.add(locNode);
						if(matchedNode.locDistList.size()!=0)
						{
							checkRemoveAmbiguousLoc(locNodeList,matchedNode);
						}	
					}
					
					else
					{
						node.locDistList.remove(0);
						if(node.locDistList.size()!=0)
						{
							checkRemoveAmbiguousLoc(locNodeList,node);
						}
					}
				}
			}
		}
		if(flag==false)
		{
			Loc_Node locNode=new Loc_Node(node.locDistList.get(0).getLoc(),node,base);
			locNodeList.add(locNode);
			return;
		}
	}

}
