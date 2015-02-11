package framwork.easy.android.viewinject.view.annotation.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.widget.ExpandableListView;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(
		listenerType = ExpandableListView.OnChildClickListener.class,
		listenerSetter = "setOnChildClickListener",
		methodName = "onChildClick")
public @interface OnChildClick {
	int[] value();

	int[] parentId() default 0;
}
