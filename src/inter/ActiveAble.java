package inter;
import map.Room;
import abStract.Character;
import abStract.Item;
public interface ActiveAble {
	public void sayToPlayer(String msg);
	
	public void attack(Character enemy);
	
	public void moveToDes(Room r);
	
	public void useItem(String name);
	//
	public void pickUpItem(String name);
	//
	public void addItem(Item i);
	public void setPoint(int ack,int def,int hel,int str,int hit,int spd);


}	