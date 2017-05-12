package core;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import map.Room;
import map.World;
import role.Player;

public class Speaker {
	private HashMap<String,String> orderList;
	private World world = World.getWorld();
	private static Speaker instance = new Speaker();
	
	public Speaker(){
		orderList = new HashMap<String,String>();

		//系统命令
		orderList.put("quit",
				"-quit命令允许你断开与服务器的连接");
		orderList.put("say", "-say命令会将你说的话发送给房间里的每一位玩家");
		orderList.put("setDesc",
				"-setDesc允许你设置你角色的自我介绍");
		//游戏命令
		orderList.put("map",
				"-map显示当前地图");			
		orderList.put("lok", "-look命令将会告诉你这个地图的一些景色");
		orderList.put("mov <地点>",
				"-mov命令后跟随地点的名字参数，可以让玩家控制的角色到达目标地点");
		orderList.put("who",
				"-who命令将会告诉你当前角色的各项属性");
		orderList.put("onl",
				"-onl命令会为你显示当前在线的用户");
		orderList.put("get <item>",
				"-get命令帮助你拾取地图上的物品到你的背包中");
		orderList.put("drp <item>",
				"-drop命令帮助你将背包中的物品扔掉");
		orderList.put("bag",
				"-openbag命令将打开你的背包，展示所有的物品");
		orderList.put("use <item>",
				"-use命令是使用物品，不添加物品名称默认使用物品栏第一个");
		orderList.put("ack <player/NPC>",
				"-attack命令允许你对其他的角色发动攻击");
		orderList.put("gve <player> <item>",
				"-give命令将背包中的物品给予其他玩家");
		orderList.put("shp",
				"-shop命令将打开商店");

		
		//趣味命令
		orderList.put("kickAss <player>",
				"-kickass命令是恶作剧命令，可以踢别的玩家屁股，不会造成任何伤害");

		
	}
	public synchronized void useCommand(Player p,String txt){
		String order = txt.substring(0,3).toLowerCase();
		String parameter = txt.substring(3).trim();
			switch (order) {
			case "map":
				this.showMap(p);
				break;
			case "lok":
				this.lookAround(p);
				break;
			case "who":
				this.showOnlinePlayer(p);
				break;
			case "mov":
				if (!parameter.equals("")) {
					this.movePlayer(p,parameter);
				}
				break;
			default:
				p.sayToPlayer("<系统>我听不懂你在说啥" + "\n\r");
				break;
			}
		}

	private void movePlayer(Player p,String place) {
		Room r = (Room) (p.getLocation());
		HashMap<String,Room> map = r.getConnector().getHashMap();
		if(map.get(place) != null){
			p.moveToDes(map.get(place));
			p.sayToPlayer("<系统>你来到了" + r.getName() + "\n\r");
		}
	}
	private void showOnlinePlayer(Player p) {
		// TODO Auto-generated method stub
		
	}
	private void lookAround(Player p) {
		Room r = (Room) (p.getLocation());
		p.sayToPlayer(r.getDescription() + "\n\r");
		
	}
	private void showMap(Player p) {
		Room r = (Room) (p.getLocation());
		HashMap<String,Room> map = r.getConnector().getHashMap();
		if(map.get("北") != null){
			p.sayToPlayer("<系统>北------>" + map.get("北").getName() + "\n\r");
		}
		if(map.get("南") != null){
			p.sayToPlayer("<系统>南------>" + map.get("北").getName() + "\n\r");
		}
		if(map.get("西") != null){
			p.sayToPlayer("<系统>西------>" + map.get("北").getName() + "\n\r");
		}
		if(map.get("东") != null){
			p.sayToPlayer("<系统>东------>" + map.get("北").getName() + "\n\r");
		}
	}
	public Set<String> getOrderList() {
		return this.orderList.keySet();
	}
	public static Speaker getSpeaker(){
		return instance;
	}
	public World getWorld(){
		return this.world;
	}
}
