package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import core.Speaker;
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

	public int getState() {
		return this.state;
	}
	public void setState(int v){
		this.state = v;
	}

	public void sendReply(String content) {
		
	}

}
