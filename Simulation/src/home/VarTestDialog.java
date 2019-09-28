package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VarTestDialog extends JDialog 
{
Base base;
JPanel centerPanel;
JPanel southPanel;
JPanel northPanel;
	public VarTestDialog(Base base)
	{
		super(base);
		this.base=base;
		
		setLayout(new BorderLayout());
		
		northPanel=new JPanel();
		
		centerPanel=new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new GridLayout(10,1));
		//int bsCount=base.masterBSList.size();
		//JLabel bsCountLb=new JLabel("base count "+bsCount);
		
		//centerPanel.add(bsCountLb);
				
		southPanel=new JPanel();
		
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
		
	}
	
	public static void main(String...args)
	{
		System.out.println("the name of author is vikrant sharma");
	}
}
