package src.snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import src.utilities.GDV5;
import src.utilities.Sound;

public class leSnek {
	
	private static ArrayList<Tile> snek;
	private Color bCol = new Color (hexCol);
	private static int hexCol = 0x0;
	private boolean alive = true;
	
	public leSnek(int length) {
		hexCol = 0x0;
		int x = Board.midCol(0).x;
		int y = Board.midCol(6).y;
		snek = new ArrayList<Tile>();
		snek.add(new Tile(x, y, new Color(0x0), 0));
		for (int i = 0; i < length; i++) {
			x += Tile.tSize();
			snek.add(new Tile(x, y, changeColor(), 0));
		}
	}
	
	public Color changeColor() {  //make the color the new shade
		hexCol += 0x11;
		bCol = new Color(hexCol, false);
		return bCol;
	}

	public static void updateBodyDir(Board board) {
		for (int i = 0; i < board.bSize(); i++) {
			for (int j = 1; j < snek.size(); j++) {
				if (snek.get(j).getX() == board.tile(i).getX() && snek.get(j).getY() == board.tile(i).getY()) {
					snek.get(j).setDirection(board.tile(i).getDir());
				}
			}
		}
	}
	
	public void moveSnek (Board board, boolean[] KeysPressed) {
		updateBodyDir(board);
		for (Tile t : snek)	{
			t.moveTile(KeysPressed);
		}
	}
	
	public boolean alive() {
		Tile head = snek.get(0);
		boolean bonk = snakeIntersect(head);
		if (head.getDir() == 0 ) 		alive = head.getMinX() >= 0 && !(bonk);
		else if (head.getDir() == 1)	alive = head.getMinY() >= 0 && !(bonk);
		else if (head.getDir() == 2) 	alive = head.getMaxX() <= GDV5.getMaxWindowX() && !(bonk);
		else if (head.getDir() == 3) 	alive = head.getMaxY() <= GDV5.getMaxWindowX() && !(bonk);
		if (!alive) Sound.playSound("bonk.wav");
		return alive;
	}
	
	public boolean snakeIntersect(Tile h) {
		for (int i = 2; i < snekLen(); i++) {
			if (h.intersects(snek.get(i))) {
				Sound.playSound("bonk.wav");
				return true;
			}
		}
		return false;
	}
	
	public boolean alive(elKens kens) {  //for mirror
		Tile head = snek.get(0);
		boolean bonk = snakeIntersect(head, kens);
		if (head.getDir() == 0 ) 		alive = head.getMinX() >= 0 && !(bonk);
		else if (head.getDir() == 1)	alive = head.getMinY() >= 0 && !(bonk);
		else if (head.getDir() == 2) 	alive = head.getMaxX() <= GDV5.getMaxWindowX() && !(bonk);
		else if (head.getDir() == 3) 	alive = head.getMaxY() <= GDV5.getMaxWindowX() && !(bonk);
		if (!alive) Sound.playSound("bonk.wav");
		return alive;
	}
	
	public boolean snakeIntersect(Tile h, elKens kens) { //for mirror
		for (int i = 2; i < snekLen(); i++) {
			if (h.intersects(snek.get(i)) || h.intersects(kens.getKensPart(i))) return true;
		}
		return false;
	}
	
	public void growSnek(Board board) {
		int x = (int) snek.get(snek.size()-1).getX();
		int y = (int) snek.get(snek.size()-1).getY();
		int dir = snek.get(snek.size()-1).getDir();
		if (dir == 0) 	x += Tile.tSize();
		if (dir == 1) 	y += Tile.tSize();
		if (dir == 2) 	x -= Tile.tSize();
		if (dir == 3)	y -= Tile.tSize();
		Tile t = new Tile(x, y, changeColor(), dir);
		snek.add(t);	
	}
	
	public Tile getSnekPart(int i) {
		return snek.get(i);
	}
	
	public int snekLen() {
		return snek.size();
	}
	
	public void drawSnek(Graphics2D win) {
		for (Tile t :snek) {
			t.draw(win, true);
		}
	}
}

