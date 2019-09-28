package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
public class SaveFigAsDialog extends JDialog implements ActionListener 
{
	JButton saveBt,cancelBt;
	JTextField nameTf;
	JLabel nameLb;
	Base base;
	VFigEditorDialog vFigEditorDialog;
	SaveFigAsDialog(Base b,VFigEditorDialog vFigEditorDialog)
	{
		super(vFigEditorDialog,"save as",false);
		base=b;
		this.setLayout(new BorderLayout());
		this.vFigEditorDialog=vFigEditorDialog;
		base=vFigEditorDialog.base;
		
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(null);
		nameLb=new JLabel("file name ");
		nameLb.setBounds(10,20,80,20);
		nameTf=new JTextField("");
		nameTf.setBounds(100,20,150,20);
		centerPanel.add(nameLb);
		centerPanel.add(nameTf);
		
		//--------------------------------------
		JPanel southPanel=new JPanel();
		saveBt=new JButton("save");
		saveBt.addActionListener(this);
		cancelBt=new JButton("cancel");
		cancelBt.addActionListener(this);
		southPanel.add(saveBt);
		southPanel.add(cancelBt);

		
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
			
			String fileName=nameTf.getText();
			String st=vFigEditorDialog.editorPa.getText();
			System.out.println(st);
			if(!fileName.isEmpty())
			{
				vFigEditorDialog.saveFig(fileName+".vf",st);
				dispose();
				//-----------------------------------------
			}
			else
			{
				base.alert.display("empty file name");
			}
			
		}
	}
	public static void main(String args[])
	{
		SaveAsDialog sd=new SaveAsDialog(null,null);
		sd.setSize(300,150);
		sd.setVisible(true);
	}

}

