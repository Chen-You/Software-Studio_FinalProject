import java.applet.Applet;

import java.util.Random;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLayeredPane;
import java.applet.Applet;

import java.applet.AudioClip;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.URL;
import java.rmi.UnknownHostException;

import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameStage extends JFrame implements KeyListener {
	// stage=0 stage=1 start/continue stage=2 developer
	public int stage = 0;
	private int end = 0;
	private boolean De_In = false;
	private Menu menu;
	private DeveloperPage de;
	private OpenScreen Opening;
	private Thread thread;

	private boolean choseGame;
	private int waitAns = 0;

	private GameStart GameStart;
	private ShootZombie ShootZombie;
	private GameStageDownStair downStair;
	private OverAnime OverAnime;
	private QuestionPannel Qpannel;
	private Thread t_GameStart;
	private Thread t_ShootZombie;
	private Thread t_downStair;
	private Thread t_OverAnime;
	private int GameState = 1;
	public int change = 0;

	public boolean Open_or_not = false;
	public int WhichMon;
	public int save = 0;
	public int Cohesion = 0;
	private Joyce_change1 Jch1;
	private Joyce_change2 Jch2;
	private Hao_change1 Hch1;
	private Hao_change2 Hch2;
	public boolean Jch1_happen = false;
	public boolean Jch2_happen = false;
	public boolean Hch1_happen = false;
	public boolean Hch2_happen = false;

	// private JLayeredPane lpane = new JLayeredPane();

	private Random ran = new Random();

	// server
	private String destinationIPAddr;
	private int destinationPortNum;
	private Socket socket;

	private static PrintWriter writer;
	private BufferedReader reader;
	private int gameState;
	private String serverString;
	private ClientGameStage gs;
	private Thread t_ClientGame;
	public boolean isHurt = false;

	private boolean isFin = false;

	private int enemyATK;
	
	public int test = 1;

	public GameStage() throws IOException {

		setLayout(null);
		setSize(450, 750);
		setLocation(100, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GameStart = new GameStart(this);
		GameStart.setBounds(0, 0, 450, 750);
		GameStart.setVisible(false);
		add(GameStart);

		gs = new ClientGameStage(this);
		gs.setBounds(0, 0, 450, 750);
		this.add(gs);
		gs.setVisible(false);

		// add(lpane, BorderLayout.CENTER);

		this.Qpannel = new QuestionPannel(this.GameStart);
		this.Qpannel.setBounds(0, 0, 450, 750);
		this.Qpannel.setVisible(false);
		this.add(Qpannel);
		// lpane.add(Qpannel, 0);

		ShootZombie = new ShootZombie();
		ShootZombie.setBounds(0, 0, 450, 750);
		ShootZombie.setVisible(false);
		this.add(ShootZombie);
		// lpane.add(ShootZombie, 1);

		downStair = new GameStageDownStair();
		downStair.setBounds(0, 0, 450, 750);
		downStair.setVisible(false);
		this.add(downStair);
		// lpane.add(downStair, 2);

		OverAnime = new OverAnime();
		OverAnime.setBounds(0, 0, 450, 750);
		OverAnime.setVisible(false);
		this.add(OverAnime);
		addKeyListener(this);
		setFocusable(true);

		this.setVisible(true);
		// lpane.setVisible(true);

		initial();

	}

	public void initial() throws IOException {
		// System.out.println("GS");
		if (stage == 0) {
			if (this.De_In == true) {
				de.stopThread();
				// thread2.interrupt();
				remove(de);
				setDe(false);
			} else {
			}
			menu = new Menu(this);
			menu.setBounds(0, 0, 450, 750);
			add(menu);
			setVisible(true);
			thread = new Thread(menu);
			thread.start();
		}
		// Start
		else if (stage == 1) {
			menu.stopThread();
			// thread.interrupt();
			remove(menu);
			this.Open_or_not = true;
			Opening = new OpenScreen(this);
			Opening.setBounds(0, 0, 450, 750);
			add(Opening);
			setVisible(true);
			Thread thread1 = new Thread(Opening);
			thread1.start();
			this.stage = 4;
		}
		// Continue
		else if (stage == 2) {
			this.WhichMon = menu.Flag;
			this.save = menu.save;
			this.Cohesion = menu.Cohesion;
			menu.stopThread();
			// thread.interrupt();
			remove(menu);
			this.stage = 5;
			this.initial();
		}
		// Developer
		else if (stage == 3) {
			menu.stopThread();
			// thread.interrupt();
			remove(menu);
			setDe(true);
			de = new DeveloperPage(this);
			de.setBounds(0, 0, 450, 750);
			add(de);
			setVisible(true);
			Thread thread2 = new Thread(de);
			thread2.start();
		} else if (stage == 4) {
			this.WhichMon = Opening.WhichMon;
			this.Opening.stopThread();
			remove(Opening);
			this.stage = 5;
			initial();
		} else if (stage == 5) {
			System.out.println("OK");
			if (this.Jch1_happen == true) {
				this.Jch1_happen = false;
				this.Jch1.stopThread();
				// thread.interrupt();
				remove(Jch1);
				this.change = 0;
				this.GameStart.setVisible(true);
			} else if (this.Jch2_happen == true) {
				this.Jch2_happen = false;
				this.Jch2.stopThread();
				// thread.interrupt();
				remove(Jch2);
				this.change = 0;
				this.GameStart.setVisible(true);
			} else if (this.Hch1_happen == true) {
				this.Hch1_happen = false;
				this.Hch1.stopThread();
				// thread.interrupt();
				remove(Hch1);
				this.change = 0;
				this.GameStart.setVisible(true);
			} else if (this.Hch2_happen == true) {
				this.Hch2_happen = false;
				this.Hch2.stopThread();
				// thread.interrupt();
				remove(Hch2);
				this.change = 0;
				this.GameStart.setVisible(true);
			} else {
				this.GameStart.setFlag(this.WhichMon);
				this.GameStart.setValue();
				this.GameStart.setVisible(true);
				this.GameStart.startCharacterThread();
				t_GameStart = new Thread(GameStart);
				t_GameStart.start();
			}
			this.stage = 6;
		} else if (stage == 6) {
			/*
			 * if(this.waitAns == 1){
			 * 
			 * }else if(this.GameStart.Ask() == true){
			 * this.GameStart.setVisible(false); this.Qpannel.setVisible(true);
			 * Thread t1 = new Thread(Qpannel); t1.start();
			 * this.Qpannel.setUse(1); this.waitAns = 1; }else
			 * if(this.GameStart.Ask() == false){
			 * this.Qpannel.setVisible(false); this.Qpannel.setUse(0); }else
			 * if(this.Qpannel.getUse() == 3){ this.GameStart.setAsk(false);
			 * this.waitAns = 0; }
			 */
			// System.out.println("rerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

			// JoyceMon
			if (this.GameStart.Flag == 0) {
				if (change == 1) {
					// this.stage = 13;
					// System.out.println("Changeee");
					this.GameStart.setVisible(false);
					this.Qpannel.setVisible(false);
					this.Jch1_happen = true;
					this.Jch1 = new Joyce_change1(this, GameStart.JoyceMon);
					Jch1.setBounds(0, 0, 450, 750);
					add(Jch1);
					setVisible(true);
					this.change = 3;
					Thread thread2 = new Thread(Jch1);
					thread2.start();
				} else if (change == 2) {

					this.GameStart.setVisible(false);
					this.Jch2_happen = true;
					this.GameStart.setVisible(false);
					this.Qpannel.setVisible(false);
					this.Jch2 = new Joyce_change2(this, GameStart.JoyceMon);
					Jch2.setBounds(0, 0, 450, 750);
					add(Jch2);
					setVisible(true);
					this.change = 3;
					Thread thread2 = new Thread(Jch2);
					thread2.start();
				} else if (change == 3) {

				} else if (this.GameStart.Ask() == true) {
					this.GameStart.setVisible(false);
					this.Qpannel = new QuestionPannel(this.GameStart);
					this.Qpannel.setBounds(0, 0, 450, 750);
					this.Qpannel.setVisible(true);
					this.add(Qpannel);
					this.stage = 11;
				}
			} else {
				if (change == 1) {
					// System.out.println("Changeee");
					this.GameStart.setVisible(false);
					this.Hch1_happen = true;
					this.GameStart.setVisible(false);
					this.Qpannel.setVisible(false);
					this.Hch1 = new Hao_change1(this, GameStart.HaoMon);
					Hch1.setBounds(0, 0, 450, 750);
					add(Hch1);
					setVisible(true);
					this.change = 3;
					Thread thread2 = new Thread(Hch1);
					thread2.start();
				} else if (change == 2) {
					this.GameStart.setVisible(false);
					this.Hch2_happen = true;
					this.GameStart.setVisible(false);
					this.Qpannel.setVisible(false);
					this.Hch2 = new Hao_change2(this, GameStart.HaoMon);
					Hch2.setBounds(0, 0, 450, 750);
					add(Hch2);
					setVisible(true);
					this.change = 3;
					Thread thread2 = new Thread(Hch2);
					thread2.start();
				} else if (change == 3) {

				} else if (this.GameStart.Ask() == true) {
					this.GameStart.setVisible(false);
					this.Qpannel = new QuestionPannel(this.GameStart);
					this.Qpannel.setBounds(0, 0, 450, 750);
					this.Qpannel.setVisible(true);
					this.add(Qpannel);
					this.stage = 11;
				}
			}

			/*
			 * if (this.GameStart.Ask() == true) { this.Qpannel = new
			 * QuestionPannel(); this.Qpannel.setBounds(0, 0, 450, 750);
			 * this.Qpannel.setVisible(true); this.add(Qpannel); this.stage =
			 * 11; } else
			 */
			if (this.GameStart.getStatus() == 3)
				this.stage = 7;
			else if (this.GameStart.getStatus() == 4) {
				this.stage = 12;
			}
		} else if (stage == 7) {
			this.GameStart.setVisible(false);
			this.ShootZombie.setVisible(false);
			this.OverAnime.setVisible(true);
			this.OverAnime.init();
			t_OverAnime = new Thread(OverAnime);
			t_OverAnime.start();
			this.stage = 8;
		} else if (stage == 8) {
			if (this.OverAnime.getend() == 1) {
				this.stage = 9;
				this.OverAnime.setVisible(false);
			}
		} else if (stage == 9) {
			this.GameStart.setVisible(false);
			this.gs.setVisible(false);
			if (this.test % 2 == 1) {
				choseGame = false;
				this.ShootZombie.setVisible(true);
				
				t_ShootZombie = new Thread(ShootZombie);
				t_ShootZombie.start();
			} else {
				choseGame = true;
				this.downStair.setVisible(true);
				
				t_downStair = new Thread(downStair);
				t_downStair.start();
			}
			this.stage = 10;
		} else if (stage == 10) {
			if (this.ShootZombie.getGG() == 1 || this.downStair.getGG() == true ) {
				// System.out.println("boooooooo");
				this.test++;
				this.stage = 6;
				this.ShootZombie.init();
				this.downStair.initial();
				this.GameStart.setStatus(1);
				this.GameStart.setVisible(true);
				this.ShootZombie.setVisible(false);
				this.downStair.setVisible(false);
				this.gs.setVisible(false);
				// this.ShootZombie.setVisible(false);
			}
		} else if (stage == 11) {
			// System.out.println("wait");
			if (this.GameStart.Ask() == false) {
				this.Qpannel.setVisible(false);
				this.Qpannel.setUse(0);
			} else if (this.Qpannel.getUse() == 3) {
				System.out.println("wait");
				this.GameStart.setVisible(true);
				this.GameStart.setAsk(false);
				this.waitAns = 0;
				this.stage = 6;
				this.Qpannel.setUse(0);
				remove(Qpannel);
			} else if (this.waitAns == 1) {

			} else if (this.GameStart.Ask() == true) {
				this.GameStart.setVisible(false);
				this.Qpannel.setVisible(true);
				Thread t1 = new Thread(Qpannel);
				t1.start();
				this.Qpannel.setUse(1);
				this.waitAns = 1;
			}
		} else if (stage == 12) {
			this.GameStart.setVisible(false);
			this.downStair.setVisible(false);
			this.ShootZombie.setVisible(false);
			this.OverAnime.setVisible(false);
			this.isFin = false;
			this.setIPAddress("127.0.0.1").setPort(8000).connect();
			this.gs.setVisible(true);
			this.gs.initial();
			t_ClientGame = new Thread(gs);
			t_ClientGame.start();
			this.stage = 13;
		} else if(stage == 13){
			if(this.isFin == true){
				this.GameStart.setStatus(1);
				this.GameStart.setVisible(true);
				this.ShootZombie.setVisible(false);
				this.downStair.setVisible(false);
				this.gs.setVisible(false);
				this.stage = 6;
			}
		}
	}

	public void setstage(int n) {
		this.stage = n;
	}

	public void setDe(boolean b) {
		this.De_In = b;
	}

	public int getEnd() {
		return this.end;
	}

	/*
	 * public void doSomeWork(){ System.out.println("OK");
	 * 
	 * while(true){ if(this.GameState == 1){ GameStart = new GameStart();
	 * GameStart.setBounds(0, 0, 450, 750); add(GameStart);
	 * this.GameStart.setVisible(true); t_GameStart = new Thread(GameStart);
	 * t_GameStart.start(); //this.ShootZombie.setVisible(false);
	 * 
	 * this.GameState = 3; }else if(this.GameState == 3){
	 * if(this.GameStart.getStatus() == 3) this.GameState = 5;
	 * /*this.GameStart.setStatus(0); this.GameStart.setVisible(false);
	 * ShootZombie.setVisible(true); }else if(this.GameState == 2){
	 * this.GameStart.setVisible(false); this.ShootZombie.setVisible(true);
	 * this.ShootZombie.init(); t_ShootZombie = new Thread(ShootZombie);
	 * t_ShootZombie.start();
	 * 
	 * 
	 * 
	 * this.GameState = 4; }else if(this.GameState == 4){
	 * System.out.println("aaa"); if(this.ShootZombie.getGG() == 1){
	 * System.out.println("boooooooo"); this.GameState = 3;
	 * this.GameStart.setStatus(1); this.GameStart.setVisible(true);
	 * //this.ShootZombie.setVisible(false); } }else if(this.GameState == 5){
	 * this.GameStart.setVisible(false); this.ShootZombie.setVisible(false);
	 * this.OverAnime.setVisible(true); this.OverAnime.init(); t_OverAnime = new
	 * Thread(OverAnime); t_OverAnime.start(); this.GameState = 6; }else if
	 * (this.GameState == 6){ if(this.OverAnime.getend() == 1){ this.GameState =
	 * 2; this.OverAnime.setVisible(false); } }
	 * 
	 * 
	 * 
	 * this.repaint(); } //this.repaint(); }
	 */

	public void keyReleased(KeyEvent ke) {
		// moveAll();
		if (choseGame == false) {
			System.out
					.println(this.ShootZombie.characters[0].getPosX() + " " + this.ShootZombie.characters[0].getPosY());
			if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
				System.out.println("ssss");
				if (this.ShootZombie.checkHit(0) == true) {
					this.ShootZombie.score++;
					System.out.println("000000");
					this.ShootZombie.moveAll();
				}
				ShootZombie.hitters[0].setIsPressed(false);
			}

			if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
				if (this.ShootZombie.checkHit(1) == true) {
					this.ShootZombie.score++;
					System.out.println("11111");
					this.ShootZombie.moveAll();
				}
				ShootZombie.hitters[1].setIsPressed(false);
			}

			if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (this.ShootZombie.checkHit(2) == true) {
					this.ShootZombie.score++;
					System.out.println("22222");
					this.ShootZombie.moveAll();
				}
				ShootZombie.hitters[2].setIsPressed(false);
			}
		}

	}

	public void keyTyped(KeyEvent ke) {
	}

	public void keyPressed(KeyEvent ke) {
		if (choseGame == false) {
			if (ke.getKeyCode() == KeyEvent.VK_LEFT) {

				ShootZombie.hitters[0].setIsPressed(true);
			}

			if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
				ShootZombie.hitters[1].setIsPressed(true);
			}

			if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
				ShootZombie.hitters[2].setIsPressed(true);
			}
		}
		else {
			if (ke.getKeyCode() == KeyEvent.VK_LEFT) {

				downStair.ch.setDir(false);
			}

			if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
				downStair.ch.setDir(true);
			}
		}
	}

	public GameStart getGameStart() {
		return this.GameStart;
	}

	public GameStage setIPAddress(String IPAddress) {
		this.destinationIPAddr = IPAddress;
		return this;
	}

	public GameStage setPort(int portNum) {
		this.destinationPortNum = portNum;
		return this;
	}

	public static void sendMessage(String message) {
		writer.println(message);
		writer.flush();
	}

	public void setGameState(int n) {
		this.gameState = n;
	}

	public int getGameState() {
		return this.gameState;
	}

	public void setServerString(String s) {
		serverString = s;
	}

	public String getServerString() {
		return this.serverString;
	}

	class ClientThread extends Thread {
		private BufferedReader in_reader;

		// temp
		// private boolean isFin = false;

		public ClientThread(BufferedReader reader) {
			this.in_reader = reader;
		}

		public void run() {
			while (isFin == false) {
				try {
					String line = this.in_reader.readLine();
					System.out.println(line);
					if (line.length() == 3) {
						if (line.equals("WAI")) {
							gameState = 0;
						}
						if (line.equals("STA")) {
							gameState = 1;
							sendMessage("REA" + GameStart.getMon().getDamage());
						}
						if (line.equals("COR")) {
							sendMessage("PLA");
							gameState = 1;
						}
						if (line.equals("WRO")) {

						}

						if (line.equals("INJ")) {
							GameStart.getMon().setNowHp(GameStart.getMon().getNowHp() - enemyATK);
							isHurt = true;
							if (GameStart.getMon().getNowHp() <= 0) {
								sendMessage("DIE");
							} else {
								sendMessage("PLA");
								gameState = 1;
							}
						}
						if (line.equals("WIN")) {
							isFin = true;
							isHurt = false;
							socket.close();
						}
						if (line.equals("LOS")) {
							isFin = true;
							isHurt = false;
							socket.close();
						}

					} else {
						if (line.substring(0, 3).equals("ATK")) {
							enemyATK = Integer.parseInt(line.substring(3, 6));
							System.out.println(enemyATK);
							sendMessage("PLA");
							gameState = 1;
						}
						if (line.substring(0, 3).equals("QUE")) {
							// isHurt = false;
							gameState = 2;
							if (line.length() == 5) {
								serverString = line.substring(3, 5);
							} else if (line.length() == 4) {
								serverString = line.substring(3, 4);
							} else {
								serverString = null;
							}
						}

					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void connect() {
		try {
			this.socket = new Socket(this.destinationIPAddr, this.destinationPortNum);
			this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ClientThread ob = new ClientThread(reader);
		ob.start();
	}
}
