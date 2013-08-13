/**
 * Name of programmer: ApolloH
 * Description: Droid module
 * Variable: curDirec - current direction of the droid
 *          curLocX - current x-coordinate location of the droid
 *          curLocY - current y-coordinate location of the droid
 */

public class Droid {
    private int prevDirec;
	private int curDirec;
    private int nxtDirec;
	private int curLocX;
	private int curLocY;
    private int mazeVal;
    private PotentialMap potential;
    private TraversalMap traversal;
    private String dialogue;
    private int front, left, right;

    /**
     * Default constructor
     */
	public Droid(){
        prevDirec = 0;
		curDirec = 0;
        nxtDirec = 0;
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
     * Get the potential value of a specific cell from the potential map
     * @param locx: Specified x-coordinate
     * @param locy: Specified y-coordinate
     * @return Potential value of a specific cell
     */
    public int getPotential(int locx, int locy){
        return potential.getPotential(locx, locy);
    }

    /**
     * Get the traversal value of a specific cell from the traversal map
     * @param locx: Specified x-coordinate
     * @param locy: Specified y-coordinate
     * @return Potential value of a specific cell
     */
    public int getTraversal(int locx, int locy){
        return traversal.getTraversal(locx, locy);
    }

    /**
     * Get dialogue for GUI
     * @return Message for user
     */
    public String getDialogue(){
        return dialogue;
    }

    /**
     * Sets the value for a cell in the traversal map based on the
     * sensor values (is there a wall or not?)
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     */
    public void setTraversalMap(int locx, int locy, MazeMap mazeMap){
        int frontM, rightM, leftM;
        mazeVal = mazeMap.getMazeVal(locx, locy);
        System.out.format("MazeVal(%d, %d): %x\n", locx, locy, mazeVal);
        switch (curDirec) {
            case 0: front=0; right=1; left=3; frontM=0x01; rightM=0x02; leftM=0x08; break;
            case 1: front=1; right=2; left=0; frontM=0x02; rightM=0x04; leftM=0x01;break;
            case 2: front=2; right=3; left=1; frontM=0x04; rightM=0x08; leftM=0x02;break;
            case 3: front=3; right=0; left=2; frontM=0x08; rightM=0x01; leftM=0x04;break;
            default: front=0; right=1; left=3; frontM=0x01; rightM=0x02; leftM=0x08;
        }
        System.out.format("(F, R, L)=(%d, %d, %d)\n", front, right, left);

        // TODO fix navigation
        // when scanning, mask is not relative to the direction
        if( (mazeVal & frontM) == frontM ){
            traversal.setWall(locx, locy, front);
        }else{
            traversal.setChecked(locx, locy, front);
        }
        if( (mazeVal & rightM) == rightM ){
            traversal.setWall(locx, locy, right);
        }else{
            traversal.setChecked(locx, locy, right);
        }
        if( (mazeVal & leftM) == leftM ){
            traversal.setWall(locx, locy, left);
        }else{
            traversal.setChecked(locx, locy, left);
        }
    }

    /**
     * Choose next direction to move
     * @return Next direction
     */ // TODO chooseTurn working?
    public int chooseTurn(){
        int [] neighborhood = new int[4];
        int travVal = traversal.getTraversal(curLocX, curLocY);
        int smallest;
        int index = 0;

        for(int i=0; i<4; i++){
            neighborhood[i] = (MicromouseRun.BOARD_MAX*MicromouseRun.BOARD_MAX);
        }

        if( (travVal&0x01) != 0 ){
            neighborhood[0] = potential.getPotential(curLocX, curLocY);
        }
        if( (travVal&0x02) != 0 ){
            neighborhood[1] = potential.getPotential(curLocX, curLocY);
        }
        if( (travVal&0x04) != 0 ){
            neighborhood[2] = potential.getPotential(curLocX, curLocY);
        }
        if( (travVal&0x08) != 0 ){
            neighborhood[3] = potential.getPotential(curLocX, curLocY);
        }

        smallest = neighborhood[0];
        System.out.format("(%d", neighborhood[0]);
        for(int i=1; i<4; i++){
            System.out.format(",%d", neighborhood[i]);
            if( smallest > neighborhood[i] ){
                smallest = neighborhood[i];
                index = i;
            }
        }
        System.out.println(") & Index: " + index);

        return index;
    }

    /**
     * Update the current coordinates of the droid
     */
    public void updateCoordinates(){
        if(prevDirec != nxtDirec){
            prevDirec = curDirec;
            switch(nxtDirec){
                // Keep current direction
                case 0: handleCoordinates();
                        curDirec = nxtDirec;
                        break;
                // Make 90 degree turn to the right
                case 1: curDirec = nxtDirec; break;
                // Make 180 degree turn
                case 2: curDirec = nxtDirec; break;
                // Make 270 degree turn to the right
                // a.k.a. 90 degree turn to the left
                case 3: curDirec = nxtDirec; break;
            }
        }else{
            handleCoordinates();
            curDirec = nxtDirec;
        }
    }

    /**
     * Droid make a move
     */
    public void makeNextMove(MazeMap mazeMap){
        // Scan area and update Traversal Map
        setTraversalMap(curLocX, curLocY, mazeMap);
        // Update Potential Map
        potential.updatePotential(curLocX, curLocY, traversal);
        // Decide where to go/make a turn
        nxtDirec = chooseTurn();
        // Update coordinates
        updateCoordinates();
        // Set dialogue
        dialogue = "Front: " + front + ", Right: " + right + ", Left: " + left + ", Next Direc: " + nxtDirec
                    + ", MazeVal: " + mazeVal;
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

    /**
     * Modify x,y-coordinates based on current direction
     */
    private void handleCoordinates(){
        switch(curDirec){
            case 0: curLocY--; break;
            case 1: curLocX++; break;
            case 2: curLocY++; break;
            case 3: curLocX--; break;
        }
    }

    /**
     * Calculates turn based on current and next direction
     * (For actual droid implementation)
     * @return The amount need to turn
     */
    //private int calcTurn(){
    //    return Math.abs(curDirec-nxtDirec);
    //}
}
