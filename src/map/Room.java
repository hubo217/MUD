package map;

import java.util.ArrayList;
import java.util.HashMap;

import abStract.Character;
import abStract.DataObject;
import abStract.Item;
import core.Connector;
import role.Player;

public class Room extends DataObject{
	private ArrayList<Room> roomList;
	private ArrayList<Item> itemList;
	private ArrayList<Player> playerList;;
	private ArrayList<Character> peopleList;
	private Connector connector;
	public Room(String name,String des) {
		super(name,des);
		this.connector = new Connector();
		this.roomList = new ArrayList<Room>();
		this.itemList = new ArrayList<Item>();
		this.playerList = new ArrayList<Player>();
		this.peopleList = new ArrayList<Character>();
	}
	//向房间添加物品
	public boolean addItem(Item i) {
		if(i.getLocation() != null){
			if(i.getLocation() instanceof Room){
				((Room) i.getLocation()).removeItem(i);
			}
		}
		i.setLocation(this);
		return itemList.add(i);
	}
	//向房间再添加房间
	public boolean addRoom(Room r){
		if(r.getLocation() != null){
			((Room) r.getLocation()).removeRoom(r);
		}
		r.setLocation(this);
		return roomList.add(r);
	}
	
	public boolean addPlayer(Player p){
		if(this.playerList.add(p)){
			if(p instanceof Player){
//				refreshPlayer();
				return true;
			}
		}
		return false;
	}
	//添加任务
	public boolean addPeople(Character c){
		if(this.peopleList.add(c)){
			
			if(c instanceof Character){
//				refreshPeople();
				return true;
			}
		}
		return false;
	}


	//从房间删除物品
	public boolean removeItem(Item i) {
		// TODO Auto-generated method stub
		i.setLocation(null);
		return this.itemList.remove(i);
	}
	
	//删除房间
	public boolean removeRoom(Room r){
		r.setLocation(null);
		return this.roomList.remove(r);
	}
	
	//删除人物
	public boolean removePeople(Character c){
		return this.peopleList.remove(c);
	}
	public boolean removePlayer(Player p){
		return this.playerList.remove(p);
	}
	
	//喊话
	public void sayToRoom(String context){
		for(Player p : this.playerList){
			p.sayToPlayer(context);
		}
	}
	
	//设置连接器
	public void setConnectorDir(String dir,Room room){
		this.connector.setRoom(dir, room);
	}
	public Room getConnectorDir(String dir){
		return this.connector.getRoom(dir);
	}
	//判断某个玩家是否在房间中：attack方法需要
	public boolean isPlayerInRoom(Player p){
		for(Player player : this.playerList){
			if(player.getName().equals(p.getName())){
				return true;
			}
		}
		return false;
	}
	public ArrayList<Player> getPlayerList() {
		return this.playerList;
	}
	
	//
	private void refreshPeople() {
		for(Character c : this.peopleList){
			for(Item i : c.openBag().getItemList()){
				i = (Item) World.getWorld().getDataObj(i.getDatabaseRef());
				i.setLocation(c);
			}
		}
		
	}
	
	
	private void refreshPlayer() {
		for(Player p : this.playerList){
			for(Item i :  p.openBag().getItemList()){
				i = (Item) World.getWorld().getDataObj(i.getDatabaseRef());
				i.setLocation(p);
			}
		}
		
	}
}
