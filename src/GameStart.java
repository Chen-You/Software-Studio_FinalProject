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
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.Random;
import java.awt.Dimension;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class GameStart extends JPanel implements MouseListener, Runnable, MouseMotionListener /* KeyListener */ {

	private Glass Glass;
	private WoodBar WoodBar;
	private FeedButton FeedButton;
	private DataButton DataButton;
	private TrainButton TrainButton;
	private SaveButton SaveButton;
	private ConnectButton ConnectButton;
	/*
	 * private JoyceMonBaby JoyceMonBaby; private JoyceMonTeenager
	 * JoyceMonTeenager; private JoyceMonAdult JoyceMonAdult;
	 */

	public JoyceMon JoyceMon;
	public ArrayList<Food> FoodList;
	private Food Food;

	public HaoMon HaoMon;
	private int Status = 1; // 1 GameStart 2 ShootZombies
	private Thread t_ShootZombie;
	private ShootZombie ShootZombie;

	public int MonMove = 0;
	public int Cohesion = 0;
	public int Flag = 1;
	private int foodlock = 0;

	private boolean change_1 = false;
	private boolean change_2 = false;

	private GameStage gs;

	public GameStart(GameStage f) {
		this.gs = f;
		setLayout(null);
		Glass = new Glass();
		WoodBar = new WoodBar();
		FeedButton = new FeedButton();
		DataButton = new DataButton();
		TrainButton = new TrainButton();
		SaveButton = new SaveButton();
		ConnectButton = new ConnectButton();
		FoodList = new ArrayList<Food>();

		JoyceMon = new JoyceMon(this);
		HaoMon = new HaoMon(this);

		JoyceMon.initial();
		HaoMon.initial();

		if (gs.Open_or_not == true)
			Flag = gs.WhichMon;
		if (gs.save == 1){
			Flag = gs.WhichMon;
			if(Flag == 0){
				
				this.JoyceMon.Cohesion = gs.Cohesion;
				if(this.JoyceMon.Cohesion >= 10 && this.JoyceMon.Cohesion < 30) change_1 = true;
				else if(this.JoyceMon.Cohesion >= 30){
					change_1 = true;
					change_2 = true;
				}
			}
			else{
				this.HaoMon.Cohesion = gs.Cohesion;
				if(this.HaoMon.Cohesion >= 10 && this.HaoMon.Cohesion < 25) change_1 = true;
				else if(this.HaoMon.Cohesion >= 25){
					change_1 = true;
					change_2 = true;
				}
			}
		}
		

		

		addMouseListener(this);
		addMouseMotionListener(this);
		// addKeyListener(this);
		// setFocusable(true);

	}
	
	public void setValue(){
		if(Flag == 0){
			
			this.JoyceMon.Cohesion = gs.Cohesion;
			if(this.JoyceMon.Cohesion >= 10 && this.JoyceMon.Cohesion < 30) change_1 = true;
			else if(this.JoyceMon.Cohesion >= 30){
				change_1 = true;
				change_2 = true;
			}
		}
		else{
			this.HaoMon.Cohesion = gs.Cohesion;
			if(this.HaoMon.Cohesion >= 10 && this.HaoMon.Cohesion < 25) change_1 = true;
			else if(this.HaoMon.Cohesion >= 25){
				change_1 = true;
				change_2 = true;
			}
		}
	}
	
	public void startCharacterThread(){
		if (this.Flag == 0) {
			
			Thread t1 = new Thread(JoyceMon);
			t1.start();
		} else {
			Thread h1 = new Thread(HaoMon);
			h1.start();
		}
	}
	
	public void setFlag(int i){
		this.Flag = i;
	}
	
	public AbstractCharacter getMon(){
		if(Flag==0){
			return this.JoyceMon;
		}else{
			return this.HaoMon;
		}
	}

	public void startThread() {
		Thread s = new Thread(this);
		s.start();
	}

	private void saveFile() throws IOException {
		File f = new File("Ima/save.txt");
		FileOutputStream fout = new FileOutputStream(f, false);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
		bw.flush();

		bw.write("Save:1");
		bw.newLine();
		bw.write("Flag:" + this.Flag);
		bw.newLine();
		if (Flag == 0) {
			bw.write("HP:" + this.JoyceMon.getMAXHp());
			bw.newLine();
			bw.write("Damage:" + this.JoyceMon.getDamage());
			bw.newLine();
			bw.write("Cohesion:" + this.JoyceMon.Cohesion);
			bw.newLine();
			bw.write("PhysicalPower:" + this.JoyceMon.getMaxPhysical());
			bw.newLine();
		} else {
			bw.write("HP:" + this.HaoMon.getMAXHp());
			bw.newLine();
			bw.write("Damage:" + this.HaoMon.getDamage());
			bw.newLine();
			bw.write("Cohesion:" + this.HaoMon.Cohesion);
			bw.newLine();
			bw.write("PhysicalPower:" + this.HaoMon.getMaxPhysical());
			bw.newLine();
		}

		bw.flush();
		bw.close();
		fout.close();
		System.out.println("Save Finish.");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(Glass.getImage(), Glass.getX(), Glass.getY(), 450, 750, null);
		for (int i = 0; i < this.FoodList.size(); i++) {
			g.drawImage(this.FoodList.get(i).getImage(), this.FoodList.get(i).getX(), this.FoodList.get(i).getY(), 50,
					50, null);
		}

		if (Flag == 0) {
			g.drawImage(JoyceMon.getImage(), JoyceMon.getnow_x(), JoyceMon.getnow_y(), JoyceMon.getsize(),
					JoyceMon.getsize(), null);
		} else {
			g.drawImage(HaoMon.getImage(), HaoMon.getnow_x(), HaoMon.getnow_y(), HaoMon.getsize(), HaoMon.getsize(),
					null);
		}
		
		g.drawImage(WoodBar.getImage(), WoodBar.getX(), WoodBar.getY(), 450, 150, null);
		g.drawImage(FeedButton.getImage(), FeedButton.getX(), FeedButton.getY(), 70, 70, null);
		//g.drawImage(DataButton.getImage(), DataButton.getX(), DataButton.getY(), 40, 40, null);
		g.drawImage(TrainButton.getImage(), TrainButton.getX(), TrainButton.getY(), 70, 70, null);
		g.drawImage(SaveButton.getImage(), SaveButton.getX(), SaveButton.getY(), 70, 70, null);
		g.drawImage(ConnectButton.getImage(), ConnectButton.getX(), ConnectButton.getY(), 70, 70, null);

	}
	// ****************************************************************************

	public boolean Ask() {
		if (Flag == 0) {
			return this.JoyceMon.getAsk();
		} else {
			return this.HaoMon.getAsk();
		}
	}

	public void setAsk(boolean t) {
		if (Flag == 0) {
			this.JoyceMon.setAsk(t);
		} else {
			this.HaoMon.setAsk(t);
		}
	}

	public int getStatus() {
		return this.Status;
	}

	public void setStatus(int i) {
		this.Status = i;
	}

	// ****************************************************************************
	public void mouseClicked(MouseEvent e) {
		if (FeedButton.OnButton(e.getX(), e.getY())) {
			FeedButton.changePressed();
			if (this.foodlock == 0) {
				this.foodlock = 1;
			} else {
				this.foodlock = 0;
			}
		} else if (TrainButton.OnButton(e.getX(), e.getY())) {

			// System.out.println("bbb");
		} else if (ConnectButton.OnButton(e.getX(), e.getY())) {

			// System.out.println("ccc");
		} else if (SaveButton.OnButton(e.getX(), e.getY())) {
			this.HaoMon.Cohesion++;
			this.JoyceMon.Cohesion++;
			try {
				saveFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// this.setVisible(false);
			// System.out.println("ddd");
		} else if (e.getY() < 575 && this.foodlock == 1) {
			this.Food = new Food(e.getX(), e.getY());
			this.FoodList.add(Food);
		}

	}

	public void mousePressed(MouseEvent e) {
		if (FeedButton.OnButton(e.getX(), e.getY())) {

		} else if (ConnectButton.OnButton(e.getX(), e.getY())) {
			ConnectButton.changePressed();
		} else if (TrainButton.OnButton(e.getX(), e.getY())) {
			TrainButton.changePressed();
		} else if (SaveButton.OnButton(e.getX(), e.getY())) {
			SaveButton.changePressed();
		} else if (DataButton.OnButton(e.getX(), e.getY())) {
			DataButton.changePressed();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (FeedButton.OnButton(e.getX(), e.getY())) {
			// FeedButton.changePressed();
		} else if (TrainButton.OnButton(e.getX(), e.getY())) {
			TrainButton.changePressed();
			this.setStatus(3);
		} else if (ConnectButton.OnButton(e.getX(), e.getY())) {
			ConnectButton.changePressed();
			this.setStatus(4);
		} else if (SaveButton.OnButton(e.getX(), e.getY())) {
			SaveButton.changePressed();
		} else if (DataButton.OnButton(e.getX(), e.getY())) {
			DataButton.changePressed();
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		this.FeedButton.move(e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {
	}

	// ******************************************************
	/*
	 * @Override public void keyTyped(KeyEvent e) {
	 * 
	 * if (e.getKeyCode() == KeyEvent.VK_RIGHT) { System.out.println(
	 * "Right key typed"); } if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	 * System.out.println("Left key typed"); }
	 * 
	 * }
	 * 
	 * @Override public void keyPressed(KeyEvent e) {
	 * 
	 * if (e.getKeyCode() == KeyEvent.VK_RIGHT) { System.out.println(
	 * "Right key pressed"); } if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	 * System.out.println("Left key pressed"); }
	 * 
	 * }
	 * 
	 * @Override public void keyReleased(KeyEvent e) { if (e.getKeyCode() ==
	 * KeyEvent.VK_RIGHT) { System.out.println("Right key Released"); } if
	 * (e.getKeyCode() == KeyEvent.VK_LEFT) { System.out.println(
	 * "Left key Released"); } }
	 * //********************************************************
	 */
	public void doSomeWork() {

		try {
			this.gs.initial();
		} catch (IOException e) {
		}
		this.repaint();

	}

	public void run() {
		// while(this.Status != 3){
		while (true) {
			// System.out.println("Thread GameStart");
			try {
				Thread.sleep(50);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						doSomeWork();
					}
				});
				// this.doSomeWork();
			} catch (InterruptedException e) {
			}
			if (this.Flag == 0) {
				if (this.JoyceMon.Cohesion >= 10 && this.JoyceMon.Cohesion < 30 && change_1 == false) {
					//System.out.println("TTTTT");
					gs.change = 1;
					change_1 = true;
					this.JoyceMon.initial();
					/*try {
						gs.initial();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				} else if (this.JoyceMon.Cohesion >= 30 && change_2 == false && change_1 == true) {
					gs.change = 2;
					change_2 = true;
					this.JoyceMon.initial();
					/*try {
						gs.initial();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}
			} else {
				if (this.HaoMon.Cohesion >= 10 && this.HaoMon.Cohesion < 25 && change_1 == false) {
					//System.out.println("TTTTT");
					gs.change = 1;
					change_1 = true;
					this.HaoMon.initial();
					/*try {
						gs.initial();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				} else if (this.HaoMon.Cohesion >= 25 && change_2 == false && change_1 == true) {
					gs.change = 2;
					change_2 = true;
					this.HaoMon.initial();
					/*try {
						gs.initial();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}
			}
		}
	}
	// }
}
