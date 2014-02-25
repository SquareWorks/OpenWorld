package com.squareworks.openworld.client.keyAction;
import org.lwjgl.input.Keyboard;




public class KeyActionContainer {
	private int key;
	private KeyAction action;
	private KeyState state = KeyState.not_held;
	public KeyActionContainer(KeyAction action, int key) {
		this.action = action;
		this.key = key;
	}
	
	private void keyPressed(){
		if(state.equals(KeyState.held) || state.equals(KeyState.pressed)){
			state = KeyState.held;
		}else if(state.equals(KeyState.not_held)){
			state = KeyState.pressed;
		}
	}
	
	private void keyReleased(){
		if(state.equals(KeyState.held)){
			state = KeyState.released;
		}else{
			state = KeyState.not_held;
		}
	}
	
	public void update(KeyActionHandler handler){
		if(Keyboard.isKeyDown(key)){
			keyPressed();
		}else{
			keyReleased();
		}
		handler.dispatch(new KeyActionEvent(action, state));
	}
}
