package core;

import java.util.HashMap;
import map.Room;
import map.World;

public class Connector {


	HashMap<String,String> map  = new HashMap<String,String>();
//	private void setMap(){
//		map.put("north",null);
//		map.put("south",null);
//		map.put("west",null);
//		map.put("east",null);
//	} 
	public Connector() {
//		this.setMap();
	}
	public void setRoom(String dir,String roomName){
		if(map.containsKey(dir)){
			map.replace(dir, roomName);
		}else{
			map.put(dir, roomName);		
		}
	}
	public Room getRoom(String dir){
		if(map.get(dir) != null){
			return World.getWorld().RoomMap.get(map.get(dir));
		}
		return null;
	}
	public HashMap<String,String> getHashMap(){
		return this.map;
	}
}
