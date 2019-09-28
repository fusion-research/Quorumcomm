package home;
import  java.io.*;  
import  java.sql.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;  

public class CreateExcelFile{
    public static void main(String[]args){
try{
String filename="c:/data.xls" ;
HSSFWorkbook hwb=new HSSFWorkbook();
HSSFSheet sheet =  hwb.createSheet("new sheet");

HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("technique");
rowhead.createCell((short) 1).setCellValue("nos");
rowhead.createCell((short) 2).setCellValue("avgmov");


Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simulink", "root", "abc");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("Select * from nostoavgmovbyothers");
int i=1;
while(rs.next()){
HSSFRow row=   sheet.createRow((short)i);
row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("technique")));
row.createCell((short) 1).setCellValue(rs.getInt("nos"));
row.createCell((short) 2).setCellValue(rs.getInt("avgmov"));
i++;
}
FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("Your excel file has been generated!");

} catch ( Exception ex ) {
    System.out.println(ex);

}
    }
}