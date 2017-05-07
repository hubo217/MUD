package abStract;

public abstract class Item extends DataObject implements Comparable<Item>{
//Item是物品的抽象类	
	public Item(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public int id;
	public String name;
	public int ACK;//物理攻击力
	public int DEF;//防御力
	public int STR;//力量
	public int HIT;//命中率
	public int SPD;//速度
	public int value;//价值
}
