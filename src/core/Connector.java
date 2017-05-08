package core;

import java.util.ArrayList;
import java.util.HashMap;

import map.Room;

public class Connector {
	//东
	private Room east;
	//西
	private Room west;
	//南
	private Room south;
	//北
	private Room north;
	/*
	 * 应该可以再定义其他的比如阁楼、地下室、密室等房间
	 */
	HashMap<String,Room> map  = new HashMap<String,Room>();
	private void setMap(){
		map.put("北",this.north);
		map.put("东",this.east);
		map.put("南",this.south);
		map.put("西",this.west);
	} 
	public Connector() {
		this.east = null;
		this.west = null;
		this.south = null;
		this.north = null;	
		this.setMap();
	}
	public void setRoom(String dir,Room des){
		map.replace(dir, des);
	}
	public Room getRoom(String dir){
		return map.get(dir);
	}
}
