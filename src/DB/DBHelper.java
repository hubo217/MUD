package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import map.World;
import role.Player;
import utils.Console;


public class DBHelper {
	private String url = "jdbc:mysql://118.190.74.250:3306/mud";
	private String classname = "com.mysql.jdbc.Driver";
	private String username = "";
	private String password = "";//不告诉你=
	
	private Connection conn = null;
	private Statement stmt = null;
	
	public DBHelper(){
		try {
			Class.forName(classname);
			Console.log("加载驱动成功");
		} catch (ClassNotFoundException e1) {
			Console.log("加载驱动失败:" + e1);
		}

	}
	private void connect(){
		try {
			conn = DriverManager.getConnection(url,username,password);
			stmt = conn.createStatement();
			Console.log("数据库连接成功");
		} catch (SQLException e) {
			Console.log("连接数据库失败");
			e.printStackTrace();
		}	
	}
	private void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean login(String name,String passwd){
		this.connect();
		String sql = "select passwd from player where name=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet re = pst.executeQuery();
			if(!re.wasNull()&&re.next()){
				if(re.getString(1).equals(passwd)){
					Console.log("验证登录成功");
					return true;
				}
				re.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		this.close();
		return false;
	}
	public Player loadPlayer(String name){
		this.connect();
		Player p = null;
		String sql = "select * from player where name=?";
		ResultSet re = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			re = pst.executeQuery();
			if(re.next()){
				p = World.getWorld().createPlayer(re.getString(2), re.getString(4));
				int hel = re.getInt(5);
				int ack = re.getInt(6);
				int def = re.getInt(7);
				int str = re.getInt(8);
				int hit = re.getInt(9);
				int spd = re.getInt(10);
				int exp = re.getInt(11);
				p.setPoint(hel, ack, def, str, hit, spd, exp, 0);
			}
			re.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return p;
	}
	public void updatePlayer(Player player) {
		this.connect();
		this.close();
	}

}
