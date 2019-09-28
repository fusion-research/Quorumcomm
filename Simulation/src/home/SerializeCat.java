package home;
import java.io.*;

public class SerializeCat
{
	public static void main(String args[])
	{
		/*Cat c=new Cat();
		c.name="ricky";
		try
		{
			FileOutputStream fs=new FileOutputStream("testSer.ser");
			ObjectOutputStream os=new ObjectOutputStream(fs);
			os.writeObject(c);
			os.close();
		}
		catch(Exception e){e.printStackTrace();}
		*/
		try
		{
			FileInputStream fis=new FileInputStream("testSer.ser");
			ObjectInputStream ois=new ObjectInputStream(fis);
			Cat v=(Cat)ois.readObject();
			ois.close();
			System.out.println(v.name);
		}
		catch(Exception e){e.printStackTrace();}
	}
}

class Cat implements Serializable{String name;}