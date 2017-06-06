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



public class game extends Applet implements Runnable{
	public Image airplane;
	public Image charge_Up1,charge_Up2,charge_Up3,charge_Down1,charge_Down2,charge_Down3,charged;
	public Image Up1,Up2,Up3,Down1,Down2,Down3;	
	public Image buffer;
	public Image ShootingMan;
	public Image EnemyImage1,EnemyImage2,EnemyImage3,EnemyImage4;
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
	int X,Y;
	int count;
	int background1_X,background2_X;
	int Life;
	int revival_Delay;
	int moojuk_Time;
	int bullet_Delay;
	int maxCount;
	int fireCount;
	int skillCount;
	int maxSkillCount;
	int airplane_index;
	int flow_index;
	int max_enemy = 0;
	int skill_Delay;
	int enemy_limit;
	int Attack_delay;	
	int level;
	int bullet_level = 1;
	int skill_level = 1;
	int animation_index;
	int score;
	int skill_damage = 10;
	int page = 1;
	boolean pase_2=false;
	boolean Up_Pressed = false;
	boolean Down_Pressed = false;
	boolean Left_Pressed = false;
	boolean Right_Pressed = false;
	boolean X_Pressed = false;
	boolean Death_Flag = false;
	boolean Moojuk_Flag = true;
	boolean ShootFlag =false;
	boolean SkillFlag = false;
	boolean StartFlag = false;
	boolean ShowMessageFlag = true;
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
	Vector attacked_enemyImageList = new Vector();
	Vector clip_vec = new Vector();
	Vector path_vec = new Vector();
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
		EnemyImage1 = getImage(getCodeBase(), "..\\rsc\\Enemy.png");
		EnemyImage2 = getImage(getCodeBase(), "..\\rsc\\Enemy2.png");
		EnemyImage3 = getImage(getCodeBase(), "..\\rsc\\Enemy3.png");
		EnemyImage4 = getImage(getCodeBase(), "..\\rsc\\Enemy4.png");
		explode = getImage(getCodeBase(), "..\\rsc\\explode.png");
		background1 = getImage(getCodeBase(), "..\\rsc\\1_1.jpg");
		background2 = getImage(getCodeBase(), "..\\rsc\\1_2.jpg");
		enemyBullet = getImage(getCodeBase(), "..\\rsc\\enemyBullet.png");
		skillBulletImage = getImage(getCodeBase(), "..\\rsc\\skillBullet.png");
		main = getImage(getCodeBase(), "..\\rsc\\main.png");
		help = getImage(getCodeBase(), "..\\rsc\\help.jpg");
		Up1 = getImage(getCodeBase(),"..\\rsc\\a.png");
		Up2 = getImage(getCodeBase(),"..\\rsc\\b.png");
		Up3 = getImage(getCodeBase(),"..\\rsc\\c.png");
		charge_Up1 = getImage(getCodeBase(),"..\\rsc\\charge_a.png"); 
		charge_Up2 = getImage(getCodeBase(),"..\\rsc\\charge_b.png");
		charge_Up3 = getImage(getCodeBase(),"..\\rsc\\charge_c.png");
		Down1 = getImage(getCodeBase(),"..\\rsc\\2.png");
		Down2 = getImage(getCodeBase(),"..\\rsc\\3.png");
		Down3 = getImage(getCodeBase(),"..\\rsc\\4.png");
		charge_Down1 = getImage(getCodeBase(),"..\\rsc\\charge_2.png");
		charge_Down2 = getImage(getCodeBase(),"..\\rsc\\charge_3.png");
		charge_Down3 = getImage(getCodeBase(),"..\\rsc\\charge_4.png");
		charged = getImage(getCodeBase(),"..\\rsc\\charge_1.png");
		bombImage = getImage(getCodeBase(),"..\\rsc\\bomb.png");
		itemImage1 = getImage(getCodeBase(), "..\\rsc\\item1.png");
		itemImage2 = getImage(getCodeBase(), "..\\rsc\\item2.png");
		Boss1 = getImage(getCodeBase(),"..\\rsc\\boss1.png");
		fire1 = getImage(getCodeBase(),"..\\rsc\\fire1.png");
		fire2 = getImage(getCodeBase(),"..\\rsc\\fire2.png");
		fire3 = getImage(getCodeBase(),"..\\rsc\\fire3.png");
		X=-50;
		Y=100;
		grap = buffer.getGraphics();
		thr.start();
		fireCount = 2;
		maxCount = 2;
		Life = 25;
		revival_Delay = 100;
		moojuk_Time = 300;
		bullet_Delay = 0;
		skillCount = 0;
		maxSkillCount = 50;
		airplane_index = 0;
		level = 1;
		flow_index = -150;
		enemy_limit = 1;
		Attack_delay = 5;
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
			Image attacked_enemyImage = getImage(getCodeBase(),"..\\rsc\\attacked_enemy"+i+".png");
			attacked_enemyImageList.add(attacked_enemyImage);
		}
		for (int i=0; i<3; i++) { 
			Image attacked_enemyImage = getImage(getCodeBase(),"..\\rsc\\attacked_boss"+(i+1)+".png");
			attacked_enemyImageList.add(attacked_enemyImage);
		}
		Font font = new Font("Arial Black",Font.PLAIN,30);
		grap.setFont(font);
		//level = 4;
		
	}
	
	public void run() {
		while (true) {
			repaint();
			if (moojuk_Time < 220) {
				if (Up_Pressed && Y>=0)
					Y-=5;
				if (Down_Pressed && Y<610)
					Y+=5;
				if (Left_Pressed && X>=0)
					X-=5;
				if (Right_Pressed && X<1300)
					X+=5;
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
		if (StartFlag) {
			MoveBackground();
			enemyAct.MoveEnemy(this);
			enemyAct.EnemyAttack(this);
			if (Death_Flag) 
				Revival();
			if (Moojuk_Flag) 
				Moojuk();
			if (SkillFlag)
				shoot.Skill(this);
			BoomHandling();
			InputHandling();
			shoot.MoveBullet(this);
			itemHandling.Process(this);
			
			switch(level) {
			case 1:
				Flow1();
				break;
			case 2:
				Flow2();
				break;
			case 3:
				Flow3();
				break;
			case 4:
				Flow4();
				break;
			}
			flow_index++;
			if (ShowMessageFlag==true)
				ShowMessage();
			grap.setColor(Color.white);
			grap.drawString("Life : "+Life, 30, 30);
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
			Up_Pressed = true;
		if (key == 1005)
			Down_Pressed = true;
		if (key == 1006)
			Left_Pressed = true;
		if (key == 1007) 
			Right_Pressed = true;
		if (key==120 && ShootFlag==false) { //Flag가 false일 때만 Z_Pressed를 true로 변경
			X_Pressed=true;
			ShootFlag = true;
		}
		if (key==112)
			page=2;
		if (key==116) {
			StartFlag = true;
			PlayBGM("..\\rsc\\bgm1.wav");
		}
		return true;
	}
	
	public boolean keyUp(Event e, int key) {
		if (key==1004)
			Up_Pressed=false;
		if (key==1005)
			Down_Pressed=false;
		if (key==1006)
			Left_Pressed=false;
		if (key==1007)
			Right_Pressed=false;
		
		if (key==120) {
			if (skillCount == maxSkillCount) {
				SkillFlag = true;
				skill_Delay = 200;
			}
			skillCount=0;
			X_Pressed = false;
			ShootFlag = false;	
		}
		return true;
	}
	
	public void PlaySound(String AudioFile) {
		PlaySoundThread s_thread = new PlaySoundThread(AudioFile);
		s_thread.start();
	}
	
	void PlayBGM(String AudioFile) {
		try {
			g_ais = AudioSystem.getAudioInputStream(new File(AudioFile));
			g_clip = AudioSystem.getClip();
			g_clip.open(g_ais);
			g_clip.loop(3);
		}
		catch (Exception ex) {
			
		}
	}
	
	void SetBackGround() {
			background1 = getImage(getCodeBase(), "..\\rsc\\"+level+"_1.jpg");
			background2 = getImage(getCodeBase(), "..\\rsc\\"+level+"_2.jpg");
	}
	
	void MoveBackground() {
		grap.drawImage(background1, background1_X, 0, 1650, 650, this);
		grap.drawImage(background2, background2_X, 0, 1650, 650, this);
		background1_X-=2;
		background2_X-=2;
		if (background1_X<=-1650)
			background1_X=1650;
		else if (background2_X<=-1650)
			background2_X = 1650;
	}
	
	void ShowMessage() {
		Image image;
		if ((Life==0 && Death_Flag )|| level==5) 
			image = getImage(getCodeBase(),"..\\rsc\\game_over.png");	
		
		else
			image = getImage(getCodeBase(),"..\\rsc\\stage"+level+".png");
		grap.drawImage(image, 0, 275, this);
		if (flow_index==0)
			ShowMessageFlag=false;
		
	}
	
	void ChangeSkillImage(String ImageName) {
		skillBulletImage = getImage(getCodeBase(),ImageName); 
	}
	
	void ShowStageClear() {
		Image image = getImage(getCodeBase(),"..\\rsc\\stage_clear"+animation_index/5+".png");
		grap.drawImage(image,0,300,this);
		if (animation_index/5<4 && flow_index<300350)
			animation_index++;
		else if (animation_index/5!=0 && flow_index>=300350)
			animation_index--;
		
	}
	
	void Revival() {
		revival_Delay-=1;
		if (revival_Delay==0 && Life > 0) {
			Life--;
			fireCount = maxCount;
			X = -50;
			Y=100;
			Moojuk_Flag = true;
			Death_Flag = false;
			revival_Delay = 100;
			moojuk_Time = 300;
		}
		else if (revival_Delay==0 && Life==0)
			ShowMessageFlag=true;		
	}
	
	void Moojuk() {
		moojuk_Time -=1;
		if (moojuk_Time <=300 && moojuk_Time >220)
			X+=3;
		if (moojuk_Time<=50)
			Moojuk_Flag = false;
	}
	void BoomHandling() {
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
	
	void InputHandling() {
		switch ((flow_index/10)%3) {
		case 0:
			grap.drawImage(fire1,X-16,Y+7,this);
			break;
		case 1:
			grap.drawImage(fire2,X-16,Y+7,this);
			break;
		case 2:
			grap.drawImage(fire3,X-16,Y+7,this);
			break;
		}
		if (Up_Pressed) {
			if (airplane_index<12 && moojuk_Time <220)
			airplane_index++;
		}
		else if (Down_Pressed && moojuk_Time <220) {
			if (airplane_index>-12)
				airplane_index--;
		}
		else {
			if (airplane_index<0)
				airplane_index++;
			else if (airplane_index>0)
				airplane_index--;
		}
			
		
		if (!Death_Flag) {
			if (airplane_index==12) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(Up3,X,Y,this);
				else
					grap.drawImage(charge_Up3,X,Y,this);
			}
			else if (airplane_index>=6) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(Up2,X,Y,this);
				else
					grap.drawImage(charge_Up2,X,Y,this);
			}
			else if (airplane_index>0) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(Up1,X,Y,this);
				else
					grap.drawImage(charge_Up1,X,Y,this);
			}
			else if (airplane_index==-12) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(Down3,X,Y,this);
				else
					grap.drawImage(charge_Down3,X,Y,this);
				}
			
			else if (airplane_index<-6) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(Down2,X,Y,this);
				else
					grap.drawImage(charge_Down2,X,Y,this);
			}
			else if (airplane_index<0) {
				if (skillCount!=maxSkillCount)
					grap.drawImage(Down1,X,Y,this);
				else
					grap.drawImage(charge_Down1,X,Y,this);
			}
			else {
				if (skillCount!=maxSkillCount)
					grap.drawImage(airplane,X,Y,this);
				else
					grap.drawImage(charged,X,Y,this);
			}
		}
			
		if (X_Pressed) {
			fireCount=0;
			X_Pressed = false;
		}
		if (ShootFlag) {			
			if (skillCount < maxSkillCount && skill_Delay==0) 
				skillCount++;
		}
		if (fireCount!=maxCount) {
			if (!Death_Flag) {
				if (bullet_Delay<=0) {
					shoot.launch(this);
					fireCount++;
					bullet_Delay = Attack_delay;
					
				}
				bullet_Delay--;
			}
		}
		if (skill_Delay!=0)
			skill_Delay--;		
	}
	
	
	void Flow1() {
		if (flow_index>=150 && flow_index<=300) {
			if (flow_index % 30 == 0) {
				createEnemy.SetSpeed(5,0,8);
				createEnemy.Pattern1(1350, 100+(int)(400*Math.random()),2,1,20,level,2,this);
				
			}			
		}
		
		else if (flow_index >= 450 && flow_index <475) {
			if (flow_index % 5 == 0) {
				createEnemy.SetSpeed(5,0,15);
				createEnemy.Pattern1(1350, 150+(flow_index-450)*13,2,2,20 ,level,2,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
		}
		
		else if (flow_index == 700) {
			createEnemy.SetSpeed(5,0,5);
			createEnemy.Pattern2(1350,300,4,3, 400 ,level,4,this);
		}
		
		else if (flow_index > 800 && flow_index <1850) {
			if (max_enemy==0)
				flow_index = 1850;
		}
			
		else if (flow_index >= 1950 && flow_index < 2050) {
			if (flow_index % 20 == 0) {
				createEnemy.SetSpeed(-1, 3, 5);
				createEnemy.Pattern1(1100,-40,3,4,20 ,level,0,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
		}
		else if (flow_index == 2100) {
			createEnemy.SetSpeed(15,0,10);
			for (int i=0; i<5; i++) 
				createEnemy.Pattern1(1350,(i+1)*110,2,2,2 ,level+1,2,this);
		}
		
		else if (flow_index == 2350) {
			createEnemy.SetSpeed(7, 0, 10);
			createEnemy.Pattern1(1350, 300, 3, 3 ,300 , level+1,3,this);
		}
		
		else if (flow_index>=2350 && flow_index < 3350) {
			if (max_enemy==0)
				flow_index=3350;
		}
		else if (flow_index >= 3500 && flow_index < 4200) {
			if (flow_index % 70 == 0) {
				createEnemy.SetSpeed(5,0,5);
				int attackPattern = 1+(int)(Math.random()*3);
				if (attackPattern==2)
					createEnemy.SetSpeed(10,0,7);
				int movePattern = 1+(int)(Math.random()*2);
				createEnemy.Pattern1(1350, 100+(int)(400*Math.random()),attackPattern,movePattern,20,level,attackPattern,this);	
			}
		}
		
		else if (flow_index==4250) {
			createEnemy.SetSpeed(5,0,8);
			createEnemy.Pattern1(1350, 325,4,3,400,level+2,4,this);
			createEnemy.SetSpeed(3,0,8);
		}
		else if (flow_index >= 4250 && flow_index <5250 && max_enemy==0)
			flow_index = 5250;
		else if (flow_index==5300) {
			createEnemy.SetSpeed(5,0,5);
			createEnemy.CreateBoss(1350, 325,10,10,2000,level,6,this);
		}
		
		else if (flow_index >=5300 && flow_index <300000) {
			if (max_enemy==0) {
				flow_index=300000;
			}
		}
		else if (flow_index==300100)
			animation_index=0;
		else if (flow_index>=300100 && flow_index <=300370) 
			ShowStageClear();
		else if (flow_index>=300500) {
			flow_index=-200;
			level++;
			ShowMessageFlag=true;
			g_clip.close();
			PlayBGM("..\\rsc\\bgm2.wav");
			SetBackGround();
		}
	}
	
	void Flow2() {
		if (flow_index==100) {
			createEnemy.SetSpeed(10,0,5);
			createEnemy.Pattern1(1449,225,1,2,40,level,1,this);
			createEnemy.Pattern1(1449,375,1,2,40,level,1,this);
		}
		else if(flow_index==330) {
			createEnemy.SetSpeed(10,0,10);
			createEnemy.Pattern1(1350,150,2,2,40,level,2,this);
			createEnemy.Pattern1(1350,300,2,2,40,level,2,this);
			createEnemy.Pattern1(1350,450,2,2,40,level,2,this);
		}
		else if (flow_index == 360) {
			createEnemy.SetSpeed(7,0,5);
			createEnemy.Pattern1(1350,225,3,2,40,level,3,this);
			createEnemy.Pattern1(1350,375,3,2,40,level,3,this);
		}
		else if (flow_index == 650) {
			createEnemy.SetSpeed(8, 0, 5);
			createEnemy.Pattern1(1350, 325, 5, 2, 200, 1 , 4, this);
		}
		
		else if (flow_index == 1000) {
			createEnemy.SetSpeed(20, 0, 3);
			createEnemy.Pattern1(1350, 100, 1, 1, 40, level+1, 1, this);
			createEnemy.Pattern1(1350, 600, 1, 1, 40, level+1, 1, this);
		}
		
		else if (flow_index == 1100) {
			createEnemy.SetSpeed(10, 0, 3);
			createEnemy.Pattern1(1350, 300, 1, 1, 40, level+2, 1, this);
		}
		
		else if (flow_index==1300) {
			createEnemy.SetSpeed(5, 0, 15);
			for (int i=0; i<10; i++)
				createEnemy.Pattern2(1350, (int)(600*Math.random()), 2, 2, 20, level+1, 2, this);
		}
		
		else if (flow_index == 1600) {
			createEnemy.SetSpeed(5, 0, 5);
			createEnemy.Pattern1(1350, 300, 6, 3, 600, level, 4, this);
		}
		else if (flow_index>=1600 && flow_index<2520 && max_enemy==0)
			flow_index = 2520;
		else if (flow_index>=2600 && flow_index<2800) {
			if (flow_index % 5 == 0) {
				createEnemy.SetSpeed(5,0,5);
				createEnemy.Pattern2(1350,100+(int)((flow_index-2600)*2.5),2,2,10 ,1,2,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
		}
		else if (flow_index == 3150) {
			createEnemy.SetSpeed(25, 0, 5);
			createEnemy.Pattern1(1350, 100, 5, 1, 100, level, 4, this);
			createEnemy.Pattern1(1350, 500, 5, 1, 100, level, 4, this);
		}
		else if (flow_index>=3400 && flow_index<5100) {
			if (flow_index % 50 == 0) {
				createEnemy.SetSpeed(8,0,5);
				int temp = 1+(int)(Math.random()*3);
				if (temp==2)
					createEnemy.SetSpeed(8, 0, 15);
				createEnemy.Pattern2(1350,100+(int)(Math.random()*400),temp,2,70 ,level,temp,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
			if (flow_index % 400 == 0) {
				createEnemy.SetSpeed(8, 0, 5);
				createEnemy.Pattern2(1350,100+(int)(Math.random()*400),3,2,300,4,3,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
		}
		
		else if (flow_index >= 5200 && flow_index < 5300) {
			if (flow_index % 20 == 0) {
				createEnemy.SetSpeed(-1, 3, 5);
				createEnemy.Pattern1(1100,-40,3,4,100,level,3,this);  //X,Y,attack_pattern,move_pattern,HP, level, this
			}
		}
		else if (flow_index == 5350) {
			createEnemy.SetSpeed(15,0,15);
			for (int i=0; i<5; i++) 
				createEnemy.Pattern1(1350,(i+1)*110,2,2,100,3,2,this);
		}
		
		else if (flow_index==5700) {
			createEnemy.SetSpeed(5, 0, 8);
			createEnemy.Pattern1(1350,325,11,10,12000,1,7,this);
		}
		
		else if (flow_index>=5700 && flow_index<300000 && max_enemy==0)
			flow_index=300000;
		
		else if (flow_index>=300100 && flow_index <=300370) 
			ShowStageClear();
		else if (flow_index>=300500) {
			flow_index=-200;
			level++;
			ShowMessageFlag=true;
			g_clip.close();
			PlayBGM("..\\rsc\\bgm3.wav");
			SetBackGround();
		}
		
		
	}
	
	void Flow3() {
		if (flow_index >= 100 && flow_index<2000) {
			if (flow_index%30==0) {
				createEnemy.SetSpeed(20,-5+Math.random()*10, 10);
				int temp = 2;
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450),temp, 4, 50, 1,temp , this);
			}
			if (flow_index%200==0) {
				createEnemy.SetSpeed(20, 0, 5);
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450),5, 1, 500, 2, 4, this);
			}
			if (flow_index%900==0) {
				createEnemy.SetSpeed(5, 0, 15);
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450),2, 2, 2000, 4, 4, this);
			}
		}
		else if (flow_index == 2200) {
			createEnemy.SetSpeed(5, 0, 20);
			createEnemy.Pattern1(1350, 200, 2, 3, 2000, 1, 2, this);
			createEnemy.Pattern1(1350, 600, 2, 3, 2000, 1, 2, this);
			createEnemy.SetSpeed(5, 0, 8);
			createEnemy.Pattern1(1350, 400, 4, 3, 2000, 4, 4, this);
		}
		
		else if (flow_index >=2200 && flow_index <3200 && max_enemy==0) {
			flow_index = 3400;
		}
		else if (flow_index ==3500) {
			createEnemy.SetSpeed(5, 0, 15);
			createEnemy.Pattern1(1350, 200, 3, 5, 3000, 3, 3, this);
		}
		else if (flow_index==3530) {
			createEnemy.SetSpeed(5, 0, 15);
			createEnemy.Pattern1(1350, 450, 3, 5, 3000, 3, 3, this);
		}
		else if (flow_index>=3530 && flow_index<4600 && max_enemy==0)
			flow_index = 4700;
		
		else if (flow_index >= 4800 && flow_index < 6100) {
			if (flow_index%50==0) {
				createEnemy.SetSpeed(5, 0, 8);
				createEnemy.Pattern3(1350, 150+(int)(Math.random()*450), 1, 1, 300, 2, 1, this);				
			}			
		}
		
		else if (flow_index>=6300 && flow_index < 6700) {
			if (flow_index % 100==0) {
				createEnemy.SetSpeed(10,0,5);
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450), 4, 1, 300, 1, 4, this);
			}
		}
		
		else if (flow_index>=6700 && flow_index < 7100) {
			if (flow_index % 100==0) {
				createEnemy.SetSpeed(12,0,5);
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450), 4, 1, 300, 2, 4, this);
			}
		}
		
		else if (flow_index>=7100 && flow_index < 7500) {
			if (flow_index % 100==0) {
				createEnemy.SetSpeed(15,0,5);
				createEnemy.Pattern3(1350, 100+(int)(Math.random()*450), 4, 1, 300, 3, 4, this);
			}
		}
		
		else if (flow_index==7500) {
			createEnemy.SetSpeed(18,0,5);
			createEnemy.Pattern3(1350, 300, 4, 1, 300, 4, 4, this);
		}
		else if (flow_index==7600)
			flow_index=8099;
		
		else if (flow_index == 8100) {
			createEnemy.SetSpeed(15,0,5);
			createEnemy.Pattern1(1350, 100, 5, 1, 300, 1, 4, this);
			createEnemy.Pattern1(1350, 300, 5, 1, 300, 1, 4, this);
			createEnemy.Pattern1(1350, 500, 5, 1, 300, 1, 4, this);
		}
		else if (flow_index==8200)
			g_clip.close();
		else if (flow_index == 8300) {
			PlayBGM("..\\rsc\\bgm4.wav");
			createEnemy.SetSpeed(5, 0, 5);
			createEnemy.Pattern1(1350, 200, 12, 12, 150000, 1, 8, this);
		}
		
		else if (flow_index>=8300 && flow_index<300000 && max_enemy==0)
			flow_index=300000;
		
		else if (flow_index>=300100 && flow_index <=300370) 
			ShowStageClear();
		else if (flow_index>=300500) {
			flow_index=-200;
			level++;
			ShowMessageFlag=true;
			g_clip.close();
			PlayBGM("..\\rsc\\bgm3.wav");
			SetBackGround();
			
		}
	}
	
	void Flow4() {
		if (flow_index==100) {
			createEnemy.SetSpeed(10,0,5);
			createEnemy.Pattern3(1449,225,1,3,40000,level,1,this);
			createEnemy.Pattern3(1449,375,1,3,40000,level,1,this);
		
		}
		if (max_enemy!=0)
			flow_index=200;
		if (flow_index==500) {
			createEnemy.SetSpeed(10,0,5);
			createEnemy.Pattern1(1449,225,1,4,40000,level,1,this);
			createEnemy.Pattern1(1449,375,1,4,40000,level,1,this);
		
		}
	}
}



