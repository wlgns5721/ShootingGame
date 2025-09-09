class Bullet {
	public int bullets_X, bullets_Y;
	public int width,height,damage;
	Bullet(int X, int Y,int Bullet_level, Game game) {
		bullets_X = X;
		width = 26;
		switch (((Bullet_level-1)/4+1)) {
		case 1:
			bullets_Y = Y;
			height = 16;
			damage = 10;
			game.maxCount = 2;
			game.attackDelay = 5;
			break;
		case 2:
			bullets_Y = Y-5;
			height = 25;
			damage = 15;
			game.maxCount = 3;
			game.attackDelay = 5;
			break;
		case 3:
			bullets_Y = Y-10;
			height = 36;
			damage = 20;
			game.maxCount = 4;
			game.attackDelay = 5;
			break;
		case 4:
			bullets_Y = Y-15;
			height = 49;
			damage = 30;
			game.maxCount = 4;
			game.attackDelay = 5;
			break;
		default:
			bullets_Y = Y-20;
			height = 61;
			damage = 40;
			game.maxCount = 6;
			game.attackDelay = 3;
			break;
		}
	}
	
}


class EnemyBullet {
	public double enemyBullet_X, enemyBullet_Y;
	public double bulletSpeed_X, bulletSpeed_Y,angle;
	public int BulletSpeed,range_type;
	EnemyBullet(double X, double Y, int bullet_Speed, double Angle) {
		bulletSpeed_Y = (double)bullet_Speed*Math.sin(Angle*Math.PI/180);
		bulletSpeed_X = Math.sqrt(bullet_Speed*bullet_Speed-bulletSpeed_Y*bulletSpeed_Y);
		enemyBullet_X = X;
		enemyBullet_Y = Y;
		BulletSpeed = bullet_Speed;
		angle = Angle;
		range_type = 1;
	}
	
	void SetRange(int bullet_speed) {
		int temp = (int)(Math.random()*2);
		switch (range_type) {
		case 1:
			bulletSpeed_Y = -bullet_speed+(Math.random()*bullet_speed*2);
			break;		
		case 2:
			bulletSpeed_Y = -bullet_speed/2+(Math.random()*bullet_speed);
			break;
		}
		switch (temp) {
		case 0: 
			bulletSpeed_X = -Math.sqrt(bullet_speed*bullet_speed-bulletSpeed_Y*bulletSpeed_Y);
			break;
		case 1:
			bulletSpeed_X = Math.sqrt(bullet_speed*bullet_speed-bulletSpeed_Y*bulletSpeed_Y);
			break;
		}
	} 
	
	void Range2(int bullet_speed) {
		int temp = (int)(Math.random()*2);
		bulletSpeed_Y = -bullet_speed/2+(Math.random()*bullet_speed);
		switch (temp) {
		case 0: 
			bulletSpeed_X = -Math.sqrt(bullet_speed*bullet_speed-bulletSpeed_Y*bulletSpeed_Y);
			break;
		case 1:
			bulletSpeed_X = Math.sqrt(bullet_speed*bullet_speed-bulletSpeed_Y*bulletSpeed_Y);
			break;
		}
	}
}

class SkillBullet {
	public double skillBullet_X, skillBullet_Y;
	public double bulletSpeed_X, bulletSpeed_Y;
	public int width,height,damage;
	public int launch_amount,numberOfBullet,delay;
	SkillBullet(int X, int Y,int Damage) {
		skillBullet_X = (double)X + 20;
		skillBullet_Y = (double)Y;
		bulletSpeed_Y = -5.0 + (Math.random()*10);
		bulletSpeed_X = Math.sqrt(100-bulletSpeed_Y*bulletSpeed_Y);
		width = 13;
		height = 13;
		damage = Damage;
	}
}
