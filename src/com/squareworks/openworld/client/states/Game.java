package com.squareworks.openworld.client.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.squareworks.openworld.client.OpenWorld;
import com.squareworks.openworld.client.keyAction.KeyAction;
import com.squareworks.openworld.client.keyAction.KeyActionEvent;
import com.squareworks.openworld.client.keyAction.KeyActionListener.onKeyAction;
import com.squareworks.openworld.client.keyAction.KeyState;

public class Game extends BasicGameState {
	private final int id;
	private double x,y;
	private int xd, yd;

	public Game(int id) {
		this.id = id;
		OpenWorld.keyHandler.addListener(this);
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		OpenWorld.keyHandler.update();
		x += Integer.signum(xd);
		y += Integer.signum(yd);

	}
	@onKeyAction(actions = {KeyAction.down, KeyAction.up, KeyAction.left, KeyAction.right}, states = {KeyState.held, KeyState.pressed})
	private void moveCamera(KeyActionEvent event){
		KeyAction action = event.getKeyAction();
		if(action.equals(KeyAction.down)){
			yd++;
		}else if(action.equals(KeyAction.up)){
			yd--;
		}else if(action.equals(KeyAction.left)){
			xd--;
		}else if(action.equals(KeyAction.right)){
			xd++;
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
