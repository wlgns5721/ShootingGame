import java.awt.Image;
import java.awt.Polygon;

class EnemyAct {
	int attack_flow = 0;
	double boss_index = 0;
	void MoveEnemy(Game game) {
		Enemy buf;
		MovePattern movePattern = new MovePattern();
		for (int i=0; i<game.enemyList.size(); i++) {
			buf = (Enemy)game.enemyList.elementAt(i);
			switch (buf.movePattern) {
			case 1:
				movePattern.pattern1(game, buf);
				break;
			case 2:
				movePattern.pattern2(game, buf);
				break;
			case 3:
				movePattern.pattern3(game, buf);
				break;
			case 4:
				movePattern.pattern4(game, buf);
				break;
			case 5:
				movePattern.pattern5(game, buf);
				break;
			case 6:
				movePattern.pattern6(game, buf);
				break;
			case 7:
				movePattern.pattern7(game, buf);
				break;
			case 10:
				movePattern.bossPattern(game, buf);
				break;
			case 12:
				movePattern.bossPattern3(game,buf);
			}
			buf.enemy_X-=buf.speed_X;
			buf.enemy_Y+=buf.speed_Y;
			game.grap.drawImage((Image)game.enemyImageList.elementAt(buf.imageNumber), (int)buf.enemy_X, (int)buf.enemy_Y,game);
			if (buf.enemy_X <-100 || buf.enemy_Y<-100 || buf.enemy_Y > 750 || buf.enemy_X > 1450) {
				game.enemyList.remove(i);
				game.maxEnemy--;
			}
			
		}
	}
	
	void EnemyAttack(Game game) {
		for (int i=0; i<game.enemyBulletList.size(); i++) {  
			EnemyBullet buff = (EnemyBullet)game.enemyBulletList.elementAt(i);
			game.grap.drawImage(game.enemyBullet,(int)buff.enemyBullet_X, (int)buff.enemyBullet_Y, game);
			buff.enemyBullet_X -=buff.bulletSpeed_X;
			buff.enemyBullet_Y -=buff.bulletSpeed_Y;
			if (CollisionCheck(game.x,game.y,game) && game.moojukFlag ==false ) 
				game.enemyBulletList.remove(i);
			else if (buff.enemyBullet_X < -20 || buff.enemyBullet_Y < -20 || buff.enemyBullet_Y > 680 || buff.enemyBullet_X > 1700)
				game.enemyBulletList.remove(i);
		}
		Bomb(game);
		
		for (int i=0; i<game.enemyList.size(); i++) {
			Enemy buf = (Enemy)game.enemyList.elementAt(i);
			double x = buf.bullet_X;
			double y = buf.enemy_Y + 10;
			switch (buf.attackPattern) {
			case 10:
				boss1_attack_Pattern(game,x,y,buf,buf.bulletSpeed);
				break;
			case 11:
				boss2_attack_Pattern(game,x,y,buf,buf.bulletSpeed);
				break;
			case 12:
				boss3_attack_Pattern(game,x,y,buf);
			}
			if (buf.enemy_X <= 1350 && buf.attack_Flag ==true) {
				buf.attack_Flag = false;
				buf.delay = buf.attackDelay;
				buf.fireCount++;
				buf.setBullet_X();
				x = buf.bullet_X;
				y = buf.enemy_Y + 10;
				switch (buf.attackPattern) {
				case 1:
					attack_Pattern1(game,x,y,buf.bulletSpeed,buf.level);
					break;
				case 2:
					attack_Pattern2(game,x,y,(double)game.x,(double)game.y,buf.bulletSpeed);
					break;
				case 3:
					attack_Pattern3(game,x,y,buf,buf.bulletSpeed);
					break;
				case 4:
					attack_Pattern4(game,x,y,buf,buf.bulletSpeed);
					break;
				case 5:
					attack_BombPattern2(game,x,y,buf,buf.bulletSpeed);
					break;
				case 6:
					attack_BombPattern1(game,x,y,buf,buf.bulletSpeed);
					break;
				
				}
				
				if (buf.fireCount>=buf.numberOfBullet) {
					buf.delay = buf.reloadDelay;
					buf.fireCount=0;
				}
			}
			else if (buf.enemy_X < 1300) {
				buf.delay -= 5;
				if (buf.delay <= 0)
					buf.attack_Flag=true;
			}
		}
		
	}
	
	void Bomb(Game game) {
		for (int i=0; i<game.bombList.size(); i++) {
			Bomb buf = (Bomb)game.bombList.elementAt(i);
			switch (buf.explodePattern) {
			case 1:
				bombPattern1(i,buf,1,game);
				break;
			case 2:
				bombPattern1(i,buf,2,game);
				break;
			}
		}
	}
	
	boolean CollisionCheck(int Character_X, int Character_Y, Game game) {
		int [] xpoint = {Character_X, Character_X+50, Character_X+50, Character_X};
		int [] ypoint = {Character_Y+3, Character_Y+3, Character_Y+30, Character_Y+30 };
		Polygon p = new Polygon(xpoint, ypoint, 4);
		for (int i=0; i<game.enemyBulletList.size(); i++) {
			EnemyBullet enemyBullet = (EnemyBullet)game.enemyBulletList.elementAt(i);
			if (p.intersects(enemyBullet.enemyBullet_X, enemyBullet.enemyBullet_Y, 13, 13) && game.moojukFlag ==false) {
				game.deathFlag = true;
				game.playSound("..\\rsc\\CharacterDeath.wav");
				BoomEffect boomEffect = new BoomEffect(game.x,game.y);
				game.boomList.add(boomEffect);
				game.x = -5000;
				game.shoot.skillLaunchAmount=0;
				game.shoot.skillFireDelay=0;
				game.skillFlag = false;
				return true;
			}
		}
		return false;
	}
	//��ä�÷� ���ÿ� �߻�
	void attack_Pattern1(Game game, double x, double y,int speed,int level) {
		int angle;
		switch (level) {
		case 1:
			angle = 30;
			break;
		case 2:
			angle = 20;
			break;
		case 3:
			angle = 10;
			break;
		default:
			angle = 5;
			break;
		}
		for (int s=45; s>=-45; s-=angle ) {
			EnemyBullet enemyBullet = new EnemyBullet(x,y,speed,s);
			game.enemyBulletList.add(enemyBullet);
		}
	}
	//���� �������� �߻�
	void attack_Pattern2(Game game, double x, double y, double Character_x,double Character_y, int speed) {
		double angle = Math.atan2(y-Character_y, x-Character_x)*180/Math.PI;
		EnemyBullet enemyBullet = new EnemyBullet(x,y,speed,(int)angle);
		game.enemyBulletList.add(enemyBullet);
	}
	//��Ѹ��� ���
	void attack_Pattern3(Game game, double x, double y,Enemy enemy, int speed) {
		EnemyBullet enemyBullet = new EnemyBullet(x,y+25,speed,enemy.numberOfBullet*5-(int)enemy.fireCount*10+enemy.fireCount/8);
		game.enemyBulletList.add(enemyBullet);
	}
	
	//��ä�÷� ������� �߻�
	void attack_Pattern4(Game game, double x, double y,Enemy enemy, int speed) {
		for (int s=82; s>-90; s-=15 ) {
			EnemyBullet enemyBullet = new EnemyBullet(x,y,speed,s+(enemy.fireCount%2)*8);
			game.enemyBulletList.add(enemyBullet);
		}
	}
	//������ ���� ���⿡ ���ߵ� ��ź
	void attack_BombPattern1(Game game, double x, double y, Enemy enemy, int flag_speed) {
			Bomb bomb = new Bomb(x,y,15,0);
			bomb.setUpBomb(1,100,200,flag_speed,2);
			game.bombList.add(bomb);
			game.playSound("..\\rsc\\bomb1.wav");
		}
	//����� ���� ��ź
	void attack_BombPattern2(Game game, double x, double y, Enemy enemy, int flag_speed) {
		Bomb bomb = new Bomb(x,y,15,0);
		bomb.setUpBomb(3,5,20,flag_speed,1);
		game.bombList.add(bomb);
		game.playSound("..\\rsc\\bomb2.wav");
		
	}
	
	void boss1_attack_Pattern(Game game, double x, double y, Enemy enemy, int flag_speed) {
		if (game.flowIndex==5400) 
			enemy.setAttackDelay(1000, 1000, 1);
		if (game.flowIndex>=5400 && game.flowIndex < 5600 && enemy.attack_Flag==true) 
			attack_BombPattern2(game,x,y*19/15,enemy,flag_speed);	
		
		else if (game.flowIndex>=5600 && game.flowIndex < 5800 && enemy.attack_Flag==true) {
			game.createEnemy.setAttackPattern(2, 2, enemy);
			enemy.setAttackDelay(70, 500, 3);
			attack_Pattern2(game,x+50,y+20,game.x,game.y,15);
			attack_Pattern2(game,x+120,y,game.x,game.y,15);
			attack_Pattern2(game,x,y*19/15,game.x,game.y,15);
			attack_Pattern2(game,x+50,y+110,game.x,game.y,15);
			attack_Pattern2(game,x+150,y+130,game.x,game.y,15);
		}
		else if (game.flowIndex == 5800)
			game.flowIndex = 5399;
		
	}
	
	void boss2_attack_Pattern(Game game, double x, double y, Enemy enemy, int flag_speed) {
		if (game.flowIndex==5800) {
			enemy.delay = 0;
			enemy.setAttackDelay(50, 300, 8);
		}
		if (game.flowIndex>=5800 && game.flowIndex < 5900 && enemy.attack_Flag==true) { 
			attack_Pattern1(game,x,y*19/15,10,3);
			attack_Pattern1(game,x,y*17/15,10,3);
		}
		
		else if (game.flowIndex>=5900 && game.flowIndex < 6150 && enemy.attack_Flag==true) {
			game.createEnemy.setAttackPattern(2, 2, enemy);
			enemy.setAttackDelay(70, 300, 3);
			attack_Pattern2(game,x+50,y+20,game.x,game.y,15);
			attack_Pattern2(game,x+120,y,game.x,game.y,15);
			attack_Pattern2(game,x,y*19/15,game.x,game.y,15);
			attack_Pattern2(game,x+50,y+110,game.x,game.y,15);
			attack_Pattern2(game,x+150,y+130,game.x,game.y,15);
		}
		else if (game.flowIndex==6150)
			enemy.setAttackDelay(50, 300, 8);
		else if (game.flowIndex >=6150 && game.flowIndex <6250 && enemy.attack_Flag==true) {
			attack_Pattern1(game,x,y*19/15,10,3);
			attack_Pattern1(game,x,y*17/15,10,3);
		}
		
		else if (game.flowIndex == 6250) {
			attack_BombPattern1(game,x,y,enemy,10);
		}
	
		else if (game.flowIndex == 6400)
			game.flowIndex = 5799;
		
		
	}
	
	void boss3_attack_Pattern(Game game, double x, double y, Enemy enemy) {
		if (enemy.HP<75000 && !game.pase_2)
			game.pase_2=true;
		if (game.flowIndex%100==0 && (game.flowIndex<8600 || game.flowIndex>8800) &&( game.flowIndex<9500 || game.flowIndex>9700)) {
			if (game.pase_2)
			attack_Pattern1(game,x+233,y+140,10,3);
		}
		if (game.flowIndex==8400)
			enemy.setAttackDelay(10, 300, 36);
		else if (game.flowIndex>8400 && game.flowIndex<8600 && enemy.attack_Flag==true) {
			attack_Pattern2(game,x+30,y+10,game.x,game.y,25);
			attack_Pattern2(game,x+20,y+140,game.x,game.y,25);
			attack_Pattern2(game,x+30,y+270,game.x,game.y,25);
		}
		else if (game.flowIndex==8600)
			game.enemyAct.boss_index=60;
		else if (game.flowIndex>=8600 && game.flowIndex < 8800 && game.flowIndex%2==0) {
			EnemyBullet enemyBullet = new EnemyBullet(x+20,y+140,10,boss_index);
			game.enemyBulletList.add(enemyBullet);
			boss_index-=1.4;
		}
		else if (game.flowIndex==8900) 
			attack_BombPattern1(game,x+30,y+10,enemy,10); 
		else if (game.flowIndex==8910)
			attack_BombPattern1(game,x+30,y+270,enemy,10);
		else if (game.flowIndex==8920) {
			Bomb bomb = new Bomb(x+20,y+140,15,0);
			bomb.setUpBomb(3,5,70,7,1);
			bomb.count = 140;
			game.bombList.add(bomb);
			game.playSound("..\\rsc\\bomb1.wav");
		}
		
		else if (game.flowIndex==9150) {
			enemy.setAttackDelay(10, 300, 36);
			boss_index=1;
		}
		else if (game.flowIndex>=9150 && game.flowIndex<9300 && game.flowIndex%5==0) {
			EnemyBullet enemyBullet = new EnemyBullet(x+30,y+10,10,60-boss_index);
			game.enemyBulletList.add(enemyBullet);
			EnemyBullet enemyBullet2 = new EnemyBullet(x+30,y+270,10,-60+boss_index);
			game.enemyBulletList.add(enemyBullet2);
			boss_index+=10;
		}
		else if (game.flowIndex==9320)
			attack_BombPattern1(game,x+20,y+140,enemy,10);
		else if (game.flowIndex == 9500)
			enemy.setAttackDelay(50, 300, 8);
		else if (game.flowIndex >=9500 && game.flowIndex < 9650 && game.flowIndex%10==0) {
			attack_Pattern1(game,x+30,y+10,8,3);
			attack_Pattern1(game,x+20,y+140,8,3);
			attack_Pattern1(game,x+30,y+270,8,3);
		}
		else if (game.flowIndex==9750)
			game.flowIndex=8399;
	}
	void bombPattern1(int i, Bomb buf, int rangeType, Game game) {
		buf.count--;
		buf.enemyBullet_X-=buf.bulletSpeed_X;
		buf.enemyBullet_Y+=buf.bulletSpeed_Y;
		buf.bulletSpeed_X-=buf.bulletSpeed_X/30;
		buf.bulletSpeed_Y-=buf.bulletSpeed_Y/30;
		if (buf.explode==buf.initExplode)
			game.grap.drawImage(game.bombImage,(int)buf.enemyBullet_X,(int)buf.enemyBullet_Y,game);
		if (buf.count<=0) {
			if (buf.explode==0)
				game.bombList.remove(i);
			if (buf.delay==0) {
				if (buf.explode==buf.initExplode) {
					buf.bulletSpeed_X=0;
					buf.bulletSpeed_Y=0;
					BoomEffect boomEffect = new BoomEffect((int)buf.enemyBullet_X-15,(int)buf.enemyBullet_Y-20);
					game.boomList.add(boomEffect);
					game.playSound("..\\rsc\\bombExplode.wav");
				}
				buf.explode--;
				for (int s=0; s<buf.fragment; s++) {
					EnemyBullet enemyBullet = new EnemyBullet(buf.enemyBullet_X,buf.enemyBullet_Y,buf.BulletSpeed,0);
					enemyBullet.rangeType = rangeType;
					enemyBullet.SetRange(buf.flagSpeed);
					game.enemyBulletList.add(enemyBullet);
				}
				buf.delay = buf.initDelay;
			}
			buf.delay--;
		}
		
	}
}
