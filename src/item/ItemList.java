package item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import abStract.ActiveAble;
import abStract.Character;
import abStract.DataObject;
import abStract.Item;
import map.Room;
import role.Player;

public class ItemList extends DataObject{
	//物品最大存放数
	private int maxSize;
	//物品列表
	private ArrayList<Item> bag;
	//物品栏状态
	private boolean isFull;
	
	public ItemList(String name,String des,int maxSize,boolean ifFull) {
		super(name,des);
		this.maxSize = maxSize;
		this.bag = new ArrayList<Item>();
		this.isFull = ifFull;
	}
//物品栏

	@Override
	public int getDatabaseRef() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getMaxSize(){
		return this.maxSize;
	}
	//设置背包最大数
	public void setMaxSize(int m){
		this.maxSize = m;
	}
	public boolean isFull(){
		return this.isFull;
	}
	//设置背包锁定
	public void setIsFull(boolean b){
		this.isFull = b;
	}
	//物品遍历
	public Item getItemByName(String name){
		for(Item item : this.bag){
			String itemName = item.getName();
			if(itemName.equals(name)){
				return item;
			}
		}
		return null;
	}
	//只有一个物品的情况
	public boolean addItemInBag(Item i,ActiveAble active){
		if(isFull() || this.bag.size() == this.maxSize){
			active.sayToPlayer("你的包满了");
			return false;
		}else{
			this.bag.add(i);
			active.sayToPlayer("你将"+i.getName()+"放入了背包");
			return true;
		}
	}

	//多个物品的情况
	public boolean addItemInBag(ArrayList<Item> i,ActiveAble active){
		if(isFull() || this.bag.size() == this.maxSize){
			active.sayToPlayer("你的包满了");
			return false;
		}else{
			for(int n = 0;n < i.size();n++){
			this.bag.add(i.get(n));
			}
			active.sayToPlayer("你将"+i.size()+"个"+i.get(0).getName()+"放入了背包");
			return true;
		}
	}

	//物品抛弃
	public boolean throwItem(String itemName,Room room,ActiveAble active){
		Item tmp = getItemByName(itemName);
		if(this.bag.remove(tmp)){
			room.addItem(tmp);
			tmp.setLocation(room);
		}
		return false;
	}

	//物品查看
	public void checkItem(Player p){
		String des = "你打开了背包，进行了一番搜索\n\r";
		if(!this.bag.isEmpty()){
			Collections.sort(this.bag);
			for(Item i : this.bag){
				des += "你掏出了" + i.getName() + ":" +i.getDescription() + "\n\r";
			}
		}
		p.sayToPlayer(des);
	}
	
	
	public boolean giveItemToSb(String itemName,String otherName,ActiveAble active){
		//物品赠送
		return false;
	}
}
