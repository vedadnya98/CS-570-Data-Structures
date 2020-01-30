package DLL;

public class IDLListTest{

	
	public static void main(String[] args) {
//		IDLList<Character> check=new IDLList<Character>();
//		//Filling up the linked list with values
//		check.add('3');
//		check.add(0,'a');
//		check.add(1,'b');
//		check.add(2,'b');
//		check.add(3,'d');
//		check.add(4,'e');
//		check.add(5,'w');
//		check.add(6,'q');
//		check.add(7,'u');
//		check.add(8,'2');
//		
//		//Converting to string and displaying the resultant linked list
//				System.out.println("First Linked List: ");
//				String s1=check.toString();
//				System.out.println(s1);
//		
//		//Inserting value in middle
//		check.add(6,'h');
////		
////		Appending
//		check.append('z');
//		
//		//Converting to string and displaying the resultant linked list
//		System.out.println("Second Linked List: ");
//		String s2=check.toString();
//		System.out.println(s2);		
//		
//		System.out.println("\nHere comes error checking for Add");
//		try 
//		{
//		check.add(50,'s');
//		}
//		catch(IndexOutOfBoundsException error)
//		{
//			System.out.println("Index out of bounds");
//		}
//		try 
//		{
//		check.add(-1,'a');
//		}
//		catch(IndexOutOfBoundsException error)
//		{
//			System.out.println("Index out of bounds");
//		}
//		
//		System.out.println("\nHere comes error checking for Get");
//		try 
//		{
//		check.get(50);
//		}
//		catch(IndexOutOfBoundsException error)
//		{
//			System.out.println("Index out of bounds");
//		}
//		try 
//		{
//		check.get(-1);
//		}
//		catch(IndexOutOfBoundsException error)
//		{
//			System.out.println("Index out of bounds");
//		}
//		
//		System.out.println("\nHere comes error checking for RemoveAt");	
//		try 
//		{
//		check.removeAt(50);
//		}
//		catch(IndexOutOfBoundsException error)
//		{
//			System.out.println("Index out of bounds");
//		}
//		
//		try 
//		{
//		check.removeAt(-1);
//		}
//		catch(IndexOutOfBoundsException error)
//		{
//			System.out.println("Index out of bounds");
//		}
//		System.out.println("");		
//		
////		//Getting elements at head,last and at a specified index
//		char Head = check.getHead();
//		System.out.println("Head element is : " + Head);
//		char Last = check.getLast();
//		System.out.println("Last element is : " +Last);
//		char atIndex = check.get(5);
//		System.out.println("Element at given index is : " +atIndex);
//		
//		//Displaying size of linked list
//		int size = check.size();
//		System.out.println("Size is : " +size);
//		
//		check.add(size, 'x');
//		
//		System.out.println("Add after (size-1) Linked List: ");
//		String s4=check.toString();
//		System.out.println(s4);
//		
//		//Removing elements at head,last and at a specified index
//		check.removeAt(5);
//		check.remove();
//		check.removeLast();
//		System.out.println("\n");
//		System.out.println("\n");
//		System.out.println("\n");
//		check.remove('d');
//		//System.out.println("Status of remove : "+removed);
//		
//		
//		//Converting to string and displaying the resultant linked list
//		System.out.println("Final Linked List: ");
//		String s=check.toString();
//		System.out.println(s);
		
		IDLList<Integer> check=new IDLList<Integer>();
//		//Filling up the linked list with values
		check.add(0);
		check.add(0,10);
		check.add(1,20);
		check.add(2,30);
		check.add(3,40);
		
		System.out.println("First Linked List: ");
		String s=check.toString();
		System.out.println(s);
		
//		check.removeAt(2);
//		check.remove();
//		check.removeLast();
		
		boolean test = check.remove(20);
		System.out.println(test);
		
		System.out.println("Final Linked List: ");
		String s1=check.toString();
		System.out.println(s1);
		
//		check.add(2,30);
//		check.add(3,40);
//		check.add(4,50);
//		check.add(5,60);
//		check.add(6,70);
//		check.add(7,80);
//		check.add(8,90);
		
	}

}