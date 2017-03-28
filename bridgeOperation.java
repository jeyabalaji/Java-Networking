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
public class bridgeOperation  {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
         System.out.println("Bridge Operation with Learning");
        System.out.println("***************************************************************");
        Scanner scan = new Scanner(System.in);
        
        //Readinf input for Forwarding DataBase
	FileReader inputf = new FileReader(new File("./fdatabase.txt")); 
        BufferedReader br_table =new BufferedReader(inputf);
	StringBuilder sb = new StringBuilder();
        String stri = br_table.readLine();

         while (stri != null)
          {
              sb.append(stri);  
              sb.append(System.lineSeparator());
              stri = br_table.readLine();
          }
        String fdatabaseTemp = sb.toString();               
        String[] fdb = fdatabaseTemp.split(" ");
        int len_fdb = fdb.length;

        int row_fdb = len_fdb/2;
        int l=0;   
        
        String[][] fDataBaseArray = new String[100][2];
         
        for(int i=0;i<row_fdb;i++)
        {
            for(int j=0;j<2;j++)
            {
                fDataBaseArray[i][j] = fdb[l];
                 l++;
               
                }
            }
        
    
        
        //Reading input for each frame
	FileReader in_f = new FileReader(new File("./in_frames.txt")); 
        BufferedReader brFrame =new BufferedReader(in_f);
        StringBuilder sbFrame = new StringBuilder();
          String strInput = brFrame.readLine();
        while (strInput != null)
          {
              sbFrame.append(strInput);  
              sbFrame.append(System.lineSeparator());
              strInput = brFrame.readLine();
          }
        String input = sbFrame.toString();               
        String[] ss = input.split(" ");   
        int len = ss.length;
        int len1 = len+1;
        int row = len1/3;
        int k = 0;
        int flag = 0;
        int q;
        int secLoop = 1;
        String[][] frameArray = new String[row][3];
         
            for (int i = 0; i<row; i++) {
                for (int j = 0; j<3; j++) {
                    frameArray[i][j] = ss[k];
                    ++k;
                }
            }
          //Bridge operation algorithm along with path learning
          for(q=0; q<frameArray.length+1; q++)
          {
           if(frameArray[q][0].equals(fDataBaseArray[secLoop][0]))
            {
                    flag = 1;//control variable
                    if(frameArray[q][2].equals(fDataBaseArray[secLoop][1])) //check entry for SOURCE in Forwarding Database
                    { 
                            flag = 1;
                            System.out.println(frameArray[q][0] +"\t"+frameArray[q][1] +"\t"+frameArray[q][2] +"  Frame Discarded: Incorrect entry");    
                    }
                    else 
                    {
                             fDataBaseArray[row_fdb][0] = frameArray[q][1];//Adding the Value to FDB
                             fDataBaseArray[row_fdb][1] = frameArray[q][2];
                             flag = 1;
                             System.out.println(frameArray[q][0] +frameArray[q][1] +frameArray[q][2] +"  Entry has been added to Forwarding DataBase :"+fDataBaseArray[row_fdb][0] + "   "+fDataBaseArray[row_fdb][1]);                       
                    }
            }
           
           int po=0;
           po=q+1;
           //checking entry for DESTINATION in Forwarding Database
           if(flag == 1)
           {
                    if(frameArray[q][1].equals(fDataBaseArray[secLoop][0])) 
                    {
                      
                        if(frameArray[q][2].equals(fDataBaseArray[secLoop][1]))// discarding frame
                        {
                           System.out.println(frameArray[po][0] +"\t"+frameArray[po][1] +"\t"+frameArray[po][2] +"  Frame Discarded: Incorrect entry");// discarding frame
                        }
                        else
                        {
                            System.out.println(frameArray[po][0] +"\t"+frameArray[po][1] +"\t"+frameArray[po][2] +"  Frame sent out on all ports: BROADCASTED");
                        }
                    }
                    else if(!frameArray[q][1].equals(fDataBaseArray[secLoop][0]))
                    {
                   
                        System.out.println(frameArray[po][0] +"\t"+frameArray[po][1] +"\t"+frameArray[po][2] +"  Forwarding DataBase Updated; sent on port " +frameArray[q][2]);                        
                    }
           }
           else
           {
               System.out.println(frameArray[po][0] +"\t"+frameArray[po][1] +"\t"+frameArray[po][2] +"  Frame Sent on Port"+frameArray[q][2]);
           }
           secLoop = secLoop+1;
          }
    }
}