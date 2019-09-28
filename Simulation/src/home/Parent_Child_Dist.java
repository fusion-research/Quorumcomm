package home;
public class Parent_Child_Dist 
{
	Object parent;
	VLabel child ;
	float dist;
	boolean connection=true;
	Base base;
	Parent_Child_Dist(Object parent,VLabel child,float dist,Base b)
	{
		base=b;
		this.parent=parent;
		this.child=child;
		this.dist=dist;
		
	}

}
