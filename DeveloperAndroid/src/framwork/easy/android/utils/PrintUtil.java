package framwork.easy.android.utils;



public class PrintUtil {
	public static void squarePrint(String string) {
		String msg = string;
		String line = "*********************************************************************";
		int length = msg.length();
		line = line.substring(0, length);
		System.out.println("**" + line + "**");
		System.out.println("**" + msg + "**");
		System.out.println("**" + line + "**");
	}
}
