package utils;


import map.God;
import net.MudServer;

public class Test {
	
	public static void main(String[] args){
		God god = new God();
		god.createWorld();
		MudServer mudServer = new MudServer();
		mudServer.start();

	}
}
