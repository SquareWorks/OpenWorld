package extraTiles;
import org.newdawn.slick.Image;

import com.squareworks.openworld.client.ResourceLoader;
import com.squareworks.openworld.world.Tile;


public class GrassTile extends Tile {
	public GrassTile() {
		super(ResourceLoader.getResource("grass"));
	}
	
	public GrassTile(float color){
		super(getColoredImage(color, ResourceLoader.getResource("grass")));
	}
	
	private static Image getColoredImage(float color, Image i){
//		System.out.println(color);
		i.setAlpha(color/1000f);
		return i;
	}
}
