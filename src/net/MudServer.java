package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import utils.Console;

public class MudServer {
	private final int PORT = 999;
	private static ArrayList<Client> clientList;
	private ServerSocket serverSocket;
	private Socket connect;
	private Client client;
	public MudServer() {
		MudServer.clientList = new ArrayList<Client>();
	}
	
	public static ArrayList<Client> getClientList(){
		return clientList;
	}
	public void start(){
		ArrayList<Client> outLineList = new ArrayList<Client>();
		try {
			serverSocket = new ServerSocket(PORT);
			
			while(true){
				
				connect = serverSocket.accept();
				client = new Client(connect,this);
				MudServer.clientList.add(client);
				Console.log("一个新用户连接了");
				client.start();
				
				outLineList.clear();
				
				for(Client c : MudServer.clientList){
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
		for(Client c : MudServer.clientList){
			if(c.getState() != Console.ERROR){
				c.sendReply(content);
			}
		}
	}
}
