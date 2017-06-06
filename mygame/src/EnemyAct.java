import java.awt.Image;
import java.awt.Polygon;

class EnemyAct {
	int attack_flow = 0;
	double boss_index = 0;
	void MoveEnemy(game Game) {
		Enemy buf;
		MovePattern movePattern = new MovePattern();
		for (int i=0; i<Game.enemyList.size(); i++) {
			buf = (Enemy)Game.enemyList.elementAt(i);
			switch (buf.move_Pattern) {
			case 1:
				movePattern.Pattern1(Game, buf);
				break;
			case 2:
				movePattern.Pattern2(Game, buf);
				break;
			case 3:
				movePattern.Pattern3(Game, buf);
				break;
			case 4:
				movePattern.Pattern4(Game, buf);
				break;
			case 5:
				movePattern.Pattern5(Game, buf);
				break;
			case 6:
				movePattern.Pattern6(Game, buf);
				break;
			case 7:
				movePattern.Pattern7(Game, buf);
				break;
			case 10:
				movePattern.BossPattern(Game, buf);
				break;
			case 12:
				movePattern.BossPattern3(Game,buf);
			}
			buf.enemy_X-=buf.speed_X;
			buf.enemy_Y+=buf.speed_Y;
			Game.grap.drawImage((Image)Game.enemyImageList.elementAt(buf.image_number), (int)buf.enemy_X, (int)buf.enemy_Y,Game);
			if (buf.enemy_X <-100 || buf.enemy_Y<-100 || buf.enemy_Y > 750 || buf.enemy_X > 1450) {
				Game.enemyList.remove(i);
				Game.max_enemy--;
			}
			
		}
	}
	
	void EnemyAttack(game Game) {
		for (int i=0; i<Game.enemyBulletList.size(); i++) {  
			EnemyBullet buff = (EnemyBullet)Game.enemyBulletList.elementAt(i);
			Game.grap.drawImage(Game.enemyBullet,(int)buff.enemyBullet_X, (int)buff.enemyBullet_Y, Game);
			buff.enemyBullet_X -=buff.bulletSpeed_X;
			buff.enemyBullet_Y -=buff.bulletSpeed_Y;
			if (CollisionCheck(Game.X,Game.Y,Game) && Game.Moojuk_Flag ==false ) 
				Game.enemyBulletList.remove(i);
			else if (buff.enemyBullet_X < -20 || buff.enemyBullet_Y < -20 || buff.enemyBullet_Y > 680 || buff.enemyBullet_X > 1700)
				Game.enemyBulletList.remove(i);
		}
		Bomb(Game);
		
		for (int i=0; i<Game.enemyList.size(); i++) {
			Enemy buf = (Enemy)Game.enemyList.elementAt(i);
			double x = buf.bullet_X;
			double y = buf.enemy_Y + 10;
			switch (buf.attack_Pattern) {
			case 10:
				boss1_attack_Pattern(Game,x,y,buf,buf.bullet_speed);
				break;
			case 11:
				boss2_attack_Pattern(Game,x,y,buf,buf.bullet_speed);
				break;
			case 12:
				boss3_attack_Pattern(Game,x,y,buf);
			}
			if (buf.enemy_X <= 1350 && buf.attack_Flag ==true) {
				buf.attack_Flag = false;
				buf.Delay = buf.attack_Delay;
				buf.fire_count++;
				buf.SetBullet_X();
				x = buf.bullet_X;
				y = buf.enemy_Y + 10;
				switch (buf.attack_Pattern) {
				case 1:
					attack_Pattern1(Game,x,y,buf.bullet_speed,buf.level);
					break;
				case 2:
					attack_Pattern2(Game,x,y,(double)Game.X,(double)Game.Y,buf.bullet_speed);
					break;
				case 3:
					attack_Pattern3(Game,x,y,buf,buf.bullet_speed);
					break;
				case 4:
					attack_Pattern4(Game,x,y,buf,buf.bullet_speed);
					break;
				case 5:
					attack_BombPattern2(Game,x,y,buf,buf.bullet_speed);
					break;
				case 6:
					attack_BombPattern1(Game,x,y,buf,buf.bullet_speed);
					break;
				
				}
				
				if (buf.fire_count>=buf.numberOfBullet) {
					buf.Delay = buf.reload_Delay;
					buf.fire_count=0;
				}
			}
			else if (buf.enemy_X < 1300) {
				buf.Delay -= 5;
				if (buf.Delay <= 0)
					buf.attack_Flag=true;
			}
		}
		
	}
	
	void Bomb(game Game) {
		for (int i=0; i<Game.bombList.size(); i++) {
			Bomb buf = (Bomb)Game.bombList.elementAt(i);
			switch (buf.explodePattern) {
			case 1:
				bombPattern1(i,buf,1,Game);
				break;
			case 2:
				bombPattern1(i,buf,2,Game);
				break;
			}
		}
	}
	
	boolean CollisionCheck(int Character_X, int Character_Y, game Game) {
		int [] xpoint = {Character_X, Character_X+50, Character_X+50, Character_X};
		int [] ypoint = {Character_Y+3, Character_Y+3, Character_Y+30, Character_Y+30 };
		Polygon p = new Polygon(xpoint, ypoint, 4);
		for (int i=0; i<Game.enemyBulletList.size(); i++) {
			EnemyBullet enemyBullet = (EnemyBullet)Game.enemyBulletList.elementAt(i);
			if (p.intersects(enemyBullet.enemyBullet_X, enemyBullet.enemyBullet_Y, 13, 13) && Game.Moojuk_Flag ==false) {
				Game.Death_Flag = true;
				Game.PlaySound("..\\rsc\\CharacterDeath.wav");
				BoomEffect boomEffect = new BoomEffect(Game.X,Game.Y);
				Game.boomList.add(boomEffect);
				Game.X = -5000;
				Game.shoot.skill_launch_amount=0;
				Game.shoot.skill_fireDelay=0;
				Game.SkillFlag = false;
				return true;
			}
		}
		return false;
	}
	//부채꼴로 동시에 발사
	void attack_Pattern1(game Game, double x, double y,int speed,int level) {
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
			Game.enemyBulletList.add(enemyBullet);
		}
	}
	//유저 방향으로 발사
	void attack_Pattern2(game Game, double x, double y, double Character_x,double Character_y, int speed) {
		double angle = Math.atan2(y-Character_y, x-Character_x)*180/Math.PI;
		EnemyBullet enemyBullet = new EnemyBullet(x,y,speed,(int)angle);
		Game.enemyBulletList.add(enemyBullet);
	}
	//흩뿌리는 방식
	void attack_Pattern3(game Game, double x, double y,Enemy enemy, int speed) {
		EnemyBullet enemyBullet = new EnemyBullet(x,y+25,speed,enemy.numberOfBullet*5-(int)enemy.fire_count*10+enemy.fire_count/8);
		Game.enemyBulletList.add(enemyBullet);
	}
	
	//부채꼴로 지그재그 발사
	void attack_Pattern4(game Game, double x, double y,Enemy enemy, int speed) {
		for (int s=82; s>-90; s-=15 ) {
			EnemyBullet enemyBullet = new EnemyBullet(x,y,speed,s+(enemy.fire_count%2)*8);
			Game.enemyBulletList.add(enemyBullet);
		}
	}
	//파편이 동서 방향에 집중된 폭탄
	void attack_BombPattern1(game Game, double x, double y, Enemy enemy, int flag_speed) {
			Bomb bomb = new Bomb(x,y,15,0);
			bomb.SetUpBomb(1,100,200,flag_speed,2);
			Game.bombList.add(bomb);
			Game.PlaySound("..\\rsc\\bomb1.wav");
		}
	//전방향 파편 폭탄
	void attack_BombPattern2(game Game, double x, double y, Enemy enemy, int flag_speed) {
		Bomb bomb = new Bomb(x,y,15,0);
		bomb.SetUpBomb(3,5,20,flag_speed,1);
		Game.bombList.add(bomb);
		Game.PlaySound("..\\rsc\\bomb2.wav");
		
	}
	
	void boss1_attack_Pattern(game Game, double x, double y, Enemy enemy, int flag_speed) {
		if (Game.flow_index==5400) 
			enemy.SetAttackDelay(1000, 1000, 1);
		if (Game.flow_index>=5400 && Game.flow_index < 5600 && enemy.attack_Flag==true) 
			attack_BombPattern2(Game,x,y*19/15,enemy,flag_speed);	
		
		else if (Game.flow_index>=5600 && Game.flow_index < 5800 && enemy.attack_Flag==true) {
			Game.createEnemy.SetAttackPattern(2, 2, enemy);
			enemy.SetAttackDelay(70, 500, 3);
			attack_Pattern2(Game,x+50,y+20,Game.X,Game.Y,15);
			attack_Pattern2(Game,x+120,y,Game.X,Game.Y,15);
			attack_Pattern2(Game,x,y*19/15,Game.X,Game.Y,15);
			attack_Pattern2(Game,x+50,y+110,Game.X,Game.Y,15);
			attack_Pattern2(Game,x+150,y+130,Game.X,Game.Y,15);
		}
		else if (Game.flow_index == 5800)
			Game.flow_index = 5399;
		
	}
	
	void boss2_attack_Pattern(game Game, double x, double y, Enemy enemy, int flag_speed) {
		if (Game.flow_index==5800) {
			enemy.Delay = 0;
			enemy.SetAttackDelay(50, 300, 8);
		}
		if (Game.flow_index>=5800 && Game.flow_index < 5900 && enemy.attack_Flag==true) { 
			attack_Pattern1(Game,x,y*19/15,10,3);
			attack_Pattern1(Game,x,y*17/15,10,3);
		}
		
		else if (Game.flow_index>=5900 && Game.flow_index < 6150 && enemy.attack_Flag==true) {
			Game.createEnemy.SetAttackPattern(2, 2, enemy);
			enemy.SetAttackDelay(70, 300, 3);
			attack_Pattern2(Game,x+50,y+20,Game.X,Game.Y,15);
			attack_Pattern2(Game,x+120,y,Game.X,Game.Y,15);
			attack_Pattern2(Game,x,y*19/15,Game.X,Game.Y,15);
			attack_Pattern2(Game,x+50,y+110,Game.X,Game.Y,15);
			attack_Pattern2(Game,x+150,y+130,Game.X,Game.Y,15);
		}
		else if (Game.flow_index==6150)
			enemy.SetAttackDelay(50, 300, 8);
		else if (Game.flow_index >=6150 && Game.flow_index <6250 && enemy.attack_Flag==true) {
			attack_Pattern1(Game,x,y*19/15,10,3);
			attack_Pattern1(Game,x,y*17/15,10,3);
		}
		
		else if (Game.flow_index == 6250) {
			attack_BombPattern1(Game,x,y,enemy,10);
		}
	
		else if (Game.flow_index == 6400)
			Game.flow_index = 5799;
		
		
	}
	
	void boss3_attack_Pattern(game Game, double x, double y, Enemy enemy) {
		if (enemy.HP<75000 && !Game.pase_2)
			Game.pase_2=true;
		if (Game.flow_index%100==0 && (Game.flow_index<8600 || Game.flow_index>8800) &&( Game.flow_index<9500 || Game.flow_index>9700)) {
			if (Game.pase_2)
			attack_Pattern1(Game,x+233,y+140,10,3);
		}
		if (Game.flow_index==8400)
			enemy.SetAttackDelay(10, 300, 36);
		else if (Game.flow_index>8400 && Game.flow_index<8600 && enemy.attack_Flag==true) {
			attack_Pattern2(Game,x+30,y+10,Game.X,Game.Y,25);
			attack_Pattern2(Game,x+20,y+140,Game.X,Game.Y,25);
			attack_Pattern2(Game,x+30,y+270,Game.X,Game.Y,25);
		}
		else if (Game.flow_index==8600)
			Game.enemyAct.boss_index=60;
		else if (Game.flow_index>=8600 && Game.flow_index < 8800 && Game.flow_index%2==0) {
			EnemyBullet enemyBullet = new EnemyBullet(x+20,y+140,10,boss_index);
			Game.enemyBulletList.add(enemyBullet);
			boss_index-=1.4;
		}
		else if (Game.flow_index==8900) 
			attack_BombPattern1(Game,x+30,y+10,enemy,10); 
		else if (Game.flow_index==8910)
			attack_BombPattern1(Game,x+30,y+270,enemy,10);
		else if (Game.flow_index==8920) {
			Bomb bomb = new Bomb(x+20,y+140,15,0);
			bomb.SetUpBomb(3,5,70,7,1);
			bomb.count = 140;
			Game.bombList.add(bomb);
			Game.PlaySound("..\\rsc\\bomb1.wav");
		}
		
		else if (Game.flow_index==9150) {
			enemy.SetAttackDelay(10, 300, 36);
			boss_index=1;
		}
		else if (Game.flow_index>=9150 && Game.flow_index<9300 && Game.flow_index%5==0) {
			EnemyBullet enemyBullet = new EnemyBullet(x+30,y+10,10,60-boss_index);
			Game.enemyBulletList.add(enemyBullet);
			EnemyBullet enemyBullet2 = new EnemyBullet(x+30,y+270,10,-60+boss_index);
			Game.enemyBulletList.add(enemyBullet2);
			boss_index+=10;
		}
		else if (Game.flow_index==9320)
			attack_BombPattern1(Game,x+20,y+140,enemy,10);
		else if (Game.flow_index == 9500)
			enemy.SetAttackDelay(50, 300, 8);
		else if (Game.flow_index >=9500 && Game.flow_index < 9650 && Game.flow_index%10==0) {
			attack_Pattern1(Game,x+30,y+10,8,3);
			attack_Pattern1(Game,x+20,y+140,8,3);
			attack_Pattern1(Game,x+30,y+270,8,3);
		}
		else if (Game.flow_index==9750)
			Game.flow_index=8399;
	}
	void bombPattern1(int i, Bomb buf, int range_type, game Game) {
		buf.count--;
		buf.enemyBullet_X-=buf.bulletSpeed_X;
		buf.enemyBullet_Y+=buf.bulletSpeed_Y;
		buf.bulletSpeed_X-=buf.bulletSpeed_X/30;
		buf.bulletSpeed_Y-=buf.bulletSpeed_Y/30;
		if (buf.explode==buf.init_explode)
			Game.grap.drawImage(Game.bombImage,(int)buf.enemyBullet_X,(int)buf.enemyBullet_Y,Game);
		if (buf.count<=0) {
			if (buf.explode==0)
				Game.bombList.remove(i);
			if (buf.delay==0) {
				if (buf.explode==buf.init_explode) {
					buf.bulletSpeed_X=0;
					buf.bulletSpeed_Y=0;
					BoomEffect boomEffect = new BoomEffect((int)buf.enemyBullet_X-15,(int)buf.enemyBullet_Y-20);
					Game.boomList.add(boomEffect);
					Game.PlaySound("..\\rsc\\bombExplode.wav");
				}
				buf.explode--;
				for (int s=0; s<buf.fragment; s++) {
					EnemyBullet enemyBullet = new EnemyBullet(buf.enemyBullet_X,buf.enemyBullet_Y,buf.BulletSpeed,0);
					enemyBullet.range_type = range_type;
					enemyBullet.SetRange(buf.flag_speed);
					Game.enemyBulletList.add(enemyBullet);
				}
				buf.delay = buf.init_delay;
			}
			buf.delay--;
		}
		
	}
}
