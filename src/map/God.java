package map;

import java.io.File;
import java.util.HashMap;

import item.Clothes;
import item.Weapon;
import role.NPC;
import utils.Console;

public class God {
	private World world = World.getWorld();
	public void createWorld(){
		this.buildWorld();
	}
	private void buildWorld() {
		//房间
		for(File f :Console.loadFiles("room")){
			if(!f.isDirectory()){
				HashMap<String,String> map = Console.loadFromFile(f);
				Room r = World.getWorld().createRoom(map.get("name"), map.get("desc"));
				if(map.get("south") != null){
					r.setConnectorDir("south", map.get("south"));
				}
				if(map.get("north") != null){
					r.setConnectorDir("north", map.get("north"));
				}
				if(map.get("west") != null){
					r.setConnectorDir("west", map.get("west"));
				}
				if(map.get("east") != null){
					r.setConnectorDir("east", map.get("east"));
				}
			}
		}
	
		//武器
		Weapon w0 = new Weapon("妖刀罪歌", "拿上这把剑，你就能lol五杀！");
		w0.setWeaponAttri(99, 99, 99, 99, 9999);
		//衣服
		Clothes c0 = new Clothes("什么都没穿", "光着身子裸着腚");
		//物品
		//NPC
		for(File f :Console.loadFiles("npc")){
			if(!f.isDirectory()){
				HashMap<String,String> map = Console.loadFromFile(f);
				NPC npc = World.getWorld().createNPC(map.get("name"), map.get("desc"), map.get("place"));
			}
		}
	}
}
