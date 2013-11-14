/**
 * Name of programmer: ApolloH
 * Description: Traversal Map module
 * Variable: traversal - current direction of the droid
 */

public class TraversalMap {
    private int[][] traversal = new int[MicromouseRun.BOARD_MAX][MicromouseRun.BOARD_MAX];

    /**
     * Default constructor
     */
    public TraversalMap() {
        initTraversal();
    }

    /**
     * Get value of a cell in the traversal map
     *
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     * @return Value of a specified cell in the traversal map
     */
    public int getTraversal(int locx, int locy) {
        return traversal[locy][locx];
    }

    /**
     * Sets the wall for a specific direction in the traveresal map for a single cell
     * 2 cells if it meets the condition. Also sets the checked bit for the given direction
     *
     * @param locx     Specified x-coordinate
     * @param locy     Specified y-coordinate
     * @param setDirec Direction of the wall (up is north(:=0))
     */
    public void setWall(int locx, int locy, int setDirec) {
        //System.out.println("Set wall called. Direction: " + setDirec);
        switch (setDirec) {
            case 0:
                traversal[locy][locx] |= 0x11;
                if (locy > 0) traversal[locy - 1][locx] |= 0x44;
                break;
            case 1:
                traversal[locy][locx] |= 0x22;
                if (locx < MicromouseRun.BOARD_MAX - 1) traversal[locy][locx + 1] |= 0x88;
                break;
            case 2:
                traversal[locy][locx] |= 0x44;
                if (locy < MicromouseRun.BOARD_MAX - 1) traversal[locy + 1][locx] |= 0x11;
                break;
            case 3:
                traversal[locy][locx] |= 0x88;
                if (locx > 0) traversal[locy][locx - 1] |= 0x22;
                break;
        }
    }

    /**
     * Set the direction checked bit for the specific direction while leaving the
     * wall bit alone.
     *
     * @param locx     Specified x-coordinate
     * @param locy     Specified y-coordinate
     * @param setDirec Direction of the wall (up is north(:=0))
     */
    public void setChecked(int locx, int locy, int setDirec) {
        //System.out.println("Set checked called");
        switch (setDirec) {
            case 0:
                traversal[locy][locx] |= 0x10;
                if (locy > 0) traversal[locy - 1][locx] |= 0x40;
                break;
            case 1:
                traversal[locy][locx] |= 0x20;
                if (locx < MicromouseRun.BOARD_MAX - 1) traversal[locy][locx + 1] |= 0x80;
                break;
            case 2:
                traversal[locy][locx] |= 0x40;
                if (locy < MicromouseRun.BOARD_MAX - 1) traversal[locy + 1][locx] |= 0x10;
                break;
            case 3:
                traversal[locy][locx] |= 0x80;
                if (locx > 0) traversal[locy][locx - 1] |= 0x20;
                break;
        }
    }

    /**
     * Plugs the dead ends by making them unaccessible
     *
     * @param locx     Specified x-coordinate
     * @param locy     Specified y-coordinate
     * @param setDirec Direction of the wall (up is north(:=0))
     */
    public void plugDeadEnd(int locx, int locy, int setDirec) {
        switch (setDirec) {
            case 0:
                if ((traversal[locy][locx] & 0x0E) == 0x0E) {
                    traversal[locy][locx] |= 0x11;
                    if (locy > 0) traversal[locy - 1][locx] |= 0x44;
                }
                break;
            case 1:
                if ((traversal[locy][locx] & 0x0D) == 0x0D) {
                    traversal[locy][locx] |= 0x22;
                    if (locx < MicromouseRun.BOARD_MAX - 1) traversal[locy][locx + 1] |= 0x88;
                }
                break;
            case 2:
                if ((traversal[locy][locx] & 0x0B) == 0x0B) {
                    traversal[locy][locx] |= 0x44;
                    if (locy < MicromouseRun.BOARD_MAX - 1) traversal[locy + 1][locx] |= 0x11;
                }
                break;
            case 3:
                if ((traversal[locy][locx] & 0x07) == 0x07) {
                    traversal[locy][locx] |= 0x88;
                    if (locx > 0) traversal[locy][locx - 1] |= 0x22;
                }
                break;
        }
    }

    /**
     * Reset the traversal board
     */
    public void resetTraversal() {
        initTraversal();
    }

    /**
     * Initialize the traversal map
     */
    private void initTraversal() {
        for (int i = 0; i < MicromouseRun.BOARD_MAX; i++) {
            for (int j = 0; j < MicromouseRun.BOARD_MAX; j++) {
                traversal[i][j] = 0;
            }
        }
        traversal[MicromouseRun.BOARD_MAX - 1][0] = 0x40;
    }

}
