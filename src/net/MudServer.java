package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import utils.Console;

public class MudServer {
	private final int PORT = 999;
	private static ArrayList<Client> clientList = new ArrayList<Client>();
	private ServerSocket serverSocket;

	public MudServer() {
	}
	
	public static ArrayList<Client> getClientList(){
		return clientList;
	}
	public void start(){
		Socket connect;
		Client client;
		ArrayList<Client> outLineList = new ArrayList<Client>();
		try {
			serverSocket = new ServerSocket(PORT);
			
			while(true){
				
				connect = serverSocket.accept();
				client = new Client(connect);
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
	public static void sayToClients(String content){
		Console.log("1");
		for(Client c : clientList){
			Console.log("2");
			if(c.getState() == Console.PLAY){
				Console.log("3");
				c.sendReply(content);
				Console.log("4");
			}
		}
	}
}
