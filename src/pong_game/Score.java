package pong_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Score extends Rectangle{

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1;
	int player2;
	
	
	public Score(int GAME_WIDTH, int GAME_HEIGHT) {
		
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}

	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calibri",Font.PLAIN,60));
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10),(GAME_WIDTH/2)-75, 50);
		g.drawString(String.valueOf(player2/10) + String.valueOf(player2%10),(GAME_WIDTH/2)+10, 50);
	}
}
