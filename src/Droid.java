/**
 * Name of programmer: ApolloH
 * Description: Droid module
 * Variable: curDirec - current direction of the droid
 *          curLocX - current x-coordinate location of the droid
 *          curLocY - current y-coordinate location of the droid
 */

public class Droid {
	private int curDirec;
	private int curLocX;
	private int curLocY;
    private PotentialMap potential;
    private TraversalMap traversal;

    /**
     * Default constructor
     */
	public Droid(){
		curDirec = 0;
		curLocX = 0;
		curLocY = MicromouseRun.BOARD_MAX-1;
        potential = new PotentialMap();
        traversal = new TraversalMap();
	}

    /**
     * Getter for the current droid direction
     * @return current direction
     */
	public int getCurDirec(){ return curDirec; }

    /**
     * Getter for the current x-coordinate for the droid
     * @return current x-coordinate
     */
	public int getCurLocX(){ return curLocX; }

    /**
     * Getter for the current y-coordinate for the droid
     * @return current y-coordinate
     */
	public int getCurLocY(){ return curLocY; }

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
     * Sets the value for a cell in the traversal map based on the
     * sensor values (is there a wall or not?)
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     * @param curDirec Current direction of the droid
     */
    public void setTraversalMap(int locx, int locy, int curDirec, MazeMap mazeMap){
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
        }else{
            traversal.setChecked(locx, locy, front);
        }
        if( (mazeMap.getMazeVal(locx, locy) & 0x02) != 0x00 ){
            traversal.setWall(locx, locy, right);
        }else{
            traversal.setChecked(locx, locy, right);
        }
        if( (mazeMap.getMazeVal(locx, locy) & 0x08) != 0x00 ){
            traversal.setWall(locx, locy, left);
        }else{
            traversal.setChecked(locx, locy, left);
        }
    }

    public int chooseTurn(){
        int [] neighborhood = {0, 0, 0, 0};
        int smallest = 0;
        int index = 0;

        if( (traversal.getTraversal(curLocX, curLocY)&0x01) != 0 ){
            neighborhood[0] = potential.getPotential(curLocX, curLocY);
        }
        if( (traversal.getTraversal(curLocX, curLocY)&0x02) != 0 ){
            neighborhood[1] = potential.getPotential(curLocX, curLocY);
        }
        if( (traversal.getTraversal(curLocX, curLocY)&0x04) != 0 ){
            neighborhood[2] = potential.getPotential(curLocX, curLocY);
        }
        if( (traversal.getTraversal(curLocX, curLocY)&0x08) != 0 ){
            neighborhood[3] = potential.getPotential(curLocX, curLocY);
        }

        smallest = neighborhood[0];
        for(int i=1; i<4; i++){
            if( smallest > neighborhood[i] ){
                smallest = neighborhood[i];
                index = i;
            }
        }

        return index;
    }

    /**
     * Droid make a move
     */
    public void makeNextMove(MazeMap mazeMap){
        // Scan area and update Traversal Map
        setTraversalMap(curLocX, curLocY, curDirec, mazeMap);
        // Update Potential Map
        potential.updatePotential(curLocX, curLocY, traversal);
        // Decide where to go/make a turn

        // Update coordinates

        // Does above action

    }

    /**
     * Resets all the variables
     */
    public void resetAll(){
        curDirec = 0;
        curLocX = 0;
        curLocY = MicromouseRun.BOARD_MAX-1;
        traversal.resetTraversal();
        potential.resetPotential();
    }
}
