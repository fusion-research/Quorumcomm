package home;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class RandomEvent extends JLabel
{
	RandomEvent thisLb;
	Base base;
	VToolBox toolBox;
	RandomEvent(Base b,final JPanel panel,final int counter)
	{
		base=b;
		thisLb=this;
		toolBox=new VToolBox(base);
		Runnable r=new Runnable()
		{
			int c=counter;
			public void run()
			{
				for(int i=0;i<base.masterNodeList.size();i++)
				{
					int x=thisLb.getX();
					int y=thisLb.getY();
					Location eventLoc=new Location(x,y,base);
					Location nodeLoc=base.masterNodeList.get(i).getLoc();
					float eventDist=toolBox.getDist(eventLoc,nodeLoc);
					if(eventDist<=base.masterNodeList.get(i).sensingRange)
					{
//						System.out.println(" VVVVVVVVVVVVVVVVVVV "+base.masterNodeList.get(i).uniqueId+ " DISTANCE :"+ eventDist );
						//base.alert.display(" NODE ID:  "+base.masterNodeList.get(i).uniqueId+ " DISTANCE :"+ eventDist);
						base.masterNodeList.get(i).setBackground(Color.DARK_GRAY);
						base.vBaseOptionDialog.getTemp(base.masterBSList.get(0),i);
					}
					
				}
				
				while(c>0)
				{
					c=c-1;
					try{Thread.sleep(10);}catch(Exception e){}
				}
				if(c<=0)
				{
					panel.remove(thisLb);
					panel.revalidate();
				}
			}
		};
		new Thread(r).start();
	}

}
