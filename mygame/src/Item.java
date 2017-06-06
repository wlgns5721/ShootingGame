import java.awt.Polygon;

class Item {
	public double item_X,item_Y;
	public int item_number;
	Item(double X, double Y, int Item_number) {
		item_X = X;
		item_Y = Y;
		item_number = Item_number;
	}
}

class ItemHandling {
	void Process(game Game) {
		MoveItem(Game);
		CollisionCheck(Game.X,Game.Y,Game);		
	}
	
	void CreateItem(double X, double Y,int Item_number, int var,game Game) {
		
		int temp;
		if (Game.level==1)
			temp = (int)(Math.random()*500);
		else if (Game.level==2)
			temp = (int)(Math.random()*800);
		else
			temp = (int)(Math.random()*1000);
		if (temp<=var*3) {
			Item item = new Item(X,Y,(int)(Math.random()*2)+1);
			Game.itemList.add(item);
		}
	}
	
	void MoveItem(game Game) {
		for (int i=0; i<Game.itemList.size(); i++) {
			Item buf = (Item)Game.itemList.elementAt(i);
			buf.item_X -= 1;
			if (buf.item_X < -40)
				Game.itemList.remove(i);
			if (buf.item_number==1)
				Game.grap.drawImage(Game.itemImage1,(int)buf.item_X,(int)buf.item_Y,Game);
			else
				Game.grap.drawImage(Game.itemImage2	,(int)buf.item_X,(int)buf.item_Y,Game);
		}
	}
	
	void Upgrade(Item item , game Game) {
		Game.PlaySound("..\\rsc\\found_item.wav");
		switch (item.item_number) {
		case 1:
			Game.score+=300;
			if (Game.bullet_level <17 ) 
				Game.bullet_level++;
				
			break;
		case 2:
			Game.score+=300;
			if (Game.skill_level < 35 ) { 
				Game.skill_level++;
				Game.skill.SetSkill(Game.skill_level,Game);
			}
			break;
		}
	}
	
	void CollisionCheck(int Character_X, int Character_Y, game Game) {
		int [] xpoint = {Character_X, Character_X+50, Character_X+50, Character_X};
		int [] ypoint = {Character_Y+3, Character_Y+3, Character_Y+30, Character_Y+30 };
		Polygon p = new Polygon(xpoint, ypoint, 4);
		for (int i=0; i<Game.itemList.size(); i++) {
			Item item = (Item)Game.itemList.elementAt(i);
			if (p.intersects(item.item_X, item.item_Y, 28, 32)) {
				Upgrade(item,Game);	
				Game.itemList.remove(i); 
			}
		}
	}
}
