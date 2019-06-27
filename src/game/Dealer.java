package game;

import cards.Card;
import cards.Hand;

public class Dealer implements Player {
	
	private Hand hand;
	
	public Dealer() {
		this.hand = new Hand();
	}
	
	public void hit(Card c) {
		this.hand.addToHand(c);
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public boolean shouldHit() {
		if (getHand().getTotalScore() >= 17) {
			return false;
		}
		return true;
	}
	
	public boolean isBust() {
		return (this.hand.getTotalScore() > 21);
	}
}
