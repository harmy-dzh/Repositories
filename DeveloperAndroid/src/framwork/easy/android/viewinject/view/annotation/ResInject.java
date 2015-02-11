package framwork.easy.android.viewinject.view.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import framwork.easy.android.viewinject.view.ResType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResInject {
	int value();

	ResType type();
}
