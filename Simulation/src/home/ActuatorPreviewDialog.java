package home;
import java.awt.*;
import javax.swing.*;
public class ActuatorPreviewDialog extends JDialog 
{
	Base base;
	public ActuatorPreviewDialog(Actuator actuator,Base b)
	{
		super(b, "actuator preview", true);
		this.base=b;	
		//setSize(600,500);
		setLayout(new GridLayout(11,0));
		JLabel uniqueIdLb=new JLabel("node id:  "+Integer.toString(actuator.uniqueId));
		JLabel posLb=new JLabel("actuator position:  "+Integer.toString(actuator.getX())+","+ Integer.toString(actuator.getY()));
		JLabel powerLb=new JLabel("power status:  "+actuator.power);
		
		JLabel directlyConnectedNodesLb=new JLabel("connected directly nodes : "+actuator.nodesDirectlyConnectedActuator.size());
		String dcnSt="";
		for(int i=0;i<actuator.nodesDirectlyConnectedActuator.size();i++)
		{
			dcnSt=dcnSt+",  "+Integer.toString(actuator.nodesDirectlyConnectedActuator.get(i).uniqueId);
		}
		
		JLabel directlyConnectedNodes1Lb=new JLabel("directly connected nodes: "+dcnSt);
		JLabel actuatorMovementLb=new JLabel("movement:    "+ actuator.distMoved);

		
		
		
		add(uniqueIdLb);
		add(posLb);
		add(powerLb);
		add(directlyConnectedNodesLb);
		add(directlyConnectedNodes1Lb);
		add(actuatorMovementLb);
		

		//setVisible(true);
		
		
		
	}
	
}
	