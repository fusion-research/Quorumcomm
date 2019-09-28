package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class ThreadControlDialog extends JDialog implements ActionListener,ItemListener
{
	JCheckBox snCommThreadCb, nodeEnergyCb;
	Base base;
	
	JButton saveBt, cancelBt;
	ThreadControlDialog(Base b)
	{
		base=b;
		this.setLayout(new BorderLayout());
		JPanel northPanel=new JPanel();
		northPanel.setBackground(Color.WHITE);
		
		
		JPanel centerPanel=new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(null);
		snCommThreadCb=new JCheckBox("sensor node thread");
		snCommThreadCb.setBackground(Color.WHITE);
		snCommThreadCb.addItemListener(this);
		snCommThreadCb.setSelected(true);
		snCommThreadCb.setBounds(10,0,200,20);
		
		nodeEnergyCb=new JCheckBox("node energy");
		nodeEnergyCb.setBackground(Color.WHITE);
		nodeEnergyCb.addItemListener(this);
		nodeEnergyCb.setBounds(10,30,200,20);
		
		centerPanel.add(snCommThreadCb);
		centerPanel.add(nodeEnergyCb);
		
		
		JPanel southPanel=new JPanel();
		
		saveBt=new JButton("save");
		saveBt.addActionListener(this);
		
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);
		
		southPanel.add(saveBt);
		southPanel.add(cancelBt);
		
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==cancelBt)
		{
			dispose();
		}
		
		if(ae.getSource()==saveBt)
		{
			System.out.println("save button have been pressed");
		}
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==snCommThreadCb)
		{
			if(snCommThreadCb.isSelected())
			{
				base.signalHouse.snCommThreadFlag=true;
			}
			else if(ie.getSource()==snCommThreadCb)
			{
				base.signalHouse.snCommThreadFlag=false;				
			}
		}
		
		
		if(ie.getSource()==nodeEnergyCb)
		{
			if(nodeEnergyCb.isSelected())
			{
				//System.exit(0);
			}
			else if(nodeEnergyCb.isSelected()==false)
			{
				//System.exit(0);
			}
			
		}
	}
	
	public static void main(String args[])
	{
		ThreadControlDialog tc=new ThreadControlDialog(null);
		tc.setSize(400,500);
		tc.setVisible(true);
		
	}
}
