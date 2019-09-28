package home;
import java.awt.Color;
import java.util.*;
public class Algorithms 
{

	/**
	 * @param args
	 */
	Base base;
	VToolBox toolBox;
	int delay=100;
	public Algorithms(Base b)
	{
		this.base=b;
		toolBox=new VToolBox(base);
		
	}
	
	/*public void flooding(final VLabel parentNode, final VLabel childNode,final int delay)
	{
		int s;
		synchronized(this)
		{
		if(childNode.parentList.contains(parentNode))
		{
			return;
		}
		this.delay=delay;
		try{Thread.sleep(10);}catch(Exception e){}//???????????
		
		System.out.println(childNode.parentList);
		System.out.println("flooding function called");
		
		childNode.xPos=childNode.getX();
		childNode.yPos=childNode.getY();
		childNode.lineColor=Color.ORANGE;
		childNode.setIcon(childNode.defaultIcon);
		
		System.out.println("DELAY   "+delay);
		//try{Thread.sleep(delay);}catch(Exception e){}
		s=childNode.neighborList.size();
		
		if(parentNode==null)
		{
			for(int i=0;i<s;i++)
			{
				VLabel tmpNode=childNode.neighborList.get(i);
				
				if(childNode.parentList.contains(tmpNode))
				{
					
					System.out.println("continue in null");
					continue;
				}
				
				int posParTmp=tmpNode.neighborList.indexOf(childNode);
				tmpNode.connectionList.remove(posParTmp);
				tmpNode.connectionList.add(posParTmp,false);
				
				
			
				//tmpNode.xPos=tmpNode.getX();
				//tmpNode.yPos=tmpNode.getY();
			}
		}
		
		else
		{
		   //int delay=(int)toolBox.getDist(parentNode, childNode);
			childNode.parentList.add(parentNode);
			s=childNode.neighborList.size();
			for(int i=0;i<s;i++)
			{
			
				VLabel tmpNode=childNode.neighborList.get(i);
				if(childNode.parentList.contains(tmpNode))
				{
					System.out.println("continue in not null");
					
					int posParTmp=tmpNode.neighborList.indexOf(childNode);
					tmpNode.connectionList.remove(posParTmp);
					tmpNode.connectionList.add(posParTmp,true);
					
					continue;
				}
				int posParTmp=tmpNode.neighborList.indexOf(childNode);
				tmpNode.connectionList.remove(posParTmp);
				tmpNode.connectionList.add(posParTmp,false);
				
				
				//tmpNode.xPos=tmpNode.getX();
				//tmpNode.yPos=tmpNode.getY();
			}
		}
		
		}
		for(int i=0;i<s;i++)
		{
			final VLabel temp=childNode.neighborList.get(i);
			final int d=(int)toolBox.getDist(parentNode, childNode);//mmmmmmmmmmmmmmmmmmm
			if(childNode.connectionList.get(i))
			{
				Runnable r=new Runnable()
				{
					public void run()
					{
						//flooding(childNode,temp);

						flooding(childNode,temp,d);//mmmmmmmmmmmmmmmmm
				
					}
				};
				new Thread(r).start();
			}
		}
		
		//}};
		//new Thread(r).start();		
		
		
		 
	}*/
	
	
	public void flooding(VLabel fromNode,Packet packet)
	{
		//VLabel source;
		VLabel parent;
		VLabel thisNode;
		fromNode.xPos=fromNode.getX();
		fromNode.yPos=fromNode.getY();
		
		fromNode.setBackground(fromNode.defaultColor);		
		
		
		if(packet.parentSequence.size()==0)
		{
			thisNode=packet.source;
			parent=null;
		}
		else
		{
			thisNode=fromNode;
			parent=packet.parentSequence.get(packet.parentSequence.size()-1);
		}
		//System.out.println(thisNode.neighborList);
		

		//packet.parentSequence.add(thisNode);//??????????????
		
		for(int i=0;i<thisNode.neighborList.size();i++)
		{
			
			
			if(! packet.parentSequence.contains(thisNode.neighborList.get(i)))
			{
				Packet newPacket=(Packet)packet.clone();
				//if(newPacket.protocolType==base.SET_GRADIENT)
				newPacket.parentSequence=(Stack)packet.parentSequence.clone();
				newPacket.protocolType=base.FLOODING;
				newPacket.fromNode=thisNode;
				//newPacket.hopCount=newPacket.hopCount+1;  //???????????????????????????????????????????????????
				newPacket.toNode=thisNode.neighborList.get(i);
				if(thisNode.neighborList.get(i).buffer.size()<thisNode.neighborList.get(i).defaultBufferSize)
				{
					//if(thisNode.powerEnoughForTransmission())
					//{
						thisNode.neighborList.get(i).buffer.add(newPacket);
						thisNode.neighborList.get(i).setBackground(base.YELLOW);					
						int posParTmp=thisNode.neighborList.indexOf(thisNode.neighborList.get(i));
						thisNode.connectionList.remove(posParTmp);
						thisNode.connectionList.add(posParTmp,true);
					//}
				}
											
			}
			
			else
			{
				
			}
			
			
		}
		
	}
	
	//------------------FUNCTION FOR GOSSIPING--------------------------------
	public void gossiping(VLabel fromNode,Packet packet)
	{
		//VLabel source;
		VLabel parent;
		VLabel thisNode;
		fromNode.xPos=fromNode.getX();
		fromNode.yPos=fromNode.getY();
		
		fromNode.setBackground(fromNode.defaultColor);		
		
		
		if(packet.parentSequence.size()==0)
		{
			thisNode=packet.source;
			parent=null;
		}
		else
		{
			thisNode=fromNode;
			parent=packet.parentSequence.get(packet.parentSequence.size()-1);
		}
		//System.out.println(thisNode.neighborList);
		

		//packet.parentSequence.add(thisNode);//??????????????
		
		for(int i=0;i<thisNode.neighborList.size();i++)
		{
			
			
			if(! packet.parentSequence.contains(thisNode.neighborList.get(i)))
			{
				Packet newPacket=(Packet)packet.clone();
				//if(newPacket.protocolType==base.SET_GRADIENT)
				newPacket.parentSequence=(Stack)packet.parentSequence.clone();
				newPacket.protocolType=base.GOSSIPING;
				newPacket.fromNode=thisNode;
				//newPacket.hopCount=newPacket.hopCount+1;  //???????????????????????????????????????????????????
				newPacket.toNode=thisNode.neighborList.get(i);
				//if(thisNode.neighborList.get(i).buffer.size()<thisNode.neighborList.get(i).defaultBufferSize)
				//{
					//if(thisNode.powerEnoughForTransmission())
					//{
						thisNode.neighborList.get(i).buffer.add(newPacket);
						thisNode.neighborList.get(i).setBackground(base.YELLOW);					
						int posParTmp=thisNode.neighborList.indexOf(thisNode.neighborList.get(i));
						thisNode.connectionList.remove(posParTmp);
						thisNode.connectionList.add(posParTmp,true);
					//}
				//}
											
			}
			
			else
			{
				
			}
			
			
		}
		
	}
		
	//------------------------------------------------------------------------
	
	
	//------------------FUNCTION FOR DIRECTED FLOODING------------------------
	public void dirFlooding(VLabel fromNode,Packet packet)
	{
		//System.exit(0);///   OK REPORT
		VLabel source;
		VLabel parent;
		VLabel thisNode;
		fromNode.xPos=fromNode.getX();
		fromNode.yPos=fromNode.getY();
		
		fromNode.setBackground(fromNode.defaultColor);
		if(packet.parentSequence.size()==0)
		{
			thisNode=packet.source;
			parent=null;
		}
		else
		{
			thisNode=fromNode;
			parent=packet.parentSequence.get(packet.parentSequence.size()-1);
		}
		//System.out.println(thisNode.neighborList);
		

	
			//Collections.sort(thisNode.neighborList,new VLabel());	
			//System.out.println("XXXXXX"+thisNode.neighborList);
			//System.exit(0);
		ArrayList<VLabel>tempList=new ArrayList<VLabel>();
		for(int i=0;i<thisNode.neighborList.size();i++)
		{
			float standard=toolBox.getDist(thisNode,thisNode.baseStationId);
			if(toolBox.getDist(thisNode.neighborList.get(i),thisNode.neighborList.get(i).baseStationId)<standard)
					{
						tempList.add(thisNode.neighborList.get(i));
					}
		}
		
		for(int i=0;i<tempList.size();i++)
		{
			
			
			if(! packet.parentSequence.contains(tempList.get(i)))
			{
				Packet newPacket=(Packet)packet.clone();
			//	newPacket.parentSequence.add(thisNode);//?????????????????????????????????
				newPacket.protocolType=base.DIRECTIONAL_FLOODING;
				newPacket.fromNode=thisNode;
			//	newPacket.hopCount=newPacket.hopCount+1; //?????????????????????????????????????????????
				newPacket.toNode=tempList.get(i);
				if(tempList.get(i).buffer.size()<tempList.get(i).defaultBufferSize)
				{
					//if(thisNode.powerEnoughForTransmission())
					//{
						tempList.get(i).buffer.add(newPacket);
						tempList.get(i).setBackground(base.YELLOW);
				
				
				//flooding(newPacket);
				
				int posParTmp=thisNode.neighborList.indexOf(tempList.get(i));
				thisNode.connectionList.remove(posParTmp);
				thisNode.connectionList.add(posParTmp,true);
				
					//}
				}
				
			}
			
			else
			{
				
			}
			
			
		}
		
	}
	
	//------------------FUNCTION FOR DIRECTED FLOODING------------------------
	public void dirDiffusion(VLabel fromNode,Packet packet)
	{
		VLabel source;
		VLabel parent;
		VLabel thisNode;
		fromNode.xPos=fromNode.getX();
		fromNode.yPos=fromNode.getY();
		
		fromNode.setBackground(fromNode.defaultColor);
		if(packet.parentSequence.size()==0)
		{
			thisNode=packet.source;
			parent=null;
		}
		else
		{
			thisNode=fromNode;
			parent=packet.parentSequence.get(packet.parentSequence.size()-1);
		}
		
		if(packet.pathStack.size()>0)
		{
				
				//VLabel tempNode=packet.pathStack.pop();
				//VLabel tempNode=thisNode.gradientStackPriorityQueue.get(0).get(1);
				Stack <VLabel>tempStack=(Stack<VLabel>)thisNode.gradientStackPriorityQueue.get(0).clone();
				tempStack.pop();
				VLabel tempNode=tempStack.pop();
				
				//base.alert.display("unique id :"+tempNode.uniqueId);
				
				//Packet newPacket=(Packet)packet.clone();
				packet.parentSequence.add(thisNode);
				packet.protocolType=base.DIR_DIFFUSION;
				packet.fromNode=thisNode;
				//newPacket.hopCount=newPacket.hopCount+1;//????????????????????????????????????????????
				packet.toNode=tempNode;

				
				if(tempNode.buffer.size()<tempNode.defaultBufferSize)
				{
					//if(thisNode.powerEnoughForTransmission())
					//{
					//try
					//{
						tempNode.buffer.add(packet);
						tempNode.setBackground(base.YELLOW);
						if(tempNode.nodeType!=base.BASESTATION)
						{
							int posParTmp=thisNode.neighborList.indexOf(tempNode);
							thisNode.connectionList.remove(posParTmp);
							thisNode.connectionList.add(posParTmp,true);
						}
					//}
					//catch(Exception e){ System.exit(0); base.alert.display("thread ruk gai koi");e.printStackTrace();}
				//}
				}
		}
				
			
		
	}	

	//--------------------------------------------------------------------------
	
	//*************************************************************************
	//FUNCTION FOR XYZ ALGORITHM
	//------------------FUNCTION FOR DIRECTED FLOODING------------------------
	public void xyz(VLabel fromNode,Packet packet)
	{
			VLabel toNode=fromNode.parentTowardsBase;
			if(toNode.buffer.size()<toNode.defaultBufferSize)
			{
				toNode.buffer.add(packet);
				toNode.setBackground(Color.pink);
				//System.exit(0);
			}
				
			
		
	}	

	
	
	//***********************IMPLEMENTING CLUSTERING**************************
	
	public void clustering(VLabel nodeArray[])
	{
		
	}
	
	//***************END IMPLEMENTING CLUSTERING******************************
	public static void main(String[] args) 
	{
		//System.out.println("the name of the author is vikrant sharma");

	}

}
