import javax.swing.JPanel;

import java.awt.*;

public class Panel extends JPanel {
	final int PIECE_WIDTH = 100;
	final int PIECE_HEIGHT = PIECE_WIDTH;
	final int HORIZONTAL_MARGIN = 10;
	final int BUTTON_HEIGHT = 25;
	final int VERTICAL_MARGIN = 10;
	final int BOARD_WIDTH = PIECE_WIDTH * 7 + HORIZONTAL_MARGIN * 8;
	final int BOARD_HEIGHT = BUTTON_HEIGHT + PIECE_HEIGHT * 6 + VERTICAL_MARGIN * 10; 
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				g2.setColor(Color.BLACK);
				if (ConnectFour.board[j][i] == 'x') {
					g2.setColor(Color.YELLOW);
				} else if (ConnectFour.board[j][i] == 'o') {
					g2.setColor(Color.RED);
				}
				g2.fillOval(i * (PIECE_WIDTH + 10) + HORIZONTAL_MARGIN, j * (PIECE_HEIGHT + 10) + VERTICAL_MARGIN + BUTTON_HEIGHT, PIECE_WIDTH, PIECE_HEIGHT);
			}
		}
			
		
	}

}
