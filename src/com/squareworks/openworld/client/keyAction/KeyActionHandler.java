package com.squareworks.openworld.client.keyAction;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.input.Keyboard;


public class KeyActionHandler {
	private ArrayList<KeyActionContainer> keybinds = new ArrayList<KeyActionContainer>();
	private Map<Method, Object> targets = new HashMap<Method, Object>();
	
	public KeyActionHandler() {
		keybinds.add(new KeyActionContainer(KeyAction.down, Keyboard.KEY_DOWN));
		keybinds.add(new KeyActionContainer(KeyAction.up, Keyboard.KEY_UP));
		keybinds.add(new KeyActionContainer(KeyAction.left, Keyboard.KEY_LEFT));
		keybinds.add(new KeyActionContainer(KeyAction.right, Keyboard.KEY_RIGHT));
		keybinds.add(new KeyActionContainer(KeyAction.zoom_in, Keyboard.KEY_EQUALS));
		keybinds.add(new KeyActionContainer(KeyAction.zoom_out, Keyboard.KEY_MINUS));
		keybinds.add(new KeyActionContainer(KeyAction.zoom_in, Keyboard.KEY_ADD));
		keybinds.add(new KeyActionContainer(KeyAction.zoom_out, Keyboard.KEY_SUBTRACT));
	}
	
	
	public void update(){
		for(KeyActionContainer kContainer : keybinds){
			kContainer.update(this);
		}
	}
	
	public void addListener(Object clazz){
		for(Method m : clazz.getClass().getDeclaredMethods()){
			m.setAccessible(true);
			for(Annotation a : m.getAnnotations()){
				if(a instanceof KeyActionListener.onKeyAction){
					System.out.println(m.getName());
					targets.put(m, clazz);
				}
			}
		}
	}


	public void dispatch(KeyActionEvent keyActionEvent) {
		for(Method m : targets.keySet()){
			KeyActionListener.onKeyAction action = m.getAnnotation(KeyActionListener.onKeyAction.class);
			List<KeyAction> actions = new ArrayList<KeyAction>();
			Collections.addAll(actions, action.actions());
			List<KeyState> states = new ArrayList<KeyState>();
			Collections.addAll(states, action.states());
			if(states.contains(keyActionEvent.getKeyState()) && actions.contains(keyActionEvent.getKeyAction())){
				try {
					m.invoke(targets.get(m), keyActionEvent);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	

}
