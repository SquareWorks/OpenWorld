package com.squareworks.openworld.client.states;

import java.lang.reflect.InvocationTargetException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.squareworks.openworld.client.GUI.GUI;
import com.squareworks.openworld.client.GUI.GUIContainer;
import com.squareworks.openworld.plugins.PluginLoader;

public abstract class GUIGameState extends BasicGameState {
	protected GUIContainer gui;
	public GUIGameState() {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		gui = new GUIContainer();
		try {
			Class[] types = {GUIGameState.class};
			PluginLoader.invokePluginMethod("enterState", types, this);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.enter(container, game);
	}
	
	public GUIContainer getGUIContainter(){
		return gui;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		gui.render(arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		gui.update(arg2);
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		gui.mouseClicked(button, x, y, clickCount);
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		gui.mouseDragged(oldx, oldy, newx, newy);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		gui.mouseMoved(oldx, oldy, newx, newy);
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		gui.mousePressed(button, x, y);
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
		gui.mouseReleased(button, x, y);
	}

}
