package map;

import java.util.ArrayList;

import abStract.DataObject;
import abStract.Item;

public class World implements Runnable{
	
	private transient Thread saveThread;
	private transient boolean threadLock;
	private transient Object ObjectLock = new Object();
	private transient static World instance = new World();
	private ArrayList<DataObject> databaseArray = new ArrayList<DataObject>();
	
	public World(){
		this.saveThread = new Thread(this);
		this.saveThread.start();
	} 
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean addToWorld(DataObject o){
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
