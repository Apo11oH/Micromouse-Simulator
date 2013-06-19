/*
 * Name of programmer: Ken Yokoyama
 * Variable: value - the spots value
 * 			chosen - boolean whether the spot was selected or not
 */

public class Droid {
	private int curDirec;
	private int curLocX;
	private int curLocY;
	
	// constructor (default)
	public Droid(){
		curDirec = 0;
		curLocX = 0;
		curLocY = MicromouseRun.BOARD_MAX-1;
	}
	
	// accessor
	public int getCurDirec(){ return curDirec; }
	
	public int getCurLocX(){ return curLocX; }
	
	public int getCurLocY(){ return curLocY; }

    public void setCurDirec(int newDirec){ curDirec = newDirec; }

    public void setCurLocX(int newLocX){ curLocX = newLocX; }

    public void setCurLocY(int newLocY){ curLocY = newLocY; }

}
