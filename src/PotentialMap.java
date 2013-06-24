/**
 * Filename: PotentialMap.java
 * Programmer: Apo11oH
 * Description:
 */

public class PotentialMap {
	private int [][] potential = new int[MicromouseRun.BOARD_MAX][MicromouseRun.BOARD_MAX];
	
	// constructor
	public PotentialMap(){
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
