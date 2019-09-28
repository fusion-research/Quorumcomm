package home;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ShowGraphDataDialog extends JDialog implements ActionListener
{
	Base base;
	JComboBox graphArrayCb;
	JButton showBt, normalizeBt;
	JButton cancelBt,saveBt;
	JTextPane showTp;
	JScrollPane jsp;
	ShowGraphDataDialog(Base b)
	{
		base=b;
		setLayout(new BorderLayout());
		JPanel northPanel=new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		graphArrayCb=new JComboBox();
		graphArrayCb.addItem("int graph array 1");
		graphArrayCb.addItem("3-D Trajectory");
		showBt=new JButton("show");
		showBt.addActionListener(this);
		normalizeBt=new JButton("normalize");
		normalizeBt.addActionListener(this);
		northPanel.add(graphArrayCb);
		northPanel.add(showBt);
		northPanel.add(normalizeBt);
		JPanel centerPanel=new JPanel();
		showTp=new JTextPane();
		centerPanel.setLayout(null);
		jsp=new JScrollPane(showTp);
		jsp.setBounds(5, 0,575,500);
		centerPanel.add(jsp);
	
		
		JPanel southPanel=new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);
		
		saveBt=new JButton("save");
		saveBt.addActionListener(this);
		southPanel.add(cancelBt);
		southPanel.add(saveBt);
		
		add("North",northPanel);
		add("Center",centerPanel);
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
			String tempSt=showTp.getText();
			VFileManager vfm=new VFileManager(tempSt,base);
			vfm.setBounds(300,300,300,150);
			vfm.setVisible(true);
		}
		
		if(ae.getSource()==normalizeBt)
		{
			if(graphArrayCb.getSelectedItem()=="int graph array 1")
			{
				String tempSt="";
				//for(int k=0;k<2;k++)
				for(int i=0;i<(1000-2);i++)
				{
					base.intGraphArray1[i]=((base.intGraphArray1[i]+base.intGraphArray1[i+1])/2);
					//tempSt=tempSt+"\n"+((base.intGraphArray1[i]+base.intGraphArray1[i+1])/2)+";";
				}
				for(int i=0;i<1000;i++)
				{					
					tempSt=tempSt+"\n"+base.intGraphArray1[i]+";";
				}
				showTp.setText(tempSt);
			}
		}
		if(ae.getSource()==showBt)
		{
			if(graphArrayCb.getSelectedItem()=="int graph array 1")
			{
				String tempSt="";
				for(int i=0;i<1000;i++)
				{
					tempSt=tempSt+"\n"+base.intGraphArray1[i]+";";
				}
				showTp.setText(tempSt);
			}
			
			if(graphArrayCb.getSelectedItem()=="3-D Trajectory")
			{
				String tempStx="x=[";
				String tempSty="y=[";
				String tempStz="z=[";
				{
					for(int i=0;i<base.nodeTrackList.size();i++)
					{
						VCoordinate tempCoordinate=base.nodeTrackList.get(i);
						tempStx=tempStx+" "+(tempCoordinate.x-100);
						tempSty=tempSty+" "+(tempCoordinate.y-100);
						tempStz=tempStz+" "+tempCoordinate.z;
					}
					tempStx=tempStx+" ];";
					tempSty=tempSty+" ];";
					tempStz=tempStz+" ];";
					
				String plotCodeSt=" x1=[370,370]; \n y1=[370,370]; \n z1=[0,0]; \n plot3(y,x,z,'-',x1,y1,z1,'.'); \n xlabel('Length (metres)'); \n ylabel('Breadth (metres)'); \n zlabel('Altitude (metres)'); \n";
				String graphCodeSt=tempStx+" \n"+tempSty+" \n"+tempStz+" \n"+plotCodeSt;
				showTp.setText(graphCodeSt);
				
				}
			}
		}
	}
	
	public static void main(String args[])
	{
		
		ShowGraphDataDialog sgdd=new ShowGraphDataDialog( null);
		sgdd.setBounds(200,200,600,500);
		sgdd.setVisible(true);
	}

}
