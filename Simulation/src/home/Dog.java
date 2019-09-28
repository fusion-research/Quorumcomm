package home;
import java.util.*;
public class Dog implements Comparator<Dog>
{
	String name;
	int age;
	
	Dog()
	{}
	Dog(String name,int age)
	{
		this.name=name;
		this.age=age;
	}
	public static void main(String args[])
	{
		ArrayList <Dog>al=new ArrayList<Dog>();
		Dog d1=new Dog("a",10);
		Dog d2=new Dog("b",9);
		Dog d3=new Dog("c",8);
		Dog d4=new Dog("d",7);
		
		al.add(d1);
		al.add(d2);
		al.add(d3);
		al.add(d4);
		
		System.out.println(al);
		
		Collections.sort(al,new Dog());
		
		System.out.println(al);
		
		System.out.println(al.get(0).age);
		
	}
	 public int compare(Dog obj1,Dog obj2) {
		  Integer p1 = obj1.age;
		  Integer p2 = obj2.age;

		  if (p1 > p2 ){
		   return 1;
		  }
		  else if (p1 < p2){
		   return -1;
		  }
		  else
		   return 0;
		 }

}
