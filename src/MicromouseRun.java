/**
 * Name of programmer: ApolloH
 * Description: Micromouse simulator text runner
 * Variable: BOARD_MAX - Max dimension of the board
 * GOAL - Goal location
 * potential - potential map module
 * traversal - traversal map module
 * droid - droid module
 * mazeMap - maze module
 */

public class MicromouseRun {
    public static final int BOARD_MAX = 16;
    public static final int GOAL = BOARD_MAX / 2 - 1;
    private Droid droid;
    private MazeMap mazeMap;

    /**
     * Constructor
     */
    public MicromouseRun() {
        droid = new Droid();
        mazeMap = new MazeMap();
    }

    /**
     * Gets the current x-coordinate of the droid
     *
     * @return x-coordinate
     */
    public int getCurLocX() {
        return droid.getCurLocX();
    }

    /**
     * Gets the current y-coordinate of the droid
     *
     * @return y-coordinate
     */
    public int getCurLocY() {
        return droid.getCurLocY();
    }

    /**
     * Gets the corresponding label for the current direction
     *
     * @return Label of direction the droid is facing
     */
    public String getCurrentDirec() {
        int val = droid.getCurDirec();
        String retString = "";
        switch (val) {
            case 0:
                retString = "North";
                break;
            case 1:
                retString = "East";
                break;
            case 2:
                retString = "South";
                break;
            case 3:
                retString = "West";
                break;
        }
        return retString;
    }

    /**
     * Gets the wall value for a specified cell
     *
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     * @return Wall value for the specified location
     */
    public int getMazeVal(int locx, int locy) {
        return mazeMap.getMazeVal(locx, locy);
    }

    /**
     * Gets the potential value for a specific cell
     *
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     * @return Potential value for the specified location
     */
    public int getPotential(int locx, int locy) {
        return droid.getPotential(locx, locy);
    }

    /**
     * Gets the traversal value for a specific cell
     *
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     * @return Traversal value for the specified location
     */
    public int getTravVal(int locx, int locy) {
        return droid.getTraversal(locx, locy);
    }

    /**
     * Instantiates the maze
     *
     * @param filepath Path to the file containing maze data
     */
    public void createMaze(String filepath) {
        mazeMap.createMaze(filepath);
    }

    /**
     * Get dialogue for GUI
     *
     * @return Message for user
     */
    public String getDialogue() {
        return droid.getDialogue();
    }

    /**
     * Droid make a move
     */
    public void makeNextMove() {
        droid.makeNextMove(this.mazeMap);
    }

    public void resetDroid() {
        droid.resetAll();
    }

    /**
     * Resets all the variables
     */
    public void resetAll() {
        droid.resetAll();
        mazeMap.resetMaze();
    }
}
