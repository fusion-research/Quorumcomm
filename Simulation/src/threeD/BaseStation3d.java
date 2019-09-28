package threeD;

import java.util.ArrayList;

import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.geometry.Cone;

public class BaseStation3d 
{
	Cone bsCone;
	TransformGroup baTransGroup;	
	BaseStation3d(Cone bsCone, TransformGroup baTransformGroup)
	{
		this.baTransGroup=baTransformGroup;
		this.bsCone=bsCone;
	}
}

