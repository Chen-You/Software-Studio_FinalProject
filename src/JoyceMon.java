import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

public class JoyceMon  extends AbstractCharacter implements Runnable {
	private int now_x = 75;
	private int now_y = 75;
	public int Cohesion = 0;
	private Random ran = new Random();
	private int destination_x = now_x;
	private int destination_y = now_y;
	private GameStart G1;
	
	public File f1 = new File("JoyceMonBabyleft.png");
	public File f2 = new File("JoyceMonBabyright.png");
	public File f3 = new File("JoyceMonTeenleft.png");
	public File f4 = new File("JoyceMonTeenright.png");
	public File f5 = new File("JoyceMonAdultleft.png");
	public File f6 = new File("JoyceMonAdultright.png");
	public File f7 = new File("JoyceMonBabyHitted.png");
	public File f8 = new File("JoyceMonTeenHitted.png");
	public File f9 = new File("JoyceMonAdultHitted.png");
	
	private BufferedImage bleft1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	private BufferedImage bright1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	private BufferedImage bleft2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	private BufferedImage bright2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	private BufferedImage bleft3 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	private BufferedImage bright3 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	private BufferedImage bh1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	private BufferedImage bh2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	private BufferedImage bh3 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	
	public int MonMove = 0;
	
	private boolean Ask = false;
	
	public JoyceMon(GameStart g1){
		this.G1 = g1;
		try{
			bleft1 = ImageIO.read(f1);
			bright1 = ImageIO.read(f2);
			bleft2 = ImageIO.read(f3);
			bright2 = ImageIO.read(f4);
			bleft3 = ImageIO.read(f5);
			bright3 = ImageIO.read(f6);
			bh1 = ImageIO.read(f7);
			bh2 = ImageIO.read(f8);
			bh3 = ImageIO.read(f9);
		}catch(IOException e){}
		if (this.Cohesion < 10){
			this.setImageleft(bleft1);
			this.setHitImage(bh1);
		}
		else if (this.Cohesion < 30 && this.Cohesion > 9){
			this.setImageleft(bleft2);
			this.setHitImage(bh2);
		}
		else{
			this.setImageleft(bleft3);
			this.setHitImage(bh3);
		}
	}
	
	public String getName() {
		if(this.Cohesion < 10)
			return "Joyce Monster Baby";
		else if(this.Cohesion < 30 && this.Cohesion > 9)
			return "Mechanical Joyce Monster";
		else 
			return "War Joyce Monster";
	}
	
	public Image getImageleft(){
		if (this.Cohesion < 10){
			return bleft1;
		}
		else if (this.Cohesion < 30 && this.Cohesion > 9){
			return bleft2;
		}
		else{
			return bleft3;
		}
	}
	
	public Image getHitImage(){
		if (this.Cohesion < 10){
			return bh1;
		}
		else if (this.Cohesion < 30 && this.Cohesion > 9){
			return bh2;
		}
		else{
			return bh3;
		}
	}
	
	public void AskQ(){
		if(this.ran.nextInt(3000) < 5){
			this.Ask = true;
		}
	}
	
	public void initial()
	{
		if(this.Cohesion < 10)
		{
			this.setMaxHp(500);
			this.setNowHp(500);
			this.setDamage(120);
			this.setDefense(30);
			this.setMaxPhysical(50);
			this.setNowPhysical(50);
			System.out.println("OK");
		}
		else if(this.Cohesion > 9 && this.Cohesion < 30)
		{
			this.setMaxHp(1500);
			this.setNowHp(1500);
			this.setDamage(200);
			this.setDefense(30);
			this.setMaxPhysical(80);
			this.setNowPhysical(80);
		}
		else
		{
			this.setMaxHp(3000);
			this.setNowHp(3000);
			this.setDamage(250);
			this.setDefense(30);
			this.setMaxPhysical(100);
		}
	}
	
	public Image getImage(){
		if(this.Cohesion < 10){
			if(this.MonMove < 50){
				return bleft1;
			}else{
				return bright1;
			}
		}else if(this.Cohesion > 9 && this.Cohesion < 30){
			if(this.MonMove < 50){
				return bleft2;
			}else{
				return bright2;
			}
		}else{
			if(this.MonMove < 50){
				return bleft3;
			}else{
				return bright3;
			}
		}
	}
	
	public int getnow_x() {
		//System.out.println("here is " + now_x);
		return now_x;
	}

	public int getnow_y() {
		//System.out.println("here is " + now_y);
		return now_y;
	}

	public void setnow_x(int x) {
		this.now_x = x;
	}

	public void setnow_y(int y) {
		this.now_y = y;
	}

	public void set_destination() {
		if (ran.nextInt(1000) < 4) {
			this.destination_x = ran.nextInt(355);
			this.destination_y = ran.nextInt(500);
		}
		//System.out.println("OOOOO");
	}
	
	public int getsize(){
		if(this.Cohesion < 10)
			return 100;
		else if(this.Cohesion < 30 && this.Cohesion > 9)
			return 200;
		else
			return 300;
	}
	
	public int getdes_x() {
		return destination_x;
	}

	public int getdes_y() {
		return destination_y;
	}
	
	public void doSomeWork(){
		if(G1.FoodList.size()!=0){
			this.destination_x = G1.FoodList.get(0).getX();
			this.destination_y = G1.FoodList.get(0).getY();
		}
		
		if (getnow_x() == getdes_x()
				&& getnow_y() == getdes_y()) {
			// System.out.println("aaaaa");
			if(G1.FoodList.size()==0)
			set_destination();
			else{
				G1.FoodList.remove(0);
				this.Cohesion = this.Cohesion + 3;
			}
		}

		if (getnow_x() < getdes_x())
			setnow_x(getnow_x() + 1);
		else if (getnow_x() > getdes_x())
			setnow_x(getnow_x() - 1);

		if (getnow_y() < getdes_y())
			setnow_y(getnow_y() + 1);
		else if (getnow_y() > getdes_y())
			setnow_y(getnow_y() - 1);
		
		this.MonMove++;
		if(this.MonMove > 98)
			this.MonMove = 0;
		
		if(this.Ask == false){
			this.AskQ();
		}
		
	}
	
	public boolean getAsk(){
		return this.Ask;
	}
	
	public void setAsk(boolean t){
		this.Ask = t;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			
			// System.out.println("Thread GameStart");
			try {
				Thread.sleep(50);
				this.doSomeWork();
			} catch (InterruptedException e) {
			}
		}
	}
	
	
}
