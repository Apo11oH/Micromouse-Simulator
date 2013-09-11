/**
 * Name of programmer: ApolloH
 * Description: Micromouse Framework GUI driver
 * Variable: newGame -- KenoGame class reference 
 * 			jcb -- GUI combo box for the number of numbers chosen
 * 			jtf -- GUI text field for input of bet
 * 			bStart -- GUI button to start game
 * 			bClear -- GUI button to clear board
 * 			board -- array of GUI buttons for each number on the board
 * 			dialogue -- text field showing user some status update
 * 			numberChosen -- the number of numbers chosen
 * 			chosenCounter -- counter for the numbers of numbers chosen
 * 			tempIntBuf -- buffer for the numbers chosen
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MicromouseFrameworkGUI extends JFrame implements ActionListener{
	MicromouseRun newRun = new MicromouseRun();
	private JTextField fileName, curDirecVal, curLocVal;
	private JButton bStart, bClear, bFile, bNext, bReset;
	private JButton [][] board;
	private JButton [][] traversal;
	private JTextField dialogue;

    /**
     * Constructs the GUI for the program
     */
	public MicromouseFrameworkGUI(){
		super("Micromouse Framework GUI");

		JPanel panel = new JPanel(true);
		panel.setLayout(new BorderLayout());
	
		/*
		 * GRID: NORTH
		 */
		JPanel p1 = new JPanel(new GridLayout(1, 6, 2, 2));
		// make combo box
		// - label
		JTextField titleText = new JTextField("Micromouse Framework GUI");
		titleText.setFont(new Font("SansSerif", Font.BOLD, 15));
		titleText.setEditable(false);
		p1.add(titleText);
		fileName = new JTextField("Please load file");
		fileName.setEditable(false);
		p1.add(fileName);
		bFile = new JButton("Browse");
		bFile.addActionListener(this);
		p1.add(bFile);
		
		/*
		 * GRID: WEST
		 */
		JPanel p2 = new JPanel(new GridLayout(3, 2, 2, 2));
		// make bot info screen
		// Print current direction of the bot
		JTextField curDirecLable = new JTextField("Current Direction: ");
		curDirecLable.setEditable(false);
		p2.add(curDirecLable);
		curDirecVal = new JTextField(newRun.getCurrentDirec());
		curDirecVal.setEditable(false);
		p2.add(curDirecVal);
		// Print current location
		JTextField curLocLable = new JTextField("Current Location: ");
		curLocLable.setEditable(false);
		p2.add(curLocLable);
		curLocVal = new JTextField("( " + newRun.getCurLocX() + ", " + newRun.getCurLocY() + " )");
		curLocVal.setEditable(false);
		p2.add(curLocVal);
        bStart = new JButton("Start");
        bStart.addActionListener(this);
        bStart.setEnabled(false);
        p2.add(bStart);
        // add next button
        bNext = new JButton("Next");
        bNext.addActionListener(this);
        bNext.setEnabled(false);
        p2.add(bNext);
		
		/*
		 * GRID: CENTER
		 */
		JPanel p3 = new JPanel(new GridLayout(16, 16), true);
		// make board GUI
		board = new JButton[newRun.BOARD_MAX][newRun.BOARD_MAX];
		for(int i=0; i<newRun.BOARD_MAX; i++){
			for(int j=0; j<newRun.BOARD_MAX; j++){
				board[i][j] = new JButton("" + newRun.getPotential(j, i));
                board[i][j].setBackground(Color.green);
                board[i][j].setOpaque(true);
				p3.add(board[i][j]);
			}
		}
		
		/*
		 * GRID: SOUTH
		 */
		// add dialogue
		JPanel p5 = new JPanel(new GridLayout(1, 3, 5, 5));
		dialogue = new JTextField();
		dialogue.setEditable(false);
		p5.add(dialogue);
        // add reset droid button
        bReset = new JButton("Reset Droid");
        bReset.addActionListener(this);
        p5.add(bReset);
		// add clear all button
		bClear = new JButton("Clear All");
		bClear.addActionListener(this);
		p5.add(bClear);
		
		panel.add(p1, BorderLayout.NORTH);
		panel.add(p2, BorderLayout.WEST);
		panel.add(p3, BorderLayout.CENTER);
		panel.add(p5, BorderLayout.SOUTH);
		getContentPane().add(panel);
	}

    /**
     * Catches the ActionListener
     * @param e : ActionEvent
     */
	public void actionPerformed(ActionEvent e) {
		Object obj;

		if( (obj = e.getSource()) == bFile){
			JFileChooser filechooser = new JFileChooser(new File("./"));
			
			int selected = filechooser.showOpenDialog(this);
			if( selected == JFileChooser.APPROVE_OPTION  ){
				File file = filechooser.getSelectedFile();
				newRun.createMaze(file.getPath());
				initMaze();
				fileName.setText("Opened file: " + file.getName());
                bStart.setEnabled(true);
			}else if( selected == JFileChooser.CANCEL_OPTION ){
				fileName.setText("Operation canceled");
			}else if( selected == JFileChooser.ERROR_OPTION ){
				fileName.setText("Error opening file");
			}
		}else if( obj == bClear ){
            bStart.setEnabled(false);
            bNext.setEnabled(false);
            unsetColor();
            newRun.resetAll();
            initMaze();
		}else if( obj == bReset ){
            bStart.setEnabled(true);
            bNext.setEnabled(false);
            unsetColor();
            newRun.resetDroid();
            initMaze();
        }else if( obj == bNext ){
            unsetColor();
            newRun.makeNextMove();
            setColors();
            updateMaze();
        }else if(obj == bStart){
            bStart.setEnabled(false);
            bNext.setEnabled(true);
            startMaze();
            setColors();
        }else{
            JOptionPane.showMessageDialog(this, "Something went wrong...");
        }
	} // end method

    /**
     * Sets the color of the button to red
     */
	// method: change color of the button
	public void setColors(){
		board[newRun.getCurLocY()][newRun.getCurLocX()].setBackground(Color.red);
	}

    /**
     * Unsets the color of the button green
     */
    public void unsetColor(){
        board[newRun.getCurLocY()][newRun.getCurLocX()].setBackground(Color.green);
    }

    /**
     * Checks the specified cell for borders and highlights them
     * @param locx: Specified x-coordinate
     * @param locy: Specified y-coordinate
     * @param opt: If true, initialize. Else, update.
     * @return Returns new Border pattern for the specified cell
     */
	public Border findBorder(int locx, int locy, boolean opt){
		int top = 0, left = 0, bottom = 0, right = 0;
		int val;

        if(opt){
            val = newRun.getMazeVal(locx, locy);
        }else{
            val = newRun.getTravVal(locx, locy);
        }
		
		if( (val&0x01) == 0x01 ) top = 5;
		if( (val&0x02) == 0x02 ) right = 5;
		if( (val&0x04) == 0x04 ) bottom = 5;
		if( (val&0x08) == 0x08 ) left = 5;
		
		return BorderFactory.createMatteBorder(top, left, bottom, right, Color.black);
	}

    /**
     * Initializes the GUI with the latest border patterns
     */
    public void initMaze(){
        for(int i=0; i<newRun.BOARD_MAX; i++){
            for(int j=0; j<newRun.BOARD_MAX; j++){
                board[i][j].setBorder(findBorder(j, i, true));
            }
        }
    }

    /**
     * Prep map for new run
     */
    public void startMaze(){
        for(int i=0; i<newRun.BOARD_MAX; i++){
            for(int j=0; j<newRun.BOARD_MAX; j++){
                board[i][j].setBorder(null);
            }
        }
    }

    /**
     * Updates the GUI with the latest border patterns
     */
	public void updateMaze(){
        // Set the borders and potential value
		for(int i=0; i<newRun.BOARD_MAX; i++){
			for(int j=0; j<newRun.BOARD_MAX; j++){
				board[i][j].setBorder(findBorder(j, i, false));
                board[j][i].setText(String.format("%d", newRun.getPotential(i, j)));
			}
		}
        // Set dialogue
        //dialogue.setText(newRun.getDialogue());
        // Update heading
        curDirecVal.setText(newRun.getCurrentDirec());
        // Update location
        curLocVal.setText("( " + newRun.getCurLocX() + ", " + newRun.getCurLocY() + " )");
	}

	public static void main(String[] args) {
		MicromouseFrameworkGUI newGUI = new MicromouseFrameworkGUI();
		newGUI.setSize(1100, 700); // width, height
		newGUI.setVisible(true);
		newGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
