/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atcnAssignment2;

/**
 *
 * @author jeyabalaji
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;  
public class Bitstuffing {

    /**
     * @param args the command line arguments
     */

    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
    int count = 0;
    String zero = "0";
    String num = "1";
    String Org_binary="";
    
    //Converting the text from file to binsry according to the encoding standard
    byte[] inputFromFile = Files.readAllBytes(Paths.get("/Users/jeyabalaji/Desktop/io.txt"));
			StringBuilder binaryString = new StringBuilder();
		    for (byte b : inputFromFile)
		    {
		       int val = b;
		       for (int i = 0; i < 8; i++)
		       {
		          binaryString.append((val & 128) == 0 ? 0 : 1);
		          val <<= 1;
		       }
		    }
		    Org_binary = binaryString.toString();
    System.out.println();
    System.out.println("Text to binary stream:");
    System.out.println(Org_binary);
    System.out.println();
    System.out.println("Start of bit stuffing");
    
    String[] binaray = Org_binary.split("");
  //creating an ArrayList to store the data bit by bit
    ArrayList<String> binlist = new ArrayList<String>(100000);
    Collections.addAll(binlist,binaray);
    
    
    for(int i=0; i<binlist.size(); i++)
    {

     if(binlist.get(i).equals(num)){  
        count = count+1;
        if(count == 5){//when the number of 1's in the stream reaches 5
            System.out.println(binlist.get(i));
            i = i+1; 
            System.out.println("Start of adding zero");
            binlist.add(i,zero); //adding a zero
            System.out.println(binlist.get(i));
            System.out.println("Added zero");
        }
 }
 
 else {count =0;}
 System.out.println(binlist.get(i));
     
}
    
    

//System.out.println("ENDDD");
    
    
    ArrayList<String> listcpy = new ArrayList<String>(100000);
    for(int r=0; r<binlist.size(); r++){
        
        listcpy.add(binlist.get(r)); //copying the arraylist, as the main list need not be disturbed
        //System.out.println(listcpy);
    }
    //System.out.println("after list copying");
    int cont=0;
         for(int i=0; i<listcpy.size(); i++){
             if(listcpy.get(i).equals("1")){
                 cont+=1;
                 if(cont==5){//when 5 ones are continuosly detected
                         i+=1;
                         listcpy.remove(i);//removing the zero
                 }
             }
             else{cont=0;}
         }
         String[] stockArr = new String[listcpy.size()];
         stockArr = listcpy.toArray(stockArr);
         StringBuffer result = new StringBuffer();
            for (int i = 0; i < stockArr.length; i++) {
             result.append( stockArr[i] );
             //result.append( optional separator );
            }
         String mynewstring = result.toString();//reverse conversion
         //System.out.println(mynewstring);
         String output = "";
         //System.out.println("test");
         
        	
         for(int i = 0; i <= mynewstring.length() - 8; i+=8)
		{
		    int k = Integer.parseInt(mynewstring.substring(i, i+8), 2);
		    output += (char) k; //back to text
		}
       
System.out.println();        
System.out.println("Bitstream to Text :");        
System.out.println(output);
         
        

    }
   
    }
//Acknowlegdements: 
//Concept of using ArrayLists for Bitstuffing was brainstromed along with Dhivya Jayaraman(B00748344)
