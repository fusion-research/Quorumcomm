package home;
import java.util.*;
public class Packet implements Cloneable 
{
	Object data;
	ArrayList<Loc_Node>locNodeList=new ArrayList<Loc_Node>();
	int packetType=0;
	int protocolType;
	int queryType;
	int ttl;
	int identifier;
	int hopCount;
	int sourceType;
	
	int toNodeId;
	
	Location moveTo;
	
	VLabel source;
	VLabel destination;
	VLabel finalDestination;
	VLabel toNode;
	VLabel fromNode;
	
	
	
	//-------------------THESE VARIABLES ARE FOR INSPECTOR PACKET----------------
	Loc_Node lastNode_Loc;
	VLabel lastNode;
	Object root;
	byte movement=0;
	//---------------------------------------------------------------------------
	
	//---------------------FOR BROADCASTING DESIRED LOCATION--------------
	ArrayList desired_Loc_List;
	Loc_Dist loc_Dist;
	//-------------------------------------------------------------------
	
	
	Stack <VLabel> pathStack=new Stack<VLabel>();
	//ArrayList <VLabel>parentSequence=new ArrayList<VLabel>();
	Stack <VLabel>parentSequence=new Stack<VLabel>();
	
	Stack <VLabel>parentSequenceNADS=new Stack<VLabel>();
	Stack <VLabel>childrenSequenceNADS=new Stack<VLabel>();
	int sourceId=-1;
	int destinationId=-1;
	
	Base base;
	public Packet(Base b)
	{
		base=b;
		movement=base.FORWARD;
	}
	
	public Object clone()
	{
		try
		{
			Packet cloned=(Packet)super.clone();
			return cloned;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	public static void main(String args[])
	{
		Packet p=new Packet(null);
		p.identifier=1;
		p.data=117;
		Packet p2=(Packet)p.clone();
		System.out.println(p2.data);
		System.out.println(p.data);
		p2.data=118;
		System.out.println(p.data);
		System.out.println(p2.data);
		p.data=119;
		System.out.println(p.data);
		System.out.println(p2.data);
		
	}

}
