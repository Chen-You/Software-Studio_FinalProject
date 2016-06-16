import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.Image;

public class Food {
	private int CurrentX, CurrentY;
	public File f1 = new File("raman.png");
	BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	
	public Food(int x, int y){
		this.init(x, y);
		try{
			img = ImageIO.read(f1);
		}catch(Exception e){}
	}
	
	public void init(int x, int y){
		this.CurrentX = x;
		this.CurrentY = y;
	}
	
	public Image getImage(){
		return img;
	}
	
	public int getX(){
		return this.CurrentX;
	}
	
	public int getY(){
		return this.CurrentY;
	}
}
