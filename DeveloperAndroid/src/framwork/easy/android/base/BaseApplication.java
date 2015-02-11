package framwork.easy.android.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Application;
import framwork.easy.android.task.TaskPool;
import framwork.easy.android.task.TaskQueue;
import framwork.easy.android.utils.AppUtil;
import framwork.easy.android.utils.CrashHandler;

/**
 * @author duzhihua
 *
 */
public abstract class BaseApplication extends Application {
	public static BaseApplication mApplication;
	private ActivityStackManager mActivityStackManager;
	private ExecutorService mExecutorService;
	private TaskPool mTaskPool;
	private TaskQueue mTaskQueue;

	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
		setmExecutorService(Executors
				.newFixedThreadPool(AppUtil.getNumCores() * 5));
		setmTaskPool(TaskPool.getInstance());
		setmTaskQueue(TaskQueue.getInstance());
		CrashHandler.getinstance().init(this);
	}

	public ActivityStackManager getActivityStackManager() {
		if (mActivityStackManager == null) {
			mActivityStackManager = ActivityStackManager.getInstance();
		}
		return mActivityStackManager;
	}

	public void exitApplication() {
		getActivityStackManager().exitApplication(false);
	}

	public void exitApplication(boolean isbackground) {
		getActivityStackManager().exitApplication(isbackground);
	}

	/**
	 * @return the mExecutorService
	 */
	public ExecutorService getExecutorService() {
		return mExecutorService;
	}

	/**
	 * @param mExecutorService
	 *            the mExecutorService to set
	 */
	private void setmExecutorService(ExecutorService mExecutorService) {
		this.mExecutorService = mExecutorService;
	}

	/**
	 * @return the mTaskPool
	 */
	public TaskPool getTaskPool() {
		return mTaskPool;
	}

	/**
	 * @param mTaskPool
	 *            the mTaskPool to set
	 */
	private void setmTaskPool(TaskPool mTaskPool) {
		this.mTaskPool = mTaskPool;
	}

	/**
	 * @return the mTaskQueue
	 */
	public TaskQueue getTaskQueue() {
		return mTaskQueue;
	}

	/**
	 * @param mTaskQueue
	 *            the mTaskQueue to set
	 */
	private void setmTaskQueue(TaskQueue mTaskQueue) {
		this.mTaskQueue = mTaskQueue;
	}
}
