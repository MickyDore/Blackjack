package game;

import cards.Card;
import cards.Hand;

public class RobotPlayer implements Player {
	
	private Hand hand;
	
	public RobotPlayer() {
		this.hand = new Hand();
	}
	
	public void hit(Card c) {
		this.hand.addToHand(c);
	}
	
	public Hand getHand() {
		return hand;
	}
	
	//Is the user out of playable moves.
	public boolean isBust() {
		return (this.hand.getTotalScore() > 21);
	}
	
	public boolean shouldHit() {
		
		int handScore = getHand().getTotalScore();
		if (handScore >= 17) {
			return false;
		}
		
		return true;
	}
}
