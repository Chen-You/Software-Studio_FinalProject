import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DownStairBK {
	private int X, Y;
	public File f1 = new File("dwbk.png");
	BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	public DownStairBK(){
		initial();
		try{
			img = ImageIO.read(f1);
		}catch(IOException e){
			
		}
	}
	
	public void initial(){
		X = 0;
		Y = 0;
	}
	
	public Image getImage(){
		return img;
	}
	
	public int getX(){
		return X;
	}
	
	public int getY(){
		return Y;
	}
}
