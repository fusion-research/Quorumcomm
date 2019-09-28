package threeD;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;
public class Sample1 
{
	public static void main(String args[])
	{
		//System.out.println("the name of the author is vikrant sharma");
		SimpleUniverse uni=new SimpleUniverse();
		BranchGroup bg=new BranchGroup();
		
		
	      Sphere sphere = new Sphere(0.05f);

	      TransformGroup tg = new TransformGroup();

	      Transform3D transform = new Transform3D();

	      Vector3f vector = new Vector3f(.0f,.0f,.0f);

	      transform.setTranslation(vector);

	      tg.setTransform(transform);

	      tg.addChild(sphere);

	      bg.addChild(tg);
		
		//--------------------------------------------------------------------
	      
	      Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light

	      BoundingSphere bounds =  new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

	      Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);

	      DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);

	      light1.setInfluencingBounds(bounds);

	      bg.addChild(light1);
	      
		
		//--------------------------------------------------------------------
		//bg.addChild(sp);
		uni.getViewingPlatform().setNominalViewingTransform();
		uni.addBranchGraph(bg);
						
	}

}
