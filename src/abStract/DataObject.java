package abStract;

import inter.DataItem;
import utils.Console;

public abstract class DataObject implements DataItem {
//DateObject是游戏中基础数据类型，所有的物品都应该继承它，类似于Minecreaft中的Block
	private static final long serialVersionUID = 1L;
	public int databaseRef;
	public DataObject location;
	private String name;
	private String description;


	public DataObject(String name,String des) {
		this.setName(name.replaceAll(" ", "_"));
		this.setDescription(des);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDatabaseRef() {
		return this.databaseRef;
	}

	public void setDatabaseRef(int ref) {
		this.databaseRef = ref;
	}

	public void setDescription(String descrip) {
		this.description = descrip;
	}

	public String getDescription() {
		return this.description;
	}

	public void setLocation(DataObject location) {
		this.location = location;
	}

	public DataObject getLocation() {
		return this.location;
	}

	public String toString() {
		String result = this.getName() + ":" + this.getDatabaseRef();
		return result;
	}


}