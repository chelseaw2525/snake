package src.snake;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics2D;

@SuppressWarnings({"serial"})
public class Tile extends Rectangle {
	private int dir;
	private Color col;
	private static int size = 20;
	private int moveX;
	private int moveY;
	
	public Tile(int x, int y, Color col, int dir) {
		super(x, y, 0, 0);
		this.setSize(size, size);
		this.col = col;
		this.dir = dir;
	}
	
	public static int tSize() {
		return size;
	}
	
	public void setDirection(int direction) {
		dir = direction;
	}
	
	public int getDir() {
		return dir;
	}
	
	public void setHeadDirection(boolean[] KeysPressed) {
		if (KeysPressed[KeyEvent.VK_LEFT] && dir != 2)			dir = 0;
		else if (KeysPressed[KeyEvent.VK_UP] && dir != 3) 		dir = 1;
		else if (KeysPressed[KeyEvent.VK_RIGHT] && dir != 0) 	dir = 2;
		else if (KeysPressed[KeyEvent.VK_DOWN] && dir != 1) 	dir = 3;
	}

	public void mirrorHeadDirection(boolean[] KeysPressed) {
		if (KeysPressed[KeyEvent.VK_RIGHT] && dir != 2)			dir = 0;
		else if (KeysPressed[KeyEvent.VK_DOWN] && dir != 3) 		dir = 1;
		else if (KeysPressed[KeyEvent.VK_LEFT] && dir != 0) 	dir = 2;
		else if (KeysPressed[KeyEvent.VK_UP] && dir != 1) 	dir = 3;
	}
	public void updateDirection(Board board) {
		for (int i = 0; i < board.bSize(); i++) {
			if (this.getX() == board.tile(i).getX() && this.getY() == board.tile(i).getY()) {
				board.tile(i).setDirection(this.getDir());	
			}
		}
	}
	
	private static int speed = 20;
	public void turn() {
		int d = this.dir;
		if (d == 0 ) {
			this.moveX = -1 * speed;
			this.moveY = 0;
		}
		if (d == 1) {
			this.moveX = 0;
			this.moveY = -1 * speed;
		}
		
		if (d == 2) {
			this.moveX = speed;
			this.moveY = 0;
		}
		
		if (d == 3) {
			this.moveX = 0;
			this.moveY = speed;
		}
	}
	
	private static int timer = 20;
	private static boolean isStop = false;
	public void moveTile(boolean[] KeysPressed) {
		stop(KeysPressed);
		if(!isStop) {
			this.turn();
			this.translate(moveX, moveY);
		}
		timer++;
	}
	
	private static int spaceCount = 1;
	public void stop(boolean[] KeysPressed) {
		if (timer >= 20) {
			if (KeysPressed[KeyEvent.VK_SPACE]) {
				spaceCount++;
				timer = 0;
			}
			if (spaceCount % 2 == 0 && !isStop )		isStop = true;
			else if (spaceCount % 2 == 1 && isStop) 	isStop = false;
		}
	}

	public static boolean stopped() {
		return isStop;
	}
	
	public void draw(Graphics2D bg, boolean fill) {
		bg.setColor(this.col);
		if (fill) bg.fill(this);
		bg.setColor(this.col.darker());
		bg.draw(this);
		bg.drawString(""+this.getDir(), (int)this.getCenterX(), (int)this.getCenterY());
	}
}

