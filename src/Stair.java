import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Stair {
	private int posX;
	private int posY;
	private boolean damage ;
	private File img = new File("normalBlock.png"); 
	private BufferedImage bi = new BufferedImage( 500, 500, BufferedImage.TYPE_INT_RGB );
	private boolean first = true;
	private File img2 = new File("damageBlock.png"); 
	private BufferedImage bi2 = new BufferedImage( 500, 500, BufferedImage.TYPE_INT_RGB );
	
	private BufferedImage image = bi;
	private BufferedImage image2 = bi2;
	
	public Stair(){
		try{
			bi = ImageIO.read(img);
			bi2 = ImageIO.read(img2);
		}catch (IOException e) { }
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
	
	public void setDamage(boolean b){
		damage = b;
		if(damage==true){
			this.setImage(bi2);
		}
		else{
			this.setImage(bi);
		}
	}
	public boolean getDamage(){
		return this.damage;
	}
	
	public void setFirst(boolean b){
		first = b;
	}
	public boolean getFirst(){
		return this.first;
	}
	
}