package home;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class Preview extends JDialog 
{
	Base base;
public Preview(VLabel lb,Base b)
{
	super(b, "preview", true);
	this.base=b;	
	setSize(900,500);
	setLayout(new GridLayout(11,0));
	JLabel uniqueIdLb=new JLabel("node id:  "+Integer.toString(lb.uniqueId));
//	JLabel parentsLb=new JLabel(" number of parents:  "+Integer.toString(lb.parentList.size()));
//	JLabel parentIdLb=new JLabel("parents:  "+parents);
	JLabel nbLb=new JLabel(" number of neighbors:  "+Integer.toString(lb.neighborList.size()));
	JLabel connectionLb=new JLabel(lb.connectionList.toString());
	String s="";
	for(int i=0;i<lb.neighborList.size();s=s+",  "+Integer.toString(lb.neighborList.get(i).uniqueId),i++);
	JLabel neighborListLb=new JLabel("neighbor list:  "+s);
	JLabel posLb=new JLabel("node position:  "+Integer.toString(lb.xPos)+","+ Integer.toString(lb.yPos));
	JLabel trLb=new JLabel("traversed status:  "+Boolean.toString(lb.traversed));
	JLabel powerLb=new JLabel("power status:  "+lb.power );
	JLabel bufferLb=new JLabel("buffer size: "+lb.defaultBufferSize);
	JLabel packetsInBufferLb=new JLabel(" Packets in buffer :"+lb.buffer.size());
	JLabel temperatureLb=new JLabel("temperature :"+base.centerPanel.coordinateMat[lb.getY()][lb.getX()].temperature);
	
	JLabel baseLocLb;
try
{
	baseLocLb=new JLabel("base station loc: "+lb.baseStationId.getX()+","+lb.baseStationId.getY());
}
catch(Exception e)
{
	baseLocLb=new JLabel("null");	
}
	JLabel packetCountLb=new JLabel("packet count:"+lb.packetQueue.size());
	
	//JLabel pathLb=new JLabel("path :"+ lb.gradientStack.size());
	
	JLabel addMeAsNeighborLb=new JLabel("node connecting base:  "+ lb.nodesConnectingBaseList.size());
	JLabel connectedLb=new JLabel("Connected :  "+lb.isConnected);
	JLabel placedLb=new JLabel("Placed :"+ lb.isPlaced);
	JLabel movableLb=new JLabel("movable :"+lb.movable);
	JLabel parentTowardsBaseLb=new JLabel("null");
	try
	{
		parentTowardsBaseLb=new JLabel("parent towards base :"+lb.parentTowardsBase.uniqueId);;
	}
	catch(Exception e){System.out.println(e);}
	 JLabel isInRangeLb=new JLabel("in range: "+lb.isInRange);
	 JLabel identifiedLb=new JLabel("identified :"+lb.identified);
	 JLabel finalLocationLb=new JLabel("null");
	 try
	 {
		 finalLocationLb.setText("final location : "+lb.finalLocation.getXLoc()+","+lb.finalLocation.getYLoc());		
	 }
	 catch(Exception e){System.out.println(e);}
	 	 
	 JLabel nearestLocLb=new JLabel(" nearest DL loc :null");
	 try
	 {
		 nearestLocLb.setText("nearest DL loc :"+lb.nearestDesiredPoint.getXLoc()+","+lb.nearestDesiredPoint.getYLoc());
	 }
	 catch(Exception e){System.out.println(e);}
	 
	 JLabel onObstacle=new JLabel("on obstacle :"+base.centerPanel.coordinateMat[lb.getX()][lb.getY()].obstacle);
	 String  tempSt="";
	 JLabel gradientSizeLb=new JLabel("");
	 JLabel gradientLb=new JLabel("");
	 try
	 {
	 Stack <VLabel>tempStack=(Stack <VLabel>)(lb.gradientStackPriorityQueue.get(0).clone());
	 gradientSizeLb=new JLabel("GRADIENET SIZE :"+ tempStack.size());
	 while(tempStack.size()>0)
	 {
		 VLabel tempLb=tempStack.pop();
		 tempSt=tempSt+", ("+tempLb.nodeType+") "+tempLb.uniqueId;
	 }
	 gradientLb=new JLabel("gradient: "+tempSt);
	 }
	 catch(Exception e){}
	 
	 JLabel totalPathsLb=new JLabel("total paths :"+lb.gradientStackPriorityQueue.size());
	 JLabel deservingLb=new JLabel("deserving node :"+lb.iAmDeserving);
	 JLabel neighboringLocListLb=new JLabel("neighboring loc list :"+ lb.nearestDesiredPoint.neighboringLocList.size());
	 JLabel neighboringUoccupiedLocListLb=new JLabel("neighboring unallocated loc list :"+ lb.nearestDesiredPoint.neighboringUnoccupiedLocList.size());
	 JLabel subDivisionLb=new JLabel("sub division number : "+lb.subDivisionNumber);
	 JLabel clusterNeighborLb=new JLabel("cluster neighbor number:"+ lb.clusterNeighborList.size());
	 JLabel workingLb=new JLabel("working of node :"+ lb.working);
	 JLabel queueLb=new JLabel("queue size:"+lb.buffer.size());
	 JLabel commThreadStateLb=new JLabel("comm thread state:"+lb.communicationThread.isAlive());
	 JLabel chCountLb=new JLabel("cluster head count:"+lb.nodeDistList.size());
	 JLabel nearestClusterHeadLb=new JLabel("");
	 try
	 {
		 nearestClusterHeadLb=new JLabel("cluster head :"+ lb.nodeClusterHead.uniqueId);
	 }
	 catch(Exception e){}
	 
	 String tempSt1="";
	 for(int i=0;i<lb.dataRecievedFromCHList.size();i++)
	 {
		 tempSt1=tempSt1+","+ lb.dataRecievedFromCHList.get(i).uniqueId;
	 }
	 JTextField dateRecievedFromLb=new JTextField("data recieved from : "+tempSt1);
	 //String sortedSt="";
	 JLabel bs_clusterLb=new JLabel("null");;
	 try
	 {
		 bs_clusterLb=new JLabel(lb.BS_Cluster_List.get(0).BaseStation.uniqueId+" ,  "+lb.BS_Cluster_List.get(0).cluster.clusterId);
	 }
	 catch(Exception e){}
	 
	 JLabel lastRoundLb=new JLabel("Last Round : "+ lb.lastRound);
	 
	 JLabel groupLb=new JLabel("Group :"+ lb.group);
	 JLabel unRcvdUallocatedLoc=new JLabel("Rcvd Unallocated Loc NADS phase3:"+lb.receivedUnallocatedDlList.size());
	 JLabel unplacedFriendSNLb=new JLabel("Unplaced Friends :"+lb.unplacedFriendSNList.size());
	 
	 JLabel tempVarLb=new JLabel("temp var:"+lb.tempVar);
	 

	

	add(uniqueIdLb);
	//add(parentsLb);
	//add(parentIdLb);
	add(nbLb);
	add(neighborListLb);
	add(connectionLb);
	add(posLb);
	add(trLb);
	add(powerLb);
	add(bufferLb);
	add(packetsInBufferLb);
	add(baseLocLb);
	add(temperatureLb);
	add(packetCountLb);
	add(addMeAsNeighborLb);
	add(connectedLb);
	add(placedLb);
	add(movableLb);
	add(parentTowardsBaseLb);
	add(isInRangeLb);
	add(identifiedLb);
	add(finalLocationLb);
	add(nearestLocLb);
	add(onObstacle);
	add(gradientLb);
	add(gradientSizeLb);
	add(totalPathsLb);
	add(deservingLb);
	add(neighboringLocListLb);
	add(neighboringUoccupiedLocListLb);
	add(subDivisionLb);
	add(clusterNeighborLb);
	add(workingLb);
	add(queueLb);
	add(commThreadStateLb);
	add(chCountLb);
	add(nearestClusterHeadLb);
	add(dateRecievedFromLb);
	add(bs_clusterLb);
	add(lastRoundLb);
	add(groupLb);
	add(unRcvdUallocatedLoc);
	add(unplacedFriendSNLb);
	add(tempVarLb);
	
	setVisible(true);
	
	
	
}

}
