package framwork.easy.android.utils;
public class SystemUIUtil
{
	/**
	 * @Description: 显示导航栏
	 * @date: May 14, 2014
	 */
	public static void showSystemBar() {
		new Thread() {
			public void run() {
				Process su = null;
				try {
					su = Runtime.getRuntime().exec("su");
					String cmd = "am startservice -n com.android.systemui/.SystemUIService\n";
					su.getOutputStream().write(cmd.getBytes());
					String exit = "exit\n";
					su.getOutputStream().write(exit.getBytes());
					su.waitFor();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (su != null) {
						su.destroy();
					}
				}
			};
		}.start();

	}
	
	/**
	 * @Description: 隐藏导航栏
	 * @date: May 14, 2014
	 */
	
	public static void hideSystemBar() {
		new Thread() {
			public void run() {
				Process su = null;
				try {
					su = Runtime.getRuntime().exec("su");
					String cmd = "service call activity 42 s16 com.android.systemui\n";
					su.getOutputStream().write(cmd.getBytes());
					String exit = "exit\n";
					su.getOutputStream().write(exit.getBytes());
					su.waitFor();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (su != null) {
						su.destroy();
					}
				}
			};
		}.start();
	}
}

