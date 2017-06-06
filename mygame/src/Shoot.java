import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

class Shoot {
	public int skill_fireDelay = 0;
	public int skill_launch_amount = 0;
	void launch(game Game) {
		Game.PlaySound("..\\rsc\\shoot.wav");
		Bullet bullet = new Bullet(Game.X+10,Game.Y+10,Game.bullet_level, Game);
		Game.bulletList.add(bullet);
	}
	
	void MoveBullet(game Game) {		
		for (int i=0; i<Game.bulletList.size(); i++) {
			Bullet buff = (Bullet)Game.bulletList.elementAt(i);
			buff.bullets_X+=30;
			Game.grap.drawImage((Image)Game.bulletImageList.elementAt((Game.bullet_level-1)/4),buff.bullets_X,buff.bullets_Y,Game);	
			if (CollisionCheck(buff.bullets_X,buff.bullets_Y,buff.width,buff.height,buff.damage,Game) || buff.bullets_X>1600) 
				Game.bulletList.remove(i);		
			
		}
				
		for (int i=0; i<Game.skillBulletList.size(); i++) {
			Game.g2 = (Graphics2D) Game.grap;
			AffineTransform turn = new AffineTransform();
			SkillBullet buf = (SkillBullet)Game.skillBulletList.elementAt(i);
			buf.skillBullet_X += buf.bulletSpeed_X;
			buf.skillBullet_Y += buf.bulletSpeed_Y;
			turn.translate(buf.skillBullet_X,buf.skillBullet_Y);			
			turn.rotate(Math.atan(buf.bulletSpeed_Y/buf.bulletSpeed_X));
			Game.g2.drawImage(Game.skillBulletImage,turn,null);
			if (CollisionCheck((int)buf.skillBullet_X,(int)buf.skillBullet_Y,buf.width,buf.height,buf.damage,Game) || buf.skillBullet_X > 1600 
					|| buf.skillBullet_Y < -20 || buf.skillBullet_Y > 620 || buf.skillBullet_X<-100) 
				Game.skillBulletList.remove(i);
			
		}
	}
	
	boolean CollisionCheck(int bullet_X,int bullet_Y,int width,int height, int damage,game Game) {
		int [] xpoint = {bullet_X,bullet_X+width,bullet_X+width,bullet_X};
		int [] ypoint = {bullet_Y, bullet_Y, bullet_Y+height, bullet_Y+height};
		Polygon p = new Polygon(xpoint, ypoint, 4);
		Enemy enemy;
		for (int i=0; i<Game.enemyList.size(); i++) {
			enemy = (Enemy)Game.enemyList.elementAt(i);
			if (Intersects(enemy,p)) {
				enemy.HP-=damage;
				Game.grap.drawImage((Image)Game.attacked_enemyImageList.elementAt(enemy.image_number),(int)enemy.enemy_X,(int)enemy.enemy_Y,Game);
				if (enemy.HP<=0) {
					Game.score+=enemy.score;
					Game.max_enemy--;
					Game.PlaySound("..\\rsc\\explode"+enemy.attack_Pattern%8+".wav");
					BoomEffect boomEffect = new BoomEffect((int)enemy.enemy_X, (int)enemy.enemy_Y,enemy.width*2,enemy.width*2);
					Game.boomList.add(boomEffect);
					Game.enemyList.remove(i);
					Game.itemHandling.CreateItem(enemy.enemy_X+enemy.width*0.2, enemy.enemy_Y+enemy.height*2/5, (int)(Math.random()*2)+1, enemy.max_HP,Game);					
				}
				return true;
			}
		}
		return false;
	}
	
	boolean Intersects(Enemy enemy,Polygon p) {
		if (enemy.move_Pattern!=10)
			return p.intersects(enemy.enemy_X, enemy.enemy_Y, enemy.width, enemy.height);
		else {
			if (p.intersects(enemy.enemy_X+8, enemy.enemy_Y+48, 48, 48)
					|| p.intersects(enemy.enemy_X+48, enemy.enemy_Y+32, enemy.width-48, enemy.height-32)
					|| p.intersects(enemy.enemy_X+152, enemy.enemy_Y, 48, 40)) {
				return true;
			}
		}
		return false;
	}
	
	void Skill(game Game) {
		if (skill_fireDelay==0) {
			Game.PlaySound("..\\rsc\\skillSound.wav");
			for (int i=0; i<Game.skill.numberOfBullet; i++) {
				SkillBullet skillBullet = new SkillBullet(Game.X+10,Game.Y+10,Game.skill_damage);
			    Game.skillBulletList.add(skillBullet);
			}
			
			skill_launch_amount++;
			skill_fireDelay = Game.skill.delay;
		}
		skill_fireDelay--;
		if (skill_launch_amount >= Game.skill.launch_amount) {
			Game.SkillFlag = false;
			skill_launch_amount = 0;
			skill_fireDelay = 0;
		}
		
	}
}
