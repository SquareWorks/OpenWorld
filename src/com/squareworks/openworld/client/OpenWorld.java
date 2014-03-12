package com.squareworks.openworld.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.net.MalformedURLException;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.state.StateBasedGame;

import com.squareworks.openworld.client.keyAction.KeyAction;
import com.squareworks.openworld.client.keyAction.KeyActionEvent;
import com.squareworks.openworld.client.keyAction.KeyActionHandler;
import com.squareworks.openworld.client.keyAction.KeyActionListener;
import com.squareworks.openworld.client.keyAction.KeyState;
import com.squareworks.openworld.client.states.Game;
import com.squareworks.openworld.world.TileLoader;

public class OpenWorld extends StateBasedGame implements KeyActionListener {
	public static int GAME = 2;
	public static KeyActionHandler keyHandler;

	public static void main(String[] args) throws SlickException {
		AppGameContainer gameContainer = new AppGameContainer(new OpenWorld(
				"Open World"));
		gameContainer.setDisplayMode(1280, 720, false);
		gameContainer
				.setIcon("C:\\Users\\Owner\\Documents\\GitHub\\OpenWorld\\assets\\openworld\\tiles\\grass.tga");
		gameContainer.start();
	}

	public OpenWorld(String title) {
		super(title);
		keyHandler = new KeyActionHandler();
		keyHandler.addListener(this);
	}
	
	

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		try {
			new ResourceLoader().loadResources();
			new TileLoader().loadResources();
		} catch (SlickException | MalformedURLException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addState(new Game(GAME));

	}

}
