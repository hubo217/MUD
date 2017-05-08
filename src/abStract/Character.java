package abStract;

import inter.ActiveAble;
import item.Clothes;
import item.Weapon;
import map.Room;

public class Character extends DataObject implements ActiveAble{
	public int id;
	public String name;
	//生命值
	public int HEL;
	//攻击力
	public int ACK;
	/*
	 *攻防结算公式
	 *一次攻击结算伤害=攻击者输出伤害*(1-防御值物免率)
	 *防御值物免率=1-1/(1+防守者防御总值/10)
	 */
	//防御力
	public int DEF;
	//力量属性，影响攻击力和速度的结算
	public int STR;
	//命中率
	public int HIT;
	//速度，影响攻击的顺序
	public int SPD;
	public int room_id;
	public Weapon weapon;
	public Clothes clothes;
	
	public Character(String name,String des) {
		super(name,des);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getDatabaseRef() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void sayToPlayer(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
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



	
}
