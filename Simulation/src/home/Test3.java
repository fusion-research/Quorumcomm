package home;

public class Test3 {


	Test3()
	{
		Runnable r=new Runnable()
		{
			public void run()
			{synchronized(this)
				{
				for(int i=1;i<10;i++)
				{
					System.out.println(i+Thread.currentThread().getName());
					try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
				}
				}
			}
		};
		Thread t1=new Thread(r,"one");
		Thread t2=new Thread(r,"two");
		t1.start();
		t2.start();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test3 s=new Test3();

	}

}
