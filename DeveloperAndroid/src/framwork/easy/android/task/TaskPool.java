package framwork.easy.android.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import framwork.easy.android.base.BaseApplication;
import framwork.easy.android.config.Tags;

public class TaskPool {
	private static TaskPool mTaskPool;
	private ExecutorService mExecutorService;

	private TaskPool(){
		mExecutorService=BaseApplication.mApplication.getExecutorService();
	}
	public static TaskPool getInstance() {
		if (mTaskPool == null) {
			mTaskPool = new TaskPool();
		}
		return mTaskPool;
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
		}
	};

	public void execute(final Task task) {
		mExecutorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					if (task != null) {
						task.doInBackground();
						Message msg = handler.obtainMessage();
						msg.obj = task;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					Log.e(Tags.TAG_EXCEPTION, e.getMessage(), e);
				}
			}
		});
	}

	private void shutdown() {
		if (!mExecutorService.isTerminated()) {
			mExecutorService.shutdown();
			shutdownListener();
		}
	}

	private void shutdownNow() {
		if (!mExecutorService.isTerminated()) {
			mExecutorService.shutdownNow();
			shutdownListener();
		}
	}

	private void shutdownListener() {
		try {
			while (!mExecutorService.awaitTermination(1, TimeUnit.MILLISECONDS)) {
				Log.d(Tags.TAG_EXCEPTION, "线程池未关闭");
			}
			Log.d(Tags.TAG_EXCEPTION, "线程池已关闭");
		} catch (Exception e) {
			Log.e(Tags.TAG_EXCEPTION, e.getMessage(), e);
		}
	}
}
