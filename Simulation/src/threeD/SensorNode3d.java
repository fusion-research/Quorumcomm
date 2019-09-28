package threeD;

import java.util.ArrayList;

import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.geometry.Cone;

public class SensorNode3d 
{
	Cone snCone;
	TransformGroup snTransGroup;	
	SensorNode3d(Cone snCone, TransformGroup snTransformGroup)
	{
		this.snTransGroup=snTransformGroup;
		this.snCone=snCone;
	}
}

