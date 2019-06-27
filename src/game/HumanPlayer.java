package game;

import cards.Card;
import cards.Hand;

public class HumanPlayer implements Player {
	
	private Hand hand;
	
	public HumanPlayer() {
		this.hand = new Hand();
	}
	
	public void hit(Card c) {
		this.hand.addToHand(c);
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public boolean isBust() {
		return (this.hand.getTotalScore() > 21);
	}
	
}
