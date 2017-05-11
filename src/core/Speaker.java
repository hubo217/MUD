package core;

import java.util.HashMap;
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
		//游戏命令
		//系统命令
		orderList.put("quit",
				"-quit命令允许你断开与服务器的连接");
		orderList.put("map",
				"-map显示当前地图");		
		orderList.put("look", "-look命令将会告诉你这个地图的一些景色");
		orderList.put("say", "-say命令会将你说的话发送给房间里的每一位玩家");
		orderList.put("go <地点>",
				"-go命令后跟随地点的名字参数，可以让玩家控制的角色到达目标地点");
		orderList.put("who",
				"-who命令将会告诉你当前角色的各项属性");
		orderList.put("online",
				"-online命令会为你显示当前在线的用户");
		orderList.put("tell <player> <message>",
				"-tell命令可以让你和其他的在线玩家进行交谈");
		orderList.put("get <item>",
				"-get命令帮助你拾取地图上的物品到你的背包中");
		orderList.put("drop <item>",
				"-drop命令帮助你将背包中的物品扔掉");
		orderList.put("openbag",
				"-openbag命令将打开你的背包，展示所有的物品");
		orderList.put("use <item>",
				"-use命令是使用物品，不添加物品名称默认使用物品栏第一个");
		orderList.put("attack <player/NPC>",
				"-attack命令允许你对其他的角色发动攻击");
		orderList.put("give <player> <item>",
				"-give命令将背包中的物品给予其他玩家");
		orderList.put("setDesc",
				"-setDesc允许你设置你角色的自我介绍");
		orderList.put("shop",
				"-shop命令将打开商店");

		
		//趣味命令
		orderList.put("kickAss <player>",
				"-kickass命令是恶作剧命令，可以踢别的玩家屁股，不会造成任何伤害");

		
	}
	public synchronized void useCommand(Player p,String txt){
		Scanner scanner = new Scanner(txt);
		if (scanner.hasNext()) {
			String command = scanner.next().trim();
			switch (command) {
			case "say":
				if(scanner.hasNextLine()){
					this.say(p,scanner.nextLine().trim());
				}else{
					p.sayToPlayer("你说啥我没听清");
				}
				break;

			default:
				break;
			}
		}
	}
	private void say(Player p, String trim) {
		Room r = (Room) this.world.getDataObj(p.getRoomId());
		r.sayToRoom(trim);
		
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
