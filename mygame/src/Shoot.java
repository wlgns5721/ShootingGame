import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

class Shoot {
	public int skill_fireDelay = 0;
	public int skill_launch_amount = 0;
	void launch(Game game) {
		game.playSound("..\\rsc\\shoot.wav");
		Bullet bullet = new Bullet(game.x+10,game.y+10,game.bulletLevel, game);
		game.bulletList.add(bullet);
	}
	
	void MoveBullet(Game game) {		
		for (int i=0; i<game.bulletList.size(); i++) {
			Bullet buff = (Bullet)game.bulletList.elementAt(i);
			buff.bullets_X+=30;
			game.grap.drawImage((Image)game.bulletImageList.elementAt((game.bulletLevel-1)/4),buff.bullets_X,buff.bullets_Y,game);	
			if (CollisionCheck(buff.bullets_X,buff.bullets_Y,buff.width,buff.height,buff.damage,game) || buff.bullets_X>1600) 
				game.bulletList.remove(i);		
			
		}
				
		for (int i=0; i<game.skillBulletList.size(); i++) {
			game.g2 = (Graphics2D) game.grap;
			AffineTransform turn = new AffineTransform();
			SkillBullet buf = (SkillBullet)game.skillBulletList.elementAt(i);
			buf.skillBullet_X += buf.bulletSpeed_X;
			buf.skillBullet_Y += buf.bulletSpeed_Y;
			turn.translate(buf.skillBullet_X,buf.skillBullet_Y);			
			turn.rotate(Math.atan(buf.bulletSpeed_Y/buf.bulletSpeed_X));
			game.g2.drawImage(game.skillBulletImage,turn,null);
			if (CollisionCheck((int)buf.skillBullet_X,(int)buf.skillBullet_Y,buf.width,buf.height,buf.damage,game) || buf.skillBullet_X > 1600 
					|| buf.skillBullet_Y < -20 || buf.skillBullet_Y > 620 || buf.skillBullet_X<-100) 
				game.skillBulletList.remove(i);
			
		}
	}
	
	boolean CollisionCheck(int bullet_X,int bullet_Y,int width,int height, int damage,Game game) {
		int [] xpoint = {bullet_X,bullet_X+width,bullet_X+width,bullet_X};
		int [] ypoint = {bullet_Y, bullet_Y, bullet_Y+height, bullet_Y+height};
		Polygon p = new Polygon(xpoint, ypoint, 4);
		Enemy enemy;
		for (int i=0; i<game.enemyList.size(); i++) {
			enemy = (Enemy)game.enemyList.elementAt(i);
			if (Intersects(enemy,p)) {
				enemy.HP-=damage;
				game.grap.drawImage((Image)game.attackedEnemyImageList.elementAt(enemy.image_number),(int)enemy.enemy_X,(int)enemy.enemy_Y,game);
				if (enemy.HP<=0) {
					game.score+=enemy.score;
					game.maxEnemy--;
					game.playSound("..\\rsc\\explode"+enemy.attack_Pattern%8+".wav");
					BoomEffect boomEffect = new BoomEffect((int)enemy.enemy_X, (int)enemy.enemy_Y,enemy.width*2,enemy.width*2);
					game.boomList.add(boomEffect);
					game.enemyList.remove(i);
					game.itemHandling.CreateItem(enemy.enemy_X+enemy.width*0.2, enemy.enemy_Y+enemy.height*2/5, (int)(Math.random()*2)+1, enemy.max_HP,game);					
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
	
	void Skill(Game game) {
		if (skill_fireDelay==0) {
			game.playSound("..\\rsc\\skillSound.wav");
			for (int i=0; i<game.skill.numberOfBullet; i++) {
				SkillBullet skillBullet = new SkillBullet(game.x+10,game.y+10,game.skillDamage);
			    game.skillBulletList.add(skillBullet);
			}
			
			skill_launch_amount++;
			skill_fireDelay = game.skill.delay;
		}
		skill_fireDelay--;
		if (skill_launch_amount >= game.skill.launch_amount) {
			game.skillFlag = false;
			skill_launch_amount = 0;
			skill_fireDelay = 0;
		}
		
	}
}
