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
		
		num.put(0, "以下哪一位非數碼寶貝中皇家騎士團成員?");
		num.put(1, "請問動漫神劍闖江湖中，飛天御劍派的奧義是?");
		num.put(2, "請問線上遊戲仙境傳說中，刺客的三轉名稱為?");
		num.put(3, "請問，遊戲<楓之谷>中，負責法師一轉的轉職教官名稱是?");
		num.put(4, "請問一公斤的棉花跟一公斤的鐵塊，哪個比較重?");
		num.put(5, "七龍珠中，首先變身為超級賽亞人第二階的人是?");
		num.put(6, "請問中華一番動畫中，誰沒有在公開的料理比賽中獲勝過?");
		num.put(7, "請問哈利波特裡，要召喚物品的咒語是？");
		num.put(8, "請問動漫鋼之煉金術師中，哪位人造人代表著色慾?");
		num.put(9, "請問奧林匹斯十二主神中的雅典娜，其父親為?");
		num.put(10, "請問海綿寶寶裡面，章魚哥會吹奏什麼樂器？");
		num.put(11, "請問航海王中，白鬍子吃了什麼果實？");
		num.put(12, "請問於去年結束連載的漫畫火影忍者最後究竟連載了幾話?");
		num.put(13, "下列哪一隻英雄沒有在四年前英雄聯盟S2的世界冠軍戰Game4中出場?");
		num.put(14, "請問傳說中的清大登山社事件發生在清大哪一齋?");
		num.put(15, "請問以下哪個老師最帥?");
		
		map.put("以下哪一位非數碼寶貝中皇家騎士團成員?", "帝王龍甲獸");
		map.put("請問動漫神劍闖江湖中，飛天御劍派的奧義是?", "天翔龍閃");
		map.put("請問線上遊戲仙境傳說中，刺客的三轉名稱為?", "十字斬首者");
		map.put("請問，遊戲<楓之谷>中，負責法師一轉的轉職教官名稱是?", "漢斯");
		map.put("請問一公斤的棉花跟一公斤的鐵塊，哪個比較重?", "一樣重");
		map.put("七龍珠中，首先變身為超級賽亞人第二階的人是?", "悟空");
		map.put("請問中華一番動畫中，誰沒有在公開的料理比賽中獲勝過?", "雷恩");
		map.put("請問哈利波特裡，要召喚物品的咒語是？", "速速前");
		map.put("請問動漫鋼之煉金術師中，哪位人造人代表著色慾?", "拉斯多");
		map.put("請問奧林匹斯十二主神中的雅典娜，其父親為?", "宙斯");
		map.put("請問海綿寶寶裡面，章魚哥會吹奏什麼樂器？", "單簧管");
		map.put("請問航海王中，白鬍子吃了什麼果實？", "震震果實");
		map.put("請問於去年結束連載的漫畫火影忍者最後究竟連載了幾話?", "700");
		map.put("下列哪一隻英雄沒有在四年前英雄聯盟S2的世界冠軍戰Game4中出場?", "奈德麗");
		map.put("請問傳說中的清大登山社事件發生在清大哪一齋?", "清齋");
		map.put("請問以下哪個老師最帥?", "以上皆是");
		
		quest = new String[16][4];
		for(int i = 0; i < 16; ++i){
	    	int j = 0; 
	    	if(i == 0){
	    		quest[i][j] = new String("奧米加獸");
	    		++j;
	    		quest[i][j] = new String("阿爾法獸");
	    		++j;
	    		quest[i][j] = new String("帝王龍甲獸");
	    		++j;
	    		quest[i][j] = new String("紅蓮騎士獸");
	    	}
	    	else if(i == 1){
	    		quest[i][j] = new String("天翔龍閃");
	    		++j;
	    		quest[i][j] = new String("龍角散");
	    		++j;
	    		quest[i][j] = new String("蓋亞能量炮");
	    		++j;
	    		quest[i][j] = new String("九頭龍閃");
	    	}
	    	else if(i == 2){
	    		quest[i][j] = new String("十字刺客");
	    		++j;
	    		quest[i][j] = new String("魅影追蹤者");
	    		++j;
	    		quest[i][j] = new String("十字斬首者");
	    		++j;
	    		quest[i][j] = new String("神行太保");
	    	}
	    	else if(i == 3){
	    		quest[i][j] = new String("王浩全");
	    		++j;
	    		quest[i][j] = new String("漢斯");
	    		++j;
	    		quest[i][j] = new String("達克魯");
	    		++j;
	    		quest[i][j] = new String("巴魯斯");
	    	}
	    	else if(i == 4){
	    		quest[i][j] = new String("棉花");
	    		++j;
	    		quest[i][j] = new String("鐵塊");
	    		++j;
	    		quest[i][j] = new String("一樣重");
	    		++j;
	    		quest[i][j] = new String("挖阿災喔");
	    	}
	    	else if(i == 5){
	    		quest[i][j] = new String("悟飯");
	    		++j;
	    		quest[i][j] = new String("悟空");
	    		++j;
	    		quest[i][j] = new String("達爾");
	    		++j;
	    		quest[i][j] = new String("撒旦");
	    	}
	    	else if(i == 6){
	    		quest[i][j] = new String("鋼棍解師傅");
	    		++j;
	    		quest[i][j] = new String("阿Q");
	    		++j;
	    		quest[i][j] = new String("雷恩");
	    		++j;
	    		quest[i][j] = new String("零勝恩");
	    	}
	    	else if(i == 7){
	    		quest[i][j] = new String("咒咒虐");
	    		++j;
	    		quest[i][j] = new String("路摸思");
	    		++j;
	    		quest[i][j] = new String("要錢錢");
	    		++j;
	    		quest[i][j] = new String("速速前");
	    	}
	    	else if(i == 8){
	    		quest[i][j] = new String("庫拉多尼");
	    		++j;
	    		quest[i][j] = new String("拉斯");
	    		++j;
	    		quest[i][j] = new String("普萊德");
	    		++j;
	    		quest[i][j] = new String("拉斯多");
	    	}
	    	else if(i == 9){
	    		quest[i][j] = new String("阿瑞斯");
	    		++j;
	    		quest[i][j] = new String("波賽頓");
	    		++j;
	    		quest[i][j] = new String("普羅米修斯");
	    		++j;
	    		quest[i][j] = new String("宙斯");
	    	}
	    	else if(i == 10){
	    		quest[i][j] = new String("喇叭");
	    		++j;
	    		quest[i][j] = new String("薩克斯風");
	    		++j;
	    		quest[i][j] = new String("單簧管");
	    		++j;
	    		quest[i][j] = new String("吉他");
	    	}
	    	else if(i == 11){
	    		quest[i][j] = new String("車震果實");
	    		++j;
	    		quest[i][j] = new String("震震果實");
	    		++j;
	    		quest[i][j] = new String("床震果實");
	    		++j;
	    		quest[i][j] = new String("防震果實");
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
	    		quest[i][j] = new String("卡爾瑟斯");
	    		++j;
	    		quest[i][j] = new String("希瓦娜");
	    		++j;
	    		quest[i][j] = new String("奈德麗");
	    		++j;
	    		quest[i][j] = new String("奧莉安娜");
	    	}
	    	else if(i == 14){
	    		quest[i][j] = new String("新齋");
	    		++j;
	    		quest[i][j] = new String("清齋");
	    		++j;
	    		quest[i][j] = new String("仁齋");
	    		++j;
	    		quest[i][j] = new String("義齋");
	    	}
	    	else if(i == 15){
	    		quest[i][j] = new String("王浩全");
	    		++j;
	    		quest[i][j] = new String("王浩全");
	    		++j;
	    		quest[i][j] = new String("王浩全");
	    		++j;
	    		quest[i][j] = new String("以上皆是");
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
