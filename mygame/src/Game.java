import java.awt.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.applet.*;
import java.lang.Runnable;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;



public class Game extends Applet implements Runnable{
	public Image airplane;
	public Image chargeUp1,chargeUp2,chargeUp3,chargeDown1,chargeDown2,chargeDown3,charged;
	public Image up1,up2,up3,down1,down2,down3;	
	public Image buffer;
	public Image shootingMan;
	public Image enemyImage1,enemyImage2,enemyImage3,enemyImage4;
	public Image explode;
	public Image background1,background2;
	public Image enemyBullet;
	public Image skillBulletImage;
	public Image fire1,fire2,fire3;
	public Image main,help;
	public Image bombImage;
	public Image itemImage1,itemImage2;
	public Image Boss1;
	Thread thr;
	Graphics grap;
	int x,y;
	int count;
	int background1_X,background2_X;
	int life;
	int revivalDelay;
	int moojukTime;
	int bullet_Delay;
	int maxCount;
	int fireCount;
	int skillCount;
	int maxSkillCount;
	int airplane_index;
	int flowIndex;
	int maxEnemy = 0;
	int skillDelay;
	int enemyLimit;
	int attackDelay;	
	int level;
	int bulletLevel = 1;
	int skillLevel = 1;
	int animationIndex;
	int score;
	int skillDamage = 10;
	int page = 1;
	boolean pase_2=false;
	boolean upPressed = false;
	boolean downPressed = false;
	boolean leftPressed = false;
	boolean rightPressed = false;
	boolean xPressed = false;
	boolean deathFlag = false;
	boolean moojukFlag = true;
	boolean shootFlag =false;
	boolean skillFlag = false;
	boolean startFlag = false;
	boolean showMessageFlag = true;
	Shoot shoot;
	EnemyAct enemyAct;
	CreateEnemy createEnemy;
	ItemHandling itemHandling;
	Skill skill;
	Vector bulletList = new Vector();
	Vector enemyList = new Vector();
	Vector boomList = new Vector();
	Vector enemyBulletList = new Vector();
	Vector skillBulletList = new Vector();
	Vector enemyImageList = new Vector();
	Vector bombList = new Vector();
	Vector itemList = new Vector();
	Vector bulletImageList = new Vector();
	Vector attackedEnemyImageList = new Vector();
	Vector clipVec = new Vector();
	Vector pathVec = new Vector();
	BufferedImage bf;
	Graphics2D g2;
	AudioInputStream g_ais;
	Clip g_clip;
	
	
	public void init()
	{
		resize(1350,650);
	}
	
	
	public void start() {
		if (thr==null)
			thr = new Thread(this);	
		buffer = createImage(1650,650);
		airplane = getImage(getCodeBase(), "..\\rsc\\1.png");
		enemyImage1 = getImage(getCodeBase(), "..\\rsc\\Enemy.png");
		enemyImage2 = getImage(getCodeBase(), "..\\rsc\\Enemy2.png");
		enemyImage3 = getImage(getCodeBase(), "..\\rsc\\Enemy3.png");
		enemyImage4 = getImage(getCodeBase(), "..\\rsc\\Enemy4.png");
		explode = getImage(getCodeBase(), "..\\rsc\\explode.png");
		background1 = getImage(getCodeBase(), "..\\rsc\\1_1.jpg");
		background2 = getImage(getCodeBase(), "..\\rsc\\1_2.jpg");
		enemyBullet = getImage(getCodeBase(), "..\\rsc\\enemyBullet.png");
		skillBulletImage = getImage(getCodeBase(), "..\\rsc\\skillBullet.png");
		main = getImage(getCodeBase(), "..\\rsc\\main.png");
		help = getImage(getCodeBase(), "..\\rsc\\help.jpg");
		up1 = getImage(getCodeBase(),"..\\rsc\\a.png");
		up2 = getImage(getCodeBase(),"..\\rsc\\b.png");
		up3 = getImage(getCodeBase(),"..\\rsc\\c.png");
		chargeUp1 = getImage(getCodeBase(),"..\\rsc\\charge_a.png"); 
		chargeUp2 = getImage(getCodeBase(),"..\\rsc\\charge_b.png");
		chargeUp3 = getImage(getCodeBase(),"..\\rsc\\charge_c.png");
		down1 = getImage(getCodeBase(),"..\\rsc\\2.png");
		down2 = getImage(getCodeBase(),"..\\rsc\\3.png");
		down3 = getImage(getCodeBase(),"..\\rsc\\4.png");
		chargeDown1 = getImage(getCodeBase(),"..\\rsc\\charge_2.png");
		chargeDown2 = getImage(getCodeBase(),"..\\rsc\\charge_3.png");
		chargeDown3 = getImage(getCodeBase(),"..\\rsc\\charge_4.png");
		charged = getImage(getCodeBase(),"..\\rsc\\charge_1.png");
		bombImage = getImage(getCodeBase(),"..\\rsc\\bomb.png");
		itemImage1 = getImage(getCodeBase(), "..\\rsc\\item1.png");
		itemImage2 = getImage(getCodeBase(), "..\\rsc\\item2.png");
		Boss1 = getImage(getCodeBase(),"..\\rsc\\boss1.png");
		fire1 = getImage(getCodeBase(),"..\\rsc\\fire1.png");
		fire2 = getImage(getCodeBase(),"..\\rsc\\fire2.png");
		fire3 = getImage(getCodeBase(),"..\\rsc\\fire3.png");
		x=-50;
		y=100;
		grap = buffer.getGraphics();
		thr.start();
		fireCount = 2;
		maxCount = 2;
		life = 25;
		revivalDelay = 100;
		moojukTime = 300;
		bullet_Delay = 0;
		skillCount = 0;
		maxSkillCount = 50;
		airplane_index = 0;
		level = 1;
		flowIndex = -150;
		enemyLimit = 1;
		attackDelay = 5;
		background1_X=0;
		background2_X=1650;
		score = 0;
		Enemy enemy;
		enemyAct = new EnemyAct();
		shoot = new Shoot();
		createEnemy = new CreateEnemy();
		itemHandling = new ItemHandling();
		skill = new Skill(this);
		bf = (BufferedImage)createImage(40,15);
		
		
		for (int i=0; i<6; i++) {
			Image enemyImage = getImage(getCodeBase(), "..\\rsc\\enemy"+i+".png");
			enemyImageList.add(enemyImage);
		}
		for (int i=0; i<3; i++) { 
			Image attacked_enemyImage = getImage(getCodeBase(),"..\\rsc\\boss"+(i+1)+".png");
			enemyImageList.add(attacked_enemyImage);
		}
		for (int i=0; i<5; i++) { 
			Image bulletImage = getImage(getCodeBase(),"..\\rsc\\bullet"+(1+i)+".png");
			bulletImageList.add(bulletImage);
		}
		for (int i=0; i<6; i++) { 
			Image attackedEnemyImage = getImage(getCodeBase(),"..\\rsc\\attacked_enemy"+i+".png");
			attackedEnemyImageList.add(attackedEnemyImage);
		}
		for (int i=0; i<3; i++) { 
			Image attackedEnemyImage = getImage(getCodeBase(),"..\\rsc\\attacked_boss"+(i+1)+".png");
			attackedEnemyImageList.add(attackedEnemyImage);
		}
		Font font = new Font("Arial Black",Font.PLAIN,30);
		grap.setFont(font);
		//level = 4;
		
	}
	
	public void run() {
		while (true) {
			repaint();
			if (moojukTime < 220) {
				if (upPressed && y>=0)
					y-=5;
				if (downPressed && y<610)
					y+=5;
				if (leftPressed && x>=0)
					x-=5;
				if (rightPressed && x<1300)
					x+=5;
			}
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void paint(Graphics g) {
		if (startFlag) {
			moveBackground();
			enemyAct.MoveEnemy(this);
			enemyAct.EnemyAttack(this);
			if (deathFlag) 
				revival();
			if (moojukFlag) 
				moojuk();
			if (skillFlag)
				shoot.Skill(this);
			boomHandling();
			inputHandling();
			shoot.MoveBullet(this);
			itemHandling.Process(this);
			
			switch(level) {
			case 1:
				flow1();
				break;
			case 2:
				flow2();
				break;
			case 3:
				flow3();
				break;
			case 4:
				flow4();
				break;
			}
			flowIndex++;
			if (showMessageFlag==true)
				showMessage();
			grap.setColor(Color.white);
			grap.drawString("Life : "+life, 30, 30);
			grap.drawString("Score : "+score, 1100, 30);
			g.drawImage(buffer,0,0,this);
			
		}
		else if (page==1){
			grap.drawImage(main,0,0,1350,650,this);
			g.drawImage(buffer,0,0,this);
		}
		else if (page==2) {
			grap.drawImage(help,0,0,1350,650,this);
			g.drawImage(buffer,0,0,this);
		}
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void stop() {
		if (thr!=null) {
			thr.stop();
			thr=null;
		}
			
	}
	public boolean keyDown(Event e, int key) {
		if (key == 1004) 
			upPressed = true;
		if (key == 1005)
			downPressed = true;
		if (key == 1006)
			leftPressed = true;
		if (key == 1007) 
			rightPressed = true;
		if (key==120 && shootFlag==false) { //Flag�� false�� ���� Z_Pressed�� true�� ����
			xPressed=true;
			shootFlag = true;
		}
		if (key==112)
			page=2;
		if (key==116) {
			startFlag = true;
			playBGM("..\\rsc\\bgm1.wav");
		}
		return true;
	}
	
	public boolean keyUp(Event e, int key) {
		if (key==1004)
			upPressed=false;
		if (key==1005)
			downPressed=false;
		if (key==1006)
			leftPressed=false;
		if (key==1007)
			rightPressed=false;
		
		if (key==120) {
			if (skillCount == maxSkillCount) {
				skillFlag = true;
				skillDelay = 200;
			}
			skillCount=0;
			xPressed = false;
			shootFlag = false;	
		}
		return true;
	}
	
	public void playSound(String AudioFile) {
		PlaySoundThread s_thread = new PlaySoundThread(AudioFile);
		s_thread.start();
	}
	
	void playBGM(String AudioFile) {
		try {
			g_ais = AudioSystem.getAudioInputStream(new File(AudioFile));
			g_clip = AudioSystem.getClip();
			g_clip.open(g_ais);
			g_clip.loop(3);
		}
		catch (Exception ex) {
			
		}
	}
	
	void setBackGround() {
			background1 = getImage(getCodeBase(), "..\\rsc\\"+level+"_1.jpg");
			background2 = getImage(getCodeBase(), "..\\rsc\\"+level+"_2.jpg");
	}
	
	void moveBackground() {
		grap.drawImage(background1, background1_X, 0, 1650, 650, this);
		grap.drawImage(background2, background2_X, 0, 1650, 650, this);
		background1_X-=2;
		background2_X-=2;
		if (background1_X<=-1650)
			background1_X=1650;
		else if (background2_X<=-1650)
			background2_X = 1650;
	}
	
	void showMessage() {
		Image image;
		if ((life==0 && deathFlag )|| level==5) 
			image = getImage(getCodeBase(),"..\\rsc\\game_over.png");	
		
		else
			image = getImage(getCodeBase(),"..\\rsc\\stage"+level+".png");
		grap.drawImage(image, 0, 275, this);
		if (flowIndex==0)
			showMessageFlag=false;
		
	}
	
	void changeSkillImage(String ImageName) {
		skillBulletImage = getImage(getCodeBase(),ImageName); 
	}
	
	void showStageClear() {
		Image image = getImage(getCodeBase(),"..\\rsc\\stage_clear"+animationIndex/5+".png");
		grap.drawImage(image,0,300,this);
		if (animationIndex/5<4 && flowIndex<300350)
			animationIndex++;
		else if (animationIndex/5!=0 && flowIndex>=300350)
			animationIndex--;
		
	}
	
	void revival() {
		revivalDelay-=1;
		if (revivalDelay==0 && life > 0) {
			life--;
			fireCount = maxCount;
			x = -50;
			y=100;
			moojukFlag = true;
			deathFlag = false;
			revivalDelay = 100;
			moojukTime = 300;
		}
		else if (revivalDelay==0 && life==0)
			showMessageFlag=true;		
	}
	
	void moojuk() {
		moojukTime -=1;
		if (moojukTime <=300 && moojukTime >220)
			x+=3;
		if (moojukTime<=50)
			moojukFlag = false;
	}
	void boomHandling() {
		int boomIndex;
		for (int i=0; i<boomList.size(); i++) {
			BoomEffect temp = (BoomEffect)boomList.elementAt(i);
			boomIndex = temp.boomIndex/3;
			grap.drawImage(explode,temp.boom_X,temp.boom_Y,temp.boom_X+temp.width,temp.boom_Y+temp.height,(boomIndex%4)*64,(boomIndex/4)*64,(boomIndex%4)*64+64,(boomIndex/4)*64+64,  this);
			temp.boomIndex++;
			if (temp.boomIndex>=48) 
				boomList.remove(i);	
		}
	}
	
	void inputHandling() {
		switch ((flowIndex/10)%3) {
		case 0:
			grap.drawImage(fire1,x-16,y+7,this);
			break;
		case 1:
			grap.drawImage(fire2,x-16,y+7,this);
			break;
		case 2:
			grap.drawImage(fire3,x-16,y+7,this);
			break;
		}
		if (upPressed) {
			if (airplane_index<12 && moojukTime <220)
			airplane_index++;
		}
		else if (downPressed && moojukTime <220) {
			if (airplane_index>-12)
				airplane_index--;
		}
		else {
			if (airplane_index<0)
				airplane_index++;
			else if (airplane_index>0)
				airplane_index--;
		}
			
		
		if (!deathFlag) {
			if (airplane_index==12) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(up3,x,y,this);
				else
					grap.drawImage(chargeUp3,x,y,this);
			}
			else if (airplane_index>=6) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(up2,x,y,this);
				else
					grap.drawImage(chargeUp2,x,y,this);
			}
			else if (airplane_index>0) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(up1,x,y,this);
				else
					grap.drawImage(chargeUp1,x,y,this);
			}
			else if (airplane_index==-12) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(down3,x,y,this);
				else
					grap.drawImage(chargeDown3,x,y,this);
				}
			
			else if (airplane_index<-6) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(down2,x,y,this);
				else
					grap.drawImage(chargeDown2,x,y,this);
			}
			else if (airplane_index<0) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(down1,x,y,this);
				else
					grap.drawImage(chargeDown1,x,y,this);
			}
			else {
				if (skillCount!=maxSkillCount)
					grap.drawImage(airplane,x,y,this);
				else
					grap.drawImage(charged,x,y,this);
			}
		}
			
		if (xPressed) {
			fireCount=0;
			xPressed = false;
		}
		if (shootFlag) {			
			if (skillCount < maxSkillCount && skillDelay==0) 
				skillCount++;
		}
		if (fireCount!=maxCount) {
			if (!deathFlag) {
				if (bullet_Delay<=0) {
					shoot.launch(this);
					fireCount++;
					bullet_Delay = attackDelay;
					
				}
				bullet_Delay--;
			}
		}
		if (skillDelay!=0)
			skillDelay--;		
	}
	
	
	void flow1() {
		if (flowIndex>=150 && flowIndex<=300) {
			if (flowIndex % 30 == 0) {
				createEnemy.SetSpeed(5,0,8);
				createEnemy.Pattern1(1350, 100+(int)(400*Math.random()),2,1,20,level,2,this);
				
			}			
		}
		
		else if (flowIndex >= 450 && flowIndex <475) {
			if (flowIndex % 5 == 0) {
				createEnemy.SetSpeed(5,0,15);
				createEnemy.Pattern1(1350, 150+(flowIndex-450)*13,2,2,20 ,level,2,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
		}
		
		else if (flowIndex == 700) {
			createEnemy.SetSpeed(5,0,5);
			createEnemy.Pattern2(1350,300,4,3, 400 ,level,4,this);
		}
		
		else if (flowIndex > 800 && flowIndex <1850) {
			if (maxEnemy==0)
				flowIndex = 1850;
		}
			
		else if (flowIndex >= 1950 && flowIndex < 2050) {
			if (flowIndex % 20 == 0) {
				createEnemy.SetSpeed(-1, 3, 5);
				createEnemy.Pattern1(1100,-40,3,4,20 ,level,0,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
		}
		else if (flowIndex == 2100) {
			createEnemy.SetSpeed(15,0,10);
			for (int i=0; i<5; i++) 
				createEnemy.Pattern1(1350,(i+1)*110,2,2,2 ,level+1,2,this);
		}
		
		else if (flowIndex == 2350) {
			createEnemy.SetSpeed(7, 0, 10);
			createEnemy.Pattern1(1350, 300, 3, 3 ,300 , level+1,3,this);
		}
		
		else if (flowIndex>=2350 && flowIndex < 3350) {
			if (maxEnemy==0)
				flowIndex=3350;
		}
		else if (flowIndex >= 3500 && flowIndex < 4200) {
			if (flowIndex % 70 == 0) {
				createEnemy.SetSpeed(5,0,5);
				int attackPattern = 1+(int)(Math.random()*3);
				if (attackPattern==2)
					createEnemy.SetSpeed(10,0,7);
				int movePattern = 1+(int)(Math.random()*2);
				createEnemy.Pattern1(1350, 100+(int)(400*Math.random()),attackPattern,movePattern,20,level,attackPattern,this);	
			}
		}
		
		else if (flowIndex==4250) {
			createEnemy.SetSpeed(5,0,8);
			createEnemy.Pattern1(1350, 325,4,3,400,level+2,4,this);
			createEnemy.SetSpeed(3,0,8);
		}
		else if (flowIndex >= 4250 && flowIndex <5250 && maxEnemy==0)
			flowIndex = 5250;
		else if (flowIndex==5300) {
			createEnemy.SetSpeed(5,0,5);
			createEnemy.CreateBoss(1350, 325,10,10,2000,level,6,this);
		}
		
		else if (flowIndex >=5300 && flowIndex <300000) {
			if (maxEnemy==0) {
				flowIndex=300000;
			}
		}
		else if (flowIndex==300100)
			animationIndex=0;
		else if (flowIndex>=300100 && flowIndex <=300370) 
			showStageClear();
		else if (flowIndex>=300500) {
			flowIndex=-200;
			level++;
			showMessageFlag=true;
			g_clip.close();
			playBGM("..\\rsc\\bgm2.wav");
			setBackGround();
		}
	}
	
	void flow2() {
		if (flowIndex==100) {
			createEnemy.SetSpeed(10,0,5);
			createEnemy.Pattern1(1449,225,1,2,40,level,1,this);
			createEnemy.Pattern1(1449,375,1,2,40,level,1,this);
		}
		else if(flowIndex==330) {
			createEnemy.SetSpeed(10,0,10);
			createEnemy.Pattern1(1350,150,2,2,40,level,2,this);
			createEnemy.Pattern1(1350,300,2,2,40,level,2,this);
			createEnemy.Pattern1(1350,450,2,2,40,level,2,this);
		}
		else if (flowIndex == 360) {
			createEnemy.SetSpeed(7,0,5);
			createEnemy.Pattern1(1350,225,3,2,40,level,3,this);
			createEnemy.Pattern1(1350,375,3,2,40,level,3,this);
		}
		else if (flowIndex == 650) {
			createEnemy.SetSpeed(8, 0, 5);
			createEnemy.Pattern1(1350, 325, 5, 2, 200, 1 , 4, this);
		}
		
		else if (flowIndex == 1000) {
			createEnemy.SetSpeed(20, 0, 3);
			createEnemy.Pattern1(1350, 100, 1, 1, 40, level+1, 1, this);
			createEnemy.Pattern1(1350, 600, 1, 1, 40, level+1, 1, this);
		}
		
		else if (flowIndex == 1100) {
			createEnemy.SetSpeed(10, 0, 3);
			createEnemy.Pattern1(1350, 300, 1, 1, 40, level+2, 1, this);
		}
		
		else if (flowIndex==1300) {
			createEnemy.SetSpeed(5, 0, 15);
			for (int i=0; i<10; i++)
				createEnemy.Pattern2(1350, (int)(600*Math.random()), 2, 2, 20, level+1, 2, this);
		}
		
		else if (flowIndex == 1600) {
			createEnemy.SetSpeed(5, 0, 5);
			createEnemy.Pattern1(1350, 300, 6, 3, 600, level, 4, this);
		}
		else if (flowIndex>=1600 && flowIndex<2520 && maxEnemy==0)
			flowIndex = 2520;
		else if (flowIndex>=2600 && flowIndex<2800) {
			if (flowIndex % 5 == 0) {
				createEnemy.SetSpeed(5,0,5);
				createEnemy.Pattern2(1350,100+(int)((flowIndex-2600)*2.5),2,2,10 ,1,2,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
		}
		else if (flowIndex == 3150) {
			createEnemy.SetSpeed(25, 0, 5);
			createEnemy.Pattern1(1350, 100, 5, 1, 100, level, 4, this);
			createEnemy.Pattern1(1350, 500, 5, 1, 100, level, 4, this);
		}
		else if (flowIndex>=3400 && flowIndex<5100) {
			if (flowIndex % 50 == 0) {
				createEnemy.SetSpeed(8,0,5);
				int temp = 1+(int)(Math.random()*3);
				if (temp==2)
					createEnemy.SetSpeed(8, 0, 15);
				createEnemy.Pattern2(1350,100+(int)(Math.random()*400),temp,2,70 ,level,temp,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
			if (flowIndex % 400 == 0) {
				createEnemy.SetSpeed(8, 0, 5);
				createEnemy.Pattern2(1350,100+(int)(Math.random()*400),3,2,300,4,3,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
		}
		
		else if (flowIndex >= 5200 && flowIndex < 5300) {
			if (flowIndex % 20 == 0) {
				createEnemy.SetSpeed(-1, 3, 5);
				createEnemy.Pattern1(1100,-40,3,4,100,level,3,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
		}
		else if (flowIndex == 5350) {
			createEnemy.SetSpeed(15,0,15);
			for (int i=0; i<5; i++) 
				createEnemy.Pattern1(1350,(i+1)*110,2,2,100,3,2,this);
		}
		
		else if (flowIndex==5700) {
			createEnemy.SetSpeed(5, 0, 8);
			createEnemy.Pattern1(1350,325,11,10,12000,1,7,this);
		}
		
		else if (flowIndex>=5700 && flowIndex<300000 && maxEnemy==0)
			flowIndex=300000;
		
		else if (flowIndex>=300100 && flowIndex <=300370) 
			showStageClear();
		else if (flowIndex>=300500) {
			flowIndex=-200;
			level++;
			showMessageFlag=true;
			g_clip.close();
			playBGM("..\\rsc\\bgm3.wav");
			setBackGround();
		}
		
		
	}
	
	void flow3() {
		if (flowIndex >= 100 && flowIndex<2000) {
			if (flowIndex%30==0) {
				createEnemy.SetSpeed(20,-5+Math.random()*10, 10);
				int temp = 2;
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450),temp, 4, 50, 1,temp , this);
			}
			if (flowIndex%200==0) {
				createEnemy.SetSpeed(20, 0, 5);
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450),5, 1, 500, 2, 4, this);
			}
			if (flowIndex%900==0) {
				createEnemy.SetSpeed(5, 0, 15);
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450),2, 2, 2000, 4, 4, this);
			}
		}
		else if (flowIndex == 2200) {
			createEnemy.SetSpeed(5, 0, 20);
			createEnemy.Pattern1(1350, 200, 2, 3, 2000, 1, 2, this);
			createEnemy.Pattern1(1350, 600, 2, 3, 2000, 1, 2, this);
			createEnemy.SetSpeed(5, 0, 8);
			createEnemy.Pattern1(1350, 400, 4, 3, 2000, 4, 4, this);
		}
		
		else if (flowIndex >=2200 && flowIndex <3200 && maxEnemy==0) {
			flowIndex = 3400;
		}
		else if (flowIndex ==3500) {
			createEnemy.SetSpeed(5, 0, 15);
			createEnemy.Pattern1(1350, 200, 3, 5, 3000, 3, 3, this);
		}
		else if (flowIndex==3530) {
			createEnemy.SetSpeed(5, 0, 15);
			createEnemy.Pattern1(1350, 450, 3, 5, 3000, 3, 3, this);
		}
		else if (flowIndex>=3530 && flowIndex<4600 && maxEnemy==0)
			flowIndex = 4700;
		
		else if (flowIndex >= 4800 && flowIndex < 6100) {
			if (flowIndex%50==0) {
				createEnemy.SetSpeed(5, 0, 8);
				createEnemy.Pattern3(1350, 150+(int)(Math.random()*450), 1, 1, 300, 2, 1, this);				
			}			
		}
		
		else if (flowIndex>=6300 && flowIndex < 6700) {
			if (flowIndex % 100==0) {
				createEnemy.SetSpeed(10,0,5);
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450), 4, 1, 300, 1, 4, this);
			}
		}
		
		else if (flowIndex>=6700 && flowIndex < 7100) {
			if (flowIndex % 100==0) {
				createEnemy.SetSpeed(12,0,5);
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450), 4, 1, 300, 2, 4, this);
			}
		}
		
		else if (flowIndex>=7100 && flowIndex < 7500) {
			if (flowIndex % 100==0) {
				createEnemy.SetSpeed(15,0,5);
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450), 4, 1, 300, 3, 4, this);
			}
		}
		
		else if (flowIndex==7500) {
			createEnemy.SetSpeed(18,0,5);
			createEnemy.Pattern3(1350, 300, 4, 1, 300, 4, 4, this);
		}
		else if (flowIndex==7600)
			flowIndex=8099;
		
		else if (flowIndex == 8100) {
			createEnemy.SetSpeed(15,0,5);
			createEnemy.Pattern1(1350, 100, 5, 1, 300, 1, 4, this);
			createEnemy.Pattern1(1350, 300, 5, 1, 300, 1, 4, this);
			createEnemy.Pattern1(1350, 500, 5, 1, 300, 1, 4, this);
		}
		else if (flowIndex==8200)
			g_clip.close();
		else if (flowIndex == 8300) {
			playBGM("..\\rsc\\bgm4.wav");
			createEnemy.SetSpeed(5, 0, 5);
			createEnemy.Pattern1(1350, 200, 12, 12, 150000, 1, 8, this);
		}
		
		else if (flowIndex>=8300 && flowIndex<300000 && maxEnemy==0)
			flowIndex=300000;
		
		else if (flowIndex>=300100 && flowIndex <=300370) 
			showStageClear();
		else if (flowIndex>=300500) {
			flowIndex=-200;
			level++;
			showMessageFlag=true;
			g_clip.close();
			playBGM("..\\rsc\\bgm3.wav");
			setBackGround();
			
		}
	}
	
	void flow4() {
		if (flowIndex==100) {
			createEnemy.SetSpeed(10,0,5);
			createEnemy.Pattern3(1449,225,1,3,40000,level,1,this);
			createEnemy.Pattern3(1449,375,1,3,40000,level,1,this);
		
		}
		if (maxEnemy!=0)
			flowIndex=200;
		if (flowIndex==500) {
			createEnemy.SetSpeed(10,0,5);
			createEnemy.Pattern1(1449,225,1,4,40000,level,1,this);
			createEnemy.Pattern1(1449,375,1,4,40000,level,1,this);
		
		}
	}
}



