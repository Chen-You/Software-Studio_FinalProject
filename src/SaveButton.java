import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class SaveButton {
	private int CurrentX, CurrentY;
	private int Pressed = 0;
	public File f1 = new File("SaveButton.png");
	public File f2 = new File("SaveButton_Press.png");
	BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	BufferedImage img2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	public SaveButton(){
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
		CurrentX = 320;
		CurrentY = 600;
	}
	
	public int getX(){
		return CurrentX;
	}
	
	public int getY(){
		return CurrentY;
	}
	
	public boolean OnButton(int x, int y){
		if(x > CurrentX && x < CurrentX + 90 && y > CurrentY && y < CurrentY + 90)
			return true;
		else
			return false;
	}
	
	public void changePressed(){
		if(this.Pressed == 1)
			this.Pressed = 0;
		else
			this.Pressed = 1;
	}
}

