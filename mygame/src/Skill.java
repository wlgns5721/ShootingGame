
public class Skill {
	public int delay,launchAmount,numberOfBullet;
	Skill(Game game) {
		setSkill(game.skillLevel,game);
	}
	void setSkill(int skillLevel,Game game) {
		switch (((skillLevel-1)+1)) {
		case 1:
			launchAmount = 4;
			numberOfBullet = 5;
			delay = 10;
			break;
		case 2:
			launchAmount = 6;
			numberOfBullet = 10;
			delay = 10;
			break;
		case 3:
			launchAmount = 12;
			numberOfBullet = 10;
			delay = 5;
			break;
		case 4:
			launchAmount = 15;
			numberOfBullet = 20;
			delay = 5;
			break;	
		case 5:
			launchAmount = 1;
			numberOfBullet = 500;
			delay = 10;
			break;
		case 8:
			launchAmount = 6;
			numberOfBullet = 30;
			delay = 10;
			game.skillDamage = 20;
			game.changeSkillImage("..\\rsc\\skillBullet2.png");
			break;
		case 10:
			launchAmount = 24;
			numberOfBullet = 7;
			delay = 5;
			break;
		case 14:
			launchAmount = 15;
			numberOfBullet = 17;
			delay = 3;
			break;
		case 18:
			launchAmount = 1;
			numberOfBullet = 400;
			delay = 10;
			break;
		case 22:
			launchAmount = 3;
			numberOfBullet = 180;
			delay = 1;
			break;
		case 26:
			game.changeSkillImage("..\\rsc\\skillBullet3.png");
			launchAmount = 5;
			numberOfBullet = 60;
			delay = 5;
			game.skillDamage = 30;
			break;
		case 30:
			launchAmount = 30;
			numberOfBullet = 15;
			delay = 3;
			break;
		case 35:
			launchAmount = 30;
			numberOfBullet = 30;
			delay = 2;
			break;
		}
		
	}
	
}
