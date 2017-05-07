package map;

import java.util.ArrayList;
import java.util.HashMap;

import abStract.Character;
import abStract.DataObject;
import abStract.Item;
import role.Player;

public class Room extends DataObject{
	private ArrayList<Room> roomList;
	private ArrayList<Item> itemList;
	private ArrayList<Character> peopleList;
	
	public Room(String name,String des) {
		super(name,des);
		// TODO Auto-generated constructor stub
	}

	private int id;
	private String name;
	private ArrayList<HashMap<String,Integer>> mapList;

	public void setMapList(int bei,int dong,int nan,int xi){
		HashMap<String,Integer> map  = new HashMap<String,Integer>();
		map.put("北", bei);
		map.put("东", dong);
		map.put("南", nan);
		map.put("西", xi);
		mapList.add(map);
	}

	@Override
	public int getDatabaseRef() {
		// TODO Auto-generated method stub
		return 0;
	}
	//向房间添加物品
	//如果是被丢弃在房间里的物品，则先修改物品的位置
	public boolean addItem(Item i) {
		if(i.getLocation() != null){
			if(i.getLocation() instanceof Room){
				((Room) i.getLocation()).removeItem(i);
			}
		}
		i.setLocation(this);
		return itemList.add(i);
	}
	//向房间添加房间
	//
	public boolean addRoom(Room r){
		if(r.getLocation() != null){
			((Room) r.getLocation()).removeRoom(r);
		}
		r.setLocation(this);
		return roomList.add(r);
	}
	
	
	//向房间人物(玩家/NPC)列表添加人物
	public boolean addPeople(Character c){
		if(this.peopleList.add(c)){
			//判断是不是玩家
			if(c instanceof Player){
				c.sayToPlayer("我胡汉三又回来了！");
				this.refreshEnemy();
				return true;
			}
		}
		return false;
	}


	//删除房间物品列表中的物品
	public boolean removeItem(Item i) {
		// TODO Auto-generated method stub
		i.setLocation(null);
		return this.itemList.remove(i);
	}
	
	//删除房间房间列表中的房间
	public boolean removeRoom(Room r){
		r.setLocation(null);
		return this.roomList.remove(r);
	}
	
	//删除房间人物列表中的人物
	public boolean removePeople(Character c){
		c.setLocation(null);
		return this.peopleList.remove(c);
	}
	
	
	private void refreshEnemy() {
		// TODO Auto-generated method stub
		
	}
	
	
	private void refreshPlayer() {
		// TODO Auto-generated method stub
		
	}
}
