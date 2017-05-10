package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import core.Speaker;
import map.Room;
import role.Player;
import utils.Console;

public class Client implements Runnable{
	

	
	private Thread thread;
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Player player;
	private MudServer server;
	private Speaker speaker;
	
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
				if(this.state == Console.INIT){
					this.init();
				}else if(state == Console.PLAY){
					
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void start() {
		this.state = Console.INIT;
		
		try {
			this.output = new ObjectOutputStream(socket.getOutputStream());
			this.input = new ObjectInputStream(socket.getInputStream());
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
		Communication com = new Communication(content);
		try {
			output.writeObject(com);
		} catch (IOException e) {
			close();
		}
	}
//获取用户发送的命令
	public String receiveCom(){
		try {
			String str = ((Communication)this.input.readObject()).getText();
			
			if( str == null){
				return "";
			}else if(str.equalsIgnoreCase("quit")){
				this.close();
				return "";
			}else if(str.equalsIgnoreCase("help")){
				String tmp = "";
				for(String s : speaker.getOrderList()){
					tmp += s + "\n\r";
				}
				this.sendReply(tmp);
				return "";
			}else{
				return str;
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
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
				+ "|    //    \\+:+岛在线跑团！                 |\n\r"
				+ "|                              |\n\r"
				+ "|                              |\n\r"
				+ "|  create by:shichiyu          |\n\r"
				+ "|  QQ:350409012                |\n\r"
				+ "|  回复help获取命令指南                                |\n\r"
				+ "|                              |\n\r"
				+ "|------------------------------|\n\r"
				);
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
