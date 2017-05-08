package role;

import abStract.Character;
import abStract.DataObject;
import item.ItemList;

public class Player extends Character{
	private Player player;
	private String password;
	private ItemList bag;
	public Player(String name,String des) {
		super(name, des);
		// TODO Auto-generated constructor stub
	}

}
