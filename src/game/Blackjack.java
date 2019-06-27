package game;

import java.util.Scanner;

import cards.Deck;
import cards.Hand;

public class Blackjack {
	
	private HumanPlayer h;
	private RobotPlayer r;
	private Dealer d;
	private Deck newDeck;
	private boolean playerHasMoves = true;
	
	//Variables for measuring the number of wins/draws/losses 
	//when simulating blackjack games with the RobotPlayer class.
	public static int gamesPlayed = 0;
	public static int robotWins = 0;
	public static int robotDraws = 0;
	
	//Instantiate objects ready to simulate an AI game of blackjack
	public void createRobotGame() {
		r = new RobotPlayer();
		d = new Dealer();
		newDeck = new Deck();
	}
	
	//Instantiate objects ready to play a human game of blackjack
	public void createHumanGame() {
		h = new HumanPlayer();
		d = new Dealer();
		newDeck = new Deck();
	}
	
	//Deal the first 2 cards to each player in the game
	private void dealFirstHands(Player p, Dealer d) {
		p.hit(newDeck.deal());
		d.hit(newDeck.deal());
		p.hit(newDeck.deal());
		d.hit(newDeck.deal());
	}
	
	//Prints the current hands and cards in play
	public void printGameState(Hand player, Hand dealer, boolean playerDone) {	
		if (playerDone) {
			System.out.println("The dealers hand:  \t" + dealer.getHand() + " -> " + dealer.getTotalScore());
			System.out.println("Your hand:  \t\t" + player.getHand() + " -> " + player.getTotalScore() + "\n");
		} else {
			System.out.println("The dealer has the  \t" + dealer.getHand().get(0));
			System.out.println("Your hand:  \t\t" + player.getHand() + " -> " + player.getTotalScore() + "\n");
		}
	}
	
	//Prints the outcome of the hand
	public static void printEndGame(int winner) {
		if (winner == 0) {
			System.out.println("The Dealer wins the hand.");
		} else if (winner == 1) {
			System.out.println("The hand is a draw.");
		} else {
			System.out.println("The Player wins the hand.");
		}
	}
	
	//Plays out a hand of blackjack with a human player against the dealer
	public void playHumanGame() {
				
		createHumanGame();
		dealFirstHands(h, d);
		printGameState(h.getHand(), d.getHand(), false);
		
		Scanner scan = new Scanner(System.in);
		
		//Begin the human player's moves
		while (playerHasMoves && !h.isBust()) {
			System.out.println("Press 1 to hit, or any other key to stand.");
			int move = scan.nextInt();
			
			if (move == 1) {
				h.hit(newDeck.deal()); //hit
				printGameState(h.getHand(), d.getHand(), false);
			} else {
				playerHasMoves = false; //or stick
			}		
		}
		scan.close();
		
		if (h.isBust()) {
			printEndGame(0);
			gamesPlayed++;
		} else {
			printGameState(h.getHand(), d.getHand(), true);
			
			//Begin the dealer's moves
			while (d.shouldHit() && !d.isBust()) {
				d.hit(newDeck.deal());
				printGameState(h.getHand(), d.getHand(), true);
			}	
			
			if (d.isBust()) {
				printEndGame(2);
				robotWins++;
				gamesPlayed++;
			} else {
				int winner = calculateWinner(d.getHand(), h.getHand());
				printEndGame(winner);
			}
		}
			
	}
	
	//Plays out a hand of blackjack with an AI player against the dealer
	public void playRobotGame() {
		
		createRobotGame(); //Initialise the players and a deck of cards
		dealFirstHands(r, d); //Deal 2 cards to each player
		printGameState(r.getHand(), d.getHand(), false); //Display the cards that have been dealt
		
		while (r.shouldHit(d.getHand().getHand().get(0)) && !r.isBust()) {
			r.hit(newDeck.deal());
			printGameState(r.getHand(), d.getHand(), false);		
		}
		
		if (r.isBust()) {
			printEndGame(0);
			gamesPlayed++;
		} else {
			printGameState(r.getHand(), d.getHand(), true);
			while (d.shouldHit() && !d.isBust()) {
				d.hit(newDeck.deal());
				printGameState(r.getHand(), d.getHand(), true);
			}
			
			if (d.isBust()) {
				printEndGame(2);
				robotWins++;
				gamesPlayed++;
			} else {
				int winner = calculateWinner(d.getHand(), r.getHand());
				printEndGame(winner);
			}
		}
			
	}
	
	//Calculates the winner of the hand based on both hand scores
	public static int calculateWinner(Hand dealerHand, Hand playerHand) {
		
		int playerScore = playerHand.getTotalScore();
		int dealerScore = dealerHand.getTotalScore();
		gamesPlayed++;
		
		if (playerScore > 21 || playerScore < dealerScore || (dealerHand.isBlackjack() && !playerHand.isBlackjack())) {
			return 0;
		} else if (playerScore == dealerScore || playerHand.isBlackjack() && dealerHand.isBlackjack()){
			robotDraws++;
			return 1;
		} else {
			robotWins++;
			return 2;
		}
	}

}
