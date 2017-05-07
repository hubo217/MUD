package abStract;

import item.Clothes;
import item.Weapon;

public abstract class Character {
	private int id;
	private String name;
	private int ACK;//物理攻击力
	private int DEF;//防御力
	private int STR;//力量
	private int HIT;//命中率
	private int SPD;//速度
	private Weapon weapon;
	private Clothes clothes;

}
