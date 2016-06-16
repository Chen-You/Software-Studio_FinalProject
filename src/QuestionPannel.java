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
		this.qes.setFont(new Font("微軟正黑體", Font.BOLD, 25));
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
	    quest_collect.put(1, "併校的看法");
		quest_collect.put(2, "支持的理由");
		quest_collect.put(3, "反對的理由");
		quest_collect.put(4, "是否覺得清大有名校光環");
		quest_collect.put(5, "對於清大校務會議合併結果的看法");
		quest_collect.put(6, "是否覺得併校過程過於倉促");
		quest_collect.put(7, "是否滿意學生會的處理態度");
		
		ans_collect[0][0] = new String("支持");
		ans_collect[0][1] = new String("反對");
		ans_collect[0][2] = new String("無意見");
		ans_collect[0][3] = new String("我來亂的");
		
		ans_collect[1][0] = new String("變成綜合型大學");
		ans_collect[1][1] = new String("可用的資源變多");
		ans_collect[1][2] = new String("課程選擇性變多");
		ans_collect[1][3] = new String("拓展同性以及異性的關係");

		ans_collect[2][0] = new String("地狹人稠");
		ans_collect[2][1] = new String("可用的資源變少");
		ans_collect[2][2] = new String("課業壓力變大");
		ans_collect[2][3] = new String("畢業證書的問題");

		ans_collect[3][0] = new String("相當認同");
		ans_collect[3][1] = new String("認同");
		ans_collect[3][2] = new String("無意見");
		ans_collect[3][3] = new String("關我什麼事情");
		
		ans_collect[4][0] = new String("支持");
		ans_collect[4][1] = new String("反對");
		ans_collect[4][2] = new String("去你的黑箱作業");
		ans_collect[4][3] = new String("無意見");
		
		ans_collect[5][0] = new String("相當認同");
		ans_collect[5][1] = new String("反對");
		ans_collect[5][2] = new String("無意見");
		ans_collect[5][3] = new String("是那麼倉促做什麼");
		
		ans_collect[6][0] = new String("滿意");
		ans_collect[6][1] = new String("不滿意");
		ans_collect[6][2] = new String("無意見");
		ans_collect[6][3] = new String("我來亂的");
		
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
