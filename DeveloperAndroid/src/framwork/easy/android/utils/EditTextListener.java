package framwork.easy.android.utils;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author duzhihua
 *
 */
public class EditTextListener implements TextWatcher
{
	private TextView tipView;
	private EditText et;
	private String digits = "";

	public EditTextListener(EditText et, TextView tipView)
	{
		this.et = et;
		this.tipView = tipView;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
	}

	@Override
	public void afterTextChanged(Editable s)
	{
		String str = s.toString();
		String temp = "";
		if ( hasIllegalChar(str) )
		{
			ShowDelayTip.show(tipView, "请勿输入非法字符");
			for (int i = 0; i < str.length(); i++)
			{
				if ( this.digits.indexOf(str.charAt(i)) < 0 )
				{
					temp = temp + str.charAt(i);
				}
			}
			this.et.setText(temp);
			CharSequence text = this.et.getText();
			if ( text instanceof Spannable )
			{
				Spannable spanText = (Spannable) text;
				Selection.setSelection(spanText, text.length());
			}
		}
	}

	private boolean hasIllegalChar(String input)
	{
		boolean b = false;
		for (int i = 0; i < input.length(); i++)
		{
			if ( digits.indexOf(input.charAt(i)) >= 0 )
			{
				b = true;
				return b;
			}
		}
		return b;
	}
}
