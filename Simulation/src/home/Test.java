package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Test
{
	public static void main(String args[])
	{
		float start=0.028f;
		int num=14;
		for(int i=1;i<=num;i++)
		{
			start=start+0.0023f;
			System.out.println("tt"+i+"="+start+";");
			System.out.println("td"+i+"="+start+";\n");		
		}
	}
}
