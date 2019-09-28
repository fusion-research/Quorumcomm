package home;
import java.io.*;

public class Test13{

   public static void main(String args[]){
   String st="the vikrant sharma";
   try{
      byte bWrite [] =st.getBytes();
      OutputStream os = new FileOutputStream("d:/test.txt");
      for(int x=0; x < bWrite.length ; x++){
         os.write( bWrite[x] ); // writes the bytes
      }
      os.close();
     
      InputStream is = new FileInputStream("d:/test.txt");
      int size = is.available();

      for(int i=0; i< size; i++){
         System.out.print((char)is.read() + "  ");
      }
      is.close();
   }catch(IOException e){
      System.out.print("Exception "+ e);
   }	
   }
}