/**
 * Created with IntelliJ IDEA.
 * User: Kenny
 * Date: 6/19/13
 * Time: 7:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class TraversalMap {
    private int [][] traversal = new int[MicromouseRun.BOARD_MAX][MicromouseRun.BOARD_MAX];

    public TraversalMap(){
        for(int i=0; i<MicromouseRun.BOARD_MAX; i++){
            for(int j=0; j<MicromouseRun.BOARD_MAX; j++){
                traversal[i][j] = 0;
            }
        }
    }

    public int getTraversal(int locx, int locy){
        return traversal[locy][locx];
    }

    public void setWall(int locx, int locy, int setDirec){
        switch(setDirec){
            case 0: traversal[locy][locx] |= 0x11;
                if( locy>0 ) traversal[locy-1][locx] |= 0x44;
                break;
            case 1: traversal[locy][locx] |= 0x22;
                if( locy < MicromouseRun.BOARD_MAX-1 ) traversal[locy][locx+1] |= 0x88;
                break;
            case 2: traversal[locy][locx] |= 0x44;
                if( locx < MicromouseRun.BOARD_MAX-1 ) traversal[locy+1][locx] |= 0x11;
                break;
            case 3: traversal[locy][locx] |= 0x88;
                if( locx > 0 ) traversal[locx-1][locy] |= 0x22;
                break;
        }
    }

    public void setTraversed(int locx, int locy, int setDirec) {
        switch (setDirec) {
            case 0: traversal[locy][locx] |= 0x10;
                if( locy>0 ) traversal[locy - 1][locx] |= 0x40;
                break;
            case 1: traversal[locy][locx] |= 0x20;
                if( locy < MicromouseRun.BOARD_MAX-1 ) traversal[locy][locx+1] |= 0x80;
                break;
            case 2: traversal[locy][locx] |= 0x40;
                if( locx < MicromouseRun.BOARD_MAX-1 ) traversal[locy+1][locx] |= 0x10;
                break;
            case 3: traversal[locy][locx] |= 0x80;
                if( locx > 0 ) traversal[locx-1][locy] |= 0x20;
                break;
        }
    }


}
