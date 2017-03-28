/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jeyabalaji
 */
public class packetRouting {

    /**
     * @param args the command line arguments
     */  
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        System.out.println("Packet Routing in a Router");
        System.out.println("***************************************************************");
	FileReader input_1 = new FileReader(new File("./frames.txt"));
	FileReader input_2 = new FileReader(new File("./addresslist.txt"));
        boolean conti = true;
	String giant; //string that is used to store all the frame as single string
        int ctr_vr = 0;	
	String[] data_one= new String[1];
	System.out.println( "Enter the no. of hosts");
        Scanner input_scan = new Scanner(System.in);//getting the number of hosts from user
	int dest = Integer.valueOf(input_scan.nextLine());
	String[][] array_string = new String [dest][5];
	BufferedReader br_table =new BufferedReader(input_1);
	BufferedReader br_address =new BufferedReader(input_2);
        
	while((giant = br_table.readLine())!=null && ctr_vr<dest)
	{       //to store the data from parsed array to two dimenssional array(i.e array_string)
		data_one = giant.split("     ");
		array_string [ctr_vr][0] = data_one[0];
		array_string [ctr_vr][1] = data_one[1];
		array_string [ctr_vr][2] = data_one[2];
		array_string [ctr_vr][3] = data_one[3];
		array_string [ctr_vr][4] = data_one[4];
		ctr_vr++;
	}	
	
	
	System.out.print("List of available Interfaces:"+"\n");//to display the list of available interfaces
        for(int i=0;i<array_string.length;i++){
        System.out.println(array_string[i][4]); 
        }
	while((giant = br_address.readLine())!=null)
	{
	conti = true;
        ctr_vr = 0;

        
        String result = "";
	while(ctr_vr<array_string.length  && conti == true)
	{
            result ="";	
            String[] ip = giant.split("\\."); //parsing the values from text file and storing in string array that holds IP addresses
            String[] routemask= array_string[ctr_vr][0].split("\\."); //parsing the values from text file and storing in string array that holds subnet masks
            ctr_vr++;
            for(int k=0;k<ip.length;k++)
            {
            result +=String.valueOf(Integer.valueOf(ip[k]) & Integer.valueOf(routemask[k]))+"."; //appending suitable ip addresses according to its subnet mask
            }
            result = result.substring(0, result.length()-1);
            for (int j=0; j<array_string.length; j++ )
		{
			if (result.equals(array_string[j][1])){ 
			System.out.println("Packet with destination address " +giant+ " will be forwarded to " +array_string[j][2]+ " out on interface " +array_string[j][4]);						
			conti = false;}}
		}
	}
        
    }
    
    
    
}




//Attribution and References:
//http://stackoverflow.com/questions/10751603/how-to-insert-values-in-two-dimensional-array-programmaticaly
//http://stackoverflow.com/questions/586182/how-to-insert-an-item-into-an-array-at-a-specific-index?rq=1
