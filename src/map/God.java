package map;

import role.Player;

public class God {
	private World world = World.getWorld();
	public void createWorld(){
		this.buildWorld();
	}
	private void buildWorld() {
		Room r0 = world.createRoom("综合小镇",
				"热闹繁华的小镇");
		Room r1 = world.createRoom("农田",
				"一望无际的田野");

		r0.setConnectorDir("north", r1);
		r1.setConnectorDir("south", r0);
		
		
		
	}
}
