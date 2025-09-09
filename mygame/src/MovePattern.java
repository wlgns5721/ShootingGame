class MovePattern {
	int temp1,temp2;
	void pattern1(Game game,Enemy enemy) {
		enemy.speed_Y = 0.5-Math.random();
 	}
	
	void pattern2(Game game, Enemy enemy) {
		enemy.speed_Y = 0.5-Math.random();
		if (enemy.enemy_X < enemy.moveLimit && enemy.moveIndex<200) {
			enemy.speed_X-=enemy.speed_X/30;
			enemy.speed_Y-=enemy.speed_Y/30;
		}
		
		else if (enemy.moveIndex>=300 && enemy.moveIndex<380) {
			enemy.speed_X+=0.2;
		}
		enemy.moveIndex++;
	}
	
	void pattern3(Game game, Enemy enemy) {
		if (enemy.moveIndex>=0 && enemy.moveIndex <100) {
			enemy.speed_X-=0.05;
		}
		else if (enemy.moveIndex==300) {
			if ((int)(Math.random()*2)==0)
				temp1=1;
			else
				temp1=-1;
			if ((int)(Math.random()*2)==0)
				temp2=1;
			else
				temp2=-1;
			enemy.speed_X = 2*temp1;
			enemy.speed_Y = 2*temp2;
		}
		else if (enemy.moveIndex >= 300 && enemy.moveIndex<400) {
			if (enemy.enemy_X <= 1000) {
				enemy.speed_X = -2;
			}
			else if (enemy.enemy_X >=1250)
				enemy.speed_X = 2;
			if (enemy.enemy_Y >= 500) {
				enemy.speed_Y = -2;
			}
			else if (enemy.enemy_Y <=0)
				enemy.speed_Y = 2;
		}
		else if (enemy.moveIndex==400) {
			enemy.speed_X=0;
			enemy.speed_Y=0;
		}

		else if (enemy.moveIndex==600) {
			if ((int)(Math.random()*2)==0)
				temp1=1;
			else
				temp1=-1;
			if ((int)(Math.random()*2)==0)
				temp2=1;
			else
				temp2=-1;
			enemy.speed_X = 2*temp1;
			enemy.speed_Y = 2*temp2;
		}
		else if (enemy.moveIndex >= 600 && enemy.moveIndex<700) {
			if (enemy.enemy_X <= 1000) {
				enemy.speed_X = -2;
			}
			else if (enemy.enemy_X >=1250)
				enemy.speed_X = 2;
			if (enemy.enemy_Y >= 500) {
				enemy.speed_Y = -2;
			}
			else if (enemy.enemy_Y <=0)
				enemy.speed_Y = 2;
		}
		else if (enemy.moveIndex==700) {
			enemy.speed_X=0;
			enemy.speed_Y=0;
		}
		else if (enemy.moveIndex==900)
			enemy.speed_X=-3;
		else
			enemy.speed_Y = 0.5-Math.random();
		enemy.moveIndex++;
		
	}
	
	void pattern4(Game game, Enemy enemy) {
		/*if (enemy.move_index>=0 && enemy.move_index <100) {
			enemy.speed_X-=0.05;
		}
		else if (enemy.move_index==300) {
			if ((int)(Math.random()*2)==0)
				temp1=1;
			else
				temp1=-1;
			if ((int)(Math.random()*2)==0)
				temp2=1;
			else
				temp2=-1;
			enemy.speed_X = 2*temp1;
			enemy.speed_Y = 2*temp2;
		}
		else if (enemy.move_index >= 300 && enemy.move_index<400) {
			if (enemy.enemy_X <= 1000) {
				enemy.speed_X = -2;
			}
			else if (enemy.enemy_X >=1250)
				enemy.speed_X = 2;
			if (enemy.enemy_Y >= 500) {
				enemy.speed_Y = -2;
			}
			else if (enemy.enemy_Y <=0)
				enemy.speed_Y = 2;
		}
		else if (enemy.move_index==400) {
			enemy.speed_X=0;
			enemy.speed_Y=0;
		}

		else if (enemy.move_index==600) {
			if ((int)(Math.random()*2)==0)
				temp1=1;
			else
				temp1=-1;
			if ((int)(Math.random()*2)==0)
				temp2=1;
			else
				temp2=-1;
			enemy.speed_X = 2*temp1;
			enemy.speed_Y = 2*temp2;
		}
		else if (enemy.move_index >= 600 && enemy.move_index<700) {
			if (enemy.enemy_X <= 1000) {
				enemy.speed_X = -2;
			}
			else if (enemy.enemy_X >=1250)
				enemy.speed_X = 2;
			if (enemy.enemy_Y >= 500) {
				enemy.speed_Y = -2;
			}
			else if (enemy.enemy_Y <=0)
				enemy.speed_Y = 2;
		}
		else if (enemy.move_index==700) {
			enemy.speed_X=0;
			enemy.speed_Y=0;
		}
		/*/
	}
	void pattern5(Game game, Enemy enemy) {
		pattern3(game, enemy);
		if (enemy.moveIndex==999)
			enemy.moveIndex=0;
		
	}
	
	void pattern6(Game game, Enemy enemy) {
		if (enemy.temp==0)
			enemy.temp = (int)enemy.enemy_Y;
		enemy.enemy_X-=5;
		enemy.enemy_Y = enemy.temp +20*Math.sin(enemy.enemy_X*Math.PI/180);
	}
	
	void pattern7(Game game, Enemy enemy) {
		if (enemy.temp==0)
			enemy.temp = (int)enemy.enemy_X;
		enemy.enemy_Y-=2;
		enemy.enemy_X = enemy.temp - Math.sqrt(Math.abs(enemy.temp*enemy.temp-enemy.enemy_Y*enemy.enemy_Y));
		
	}
	
	void bossPattern(Game game, Enemy enemy) {
		if (enemy.moveIndex>=0 && enemy.moveIndex <100) {
			enemy.speed_X-=0.05;
		}
		else if (enemy.moveIndex==300) {
			enemy.speed_X = 2;
			enemy.speed_Y = 2;
		}
		else if (enemy.moveIndex >= 300 && enemy.moveIndex<600) {
			if (enemy.enemy_X <= 1000) {
				enemy.speed_X = -2;
			}
			else if (enemy.enemy_X >=1200)
				enemy.speed_X = 2;
			if (enemy.enemy_Y >= 450) {
				enemy.speed_Y = -2;
			}
			else if (enemy.enemy_Y <=0)
				enemy.speed_Y = 2;
		}
		else if (enemy.moveIndex==600) {
			enemy.speed_X=0;
			enemy.speed_Y=0;
		}
		else if (enemy.moveIndex==700)
			enemy.moveIndex=299;
		enemy.moveIndex++;
	}
	void bossPattern3(Game game, Enemy enemy) {
		if (game.flowIndex >=8600 && game.flowIndex <8800) {
			enemy.speed_X=0;
			enemy.speed_Y=0;
			return;
		}
		if (enemy.moveIndex>=0 && enemy.moveIndex <100) {
			enemy.speed_X-=0.05;
		}
		
		else if (enemy.moveIndex==300 ) {
			enemy.speed_X = 1;
			enemy.speed_Y = 1;
		}
		else if (enemy.moveIndex >= 300 && enemy.moveIndex<600) {
			if (enemy.enemy_X <= 900) {
				enemy.speed_X = -1;
			}
			else if (enemy.enemy_X >=1070)
				enemy.speed_X = 1;
			if (enemy.enemy_Y >= 300) {
				enemy.speed_Y = -1;
			}
			else if (enemy.enemy_Y <=0)
				enemy.speed_Y = 1;
		}
		else if (enemy.moveIndex==600) {
			enemy.speed_X=0;
			enemy.speed_Y=0;
		}
		else if (enemy.moveIndex==700)
			enemy.moveIndex=299;
		enemy.moveIndex++;
		}
	
	
	
	
	//void Pattern2(game game, Enemy enemy)
}
