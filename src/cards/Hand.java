package cards;

import java.util.ArrayList;

public class Hand {
	
	private int totalHandScore = 0;
	private boolean softHand = false;
	
	private ArrayList<Card> hand;
	
	//Constructor for a new hand
	public Hand() {
		hand = new ArrayList<>(); 
	}
	
	//Adds the score of a new card to the current hand total
	public void addToTotalScore(int score) {
		this.totalHandScore += score;
	}
	
	//Returns the current total score of the hand
	public int getTotalScore() {
		return this.totalHandScore;
	}
	
	//Adds a card to the hand and also adds the correct 
	//number of points to the total hand score
	public void addToHand(Card c) {
		if (c.getRank() == Card.Rank.ACE) {
			if (hasAce()) {
				totalHandScore += 1;
				hand.add(c);
				softHand = true;
			}
			else {
				totalHandScore += c.getRank().getValue();
				hand.add(c);
			}
		} else {
			totalHandScore += c.getRank().getValue();
			hand.add(c);
		}
		
		if ((totalHandScore > 21) && hasAce() && !softHand) {
			totalHandScore -= 10;
			softHand = true;
		}
	}
	
	//Checks whether the hand contains an ace.
	public boolean hasAce() {
		for (Card c : hand) {
			if (c.getRank() == Card.Rank.ACE) {
				return true;
			}
		}		
		return false;
	}
	
	//Is the user out of playable moves.
	public boolean isBust() {
		return (this.totalHandScore > 21) && !hasAce();
	}
	
	//Checks whether the player has got blackjack.
	public boolean isBlackjack() {
		return (this.totalHandScore == 21) && (this.hand.size() == 2);
	}
	
	//Returns the hand as an ArrayList of cards
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	public String toString() {
		return hand.toString();
	}
}
