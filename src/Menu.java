import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JPanel implements MouseListener, Runnable {
	private static final long serialVersionUID = 1L;
	public int save = 0; 
	public int Flag = 0;
	public int Cohesion = 0;
	public String str[][];
	private Scanner scanner;
	private GameStage gs;
	private boolean running = true;
	BufferedImage bi;
	private StartBtn Start;
	private ContinueBtn Continue;
	private DeveloperBtn Developer;

	public Menu(GameStage f) throws IOException {
		this.gs = f;
		setLayout(null);
		initialAudio();
		initButton();
		initialIma();
		Initialtxt();
		addMouseListener(this);
	}

	private void initialAudio() throws MalformedURLException {
		// TODO Auto-generated method stub
		URL cb;
		AudioClip aau;
		File f = new File("Ima/final_open.wav");
		cb = f.toURI().toURL();
		aau = Applet.newAudioClip(cb);
		aau.play();
		System.out.println("Music");

	}

	private void initialIma() {
		File f1 = new File("Ima/menu.png");
		try {
			bi = ImageIO.read(f1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void Initialtxt() throws IOException {
		str = new String[6][2];
		try {
			File file = new File("Ima/save.txt");
			scanner = new Scanner(file);
			int num = 0;

			while (scanner.hasNext()) {
				str[num] = scanner.next().split(":"); 
				num++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
		if (Integer.parseInt(str[0][1]) == 0) {
			save = 0;
		} else {
			save = 1;
		}
		
		if (Integer.parseInt(str[1][1]) == 0) {
			Flag = 0;
		} else {
			Flag = 1;
		}
		this.Cohesion = Integer.parseInt(str[4][1]);
	}

	private void initButton() {
		Start = new StartBtn();
		Continue = new ContinueBtn();
		Developer = new DeveloperBtn();

		add(Start);
		add(Continue);
		add(Developer);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bi, 0, 0, 450, 750, null);
		if (Start.press == true) {
			g.drawImage(Start.getImage_true(), 135, 300, 180, 65, null);
		} else {
			g.drawImage(Start.getImage_false(), 135, 300, 180, 65, null);
		}
		if (Continue.press == true) {
			g.drawImage(Continue.getImage_true(), 135, 390, 180, 65, null);
		} else {
			g.drawImage(Continue.getImage_false(), 135, 390, 180, 65, null);
		}
		if (Developer.press == true) {
			g.drawImage(Developer.getImage_true(), 135, 480, 180, 65, null);
		} else {
			g.drawImage(Developer.getImage_false(), 135, 480, 180, 65, null);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// Start
		if (e.getX() > 135 && e.getX() < 315 && e.getY() > 300 && e.getY() < 365) {
			System.out.println("Start");
			if (save == 1) {
				int dialogButton = 0;
				dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure to restart?", "Confirm", dialogButton);
				if (dialogButton == JOptionPane.YES_OPTION) {
					
					for (int i = 0; i < 6; i++) {
						str[i][1] = Integer.toString(0);
						// System.out.println(str[i][1]);
					}
					save = 0;
					gs.setstage(1);
					try {
						gs.initial();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				gs.setstage(1);
				try {
					gs.initial();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		// Continue
		else if (e.getX() > 135 && e.getX() < 315 && e.getY() > 390 && e.getY() < 455) {
			System.out.println("Continue");
			if (save == 0) {
				int dialogButton = -1;
				dialogButton = JOptionPane.showConfirmDialog(null, "Sorry, you don't have any record.", "Confirm",
						dialogButton);
				if (dialogButton == JOptionPane.CLOSED_OPTION) {
				}
			} else {
				gs.setstage(2);
				try {
					gs.initial();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		// Developer
		else if (e.getX() > 135 && e.getX() < 315 && e.getY() > 480 && e.getY() < 545) {
			System.out.println("Developer");
			gs.setstage(3);
			try {
				gs.initial();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getX() > 135 && e.getX() < 315 && e.getY() > 300 && e.getY() < 365) {
			Start.set_press(true);
		}
		// Continue
		else if (e.getX() > 135 && e.getX() < 315 && e.getY() > 390 && e.getY() < 455) {
			Continue.set_press(true);
		}
		// Developer
		else if (e.getX() > 135 && e.getX() < 315 && e.getY() > 480 && e.getY() < 545) {
			Developer.set_press(true);
		} else {
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getX() > 135 && e.getX() < 315 && e.getY() > 300 && e.getY() < 365) {
			Start.set_press(false);
		}
		// Continue
		else if (e.getX() > 135 && e.getX() < 315 && e.getY() > 390 && e.getY() < 455) {
			Continue.set_press(false);
		}
		// Developer
		else if (e.getX() > 135 && e.getX() < 315 && e.getY() > 480 && e.getY() < 545) {
			Developer.set_press(false);
		} else {
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (running) {
			this.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void stopThread() {
		this.running = false;
	}
}
