package base.bin;

import java.util.Properties;

import org.newdawn.slick.geom.Point;

import base.bin.gui.ResourceGUI;

import com.squareworks.openworld.client.OpenWorld;
import com.squareworks.openworld.client.states.GUIGameState;
import com.squareworks.openworld.client.states.Game;
import com.squareworks.openworld.plugins.Plugin;

public class BasePlugin extends Plugin {

	public BasePlugin(Properties properties) {
		super(properties);
	}
	
	@Override
	public void enterState(GUIGameState state) {
		if(state.getID() == OpenWorld.GAME){
			state.getGUIContainter().addGuiObject(new ResourceGUI(new Point(0, 0), state.getGUIContainter(), ((Game)state).thePlayer));
		}
	}
}
