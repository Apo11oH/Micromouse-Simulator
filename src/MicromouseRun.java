/**
 * Filename: MicromouseRun.java
 * Programmer: Apo11oH
 * Description:
 */

public class MicromouseRun {
	public static final int BOARD_MAX = 16;
	public static final int	GOAL = BOARD_MAX/2-1;
	private Droid droid;
	private MazeMap mazeMap;
	
	// constructor (default)
	public MicromouseRun(){
        mazeMap = new MazeMap();
		droid = new Droid();
	}

    /*
     *  Droid related functions
     */
	public int getCurLocX(){
		return droid.getCurLocX();
	}
	
	public int getCurLocY(){
		return droid.getCurLocY();
	}
	
	public String getCurrentDirLable(){
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

    /*
     *  Maze building related functions
     */
	public int getMazeVal(int locx, int locy){
		return mazeMap.getMazeVal(locx, locy);
	}
	
	public void createMaze(String filepath){
		mazeMap.createMaze(filepath);
	}

    /*
     *  Traversal map related functions
     */


    /*
     *  Potential map related functions
     */
    public int getPotential(int locx, int locy){
        return droid.getPotential(locx, locy);
    }
}
