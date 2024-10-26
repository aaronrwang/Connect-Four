import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
public class ConnectFour implements ActionListener {
	final int PIECE_WIDTH = 100;
	final int PIECE_HEIGHT = PIECE_WIDTH;
	final int HORIZONTAL_MARGIN = 10;
	final int BUTTON_HEIGHT = 25;
	final int VERTICAL_MARGIN = 10;
	final int BOARD_WIDTH = PIECE_WIDTH * 7 + HORIZONTAL_MARGIN * 8;
	final int BOARD_HEIGHT = BUTTON_HEIGHT + PIECE_HEIGHT * 6 + VERTICAL_MARGIN * 8; 
	JButton[] button = new JButton [7];
	Panel panel = new Panel();
	JFrame frame = new JFrame();
	boolean gameover = false;
	public static char[][] board  = new char[6][7];
	public static int turn = 1;
	public static void main(String[] args) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				board [i][j] = ' ';
			}
		}
		new ConnectFour();
		
		
	}
	public ConnectFour() {
		
		
		frame.setSize(BOARD_WIDTH, BOARD_HEIGHT + 50);
		frame.setLocationRelativeTo(null);
		
		for (int i = 0; i < 7; i++) {
			button[i] = new JButton(i + 1 + "");
			button[i].addActionListener(this);
		}
		
		
		panel.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
		panel.setBackground(Color.blue);
		panel.setFocusable(true);
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		panel.setLayout(new GridLayout(0, 1));
		frame.add(panel);
		frame.setVisible(true);
		frame.setResizable(false);
		for (int i = 0; i < 7; i++) {
			button[i].setBounds(10 + (100+10)*i, 0, PIECE_WIDTH, BOARD_HEIGHT-10);
			button[i].setOpaque(false);
			button[i].setContentAreaFilled(false);
			button[i].setBorderPainted(false);

			frame.add(button[i]);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int column = Integer.parseInt(e.getActionCommand());
		int i = 5;
		if (!(column < 1 || column > 7) && !gameover) {
			while ( i >= 0 && board[i][(column-1)] != ' ' ) {
				i--;
			}
			if (i!=-1) {
				if (turn % 2 == 1) {
					board[i][(column-1)] = 'x';
				} else if (turn % 2 == 0) {
					board[i][(column-1)] = 'o';
				}
				turn++;
				panel.repaint();
				
			} else {
				System.out.println("Invalid input");
			}
		} else {
			System.out.println("Invalid input");
		}
		JFrame framedone = new JFrame();
			if (turn == 43 && !checkwin()) {
				framedone = new JFrame("Tie");
				gameover = true;
			} else if (checkwin() && turn % 2 == 0) {
				framedone = new JFrame("Yellow won");
				gameover = true;
			} else if (checkwin() && turn % 2 == 1){
				framedone = new JFrame("Red won");
				gameover = true;
			}
			if (gameover) {
				framedone.setSize(250, 50);
				framedone.setLocationRelativeTo(null);
				framedone.setVisible(true);
				framedone.setResizable(false);
				frame.disable();
			}

	}
	public static boolean checkwin () {
		boolean gameend = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				if (board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j] == board[i+3][j] && board[i][j] != ' ') {
					gameend = true;
					//check vertical wins
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j] == board[i][j+3] && board[i][j] != ' ') {
					gameend = true;
					//check horizontal wins
				}
			}
		}
		for (int i = 5; i > 2; i--) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == board[i-1][j+1] && board[i][j] == board[i-2][j+2] && board[i][j] == board[i-3][j+3] && board[i][j] != ' ') {
					gameend = true;
					//check forward slash wins
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == board[i+1][j+1] && board[i][j] == board[i+2][j+2] && board[i][j] == board[i+3][j+3] && board[i][j] != ' ') {
					gameend = true;
					//check back slash wins
				}
			}
		}
		return gameend;
	}
}
