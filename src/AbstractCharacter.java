import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class AbstractCharacter 
{
	private int MaxHp, NowHp;
	private int Damage;
	private int Defense;
	private int MaxPhysical, NowPhysical;
	private int Cohesion;
	private boolean isActive;
	private Image imageleft, imageright,imageHit;
	abstract public String getName();	//return the name of the character
	abstract public void initial(); // initial the parameter of the character
	
	public void setActive(){
		isActive = true;
	}
	
	public void disActive(){
		isActive = false;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public int getNowHp()
	{
		return NowHp;
	}
	
	public int getMAXHp(){
		return MaxHp;
	}
	
	public void setNowHp(int now)
	{
		NowHp = now;
	}
	public void setMaxHp(int max)
	{
		MaxHp = max;
	}
	
	public int getNowPhysical()//體力
	{
		return NowPhysical;
	}
	public int getMaxPhysical()
	{
		return MaxPhysical;
	}
	public void setNowPhysical(int nowphysical)
	{
		NowPhysical = nowphysical;
	}
	public void setMaxPhysical(int maxphysical)
	{
		MaxPhysical = maxphysical;
	}
	
	public int getDamage()
	{
		return Damage;
	}
	public void setDamage(int damage)
	{
		Damage = damage;
	}
	public int getDefense()
	{
		return Defense;
	}
	public void setDefense(int defense)
	{
		Defense = defense;
	}
	public int getCohesion()
	{
		return Cohesion;
	}
	public void setCohesion(int cohesion)
	{
		Cohesion = cohesion;
	}
	
	public void setImageleft(BufferedImage bleft)//抓影像
	{
		imageleft = bleft;
	}
	public Image getImageleft()
	{
		return imageleft;
	}
	
	public void setImageright(BufferedImage bright)
	{
		imageright = bright;
	}
	public Image getImageright()
	{
		return imageright;
	}
	
	public void setHitImage(BufferedImage b){
		imageHit = b;
	}
	public Image getHitImage(){
		return imageHit;
	}
}
