package home;

import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VFileManager extends JDialog implements ActionListener
{
	JLabel nameLb;
	JTextField nameTf;
	JButton saveBt;
	JButton cancelBt;
	Base base;
	String fileData;
	public VFileManager(String dataSt, Base b)
	{
		base=b;
		fileData=dataSt;
		this.setLayout(new BorderLayout());
		
		JPanel northPanel=new JPanel();
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		nameLb=new JLabel("File name");
		nameTf=new JTextField();
		nameLb.setBounds(10,20,80,20);
		nameTf.setBounds(120,20,150,20);
		centerPanel.add(nameLb);
		centerPanel.add(nameTf);
		
		JPanel southPanel=new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		saveBt=new JButton("save");
		saveBt.addActionListener(this);
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);
		
		southPanel.add(saveBt);
		southPanel.add(cancelBt);
		
		add("North", northPanel);
		add("Center", centerPanel);
		add("South",southPanel);
		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==cancelBt)
		{
			this.dispose();
		}
		
		if(ae.getSource()==saveBt)
		{
			String nameSt=nameTf.getText();
			if(nameSt=="")
			{
				base.alert.display("Enter valid file name");
			}
			else
			{
				savetoFile(nameSt+".m",fileData);
				this.dispose();
			}
		}
	}
	
	void savetoFile(String fileName,String scriptSt)
	{
		   
		   try{
		      byte bWrite [] =scriptSt.getBytes();
		      OutputStream os = new FileOutputStream("results/"+fileName);
		      for(int x=0; x < bWrite.length ; x++)
		      {
		         os.write( bWrite[x] ); // writes the bytes
		      }		
		      os.close();
		   }catch(Exception e){base.alert.display(e+"");}
	}
	
	public static void main(String...args)
	{
		System.out.println("the name of the author is vikrant sharma");
		VFileManager vfm=new VFileManager(null,null);
		vfm.setBounds(400,400,300,150);
		vfm.setVisible(true);
		
	}

}