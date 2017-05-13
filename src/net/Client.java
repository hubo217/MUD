package net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import core.Speaker;
import map.Room;
import map.World;
import role.Player;
import utils.Console;

public class Client implements Runnable{
	

	
	private Thread thread;
	private Socket socket;
	private MudServer mudServer;
	private OutputStream output;
	private InputStream input;
	private Player player;
	private Speaker speaker;
	private boolean passwordConfirmed = false;
	private int state;
	public Client(Socket connect,MudServer server) {
		this.socket = connect;
		this.mudServer = server;
		this.speaker = Speaker.getSpeaker();
	}

	@Override
	public void run() {
		try{
			while( this.state != Console.OVER ){
				synchronized (speaker.getWorld().getObjectLock()) {
					while(speaker.getWorld().getThreadLock()){
						try{
							speaker.getWorld().getObjectLock().wait();
						}catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
				if( this.state == Console.INIT ){
					//开始界面
					this.init();
				}else if( this.state == Console.PLAY ){
					//游戏界面
					//处理命令
					this.orderHandler();
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void start() {
		this.state = Console.INIT;
		
		try {
			this.output = socket.getOutputStream();
			this.input = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		this.thread = new Thread(this);
		thread.start();
		
	}
//获取client状态
	public int getState() {
		return this.state;
	}
//设置状态
	public void setState(int v){
		this.state = v;
	}
	public Player getPlayer(){
		return this.player;
	}
	public MudServer getServer(){
		return this.mudServer;
	}
//将字符串发送
	public void sendReply(String content) {
		String str = content + "\n\r";
		try {
			output.write(str.getBytes("utf-8"));
			output.flush();
		} catch (IOException e) {
			close();
		}
	}
//获取用户发送的命令
	public String receiveFrom(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String str = br.readLine();
			return str.trim();
		} catch (Exception e) {
			close();
		}
		return "";
	}
//初始化
	private void init(){
		this.sendReply(
				  "|------------------------------|\n\r"
				+ "|       /\\                     |\n\r"
				+ "|      //\\                     |\n\r"
				+ "|     //==\\                    |\n\r"
				+ "|    //    \\+:+岛在线跑团！    |\n\r"
				+ "|                              |\n\r"
				+ "|                              |\n\r"
				+ "|                              |\n\r"
				+ "|                              |\n\r"
				+ "|                              |\n\r"
				+ "|      version:0.1             |\n\r"
				+ "|------------------------------|\n\r"
				);
		//读取公告
		try {
			this.sendReply(Console.loadFile("welcome.txt"));
		} catch (FileNotFoundException e) {
		}

		while(!this.passwordConfirmed){
			String username = "";
			String password = "";
			//获得用户名
			while(username.equals("")){
				this.sendReply("请输入你的用户名:");
				username = receiveFrom().trim();
				Console.log(username);
			}
			//获得密码
			while(password.equals("")){
				this.sendReply("请输入你的密码:");
				password = receiveFrom().trim();
				Console.log(password);
			}
			//验证
			if(verifyLogin(username, password)){

				//读取玩家、创建玩家
				this.initPlayer(username);

				//游戏开始
				this.sendReply(player.getName() + "从梦中醒来！新的一天开始了"+"\n\r"
						+"这里是"+((Room)player.getLocation()).getName());
				return;				
			}else{
				this.sendReply("(つд⊂)用户名或密码错误");
			}
		}
	}
//系统命令直接由服务器处理，然后再转交演讲者类处理
	private void orderHandler(){
		String str;
		str = receiveFrom().trim();
		if(str.equals("quit")){
			this.sendReply("已成功下线，请关闭终端");
			this.close();
		}else if(str.toLowerCase().indexOf("say") == 0){
			mudServer.sayToClients(this.player.getName()+":"+str.substring(3).trim());
		}else if(str.toLowerCase().indexOf("setDesc") == 0){
			this.player.setDescription(str.substring(7).trim());
		}else if(!str.equals("") || str != null){
//			this.player.sayToPlayer("test");
			this.speaker.useCommand(this.player,str);
		}
	}
	
//关闭客户端连接
	public void close(){
		this.state = Console.OVER;
		Client c = this;
		mudServer.getClientList().remove(this);
		if(c != null){
			if(c.player != null){
				c.mudServer.sayToClients(c.player.getName() + "下线了");
				c.player.setClient(null);
				Room r =  (Room) c.player.getLocation();
				r.removePlayer(c.player);
				//保存人物
				World.getWorld().savePlayer(c.player);
				World.getWorld().removeLoggedOn(c.player.getName());
				//移除物品
				
				//还需要下线、
				
			}
			World.getWorld().playerMap.remove(c.player);
			World.getWorld().delFromWorld(player);
			c = null;
		}	
	}
//验证登录
	private boolean verifyLogin(String name,String passwd){
		if(mudServer.getHelper().login(name, passwd)){
			this.passwordConfirmed = true;
			return true;
		}
		return false;
	}
//创建玩家对象
	private void initPlayer(String username){
			Console.log("开始加载人物");
		this.player = mudServer.getHelper().loadPlayer(username);
		if(this.player != null){
			this.player.setClient(this);
			this.state = Console.PLAY;
			this.sendReply("人物加载成功");
		}else{
			this.sendReply("人物加载失败");
		}
	}


}
