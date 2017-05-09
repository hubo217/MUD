package map;

import java.util.ArrayList;
import java.util.HashMap;

import javax.print.attribute.HashAttributeSet;

import abStract.DataObject;
import abStract.Item;
import role.NPC;
import role.Player;

public class World implements Runnable{
	
	private transient Thread saveThread;
	private transient boolean threadLock;
	private transient Object ObjectLock = new Object();
	private transient static World instance = new World();
	private ArrayList<DataObject> databaseArray = new ArrayList<DataObject>();
	private int objNumber;
	private HashMap<String,Player> playerMap = new HashMap<String,Player>();
	private HashMap<String,NPC> enemyMap = new HashMap<String,NPC>();
	
	public World(){
		this.saveThread = new Thread(this);
		this.saveThread.start();
	} 
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean addToWorld(DataObject o){
		if(databaseArray.add(o)){
			return true;
		}
		return false;
	}
	public boolean delFromWorld(DataObject o){
		return false;
	}
	//遍历数据数组，根据对象id找到对象
	public DataObject getDataObj(int objID){
		for(DataObject doj : this.databaseArray){
			if(doj.getDatabaseRef() == objID){
				return doj;
			}
		}
		return null;
	}
	//获得世界的实例
	public static World getWorld(){
		return instance;
	}
}
