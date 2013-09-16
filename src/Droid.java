/**
 * Name of programmer: ApolloH
 * Description: Droid module
 * Variable: curDirec - current direction of the droid
 *          curLocX - current x-coordinate location of the droid
 *          curLocY - current y-coordinate location of the droid
 */

public class Droid {
	private int curDirec;
    private int nxtDirec;
	private int curLocX;
	private int curLocY;
    private int mazeVal;
    private PotentialMap potential;
    private TraversalMap traversal;
    private String dialogue;
    private int front, left, right;
    private boolean turn;
    private boolean goal;

    /**
     * Default constructor
     */
	public Droid(){
        goal = false;
        turn = false;
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
        //System.out.format("MazeVal(%d, %d): %x\n", locx, locy, mazeVal);
        switch (curDirec) {
            case 0: front=0; right=1; left=3; frontM=0x01; rightM=0x02; leftM=0x08; break;
            case 1: front=1; right=2; left=0; frontM=0x02; rightM=0x04; leftM=0x01;break;
            case 2: front=2; right=3; left=1; frontM=0x04; rightM=0x08; leftM=0x02;break;
            case 3: front=3; right=0; left=2; frontM=0x08; rightM=0x01; leftM=0x04;break;
            default: front=0; right=1; left=3; frontM=0x01; rightM=0x02; leftM=0x08;
        }

        if( (mazeVal&0xf0)!=0xf0 ){
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
    }

    public int chooseTurnHandler(){
        if(!goal){
            return chooseTurnForGoal();
        }else{
            return chooseTurnForExplore();
        }
    }


    /**
     * Choose next direction to move while searching
     * @return Next direction
     */
    public int chooseTurnForGoal(){
        int [] neighborhood = new int[4];
        int travVal = traversal.getTraversal(curLocX, curLocY);
        int smallest;
        int index = 0;

        for(int i=0; i<4; i++){
            neighborhood[i] = (MicromouseRun.BOARD_MAX*MicromouseRun.BOARD_MAX);
        }

        if( (travVal&0x01) != 0x01 && curLocY > 0 ){
            neighborhood[0] = potential.getPotential(curLocX, curLocY-1);
        }
        if( (travVal&0x02) != 0x02 && curLocX < MicromouseRun.BOARD_MAX-1 ){
            neighborhood[1] = potential.getPotential(curLocX+1, curLocY);
        }
        if( (travVal&0x04) != 0x04 && curLocY < MicromouseRun.BOARD_MAX-1 ){
                neighborhood[2] = potential.getPotential(curLocX, curLocY+1);
        }
        if( (travVal&0x08) != 0x08 && curLocX > 0 ){
            neighborhood[3] = potential.getPotential(curLocX-1, curLocY);
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
     * Choose next direction to move after center
     * @return Next direction
     */
    public int chooseTurnForExplore(){
        int [] neighborhood = new int[4];
        int travVal = traversal.getTraversal(curLocX, curLocY);
        int largest;
        int index = 0;

        for(int i=0; i<4; i++){
            neighborhood[i] = 0;
        }

        if( (travVal&0x01) != 0x01 && curLocY > 0 ){
            System.out.format("N: %x\n", (traversal.getTraversal(curLocX, curLocY-1)&0xf0));
            if( (traversal.getTraversal(curLocX, curLocY-1)&0xf0) == 0xf0 ){
                neighborhood[0] = potential.getPotential(curLocX, curLocY-1);
            }else{
                neighborhood[0] = MicromouseRun.BOARD_MAX*MicromouseRun.BOARD_MAX;
            }
        }
        if( (travVal&0x02) != 0x02 && curLocX < MicromouseRun.BOARD_MAX-1 ){
            System.out.format("E: %x\n", (traversal.getTraversal(curLocX+1, curLocY)&0xf0));
            if( (traversal.getTraversal(curLocX+1, curLocY)&0xf0) == 0xf0 ){
                neighborhood[1] = potential.getPotential(curLocX+1, curLocY);
            }else{
                neighborhood[1] = MicromouseRun.BOARD_MAX*MicromouseRun.BOARD_MAX;
            }
        }
        if( (travVal&0x04) != 0x04 && curLocY < MicromouseRun.BOARD_MAX-1 ){
            System.out.format("S: %x\n", (traversal.getTraversal(curLocX, curLocY+1)&0xf0));
            if( (traversal.getTraversal(curLocX, curLocY+1)&0xf0) == 0xf0 ){
                neighborhood[2] = potential.getPotential(curLocX, curLocY+1);
            }else{
                neighborhood[2] = MicromouseRun.BOARD_MAX*MicromouseRun.BOARD_MAX;
            }
        }
        if( (travVal&0x08) != 0x08 && curLocX > 0 ){
            if( (traversal.getTraversal(curLocX-1, curLocY)&0xf0) == 0xf0 ){
                System.out.format("W: %x\n", (traversal.getTraversal(curLocX-1, curLocY)&0xf0));
                neighborhood[3] = potential.getPotential(curLocX-1, curLocY);
            }else{
                neighborhood[3] = MicromouseRun.BOARD_MAX*MicromouseRun.BOARD_MAX;
            }
        }

        largest = neighborhood[0];
        System.out.format("(%d", neighborhood[0]);
        for(int i=1; i<4; i++){
            System.out.format(",%d", neighborhood[i]);
            if( largest < neighborhood[i] ){
                largest = neighborhood[i];
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
        if( curDirec != nxtDirec ){
            turn = true;
        }
        if(!turn){
            handleCoordinates();
        }else{
            turn = false;
        }
        curDirec = nxtDirec;
    }

    /**
     * Droid make a move
     */
    public void makeNextMove(MazeMap mazeMap){
        //double start;
        //double stop;

        // Scan area and update Traversal Map
        //start = System.nanoTime();
        setTraversalMap(curLocX, curLocY, mazeMap);
        //stop = System.nanoTime();
        //System.out.format("Scan: \t\t%f ns\n", stop-start);

        // Update Potential Map
        //start = System.nanoTime();
        potential.updatePotential(curLocX, curLocY, traversal);
        //stop = System.nanoTime();
        //System.out.format("Update Potential: \t%f ns\n", stop-start);

        // Decide where to go/make a turn
        //start = System.nanoTime();
        nxtDirec = chooseTurnHandler();
        //stop = System.nanoTime();
        //System.out.format("Choose direction: \t%f ns\n", stop-start);

        // If current was dead end, plug it
        //start = System.nanoTime();
        traversal.plugDeadEnd(curLocX, curLocY, curDirec);
        //stop = System.nanoTime();
        //System.out.format("Plug Dead End: \t%f ns\n", stop-start);

        // Update coordinates
        //start = System.nanoTime();
        updateCoordinates();
        //stop = System.nanoTime();
        //System.out.format("Update coordinates: \t%f ns\n", stop-start);
        if( curLocX == MicromouseRun.GOAL && curLocY == MicromouseRun.GOAL ){
            goal = true;
        }
        System.out.println();
    }

    /**
     * Resets all the variables
     */
    public void resetAll(){
        curDirec = 0;
        curLocX = 0;
        curLocY = MicromouseRun.BOARD_MAX-1;
        goal = false;
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
