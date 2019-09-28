package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StopWatch extends JDialog implements ActionListener
{
	JLabel stopWatchLb;
	JButton startBt;
	
	StopWatch()
	{
		JPanel centerPanel=new JPanel();
		stopWatchLb=new JLabel("00");
		startBt=new JButton("start");
		startBt.addActionListener(this);
		centerPanel.add(stopWatchLb);
		centerPanel.add(startBt);
		add(centerPanel);
	}
	
	public static void main(String atrgs[])
	{
		
		StopWatch sw=new StopWatch();
		sw.setSize(200,200);
		sw.setVisible(true);

	}
	
	//****************************STARTING THE ACTION EVENT ********************
	public void actionPerformed(ActionEvent ae)
	{
		int i=0;
		if(ae.getSource()==startBt)
		{
			System.out.println("start button have been pressed");
			for(;;)
			{
				try
				{
					Thread.sleep(10);
					System.out.println(i);
					stopWatchLb.setText(Integer.toString(i));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				i++;
			}
		}
			
	}
	//--------------------------------------------------------------------------

}
