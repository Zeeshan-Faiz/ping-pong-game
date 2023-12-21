package pong_game;

import java.awt.Color;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{

	
	public GameFrame() {
		
		GamePanel panel = new GamePanel();
		this.add(panel);
		this.setTitle("Ping Pong Game");
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	
	}
}
