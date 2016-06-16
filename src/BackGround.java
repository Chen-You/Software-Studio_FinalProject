import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGround {
	private int CurrentX, CurrentY;
	private int nowpic = 1;
	public File f1 = new File("ani/1.png");
	public File f2 = new File("ani/2.png");
	public File f3 = new File("ani/3.png");
	public File f4 = new File("ani/4.png");
	public File f5 = new File("ani/5.png");
	public File f6 = new File("ani/6.png");
	public File f7 = new File("ani/7.png");
	public File f8 = new File("ani/8.png");
	BufferedImage img1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	BufferedImage img2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	BufferedImage img3 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	BufferedImage img4 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	BufferedImage img5 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	BufferedImage img6 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	BufferedImage img7 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	BufferedImage img8 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	public BackGround(){
		initial();
		try{
			img1 = ImageIO.read(f1);
			img2 = ImageIO.read(f2);
			img3 = ImageIO.read(f3);
			img4 = ImageIO.read(f4);
			img5 = ImageIO.read(f5);
			img6 = ImageIO.read(f6);
			img7 = ImageIO.read(f7);
			img8 = ImageIO.read(f8);
		}catch(IOException e){
			
		}
	}
	
	public Image getImage(){
		if(nowpic == 1)
			return img1;
		else if(nowpic == 2)
			return img2;
		else if(nowpic == 3)
			return img3;
		else if(nowpic == 4)
			return img4;
		else if(nowpic == 5)
			return img5;
		else if(nowpic == 5)
			return img6;
		else if(nowpic == 5)
			return img7;
		else
			return img8;
	}
	
	public void initial(){
		CurrentX = 0;
		CurrentY = 0;
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
	
	public void changenowpic(){
		nowpic++;
	}
	
	public int getnowpic(){
		return this.nowpic;
	}
	
	public void setnowpic(int i){
		this.nowpic = i;
	}
}


