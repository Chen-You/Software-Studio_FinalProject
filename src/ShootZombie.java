import javax.swing.*;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.awt.*;


public class ShootZombie extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	Character[] characters;
	public Hitter[] hitters;
	private Random rand = new Random();
	public int score;
	private JLabel scoreLabel;
	private JLabel timeLabel;
	
	private int time;
	private int timeCount;
	private ZombieBK zbk;
	
	private int GG;
	
	public ShootZombie(){
		zbk = new ZombieBK();
		characters = new Character[7];
		hitters = new Hitter[3];
		
		hitters[0] = new Hitter();
		hitters[0].setPosX(50);
		hitters[0].setPosY(650);
		
		
		hitters[1] = new Hitter();
		hitters[1].setPosX(200);
		hitters[1].setPosY(650);
		
		
		hitters[2] = new Hitter();
		hitters[2].setPosX(350);
		hitters[2].setPosY(650);
		
		for(int i=0;i<7;i++){
			characters[i] = new Character();
			characters[i].setPosX(50+150*rand.nextInt(3));
			characters[i].setPosY(50+75*i);
		}
		this.init();
		scoreLabel = new JLabel("Score :" + this.score);
		scoreLabel.setBounds(0,0, 100, 50);
		scoreLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		add(scoreLabel);
		timeLabel = new JLabel("TIME :" + this.time);
		timeLabel.setBounds(100,0, 100, 50);
		timeLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		add(timeLabel);
		
		this.timeCount = 0;
		
		
	}
	
	public void init(){
		this.score = 0;
		this.time = 15;
		this.GG = 0;
	}
	
	public boolean checkHit(int n){
		for(int i=0;i<7;i++){
			if(characters[i].getPosY()==500){
				System.out.println(characters[i].getPosX());
				if((50+150*n)==characters[i].getPosX()){
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	
	
	
	public void moveAll(){
		System.out.println(this.score);
			for(int i=0;i<7;i++){
				//System.out.println(characters[i].getPosY());
				if(characters[i].getPosY()==500){
					characters[i].setPosX(50+rand.nextInt(3)*150);
					characters[i].setPosY(50);
				}else{
				characters[i].setPosY(characters[i].getPosY()+75);
				}
			}
	}
	
	
	public void run(){
		
		while(time>0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timeCount++;
			if(timeCount==10){
				time--;
				timeLabel.setText("TIME :" + this.time);
				timeCount = 0;
			}
			
			scoreLabel.setText("Score :" + this.score);
			
			this.repaint();
		}
		this.GG = 1;
		System.out.println("GG!!!!!!");
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color c = new Color(0x99D9EA);
        setBackground(c);
        g.drawImage(zbk.getImage(), 0, 0, 450, 750, null);
        for(int i=0;i<3;i++){
        	g.drawImage(hitters[i].getImage(), hitters[i].getPosX(), hitters[i].getPosY(),100,100, this);
        }
        for(int i=0;i<7;i++){
        	g.drawImage(characters[i].getImage(), characters[i].getPosX(), characters[i].getPosY(),75,75, this);
        }
    }
	
	public int getGG(){
		return this.GG;
	}

}
