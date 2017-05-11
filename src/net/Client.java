package net;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	private OutputStream output;
	private InputStream input;
	private Player player;
	private MudServer server;
	private Speaker speaker;
	private boolean passwordConfirmed = false;
	private int state;
	public Client(Socket connect, MudServer mudServer) {
		this.socket = connect;
		this.server = mudServer;
	}

	@Override
	public void run() {
		try{
			while( this.state != Console.OVER ){
				synchronized (input) {
					
				}
				if( this.state == Console.INIT ){
					//开始界面
					this.init();
				}else if( this.state == Console.PLAY ){
					//游戏界面
					String order = this.receiveFrom();
					//处理命令
					
					//返回信息
					this.sendReply( order + "\n\r" );
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
//将字符串发送
	public void sendReply(String content) {
		try {
			output.write(content.getBytes());
		} catch (IOException e) {
			close();
		}
	}
//获取用户发送的命令
	public String receiveFrom(){
		try {
			int size;
			StringBuffer request = new StringBuffer(1024);
			byte[] buffer = new byte[1024];
			size = input.read(buffer);
			for (int i = 0; i < size; i++) {
				request.append((char) buffer[i]);
			}
			String str = request.toString();
			return str;
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
		//密码验证\还没有数据库，暂时不生效
		while(!this.passwordConfirmed){
			String username = "";
			String password = "";
			//获得用户名
			while(username.equals("")){
				this.sendReply("请输入你的用户名:\n\r");
				username = receiveFrom().trim();
			}
			//获得密码
			while(password.equals("")){
				this.sendReply("请输入你的密码:\n\r");
				password = receiveFrom().trim();
			}
			//验证
			if(true){
				this.sendReply("登录成功\n\r");
				//读取玩家、创建玩家
				Player p = World.getWorld().createPlayer("test", "test");
				this.state = Console.PLAY;
				this.passwordConfirmed = true;

				//游戏开始
				this.sendReply(p.getName() + "从梦中醒来！新的一天开始了\n\r"
						+"这里是"+((Room)p.getLocation()).getName()+"\n\r");
				return;
			}else{
				this.sendReply("(つд⊂)用户名或密码错误\n\r");
			}
		}
	}
//关闭客户端连接
	public void close(){
		this.state = Console.OVER;
		Client c = this;
		MudServer.getClientList().remove(this);
		if(c != null){
			if(c.player != null){
				c.server.sayToClients(c.player.getName()+"下线了");
				c.player.setClient(null);
				Room r =  (Room) c.player.getLocation();
				//保存人物
				r.removePlayer(c.player);
				//移除物品
				
				//还需要下线、
				
			}
			c = null;
		}	
	}

}
