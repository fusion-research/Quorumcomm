package home;
import java.util.*;

class Test10 
{
	public static void main (String args [] )
	{
		//Base base=new Base();
		System.out.println("starting the program");
		 Random randX=new Random();
		 Random randY=new Random();
		 Random randI=new Random();
		 
		//int x=Integer.parseInt(base.dxTf.getText());
		//int y=Integer.parseInt(base.dyTf.getText());
		
		int x=500;
		int y=500;
		int randomInterval=0;
		 
		 while(true)
		 {
			 try
			 {	
				 int tempX=randX.nextInt(x);
				 int tempY=randY.nextInt(y);
				 randomInterval=randI.nextInt(10000);
				 
				 System.out.println("X = "+tempX);
				 System.out.println("Y = "+tempY);
				 System.out.println("Interval = "+randomInterval);
				 
				 try{Thread.sleep(randomInterval);}catch(Exception e){}

				 
				// System.out.printn(tempX);
				 //tempY;
				 
				// System.out.println(xArray[i]);
				// base.nodeArray[i].defaultBufferSize=Integer.parseInt(bufferSizeSp.getValue().toString());
				 //System.out.println(base.nodeArray[i].uniqueId);
				 
			 }
			 catch(Exception e)
			 {
				 MsgDialog md=new MsgDialog("deployment area not set",null);
				 md.setBounds(300,300,300,150);
				 md.setVisible(true);
				
				 break;
			 }
		 }
	}
}