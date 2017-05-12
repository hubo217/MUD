package core;

import java.util.HashMap;
import map.Room;

public class Connector {


	HashMap<String,Room> map  = new HashMap<String,Room>();
	private void setMap(){
		map.put("north",null);
		map.put("south",null);
		map.put("west",null);
		map.put("east",null);
	} 
	public Connector() {
		this.setMap();
	}
	public void setRoom(String dir,Room des){
		if(map.containsKey(dir)){
			map.replace(dir, des);
		}else{
			map.put(dir, des);		
		}
	}
	public Room getRoom(String dir){
		return map.get(dir);
	}
	public HashMap<String,Room> getHashMap(){
		return this.map;
	}
}
