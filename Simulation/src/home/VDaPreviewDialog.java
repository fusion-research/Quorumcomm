package home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class VDaPreviewDialog extends JDialog
{
	Base base;
	
	JLabel coveredLb;
	JLabel obstacleLb;
	JLabel affectedLb;
	JLabel locationLb;
	JLabel subDivisionNumberLb;
	JLabel areaOfInterestLb;
	JLabel inTunnelLb;
	JLabel lengthLb;
	int xLoc;
	int yLoc;
	VToolBox toolBox;
	
	
	public VDaPreviewDialog(Location loc,Base b)
	{
		super(b);
		base=b;
		xLoc=loc.getXLoc();
		yLoc=loc.getYLoc();
		
		toolBox=new VToolBox(base);
		VCoordinate tempCoordinate=null;
		
		System.out.println("x: "+xLoc +"y: "+yLoc);
		//System.exit(0);
		tempCoordinate=base.centerPanel.coordinateMat[yLoc][xLoc];
		
		setLayout(new BorderLayout());
		
		
		base=b;
		
		JPanel northPanel=new JPanel();
		
		JPanel centerPanel=new JPanel();
		centerPanel.setLayout(new GridLayout(10,1));
		/*try
		{*/
			coveredLb=new JLabel("cevered : "+ tempCoordinate.covered);
			obstacleLb=new JLabel("obstacle : "+ tempCoordinate.obstacle);
			affectedLb=new JLabel("affacted : "+tempCoordinate.affected);
			base.alert.display("x: "+xLoc+ "y :"+ yLoc );
			locationLb=new JLabel("location : ( "+tempCoordinate.location.getXLoc()+", "+tempCoordinate.location.getYLoc()+" )");
			subDivisionNumberLb=new JLabel("sub division number :"+tempCoordinate.subDivisionNumber);
			areaOfInterestLb=new JLabel("area of interest : "+tempCoordinate.areaOfInterest);
			boolean inTunnel=toolBox.checkForPath(xLoc,yLoc,base.candidateLoc.getXLoc(),base.candidateLoc.getYLoc(),(int)base.px1,(int)base.py1);
			inTunnelLb=new JLabel("in tunnel : "+inTunnel);
			

		
		
		centerPanel.add(coveredLb);
		centerPanel.add(coveredLb);
		centerPanel.add(affectedLb);
		centerPanel.add(locationLb);
		centerPanel.add(subDivisionNumberLb);
		centerPanel.add(areaOfInterestLb);
		centerPanel.add(inTunnelLb);
		/*}
		catch(Exception e){}*/
		
		
		JPanel southPanel=new JPanel();
		
		add("North",northPanel);
		add("Center",centerPanel);
		add("South",southPanel);
		
	}	
}