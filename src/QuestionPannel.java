import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.JTextArea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

import java.io.*;

public class QuestionPannel extends JPanel implements Runnable, ActionListener{
	//private int
	private JTextField textField;
	private int useable = 0;
	
	private JButton A;
	private JButton B;
	private JButton C;
	private JButton D;
	
	private boolean running = true;
	private JTextArea textArea;
	private JLabel qes;
	private Scanner scanner;
	
	private Qpannelbk bk;
	private GameStart GS;
	
	private GameStage gs;
	public static HashMap quest_collect = new HashMap();
	public String [][]ans_collect = new String[7][4];
	public int index = 1;
	
	public QuestionPannel(GameStart gs){
		this.GS = gs;
		//this.gs = f;
		setLayout(null);
		this.qes = new JLabel();
		this.qes.setBounds(28, 90, 450, 30);
		this.qes.setFont(new Font("�L�n������", Font.BOLD, 25));
		this.add(qes);
		
		this.bk = new Qpannelbk();
		
		try(BufferedReader br = new BufferedReader(new FileReader("QuestionNumber.txt")))
		{
			String line;
			while((line = br.readLine()) != null){
				index = Integer.parseInt(line);
			}
		}catch(Exception e){
			
		}
		
		/*this.textField = new JTextField();
		this.textField.setBounds(30, 20, 200, 100);
	    //this.textField.setPreferredSize(new Dimension(40,40));
	    this.textField.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				if(useable == 1){
					System.out.println("AAAAAAAA");
					if(textField.getText().equals("end")){
						System.out.println("BBBBBBB");
						useable = 3;
						running = false;
						textField.setText("");
					}
				}
			}
	    	
	    });
	    this.add(this.textField);*/
	    quest_collect.put(1, "�֮ժ��ݪk");
		quest_collect.put(2, "������z��");
		quest_collect.put(3, "�Ϲ諸�z��");
		quest_collect.put(4, "�O�_ı�o�M�j���W�ե���");
		quest_collect.put(5, "���M�j�հȷ|ĳ�X�ֵ��G���ݪk");
		quest_collect.put(6, "�O�_ı�o�֮չL�{�L��ܫP");
		quest_collect.put(7, "�O�_���N�ǥͷ|���B�z�A��");
		
		ans_collect[0][0] = new String("���");
		ans_collect[0][1] = new String("�Ϲ�");
		ans_collect[0][2] = new String("�L�N��");
		ans_collect[0][3] = new String("�ڨӶê�");
		
		ans_collect[1][0] = new String("�ܦ���X���j��");
		ans_collect[1][1] = new String("�i�Ϊ��귽�ܦh");
		ans_collect[1][2] = new String("�ҵ{��ܩ��ܦh");
		ans_collect[1][3] = new String("�ݮi�P�ʥH�β��ʪ����Y");

		ans_collect[2][0] = new String("�a�U�H�Y");
		ans_collect[2][1] = new String("�i�Ϊ��귽�ܤ�");
		ans_collect[2][2] = new String("�ҷ~���O�ܤj");
		ans_collect[2][3] = new String("���~�ҮѪ����D");

		ans_collect[3][0] = new String("�۷�{�P");
		ans_collect[3][1] = new String("�{�P");
		ans_collect[3][2] = new String("�L�N��");
		ans_collect[3][3] = new String("���ڤ���Ʊ�");
		
		ans_collect[4][0] = new String("���");
		ans_collect[4][1] = new String("�Ϲ�");
		ans_collect[4][2] = new String("�h�A���½c�@�~");
		ans_collect[4][3] = new String("�L�N��");
		
		ans_collect[5][0] = new String("�۷�{�P");
		ans_collect[5][1] = new String("�Ϲ�");
		ans_collect[5][2] = new String("�L�N��");
		ans_collect[5][3] = new String("�O����ܫP������");
		
		ans_collect[6][0] = new String("���N");
		ans_collect[6][1] = new String("�����N");
		ans_collect[6][2] = new String("�L�N��");
		ans_collect[6][3] = new String("�ڨӶê�");
		
		qes.setText((String)quest_collect.get(index));
		
	    this.A = new JButton();
	    this.B = new JButton();
	    this.C = new JButton();
	    this.D = new JButton();
	    this.A.setBounds(40, 570, 150, 50);
	    this.B.setBounds(260, 570, 150, 50);
	    this.C.setBounds(40, 630, 150, 50);
	    this.D.setBounds(260, 630, 150, 50);
	    A.setText(ans_collect[index-1][0]);
	    A.addActionListener(this);
	    B.setText(ans_collect[index-1][1]);
	    B.addActionListener(this);
	    C.setText(ans_collect[index-1][2]);
	    C.addActionListener(this);
	    D.setText(ans_collect[index-1][3]);
	    D.addActionListener(this);
	    this.add(A);
	    this.add(B);
	    this.add(C);
	    this.add(D);
	    
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(bk.getImage(), bk.getX(), bk.getY(), 450, 750, null);
		
		if(GS.Flag == 0){
			g.drawImage(GS.JoyceMon.getImage(), 30, 300, 300,  300, null);
		}else{
			g.drawImage(GS.HaoMon.getImage(), 30, 230,  300, 300, null);
		}
	}
	
	public void actionPerformed(ActionEvent e) { 
		System.out.println("in");

		if(useable == 1){
			if(e.getSource() == A){
				System.out.println("A");
				try{
					File f = new File("QuestionAns.txt");
					FileOutputStream fout = new FileOutputStream(f, true);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
					bw.flush();
					String str = ans_collect[index-1][0];
					bw.write(str);
					bw.newLine();
					bw.flush();
					bw.close();
			    }catch(Exception a){}
				useable = 4;
				//running = false;
			}else if(e.getSource() == B){
				System.out.println("B");
				try{
					File f = new File("QuestionAns.txt");
					FileOutputStream fout = new FileOutputStream(f, true);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
					bw.flush();
					String str = ans_collect[index-1][1];
					bw.write(str);
					bw.newLine();
					bw.flush();
					bw.close();
			    }catch(Exception a){}
				useable = 4;
				//running = false;
			}else if(e.getSource() == C){
				System.out.println("C");
				try{
					File f = new File("QuestionAns.txt");
					FileOutputStream fout = new FileOutputStream(f, true);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
					bw.flush();
					String str = ans_collect[index-1][2];
					bw.write(str);
					bw.newLine();
					bw.flush();
					bw.close();
			    }catch(Exception a){}
				useable = 4;
				//running = false;
			}else if(e.getSource() ==D){
				System.out.println("D");
				try{
					File f = new File("QuestionAns.txt");
					FileOutputStream fout = new FileOutputStream(f, true);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
					bw.flush();
					String str = ans_collect[index-1][3];
					bw.write(str);
					bw.newLine();
					bw.flush();
					bw.close();
			    }catch(Exception a){}
				useable = 4;
				//running = false;
			}
		}
		
	}
	
	public void setUse(int t){
		this.useable = t;
	}
	
	public int getUse(){
		return this.useable;
	}
	
	public void doSomeWork(){
		if(useable == 4){
			this.index++;
			if(index > 7 ){
				index = 1;
			}
			A.setText(ans_collect[index-1][0]);
		    B.setText(ans_collect[index-1][1]);
		    C.setText(ans_collect[index-1][2]);
		    D.setText(ans_collect[index-1][3]);
		    //doSomeWork();
		    
		    try{
		    	FileWriter fw = new FileWriter("QuestionNumber.txt");
				BufferedWriter bufw = new BufferedWriter(fw);
				String str = String.valueOf(index);
				bufw.write(str);
				bufw.newLine();
				bufw.flush();
				bufw.close();
		    }catch(Exception e){}
		    
		    running = false;
		    useable = 3;
		}
		this.repaint();
	}
	
	public void run(){
		System.out.println(index);
		while(running){
			try{
				Thread.sleep(15);
				SwingUtilities.invokeLater(new Runnable(){public void run(){doSomeWork();}});
			}catch(InterruptedException e){}
		}
	}
	
}
