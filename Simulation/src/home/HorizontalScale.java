package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HorizontalScale extends JPanel 
{
	HorizontalScale()
	{
		setBackground(Color.WHITE);
		add(new JButton("button"));
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.drawLine(10, 10, 500, 10);
	}
}
