package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import DB.DBHelper;

public class MudServer {
	private final int PORT = 999;
	private ArrayList<Client> clientList = new ArrayList<Client>();
	private ServerSocket serverSocket;
	private DBHelper helper = new DBHelper();
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
				client.start();
				
				outLineList.clear();
				
				for(Client c : clientList){
					if(c.getState() == ClientState.OVER || c.getState() == ClientState.ERROR){
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
	public DBHelper getHelper(){
		return this.helper;
	}
	public void sayToClients2(String content){
		for(Client c : this.clientList){
			if(c.getState() == ClientState.PLAY){
				c.sendReply(content);
			}
		}
	}
	public void sayToClients(String content){
		for(Client c : this.clientList){
			if(c.getState() == ClientState.PLAY){
				c.sendReply("<聊天>"+content);
			}
		}
	}
}
