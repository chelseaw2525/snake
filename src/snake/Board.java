package src.snake;

import java.awt.Color;
import java.awt.Graphics2D;

import src.utilities.GDV5;

public class Board {
	private static Tile[] board;
	
	int width = GDV5.getMaxWindowX();
	int height = GDV5.getMaxWindowY();
	
	public void makeBoard() {
		int x = 0, y = 0;
		int tVal = (width * height)/(int)(Math.pow(Tile.tSize(), 2));
		board = new Tile[tVal];
		int dir = 0;
		for (int i = 0; i < tVal; i++) {
			if (i > tVal/2) {
				dir = 2;
			}
			board[i] = new Tile(x, y, new Color(0x11aa00), dir);
			x += Tile.tSize();
			if (i != 0 && (i+1) % (width/Tile.tSize()) == 0) {
				x = 0;
				y += Tile.tSize();
			}
		}
	}
	
	public int bSize() {
		return board.length;
	}
	
	public Tile tile(int n) {
		return board[n];
	}
	
	public static Tile midCol(int i) {
		return board[board.length/25*i + 12];
	}
	
	public void drawBoard(Graphics2D bg) {
		for (Tile t : board) {
			t.draw(bg, true);
		}
	}
}
