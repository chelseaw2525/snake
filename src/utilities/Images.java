package src.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	public BufferedImage yum; {
		try {
			File file = new File("src/assets/yum.png");
			FileInputStream fis = new FileInputStream(file);
			yum = ImageIO.read(fis);
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}
}


