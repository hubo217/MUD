package map;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	private int id;
	private String name;
	private ArrayList<HashMap<String,Integer>> mapList;
	public Room(int id,String name){
		this.id = id;
		this.name = name;
		mapList = new ArrayList<HashMap<String,Integer>>();
	}
	public void setMapList(int bei,int dong,int nan,int xi){
		HashMap<String,Integer> map  = new HashMap<String,Integer>();
		map.put("北", bei);
		map.put("东", dong);
		map.put("南", nan);
		map.put("西", xi);
		mapList.add(map);
	}
}
