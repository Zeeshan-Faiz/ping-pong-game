# Ping Pong Game Documentation

## Overview

This simple Ping Pong game, developed in Java, provides a classic two-player experience where players control paddles to hit a bouncing ball back and forth across the screen. The objective is to score points by making the ball pass the opponent's paddle.

## Features

### 1. Game Screen

The game is played on a screen with two paddles on either side and a ball that moves between them. Each player controls one paddle.

### 2. Player Controls

- **Player 1**: Controls the left paddle, typically using the keyboard (e.g., up and down arrow keys).
- **Player 2**: Controls the right paddle, usually with a separate set of keys (e.g., 'W' and 'S').

### 3. Ball Movement

The ball bounces between the paddles, changing direction when it hits the top, bottom, or either paddle. The speed of the ball may increase as the game progresses.

### 4. Scoring

Players score points when their opponent fails to hit the ball, causing it to go past their paddle. The game keeps track of the score.

### 5. Game Over

The game ends when one of the players reaches the winning score. The winner is declared, and players have the option to restart the game.

## How to Play

1. **Player 1 Controls**: Use keyboard up and down arrow keys to move the right paddle up and down.
2. **Player 2 Controls**: Use 'W' and 'S' keys to move the left paddle up and down.
3. **Scoring**: Score points by making the ball pass your opponent's paddle.
4. **Winning**: The game ends when one player reaches the predefined score.
5. **Restart**: After the game concludes, players can restart for a new match.

## Sample Code

```java
// Sample code snippet for handling paddle movement
switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
				move();				
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
				move();				
			}
			break;
			
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
				move();				
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();				
			}
			break;
		}
