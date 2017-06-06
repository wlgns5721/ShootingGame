
class Enemy {
	public double enemy_X, enemy_Y, bullet_X;
	public int HP,attack_Delay,reload_Delay, numberOfBullet,Delay,bullet_speed,score;
	public int width, height;
	public boolean attack_Flag;
	public int move_Limit,fire_count;
	public double speed_X, speed_Y;
	public int attack_Pattern, move_Pattern, move_index,image_number,max_HP,temp,level=1;
	Enemy(double X, double Y, int hp, double Speed_X, double Speed_Y,int move_limit,int Attack_Pattern, int Move_Pattern, int Bullet_speed,int Image_number) {
		enemy_X = X;
		enemy_Y = Y;
		HP = hp;
		max_HP = hp;
		attack_Flag = false;
		move_Limit = move_limit;
		switch (Image_number){
		case 0:
			width = 50;
			height = 23;
			score = 170;
			break;
		case 1:
			width = 100;
			height = 56;
			score = 170;
			break;
		case 2:
			width = 66;
			height = 23;
			score = 240;
			break;
		case 3:
			width = 100;
			height = 83;
			score = 300;
			break;
		case 4:
			width = 100;
			height = 56;
			score = 460;
			break;
		case 5:
			width = 64;
			height = 43;
			score = 620;
			break;
		case 6:
			width = 64;
			height = 43;
			score = 10000;
			break;
		case 7:
			width = 200;
			height = 115;
			score = 20000;
			break;
		case 8:
			width = 244;
			height = 312;
			score = 30000;
			break;
		}
		speed_X = Speed_X;
		speed_Y = Speed_Y;
		attack_Pattern = Attack_Pattern;
		fire_count = 0;
		Delay = 100;
		move_index = 0;
		move_Pattern = Move_Pattern;
		bullet_speed = Bullet_speed;
		image_number = Image_number;
		temp = 0;
	}
	
	void SetBullet_X() {
		bullet_X = enemy_X - 10;
	}
	
	void SetAttackDelay(int Attack_Delay, int Reload,int NumberOfBullet) {
		attack_Delay = Attack_Delay;
		reload_Delay = Reload;
		numberOfBullet = NumberOfBullet;
	}
	
	
}


class CreateEnemy {
	private double move_speed_X=5,move_speed_Y=0;
	private int bullet_speed=5;
	
	void SetSpeed(double Move_speed_X, double Move_speed_Y, int Bullet_speed) {
		move_speed_X = Move_speed_X;
		move_speed_Y = Move_speed_Y;
		bullet_speed = Bullet_speed;
	}
	//지정된 위치에 몹 생성
	void Pattern1(int X, int Y, int Attack_Pattern, int Move_Pattern, int HP, int Level, int Image,game Game) {
		Enemy enemy = new Enemy(X,Y,HP,move_speed_X,move_speed_Y,1200,Attack_Pattern,Move_Pattern,bullet_speed,Image);
		SetAttackPattern(Attack_Pattern, Level,enemy);
		Game.enemyList.add(enemy);
		Game.max_enemy++;
	}
	
	
	void Pattern2(int X, int Y, int Attack_Pattern, int Move_Pattern, int HP, int Level, int Image, game Game) {
		Enemy enemy = new Enemy(X,Y,HP,move_speed_X,move_speed_Y,1100+(int)(Math.random()*300),Attack_Pattern,Move_Pattern,bullet_speed,Image);
		SetAttackPattern(Attack_Pattern, Level,enemy);
		Game.enemyList.add(enemy);
		Game.max_enemy++;
	}
	void Pattern3(int X, int Y, int Attack_Pattern, int Move_Pattern, int HP, int Level, int Image, game Game) {
		Enemy enemy = new Enemy(X,Y,HP,move_speed_X,move_speed_Y,1100+(int)(Math.random()*300),Attack_Pattern,Move_Pattern,bullet_speed,Image);
		SetAttackPattern(Attack_Pattern, Level,enemy);
		enemy.Delay = (int)(10*(Math.random()*10));
		Game.enemyList.add(enemy);
		Game.max_enemy++;
	}
	void CreateBoss(int X, int Y, int Attack_Pattern, int Move_Pattern, int HP, int Level, int Image, game Game) {
		Enemy enemy = new Enemy(X,Y,HP,move_speed_X,move_speed_Y,1100,Attack_Pattern,Move_Pattern,bullet_speed,Image);
		SetAttackPattern(Attack_Pattern, Level,enemy);
		switch(Image) {
		case 6:
			enemy.width = 200;
			enemy.height = 139;
			break;
		case 7:
			enemy.width = 200;
			enemy.height = 115;
			break;
		case 8:
			enemy.width = 130;
			enemy.height = 116;
			break;
		}
		
		Game.enemyList.add(enemy);
		Game.max_enemy++;
	}
	
	void SetAttackPattern(int attack_Pattern, int Level ,Enemy enemy) {
		int attack_delay=0,reload_delay=0, numberOfBullet=0;
		if (attack_Pattern==1) {
			enemy.level = Level;
			switch (Level) {
			case 1:
				attack_delay = 400;
				reload_delay = 400;
				numberOfBullet = 1;
				break;
			case 2:
				attack_delay = 200;
				reload_delay = 400;
				numberOfBullet = 1;
				break;
			case 3:
				attack_delay = 100;
				reload_delay = 500;
				numberOfBullet = 3;
				break;
			case 4:
				attack_delay = 50;
				reload_delay = 300;
				numberOfBullet = 3;
				break;
			default:
				attack_delay = 50;
				reload_delay = 100;
				numberOfBullet = 5;
				break;
			}
			
			enemy.SetAttackDelay(attack_delay, reload_delay, numberOfBullet);
		}
		else if (attack_Pattern==2) {
			switch (Level) {
			case 1:
				attack_delay = 500;
				reload_delay = 500;
				numberOfBullet = 1;
				break;
			case 2:
				attack_delay = 50;
				reload_delay = 500;
				numberOfBullet = 2;
				break;
			case 3:
				attack_delay = 10;
				reload_delay = 500;
				numberOfBullet = 4;
				break;
			case 4:
				attack_delay = 10;
				reload_delay = 400;
				numberOfBullet = 8;
				break;
			default:
				attack_delay = 10;
				reload_delay = 200;
				numberOfBullet = 2;
				break;
			}			
			enemy.SetAttackDelay(attack_delay, reload_delay, numberOfBullet);
			
		}
		else if (attack_Pattern==3) {
			switch (Level) {
			case 1:
				attack_delay = 50;
				reload_delay = 500;
				numberOfBullet = 8;
				break;
			case 2:
				attack_delay = 10;
				reload_delay = 500;
				numberOfBullet = 16;
				break;
			case 3:
				attack_delay = 10;
				reload_delay = 500;
				numberOfBullet = 32;
				break;
			case 4:
				attack_delay = 10;
				reload_delay = 400;
				numberOfBullet = 64;
				break;
			default:
				attack_delay = 10;
				reload_delay = 150;
				numberOfBullet = 100;
				break;
			}			
			enemy.SetAttackDelay(attack_delay, reload_delay, numberOfBullet);
			 
		}
		else if (attack_Pattern==4) {
			switch (Level) {
			case 1:
				attack_delay = 100;
				reload_delay = 1000;
				numberOfBullet = 4;
				break;
			case 2:
				attack_delay = 100;
				reload_delay = 800;
				numberOfBullet = 6;
				break;
			case 3:
				attack_delay = 50;
				reload_delay = 800;
				numberOfBullet = 8;
				break;
			case 4:
				attack_delay = 30;
				reload_delay = 500;
				numberOfBullet = 8;
				break;
			default:
				attack_delay = 20;
				reload_delay = 300;
				numberOfBullet = 15;
				break;
			}			
			enemy.SetAttackDelay(attack_delay, reload_delay, numberOfBullet);
			
		}
		
		else if (attack_Pattern==5) {
			switch (Level) {
			case 1:
				attack_delay = 100;
				reload_delay = 1500;
				numberOfBullet = 1;
				break;
			case 2:
				attack_delay = 100;
				reload_delay = 700;
				numberOfBullet = 1;
				break;
			case 3:
				attack_delay = 200;
				reload_delay = 1200;
				numberOfBullet = 2;
				break;
			case 4:
				attack_delay = 100;
				reload_delay = 500;
				numberOfBullet = 3;
				break;
			default:
				attack_delay = 50;
				reload_delay = 300;
				numberOfBullet = 5;
				break;
			}			
			enemy.SetAttackDelay(attack_delay, reload_delay, numberOfBullet);			
		}
		
		else if (attack_Pattern==6) {
			switch (Level) {
			case 1:
				attack_delay = 100;
				reload_delay = 1500;
				numberOfBullet = 1;
				break;
			case 2:
				attack_delay = 100;
				reload_delay = 700;
				numberOfBullet = 1;
				break;
			case 3:
				attack_delay = 200;
				reload_delay = 1200;
				numberOfBullet = 2;
				break;
			case 4:
				attack_delay = 100;
				reload_delay = 500;
				numberOfBullet = 3;
				break;
			default:
				attack_delay = 50;
				reload_delay = 300;
				numberOfBullet = 5;
				break;
			}			
			enemy.SetAttackDelay(attack_delay, reload_delay, numberOfBullet);			
		}
		else {
			enemy.SetAttackDelay(0,0,0);
		}
			
	}
	
}
