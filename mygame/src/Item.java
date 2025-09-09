import java.awt.Polygon;

class Item {
	public double item_X,item_Y;
	public int itemNumber;
	Item(double x, double y, int itemNumber) {
		this.item_X = x;
		this.item_Y = y;
		this.itemNumber = itemNumber;
	}
}

class ItemHandling {
	void Process(Game game) {
		moveItem(game);
		collisionCheck(game.x,game.y,game);		
	}
	
	void createItem(double x, double y,int itemNumber, int var,Game game) {
		
		int temp;
		if (game.level==1)
			temp = (int)(Math.random()*500);
		else if (game.level==2)
			temp = (int)(Math.random()*800);
		else
			temp = (int)(Math.random()*1000);
		if (temp<=var*3) {
			Item item = new Item(x,y,(int)(Math.random()*2)+1);
			game.itemList.add(item);
		}
	}
	
	void moveItem(Game game) {
		for (int i=0; i<game.itemList.size(); i++) {
			Item buf = (Item)game.itemList.elementAt(i);
			buf.item_X -= 1;
			if (buf.item_X < -40)
				game.itemList.remove(i);
			if (buf.itemNumber==1)
				game.grap.drawImage(game.itemImage1,(int)buf.item_X,(int)buf.item_Y,game);
			else
				game.grap.drawImage(game.itemImage2	,(int)buf.item_X,(int)buf.item_Y,game);
		}
	}
	
	void upgrade(Item item , Game game) {
		game.playSound("..\\rsc\\found_item.wav");
		switch (item.itemNumber) {
		case 1:
			game.score+=300;
			if (game.bulletLevel <17 ) 
				game.bulletLevel++;
				
			break;
		case 2:
			game.score+=300;
			if (game.skillLevel < 35 ) { 
				game.skillLevel++;
				game.skill.setSkill(game.skillLevel,game);
			}
			break;
		}
	}
	
	void collisionCheck(int Character_X, int Character_Y, Game game) {
		int [] xpoint = {Character_X, Character_X+50, Character_X+50, Character_X};
		int [] ypoint = {Character_Y+3, Character_Y+3, Character_Y+30, Character_Y+30 };
		Polygon p = new Polygon(xpoint, ypoint, 4);
		for (int i=0; i<game.itemList.size(); i++) {
			Item item = (Item)game.itemList.elementAt(i);
			if (p.intersects(item.item_X, item.item_Y, 28, 32)) {
				upgrade(item,game);	
				game.itemList.remove(i); 
			}
		}
	}
}
