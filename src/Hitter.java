import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Hitter {
	private int posX,posY;	
	private BufferedImage image;
	private File img = new File("gun.png"); 
	private BufferedImage bi = new BufferedImage( 500, 500, BufferedImage.TYPE_INT_RGB );
	private boolean isPressed = false;
	private File img2 = new File("gun_shoot.png"); 
	private BufferedImage bi2 = new BufferedImage( 500, 500, BufferedImage.TYPE_INT_RGB );
	
	public Hitter(){
		try{
			bi = ImageIO.read(img);
			bi2 = ImageIO.read(img2);
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
	
	public void setIsPressed(boolean b){
		isPressed = b;
		if(b==false){
			setImage(bi);
		}else{
			setImage(bi2);
		}
	}
	
	public Image getImage(){
		return image;
	}
}
