package framwork.easy.android.utils;

import framwork.easy.android.R;
import framwork.easy.android.R.id;
import framwork.easy.android.R.layout;
import framwork.easy.android.R.style;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author duzhihua
 *
 */
public class DialogUtil extends Dialog {
	private int layoutResID;
	private Button OKBtn;
	private Button CancelBtn;
	private TextView textView;
	private static EditText editText;
	private View view;
	private String string;
	private boolean isOneBtn;
	private boolean isProgress = false;
	private boolean isInput = false;
	private View.OnClickListener okListener;
	private View.OnClickListener cancelListener;
	private static DialogUtil mDialog;

	private DialogUtil(Context context, int theme, int layoutResID,
			String string, boolean isOneBtn) {
		super(context, theme);
		this.layoutResID = layoutResID;
		this.string = string;
		this.isOneBtn = isOneBtn;
	}

	private DialogUtil(Context context, int theme, int layoutResID,
			String string, boolean isOneBtn, boolean isInput) {
		super(context, theme);
		this.layoutResID = layoutResID;
		this.string = string;
		this.isOneBtn = isOneBtn;
		this.isInput = isInput;
	}

	private DialogUtil(Context context, int theme, int layoutResID,
			String string) {
		super(context, theme);
		this.layoutResID = layoutResID;
		this.string = string;
		this.isProgress = true;
	}

	private void setOnclicklistener(View.OnClickListener okListener,
			View.OnClickListener cancelListener) {
		this.okListener = okListener;
		this.cancelListener = cancelListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layoutResID);
		if (isProgress) {
			textView = (TextView) findViewById(R.id.textview);
			textView.setText(string);
		} else {
			OKBtn = (Button) findViewById(R.id.ok_btn);
			CancelBtn = (Button) findViewById(R.id.cancel_btn);
			view = findViewById(R.id.view);
			editText = (EditText) findViewById(R.id.edittext);
			textView = (TextView) findViewById(R.id.textview);
			if (isInput) {
				textView.setVisibility(View.GONE);
				editText.setHint(string);
			} else {
				editText.setVisibility(View.GONE);
				textView.setText(string);
			}
			if (isOneBtn) {
				CancelBtn.setVisibility(View.GONE);
				view.setVisibility(View.GONE);
			} else {
				CancelBtn.setVisibility(View.VISIBLE);
				view.setVisibility(View.VISIBLE);
			}
			OKBtn.setOnClickListener(this.okListener);
			CancelBtn.setOnClickListener(this.cancelListener);
		}
	}

	private static DialogUtil createDialog(Context context, String string,
			boolean singleBtn) {
		DialogUtil toastDialog = new DialogUtil(context, R.style.dialog,
				R.layout.layout_dialog, string, singleBtn);
		Window win = toastDialog.getWindow();
		WindowManager.LayoutParams params = win.getAttributes();
		params.y = 500;
		win.setAttributes(params);
		toastDialog.setCanceledOnTouchOutside(false);
		return toastDialog;
	}

	private static DialogUtil createInputDialog(Context context, String string,
			boolean isOneBtn) {
		DialogUtil inputDialog = new DialogUtil(context, R.style.dialog,
				R.layout.layout_dialog, string, isOneBtn, true);
		Window win = inputDialog.getWindow();
		WindowManager.LayoutParams params = win.getAttributes();
		params.y = 500;
		win.setAttributes(params);
		inputDialog.setCanceledOnTouchOutside(false);
		return inputDialog;
	}

	private static DialogUtil creatProgress(Context context, String string) {
		DialogUtil progressDialog = new DialogUtil(context, R.style.dialog,
				R.layout.layout_progress, string);
		Window win = progressDialog.getWindow();
		WindowManager.LayoutParams params = win.getAttributes();
		params.y = 500;
		win.setAttributes(params);
		progressDialog.setCanceledOnTouchOutside(false);
		return progressDialog;
	}

	public static void showTip(Context context, String tip) {
		if (mDialog != null) {
			mDialog.cancel();
			mDialog = null;
		}
		mDialog = createDialog(context, tip, true);

		mDialog.setOnclicklistener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mDialog != null) {
					mDialog.cancel();
					mDialog = null;
				}
			}
		}, null);

		mDialog.show();
	}

	public static void showDialog(Context context, String tip,
			boolean isOneBtn, View.OnClickListener listener) {
		if (mDialog != null) {
			mDialog.cancel();
			mDialog = null;
		}
		mDialog = createDialog(context, tip, isOneBtn);
		if (isOneBtn) {
			mDialog.setOnclicklistener(listener, null);
		} else {
			mDialog.setOnclicklistener(listener, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mDialog != null) {
						mDialog.cancel();
						mDialog = null;
					}
				}
			});
		}

		mDialog.show();
	}

	public static void showInputDialog(Context context, String hint,
			boolean isOneBtn, View.OnClickListener listener) {
		if (mDialog != null) {
			mDialog.cancel();
			mDialog = null;
		}
		mDialog = createInputDialog(context, hint, isOneBtn);
		if (isOneBtn) {
			mDialog.setOnclicklistener(listener, null);
		} else {
			mDialog.setOnclicklistener(listener, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mDialog != null) {
						mDialog.cancel();
						mDialog = null;
					}
				}
			});
		}

		mDialog.show();
	}

	public static String getEditText() {
		return editText.getText().toString();
	}

	public static void showProgress(Context context, String message) {
		if (mDialog != null) {
			mDialog.cancel();
			mDialog = null;
		}

		mDialog = creatProgress(context, message);

		mDialog.show();
	}

	public static void dismissDialog() {
		if (mDialog != null) {
			mDialog.cancel();
			mDialog = null;
		}
	}
}
