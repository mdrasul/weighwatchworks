package com.test.weassignment;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class zNthSmallest{
  public static void main (String [] args){
    //Scanner input = new Scanner(System.in);
    //System.out.println("Please enter the size of the array");

    //final int N = 500;
    //int n  = input.nextInt();

    Double array[]  = new Double[500];
    //System.out.println("enter 15 number ");

    for (int i = 0 ; i < array.length; i++ ) {
      array[i] =  Math.random();
      System.out.println(array[i]);
    }

    System.out.println("===================");

    System.out.println(findNthSmallest(array, 9));
  }

  
  public static Double findNthSmallest(Double[] array, int n) 
  {
	    Arrays.sort(array);
	    return array[n-1];
	}  
}