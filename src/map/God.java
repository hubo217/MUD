package map;

import role.Player;

public class God {
	private World world = World.getWorld();
	public void createWorld(){
		this.buildWorld();
	}
	private void buildWorld() {
		Room r0 = world.createRoom("综合小镇",
				"");
		Room r1 = world.createRoom("农田",
				"");

		r0.setConnectorDir("北", r1);
		r1.setConnectorDir("南", r0);
		
		
		
	}
}
