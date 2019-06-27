package game;

import java.util.Scanner;

import cards.Deck;
import cards.Hand;

public class Blackjack {
	
	public HumanPlayer h;
	public RobotPlayer r;
	public Dealer d;
	public Deck newDeck;
	public boolean playerHasMoves = true;
	
	static int gamesPlayed = 0;
	static int robotWins = 0;
	static int robotDraws = 0;
	
	public void createRobotGame() {
		r = new RobotPlayer();
		d = new Dealer();
		newDeck = new Deck();
	}
	
	public void createHumanGame() {
		h = new HumanPlayer();
		d = new Dealer();
		newDeck = new Deck();
	}
	
	private void dealFirstHands(Player p, Dealer d) {
		p.hit(newDeck.deal());
		d.hit(newDeck.deal());
		p.hit(newDeck.deal());
		d.hit(newDeck.deal());
	}
	
	public void printGameState(Hand player, Hand dealer, boolean playerDone) {
		
		if (playerDone) {
			System.out.println("The dealers hand:  \t" + dealer.getHand() + " -> " + dealer.getTotalScore());
			System.out.println("Your hand:  \t\t" + player.getHand() + " -> " + player.getTotalScore() + "\n");
		} else {
			System.out.println("The dealer has the  \t" + dealer.getHand().get(0));
			System.out.println("Your hand:  \t\t" + player.getHand() + " -> " + player.getTotalScore() + "\n");
		}
	}
	
	public static void printEndGame(int winner) {
		if (winner == 0) {
			System.out.println("The Dealer wins the hand.");
		} else if (winner == 1) {
			System.out.println("The hand is a draw.");
		} else {
			System.out.println("The Player wins the hand.");
		}
	}
	
	
	//Plays out a game of an AI player against the dealer
	public void playHumanGame() {
		
		Scanner scan = new Scanner(System.in);	
		createHumanGame(); //Initialise the players and a deck of cards
		dealFirstHands(h, d); //Deal 2 cards to each player
		printGameState(h.getHand(), d.getHand(), false); //Display the cards that have been dealt
		
		while (playerHasMoves && !h.isBust()) {
			System.out.println("Press 1 to hit, or 0 to stand.");
			int move = scan.nextInt();
			if (move == 1) {
				h.hit(newDeck.deal());
				printGameState(h.getHand(), d.getHand(), false);
			} else {
				playerHasMoves = false;
			}		
		}
		scan.close();
		if (h.isBust()) {
			printEndGame(0);
			gamesPlayed++;
		} else {
			printGameState(h.getHand(), d.getHand(), true);
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
	
	//Plays out a game of an AI player against the dealer
		public void playRobotGame() {
			
			createRobotGame(); //Initialise the players and a deck of cards
			dealFirstHands(r, d); //Deal 2 cards to each player
			printGameState(r.getHand(), d.getHand(), false); //Display the cards that have been dealt
			
			while (r.shouldHit() && !r.isBust()) {
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
