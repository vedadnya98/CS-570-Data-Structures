package Anagrams;

import java.io.*;
import java.util.*;

/**
 * 
 * @author vedadnya_98
 *
 */
public class Anagrams {
	
	final Integer[] primes;
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	
	public Anagrams (){
		primes = new Integer[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,67, 71, 73, 79, 83, 89, 97, 101};
		buildLetterTable();
		anagramTable=new Hashtable<Long, ArrayList<String>>();	
	}
	
	/*
	 * Builds a Letter Table for hashing
	 */
	private void buildLetterTable() {
		letterTable = new Hashtable<Character, Integer>();
		Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for (int i=0; i<primes.length ; i++) 
		{
			letterTable.put(letters[i], primes[i]);
		}	
	}
	
	/**
	 * A functions for storing anagrams
	 * @param s
	 */
	private void addWord(String s) {
		Long len=myHashCode(s);
		ArrayList<String> check;
		ArrayList<String> word = new ArrayList<String>();
		check=anagramTable.get(len);
		if(check==null)
		{	
			word.add(s);
			anagramTable.put(len, word);
		}
		else
		{
			check.add(s);
			
		}
	}
	
	/**
	 * Here we generate unique keys for hash functions
	 */
	private Long myHashCode(String s) {
		Long product = 1L;
		char letter;
		for(int i=0 ; i<s.length() ; i++)
		{	
			letter=s.charAt(i);
			product=product*letterTable.get(letter);
		}
		return product;
	}
	
	/**
	 * Open the text document to check for anagrams here
	 * Put path as required
	 * @param s The word
	 * @throws IOException The file could not be read
	 */
	private void processFile ( String s ) throws IOException {
		
		FileInputStream fstream = new FileInputStream (s );
		BufferedReader br = new BufferedReader ( new InputStreamReader ( fstream ));
		String strLine ;
		while (( strLine = br . readLine ()) != null ) 
		{
			this . addWord ( strLine );
		}
		br . close ();
		}
	
	/**
	 * Gets the key with the most anagrams
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries=new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		int max=0;
		for(Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet())
		{		
			if(entry.getValue().size() < max)
			{
				continue;
			}
			else if(entry.getValue().size() > max)
			{
				maxEntries.clear();
				max=entry.getValue().size();
				maxEntries.add(entry);
			}
			else
			{
				maxEntries.add(entry);
			}	
		}
		return maxEntries;	
	}
	
	public static void main ( String [] args ) {
		Anagrams a = new Anagrams ();
		final long startTime = System . nanoTime ();
		try 
		{
			a.processFile("words_alpha.txt");
		} 
		catch ( IOException e1 ) 
		{
			e1.printStackTrace ();
		}
		ArrayList < Map . Entry < Long , ArrayList < String > >> maxEntries = a. getMaxEntries ();
		final long estimatedTime = System . nanoTime () - startTime ;
		final double seconds = (( double ) estimatedTime /1000000000);
		System.out .println (" Time : "+ seconds );
	    System.out .println (" List of max anagrams : "+ maxEntries );
		}
}
