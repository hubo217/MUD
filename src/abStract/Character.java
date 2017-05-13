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
	public int exp;
	public int level;
	public int room_id;
	public ItemList bag;
	public Weapon weapon;
	public Clothes clothes;
	public boolean isFighting;
	
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
	public void setPoint(int hel, int ack, int def, int str, int hit, int spd,int exp,int level) {

		this.HEL = hel;
		this.ACK = ack;
		this.DEF = def;
		this.STR = str;
		this.HIT = hit;
		this.SPD = spd;
		this.exp = exp;
		this.level = level;
	}
//此方法结算力量点数对攻击和速度的影响
	private void calStr() {
		//攻击收等级和力量影响
		this.ACK = (this.STR * 5)/this.level;
	}


	private void updateLevel() {
		int nextExp = this.level * (this.level + 5) * 10;
		while(this.exp > nextExp){
			this.level++;
		}
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
