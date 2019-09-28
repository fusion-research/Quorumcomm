package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class VEventDialog extends JDialog implements ActionListener
{
	JLabel eventTypeLb,eventModeLb,eventRepetationLb;
	JComboBox eventTypeCb,eventModeCb,eventRepetationCb;
	JButton okBt,cancelBt;
	String eventTypeStArr[]={"any","forest fire","intruder"};
	String eventModeStArr[]={"random","fixed"};
	String repetationStArr[]={"infinite","single"};
	
	Base base;
	VEventDialog(Base b)
	{
		super(b, "select event", true);
		base=b;
		this.setLayout(new BorderLayout());
		JPanel northPanel=new JPanel();
		
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		
		eventTypeLb=new JLabel("event type");				eventTypeCb=new JComboBox(eventTypeStArr);
		eventTypeLb.setBounds(10,10,120,20);				eventTypeCb.setBounds(150,10,120,20);
		
		eventModeLb=new JLabel("event mode");				eventModeCb=new JComboBox(eventModeStArr);
		eventModeLb.setBounds(10,40,120,20);				eventModeCb.setBounds(150,40,120,20);
		
		eventRepetationLb=new JLabel("repetation");			eventRepetationCb=new JComboBox(repetationStArr);
		eventRepetationLb.setBounds(10,70,120,20); 			eventRepetationCb.setBounds(150,70,120,20);
		
		centerPanel.add(eventTypeLb);						centerPanel.add(eventTypeCb);
		centerPanel.add(eventModeLb);						centerPanel.add(eventModeCb);
		centerPanel.add(eventRepetationLb);					centerPanel.add(eventRepetationCb);
		
		
		
		//----------------------------------------------------------
		JPanel southPanel=new JPanel();
		okBt=new JButton("ok");
		okBt.addActionListener(this);
		
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);

		southPanel.add(okBt);
		southPanel.add(cancelBt);
		
		this.add("North",northPanel);
		this.add("Center",centerPanel);
		this.add("South",southPanel);
		
		
		
	}
	//******************IMPLEMENTING THE ACTION EVENTS***************
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==okBt)
		{
			String eventTypeSt=eventTypeCb.getSelectedItem().toString();
			String eventModeSt=eventModeCb.getSelectedItem().toString();
			String eventRepetationSt=eventRepetationCb.getSelectedItem().toString();
			if(eventTypeSt.equals("any") && eventModeSt.equals("random") && eventRepetationSt.equals("infinite"))

			{
				Runnable r=new Runnable()
				{
					public void run()
					{
						base.shouldGenerateEvents=true;
						base.centerPanel.generateRandomEvents(base.centerPanel,500,500,10000);
					}
				};
				new Thread(r).start();
			}
			dispose();
		}
		if(ae.getSource()==cancelBt)
		{
			dispose();
		}
			
	}
	//---------------------------------------------------------------
	public static void main(String args[])
	{
		System.out.println("this is vikrant sharma");
		VEventDialog ved=new VEventDialog(null);
		ved.setSize(400,500);
		ved.setVisible(true);
	}

}
