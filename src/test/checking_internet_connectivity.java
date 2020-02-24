package test;

// Java program for Checking Internet connectivity 
import java.util.*; 
import java.io.*; 
  
public class checking_internet_connectivity { 
    public static void main(String args[]) throws Exception 
    { 
        Process process = java.lang.Runtime.getRuntime().exec("ping www.geeksforgeeks.org"); 
        int x = process.waitFor(); 
        if (x == 0) { 
            System.out.println("Connection Successful, "
                               + "Output was " + x); 
        } 
        else { 
            System.out.println("Internet Not Connected, "
                               + "Output was " + x); 
        } 
    } 
} 