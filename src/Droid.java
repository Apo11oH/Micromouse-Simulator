/**
 * Filename: Droid.java
 * Programmer: Apo11oH
 * Description:
 */

public class Droid {
	private int curDirec;
    private int nxtDirec;
	private int curLocX;
	private int curLocY;
    private PotentialMap potential;
    private TraversalMap traversal;
	
	// constructor (default)
	public Droid(){
		curDirec = 0;
		curLocX = 0;
		curLocY = MicromouseRun.BOARD_MAX-1;
        potential = new PotentialMap();
        traversal = new TraversalMap();
	}

    /*
     *  Droid specific getters and setters
     */
	public int getCurDirec(){ return curDirec; }
	
	public int getCurLocX(){ return curLocX; }
	
	public int getCurLocY(){ return curLocY; }

    public void setCurDirec(int newDirec){ curDirec = newDirec; }

    public void setCurLocX(int newLocX){ curLocX = newLocX; }

    public void setCurLocY(int newLocY){ curLocY = newLocY; }


    /*
     *  Sensor functions
     */



    /*
     *  Traversal Map specific getters and setters
     */


    /*
     *  Potential Map specific getters and setters
     */
    public int getPotential(int locx, int locy){
        return potential.getPotential(locx, locy);
    }


    /*
     *  Navigation
     */
    public int findNextDirection(){
        int [] neighbourhood = new int[4];
        int lowestPotential;
        int lowIndex = 0;
        int curTraversalVal = traversal.getTraversal(curLocX, curLocY);

        for(int i=0; i<neighbourhood.length; i++){
            neighbourhood[i] = MicromouseRun.BOARD_MAX * MicromouseRun.BOARD_MAX + 1;
        }

        // check front
        if(curLocY > 0){
            if((curTraversalVal&0x01) != 0x01 && (curTraversalVal&0x10) != 0x10 ){
                neighbourhood[0] = potential.getPotential(curLocX, curLocY-1);
            }
        }
        // check right
        if(curLocX < MicromouseRun.BOARD_MAX - 1){
            if((curTraversalVal&0x02) != 0x02 && (curTraversalVal&0x20) != 0x20 ){
                neighbourhood[1] = potential.getPotential(curLocX+1, curLocY);
            }
        }
        // check back
        if(curLocY < MicromouseRun.BOARD_MAX - 1){
            if((curTraversalVal&0x01) != 0x01 && (curTraversalVal&0x10) != 0x10 ){
                neighbourhood[2] = potential.getPotential(curLocX, curLocY+1);
            }
        }
        // check left
        if(curLocY > 0){
            if((curTraversalVal&0x01) != 0x01 && (curTraversalVal&0x10) != 0x10 ){
                neighbourhood[3] = potential.getPotential(curLocX-1, curLocY);
            }
        }

        lowestPotential = neighbourhood[0];
        for(int i=1; i<neighbourhood.length; i++){
            if(lowestPotential > neighbourhood[i]){
                lowestPotential = neighbourhood[i];
                lowIndex = i;
            }
        }

        return lowIndex;
    }

    public int chooseTurn(){
        if(curDirec == nxtDirec){
            traversal.setTraversed( curLocX, curLocY, curDirec );
            switch(curDirec){
                case 0: curLocY--; break;
                case 1: curLocX++; break;
                case 2: curLocY++; break;
                case 3: curLocX--; break;
            }
            // Keep current direction
            return 0;
        }else if((curDirec+1)%4 == nxtDirec){
            // Turn 90 right
            return 1;
        }else if((curDirec+2)%4 == nxtDirec){
            // Flip 180
            return 2;
        }else{
            // Turn 90 left
            return 3;
        }
    }
}
