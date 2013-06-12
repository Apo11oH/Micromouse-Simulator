/*
 * Name of programmer: Ken Yokoyama
 * Current Date: 12/11/2011
 * Computer/Compiler: Windows 7/Eclipse (JavaSE-1.6)
 * Description: Keno Game GUI driver
 */

import java.io.*;

public class MazeMap{
	private int mazeMap [][] = new int [MicromouseRun.BOARD_MAX][MicromouseRun.BOARD_MAX];
	private BufferedReader reader;
	private String line = null;
	private String parts [] = null;
	
	public MazeMap(){
		for(int i=0; i<MicromouseRun.BOARD_MAX; i++){
			for(int j=0; j<MicromouseRun.BOARD_MAX; j++){
				mazeMap[i][j] = 0;
			}
		}
	}
	
	public int getMazeVal(int locx, int locy){
		return mazeMap[locy][locx];
	}
	
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
