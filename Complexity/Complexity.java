package Complexity;

public class Complexity {
	private static int counterexp = 1;
	
	//if needed , not present in the assignmnent question
	/*
	public static void method0(int n){
		//implement O(n)
		int counter =0;
		for(int i=0 ; i<n ; i++)
		  {	  
		   //System . out . println (" Operation "+ counter );
		   counter ++;
		  }
		System . out . println (" Operation executes (O(n)) : "+ counter + " times");
	}
	*/

	public static void method1(int n){
		//implement O(n^2)
		if(n<0) 
		{
			System.out.println("The integer value for O(n^2) can't be negative");
		}
		else 
		{
		int counter =0;
		for (int i =0; i <n; i++) 
		{
		 for( int j=0 ; j<n ; j++)
		 {	 
		  counter ++;
		  System . out . println (" Operation executes (O(n^2)) : "+ counter + " times");
		 } 
		}
		}
	}
	
	public static void method2(int n){
		//implement O(n^3)
		if(n<0) 
		{
			System.out.println("The integer value for O(n^3) can't be negative");
		}
		else
		{	
		int counter =0;
		for (int i =0; i <n; i ++) 
		{
		 for( int j=0 ; j<n ; j++)
		 {
		  for(int k=0 ; k<n ; k++)
		  {	  
		   counter ++;
		   System . out . println (" Operation executes (O(n^3)) : "+ counter + " times");
		  }
		 } 
		}
		}
	}

	
	public static void method3(int n){
		//implement O(log n)
		if(n<0) 
		{
			System.out.println("The integer value for O(log n) can't be negative");
		}
		else 
		{
		int counter =0;
		for(int i=1 ; i<n ; i*=2)
		  {	  
		   counter ++;
		   System . out . println (" Operation executes (O(logn)) : "+ counter + " times");
		  }
	}
	}	
	
	public static void method4(int n){
		//implement O(nlogn)
		if(n<0) 
		{
			System.out.println("The integer value for O(nlogn) can't be negative");
		}
		else 
		{
		int counter =0;
		for (int i =0; i <n; i++) 
		{
		 for( int j=1 ; j<n ; j*=2)
		 {	 
		  counter ++;
		  System . out . println (" Operation executes (O(nlogn)) : "+ counter + " times");
		 } 
		}
	}
	}
	
	
	public static void method5(int n){
		//implement O(loglogn)
		if(n<0) 
		{
			System.out.println("The integer value for O(loglogn) can't be negative");
		}
		else 
		{
		int counter =0;
		for( int i = n ; i>2 ; i = (int) Math.sqrt(i))
		 {	 
		  counter ++;
		  System . out . println (" Operation executes (O(loglogn)) : "+ counter + " times");
		 }
	}
	}
	
	public static int method6(int n) {
		//implement O(2^n)
		
		//if ((n == 0) || (n == 1))
	    //     return n;
	    //  else
	    //    return method6(n - 1) + method6(n - 2);
		if(n<0)
		{
			System.out.println("The integer value for O(2^n) can't be negative");
			return -1;
			
		}
		else
		{
		counterexp++;
		System . out . println (" Operation executes (O(2^n)) : "+ counterexp + " times");
			if(n==0||n==1)
			{  
				//System . out . println (" Operation executes (O(2^n)) : "+ counterexp + " times");
				return counterexp;
			}
			else
			{
				
				return method6(n-1)+method6(n-1);
			}
		}	
	}
	
	 public static void main(String args[])
 {
	int n=10;
//  method0(n);
	method1(n);
	method2(n);
	method3(n);
	method4(n);
	method5(n);
	method6(n);
 }
} 
