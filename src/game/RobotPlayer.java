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
	
	public boolean isBust() {
		return (this.hand.getTotalScore() > 21);
	}
	
	public boolean shouldHit(Card dealersCard) {
		
		int handScore = getHand().getTotalScore();
		int dealerScore = dealersCard.getRank().getValue();
		boolean softStartingHand = (getHand().getHand().size() == 2) && getHand().hasAce();
		
		if (handScore >= 17) {
			return false;
		} else if (handScore < 17 && handScore > 11 && dealerScore < 6) {
			return false;
		} else if (handScore <= 11) {
			return true;
		} else if (softStartingHand && handScore >= 13 && handScore <= 17) {
			return true;
		} else {
			return false;
		}
		
	}
}
