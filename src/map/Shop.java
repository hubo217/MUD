package map;

import java.util.ArrayList;

import abStract.Item;
import item.Money;

public class Shop extends Room{

	public Shop(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}
	
	public void sellItem(ArrayList<Item> i,Money money){
		int num = i.size();
		int val = i.get(0).value;
		money.getMoney(num * val);
	}
	public void sellItem(Item i,Money money){
		int val = i.value;
		money.getMoney(val);
	}
	
	
	public void buyItem(ArrayList<Item> i,Money money){
		int num = i.size();
		int val = i.get(0).value;
		money.useMoney(num * val);
	}
	public void buyItem(Item i,Money money){
		int val = i.value;
		money.useMoney(val);
	}
	
}
