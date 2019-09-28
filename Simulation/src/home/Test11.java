package home;
import javax.swing.*;
import java.awt.*;

public class Test11{
  public static void main(String[] args)
  {
	  double degree=45.0;
	  double radians=Math.toRadians(degree);
	  System.out.println("radians of 45 is : "+radians);
	  System.out.println("degree of radian is : "+Math.toDegrees(radians));
	  System.out.println(Math.toDegrees((Math.atan(1))));
  }
}