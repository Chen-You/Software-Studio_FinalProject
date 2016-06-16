
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CharacterDownStair {
	private int hp;
	private int posX;
	private int posY;
	private boolean dir = false;
	private File img = new File("bird_right.png"); 
	private File img2 = new File("bird_left.png"); 
	private BufferedImage bi = new BufferedImage( 500, 500, BufferedImage.TYPE_INT_RGB );
	private BufferedImage bi2 = new BufferedImage( 500, 500, BufferedImage.TYPE_INT_RGB );
	
private BufferedImage image;
	
	public CharacterDownStair(){
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
	
	public Image getImage(){
		return image;
	}
	
	public void setHp(int h){
		this.hp = h;
	}
	
	public int getHp(){
		return this.hp;
	}
	
	public void setDir(boolean b){
		this.dir = b;
		if(dir == true)
			this.setImage(bi);
		else
			this.setImage(bi2);
	}
	
	public void move(){
		if(dir==true){
			this.setPosX(this.posX+2);
		}else{
			this.setPosX(this.posX-2);
		}
	}
	
}
