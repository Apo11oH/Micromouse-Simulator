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
	private PotentialBoard potential;
    private TraversalMap traversal;
	private Droid droid;
	private MazeMap mazeMap;
	
	// constructor (default)
	public MicromouseRun(){
        potential = new PotentialBoard();
        traversal = new TraversalMap();
		droid = new Droid();
		mazeMap = new MazeMap();
	}
	
	public int getPotential(int locx, int locy){
		return potential.getPotential(locx, locy);
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

    public void setTraversalMap(int locx, int locy, int curDirec){
        int front=0;
        int left=0;
        int right=0;

        switch (curDirec) {
            case 0: front=0; right=1; left=3; break;
            case 1: front=1; right=2; left=0; break;
            case 2: front=2; right=3; left=1; break;
            case 3: front=3; right=0; left=2; break;
        }

        if( (mazeMap.getMazeVal(locx, locy) & 0x01) != 0x00 ){
            traversal.setWall(locx, locy, front);
        }
        if( (mazeMap.getMazeVal(locx, locy) & 0x02) != 0x00 ){
            traversal.setWall(locx, locy, right);
        }
        if( (mazeMap.getMazeVal(locx, locy) & 0x08) != 0x00 ){
            traversal.setWall(locx, locy, left);
        }
    }

	public int getMazeVal(int locx, int locy){
		return mazeMap.getMazeVal(locx, locy);
	}
	
	public void createMaze(String filepath){
		mazeMap.createMaze(filepath);
	}
}
