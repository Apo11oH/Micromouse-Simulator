/*
 * Name of program: Homework #7
 * Name of programmer: Ken Yokoyama
 * Current Date: 12/11/2011
 * Computer/Compiler: Windows 7/Eclipse (JavaSE-1.6)
 * Description: class for the keno board
 * Variable: kenoSpots - array of spots for the keno board
 * 			drawnSpots - array of spots selected from the keno board
 */

public class KenoBoard {
	private Spot [] kenoSpots;
	private Spot [] drawnSpots;
	
	// constructor
	public KenoBoard(){
		kenoSpots = new Spot[KenoGame.MAX_KENO_NUMBER + 1];
		drawnSpots = new Spot[KenoGame.DRAWN_NUMBER];
		for(int i=1; i<KenoGame.MAX_KENO_NUMBER+1; i++){
			kenoSpots[i] = new Spot(i);
		}
		for(int i=0; i<KenoGame.DRAWN_NUMBER; i++){
			drawnSpots[i] = new Spot(i);
		}
		choseSpots();
	}
	
	// method: resets boolean value in kenoSpots 
	// 			& null for each elements of drawnSpots
	public void resetAll(){
		for(int i=1; i<KenoGame.MAX_KENO_NUMBER + 1; i++){
			if(kenoSpots[i].getChosen())
				kenoSpots[i].setChosen(false);
		}
		for(int i=0; i<KenoGame.DRAWN_NUMBER; i++){
			drawnSpots[i] = null;
		}
	}
	
	// accessor for keno spot
	public Spot getKenoSpot(int index){
		if(index > 0 && index < KenoGame.MAX_KENO_NUMBER+1)
			return kenoSpots[index];
		else
			return null;
	}
	
	// method: chose spots in kenoSpots
	public void choseSpots(){
		int index;
		int counter = 0;
		
		while(counter < KenoGame.DRAWN_NUMBER){
			index =  (int)(Math.random() * (KenoGame.MAX_KENO_NUMBER-1+1) ) + 1;
			if(!kenoSpots[index].getChosen()){
				drawnSpots[counter] = kenoSpots[index];
				drawnSpots[counter++].setChosen(true);
			}
		}
	}
	
	// method: return the number of drawn spots
	public int [] getDrawnSpots(){
		int num[] = new int[KenoGame.DRAWN_NUMBER];
		
		for(int i=0; i<KenoGame.DRAWN_NUMBER; i++){
			if(drawnSpots[i].getChosen())
				num[i] = drawnSpots[i].getValue();
		}
		
		return num;
	}
	
	// overide: toString
	public String toString(){
		StringBuilder output = new StringBuilder("Keno Board spots: ");
		
		for(int i = 1; i<KenoGame.DRAWN_NUMBER; i++){
			output.append(drawnSpots[i].getValue());
			output.append(' ');
		}
		
		return output.toString();
	}
}
