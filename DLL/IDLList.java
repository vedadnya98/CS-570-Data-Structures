package DLL;
import java.util.*;

public class IDLList <E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	private class Node<E>
	{
		E data;
		Node<E> next;
		Node<E> prev;
		
		Node (E elem)
		{
			data = elem;
			this.prev = null;
			this.next = null;
		}
		
		Node (E elem, Node<E> prev, Node<E> next)
		{
			data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	
	public IDLList ()
	{
		this.size = 0;
		this.head = null;
		this.tail = null;
		this.indices = new ArrayList<Node<E>>();
	}
	
	public boolean add (int index, E elem)
	{
		Node<E> Previous;
		Node<E> Next;
		
		boolean check = false;
		if (index < 0 || index > size) 
		{
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		else
		{	
		if(head==null)
		{
			Node<E> addNode = new Node<E>(elem);
			head = addNode;
			tail = addNode;
			size++;
			indices.add(index,addNode);
			check = true;
		}
		if(index==0)
		{
			Next=indices.get(0);
			Node<E> addNode = new Node<E>(elem , null , Next);
			head=addNode;
			Next.prev=addNode;
			size++;
			indices.add(index,addNode);
			check = true;
			
		}
		if(index>0 && index<size-1)
		{
			Previous=indices.get(index-1);
			Next=indices.get(index);
			Node<E> addNode = new Node<E>(elem , Previous , Next);
			Previous.next=addNode;
			Next.prev=addNode;
			size++;
			indices.add(index,addNode);
			check = true;
		}
		if(index==size-1)
		{	
			Previous=indices.get(index-1);
			Node<E> addNode = new Node<E>(elem , Previous , null);
			tail=addNode;
			addNode.prev=Previous;
			size++;
			indices.add(index,addNode);
			check = true;
		}
		if(index==size)
		{	
			Previous=indices.get(size-1);
			Node<E> addNode = new Node<E>(elem , Previous , null);
			addNode.prev = tail;
			tail.next=addNode;
			tail = addNode;
			size++;
			indices.add(index,addNode);
			check = true;
		}
		return check;
		}
	}
	
	public boolean add (E elem)
	{
		Node<E> Next;
		boolean check = false;
		if(size==0)
		{
		Node<E> headNode = new Node<E>(elem);
		head = headNode;
		size++;
		indices.add(0,head);
		check = true;
		}
		else
		{
		Next = indices.get(0);
		Node<E> headNode = new Node<E>(elem ,null , Next);
		head=headNode;
		Next.prev=headNode;
		size++;
		indices.add(0,head);
		check = true;
		}
	return check;
	}
	
	public boolean append (E elem)
	{
		boolean check = false;
		if(size==0)
		{
		Node<E> appendNode = new Node<E>(elem , null , null);
		head = appendNode;
		size++;
		indices.add(0,head);
		check = true;
		return check;
		}
		else
		{
		Node<E> Previous = indices.get(size-1);
		Node<E> appendNode = new Node<E>(elem,Previous , null);
		tail=appendNode;
		Previous.prev=appendNode;
		size++;
		indices.add(size-1 , appendNode);
		check = true;
		return check;
		}
	}
	
	public E get (int index)
	{
		if (index < 0 || index >= size) 
		{
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		else
		{
		Node<E> getNode = indices.get(index);
		return getNode.data;
		}
	}
	
	public E getHead ()
	{
		Node<E> getHeadNode = indices.get(0);
		return getHeadNode.data;
	}
	
	public E getLast ()
	{
		Node<E> getTailNode = indices.get(size-1);
		return getTailNode.data;
	}
	
	public int size()
	{
		return size;
	}
	
	public E remove ()
	{
		Node<E> remHeadNode = indices.get(0);
		Node<E> Next = indices.get(1);
		Next.prev=null;
		head=Next;
		size--;
		indices.remove(0);
		return remHeadNode.data;
	}
	
	public E removeLast ()
	{
		Node<E> remLastNode = indices.get(size-1);
		Node<E> Previous = indices.get(size-2);
		Previous.next=null;
		tail=Previous;
		indices.remove(size-1);
		size--;
		return remLastNode.data;
	}
	
	public E removeAt (int index)
	{
		if (index < 0 || index >= size) 
		{
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		else
		{
		Node<E> remIndexNode = indices.get(index);
		Node<E> Previous;
		Node<E> Next;
		if(index==0)
		{
		remIndexNode = indices.get(0);
		Next = indices.get(1);
		Next.prev=null;
		head=Next;
		size--;
		indices.remove(0);	
		}
		if(index>0||index<size-1)
		{	
		remIndexNode = indices.get(index);
		Previous = indices.get(index-1);
		Next = indices.get(index+1);
		Previous.next=Next;
		Next.prev=Previous;
		size--;
		indices.remove(index);
		}
		if(index == size-1)
		{
		remIndexNode = indices.get(size-1);
		Previous = indices.get(size-2);
		Previous.next=null;
		tail=Previous;
		size--;
		indices.remove(size-1);
		}
		return remIndexNode.data;
		}
	}
	
	public boolean remove (E elem)
	{
		Node<E> remElemNode ;
		Node<E> ElemNode ;
		Node<E> Previous;
		Node<E> Next;
		boolean check = false;
		int elemIndex = 0;
		for(int i=0 ; i<size ; i++)
		{
			ElemNode = indices.get(i);
			if(ElemNode.data.equals(elem))
			{
				elemIndex = i;
			}
			else
			{
				check = false;
			}
		}
		if(elemIndex==0)
		{
		remElemNode = indices.get(0);
		Next = indices.get(1);
		Next.prev=null;
		head=Next;
		size--;
		indices.remove(0);
		check = true;
		}
		if(elemIndex>0||elemIndex<size-1)
		{	
		remElemNode = indices.get(elemIndex);
		Previous = indices.get(elemIndex-1);
		Next = indices.get(elemIndex+1);
		Previous.next=Next;
		Next.prev=Previous;
		size--;
		indices.remove(elemIndex);
		check = true;
		}
		if(elemIndex == size-1)
		{
		remElemNode = indices.get(size-1);
		Previous = indices.get(size-2);
		Previous.next=null;
		tail=Previous;
		size--;
		indices.remove(size-1);
		check = true;
		}
		return check;	
	}
	
	public String toString()
	{
		Node<E> StringNode;
		String list ="";
		for(int i=0 ; i<size ; i++)
		{
			StringNode = indices.get(i);
			list = list+StringNode.data+" ";
		}
	return list;
	}
}
