package item;

import abStract.Item;

public class Weapon extends Item{
	public Weapon(String name,String des) {
		super(name,des);
		// TODO Auto-generated constructor stub
	}
	public void setWeaponAttri(int ack,int def,int spd,int hit,int v){

		super.ACK = ack;
		super.DEF = def;
		super.SPD = spd;
		super.HIT = hit;
		super.value = v;
	}
	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getDatabaseRef() {
		// TODO Auto-generated method stub
		return 0;
	}
}
