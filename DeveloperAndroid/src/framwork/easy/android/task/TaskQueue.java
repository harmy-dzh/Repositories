package framwork.easy.android.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import framwork.easy.android.base.BaseApplication;
import framwork.easy.android.config.Tags;

public class TaskQueue extends Thread {
	private static TaskQueue mTaskQueue;
	private ExecutorService mExecutorService;
	private List<Task> mTaskList;
	private boolean mQuit;

	private TaskQueue() {
		mQuit = false;
		mTaskList = new ArrayList<Task>();
		Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
		mExecutorService=BaseApplication.mApplication.getExecutorService();
		mExecutorService.submit(this);
	}

	public static TaskQueue getInstance() {
		if (mTaskQueue == null)
			mTaskQueue = new TaskQueue();
		return mTaskQueue;
	}

	public void execute(Task task) {
		addTask(task);
	}

	public void execute(Task task, boolean isClean) {
		if (isClean) {
			quit();
		}
		addTask(task);
	}

	private synchronized void addTask(Task task) {
		getInstance();
		mTaskList.add(task);
		this.notify();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				Task task = (Task) msg.obj;
				if (task != null) {
					task.onPostExecute();
				}
			} catch (Exception e) {
				Log.e(Tags.TAG_EXCEPTION, e.getMessage(), e);
			}
		};
	};

	@Override
	public void run() {
		while (!mQuit) {
			try {
				while (mTaskList.size() > 0) {
					Task task = mTaskList.remove(0);
					if (task != null) {
						task.doInBackground();
						Message msg = handler.obtainMessage();
						msg.obj = task;
						handler.sendMessage(msg);
					}
					if (mQuit) {
						mTaskList.clear();
						return;
					}
				}
				try {
					synchronized (this) {
						this.wait();
					}
				} catch (Exception e) {
					Log.e(Tags.TAG_EXCEPTION, e.getMessage(), e);
					if (mQuit) {
						mTaskList.clear();
						return;
					}
					continue;
				}
			} catch (Exception e) {
				Log.e(Tags.TAG_EXCEPTION, e.getMessage(), e);
			}
		}
	}

	private void quit() {
		mQuit = true;
		interrupted();
		mTaskQueue = null;
	}
}
