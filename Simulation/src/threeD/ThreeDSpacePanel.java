package threeD;
import java.awt.*;

import com.sun.j3d.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument.BranchElement;
public class ThreeDSpacePanel extends JPanel
{
	public static void main(String arg[])
	{
		SimpleUniverse uni=new SimpleUniverse();
		BranchGroup groups=new BranchGroup();
		
			groups.addChild(new ColorCube(.05f));
		
		
		uni.getViewingPlatform().setNominalViewingTransform();
		uni.addBranchGraph(groups);
	}

}
