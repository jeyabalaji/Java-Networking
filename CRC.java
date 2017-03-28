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

import java.util.*;

public class CRC {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n,choice;
                int div[] = new int[100000];
                String temp_div;
                System.out.println("Select the type of CRC Standard: ");
                System.out.println();
                System.out.println("*************************");
                System.out.println("1. CRC-1 ");
                System.out.println();
                System.out.println("2. CRC-5 ");
                System.out.println();
                System.out.println("3. CRC-8 ");
                System.out.println();
                System.out.println("4. CRC-32 ");
                System.out.println("*************************");
                choice = scan.nextInt();
                int crc1_div[] = new int[10000];
                crc1_div = new int[]{1,1};
                int crc5_div[] = new int[10000];
                crc5_div = new int[]{1,0,1,0,1};
                int crc8_div[] = new int[10000];
                crc8_div = new int[]{1,1,0,0,1,1,0,1,1};
                int crc32_div[] = new int[10000];
                crc32_div = new int[]{1,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,1,0,0,0,1,1,1,0,1,1,0,1,1,1,1,1,1};
                
                switch(choice)
                {
                    case 1:  temp_div="11"; 
                                                div=crc1_div.clone();                                               
						System.out.println("Ploynomial G(x) is set as: "+temp_div);
						break;
					
				case 2: temp_div = "10101";
                               
                                                div=crc5_div.clone();                                                
						System.out.println("Ploynomial G(x) is set as: "+temp_div);
						break;
				
				case 3: temp_div = "110011011";
                                
                                                div=crc8_div.clone();                                               
						System.out.println("Ploynomial G(x) is set as: "+temp_div);
						break;
				
				case 4: temp_div = "100000100110000010001110110111111";
                                
                                                div=crc32_div.clone();
						System.out.println("Ploynomial G(x) is set as: "+temp_div);
						break;
						
				default: System.out.println("Invalid Entry.");
						 break;
                }
                
                
                
                
                
                
		System.out.println("Enter the total number of bits present in data:");
		n = scan.nextInt();
		int[] given_data = new int[n];
		System.out.println("Enter the data, (one bit once):");
		int i=0;
		System.out.println("Enter " + (n-i) + "bits of data");
		for(i=0 ; i < n ; i++) {
			
			given_data[i] = scan.nextInt();
		}
				
//		System.out.println("Enter the divisor size:");
//		n = scan.nextInt();
//		
//		System.out.println("Enter the bit by bit div:");
//		
//		
//		for(i=0 ; i < n ; i++) {
//			System.out.println("Enter bit number " + (n-i) + ":");
//			div[i] = scan.nextInt();
//		}
//		
		int[] remainder = bit_division(given_data, div);
		for(i=0 ; i < remainder.length-1 ; i++) {
			System.out.print(remainder[i]);
		}
		System.out.println("\nCRC Sucessfull");
		
		for(i=0 ; i < given_data.length ; i++) {
			System.out.print(given_data[i]);
		}
		for(i=0 ; i < remainder.length-1 ; i++) {
			System.out.print(remainder[i]);
		}
		System.out.println();
		int sent_data[] = new int[given_data.length + remainder.length - 1];
		System.out.println("Enter the data to be sent:");
		for(i=0 ; i < sent_data.length ; i++) {
			System.out.println("Enter bit number " + (sent_data.length-i)
								+ ":");
			sent_data[i] = scan.nextInt();
		}
		message_validate(sent_data, div);
	}
	
        static int[] bit_division(int old[], int div[]) {
		int rem_arr[] , i;
		int data[] = new int[old.length + div.length];
		System.arraycopy(old, 0, data, 0, old.length);
		rem_arr = new int[div.length];
		System.arraycopy(data, 0, rem_arr, 0, div.length);
		
		for(i=0 ; i < old.length ; i++) {
			System.out.println("Quotient is "
								+ rem_arr[0]);
			System.out.print("Remainder : ");
			if(rem_arr[0] == 1) {
				for(int j=1 ; j < div.length ; j++) {
					rem_arr[j-1] = final_equal_check(rem_arr[j], div[j]);
					System.out.print(rem_arr[j-1]);
				}
			}
			else {			
				for(int j=1 ; j < div.length ; j++) {
					rem_arr[j-1] = final_equal_check(rem_arr[j], 0);
					System.out.print(rem_arr[j-1]);
				}
			}
			rem_arr[div.length-1] = data[i+div.length];
			System.out.println(rem_arr[div.length-1]);
		}
		return rem_arr;
	}
        
	static int final_equal_check(int a, int b) {
		if(a == b) {
			return 0;
		}
		return 1;
	}
        
        static int[] strtoary(String s){
            int result_div[] = null;
            for(int i=0;i<s.length();i++){
                result_div[i]=Integer.parseInt(s);
            }
            return result_div;
        }
        
        public static int[] parseBinaryToIntArray(String input) {
    // TODO: Validation
    int[] output = new int[input.length() / 32];
    for (int i = 0; i < output.length; i++) {
    String section = input.substring(i * 32, (i + 1) * 32);
        output[i] = (int) Long.parseLong(section, 2);
    }
    return output;
}
        
        public static int[] convertIntoIntArray(String message) {
        System.out.println(message.length());
        int[] t = new int[message.length()/32];
        for(int i = 0; i < t.length; i++){
            //t[i] = Integer.parseInt(message.substring(i*32, (i+1)*32),2);
            String section = message.substring(i*32, (i+1)*32);
            t[i] = (int) Long.parseLong(section, 2);
        //  System.out.println(i+"fsa");
        }

        return t;   
    }

	static void message_validate(int data[], int div[]) { 
		int remdr[] = bit_division(data, div);
		for(int i=0 ; i < remdr.length ; i++) {
			if(remdr[i] != 0) {
				System.out.println("ERROR Detected!!");
				return;
			}
		}
		System.out.println("Transmission Sucessful! NO Errors!!");
	}
}




//Acknowlegdements: 
//http://stackoverflow.com/questions/26959765/programming-the-crc-algorithm-manipulating-strings-in-java
//https://www.youtube.com/watch?v=MSAog5MEhrs
//http://stackoverflow.com/questions/5126616/xor-operation-with-two-strings-in-java

