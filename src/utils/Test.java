package utils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import DB.DBHelper;
import map.God;
import map.Room;
import map.World;
import net.MudServer;
import role.Player;

public class Test {
	
	public static void main(String[] args){
		God god = new God();
		god.createWorld();
		MudServer mudServer = new MudServer();
		mudServer.start();
	}
}
