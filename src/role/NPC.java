package role;

import java.io.IOException;
import java.util.HashMap;

import abStract.Character;
import utils.Console;

public class NPC extends Character{
	private boolean isFriendly;
	private HashMap<String,String> tolk;
	public NPC(String name, String des) {
		super(name, des);
		// TODO Auto-generated constructor stub
	}
	public void addItem(){
		
	}
	public void loadTolk(String path){
		try {
			tolk = Console.loadStory(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void beAsked(String s,Player p){
		String content = this.getName() + "说:" + "走开走开";
		if(tolk != null){
			if(tolk.get(s) != null){
				content = this.getName() + "说:" + tolk.get(s);
			}
		}	
		p.sayToPlayer(content);
		
	}
}
