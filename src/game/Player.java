package game;

import cards.Card;
import cards.Hand;

public interface Player {
	
	/**
	 * Retrieves the players hand
	 * @return The players hand
	 */   
	Hand getHand();
	
	/**
	 * Retrieves the players hand
	 * param c Add new card c to the players hand
	 */  
	void hit(Card c);
	
	/**
	 * Calculates whether the players hand is bust
	 * @return A boolean determining if the players hand is over 21 points
	 */  
	boolean isBust();
}
