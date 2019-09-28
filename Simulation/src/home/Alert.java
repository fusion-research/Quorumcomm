package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Alert extends JDialog 
{

	JLabel msgLb;
	JPanel centerPanel;
	Alert()
	{
		
		JPanel northPanel=new JPanel();
		
		centerPanel=new JPanel();
		msgLb=new JLabel("----------");
		centerPanel.add(msgLb);
		
		JPanel southPanel=new JPanel();
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
	}

	void display(String msg)
	{
		msgLb.setText(msg);
		centerPanel.revalidate();
		this.setBounds(900,200,400,100);
		this.setVisible(true);
		
	}
	
	void beep()
	{
		while(true)
		{
			try
			{
				Thread.sleep(5000);
				System.out.print("\007");
				System.out.flush();
			}
			catch(Exception e){}
		}
	}
	public static void main(String args[])
	{
		Alert a=new Alert();
		a.display("this is vikrant sharma");
	}

}
