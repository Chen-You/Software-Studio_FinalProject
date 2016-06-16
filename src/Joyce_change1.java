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

public class Joyce_change1 extends JPanel implements MouseListener, Runnable {

	private GameStage gs;
	private boolean running = true;
	private int page = 1;
	private int count = 0;
	private int count_2 = 0;
	private JoyceMon JJ;

	BufferedImage bi;
	BufferedImage bi2;
	BufferedImage bi3;

	public Joyce_change1(GameStage f, JoyceMon J) {
		this.gs = f;
		this.JJ = J;
		setLayout(null);
		initialIma();
		addMouseListener(this);
	}

	private void initialIma() {
		// TODO Auto-generated method stub
		File f1 = new File("Ima/change01_1.png");
		try {
			bi = ImageIO.read(f1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f2 = new File("Ima/change01_2.png");
		try {
			bi2 = ImageIO.read(f2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f3 = new File("Ima/change01_3.png");
		try {
			bi3 = ImageIO.read(f3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		if (page == 1) {
			if (count < 10) {
				g.drawImage(bi, 0, 0, 450, 750, null);
				count++;
				//System.out.println(count);
			} else if (count >= 10 && count <= 20) {
				g.drawImage(bi2, 0, 0, 450, 750, null);
				count++;
				if (count == 20)
					count = 0;
			}

		} else if (page == 2) {
			if (count_2 < 3) {
				g.drawImage(bi, 0, 0, 450, 750, null);
				count_2++;
			} else if (count_2 >= 3 && count_2 <= 6) {
				g.drawImage(bi2, 0, 0, 450, 750, null);
				count_2++;
				if (count_2 == 6)
					count_2 = 0;
			}

		} else {
			g.drawImage(bi3, 0, 0, 450, 750, null);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		page++;
		if (page >= 3) {
			int dialogButton = -1;
			dialogButton = JOptionPane.showConfirmDialog(null, "Congratuation! You got a " + JJ.getName(), "Confirm",
					dialogButton);
			if (dialogButton == JOptionPane.OK_OPTION) {
				gs.setstage(5);
				try {
					gs.initial();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
