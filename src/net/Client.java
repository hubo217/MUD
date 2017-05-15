package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

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
			while( this.state != ClientState.OVER ){
				synchronized (speaker.getWorld().getObjectLock()) {
					while(speaker.getWorld().getThreadLock()){
						try{
							speaker.getWorld().getObjectLock().wait();
						}catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
				if( this.state == ClientState.INIT ){
					//开始界面
					this.init();
				}else if( this.state == ClientState.PLAY ){
					//游戏界面
					//处理命令
					this.orderHandler();
				}
			}
		}catch (Exception e) {
			this.state = ClientState.ERROR;
		}
	}

	public void start() {
		this.state = ClientState.INIT;
		
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
			this.state = ClientState.OVER;
		}
	}
//获取用户发送的命令
	public String receiveFrom(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String str = br.readLine();
			return str.trim();
		} catch (Exception e) {
			this.state = ClientState.OVER;
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

		this.sendReply(Console.loadFile("welcome.txt"));

		while(!this.passwordConfirmed){
			String username = "";
			String password = "";
			
			//获得用户名
			while(username.equals("")){
				
				this.sendReply("请输入你的用户名:");
				username = receiveFrom().trim();
			}
			//获得密码

			while(password.equals("")){

				this.sendReply("请输入你的密码:");
				password = receiveFrom().trim();

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
		str = receiveFrom();
		if(!str.equals("")){
			str = str.trim();
			//遇到/才解释为命令
			if(str.indexOf("/") == 0){
				if(str.equals("/quit")){
					this.sendReply("已成功下线，请关闭终端");
					this.close();
				}else{
					this.speaker.useCommand(this.player,str);
				}
			}else{
				mudServer.sayToClients(this.player.getName()+":"+str);
			}
		}

	}
	
//关闭客户端连接
	public void close(){
		try {
			this.state = ClientState.OVER;
			this.socket.close();
			Client c = this;
			mudServer.getClientList().remove(c);
			if(c != null){
				if(c.player != null){
					//
					//保存人物
					World.getWorld().savePlayer(c.player);
					//从房间移除人物
					Room r =  (Room) c.player.getLocation();
					r.removePlayer(c.player);
					//从hashmap移除人物
					World.getWorld().playerMap.remove(c.player.getName());
					//从数组中删除
					World.getWorld().delFromWorld(player);
				}
			}
			c = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			this.state = ClientState.PLAY;
			this.sendReply("人物加载成功");
		}else{
			this.sendReply("人物加载失败");
		}
	}


}
