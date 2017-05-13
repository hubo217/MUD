package map;

import java.io.File;

import item.Clothes;
import item.Weapon;
import role.NPC;
import role.Player;

public class God {
	private World world = World.getWorld();
	public void createWorld(){
		this.buildWorld();
	}
	private void buildWorld() {
		//房间
		Room r0 = world.createRoom("综合小镇",
				"热闹繁华的小镇");
		Room r1 = world.createRoom("农田",
				"一望无际的田野");

		r0.setConnectorDir("north", r1);
		r1.setConnectorDir("south", r0);
		//武器
		Weapon w0 = new Weapon("妖刀罪歌", "拿上这把剑，你就能lol五杀！");
		w0.setWeaponAttri(99, 99, 99, 99, 9999);
		//衣服
		Clothes c0 = new Clothes("什么都没穿", "光着身子裸着腚");
		//物品
		//NPC
		NPC n0 = world.createNPC("ATM", "匿名版的掌管者，但是在这里也不过是npc而已", r0);
		n0.loadTolk("story" + File.separatorChar + "atm.txt");
		n0.setClothes(c0);
		n0.setWeapon(w0);
	}
}
