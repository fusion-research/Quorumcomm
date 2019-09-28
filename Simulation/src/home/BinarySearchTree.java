package home;
//import java.io.*;
public class BinarySearchTree
{
	Node root;
	int size=0;
	Base base;

	public BinarySearchTree(Base b)
	{
		base=b;
	}
	Node locatePos(Node node)
	{
	Node nextNode=root;
	Node currentNode=nextNode;
	System.out.println("Node data="+node.data);
	System.out.println(size);
		while(nextNode!=null)
		{
			currentNode=nextNode;
			if(node.data>currentNode.data)
			{
				nextNode=currentNode.rightLink;
			}
			else if(node.data<currentNode.data)
			{
				nextNode=currentNode.leftLink;
			}
			else
			{
				System.out.println("node already exist");
				break;
			}
		}
		
		System.out.println("currentNode data="+currentNode.data);
		return currentNode;	
	}
	
	boolean insertNode(Node node)
	{	
		if(size==0)
		{
			System.out.println("new tree");
			root=node;
			size++;
			return true;
		}
		else
		{
			Node posNode=locatePos(node);			
			if(node.data>posNode.data)
			{
				posNode.rightLink=node;
				node.parentLink=posNode;
				size++;
				return true;
			}
			else if(node.data<posNode.data)
			{
				posNode.leftLink=node;
				node.parentLink=posNode;
				size++;
				return true;
			}
			else
			{
				System.out.println("node already exist");
				return false;
			}
		}
	}
	
	
	boolean insertNodeNLSelectionNADS(Node node)
	{	
		if(size==0)
		{
			System.out.println("new tree");
			root=node;
			size++;
			return true;
		}
		else
		{
			Node posNode=locatePos(node);			
			if(node.data>posNode.data)
			{
				posNode.rightLink=node;
				node.parentLink=posNode;
				
				int x=node.data/1000;
				int y=node.data%1000;
				Location loc=new Location(x,y,base);				
				node.sn.nearestDesiredPoint=loc;
				
				size++;
				return true;
			}
			else if(node.data<posNode.data)
			{
				posNode.leftLink=node;
				node.parentLink=posNode;
				
				int x=node.data/1000;
				int y=node.data%1000;
				Location loc=new Location(x,y,base);				
				node.sn.nearestDesiredPoint=loc;
				
				size++;
				return true;
			}
			else
			{
				System.out.println("node already exist");
				//return false;
				
				if(node.dist<posNode.dist)
				{
					if(posNode.data>posNode.parentLink.data)
					{
						posNode.parentLink.rightLink=node;
						node.parentLink=posNode.parentLink;
						return true;
					}
					
					else //if(posNode.data<posNode.parentLink.data)
					{
						posNode.parentLink.leftLink=node;
						node.parentLink=posNode.parentLink;
						return true;
					}
				}
				else
				{
					return false;
				}
				
				
			}
		}
	}

/*	public static void main(String[] args)
	{
		BinaryTree bt=new BinaryTree();
		while(true)
		{
		Console cons=System.console();
		String st=cons.readLine();
		int d=Integer.parseInt(st);
		Node n=new Node(d);
		bt.insertNode(n);
		}
	}*/
}
