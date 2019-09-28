package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class VPacketEditorDialog extends JDialog implements ActionListener 
{
	Base base;
	
	JButton cancelBt;
	VPacketEditorDialog(Base b)
	{
		base=b;
		JPanel northPanel=new JPanel();
		
		JPanel centerPanel=new JPanel();
		
		JPanel southPanel=new JPanel();
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);
		
		add("Center",centerPanel);
		add("North",northPanel);
		add("South",southPanel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==cancelBt)
		{
			dispose();
		}
	}

}
