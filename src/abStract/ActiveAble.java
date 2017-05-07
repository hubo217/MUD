package abStract;

import map.Room;

public interface ActiveAble {
	//此方法如果是怪物，实现了剧情的对话
	//如果是玩家，则是在交流
	public void sayToPlayer(String msg);
	
	//此方法用来攻击敌人
	public void attack(Character enemy);
	
	//移动
	public void moveToDes(Room r);
	
	//使用物品
	public void useItem(String name);
	
	//
}
