/**
 * Name of programmer: ApolloH
 * Description: Micromouse simulator text runner
 * Variable: BOARD_MAX - Max dimension of the board
 *          GOAL - Goal location
 *          potential - potential map module
 *          traversal - traversal map module
 *          droid - droid module
 *          mazeMap - maze module
 */

public class MicromouseRun {
	public static final int BOARD_MAX = 16;
	public static final int	GOAL = BOARD_MAX/2-1;
	private PotentialBoard potential;
    private TraversalMap traversal;
	private Droid droid;
	private MazeMap mazeMap;

    /**
     * Constructor
     */
	public MicromouseRun(){
        potential = new PotentialBoard();
        traversal = new TraversalMap();
		droid = new Droid();
		mazeMap = new MazeMap();
	}

    /**
     * Get the potential value of a specific cell from the potential variable
     * @param locx: Specified x-coordinate
     * @param locy: Specified y-coordinate
     * @return Potential value of a specific cell
     */
	public int getPotential(int locx, int locy){
		return potential.getPotential(locx, locy);
	}

    /**
     * Gets the current x-coordinate of the droid
     * @return x-coordinate
     */
	public int getCurLocX(){
		return droid.getCurLocX();
	}

    /**
     * Gets the current y-coordinate of the droid
     * @return y-coordinate
     */
	public int getCurLocY(){
		return droid.getCurLocY();
	}

    /**
     * Gets the corresponding label for the current direction
     * @return Label of direction the droid is facing
     */
	public String getCurrentDirec(){
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

    /**
     * Updates potential map based on the traversal map.
     */
	public void updatePotential(){
		
	}

    /**
     * Sets the value for a cell in the traversal map based on the
     * sensor values (is there a wall or not?)
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     * @param curDirec Current direction of the droid
     */
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

    /**
     * Gets the wall value for a specified cell
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     * @return Wall value for the specified location
     */
	public int getMazeVal(int locx, int locy){
		return mazeMap.getMazeVal(locx, locy);
	}

     /**
     * Instantiates the maze
     * @param filepath Path to the file containing maze data
     */
	public void createMaze(String filepath){
		mazeMap.createMaze(filepath);
	}
}
