package inter;

import java.io.Serializable;

import abStract.DataObject;

public interface DataItem extends Serializable{

	public String getName();//获得名字
	public void setName(String name);//设置名字
	public int getDatabaseRef();
	public void setDatabaseRef(int ref);
	public void setDescription(String descrip);
	public String getDescription();
	public void setLocation(DataObject location);
	public DataObject getLocation();
	public String toString();
}
