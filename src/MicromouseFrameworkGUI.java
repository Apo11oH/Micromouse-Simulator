/**
 * Filename: MicromouseFrameworkGUI.java
 * Programmer: Apo11oH
 * Description:
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.File;

public class MicromouseFrameworkGUI extends JFrame implements ActionListener{
	MicromouseRun newRun = new MicromouseRun();
	private JTextField fileName, curDirecVal, curLocVal;
	private JButton bStart, bClear, bFile, bNext;
	private JButton [][] board;
	private JButton [][] traversal;
	private JTextField dialogue;
	
	public MicromouseFrameworkGUI(){
		super("Micromouse Framework GUI");

		JPanel panel = new JPanel();
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
		curDirecVal = new JTextField(newRun.getCurrentDirLable());
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
        p2.add(bStart);
        // add next button
        bNext = new JButton("Next");
        bNext.addActionListener(this);
        p2.add(bNext);
		
		/*
		 * GRID: CENTER
		 */
		JPanel p3 = new JPanel(new GridLayout(16, 16));
		// make board GUI
		board = new JButton[newRun.BOARD_MAX][newRun.BOARD_MAX];
		for(int i=0; i<newRun.BOARD_MAX; i++){
			for(int j=0; j<newRun.BOARD_MAX; j++){
				board[i][j] = new JButton("" + newRun.getPotential(j, i));
				board[i][j].setBorder(findBorder(j, i));
				p3.add(board[i][j]);
			}
		}

        /*
         *  GRID: EAST
         */
        JPanel p4 = new JPanel(new GridLayout(16, 16));
        // make board GUI
        traversal = new JButton[newRun.BOARD_MAX][newRun.BOARD_MAX];
        for(int i=0; i<newRun.BOARD_MAX; i++){
            for(int j=0; j<newRun.BOARD_MAX; j++){
                traversal[i][j] = new JButton("");
                p3.add(board[i][j]);
            }
        }
		
		/*
		 * GRID: SOUTH
		 */
		// add dialogue
		JPanel p5 = new JPanel(new GridLayout(1, 2, 5, 5));
		dialogue = new JTextField();
		dialogue.setEditable(false);
		p5.add(dialogue);
		// add clear button
		bClear = new JButton("Reset Run");
		bClear.addActionListener(this);
		p5.add(bClear);
		
		panel.add(p1, BorderLayout.NORTH);
		panel.add(p2, BorderLayout.WEST);
		panel.add(p3, BorderLayout.CENTER);
        panel.add(p4, BorderLayout.EAST);
		panel.add(p5, BorderLayout.SOUTH);
		getContentPane().add(panel);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj;

		if( (obj = e.getSource()) == bFile){
			JFileChooser filechooser = new JFileChooser(new File("./"));
			
			int selected = filechooser.showOpenDialog(this);
			if( selected == JFileChooser.APPROVE_OPTION  ){
				File file = filechooser.getSelectedFile();
				newRun.createMaze(file.getPath());
				updateMaze();
				fileName.setText("Opened file: " + file.getName());
			}else if( selected == JFileChooser.CANCEL_OPTION ){
				fileName.setText("Operation canceled");
			}else if( selected == JFileChooser.ERROR_OPTION ){
				fileName.setText("Error opening file");
			}
		}else if( obj == bClear ){
		}else if( obj == bNext ){

        }else if(obj == bStart){
            changeColors();
        }
	} // end method
	
	// method: change color of the button
	public void changeColors(){
		if( board[newRun.getCurLocY()][newRun.getCurLocX()].getBackground() != Color.red ){
			 board[newRun.getCurLocY()][newRun.getCurLocX()].setBackground(Color.red);
		}else{
			 board[newRun.getCurLocY()][newRun.getCurLocX()].setBackground(null);
		}
	}
	
	public Border findBorder(int locx, int locy){
		int top = 0, left = 0, bottom = 0, right = 0;
		int val = newRun.getMazeVal(locx, locy);
		
		if( (val&0x01) == 0x01 ) top = 5;
		if( (val&0x02) == 0x02 ) right = 5;
		if( (val&0x04) == 0x04 ) bottom = 5;
		if( (val&0x08) == 0x08 ) left = 5;
		
		return BorderFactory.createMatteBorder(top, left, bottom, right, Color.black);
	}
	
	public void updateMaze(){
		for(int i=0; i<newRun.BOARD_MAX; i++){
			for(int j=0; j<newRun.BOARD_MAX; j++){
				board[i][j].setBorder(findBorder(j, i));
			}
		}
	}
	
	public static void main(String[] args) {
		MicromouseFrameworkGUI newGUI = new MicromouseFrameworkGUI();
		newGUI.setSize(1200, 900); // width, height
		newGUI.setVisible(true);
		newGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}