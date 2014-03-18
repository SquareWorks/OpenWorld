package com.squareworks.openworld.client.keyAction;

import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.script.Invocable;

public class KeyActionHook {
	private Method invoke;
	private KeyActionListener invoker;
	private List<KeyAction> actions;
	private List<KeyState> states;
	
	public KeyActionHook(KeyActionListener invoker, Method invoke){
		this.invoker = invoker;
		this.invoke = invoke;
		KeyActionListener.onKeyAction annotation = invoke.getAnnotation(KeyActionListener.onKeyAction.class);
		actions = Arrays.asList(annotation.actions());
		states = Arrays.asList(annotation.states());
	}
	
	public void invoke(KeyActionEvent event) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if(actions.contains(event.getKeyAction()) && states.contains(event.getKeyState())){
			invoke.invoke(invoker, event);
		}
	}

}
