package framwork.easy.android.utils;

import java.io.File;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

public class SystemInfoUtil
{
	// 获取本机容量信息
	/**
	 * @Title getPhoneMBCapacity
	 * @Description 返回： 可用MB/总MB
	 * @author du110zhihua@hotmail.com
	 * @return
	 * @return String 返回类型
	 */
	public static String getPhoneMBCapacity()
	{
		// 获取本机信息
		File data = Environment.getDataDirectory();
		StatFs statFs = new StatFs(data.getPath());
		long availableBlocks = statFs.getAvailableBlocks();// 可用存储块的数量
		long blockCount = statFs.getBlockCount();// 总存储块的数量

		long size = statFs.getBlockSize();// 每块存储块的大小

		long totalSize = blockCount * size;// 总存储量

		long availableSize = availableBlocks * size;// 可用容量

		String phoneCapacity = String.valueOf(availableSize / 1024 / 1024) + "MB/"
				+ String.valueOf(totalSize / 1024 / 1024) + "MB";

		return phoneCapacity;
	}

	// 获取sdcard容量信息
	/**
	 * @Title getSdcardMBCapacity
	 * @Description 返回： 可用MB/总MB
	 * @author du110zhihua@hotmail.com
	 * @return
	 * @return String 返回类型
	 */
	public static String getSdcardMBCapacity()
	{
		// 获取sdcard信息
		File sdData = Environment.getExternalStorageDirectory();
		StatFs sdStatFs = new StatFs(sdData.getPath());

		long sdAvailableBlocks = sdStatFs.getAvailableBlocks();// 可用存储块的数量
		long sdBlockcount = sdStatFs.getBlockCount();// 总存储块的数量
		long sdSize = sdStatFs.getBlockSize();// 每块存储块的大小
		long sdTotalSize = sdBlockcount * sdSize;
		long sdAvailableSize = sdAvailableBlocks * sdSize;

		String sdcardCapacity = String.valueOf(sdAvailableSize / 1024 / 1024) + "MB/"
				+ String.valueOf(sdTotalSize / 1024 / 1024) + "MB";
		return sdcardCapacity;
	}

	// 获取本机容量信息
	/**
	 * @Title getPhoneAvaCapacity
	 * @Description 返回： long 可用容量，单位MB
	 * @author du110zhihua@hotmail.com
	 * @return
	 * @return long 返回类型
	 */
	public static long getPhoneAvaCapacity()
	{
		// 获取本机信息
		File data = Environment.getDataDirectory();
		StatFs statFs = new StatFs(data.getPath());
		long availableBlocks = statFs.getAvailableBlocks();// 可用存储块的数量
		long blockCount = statFs.getBlockCount();// 总存储块的数量

		long size = statFs.getBlockSize();// 每块存储块的大小

		long totalSize = blockCount * size;// 总存储量

		long availableSize = availableBlocks * size;// 可用容量

		long phoneCapacity = availableSize / 1024 / 1024;

		return phoneCapacity;
	}

	// 获取sdcard容量信息
	/**
	 * @Title getSdcardAvaCapacity
	 * @Description 返回： long 可用容量，单位MB
	 * @author du110zhihua@hotmail.com
	 * @return
	 * @return long 返回类型
	 */
	public static long getSdcardAvaCapacity()
	{
		// 获取sdcard信息
		File sdData = Environment.getExternalStorageDirectory();
		StatFs sdStatFs = new StatFs(sdData.getPath());

		long sdAvailableBlocks = sdStatFs.getAvailableBlocks();// 可用存储块的数量
		long sdBlockcount = sdStatFs.getBlockCount();// 总存储块的数量
		long sdSize = sdStatFs.getBlockSize();// 每块存储块的大小
		long sdTotalSize = sdBlockcount * sdSize;
		long sdAvailableSize = sdAvailableBlocks * sdSize;

		long sdcardCapacity = sdAvailableSize / 1024 / 1024;
		return sdcardCapacity;
	}

	// 获取本机容量信息
	/**
	 * @Title getPhoneCapacity
	 * @Description 返回： long 总容量，单位MB
	 * @author du110zhihua@hotmail.com
	 * @return
	 * @return long 返回类型
	 */
	public static long getPhoneCapacity()
	{
		// 获取本机信息
		File data = Environment.getDataDirectory();
		StatFs statFs = new StatFs(data.getPath());
		long availableBlocks = statFs.getAvailableBlocks();// 可用存储块的数量
		long blockCount = statFs.getBlockCount();// 总存储块的数量

		long size = statFs.getBlockSize();// 每块存储块的大小

		long totalSize = blockCount * size;// 总存储量

		long availableSize = availableBlocks * size;// 可用容量

		long phoneCapacity = totalSize / 1024 / 1024;

		return phoneCapacity;
	}

	// 获取sdcard容量信息
	/**
	 * @Title getSdcardCapacity
	 * @Description 返回： long 总容量，单位MB
	 * @author du110zhihua@hotmail.com
	 * @return
	 * @return long 返回类型
	 */
	public static long getSdcardCapacity()
	{
		// 获取sdcard信息
		File sdData = Environment.getExternalStorageDirectory();
		StatFs sdStatFs = new StatFs(sdData.getPath());

		long sdAvailableBlocks = sdStatFs.getAvailableBlocks();// 可用存储块的数量
		long sdBlockcount = sdStatFs.getBlockCount();// 总存储块的数量
		long sdSize = sdStatFs.getBlockSize();// 每块存储块的大小
		long sdTotalSize = sdBlockcount * sdSize;
		long sdAvailableSize = sdAvailableBlocks * sdSize;

		long sdcardCapacity = sdTotalSize / 1024 / 1024;
		return sdcardCapacity;
	}

	/**
	 * @Title getTotalAvaCapacity
	 * @Description 返回： long 可用容量 内置+SD卡，单位MB
	 * @author du110zhihua@hotmail.com
	 * @return
	 * @return long 返回类型
	 */
	public static long getTotalAvaCapacity()
	{
		long TotalCapacity = getPhoneAvaCapacity() + getSdcardAvaCapacity();
		return TotalCapacity;
	}
	public static long getTotalCapacity(){
		long TotalCapacity = getPhoneCapacity() + getSdcardCapacity();
		return TotalCapacity;
	}

	// 方式二，使用ActivityManager的getMemoryInfo(ActivityManager.MemoryInfo outInfo)
	// ActivityManager.getMemoryInfo()主要是用于得到当前系统剩余内存的及判断是否处于低内存运行。
	// 实例1：
	// private void displayBriefMemory() {
	// final ActivityManager activityManager = (ActivityManager)
	// getSystemService(ACTIVITY_SERVICE);
	// ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
	// activityManager.getMemoryInfo(info);
	// Log.i(tag,"系统剩余内存:"+(info.availMem >> 10)+"k");
	// Log.i(tag,"系统是否处于低内存运行："+info.lowMemory);
	// Log.i(tag,"当系统剩余内存低于"+info.threshold+"时就看成低内存运行");
	// }
	// ActivityManager.getMemoryInfo()是用ActivityManager.MemoryInfo返回结果，而不是Debug.MemoryInfo，他们不一样的。
	// ActivityManager.MemoryInfo只有三个Field:
	// availMem:表示系统剩余内存
	// lowMemory：它是boolean值，表示系统是否处于低内存运行
	// hreshold：它表示当系统剩余内存低于好多时就看成低内存运行
	// 方式三，在代码中使用Debug的getMemoryInfo(Debug.MemoryInfo
	// memoryInfo)或ActivityManager的MemoryInfo[] getProcessMemoryInfo(long[]
	// pids)
	// 该方式得到的MemoryInfo所描述的内存使用情况比较详细.数据的单位是KＢ.
	// MemoryInfo的Field如下
	// dalvikPrivateDirty： The private dirty pages used by dalvik。
	// dalvikPss ：The proportional set size for dalvik.
	// dalvikSharedDirty ：The shared dirty pages used by dalvik.
	// nativePrivateDirty ：The private dirty pages used by the native heap.
	// nativePss ：The proportional set size for the native heap.
	// nativeSharedDirty ：The shared dirty pages used by the native heap.
	// otherPrivateDirty ：The private dirty pages used by everything else.
	// otherPss ：The proportional set size for everything else.
	// otherSharedDirty ：The shared dirty pages used by everything else.
	// Android和Linux一样有大量内存在进程之间进程共享。某个进程准确的使用好多内存实际上是很难统计的。
	// 因为有paging out to disk（换页），所以如果你把所有映射到进程的内存相加，它可能大于你的内存的实际物理大小。
	// dalvik：是指dalvik所使用的内存。
	// native：是被native堆使用的内存。应该指使用C\C++在堆上分配的内存。
	// other:是指除dalvik和native使用的内存。但是具体是指什么呢？至少包括在C\C++分配的非堆内存，比如分配在栈上的内存。puzlle!
	// private:是指私有的。非共享的。
	// share:是指共享的内存。
	// PSS：实际使用的物理内存（比例分配共享库占用的内存）
	// Pss：它是把共享内存根据一定比例分摊到共享它的各个进程来计算所得到进程使用内存。网上又说是比例分配共享库占用的内存，那么至于这里的共享是否只是库的共享，还是不清楚。
	// PrivateDirty：它是指非共享的，又不能换页出去（can not be paged to disk
	// ）的内存的大小。比如Linux为了提高分配内存速度而缓冲的小对象，即使你的进程结束，该内存也不会释放掉，它只是又重新回到缓冲中而已。
	// SharedDirty:参照PrivateDirty我认为它应该是指共享的，又不能换页出去（can not be paged to disk
	// ）的内存的大小。比如Linux为了提高分配内存速度而缓冲的小对象，即使所有共享它的进程结束，该内存也不会释放掉，它只是又重新回到缓冲中而已。

	public static void displayBriefMemory(Context context)
	{
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(info);
		System.out.println("系统剩余内存:" + (info.availMem >> 10) + "k");
		System.out.println("系统是否处于低内存运行：" + info.lowMemory);
		System.out.println("当系统剩余内存低于" + info.threshold + "时就看成低内存运行");
	}
}
