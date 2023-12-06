package src.utilities;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;


public class Sound {
	public static void playSound(String filename) {
		new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/assets/"+ filename));
		        clip.open(inputStream);
		        clip.start();
		      } 
		      catch (Exception e) {
		    	  System.err.println(e.getMessage());
		      }
		    }
		  }).start();
	}
	
	public static void loop(String filename) {
		new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/assets/" + filename));
		        clip.open(inputStream);
		        clip.loop(Clip.LOOP_CONTINUOUSLY);
		      } 
		      catch (Exception e) {
		    	  System.err.println(e.getMessage());
		      }
		    }
		  }).start();
	}
	
}
