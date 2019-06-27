package cards;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	
	public static ArrayList<Card> deck;
	
	//Constructor for a new deck of cards
	public Deck() {
		newDeck();
	}
	
	//Method to create a new deck of 52 shuffled Card objects.
    public static void newDeck() {
        deck = new ArrayList<>();

        //For every suit...
        for (int i = 0; i < Card.Suit.values().length; i++) { 
            //Make 13 different cards with different ranks
            for (int j = 0; j < Card.Rank.values().length; j++) { 
                Card card = new Card(Card.Rank.values()[j],
                        Card.Suit.values()[i]);
                deck.add(card); //add the card to the deck
            }
        }
        Collections.shuffle(deck); //shuffles the deck of cards.
    }
    

    //Retrieve the card from the top of the deck
    public Card deal() {
        return deck.remove(0);
    }
    
    public String toString() {
    	return deck.toString();
    }

}
