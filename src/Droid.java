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
     *  Potential Map specific getters and setters
     */


    /*
     *  Traversal Map specific getters and setters
     */


    /*
     *  Navigation
     */

    public void findNextTurn(){

    }

    public void chooseTurn(){

    }
}
