package src.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

import src.utilities.Images;


public class Screens implements ImageObserver {
	static Font title = new Font("COMIC_SANS", Font.BOLD, 30);
	static Font text = new Font("COMIC_SANS", Font.PLAIN, 15);
	static Font num = new Font("SERIF", Font.BOLD, 50);
	static Font stop = new Font("COMIC_SANS", Font.BOLD, 100);
	Images image = new Images();

	public void drawSplash(Graphics2D win) {
		win.setColor(Color.WHITE);
		win.setFont(title);
		win.drawString("snek, by chelsea wen", 100, 100);
		win.setFont(text);
		win.drawString("use arrows to move snek and eat fruit, don't crash", 90, 150);
		win.setColor(Color.BLUE.brighter());
		win.drawString("select number of fruit at once. press enter to start", 90, 200);
	}
	
	public void initGame(Graphics2D win, Board b, leSnek s, Yums yums, elKens a, boolean mirror) {
		b.drawBoard(win);
		s.drawSnek(win);
		yums.drawYums(win);
		win.drawImage(image.yum, yums.getYum().x, yums.getYum().y, this);
		if (mirror) {
			a.drawKens(win);
		}
	}

	public void drawGameOver(Graphics2D win, int length) {
		win.setColor(Color.WHITE);
		win.setFont(title);
		win.drawString(">:| snek got stepped", 99, 150);
		win.drawString("it was " + length + " units long", 120, 200);
		win.setColor(Color.YELLOW);
		win.setFont(text);
		win.drawString("Play again? (Press ENTER)", 150, 250);
	}
	
	public void drawVictory(Graphics2D win) {
		win.setColor(Color.WHITE);
		win.setFont(title);
		win.drawString("No way! You filled the map!", 60, 150);
		win.setColor(Color.YELLOW);
		win.setFont(text);
		win.drawString("Play again? (Press ENTER)", 150, 250);
	}
	
	public boolean changeScreen(boolean[] KeyType) {
		if (KeyType[KeyEvent.VK_ENTER]) return false;
		return true;
	}
	
	public boolean mirror(boolean[] KeyType, Graphics2D win) {
		boolean m;
		win.setColor(Color.WHITE);
		win.drawString("hold shift + enter for harder version -- mirror snake", 90, 250);
		if (KeyType[KeyEvent.VK_SHIFT])	m = true;
		else 	m = false;
		win.drawString("mirrored? " + m, 90, 275);
		return m;
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}

