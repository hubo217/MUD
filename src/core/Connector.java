package core;

import java.util.HashMap;
import map.Room;

public class Connector {

	/*
	 * 应该可以再定义其他的比如阁楼、地下室、密室等房间
	 */
	HashMap<String,Room> map  = new HashMap<String,Room>();
	private void setMap(){
		map.put("北",null);
		map.put("东",null);
		map.put("南",null);
		map.put("西",null);
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
}
