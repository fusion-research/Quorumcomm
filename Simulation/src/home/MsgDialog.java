package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class MsgDialog extends JDialog implements ActionListener
{
	JButton okBt;
	JLabel msgLb;
	Base base;
	MsgDialog(String msg,Base b)
	{
		super(b, "message", true);
		base=b;
		JPanel northPanel=new JPanel();
		
		JPanel southPanel=new JPanel();
		okBt=new JButton("ok");
		okBt.addActionListener(this);
		southPanel.add(okBt);
		
		JPanel centerPanel=new JPanel();
		msgLb=new JLabel(msg);
		centerPanel.add(msgLb);
		
		add("South",southPanel);
		add("North",northPanel);
		add("Center",centerPanel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==okBt)
		{
			System.out.println("ok button have been pressed");
			dispose();
		}
	}
	
	public static void main(String args[])
	{
		MsgDialog md=new MsgDialog("error message",null);
		md.setSize(300,150);
		md.setVisible(true);
	}
}
