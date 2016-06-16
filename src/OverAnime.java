import java.awt.Graphics;
import java.awt.MouseInfo;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.lang.Thread;
import javax.swing.*;

import java.util.Random;
import java.awt.Dimension;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OverAnime extends JPanel implements Runnable{
	private BackGround BackGround;
	private int end = 0;
	public OverAnime(){
		BackGround = new BackGround();
		this.init();
	}
	
	public void init(){
		this.BackGround.setnowpic(1);
		this.end = 0;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(BackGround.getImage(), BackGround.getX(), BackGround.getY(), 450, 750, null);
	}
	
	public void run(){
		while(BackGround.getnowpic() < 9){
			try {
				Thread.sleep(110);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.BackGround.changenowpic();
			this.repaint();
		}
		this.end = 1;
	}
	
	public int getend(){
		return this.end;
	}
}
