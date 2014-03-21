package com.squareworks.openworld.client.keyAction;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

public class KeyActionHandler {
	private ArrayList<KeyActionContainer> keybinds = new ArrayList<KeyActionContainer>();
	private ArrayList<KeyActionHook> hooks = new ArrayList<KeyActionHook>();
	
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
	
	public void addListener(KeyActionListener listener){
		for(Method m : listener.getClass().getDeclaredMethods()){
			m.setAccessible(true);
			for(Annotation a : m.getAnnotations()){
				if(a instanceof KeyActionListener.onKeyAction){
					System.out.println(m.getName());
					hooks.add(new KeyActionHook(listener, m));
				}
			}
		}
	}


	public void dispatch(KeyActionEvent keyActionEvent) {
		for(KeyActionHook hook : hooks){
			try {
				hook.invoke(keyActionEvent);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
