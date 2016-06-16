import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.Image;

public class FeedButton {
	private int CurrentX, CurrentY;
	private int Pressed = 0;
	public File f1 = new File("feed.png");
	public File f2 = new File("FeedButton_Pressed.png");
	BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	BufferedImage img2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	public FeedButton(){
		initial();
		try{
			img = ImageIO.read(f1);
			img2 = ImageIO.read(f2);
		}catch(IOException e){
			
		}
	}
	
	public Image getImage(){
		if(Pressed == 0)
			return img;
		else
			return img2;
	}
	
	public void initial(){
		CurrentX = 50;
		CurrentY = 600;
	}
	
	public int getX(){
		return CurrentX;
	}
	
	public int getY(){
		return CurrentY;
	}
	
	public boolean OnButton(int x, int y){
		if(x > CurrentX - 10 && x < CurrentX + 90 && y > CurrentY - 10 && y < CurrentY + 90)
			return true;
		else
			return false;
	}
	
	//***************************************************
	
	private boolean lock = false;
	
	public boolean getLock(){
		return lock;
	}
	
	public void setLock(boolean t){
		lock = t;
	}
	
	public void move(int x, int y){
		if(lock){
			CurrentX = x;
			CurrentY = y;
		}
	}
	
	public void changePressed(){
		if(this.Pressed == 1)
			this.Pressed = 0;
		else
			this.Pressed = 1;
	}
}
