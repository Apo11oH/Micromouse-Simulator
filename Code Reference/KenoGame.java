/*
 * Name of program: Homework #7
 * Name of programmer: Ken Yokoyama
 * Current Date: 12/11/2011
 * Computer/Compiler: Windows 7/Eclipse (JavaSE-1.6)
 * Description: class for the keno game
 * Variable: MAX_KENO_NUMBER - max amount of numbers on the keno board
 * 			DRAWN_NUMBER - max number of numbers to be drawn
 * 			payoff - 2-dim array of doubles for the payoff of the game
 * 			newBoard - reference to the KenoBoard class
 * 			newPlayer - reference to the KenoPlayer class
 */

public class KenoGame implements NumberGameInterface {
	public static final int MAX_KENO_NUMBER = 80;
	public static final int DRAWN_NUMBER = 20;
	public static double [][] payoff = {
		{0},
		{0, 2.75},
		{0, 1., 5.},
		{0, 0, 2.50, 25.00},
		{0, 0, 1.0, 5.0, 80.0},
		{0, 0, 0, 2.0, 10.0, 600.0},
		{0, 0, 0, 1.0, 8.0, 50., 1499.0},
		{0, 0, 0, 0, 5.0, 10.0, 250., 1500.0},
		{0, 0, 0, 0,4.00, 8.00, 40.00, 400.00, 10000.00},
		{0, 0, 0, 0, 2.00, 5.00, 20.00, 80.00, 2500.00, 15000.00},
		{0, 0, 0, 0, 0, 0, 2.00, 30.00, 100.00, 500.00, 3000.00, 17500.00},
		{0, 0, 0, 0, 0, 0, 2.00, 15.00, 50.00, 80.00, 800.00, 8000.00, 27500.00},
		{0, 0, 0, 0, 0, 0, 1.00, 5.00, 30.00, 90.00, 500.00, 2500.00, 15000.00, 30000.00},
		{0, 0, 0, 0, 0, 0, 1.00, 2.00, 10.00, 50.00, 500.00, 1000.00, 10000.00, 15000.00, 30,000.00},
		{0, 0, 0, 0, 0, 0, 2.00, 8.00, 32.00, 300.00, 800.00, 2500.00, 12000.00, 18000.00, 30000.00},
		{0, 0, 0, 0, 0, 0, 1.00, 7.00, 21.00, 100.00, 400.00, 2000.00, 8000.00, 12000.00, 25000.00, 30,000.}
	};
	private KenoBoard newBoard;
	private KenoPlayer newPlayer;
	
	// constructor (default)
	public KenoGame(){
		newBoard = new KenoBoard();
	}
	
	// method: instantiate new KenoPlayer
	public void createPlayer(int numbers, int bet){
		newPlayer = new KenoPlayer(numbers, bet);
	}
	
	// method: set one spot in KenoPlayer's MarkedSpot
	public int setOneSpot(int inSpot){
		if(inSpot>0 && inSpot < MAX_KENO_NUMBER+1){
			if(newPlayer == null)
				return -2;
			else{
				if(newPlayer.setSpot(newBoard.getKenoSpot(inSpot)))
					return 0;
				else
					return 1;
			}
		}
		else
			return -1;
	}
	
	// method: return length of KenoPlayer's markedSpots array
	public int playerArrayLength(){
		return newPlayer.getArrayLength();
	}
	
	// override: drawNumbers
	public void drawNumbers() {
		newBoard.resetAll();
		newBoard.choseSpots();
	}

	// override: calcResults
	public void calcResults() {
		newPlayer.calcNumMatches();
		newPlayer.calcWinnings();
	}

	// method: get matches
	public int getMatches(){ return newPlayer.getMatches(); }
	
	// method: get winnings
	public double getWinnings(){ return newPlayer.getWinnings(); }
	
	// method: get drawn spots
	public int [] getDrawnSpots(){ return newBoard.getDrawnSpots(); }
	
	// override: displayResults
	public void displayResults() {
		System.out.println(newBoard.toString());
		System.out.println(newPlayer.toString());
	}

}
