package home;
import java.util.*;
public class DHDS2 
{
	Base base;
	VToolBox vToolBox;
	DHDS2(Base b)
	{
		base=b;
		vToolBox=new VToolBox(base);
	}
//GETTING DESIRED POINTS IN THE GIVEN RANGE IN SORTED ORDER
	
	ArrayList <Loc_Dist> getDesiredPointsWithDist(Location loc,ArrayList<Location>desiredLocList)  
	{
		ArrayList<Location>tempLocList=new ArrayList<Location>();
		ArrayList<Loc_Dist>locDistList=new ArrayList<Loc_Dist>();
		for(int i=0;i<desiredLocList.size();i++)
		{
				tempLocList.add(desiredLocList.get(i));
		}
		//Collections.sort(tempLocList,new Location.orderByDistance());  // SORTING THE DESIRED POINTS IN THE RANGE GIVEN
		//System.out.println("TEMP LOCATIONS LIST SIZE  "+tempLocList.size());
		tempLocList=vToolBox.vSort(loc,tempLocList);
		
		for(int j=0;j<tempLocList.size();j++)
		{
			
			Loc_Dist locDist=new Loc_Dist(tempLocList.get(j),vToolBox.getDist(loc,tempLocList.get(j)),base);
			locDistList.add(locDist);
		}
		//return tempLocList;
		return locDistList;
	}
	
//---------------------------------------------------------------
	
	//ArrayList<Loc_Node> getLocNodeTable(Vlabel sn,ArrayList<VLabel> nodeList,ArrayList <Location> desiredLocList)
	ArrayList<Loc_Node> getLocNodeTable(VLabel sn)
	{
		ArrayList <Loc_Node>locNodeList=new ArrayList<Loc_Node>();
		//********************************************************************
		//FOR EVERY NODE CREATE SORTED LIST OF LOC-DIST-LIST
		for(int i=0;i<sn.unplacedFriendSNList.size();i++)
		{
			Location loc=new Location(sn.unplacedFriendSNList.get(i).getX(),sn.unplacedFriendSNList.get(i).getY(),base);
			sn.unplacedFriendSNList.get(i).locDistList=getDesiredPointsWithDist(loc, sn.unallocatedDlList);
			//System.out.println("LOC DIST LIST SIZE OF NODES : "+nodeList.get(i).locDistList.size());
		}
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		for(int i=0;i<sn.unplacedFriendSNList.size();i++)
		{
			System.out.println("------------------------------------------------");
			System.out.println("NODE :"+sn.unplacedFriendSNList.get(i).uniqueId);
			for(int j=0;j<sn.unplacedFriendSNList.get(i).locDistList.size();j++)
			{
				System.out.println("("+sn.unplacedFriendSNList.get(i).locDistList.get(j).getLoc().getXLoc()+","+sn.unplacedFriendSNList.get(i).locDistList.get(j).getLoc().getYLoc()+")   ,  "+sn.unplacedFriendSNList.get(i).locDistList.get(j).distance);
			}
		}
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		//System.exit(0);
		int nodeListInitialSize=sn.unplacedFriendSNList.size();
		//*********************************************************************
		ArrayList <Location>tempLocList=new ArrayList<Location>();
		
		while(sn.unplacedFriendSNList.size()>0 && sn.unallocatedDlList.size()>0 )
		{
			
			int i=0;
			VLabel tempNode=sn.unplacedFriendSNList.get(i);
			Loc_Dist tempLocDist=tempNode.locDistList.get(0);
			Location tempLoc=tempLocDist.getLoc();
			float tempDist=tempLocDist.getDistance();
			
			i=i+1;
			while(i<sn.unplacedFriendSNList.size())
			{
				VLabel tempNode2=sn.unplacedFriendSNList.get(i);
				Loc_Dist tempLocDist2=tempNode2.locDistList.get(0);
				Location tempLoc2=tempLocDist2.getLoc();
				float tempDist2=tempLocDist2.getDistance();
				
				if(vToolBox.cmpLoc(tempLoc,tempLoc2))
				{
					if(tempDist<=tempDist2)
					{
						tempNode2.locDistList.remove(0);
					}
					else
					{
						tempNode.locDistList.remove(0);
						tempNode=tempNode2;
					}
				}				
				i=i+1;			
			}
			
			Loc_Node locNode=new Loc_Node(tempNode.locDistList.get(0).getLoc(),tempNode,base);
			if(vToolBox.canBeAdded(locNodeList,locNode))
			{
				locNodeList.add(locNode);
				sn.unplacedFriendSNList.remove(sn.unplacedFriendSNList.indexOf(locNode.getNode()));
				base.remainingLocList.remove(0);
			}
			else
			{
				tempNode.locDistList.remove(0);
			}
		}
		
		/*for(int j=0;j<desiredLocList.size() && j<nodeListInitialSize;j++)
		{
			
			//nodeList=refineNodeList(nodeList,tempLocList);
			
			System.out.println("bbbbbbbbbbbbbbbbbbb"+nodeList.size());
			int index=0;
			Location location=nodeList.get(0).locDistList.get(0).getLoc();
			float dist=nodeList.get(0).locDistList.get(0).getDistance();
			Loc_Node locNode=new Loc_Node(nodeList.get(index).locDistList.get(0).getLoc(),nodeList.get(index),base);
			

			
			for(int i=0;i<nodeList.size();i++)
			{
				if(vToolBox.cmpLoc(location,nodeList.get(i).locDistList.get(0).getLoc()))
				{
					if(nodeList.get(i).locDistList.get(0).getDistance()==dist)
					{
						dist=nodeList.get(i).locDistList.get(0).getDistance();
						index=i;
						locNode=new Loc_Node(nodeList.get(index).locDistList.get(0).getLoc(),nodeList.get(index),base);	
					}
					else if(nodeList.get(i).locDistList.get(0).getDistance()<dist)
					{
						nodeList.get(index).locDistList.remove(0);
						dist=nodeList.get(i).locDistList.get(0).getDistance();
						index=i;
						locNode=new Loc_Node(nodeList.get(index).locDistList.get(0).getLoc(),nodeList.get(index),base);
					}
					else
					{
						nodeList.get(i).locDistList.remove(0);
					}
				}
				else
				{
					
				}
			
			}


				locNodeList.add(locNode);
				nodeList.remove(nodeList.indexOf(locNode.getNode()));

	
		}*/
		
		//
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		
		vToolBox.printLocNode(locNodeList);
		
		//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
		return locNodeList;
	}
	

}
