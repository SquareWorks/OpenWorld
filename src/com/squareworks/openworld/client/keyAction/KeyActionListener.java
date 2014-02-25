package com.squareworks.openworld.client.keyAction;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public interface KeyActionListener {
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface onKeyAction{
		KeyState[] states();
		KeyAction[] actions();
	}
}
