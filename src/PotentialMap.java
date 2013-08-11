/**
 * Name of programmer: ApolloH
 * Description: Potential Board module
 * Variable: potential - 2D array holding the potential values for the map
 */

public class PotentialMap {
	private int [][] potential = new int[MicromouseRun.BOARD_MAX][MicromouseRun.BOARD_MAX];

    /**
     * Default constructor
     */
	public PotentialMap(){
		initPotential();
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
	public int updatePotential(int locx, int locy, TraversalMap traversal){
        boolean foundFlag;

        initPotential();

        // Set goal cell
        potential[MicromouseRun.GOAL][MicromouseRun.GOAL] = 0;

        // Make the potential map
        for(int i=0; i<MicromouseRun.GOAL*MicromouseRun.GOAL; i++){
            foundFlag = true;
            // look for where the potential is i
            for(int j=0; j<MicromouseRun.GOAL; j++){
                for(int k=0; k<MicromouseRun.GOAL; k++){
                    if( potential[j][k] == i ){
                        foundFlag = false;
                        // look at north
                        // cond. true if there is no wall or we haven't travelled there yet
                        if((traversal.getTraversal(j, k)&0x01)==0 || (traversal.getTraversal(j, k)&0x10)==0)
                            // if ( potential of current cell + 1 is smaller than potential of cell in front
                            if(potential[j][k]+1 < potential[j-1][k])
                                // set potential of cell in front to potential of current cell + 1
                                potential[j-1][k] = potential[j][k] + 1;
                        // look at east
                        if((traversal.getTraversal(j, k)&0x02)==0 || (traversal.getTraversal(j, k)&0x20)==0)
                            if(potential[j][k]+1 < potential[j][k+1])
                                potential[j][k+1] = potential[j][k] + 1;
                        // look at south
                        if((traversal.getTraversal(j, k)&0x04)==0 || (traversal.getTraversal(j, k)&0x40)==0)
                            if(potential[j][k]+1 < potential[j+1][k])
                                potential[j+1][k] = potential[j][k] + 1;
                        // look at west
                        if((traversal.getTraversal(j, k)&0x08)==0 || (traversal.getTraversal(j, k)&0x80)==0)
                            if(potential[j][k]+1 < potential[j][k-1])
                                potential[j][k-1] = potential[j][k] + 1;
                        // look for start position
                        if(j == locx && k == locy)
                            return 1;
                    }
                }
            }
            // Could not find start
            if(foundFlag) break;
        }
        return 0;
	}

    /**
     * Resets the potential map to it's initial state
     */
	public void resetPotential(){
		initPotential();
		potential[MicromouseRun.GOAL][MicromouseRun.GOAL] = 0;
	}

    /**
     * Initializes the potential map
     */
    private void initPotential(){
        for(int i=0; i<MicromouseRun.BOARD_MAX; i++){
            for(int j=0; j<MicromouseRun.BOARD_MAX; j++){
                potential[i][j] = MicromouseRun.BOARD_MAX * MicromouseRun.BOARD_MAX + 1;
            }
        }
    }
}
