package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class PropertiesIFrame extends JInternalFrame 
{
	PropertiesIFrame(String title,boolean resizable,boolean closable, boolean movable,boolean iconifiable)
	{
		super(title,resizable,closable,movable,iconifiable);
		JPanel northPanel=new JPanel();
		
		JPanel centerPanel=new JPanel();
		
		JPanel southPanel=new JPanel();
		
		JPanel westPanel=new JPanel();
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
		add("West",westPanel);
		

	      setBounds(100,100, 500, 500);
	}
	
}
