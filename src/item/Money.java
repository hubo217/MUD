package item;

import abStract.Item;

public class Money extends Item{
//钱是一般等价物，也属于物品

	public Money(String name,int v) {
		super(name);
		super.value = v;
	}
	
	public void useMoney(int v){
		super.value = value - v;
	}
	
	public void getMoney(int v){
		super.value = value + v;
	}
	
	public int showMoney(){
		return super.value;
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
