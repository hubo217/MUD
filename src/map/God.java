package map;

import role.Player;

public class God {
	private World world = World.getWorld();
	public void createWorld(){
		this.buildWorld();
	}
	private void buildWorld() {
		Room r0 = world.createRoom("综合版",
				"综合版是a岛大陆的中心，村民肥肥在这里无拘无束的生活着");
		Room r1 = world.createRoom("综合二",
				"综合二原来是a岛大陆的最大城市，但在一次天灾后沉入了大海");
		Room r2 = world.createRoom("欢乐恶搞",
				"'来跑团吧'，勇士们说道，他们是最棒的战士！");
		Room r3 = world.createRoom("汪星堡",
				"狗头人在这片黑暗的大陆上即使猎人也是猎物");
		Room r4 = world.createRoom("女装群岛",
				"女装群岛上到处都是信奉路西法的邪教徒");
		Room r5 = world.createRoom("速报山脉",
				"我看这大清国是迟早要玩啊");
		Room r6 = world.createRoom("推理", "推你麻痹！");
		Room r7 = world.createRoom("喵奴共和国", 
				"这里的统治者似乎是猫？他们和狗星堡是敌对呢");
		Room r8 = world.createRoom("码农神界",
				"这里隐居着众多创世神");
		Room r9 = world.createRoom("姐妹",
				"滴滴滴滴滴滴滴滴！");
		Room r10 = world.createRoom("圈内",
				"其实这个字读juan");
		Room r11 = world.createRoom("值班室",
				"如果技术版是神界，那么值班室就是掌管生死的地府");
		r0.setConnectorDir("北", r2);
		r0.setConnectorDir("东", r3);
		r0.setConnectorDir("南", r4);
		r0.setConnectorDir("西", r5);
		r0.setConnectorDir("水下", r1);
		r1.setConnectorDir("地上", r0);
		r2.setConnectorDir("南", r0);
		r2.setConnectorDir("东", r6);
		r3.setConnectorDir("西", r0);
		r3.setConnectorDir("东", r7);
		r4.setConnectorDir("北", r0);
		r4.setConnectorDir("神界", r8);
		r4.setConnectorDir("南", r9);
		r5.setConnectorDir("东", r0);
		r5.setConnectorDir("南", r10);
		r6.setConnectorDir("西", r2);
		r7.setConnectorDir("西", r3);
		r8.setConnectorDir("人间", r4);
		r9.setConnectorDir("北", r4);
		r10.setConnectorDir("北", r5);
		r10.setConnectorDir("南", r11);
		r11.setConnectorDir("值班室", r10);
		
		
		
		
	}
}
