
class Bomb {
	public double enemyBullet_X, enemyBullet_Y;
	public double bulletSpeed_X, bulletSpeed_Y;
	public int BulletSpeed,count;
	public int fragment,explode,delay,initExplode,initDelay;
	public int explodePattern;
	public int flagSpeed;
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
		initExplode = 1;
		initDelay = 10;
	}
	
	void setUpBomb(int explode,int delay, int fragment,int flagSpeed, int explodePattern) {
		this.explode = explode;
		this.initExplode = explode;
		this.initDelay = delay;
		this.fragment = fragment;
		this.explodePattern = explodePattern;
		this.flagSpeed = flagSpeed;
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