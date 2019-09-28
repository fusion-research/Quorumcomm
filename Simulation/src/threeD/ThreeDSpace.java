package threeD;
import javax.swing.*;
import java.awt.*;

public class ThreeDSpace extends JFrame 
{

	/**
	 * @param args
	 */
	ThreeDSpace()
	{
		setLayout(new BorderLayout());
		
		
		JPanel northPanel=new JPanel();
		
		JPanel centerPanel=new JPanel();
		centerPanel.setBackground(Color.WHITE);	
		
		
		JPanel southPanel=new JPanel();
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
		
	}
	public static void main(String[] args) 
	{
		System.out.println("the name of auhtor is vikrant sharma");
		ThreeDSpace tds=new ThreeDSpace();
		tds.setSize(1200,700);
		tds.setVisible(true);
	}

}
