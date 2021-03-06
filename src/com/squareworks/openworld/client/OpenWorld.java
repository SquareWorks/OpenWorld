package com.squareworks.openworld.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;






import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.squareworks.openworld.client.keyAction.KeyActionHandler;
import com.squareworks.openworld.client.keyAction.KeyActionListener;
import com.squareworks.openworld.client.states.Game;
import com.squareworks.openworld.plugins.PluginLoader;
import com.squareworks.openworld.world.TileLoader;

public class OpenWorld extends StateBasedGame implements KeyActionListener {
	public static int GAME = 2;
	public static KeyActionHandler keyHandler;

	public static void main(String[] args) throws SlickException {
		AppGameContainer gameContainer = new AppGameContainer(new OpenWorld(
				"Open World"));
		gameContainer.setDisplayMode(1280, 720, false);
		if (!System.getProperty("os.name").equals("Mac OS X")) {
			gameContainer
					.setIcon("C:\\Users\\Owner\\Documents\\GitHub\\OpenWorld\\assets\\openworld\\tiles\\grass.tga");
		}
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
			new GraphicsLoader().loadResources();
			new TileLoader().loadResources();
			PluginLoader.loadPlugins(new File("plugins"));
		} catch (SlickException | InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addState(new Game(GAME));

	}

}
