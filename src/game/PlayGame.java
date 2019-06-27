package game;

public class PlayGame {
	
	static Blackjack bj = new Blackjack();
	
	public static void main(String[] args) {
		
		//Uncomment the following statement to play a human based game of blackjack
		//bj.playHumanGame();
		
		//Uncomment the following statement to play out a robot based game of blackjack
		//bj.playRobotGame();
		
		//Uncomment the following block of code to simulate 1,000 blackjack games
		//for (int i = 0; i < 1000; i ++) {
		//	bj.playRobotGame();	
		//}
		
		//System.out.println("The robot played: " + bj.gamesPlayed + " games.");
		//System.out.println("The robot won:  " + bj.robotWins + " games.");
		//System.out.println("The robot drew: " + bj.robotDraws + " games.");
		//System.out.println("The robot lost: " + (bj.gamesPlayed - (bj.robotDraws+bj.robotWins)));
	}

}
