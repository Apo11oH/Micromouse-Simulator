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

    /**
     * Default constructor
     */
	public Droid(){
		curDirec = 0;
		curLocX = 0;
		curLocY = MicromouseRun.BOARD_MAX-1;
	}

    /**
     * Getter for the current droid direction
     * @return current direction
     */
	public int getCurDirec(){ return curDirec; }
	
	public int getCurLocX(){ return curLocX; }
	
	public int getCurLocY(){ return curLocY; }

    public void setCurDirec(int newDirec){ curDirec = newDirec; }

    public void setCurLocX(int newLocX){ curLocX = newLocX; }

    public void setCurLocY(int newLocY){ curLocY = newLocY; }

}
