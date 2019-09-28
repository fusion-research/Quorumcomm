package home;
import java.util.*;
class Test2
{
	public static void main(String athe[])
	{
	 Stack<Integer> stack=new Stack<Integer>();
	 stack.push(10);
	 stack.push(20);
	 stack.push(30);
	 stack.push(40);
	 stack.push(50);
	 stack.push(60);
	 stack.push(70);
	 stack.push(80);
	 stack.push(90);
	 stack.push(100);
	 Stack <Integer>s2=(Stack<Integer>)(stack.clone());
	 System.out.println(stack);
	 System.out.println(stack.pop());
	 System.out.println(stack);
	 System.out.println(s2);
	 System.out.println(s2.size());
	 
	 
	 
	}
}