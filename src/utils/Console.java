package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Console {

	public static void log(String content){
		System.out.println(content);
	}
	public static String loadFile(String path) {
		File f = new File(path);
		if(f.exists()){
			String str = "";
			FileInputStream input = null;
			try {
				 String tmp = null;
				 BufferedReader br = new BufferedReader(new FileReader(f));
				 while((tmp = br.readLine()) != null){
					 str = str + tmp + "\r\n";
				 }
				 br.close();
				 return str;
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}else{
			return "";
		}
	}
	public static File[] loadFiles(String pathname){
		File f = new File(pathname);
		return f.listFiles();
	}
	@SuppressWarnings("null")
	public static HashMap<String,String> loadFromFile(File f){
		HashMap<String,String> map = new HashMap<String,String>();
		if(f.exists()){
			FileInputStream input = null;
			try {
				 String tmp = null;
				 BufferedReader br = new BufferedReader(new FileReader(f));
				 while((tmp = br.readLine()) != null){
					 map.put(tmp.split("@")[0], tmp.split("@")[1]);
				 }
				 br.close();
				 return map;
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			Console.log("读取文件失败");
			return null;
		}
		return map;
	}
	public static HashMap<String,String> loadStory(String path) throws IOException{
		HashMap<String,String> tolk = new HashMap<String,String>();
		File f = new File(path);
		if(f.exists()){
			String str = "";
			FileInputStream input = null;
			try {
				 String tmp = null;
				 BufferedReader br = new BufferedReader(new FileReader(f));
				 while((tmp = br.readLine()) != null){
					 tolk.put(tmp.split("@")[0], tmp.split("@")[1]);
				 }
				 return tolk;
			} catch (IOException e) {
				e.printStackTrace();
			}
			input.close();
		}else{
			return null;
		}
		return tolk;	
	}
}
