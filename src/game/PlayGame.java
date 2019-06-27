package game;

public class PlayGame {
	
	static Blackjack bj = new Blackjack();
	
	public static void main(String[] args) {
		
		//Uncomment the following statement to play a human based game of blackjack
		bj.playHumanGame();
		
		//Uncomment the following statement to play out a robot based game of blackjack
		//bj.playRobotGame();
	}

}
