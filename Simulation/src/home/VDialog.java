package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class VDialog extends JDialog implements ActionListener
{
	JButton okBt,cancelBt;
	JPanel centerPanel,southPanel,northPanel;
	Base base;
	VDialog(Base b,String title)
	{
		super(b,title, true);
		this.base=b;
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/titlelogo.png"));
		this.setLayout(new BorderLayout());
		
		northPanel=new JPanel();
		
		centerPanel=new JPanel();
		
		southPanel=new JPanel();
		okBt=new JButton("ok");
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);
		southPanel.add(okBt);
		southPanel.add(cancelBt);

		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);	
	}
	public void actionPerformed(ActionEvent ae){}
}
