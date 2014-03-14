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
import com.squareworks.openworld.world.World;

public class Game extends BasicGameState {
	private final int id;
	private double x,y, scale = 1.0;
	private int xD, yD, scaleD;
	World world;

	public Game(int id) {
		this.id = id;
		OpenWorld.keyHandler.addListener(this);
		this.world = new World(0xbada55);
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		Graphics g = new Graphics();
		world.draw(g, gc, x, y, scale);
		arg2.setCurrent(g);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta)
			throws SlickException {
		OpenWorld.keyHandler.update();
		x += Integer.signum(xD) * (delta/10.0)  * (1/scale);
		y += Integer.signum(yD) * (delta/10.0) * (1/scale);
		scale += Integer.signum(scaleD) * (delta/10000.0);
		if(x > 0){
			x = 0;
		}
		if(y > 0){
			y = 0;
		}
		xD = 0;
		yD = 0;
		scaleD = 0;

	}
	@onKeyAction(actions = {KeyAction.down, KeyAction.up, KeyAction.left, KeyAction.right, KeyAction.zoom_in, KeyAction.zoom_out}, states = {KeyState.held, KeyState.pressed})
	private void moveCamera(KeyActionEvent event){
		KeyAction action = event.getKeyAction();
		if(action.equals(KeyAction.down)){
			yD--;
		}else if(action.equals(KeyAction.up)){
			yD++;
		}else if(action.equals(KeyAction.left)){
			xD++;
		}else if(action.equals(KeyAction.right)){
			xD--;		
		}
		
		if(action.equals(KeyAction.zoom_in)){
			scaleD++;
		}else if(action.equals(KeyAction.zoom_out)){
			scaleD--;
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
