/**
 * Name of programmer: ApolloH
 * Description: Potential Board module
 * Variable: potential - 2D array holding the potential values for the map
 */

public class PotentialBoard {
	private int [][] potential = new int[MicromouseRun.BOARD_MAX][MicromouseRun.BOARD_MAX];

    /**
     * Default constructor
     */
	public PotentialBoard(){
		for(int i=0; i<MicromouseRun.BOARD_MAX; i++){
			for(int j=0; j<MicromouseRun.BOARD_MAX; j++){
				potential[i][j] = MicromouseRun.BOARD_MAX * MicromouseRun.BOARD_MAX + 1;
			}
		}
		potential[MicromouseRun.GOAL][MicromouseRun.GOAL] = 0;
	}

    /**
     * Get the potential value for a specific cell
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     * @return Value of a specified cell in the potential map
     */
	public int getPotential(int locx, int locy){
		return potential[locy][locx];
	}

    /**
     * Sets the potential value for a specific cell
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     * @param newVal The new potential value
     */
	public void setPotential(int locx, int locy, int newVal){
		potential[locy][locx] = newVal;
	}

    /**
     * Updates the potential map based on the traversal map
     */
	public void updatePotential(){
		
	}

    /**
     * Resets the potential map to it's initial state
     */
	public void resetAll(){
		for(int i=0; i<MicromouseRun.BOARD_MAX; i++){
			for(int j=0; j<MicromouseRun.BOARD_MAX; j++){
				potential[i][j] = MicromouseRun.BOARD_MAX * MicromouseRun.BOARD_MAX + 1;
			}
		}
		potential[MicromouseRun.GOAL][MicromouseRun.GOAL] = 0;
	}
}
