package Treap;
import java.util.Random;
import java.util.Stack;


/**
 * @author Vedadnya Jadhav
 * @CWID : 10450603
 */
public class Treap<E extends Comparable<E>> {
		

	private Random priorityGenerator=new Random(10);
	private Node<E> root;
	private Stack<Node<E>> route=new Stack<Node<E>>();

	private static class Node<E>{
	
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left;
		public Node <E> right;
		
		/**
		 * Creates a new node with the given data and priority.
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority)
		{
			 try
			 {
				this.data=data;
				this.priority=priority;
				this.left= null;
				this.right= null;
			 }
			 catch(Exception e)
			 {
					System.out.println(e);
			 }
		}
		
		/**
		 * Performs a right rotation in the Treap
		 */
		public Node<E> rotateRight()
		{
			Node<E> rotateNode = new Node<E>(this.data,this.priority);
			if (this.left != null) 
			{
				if(this.right != null) 
				{
					rotateNode.right = this.right;
				}
				if(this.left.right != null) 
				{
					rotateNode.left=this.left.right;
				}
				this.priority = this.left.priority;
				this.data = this.left.data;
				this.right = rotateNode;
				if(this.left.left == null)
				{
					this.left = null;
				} 
				else
				{
					this.left = this.left.left;
				}
			}
			return this.right;
		}		
		
		/**
		 * Performs a left rotation in the Treap
		 */
		public Node<E> rotateLeft()
		{
			Node<E> rotateNode = new Node<E>(this.data,this.priority);
			if(this.right != null) 
			{
				if(this.left != null) 
				{
					rotateNode.left = this.left;
				}
				if(this.right.left !=  null) 
				{
					rotateNode.right = this.right.left;
				}
				this.priority = this.right.priority;
				this.data = this.right.data;
				this.left = rotateNode;
				if(this.right.right == null) 
				{
					this.right = null;
				} 
				else 
				{
					this.right = this.right.right;	
				}
			}
			return this.left;

		}
		
		
	}
	
	/**
	 * Treap to create an empty Treap using new Random().
	 */
	public Treap()
	{
		this.priorityGenerator=new Random();
		this.root=null;
	}
	
	/**
	 * Treap to create an empty Treap using new Random(seed).
	 * @param seed
	 */
	public Treap(long seed)
	{
		this.priorityGenerator=new Random(seed);
		this.root=null;
	}
	
	/**
	 * Adding with only the key provided
	 * @param key
	 * @return
	 */
	boolean add(E key)
	{
		if(add(key,priorityGenerator.nextInt()))
			return true;
		else
			return false;	
	}
	
	
	/**
	 * Add Method with key and priority provided
	 * @param key
	 * @param priority
	 * @return
	 */
	boolean add(E key,int priority)
	{
		Node<E> newNode=new Node<E>(key,priority);
		if(root==null)
		{
			root=newNode;
			return true;
		}
		
		Node<E> rootNode=root;
		while(rootNode!=null)
		{
			route.push(rootNode);
			if(key.compareTo(rootNode.data)>0)
			{
				rootNode=rootNode.right;
			}
			else
			{
				rootNode=rootNode.left;
			}
		}
		
		if(key.compareTo(route.peek().data)>0)
		{
			route.peek().right=newNode;
		}
		else
		{
			route.peek().left=newNode;
		}
		reheap(newNode);
		return true;
	}
	
	
	/**
	 * Helper function to restore the heap invariant 
	 * @param newNode
	 */
	private void reheap(Node<E> newNode)
	{
		Node<E> currentTop=route.pop();
		while(currentTop!=null && currentTop.priority<newNode.priority) 
		{
			if(newNode.data.compareTo(currentTop.data)<0) 
			{
				currentTop.rotateRight();
				if(route.isEmpty())
				{
					return;
				}
				currentTop=route.pop();
			}
			else 
			{
				currentTop.rotateLeft();
				if(route.isEmpty()) 
				{
					return;
				}
				currentTop=route.pop();
			}
		}
	}
	
	
	/**
	 * Delete Method with key provided
	 * @param key
	 * @return
	 */
	boolean delete(E key)
	{
		if(find(key)!=true)
			return false;
		else if(root.data.compareTo(key) == 0 && root.right == null && root.left==null)
        {
        	root = null;
        	return true;
        }
		else 
		{
			boolean deleteStatus = deleteHelper(root , key);	
			if(deleteStatus==true)
				return true;
			else
				return false;
		}
	}
	
	/**
	 * Helper Method for the Delete Function
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean deleteHelper(Node<E> root,E key)
	{
		Node<E> rootNode=root;
		Node<E> deleteNode = null; 
        while(rootNode.data.compareTo(key) != 0)
        {
        	deleteNode=rootNode;
            if(rootNode.data.compareTo(key) > 0) 
            {
            	rootNode=rootNode.left;
            }
            else 
            {
            	rootNode=rootNode.right;
            }
        }
        while(!(rootNode.left == null && rootNode.right == null)) 
        {
        	deleteNode=rootNode;
        	if(rootNode.left == null) 
        	{
        		rootNode=rootNode.rotateLeft();	        	
        	}
        	else if(rootNode.right == null) 
        	{
        		rootNode=rootNode.rotateRight();
        	}
        	else if(rootNode.left.priority>rootNode.right.priority) 
        	{
        		rootNode=rootNode.rotateRight();
        	}
        	else 
        	{
        		rootNode=rootNode.rotateLeft();
        	}
        }
        if((deleteNode.right != null && deleteNode.right.data.compareTo(key)==0))
        {
        	deleteNode.right = null;
        }
        else if(deleteNode.left != null && deleteNode.left.data.compareTo(key)==0) 
        {
        	deleteNode.left = null;
        }
        return true;
	}
	
	/**
	 * Find method with Root and Key provided
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean find(Node<E> root,E key)
	{
		while(true)
		{
		
		if(root!=null)
		{
			if(root.data==key)
			{
				return true;
			}
			else if(root.data.compareTo(key)>0)
			{
				root=root.left;
			}
			else if(root.data.compareTo(key)<0)
			{
				root=root.right;
			}
		}
		else
		{
			return false;
		}
		}
	}
	
	/**
	 * Find method with only key provided
	 * @param key
	 * @return
	 */
	public boolean find(E key)
	{
		boolean finder = find(root,key);
		return finder;
	}
	
	
	
	/**
	 * Preorder Traversal through the treap for toString
	 * @param rootNode
	 * @param depth
	 * @param sb
	 */
	private void preorderTraversal(Node<E> rootNode,int depth,StringBuilder sb)
	{
		for(int i=0;i<depth;i++)
		{
			sb.append("  ");
		}
		if(rootNode==null)
		{
			sb.append("null\n");
		}
		else
		{
			sb.append("(key="+rootNode.data.toString()+" , priority="+rootNode.priority+")\n");
			preorderTraversal(rootNode.left,depth+1,sb);
			preorderTraversal(rootNode.right,depth+1,sb);
		}
	}
	
	/**
	 * Coverting and returing treap as a string
	 */
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		preorderTraversal(root,1,sb);
		return sb.toString();
	}



public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	Treap<Integer> testTree = new Treap <Integer>();    
	  testTree.add(4,19); 
	  testTree.add(2,31);
	  testTree.add(6,70); 
	  testTree.add(1,84);
	  testTree.add(3,12); 
	  testTree.add(5,83);
	  testTree.add(7,26);	
	String string=testTree.toString();
	System.out.println(string);
	
	testTree.delete(1);
	if(testTree.find(4))
	{
		System.out.println("Found!");
	}
	else
	{
		System.out.println("Not found!");
	}
	String str=testTree.toString();
	System.out.println(str);
//	Treap<Integer> testTree = new Treap<Integer>();
//	testTree.add (4 ,19);
//	testTree.add (2 ,31);
//	testTree.add (6 ,70);
//	testTree.add (1 ,84);
//	testTree.add (3 ,12);
//	testTree.add (5 ,83);
//	testTree.add (7 ,26);
//	//System.out.println(testTree.toString());
//	testTree.delete(1);
//	testTree.delete(7);
//	testTree.delete(4);
//	testTree.delete(6);
//	testTree.delete(3);
//	testTree.delete(5);
//	testTree.delete(2);
//	System.out.println(testTree.find(5));
//	System.out.println(testTree.toString());
	

}
}