package cards;

public class Card {
	
	final private Suit SUIT;
	final private Rank RANK;
	
	public static enum Suit {
		CLUBS, SPADES, DIAMONDS, HEARTS
	};
	
	public static enum Rank {
		//Values for a Rank of a card.
        TWO(2), THREE(3), FOUR(4), FIVE(5),
        SIX(6), SEVEN(7), EIGHT(8), NINE(9), 
        TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

        private int value;

        //Constructor for a Card Rank.
        Rank(int value) {
            this.value = value;
        }

        //Accessor method to return the value of a Rank.
        public int getValue() {
            return value;
        }
	};
	
	public Suit getSuit() {
		return this.SUIT;
	};
	
	public Rank getRank() {
		return this.RANK;
	};
	
	public Card(Rank rank, Suit suit) {
		this.SUIT = suit;
		this.RANK = rank;
	}

	public String toString() {
		return this.RANK + " of " + this.SUIT;
	}
	
}
