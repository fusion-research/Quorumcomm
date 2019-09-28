package home;
import java.util.*;
import javax.swing.*;

import java.awt.*;
public class MonitorPanel extends JPanel
{
	Base base;
	MonitorPanel(Base b)
	{
		base=b;
		setBorder(BorderFactory.createTitledBorder("monitor"));
		
	}
	public void repaintMonitor()
	{
		this.repaint();
		this.revalidate();
	}



}
