
class Bomb {
	public double enemyBullet_X, enemyBullet_Y;
	public double bulletSpeed_X, bulletSpeed_Y;
	public int BulletSpeed,count;
	public int fragment,explode,delay,init_explode,init_delay;
	public int explodePattern;
	public int flag_speed;
	Bomb(double X, double Y, int bullet_Speed, int Angle) {
		bulletSpeed_Y = (double)bullet_Speed*Math.sin(Angle*Math.PI/180);
		bulletSpeed_X = Math.sqrt(bullet_Speed*bullet_Speed-bulletSpeed_Y*bulletSpeed_Y);
		enemyBullet_X = X;
		enemyBullet_Y = Y;
		BulletSpeed = bullet_Speed;
		count = 70;
		fragment = 50;
		explode=1;
		delay=0;
		init_explode = 1;
		init_delay = 10;
	}
	
	void SetUpBomb(int Explode,int Delay, int Fragment,int Flag_speed, int ExplodePattern) {
		explode = Explode;
		init_explode = Explode;
		init_delay = Delay;
		fragment = Fragment;
		explodePattern = ExplodePattern;
		flag_speed = Flag_speed;
	}
	
}

class BoomEffect {
	public int boomIndex;
	public int boom_X, boom_Y;
	public int width, height;
	BoomEffect(int X, int Y) {
		boom_X = X-26;
		boom_Y = Y-30;
		width = 100;
		height = 100;
	}
	BoomEffect(int X, int Y,int Width, int Height) {
		width = Width;
		height = Height;
		boom_X = X-width/3;
		boom_Y = Y-(int)((double)height/2.5);
	}
}