package home;
import java.util.ArrayList;
public class Parent_Children 
{
	VLabel parent;
	ArrayList<VLabel> children_List=new ArrayList<VLabel>();
	Base base;
	Parent_Children(VLabel parent, ArrayList<VLabel>chlidren_List,Base b)
	{
		base=b;
		this.parent=parent;
		this.children_List=chlidren_List;		
	}
}
