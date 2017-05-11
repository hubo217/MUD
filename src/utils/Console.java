package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	public static int OVER = 0x1;
	public static int ERROR = 0x2;
	public static int INIT = 0x3;
	public static int PLAY = 0x4;
	public static boolean ALLOWPASSWD = true;
	public static void log(String content){
		System.out.println(content);
	}
	public static String loadFile(String path) throws FileNotFoundException{
		File f = new File(path);
		if(f.exists()){
			String str = "";
			FileInputStream input;
			try {
				 String tmp = null;
				 BufferedReader br = new BufferedReader(new FileReader(f));
				 while((tmp = br.readLine()) != null){
					 str = str + tmp + "\r\n";
				 }
				 return str;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}else{
			return "";
		}
	}
}
