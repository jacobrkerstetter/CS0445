import java.io.*;
import java.util.*;

// just generates all the strings & prints them as they are generated

public class Boggle
{
	static String[][] board;
	static long startTime,endTime; // for timing
	static final long MILLISEC_PER_SEC = 1000;

	// define your dictionary set and and your hits set UP HERE as TreeSets
	static TreeSet<String> dictionary = new TreeSet<String>();
	static TreeSet<String> matches = new TreeSet<String>();

	public static void main( String args[] ) throws Exception
	{
		startTime= System.currentTimeMillis();
		board = loadBoard( args[1] );

		// INITIALIZE DICT AND HITS HERE
		Scanner dictScanner = new Scanner(new File(args[0]));

		while (dictScanner.hasNext())
			dictionary.add(dictScanner.next());

		dictScanner.close();

		// iterate over every starting character
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs( row, col, ""  );

		// PRINT OUT YOUR SORTED HITS CONTAINER ONE WORD PER LINE
		for (String match : matches)
			System.out.println(match);

		endTime =  System.currentTimeMillis(); // for timing
		//System.out.println("GENERATION COMPLETED: runtime=" + (endTime-startTime)/MILLISEC_PER_SEC );

	} // END MAIN ----------------------------------------------------------------------------

	static void dfs( int r, int c, String word  )
	{
		word += board[r][c];

		// heuristic
		if (!dictionary.ceiling(word).contains(word)) {
			return;
		}

		if (word.length() > 2 && dictionary.contains(word)) {
			dictionary.remove(word);
			matches.add(word);
		}

		// THIS IS THE FORM OF EACH OF YOUR N,NE,E,SE,S,SW,W,NW BLOCKS
		if (r - 1 >= 0 && board[r-1][c] != null) { // N
			String temp = board[r][c];
			board[r][c] = null;
			dfs(r - 1, c, word);
			board[r][c] = temp;
		}
		if (r - 1 >= 0 && c + 1 < board.length && board[r-1][c+1] != null) { // NE
			String temp = board[r][c];
			board[r][c] = null;
			dfs(r - 1, c + 1, word);
			board[r][c] = temp;
		}
		if (c + 1 < board.length && board[r][c+1] != null) { // E
			String temp = board[r][c];
			board[r][c] = null;
			dfs(r, c + 1, word);
			board[r][c] = temp;
		}
		if (r + 1 < board.length && c + 1 < board.length && board[r+1][c+1] != null) { // SE
			String temp = board[r][c];
			board[r][c] = null;
			dfs(r + 1, c + 1, word);
			board[r][c] = temp;
		}
		if (r + 1 < board.length && board[r+1][c] != null) { // S
			String temp = board[r][c];
			board[r][c] = null;
			dfs(r + 1, c, word);
			board[r][c] = temp;
		}
		if (r + 1 < board.length && c - 1 >= 0 && board[r+1][c-1] != null) { // SW
			String temp = board[r][c];
			board[r][c] = null;
			dfs(r + 1, c - 1, word);
			board[r][c] = temp;
		}
		if (c - 1 >= 0 && board[r][c-1] != null) {
			String temp = board[r][c];
			board[r][c] = null;
			dfs(r, c - 1, word);
			board[r][c] = temp;
		}
		if (r - 1 >= 0 && c - 1 >= 0 && board[r-1][c-1] != null) {
			String temp = board[r][c];
			board[r][c] = null;
			dfs(r - 1, c - 1, word);
			board[r][c] = temp;
		}
	} // END DFS ----------------------------------------------------------------------------

	//=======================================================================================
	static String[][] loadBoard( String fileName ) throws Exception
	{	Scanner infile = new Scanner( new File(fileName) );
		int rows = infile.nextInt();
		int cols = rows;
		String[][] board = new String[rows][cols];
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
				board[r][c] = infile.next();
		infile.close();
		return board;
	} //END LOADBOARD

} // END BOGGLE CLASS
