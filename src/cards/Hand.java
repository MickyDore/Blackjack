package cards;

import java.util.ArrayList;

public class Hand {
	
	private int totalHandScore = 0;
	private int lowScoreWithAce = 0;
	private boolean softHand = false;
	private ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<>(); 
	}
	
	public void addToTotalScore(int score) {
		this.totalHandScore += score;
	}
	
	public void setLowAceScore(int score) {
		this.lowScoreWithAce = score;
	}
	
	public int getTotalScore() {
		return this.totalHandScore;
	}
	
	public int getLowAceScore() {
		return this.lowScoreWithAce;
	}
	
	
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
	
	//Checks whether a hand contains an ace.
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
	
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	public String toString() {
		return hand.toString();
	}
}
