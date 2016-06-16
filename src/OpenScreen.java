import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OpenScreen extends JPanel implements MouseListener, Runnable {
	private GameStage gs;
	private boolean running = true;
	private int page = 1;
	private int count = 0;
	Random ran = new Random();
	
	public int WhichMon;
	BufferedImage bi;
	BufferedImage bi2_1;
	BufferedImage bi2_2;
	BufferedImage bi3;
	BufferedImage bi4;
	BufferedImage bi5;
	BufferedImage bi6;
	BufferedImage bi7_1;
	BufferedImage bi7_2;

	public OpenScreen(GameStage f) {
		this.gs = f;
		setLayout(null);
		initialIma();
		addMouseListener(this);
	}

	private void initialIma() {
		// TODO Auto-generated method stub
		File f1 = new File("Ima/Opening1.png");
		try {
			bi = ImageIO.read(f1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f2 = new File("Ima/Opening2_1.png");
		try {
			bi2_1 = ImageIO.read(f2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f3 = new File("Ima/Opening2_2.png");
		try {
			bi2_2 = ImageIO.read(f3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f4 = new File("Ima/Opening3.png");
		try {
			bi3 = ImageIO.read(f4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f5 = new File("Ima/Opening4.png");
		try {
			bi4 = ImageIO.read(f5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f6 = new File("Ima/Opening5.png");
		try {
			bi5 = ImageIO.read(f6);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f7 = new File("Ima/Opening6.png");
		try {
			bi6 = ImageIO.read(f7);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f8 = new File("Ima/Opening7_1.png");
		try {
			bi7_1 = ImageIO.read(f8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f9 = new File("Ima/Opening7_2.png");
		try {
			bi7_2 = ImageIO.read(f9);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getWhichMon(){
		return this.WhichMon;
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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (page == 1)
			g.drawImage(bi, 0, 0, 450, 750, null);
		else if (page == 2) {
			if (count < 10) {
				g.drawImage(bi2_1, 0, 0, 450, 750, null);
				count++;
			} else if (count >= 10 && count <= 20) {
				g.drawImage(bi2_2, 0, 0, 450, 750, null);
				count++;
				if(count == 20) count = 0;
			}
		} else if (page == 3) {
			g.drawImage(bi3, 0, 0, 450, 750, null);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			page++;
		} else if (page == 4) {
			g.drawImage(bi4, 0, 0, 450, 750, null);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			page++;
		} else if (page == 5) {
			g.drawImage(bi5, 0, 0, 450, 750, null);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			page++;
		} else if (page == 6) {
			g.drawImage(bi6, 0, 0, 450, 750, null);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			page++;
		} else if(page == 7){
			WhichMon = ran.nextInt(2);
			if (WhichMon == 0)
				g.drawImage(bi7_1, 0, 0, 450, 750, null);
			else
				g.drawImage(bi7_2, 0, 0, 450, 750, null);
		}
		else{
			if (WhichMon == 0)
				g.drawImage(bi7_1, 0, 0, 450, 750, null);
			else
				g.drawImage(bi7_2, 0, 0, 450, 750, null);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		page++;
		if (page >= 7) {
			System.out.println("Test");
			/*
			 * gs.setstage(0); try { gs.initial(); } catch (IOException e1) { //
			 * TODO Auto-generated catch block e1.printStackTrace(); }
			 */
			if (WhichMon == 0) {
				int dialogButton = -1;
				dialogButton = JOptionPane.showConfirmDialog(null, "Congratuation! You got a JoyceMonBaby!", "Confirm",
						dialogButton);
				if (dialogButton == JOptionPane.OK_OPTION) {
					gs.setstage(4);
					System.out.println("6666666666_1");
					try {
						gs.initial();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} 
			else {
				int dialogButton = -1;
				dialogButton = JOptionPane.showConfirmDialog(null, "Congratuation! You got a Hao-ChuanMonBaby!",
						"Confirm", dialogButton);
				if (dialogButton == JOptionPane.OK_OPTION) {
					gs.setstage(4);
					System.out.println("6666666666_2");
					try {
						gs.initial();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
