package framwork.easy.android.utils;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;


/**
 * @author duzhihua
 *
 */
public class ShowDelayTip
{
	public static void show(TextView textView, String notice)
	{
		Handler showHandler = new Handler();
		if ( notice != null )
		{
			textView.setText(notice);
		}
		textView.setVisibility(View.VISIBLE);
		showHandler.postDelayed(new HideTextRunnable(textView), 2000);
	}
}

class HideTextRunnable implements Runnable
{
	private View tv;

	public HideTextRunnable(TextView tv)
	{
		this.tv = tv;
	}

	@Override
	public void run()
	{
		this.tv.setVisibility(View.INVISIBLE);
	}
}
