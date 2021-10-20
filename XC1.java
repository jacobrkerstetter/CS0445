/*
	THE STARTER FILE FOR 445 XC1  MISSING NUMBER PROBLEm
*/

import java.util.*;
import java.io.*;

public class XC1
{
	public static void main(String[] args) throws Exception
	{	
		if ( args.length < 1 ) 
		{
			System.out.println("MISSING INPUT FILE ON CMD LINE\n");
			System.exit(0);
		}
		Scanner infile = new Scanner( new File( args[0] ) );
		
		// DO THE WHOLE PROGRAM INSIDE THIS LOOP
		Node<Integer> head = null;
		while ( infile.hasNextLine() ) // assume line ->  "1 9 2 8 3 10 7 4 6"  (i.e. its missing 5)
		{
			String[]tokens = infile.nextLine().split("\\s+"); // tokens -> ["1"]["9"]["2"]["8"]["3"]["10"]["7"]["4"]["6"]

			int min = 0, max = 0, total = 0, sumOfDigits = 0, missing = 0;
			for( int i=0 ; i<tokens.length ; ++i )
			{
				int number = Integer.parseInt( tokens[i] ); // convert. "7" to 7
				System.out.print(number + " ");

				head = new Node<Integer>(number, head);

				if (number > max) max = number;
				else if (number < min) min = number; 

				total += number;
			}
			
			sumOfDigits = ((max * (max + 1) - min) / 2);
			missing = sumOfDigits - total;

			System.out.print("missing " + missing + "\n"); // or whatever the actual missing number is 
			
			head = null;
		} // END WHILE THER IS A ANOTHER LINE IN THE FILE 
	} // END MAIN
} // END CLASS XC1

class Node<T>
{
  private T data;
  private Node<T> next;

  public Node()
  {
    this( null, null );
  }

  public Node(T data)
  {
    this( data, null );
  }

  public Node(T data, Node<T> next)
  {
    setData( data );
    setNext( next );
  }

  public T getData()
  {
    return data;
  }

  public Node<T> getNext()
  {
    return next;
  }

  public void setData(T data)
  {
     this.data = data;
  }

  public void setNext(Node<T> next)
  {
    this.next = next;
  }
  public String toString()
  {
	  return ""+getData();
  } 
	 
} //EOF
