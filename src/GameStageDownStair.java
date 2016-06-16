import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

public class GameStageDownStair extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public CharacterDownStair ch;
	private Stair[]  st;
	private Random ran = new Random();
	
	private int stairNum;
	private boolean GG;
	private DownStairBK dwbk;
	
	private JLabel scoreLabel,HpLabel;
	
	public GameStageDownStair(){
		ch = new CharacterDownStair();
		ch.setPosX(50);
		ch.setPosY(100);
		ch.setHp(100);
		
		dwbk = new DownStairBK();
		
		st= new Stair[4];
		
		for(int i=0;i<4;i++){
			st[i]=new Stair();
			
			if(ran.nextInt(3)==0){
				st[i].setDamage(true);
			}else{
				st[i].setDamage(false);
			}
			
			if(i==0){
				st[i].setPosX(50);
			}else{
			st[i].setPosX(ran.nextInt(350));
			}
			st[i].setPosY(175+i*175);
		}
		
		stairNum = 0;
		
		scoreLabel = new JLabel("FLOOR :" + this.stairNum);
		scoreLabel.setBounds(0,0, 100, 50);
		scoreLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		add(scoreLabel);
		HpLabel = new JLabel("HP :" + ch.getHp());
		HpLabel.setBounds(100,0, 100, 50);
		HpLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		add(HpLabel);
		
		GG = false;
		
		setFocusable(true);
	}
	public void initial(){
		ch.setHp(100);
		stairNum = 0;
		ch.setPosX(50);
		ch.setPosY(100);
		GG = false;
		
		for(int i=0;i<4;i++){
			if(ran.nextInt(3)==0){
				st[i].setDamage(true);
			}else{
				st[i].setDamage(false);
			}
			
			if(i==0){
				st[i].setPosX(50);
			}else{
			st[i].setPosX(ran.nextInt(350));
			}
			st[i].setPosY(175+i*175);
		}
	}
	
	public void moveAll(){
		ch.setPosY(ch.getPosY()-1);
		for(int i=0;i<4;i++){
			if(st[i].getPosY()==100){
				if(i==0){
					st[0].setPosY(st[3].getPosY()+175);
				}
				else{
					st[i].setPosY(st[i-1].getPosY()+175);
				}
				st[i].setPosX(ran.nextInt(350));
				if(ran.nextInt(3)==0){
					st[i].setDamage(true);
				}else{
					st[i].setDamage(false);
				}
				st[i].setFirst(true);
			}
			st[i].setPosY(st[i].getPosY()-1);
		}
	}
	
	public void run(){
		while(ch.getHp()>0){
			try {
				if(this.stairNum<=20){
					
				Thread.sleep(20);
				}else{
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			moveAll();
			for(int i=0;i<4;i++){
				if(ch.getPosX()+25>st[i].getPosX() && ch.getPosX()+25 < st[i].getPosX()+100 && ch.getPosY()+75>=st[i].getPosY() && ch.getPosY()+75<=st[i].getPosY()+5){
					if(st[i].getDamage()==true && st[i].getFirst()==true){
						ch.setHp(ch.getHp()-5);
						stairNum++;
						st[i].setFirst(false);
					}
					ch.setPosY(st[i].getPosY()-75);
					if(st[i].getFirst()==true){
						stairNum++;
						st[i].setFirst(false);
					}
				}
				else{
					ch.setPosY(ch.getPosY()+1);
				}
			}
			
			if(ch.getPosY()==50||ch.getPosY()>=750){
				ch.setHp(0);
			}
			
			ch.move();
			HpLabel.setText("HP :" + ch.getHp());
			scoreLabel.setText("FLOOR :" + this.stairNum);
			this.repaint();
		}
		GG = true;
		System.out.println("GG!!!");
	}
	
	public boolean getGG(){
		return this.GG;
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color c = new Color(0x99D9EA);
        
        g.drawImage(dwbk.getImage(), 0, 0, 450, 750, null);
        g.drawImage(ch.getImage(), ch.getPosX(), ch.getPosY(),50,75, null);
        for(int i=0;i<4;i++){
        	g.drawImage(st[i].getImage(),st[i].getPosX(), st[i].getPosY(),100,50, null);
        }
    }
	
}