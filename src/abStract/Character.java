package abStract;

import inter.ActiveAble;
import item.Clothes;
import item.ItemList;
import item.Weapon;
import map.Room;
import net.Client;

public class Character extends DataObject implements ActiveAble{
	public int id;
	public String name;
	public int HEL;
	public int ACK;
	public int DEF;
	public int STR;
	public int HIT;
	public int SPD;
	public int room_id;
	public ItemList bag;
	public Weapon weapon;
	public Clothes clothes;
	private boolean isFighting;
	
	public Character(String name,String des) {
		super(name,des);
		// TODO Auto-generated constructor stub
	}


	public void attack(Character enemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveToDes(Room r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useItem(String name) {
		// TODO Auto-generated method stub
		
	}

	public int getRoomId(){
		return this.room_id;
	}
	
	public ItemList openBag(){
		return this.bag;
	}
	public void boundBag(ItemList il){
		this.bag = il;
	}
	public void setIsFight(boolean b){
		this.isFighting = b;
	}
	public boolean getIsFight(){
		return this.isFighting;
	}

	@Override
	public void setPoint(int ack, int def, int hel, int str, int hit, int spd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pickUpItem(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addItem(Item i) {
		// TODO Auto-generated method stub
		this.bag.addItemInBag(i, this);
	}


	@Override
	public void sayToPlayer(String msg) {
		// TODO Auto-generated method stub
		
	}
	
}
