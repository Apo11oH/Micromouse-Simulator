/*
 * Name of program: Homework #7
 * Name of programmer: Ken Yokoyama
 * Description: class for the keno game
 * Variable: MAX_KENO_NUMBER - max amount of numbers on the keno board
 * 			DRAWN_NUMBER - max number of numbers to be drawn
 * 			payoff - 2-dim array of doubles for the payoff of the game
 * 			newBoard - reference to the KenoBoard class
 * 			newPlayer - reference to the KenoPlayer class
 */

public class MicromouseRun {
	public static final int BOARD_MAX = 16;
	public static final int	GOAL = BOARD_MAX/2-1;
	PotentialBoard newPotBoard;
	private Droid droid;
	private MazeMap mazeMap;
	
	// constructor (default)
	public MicromouseRun(){
		newPotBoard = new PotentialBoard();
		droid = new Droid();
		mazeMap = new MazeMap();
	}
	
	public int getPotential(int locx, int locy){
		return newPotBoard.getPotential(locx, locy);
	}
	
	public int getCurLocX(){
		return droid.getCurLocX();
	}
	
	public int getCurLocY(){
		return droid.getCurLocY();
	}
	
	public String getCurrentLoc(){
		int val = droid.getCurDirec();
		String retString = "";
		switch(val){
			case 0: retString = "North"; break;
			case 1: retString = "East"; break;
			case 2: retString = "South"; break;
			case 3: retString = "West"; break;
		}
		return retString;
	}
	
	public void updatePotential(){
		
	}

	public int getMazeVal(int locx, int locy){
		return mazeMap.getMazeVal(locx, locy);
	}
	
	public void createMaze(String filepath){
		mazeMap.createMaze(filepath);
	}
}
