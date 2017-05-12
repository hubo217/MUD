package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import utils.Console;

public class MudServer {
	private final int PORT = 999;
	private ArrayList<Client> clientList = new ArrayList<Client>();
	private ServerSocket serverSocket;

	public MudServer() {
	}
	
	public ArrayList<Client> getClientList(){
		return this.clientList;
	}
	public void start(){
		Socket connect;
		Client client;
		ArrayList<Client> outLineList = new ArrayList<Client>();
		try {
			serverSocket = new ServerSocket(PORT);
			
			while(true){
				
				connect = serverSocket.accept();
				client = new Client(connect,this);
				clientList.add(client);
				Console.log(connect.getInetAddress()+"客户端连接了");
				client.start();
				
				outLineList.clear();
				
				for(Client c : clientList){
					if(c.getState() == Console.OVER){
						outLineList.add(c);
					}

				}
				clientList.removeAll(outLineList);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			if(serverSocket != null){
				try {
					serverSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	public void sayToClients(String content){
		for(Client c : this.clientList){
			if(c.getState() == Console.PLAY){
				c.sendReply(content);
			}
		}
	}
}
