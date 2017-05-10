package utils;

public class Console {
	public static int OVER = 0x1;
	public static int ERROR = 0x2;
	public static int INIT = 0x3;
	public static int PLAY = 0x4;
	
	public static void log(String content){
		System.out.println(content);
	}
}
