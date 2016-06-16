import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Character {
	private File img = new File("zombie.png"); 
	private BufferedImage bi = new BufferedImage( 500, 500, BufferedImage.TYPE_INT_RGB );
	private Random rand = new Random();
	private int posX,posY;
	private BufferedImage image;
	
	public Character(){
		try{
			bi = ImageIO.read(img);
		}catch (IOException e) { }
		setImage(bi);		
	}
	
	public void setPosX(int x){
		this.posX = x;
	}
	
	public void setPosY(int y){
		this.posY = y;
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public void setImage(BufferedImage bi){
		image = bi;
	}
	
	public Image getImage(){
		return image;
	}
	
}
