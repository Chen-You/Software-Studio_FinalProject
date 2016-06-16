import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.*;
public class ClientGameStage extends JPanel implements Runnable,ActionListener{
private static final long serialVersionUID = 1L;
	
	
	private JButton[] btn; 
	private JLabel [] ans;
	private GameStage game;
	private int getStringNum;
	private String ansSelect;
	//HashMap num store index and question
	public static HashMap num = new HashMap();
	//HashMap map store map and ans
	public static HashMap map = new HashMap();
	public int index;
	private static String [][]quest;
	
	private JLabel hpLabel;
	
	public ClientGameStage(GameStage g){
		
		this.setLayout(null);
		this.game = g;
		
		btn = new JButton[4];
		btn[0] = new JButton();
		btn[0].setBounds(30, 590, 50,50);
		add(btn[0]);
		btn[0].addActionListener(this);
		btn[0].setText("A");
		btn[1] = new JButton();
		btn[1].setBounds(270, 590, 50, 50);
		add(btn[1]);
		btn[1].addActionListener(this);
		btn[1].setText("B");
		btn[2] = new JButton();
		btn[2].setBounds(30, 670, 50, 50);
		add(btn[2]);
		btn[2].addActionListener(this);
		btn[2].setText("C");
		btn[3] = new JButton();
		btn[3].setBounds(270, 670, 50, 50);
		add(btn[3]);
		btn[3].addActionListener(this);
		btn[3].setText("D");
		
		ans = new JLabel[5];
		ans[0] = new JLabel();
		ans[0].setBounds(30, 450, 400, 60);
		ans[1] = new JLabel();
		ans[1].setBounds(90, 590, 150, 60);
		ans[2] = new JLabel();
		ans[2].setBounds(330, 590, 150, 60);
		ans[3] = new JLabel();
		ans[3].setBounds(90, 670, 150, 60);
		ans[4] = new JLabel();
		ans[4].setBounds(330, 670, 150, 60);
		
		add(ans[0]);
		add(ans[1]);
		add(ans[2]);
		add(ans[3]);
		add(ans[4]);
		
		num.put(0, "�H�U���@��D�ƽX�_�����Ӯa�M�h�Φ���?");
		num.put(1, "�аݰʺ����C�����򤤡A���ѱs�C�������q�O?");
		num.put(2, "�аݽu�W�C���P�Ҷǻ����A��Ȫ��T��W�٬�?");
		num.put(3, "�аݡA�C��<������>���A�t�d�k�v�@�઺��¾�Щx�W�٬O?");
		num.put(4, "�аݤ@���窺�֪��@���窺�K���A���Ӥ����?");
		num.put(5, "�C�s�]���A�����ܨ����W���ɨȤH�ĤG�����H�O?");
		num.put(6, "�аݤ��ؤ@�f�ʵe���A�֨S���b���}���Ʋz���ɤ���ӹL?");
		num.put(7, "�аݫ��Q�i�S�̡A�n�l�ꪫ�~���G�y�O�H");
		num.put(8, "�аݰʺ������Ҫ��N�v���A����H�y�H�N��ۦ⼤?");
		num.put(9, "�аݶ��L�Ǵ��Q�G�D����������R�A����ˬ�?");
		num.put(10, "�аݮ����_�_�̭��A�������|�j������־��H");
		num.put(11, "�аݯ�������A���G�l�Y�F����G��H");
		num.put(12, "�аݩ�h�~�����s�������e���v�Ԫ̳̫�s���s���F�X��?");
		num.put(13, "�U�C���@���^���S���b�|�~�e�^���p��S2���@�ɫa�x��Game4���X��?");
		num.put(14, "�аݶǻ������M�j�n�s���ƥ�o�ͦb�M�j���@�N?");
		num.put(15, "�аݥH�U���ӦѮv�̫�?");
		
		map.put("�H�U���@��D�ƽX�_�����Ӯa�M�h�Φ���?", "�Ҥ��s���~");
		map.put("�аݰʺ����C�����򤤡A���ѱs�C�������q�O?", "�ѵ��s�{");
		map.put("�аݽu�W�C���P�Ҷǻ����A��Ȫ��T��W�٬�?", "�Q�r�٭���");
		map.put("�аݡA�C��<������>���A�t�d�k�v�@�઺��¾�Щx�W�٬O?", "�~��");
		map.put("�аݤ@���窺�֪��@���窺�K���A���Ӥ����?", "�@�˭�");
		map.put("�C�s�]���A�����ܨ����W���ɨȤH�ĤG�����H�O?", "����");
		map.put("�аݤ��ؤ@�f�ʵe���A�֨S���b���}���Ʋz���ɤ���ӹL?", "�p��");
		map.put("�аݫ��Q�i�S�̡A�n�l�ꪫ�~���G�y�O�H", "�t�t�e");
		map.put("�аݰʺ������Ҫ��N�v���A����H�y�H�N��ۦ⼤?", "�Դ��h");
		map.put("�аݶ��L�Ǵ��Q�G�D����������R�A����ˬ�?", "�z��");
		map.put("�аݮ����_�_�̭��A�������|�j������־��H", "��®��");
		map.put("�аݯ�������A���G�l�Y�F����G��H", "�_�_�G��");
		map.put("�аݩ�h�~�����s�������e���v�Ԫ̳̫�s���s���F�X��?", "700");
		map.put("�U�C���@���^���S���b�|�~�e�^���p��S2���@�ɫa�x��Game4���X��?", "�`�w�R");
		map.put("�аݶǻ������M�j�n�s���ƥ�o�ͦb�M�j���@�N?", "�M�N");
		map.put("�аݥH�U���ӦѮv�̫�?", "�H�W�ҬO");
		
		quest = new String[16][4];
		for(int i = 0; i < 16; ++i){
	    	int j = 0; 
	    	if(i == 0){
	    		quest[i][j] = new String("���̥[�~");
	    		++j;
	    		quest[i][j] = new String("�����k�~");
	    		++j;
	    		quest[i][j] = new String("�Ҥ��s���~");
	    		++j;
	    		quest[i][j] = new String("�����M�h�~");
	    	}
	    	else if(i == 1){
	    		quest[i][j] = new String("�ѵ��s�{");
	    		++j;
	    		quest[i][j] = new String("�s����");
	    		++j;
	    		quest[i][j] = new String("�\�ȯ�q��");
	    		++j;
	    		quest[i][j] = new String("�E�Y�s�{");
	    	}
	    	else if(i == 2){
	    		quest[i][j] = new String("�Q�r���");
	    		++j;
	    		quest[i][j] = new String("�y�v�l�ܪ�");
	    		++j;
	    		quest[i][j] = new String("�Q�r�٭���");
	    		++j;
	    		quest[i][j] = new String("����ӫO");
	    	}
	    	else if(i == 3){
	    		quest[i][j] = new String("���E��");
	    		++j;
	    		quest[i][j] = new String("�~��");
	    		++j;
	    		quest[i][j] = new String("�F�J�|");
	    		++j;
	    		quest[i][j] = new String("�ھ|��");
	    	}
	    	else if(i == 4){
	    		quest[i][j] = new String("�֪�");
	    		++j;
	    		quest[i][j] = new String("�K��");
	    		++j;
	    		quest[i][j] = new String("�@�˭�");
	    		++j;
	    		quest[i][j] = new String("�����a��");
	    	}
	    	else if(i == 5){
	    		quest[i][j] = new String("����");
	    		++j;
	    		quest[i][j] = new String("����");
	    		++j;
	    		quest[i][j] = new String("�F��");
	    		++j;
	    		quest[i][j] = new String("����");
	    	}
	    	else if(i == 6){
	    		quest[i][j] = new String("���ҸѮv��");
	    		++j;
	    		quest[i][j] = new String("��Q");
	    		++j;
	    		quest[i][j] = new String("�p��");
	    		++j;
	    		quest[i][j] = new String("�s�Ӯ�");
	    	}
	    	else if(i == 7){
	    		quest[i][j] = new String("�G�G�h");
	    		++j;
	    		quest[i][j] = new String("���N��");
	    		++j;
	    		quest[i][j] = new String("�n����");
	    		++j;
	    		quest[i][j] = new String("�t�t�e");
	    	}
	    	else if(i == 8){
	    		quest[i][j] = new String("�w�Ԧh��");
	    		++j;
	    		quest[i][j] = new String("�Դ�");
	    		++j;
	    		quest[i][j] = new String("���ܼw");
	    		++j;
	    		quest[i][j] = new String("�Դ��h");
	    	}
	    	else if(i == 9){
	    		quest[i][j] = new String("���紵");
	    		++j;
	    		quest[i][j] = new String("�i�ɹy");
	    		++j;
	    		quest[i][j] = new String("��ù�̭״�");
	    		++j;
	    		quest[i][j] = new String("�z��");
	    	}
	    	else if(i == 10){
	    		quest[i][j] = new String("��z");
	    		++j;
	    		quest[i][j] = new String("�ħJ����");
	    		++j;
	    		quest[i][j] = new String("��®��");
	    		++j;
	    		quest[i][j] = new String("�N�L");
	    	}
	    	else if(i == 11){
	    		quest[i][j] = new String("���_�G��");
	    		++j;
	    		quest[i][j] = new String("�_�_�G��");
	    		++j;
	    		quest[i][j] = new String("�ɾ_�G��");
	    		++j;
	    		quest[i][j] = new String("���_�G��");
	    	}
	    	else if(i == 12){
	    		quest[i][j] = new String("600");
	    		++j;
	    		quest[i][j] = new String("700");
	    		++j;
	    		quest[i][j] = new String("650");
	    		++j;
	    		quest[i][j] = new String("750");
	    	}
	    	else if(i == 13){
	    		quest[i][j] = new String("�d���洵");
	    		++j;
	    		quest[i][j] = new String("�ƥˮR");
	    		++j;
	    		quest[i][j] = new String("�`�w�R");
	    		++j;
	    		quest[i][j] = new String("�����w�R");
	    	}
	    	else if(i == 14){
	    		quest[i][j] = new String("�s�N");
	    		++j;
	    		quest[i][j] = new String("�M�N");
	    		++j;
	    		quest[i][j] = new String("���N");
	    		++j;
	    		quest[i][j] = new String("�q�N");
	    	}
	    	else if(i == 15){
	    		quest[i][j] = new String("���E��");
	    		++j;
	    		quest[i][j] = new String("���E��");
	    		++j;
	    		quest[i][j] = new String("���E��");
	    		++j;
	    		quest[i][j] = new String("�H�W�ҬO");
	    	}
		}
		
		hpLabel = new JLabel("HP: "+ game.getGameStart().getMon().getNowHp());
		hpLabel.setBounds(30, 20, 400, 60);
		hpLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		add(hpLabel);
	}
	
	public void initial(){
			game.getGameStart().getMon().setNowHp(game.getGameStart().getMon().getMAXHp());
			for(int i=0;i<5;i++){
				ans[i].setText("");
			}
	}
	
	public void actionPerformed(ActionEvent e) { 
		if(game.getGameState()==2){
			if(e.getSource()==btn[0]){
				setAns(0);
			}
			if(e.getSource()==btn[1]){
				setAns(1);
			}
			if(e.getSource()==btn[2]){
				setAns(2);
			}
			if(e.getSource()==btn[3]){
				setAns(3);
			}
			game.isHurt = false;
			game.sendMessage("ANS"+this.getAns());
		}
	}
	
	public void run(){
		while(true){
			if(game.getGameState()==2&&game.getServerString()!=null){
				int index = Integer.parseInt(game.getServerString());
				ans[0].setText((String) num.get(index));
				ans[1].setText(quest[index][0]);
				ans[2].setText(quest[index][1]);
				ans[3].setText(quest[index][2]);
				ans[4].setText(quest[index][3]);
				game.setServerString(null);
			}
			hpLabel.setText("HP: "+ game.getGameStart().getMon().getNowHp());
			this.repaint();
		}
	}
	
	public void setAns(int n){
		ansSelect = btn[n].getText();
		//System.out.println(ansSelect);
	}
	
	public String getAns(){
		return this.ansSelect;
	}
	
	protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
	     Color c = new Color(0x99D9EA);
	     setBackground(c);
        if(game.isHurt == true){
        	g.drawImage(game.getGameStart().getMon().getHitImage(), 50, 175,300,300, null);
        }else{
        	g.drawImage(game.getGameStart().getMon().getImageleft(), 50, 175,300,300, null);
        }
    }
	
	
}
