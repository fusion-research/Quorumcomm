package home;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.*;
public class Joint

{
	 
	public Connection conn;
	Statement smt;
	public String url;
	public String pwd;
	public String db_name;
	//creating a static block 
	public Base base;
	
{
url="jdbc:mysql://localhost:3306/";	
db_name="simulink";
pwd="abc";
System.out.println(url);
}
	//=======================
	
	//making the conncection with the databsae
	public Joint(Base b)
	{
		base=b;
		System.out.println("starting the joint interface");
		try
		{
			System.out.println("creating the connection");
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("connect");
		conn=DriverManager.getConnection(url+db_name,"root",pwd);
		
		}catch(SQLException ex){ex.printStackTrace();
		//JOptionPane.showMessageDialog(null,"UNREACHABLE DATABASE");
		}
		catch(ClassNotFoundException ex1){
			System.out.println("class not found "+ex1);
			JOptionPane.showMessageDialog(null,ex1);	
		}
		
	}
	//==============================================
		public Joint(String db_name)
	{
		this.db_name=db_name;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection(url+db_name,"root",pwd);
		}catch(SQLException ex){ex.printStackTrace();}
		catch(ClassNotFoundException ex1){}
		
	}
	//===============================================
		//==============================================
		public Joint(String db_name,String pwd)
	{
		this.db_name=db_name;
		this.pwd=pwd;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection(url+db_name,"root",pwd);
		}catch(SQLException ex){ex.printStackTrace();}
		catch(ClassNotFoundException ex1){}
		
	}
	//===============================================
	
	
	
	public String getIp()
{
	String ip=null;
	try
	{
	File file=new File("meta/ip.txt");
	FileReader fr=new FileReader(file);
	BufferedReader bf=new BufferedReader(fr);
	ip=bf.readLine();
	System.out.println(ip);
	}catch(IOException e){e.printStackTrace();}
	return ip;
}

//============defining the interfaces=====================
public void fix(String db_name,String uname,String pwd,String q)
	{
	try
	{
		
	smt=conn.createStatement();  
	smt.executeUpdate(q);
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	}
//========================================================
	//============defining the interfaces=====================
public void fix(String pwd,String q)
	{
	try
	{
		
	smt=conn.createStatement();
	smt.executeUpdate(q);
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	}
//========================================================
		//============defining the interfaces=====================
public void fix(String q)
	{
	try
	{
		
	smt=conn.createStatement();
	smt.executeUpdate(q);
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	}
//========================================================
			//============defining the interfaces=====================
public void fixEx(String q)throws SQLException 
	{
	try
	{
		
	smt=conn.createStatement();
	smt.executeUpdate(q);
	}
	catch(SQLException e)
	{
		e.printStackTrace();
		throw e;
	}
	}
//========================================================
	//=======================defining second interface============
public String[]fix(String q,int[]attrib)
	{
		String data[]=new String[2000];
		int i=1;
		try{
			smt=conn.createStatement();
	ResultSet rs=smt.executeQuery(q);
	while(rs.next())
	{
	for(int j:attrib)
		data[i++]=rs.getString(j);
	}
	}catch(Exception e){e.printStackTrace();}
		data[0]=Integer.toString(i);
		return data;
	}

	//============================================================
		//=======================defining second interface============
public String[]fixEx(String q,int[]attrib)throws SQLException 
	{
		String data[]=new String[2000];
		int i=1;
		try{
			smt=conn.createStatement();
	ResultSet rs=smt.executeQuery(q);
	while(rs.next())
	{
	for(int j:attrib)
		data[i++]=rs.getString(j);
	}
	}catch(SQLException e){e.printStackTrace();throw e;}
		data[0]=Integer.toString(i);
		return data;
	}

	//============================================================
	
		//=======================defining second interface============
public String[]fix(String db_name,String uname,String pwd,String q,int[]attrib)
{
	String data[]=new String[2000];
	int i=1;
	try{
		smt=conn.createStatement();
ResultSet rs=smt.executeQuery(q);
while(rs.next())
{
	for(int j:attrib)
	data[i++]=rs.getString(j);
}
}catch(SQLException e){e.printStackTrace();}
	data[0]=Integer.toString(i);
	return data;
}

	//===================================================================
	//=============================DEFINING THE THIRD INTERFACE==========
			//=======================defining second interface============
public String[]fix(String q,int att)
	{
		String data[]=new String[2000];
		int i=1;
		try{
			smt=conn.createStatement();
	ResultSet rs=smt.executeQuery(q);
	while(rs.next())
	{
	for(int j=1;j<=att;j++)
		data[i++]=rs.getString(j);
	}
	}catch(Exception e){e.printStackTrace();}
		data[0]=Integer.toString(i);
		return data;
	}

	//============================================================
//===========================function for handling the string type input from the user===============

public String[]fixEx(String q,String []attrib)throws SQLException 
{
	String data[]=new String[2000];
	int i=1;
	try{
		smt=conn.createStatement();
ResultSet rs=smt.executeQuery(q);
while(rs.next())
{
for(String j:attrib)
	data[i++]=rs.getString(j);
}
}catch(SQLException e){e.printStackTrace();throw e;}
	data[0]=Integer.toString(i);
	return data;
}

//====================================================================================================

//===========================function for handling the string type input from the user===============

public String[]fix(String q,String []attrib)
{
	String data[]=new String[2000];
	int i=1;
	try{
		smt=conn.createStatement();
ResultSet rs=smt.executeQuery(q);
while(rs.next())
{
for(String j:attrib)
	data[i++]=rs.getString(j);
}
}catch(SQLException e){e.printStackTrace();}
	data[0]=Integer.toString(i);
	return data;
}

//====================================================================================================

//===========================function for handling the string type input from the user===============

public String getDataEx(String q)throws SQLException 
{
	String data;
	try{
		smt=conn.createStatement();
ResultSet rs=smt.executeQuery(q);
rs.next();
data=rs.getString(1);
}catch(SQLException e){e.printStackTrace();throw e;}
	//data[0]=Integer.toString(i);
	return data;
}

//====================================================================================================

//===========================function for handling the string type input from the user===============

public void setDataToTable(DefaultTableModel model,String q,int upto)throws SQLException 
{
	try
	{
		smt=conn.createStatement();
		ResultSet rs=smt.executeQuery(q);
		while(rs.next())
		{
			Vector <String>data=new Vector<String>();
			for(int i=1;i<=upto;i++)
			{
				data.add(rs.getString(i));
			}
			model.addRow(data);
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();throw e;
	}
	//data[0]=Integer.toString(i);
	//return data;
}

//====================================================================================================


public void updateDataToTable(DefaultTableModel model,String q,int upto)throws SQLException 
{
	int rowCount=model.getRowCount();
	for(int i=rowCount-1;i>=0;i--)
	{
		model.removeRow(i);
	}
	try
	{
		smt=conn.createStatement();
		ResultSet rs=smt.executeQuery(q);
		while(rs.next())
		{
			Vector <String>data=new Vector<String>();
			for(int i=1;i<=upto;i++)
			{
				data.add(rs.getString(i));
			}
			model.addRow(data);
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();throw e;
	}
	//data[0]=Integer.toString(i);
	//return data;
}

//====================================================================================================

//===============FUNCTION TO GET VALUE FROM DATABASE IN ARRAY LIST===============

public void setDataToArrayList(ArrayList <String>data,String q,int upto)throws SQLException 
{
	try
	{
		data.removeAll(data);
		smt=conn.createStatement();
		ResultSet rs=smt.executeQuery(q);
		while(rs.next())
		{
			
			for(int i=1;i<=upto;i++)
			{
				data.add(rs.getString(i));
			}
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();throw e;
	}
	//data[0]=Integer.toString(i);
	//return data;
}
//-------------------------------------------------------------------------------------------------

//===============FUNCTION TO GET VALUE FROM DATABASE IN ARRAY LIST===============

public ResultSet getDataByResultSet(String query)throws SQLException 
{
	ResultSet rs;
	try
	{
		smt=conn.createStatement();
		rs=smt.executeQuery(query);
	}
	catch(SQLException e)
	{
		e.printStackTrace();throw e;
	}
	//data[0]=Integer.toString(i);
	return rs;
}
//-------------------------------------------------------------------------------------------------

//=================FUNCTION TO PUT DATA INTO THE DATABASE============================================
public void putData(String q)throws SQLException 
{
try
{
	
smt=conn.createStatement();
smt.executeUpdate(q);
}
catch(SQLException e)
{
	e.printStackTrace();
	throw e;
}
}
//===================================================================================================

//=================FUNCTION TO EXECUTE QUERIES FORM ARRAY LIST=======================================
public void executeArrayListQueries(ArrayList <String> q)throws SQLException 
{
try
{
	
smt=conn.createStatement();
for(int i=0;i<q.size();i++)
{
	smt.executeUpdate(q.get(i));
}
}
catch(SQLException e)
{
	e.printStackTrace();
	throw e;
}
}
//===================================================================================================

//**********************FUNCTION TO FIND DATA FROM THE DATA BASE*************************************
public boolean findInfoEx(String data,String fieldName,String tableName)throws SQLException
{
    String q;
    boolean flag=false;
    q="select * from "+tableName+" where "+fieldName+"='"+data+"'";
    System.out.println("???/"+q);
	try{
		smt=conn.createStatement();
ResultSet rs=smt.executeQuery(q);
flag=rs.next();
}catch(SQLException e){e.printStackTrace();throw e;}
	//data[0]=Integer.toString(i);
return flag;
}

//---------------------------------------------------------------------------------------------------





public static void main(String...args)
{
	System.out.println("starting the main method ");
	Joint js=new Joint(new Base());
	System.out.println(js.getIp());
	int i[]={1,2};
	System.out.println(js);
	try
	{
		String a=js.getDataEx("select * from tester");
		System.out.println(a);
	}
	catch(SQLException se){}
}
}