package BinaryNumber;

import java.lang.Math;
import java.util.*;
class BinaryNumber {   
private int data[];  
private boolean overflow;
//Constructor for a Binary Numbers consisting only of 0's
public BinaryNumber(int length) 
{ 
	if(length<1)
	{
		System.out.println("Lenght of the Binary Number cannot be zero or less than zero");
		return;
	}
	data = new int [length];
	for (int i=0; i<length ; i++)
	{
		data[i] = 0;
	}
	System.out.print("The Binary Numbers consisting only of 0's of length "+length+" is :  ");
	for(int i=0;i<length;i++)
	{
		System.out.print(data[i]);
	}
}

//Constructor for converting a string of Binary Number to a Numeric Array
public BinaryNumber(String str) 
{
	data=new int[str.length()];
	int len =str.length();
	char[] str1 = str.toCharArray();
	
	for(int i=0;i<len;i++)
	{
		data[i]=Character.getNumericValue(str1[i]);		
	}
	System.out.print("\n\nThe given String has been converted to a Binary Number and is stored in a NumericArray : ");
	for(int i=0;i<len;i++)
	{
		System.out.print(data[i]);
	}
}

//method to find length of a Binary Number
int getLength()
{
	System.out.print("\n\nThe length of the given Binary Number is :  ");
	return data.length;
}

//method to find the value at a specific position in a Numeric Array
int getDigit(int index)
{
	if(index<0 || index>data.length-1 )
	{
		System.out.print("\nThe index you entered is out of bound");
		return -1;
	}
	else
	{
	System.out.print("\nThe digit at index "+index+" is : ");
	return data[index];
	}
}

//method to convert a Binary Number to its Decimal Equivalent
int toDecimal()
{
	int value=0 ;
	for(int i=0;i<data.length;i++)
	{
	value=(int) (value + data[i]*Math.pow(2, i));
	}
	System.out.print("\n\nThe Decimal conversion of the given Binary Number is : ");
	return value;
}

//method to shift right a specific number of places the digits of a Binary Number
void shiftR(int amount)
{
	int i;
	int[] shifted=new int[amount+data.length];
	System.out.print("\nRight shifted Binary Number is : ");
	for(i=0;i<amount;i++)
	{
		shifted[i]=0;
		System.out.print(shifted[i]);
	}
	int j=0;
	
	for(i=amount;i<amount+data.length;i++)
	{
		shifted[i]=data[j];
		j++;
		System.out.print(shifted[i]);
	}	
}

//method to aBinaryNumber.data two Binary Numbers
void add(BinaryNumber aBinaryNumber) 
{

	if(data.length!=aBinaryNumber.data.length)
	{
		System.out.print("\n\nThe length of the binary numbers is different!");         
	}	
	//We shift data[i] in a different array data1[i] for us to store the sum in data[i]
	int[] data1= new int[data.length];
	System.out.print("\n\nThe Strings to be added : ");
	for(int i=0;i<data.length;i++)
	{
		data1[i]=data[i];
		System.out.print(data1[i]);
	}
	System.out.print(" and ");
	for(int i=0;i<data.length;i++)
	{
		System.out.print(aBinaryNumber.data[i]);
	}
	int[] carry = new int[data.length+1];
	
	for(int i=0;i<carry.length; i++)
	{
		carry[i]=0;
	}
	for(int i=0;i<data.length;i++)
	{
		if(aBinaryNumber.data[i]==1 && data1[i]==1)
		{
			if(carry[i]==0)
			{
				data[i]=0;
				carry[i+1]=1;
			}
			else
			{
				data[i]=1;
				carry[i+1]=1;
			}
		}
		if(aBinaryNumber.data[i]==1 && data1[i]==0||aBinaryNumber.data[i]==0 && data1[i]==1)
		{
			if(carry[i]==0)
			{
				data[i]=1;
				carry[i+1]=0;
			}
			else
			{
				data[i]=0;
				carry[i+1]=1;
			}
		}
		if(aBinaryNumber.data[i]==0 && data1[i]==0)
		{
			if(carry[i]==0)
			{
				data[i]=0;
				carry[i+1]=0;
			}
			else
	 		{
				data[i]=1;
				carry[i+1]=0;
			}
		}
		
	}
	//System.out.print("\nThe carry Array is : ");
	for(int i=0;i<data.length+1; i++)
	{
		//System.out.print(carry[i]);
		if(carry[data.length]==1)
		{
			overflow=true;
		}
	}
	/*System.out.print("\nThe Answer of the added String is : ");
	for(int i=0;i<data.length; i++)
	{
		System.out.print(data[i]);
	}*/
	
}
public String toString()
{
String str="";
if(overflow==true)
{
	str="Overflow";
}
else
{
	str=Arrays.toString(data);
}
return str;	
}
public void clearOverflow()
{
	overflow=false;
	System.out.println("\nThe Overflow Flag is set to False");
}


public static void main(String[] args)
{
	// TODO Auto-generated method stub
	BinaryNumber zero = new BinaryNumber(5);
	BinaryNumber binary = new BinaryNumber("10101");
	System.out.println(binary);
	//BinaryNumber shift = new BinaryNumber("1011");
//	int n1=binary.getLength();
//	System.out.println(n1);
//	int n=binary.getDigit(5);
//	/*if(n==-1)
//	{
//		System.out.println("\nThis index is out of bounds");
//	}
//	else
//	{	
//	}*/
//	System.out.println(n);
//
//	binary.shiftR(2);
//	binary.add(new BinaryNumber("10111"));
//	String bin=binary.toString();
//	System.out.print("\n\nAddition Result : "+bin);
//	int n2=binary.toDecimal();
//	System.out.println(n2);
//	binary.clearOverflow();
}
}
