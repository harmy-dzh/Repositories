package framwork.easy.android.utils;

import framwork.easy.android.R;
import framwork.easy.android.R.drawable;
import framwork.easy.android.R.id;
import framwork.easy.android.R.layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;

/**
 * @author duzhihua
 *
 */
public class PopwindowUtil {
	private View view;
	private PopupWindow mPopupWindow;
	private ListView listview;
	
	private void basePopwindow(Context context,int ResId,int background){
		if (view != null) {
			view = null;
		}
		view = LayoutInflater.from(context)
				.inflate(ResId, null);
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
			mPopupWindow = null;
		}
		// mPopupWindow = new PopupWindow(view, 0, 0, true);
		mPopupWindow = new PopupWindow(view);
		mPopupWindow.setHeight(LayoutParams.WRAP_CONTENT);
		mPopupWindow.setBackgroundDrawable(context.getResources().getDrawable(
				background));
	}

	public void creatSpiner(Context context) {
		basePopwindow(context, R.layout.list_spinner, R.drawable.spinner_bg);
		if (listview != null) {
			listview = null;
		}
		listview = (ListView) view.findViewById(R.id.list);
	}

	public void setListViewAdapter(BaseAdapter adapter) {
		listview.setAdapter(adapter);
	}

	public void showAsDropDown(View view) {
		mPopupWindow.setWidth(view.getWidth());
		mPopupWindow.showAsDropDown(view);
	}

	public void setListOnItemListener(OnItemClickListener listener) {
		listview.setOnItemClickListener(listener);
	}

	public void dismiss() {
		if (mPopupWindow != null && mPopupWindow.isShowing()) {
			mPopupWindow.dismiss();
		}
	}
}
