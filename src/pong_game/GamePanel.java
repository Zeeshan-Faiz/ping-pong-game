package pong_game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1250;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	public GamePanel(){
		
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new ActiveLisener());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void newBall() {
		
		//ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);
		
		//to start the ball from anywhere in the middle we can use the below code.
		random = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	
	public void newPaddles() {
		
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	
	public void paint(Graphics g) {
		
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	public void draw(Graphics g) {
		
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		
	}
	
	public void move() {
		
		//to make the paddle movement smooth we'll just call move method of paddle here.
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	public void checkCollision() {
		//this stops paddle from going out of the window screen
		if(paddle1.y <= 0)
			paddle1.y=0;
		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle1.y = (GAME_HEIGHT-PADDLE_HEIGHT);
		
		if(paddle2.y <= 0)
			paddle2.y=0;
		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle2.y = (GAME_HEIGHT-PADDLE_HEIGHT);
		
		
		//this stops the ball from going out of the window screen
		if(ball.y <= 0)
			ball.setYDirection(-ball.yVelocity);
		
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER)
			ball.setYDirection(-ball.yVelocity);
		
		//this helps in bouncing the ball off the paddles(paddle 1- blue, paddle 2 - red)
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			
			//optional to increase the ball velocity after it hits any of the paddle
			ball.xVelocity++;
			if(ball.yVelocity>0)
				ball.yVelocity ++;
			else
				ball.yVelocity --;
			
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			
			//optional to increase the ball velocity after it hits any of the paddle
			ball.xVelocity++;
			if(ball.yVelocity>0)
				ball.yVelocity ++;
			else
				ball.yVelocity --;
			
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		//Gives score for the players and create a new paddle and a new ball
		if(ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
		}
		
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
		}

	}
	
	public void run() {
		
		//basic gampe loop for 60 fps
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double nanoSeconds = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now-lastTime)/nanoSeconds;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta --;
			}
		}
	}
	
	public class ActiveLisener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
