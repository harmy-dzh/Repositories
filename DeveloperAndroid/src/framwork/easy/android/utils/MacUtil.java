package framwork.easy.android.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * @Description: 获取mac地址
 * @author: ethanchiu@126.com
 * @date: Apr 3, 2014
 */
public class MacUtil {
	
	public static String getLocalMacAddress(Context context)
	{
		WifiInfo info = null;
		final WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

		if ( wifi == null || ((info = wifi.getConnectionInfo()) == null) )
		{
			return null;
		}
		
		String macAddress = info.getMacAddress();

		return macAddress == null ? getMacAddress() : macAddress;

	}
	
	public static String getMacAddress()
	{
		try
		{
			return loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String loadFileAsString(String filePath) throws java.io.IOException
	{
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1)
		{
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}
	
}
