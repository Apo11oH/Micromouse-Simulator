/*
 * Name of programmer: Ken Yokoyama
 * Description: class for the keno board
 * Variable: kenoSpots - array of spots for the keno board
 * 			drawnSpots - array of spots selected from the keno board
 */

public class PotentialBoard {
	private int [][] potential = new int[MicromouseRun.BOARD_MAX][MicromouseRun.BOARD_MAX];
	
	// constructor
	public PotentialBoard(){
		for(int i=0; i<MicromouseRun.BOARD_MAX; i++){
			for(int j=0; j<MicromouseRun.BOARD_MAX; j++){
				potential[i][j] = MicromouseRun.BOARD_MAX * MicromouseRun.BOARD_MAX + 1;
			}
		}
		potential[MicromouseRun.GOAL][MicromouseRun.GOAL] = 0;
	}
	
	public int getPotential(int locx, int locy){
		return potential[locy][locx];
	}
	
	public void setPotential(int locx, int locy, int newVal){
		potential[locy][locx] = newVal;
	}
	
	public void updatePotential(){
		
	}
	
	public void resetAll(){
		for(int i=0; i<MicromouseRun.BOARD_MAX; i++){
			for(int j=0; j<MicromouseRun.BOARD_MAX; j++){
				potential[i][j] = MicromouseRun.BOARD_MAX * MicromouseRun.BOARD_MAX + 1;
			}
		}
		potential[MicromouseRun.GOAL][MicromouseRun.GOAL] = 0;
	}
}
