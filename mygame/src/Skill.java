
public class Skill {
	public int delay,launch_amount,numberOfBullet;
	Skill(game Game) {
		SetSkill(Game.skill_level,Game);
	}
	void SetSkill(int skill_level,game Game) {
		switch (((skill_level-1)+1)) {
		case 1:
			launch_amount = 4;
			numberOfBullet = 5;
			delay = 10;
			break;
		case 2:
			launch_amount = 6;
			numberOfBullet = 10;
			delay = 10;
			break;
		case 3:
			launch_amount = 12;
			numberOfBullet = 10;
			delay = 5;
			break;
		case 4:
			launch_amount = 15;
			numberOfBullet = 20;
			delay = 5;
			break;	
		case 5:
			launch_amount = 1;
			numberOfBullet = 500;
			delay = 10;
			break;
		case 8:
			launch_amount = 6;
			numberOfBullet = 30;
			delay = 10;
			Game.skill_damage = 20;
			Game.ChangeSkillImage("..\\rsc\\skillBullet2.png");
			break;
		case 10:
			launch_amount = 24;
			numberOfBullet = 7;
			delay = 5;
			break;
		case 14:
			launch_amount = 15;
			numberOfBullet = 17;
			delay = 3;
			break;
		case 18:
			launch_amount = 1;
			numberOfBullet = 400;
			delay = 10;
			break;
		case 22:
			launch_amount = 3;
			numberOfBullet = 180;
			delay = 1;
			break;
		case 26:
			Game.ChangeSkillImage("..\\rsc\\skillBullet3.png");
			launch_amount = 5;
			numberOfBullet = 60;
			delay = 5;
			Game.skill_damage = 30;
			break;
		case 30:
			launch_amount = 30;
			numberOfBullet = 15;
			delay = 3;
			break;
		case 35:
			launch_amount = 30;
			numberOfBullet = 30;
			delay = 2;
			break;
		}
		
	}
	
}
