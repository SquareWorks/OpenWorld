package com.squareworks.openworld.client.keyAction;

public class KeyActionEvent{
	
	private KeyAction action;
	private KeyState keyState;
	public KeyActionEvent(KeyAction action, KeyState keyState){
		this.action = action;
		this.keyState = keyState;
	}
	
	public KeyState getKeyState(){
		return keyState;
	}
	
	public KeyAction getKeyAction(){
		return action;
	}
}
