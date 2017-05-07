package role;

import java.util.ArrayList;

import abStract.Character;
import abStract.Item;
import item.Clothes;
import item.Money;
import item.Weapon;
import map.Room;

public class Player extends Character{
	//玩家信息
	private int id;
	private String name;
	private String pass;
	//玩家属性
	private Money money;
	private Room room;
	private int statpoints;
	//装备栏
	private Weapon weapon;
	private Clothes clothes;
	//物品栏
	private ArrayList<Item> itemList;
	//临时信息
	boolean active;
	boolean loggedin;
	
	public Player() {
		
	}
}
