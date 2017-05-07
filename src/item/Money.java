package item;

import abStract.Item;

public class Money extends Item{
//钱是一般等价物，也属于物品



	public Money(String name, String des) {
		super(name, des);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDatabaseRef() {
		// TODO Auto-generated method stub
		return 0;
	}
}
