/**
 * Name of programmer: ApolloH
 * Description: Maze module
 * Variable: mazeMap - 2D array holding the maze data
 *          reader - input stream handler
 *          line - input stream buffer
 *          parts - array holding each individual value on a line
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MazeMap{
	private int mazeMap [][] = new int [MicromouseRun.BOARD_MAX][MicromouseRun.BOARD_MAX];
	private BufferedReader reader;
	private String line = null;
	private String parts [] = null;

    /**
     * Default constructor
     */
	public MazeMap(){
		for(int i=0; i<MicromouseRun.BOARD_MAX; i++){
			for(int j=0; j<MicromouseRun.BOARD_MAX; j++){
				mazeMap[i][j] = 0;
			}
		}
	}

    /**
     * Get the wall value for a specific cell
     * @param locx Specified x-coordinate
     * @param locy Specified y-coordinate
     * @return Wall value for a specified cell
     */
	public int getMazeVal(int locx, int locy){
		return mazeMap[locy][locx];
	}

    /**
     * Reads in the map data from a file and initializes the map used
     * for testing
     * @param filePath Path to the maze data
     */
	public void createMaze(String filePath){
		try{
			reader = new BufferedReader(new FileReader(filePath));
			int i=0;
			while( (line = reader.readLine()) != null ){
				parts = line.split("\\s");
				for( int j=0; j<MicromouseRun.BOARD_MAX; j++ ){
					mazeMap[i][j] = Integer.valueOf(parts[j].trim());
				}
				i++;
			}
		}catch(FileNotFoundException e){
			System.out.println("File not found. Try again.");
		}catch(IOException e){
			System.out.println("Oh No! Read error :(");
		}catch(Exception e){
			System.out.println("Some other error occured...");
		}
	}
}
