package framwork.easy.android.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import framwork.easy.android.config.Tags;
import framwork.easy.android.viewinject.ViewInjector;
import framwork.easy.android.viewinject.view.annotation.ContentView;

/**
 * @author duzhihua
 *
 */
public abstract class BaseFragment extends Fragment {
	private int layoutResID;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		onSetContentView();
		View view = getContentView(inflater, container);
		Log.d(Tags.TAG_FRAGMENT, "onCreate--->" + this.getClass().getName());
		ViewInjector.inject(this, view);
		onAfterOnCreate();
		return view;
	}

	/**
	 * 通过注解自动导入布局文件
	 */
	private View getContentView(LayoutInflater inflater, ViewGroup container) {
		Class<?> fragment = this.getClass();
		ContentView contentview = fragment.getAnnotation(ContentView.class);
		if (contentview != null) {
			layoutResID = contentview.value();
		}
		View view = inflater.inflate(layoutResID, container, false);
		return view;
	}

	/**
	 * 在此使用{@link #setContentView(int layoutResID)}指定布局文件, 若此处不使用
	 * {@link #setContentView(int layoutResID)}
	 * ,则需要在Fragment上面加@ContentView(ResId)自动导入布局,两种必须选一种
	 */
	protected abstract void onSetContentView();

	protected abstract void onAfterOnCreate();

	public void setContentView(int id) {
		layoutResID = id;
	}

	@Override
	public void onDestroy() {
		Log.d(Tags.TAG_FRAGMENT, "onDestroy--->" + this.getClass().getName());
		super.onDestroy();
	}
}
