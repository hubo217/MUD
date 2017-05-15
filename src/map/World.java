package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import javax.print.attribute.HashAttributeSet;

import DB.DBHelper;
import abStract.DataObject;
import abStract.Item;
import role.NPC;
import role.Player;
import utils.Console;

public class World implements Runnable{
	
	public transient Thread saveThread;
	public transient boolean threadLock;
	public transient Object ObjectLock = new Object();
	public transient static World instance = new World();
	public ArrayList<DataObject> databaseArray = new ArrayList<DataObject>();
	public HashMap<String,Player> playerMap = new HashMap<String,Player>();
	public HashMap<String,NPC> NPCMap = new HashMap<String,NPC>();
	public HashMap<String,Room> RoomMap = new HashMap<String,Room>();
	private Set<String> playersLoggedOn = new TreeSet<String>();
	public World(){
		
		this.saveThread = new Thread(this);
		this.saveThread.start();
		
	} 
	
	@Override
	public void run() {
		boolean justStarted = true;
		try {
			for (;;) {

				synchronized (this.ObjectLock) {
					while (this.getThreadLock()) {
						try {
							this.ObjectLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

				if (!justStarted) {
					this.saveWorld();
				}
				if (justStarted) {
					justStarted = false;
				}
				Thread.sleep(100000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void saveWorld() {
		Console.log("游戏保存");
		this.lockThraed();
			//保存世界的代码
		
	}

	public boolean addToWorld(DataObject o){
		boolean re = this.databaseArray.add(o);
		if(re){
			o.setDatabaseRef((this.databaseArray.size()-1));
			Console.log("ID:" + o.getDatabaseRef() + " " + o.getName() + "被创建了");
			return true;
		}
		return false;
	}
	public boolean delFromWorld(DataObject o){
		Console.log("删除"+o.getDatabaseRef());
		return databaseArray.remove(o);
	}
	public void updateWorld(DataObject o,DataObject newO){
		this.databaseArray.add(o.getDatabaseRef(), newO);
	}
	//根据id获取对象
	public DataObject getDataObj(int objID){
		for(DataObject doj : this.databaseArray){
			if(doj.getDatabaseRef() == objID){
				return doj;
			}
		}
		return null;
	}

	public static World getWorld(){
		return instance;
	}
	
	public ArrayList<Player> getPlayers(){
		ArrayList<Player> pl = new ArrayList<>();
		for(Player p : this.playerMap.values()){
			pl.add(p);
		}
		return pl;
	}
	public boolean isPlayerOnLine(String name){
		return this.playerMap.containsKey(name);
	}
	public Room createRoom(String name,String desc){
		Room r = new Room(name,desc);
		if(this.RoomMap.put(r.getName(), r) == null){
			this.addToWorld(r);
			return r;
		}
		return r;
	}
	public Player createPlayer(String name,String des){
		if(this.isPlayerOnLine(name)){
			return null;
		}
		Player p = new Player(name, des);
		Room r = (Room) World.getWorld().RoomMap.get("市政中心");
		p.setLocation(r);
		p.setRoomId(r.getDatabaseRef());
		r.addPlayer(p);
		if(this.playerMap.put(p.getName(), p) == null){
			this.addToWorld(p);
			return p;
		}
		return null;
	}
	public boolean nameExists(String name){
		for(DataObject doj : this.databaseArray){
			if(doj.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	public NPC createNPC(String name,String des,String room){
		if(this.nameExists(name)){
			return null;
		}
		NPC npc = new NPC(name,des);
		Room r = this.RoomMap.get(room);
		if(this.NPCMap.put(npc.getName(), npc) == null){
			this.addToWorld(npc);
			npc.setLocation(r);
			r.addPeople(npc);
			return npc;
		}
		return npc;
	}
	public void addItemToWorld(Item i,DataObject location){
		World.getWorld().addToWorld(i);
		if(location instanceof Room){
			((Room) location).addItem(i);
		}else if(location instanceof Player){
			((Player) location).addItem(i);
		}else if(location instanceof NPC){
			((NPC) location).addItem(i);
		}
		i.setLocation(location);
	}
	//线程
	public void lockThraed(){
		this.threadLock = true;
	}
	public void unlockThread(){
		this.threadLock = false;
	}
	public boolean getThreadLock(){
		return this.threadLock;
	}
	public Object getObjectLock(){
		return this.ObjectLock;
	}

	public void savePlayer(Player player) {
		// TODO Auto-generated method stub
		this.lockThraed();
		DBHelper helper = player.getClient().getServer().getHelper();
		helper.updatePlayer(player);
		this.unlockThread();
	}
	public void savePlayers() {
		for (Player player : this.getPlayers()) {
			this.savePlayer(player);
		}
	}
	public boolean addLoggedOn(String name){
		return this.playersLoggedOn.add(name);
	}
	public boolean removeLoggedOn(String name) {
		// TODO Auto-generated method stub
		return this.playersLoggedOn.remove(name);
	}
	public boolean playerIsLoggedOn(String name){
		return this.playersLoggedOn.contains(name);
	}
	public Set<String> getPlayersLoggedOn(){
		return this.playersLoggedOn;
	}
}
