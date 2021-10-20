import java.io.*;
import java.util.*;

public class LinkedList<T extends Comparable>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print
	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next )
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################



	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		int count = 0;

		for (Node curr = head; curr != null; curr = curr.next)
			count++;

		return count;
	}

	public boolean empty()
	{
		return size() == 0;
	}

	public boolean contains( T key )
	{
		return search(key) != null;
	}

	@SuppressWarnings("unchecked")
	public Node<T> search( T key )
	{
		if (head == null) return null;

		for (Node curr = head; curr != null; curr = curr.next) {
			if (key.equals(curr.data))
				return curr;
		}

		return null;
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		if (head == null) {
			insertAtFront(data);
			return;
		}

		Node curr = head;

		while(curr.next != null)
			curr = curr.next;

		curr.next = new Node<T>(data, null);
	}

	@SuppressWarnings("unchecked")  //CAST TO COMPARABLE THROWS WARNING
	public void insertInOrder(T  data)
	{
		Node curr = head;

		if (head == null) { 
			insertAtFront(data);
			return;
		}

		if (size() == 1) {
			if (data.compareTo(curr.data) < 0) {
				insertAtFront(data);
				return;
			}
			else {
				insertAtTail(data);
				return;
			}
		}

		if (data.compareTo(curr.data) < 0) {
			insertAtFront(data);
			return;
		}

		while (curr.next != null) {
			if (data.compareTo(curr.next.data) > 0)
				curr = curr.next;
			else if (data.compareTo(curr.next.data) < 0) {
				curr.next = new Node<T>(data, curr.next);
				return;
			}
		}

		insertAtTail(data);
	}

	@SuppressWarnings("unchecked")
	public boolean remove(T key)
	{
		if (empty()) return false;

		Node curr = head;

		if (curr.data.equals(key)) return removeAtFront();

		while (curr.next != null) {
			if (curr.next.data.equals(key)) {
				curr.next = curr.next.next;
				return true;
			}

			curr = curr.next;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (empty()) return false;
		if (head.next == null) return removeAtFront();

		Node curr = head;

		while (curr.next.next != null)
			curr = curr.next;

		curr.next = null;
		return true;		
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (empty()) return false;

		head = head.next;
		return true;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> union( LinkedList<T> other )
	{
		LinkedList<T> unionResult = new LinkedList<T>();

		for (Node<T> curr = head; curr != null; curr = curr.next) {
			unionResult.insertAtTail(curr.data);
		}

		for (Node<T> curr = other.head; curr != null; curr = curr.next) {
			if (!unionResult.contains(curr.data)) 
				unionResult.insertInOrder(curr.data);
		}

		return unionResult;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter = new LinkedList<T>();

		for (Node<T> curr = head; curr != null; curr = curr.next) {
			if (other.contains(curr.data))
				inter.insertInOrder(curr.data);
		}

		return inter;
	}
	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> diff = new LinkedList<T>();

		for (Node<T> curr = head; curr != null; curr = curr.next) {
			if (!other.contains(curr.data))
				diff.insertInOrder(curr.data);
		}

		return diff;
	}
	public LinkedList<T> xor( LinkedList<T> other )
	{
		return union(other).diff(inter(other)); 

	}

} //END LINKEDLIST CLASS

class Node<T extends Comparable>
{
  T data;
  Node<T> next;

  Node()
  {
    this( null, null );
  }

  Node(T data)
  {
    this( data, null );
  }

  Node(T data, Node<T> next)
  {
    setData( data );
    setNext( next );
  }

  T getData()
  {
    return data;
  }

  Node<T> getNext()
  {
    return next;
  }

  void setData(T data)
  {
     this.data = data;
  }

  void setNext(Node<T> next)
  {
    this.next = next;
  }
  public String toString()
  {
	  return ""+getData();
  } 
	 
} //EOF