
class Enemy {
	public double enemy_X, enemy_Y, bullet_X;
	public int HP,attackDelay,reloadDelay, numberOfBullet,delay,bulletSpeed,score;
	public int width, height;
	public boolean attack_Flag;
	public int moveLimit,fireCount;
	public double speed_X, speed_Y;
	public int attackPattern, movePattern, moveIndex,imageNumber,maxHP,temp,level=1;
	Enemy(double X, double Y, int hp, double speed_X, double speed_Y,int moveLimit,int attackPattern, int movePattern, int bulletSpeed,int imageNumber) {
		this.enemy_X = X;
		this.enemy_Y = Y;
		this.HP = hp;
		this.maxHP = hp;
		this.attack_Flag = false;
		this.moveLimit = moveLimit;
		switch (imageNumber){
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
		this.speed_X = speed_X;
		this.speed_Y = speed_Y;
		this.attackPattern = attackPattern;
		this.fireCount = 0;
		this.delay = 100;
		this.moveIndex = 0;
		this.movePattern = movePattern;
		this.bulletSpeed = bulletSpeed;
		this.imageNumber = imageNumber;
		this.temp = 0;
	}
	
	void setBullet_X() {
		bullet_X = enemy_X - 10;
	}
	
	void setAttackDelay(int attackDelay, int reload,int numberOfBullet) {
		this.attackDelay = attackDelay;
		this.reloadDelay = reload;
		this.numberOfBullet = numberOfBullet;
	}
	
	
}


class CreateEnemy {
	private double moveSpeed_X=5,moveSpeed_Y=0;
	private int bulletSpeed=5;
	
	void SetSpeed(double moveSpeed_X, double moveSpeed_Y, int bulletSpeed) {
		this.moveSpeed_X = moveSpeed_X;
		this.moveSpeed_Y = moveSpeed_Y;
		this.bulletSpeed = bulletSpeed;
	}
	//������ ��ġ�� �� ��
	void pattern1(int x, int y, int attackPattern, int movePattern, int hp, int level, int image,Game game) {
		Enemy enemy = new Enemy(x,y,hp,moveSpeed_X,moveSpeed_Y,1200,attackPattern,movePattern,bulletSpeed,image);
		setAttackPattern(attackPattern, level,enemy);
		game.enemyList.add(enemy);
		game.maxEnemy++;
	}
	
	
	void pattern2(int x, int y, int attackPattern, int movePattern, int hp, int level, int image, Game game) {
		Enemy enemy = new Enemy(x,y,hp,moveSpeed_X,moveSpeed_Y,1100+(int)(Math.random()*300),attackPattern,movePattern,bulletSpeed,image);
		setAttackPattern(attackPattern, level,enemy);
		game.enemyList.add(enemy);
		game.maxEnemy++;
	}
	void pattern3(int x, int y, int attackPattern, int movePattern, int hp, int level, int image, Game game) {
		Enemy enemy = new Enemy(x,y,hp,moveSpeed_X,moveSpeed_Y,1100+(int)(Math.random()*300),attackPattern,movePattern,bulletSpeed,image);
		setAttackPattern(attackPattern, level,enemy);
		enemy.delay = (int)(10*(Math.random()*10));
		game.enemyList.add(enemy);
		game.maxEnemy++;
	}
	void createBoss(int x, int y, int attackPattern, int movePattern, int hp, int level, int image, Game game) {
		Enemy enemy = new Enemy(x,y,hp,moveSpeed_X,moveSpeed_Y,1100,attackPattern,movePattern,bulletSpeed,image);
		setAttackPattern(attackPattern, level,enemy);
		switch(image) {
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
		
		game.enemyList.add(enemy);
		game.maxEnemy++;
	}
	
	void setAttackPattern(int attackPattern, int level ,Enemy enemy) {
		int attackDelay=0,reloadDelay=0, numberOfBullet=0;
		if (attackPattern==1) {
			enemy.level = level;
			switch (level) {
			case 1:
				attackDelay = 400;
				reloadDelay = 400;
				numberOfBullet = 1;
				break;
			case 2:
				attackDelay = 200;
				reloadDelay = 400;
				numberOfBullet = 1;
				break;
			case 3:
				attackDelay = 100;
				reloadDelay = 500;
				numberOfBullet = 3;
				break;
			case 4:
				attackDelay = 50;
				reloadDelay = 300;
				numberOfBullet = 3;
				break;
			default:
				attackDelay = 50;
				reloadDelay = 100;
				numberOfBullet = 5;
				break;
			}
			
			enemy.setAttackDelay(attackDelay, reloadDelay, numberOfBullet);
		}
		else if (attackPattern==2) {
			switch (level) {
			case 1:
				attackDelay = 500;
				reloadDelay = 500;
				numberOfBullet = 1;
				break;
			case 2:
				attackDelay = 50;
				reloadDelay = 500;
				numberOfBullet = 2;
				break;
			case 3:
				attackDelay = 10;
				reloadDelay = 500;
				numberOfBullet = 4;
				break;
			case 4:
				attackDelay = 10;
				reloadDelay = 400;
				numberOfBullet = 8;
				break;
			default:
				attackDelay = 10;
				reloadDelay = 200;
				numberOfBullet = 2;
				break;
			}			
			enemy.setAttackDelay(attackDelay, reloadDelay, numberOfBullet);
			
		}
		else if (attackPattern==3) {
			switch (level) {
			case 1:
				attackDelay = 50;
				reloadDelay = 500;
				numberOfBullet = 8;
				break;
			case 2:
				attackDelay = 10;
				reloadDelay = 500;
				numberOfBullet = 16;
				break;
			case 3:
				attackDelay = 10;
				reloadDelay = 500;
				numberOfBullet = 32;
				break;
			case 4:
				attackDelay = 10;
				reloadDelay = 400;
				numberOfBullet = 64;
				break;
			default:
				attackDelay = 10;
				reloadDelay = 150;
				numberOfBullet = 100;
				break;
			}			
			enemy.setAttackDelay(attackDelay, reloadDelay, numberOfBullet);
			 
		}
		else if (attackPattern==4) {
			switch (level) {
			case 1:
				attackDelay = 100;
				reloadDelay = 1000;
				numberOfBullet = 4;
				break;
			case 2:
				attackDelay = 100;
				reloadDelay = 800;
				numberOfBullet = 6;
				break;
			case 3:
				attackDelay = 50;
				reloadDelay = 800;
				numberOfBullet = 8;
				break;
			case 4:
				attackDelay = 30;
				reloadDelay = 500;
				numberOfBullet = 8;
				break;
			default:
				attackDelay = 20;
				reloadDelay = 300;
				numberOfBullet = 15;
				break;
			}			
			enemy.setAttackDelay(attackDelay, reloadDelay, numberOfBullet);
			
		}
		
		else if (attackPattern==5) {
			switch (level) {
			case 1:
				attackDelay = 100;
				reloadDelay = 1500;
				numberOfBullet = 1;
				break;
			case 2:
				attackDelay = 100;
				reloadDelay = 700;
				numberOfBullet = 1;
				break;
			case 3:
				attackDelay = 200;
				reloadDelay = 1200;
				numberOfBullet = 2;
				break;
			case 4:
				attackDelay = 100;
				reloadDelay = 500;
				numberOfBullet = 3;
				break;
			default:
				attackDelay = 50;
				reloadDelay = 300;
				numberOfBullet = 5;
				break;
			}			
			enemy.setAttackDelay(attackDelay, reloadDelay, numberOfBullet);			
		}
		
		else if (attackPattern==6) {
			switch (level) {
			case 1:
				attackDelay = 100;
				reloadDelay = 1500;
				numberOfBullet = 1;
				break;
			case 2:
				attackDelay = 100;
				reloadDelay = 700;
				numberOfBullet = 1;
				break;
			case 3:
				attackDelay = 200;
				reloadDelay = 1200;
				numberOfBullet = 2;
				break;
			case 4:
				attackDelay = 100;
				reloadDelay = 500;
				numberOfBullet = 3;
				break;
			default:
				attackDelay = 50;
				reloadDelay = 300;
				numberOfBullet = 5;
				break;
			}			
			enemy.setAttackDelay(attackDelay, reloadDelay, numberOfBullet);			
		}
		else {
			enemy.setAttackDelay(0,0,0);
		}
			
	}
	
}
