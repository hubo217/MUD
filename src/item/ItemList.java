package item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import inter.ActiveAble;
import abStract.Character;
import abStract.DataObject;
import abStract.Item;
import map.Room;
import role.Player;

public class ItemList extends DataObject{
	//背包各自
	private int maxSize;
	//包
	private ArrayList<Item> bag;
	//״
	private boolean isFull;
	
	public ItemList(String name,String des,int maxSize,boolean ifFull) {
		super(name,des);
		this.maxSize = maxSize;
		this.bag = new ArrayList<Item>();
		this.isFull = ifFull;
	}


	@Override
	public int getDatabaseRef() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getMaxSize(){
		return this.maxSize;
	}
	//设置最大背包格子
	public void setMaxSize(int m){
		this.maxSize = m;
	}
	public boolean isFull(){
		return this.isFull;
	}

	public void setIsFull(boolean b){
		this.isFull = b;
	}
	//通过物品名字找到物品
	public Item getItemByName(String name){
		for(Item item : this.bag){
			String itemName = item.getName();
			if(itemName.equals(name)){
				return item;
			}
		}
		return null;
	}
	//把物品放入背包中
	public boolean addItemInBag(Item i,ActiveAble active){
		if(isFull() || this.bag.size() == this.maxSize){
			active.sayToPlayer("��İ�����");
			return false;
		}else{
			this.bag.add(i);
			active.sayToPlayer("�㽫"+i.getName()+"�����˱���");
			return true;
		}
	}

	//把多个物品放入背包中
	public boolean addItemInBag(ArrayList<Item> i,ActiveAble active){
		if(isFull() || this.bag.size() == this.maxSize){
			active.sayToPlayer("��İ�����");
			return false;
		}else{
			for(int n = 0;n < i.size();n++){
			this.bag.add(i.get(n));
			}
			active.sayToPlayer("�㽫"+i.size()+"��"+i.get(0).getName()+"�����˱���");
			return true;
		}
	}

	//��Ʒ����
	public boolean throwItem(String itemName,Room room,ActiveAble active){
		Item tmp = getItemByName(itemName);
		if(this.bag.remove(tmp)){
			room.addItem(tmp);
			tmp.setLocation(room);
		}
		return false;
	}

	//��Ʒ�鿴
	public void checkItem(Player p){
		String des = "";
		if(!this.bag.isEmpty()){
			Collections.sort(this.bag);
			for(Item i : this.bag){
				des += i.getName() + ":" +i.getDescription() + "\n\r";
			}
		}
		p.sayToPlayer(des);
	}
	//
	public ArrayList<Item> getItemList(){
		return this.bag;
	}
	
	public boolean giveItemToSb(String itemName,String otherName,ActiveAble active){
		//��Ʒ����
		return false;
	}
	

}
