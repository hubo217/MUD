package map;

import abStract.DataObject;
import abStract.Item;

public class World implements Runnable{

	private transient Thread saveThread;
	private transient boolean threadLock;
	private transient Object ObjectLock = new Object();
	private transient static World instance = new World();
	
	
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
}
