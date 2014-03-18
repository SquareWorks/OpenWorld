package base.bin.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import com.squareworks.openworld.client.GUI.GUI;
import com.squareworks.openworld.client.GUI.GUIContainer;
import com.squareworks.openworld.server.Player;
import com.squareworks.openworld.world.Resource;

public class ResourceGUI extends GUI {
	
	private Player player;

	public ResourceGUI(Point pos, GUIContainer container, Player player) {
		super(pos, container);
		this.player = player;
	}

	@Override
	public void render(Graphics g) {
		Object[] resources = player.getResources().keySet().toArray();
		for(int i = 0; i < resources.length; i++){
			Resource resource = (Resource)resources[i];
			g.drawImage(resource.getGraphic("icon"), x + 64*i, y);
			int height = g.getFont().getHeight("X");
			g.drawString("X" + player.getResources().get(resource), x + 64*i + 40, y + (height/2));
		}
		
	}

}
