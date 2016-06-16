import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Glass {
	private int CurrentX, CurrentY;
	
	public File f1 = new File("glass.png");
	BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	public Glass(){
		initial();
		try{
			img = ImageIO.read(f1);
		}catch(IOException e){
			
		}
	}
	
	public void initial(){
		CurrentX = 0;
		CurrentY = 0;
	}
	
	public Image getImage(){
		return img;
	}
	
	public int getX(){
		return CurrentX;
	}
	
	public int getY(){
		return CurrentY;
	}
}
