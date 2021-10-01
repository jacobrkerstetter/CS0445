/*
	Deck class (for TopCardPlacer class of project #1
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];
		for ( int i=0 ; i<numCards ; i++ ) deck[i] = i;
	}
	
	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{
		int halfLength = deck.length / 2;
		int[] topHalf = new int[halfLength];
		int[] bottomHalf = new int[halfLength];

		for (int i = 0; i < halfLength; i++) {
			topHalf[i] = deck[i];
			bottomHalf[i] = deck[i + halfLength];
		}

		int j = 0, k = 1;

		for (int i = 0; i < halfLength; i++) {
			deck[j] = bottomHalf[i];
			deck[k] = topHalf[i];

			j+=2;
			k+=2;
		}
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		int halfLength = deck.length / 2;
		int[] topHalf = new int[halfLength];
		int[] bottomHalf = new int[halfLength];

		for (int i = 0; i < halfLength; i++) {
			topHalf[i] = deck[i];
			bottomHalf[i] = deck[i + halfLength];
		}

		int j = 0, k = 1;

		for (int i = 0; i < halfLength; i++) {
			deck[j] = topHalf[i];
			deck[k] = bottomHalf[i];

			j+=2;
			k+=2;
		}	
	}
	
	public String toBitString( int n ) 
	{
		if (n == 0)
			return "";

		int bitsNeeded = (int)(Math.log(n) / Math.log(2)) + 1;
		char[] bits = new char[bitsNeeded];

		int i = 0;
		while (n != 0) {
			bitsNeeded--;
			double twoPow = Math.pow(2.0, (double)bitsNeeded);

			if (twoPow > n) 
				bits[i] = '0';
			else {
				bits[i] = '1';
				n -= twoPow;
			}

			i++;
		}
		return new String(bits);
	}
	
}	// END DECK CLASS