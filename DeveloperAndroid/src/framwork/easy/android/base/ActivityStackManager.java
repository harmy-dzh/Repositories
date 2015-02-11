package framwork.easy.android.base;

import java.util.Stack;

/**
 * 管理ActivityStack的类
 * 
 * @author duzhihua
 *
 */
public class ActivityStackManager {
	private static Stack<BaseActivity> activitystack;
	private static ActivityStackManager mActivitystackManager;

	public static ActivityStackManager getInstance() {
		if (mActivitystackManager == null) {
			mActivitystackManager = new ActivityStackManager();
		}
		return mActivitystackManager;
	}

	public Stack<BaseActivity> getActivityStack() {
		return activitystack;
	}

	public BaseActivity getCurrentActivity() {
		BaseActivity activity = activitystack.lastElement();
		return activity;
	}

	public void addActivity(BaseActivity activity) {
		if (activitystack == null) {
			activitystack = new Stack<BaseActivity>();
		}
		activitystack.add(activity);
	}

	public void removeActivity(BaseActivity activity) {
		if (activitystack == null) {
			activitystack = new Stack<BaseActivity>();
		}
		activitystack.remove(activity);
	}

	public void finishActivity() {
		if (activitystack.isEmpty() || activitystack == null) {
			return;
		}
		BaseActivity activity = activitystack.lastElement();
		finishActivity(activity);
	}

	public void finishActivity(BaseActivity activity) {
		if (activity != null && !activitystack.isEmpty()
				&& activitystack != null) {
			activitystack.remove(activity);
			activity.finish();
		}
	}

	public void finishActivity(Class<?> clazz) {
		if (activitystack.isEmpty() || activitystack == null) {
			return;
		}
		for (BaseActivity activity : activitystack) {
			if (activity.getClass().equals(clazz)) {
				finishActivity(activity);
			}
		}
	}

	public void finishAllActivity() {
		if (activitystack.isEmpty() || activitystack == null) {
			return;
		}
		while (!activitystack.isEmpty()) {
			BaseActivity activity = activitystack.lastElement();
			activitystack.remove(activity);
			activity.finish();
		}
	}

	public void exitApplication(boolean isbackground) {
		finishAllActivity();
		if (!isbackground) {
			System.exit(0);
		}
	}
}
