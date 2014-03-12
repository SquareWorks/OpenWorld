package extraTiles;

import com.squareworks.openworld.client.ResourceLoader;
import com.squareworks.openworld.world.Tile;

public class WaterTile extends Tile{
	public WaterTile(){
		super(ResourceLoader.getResource("water"));
	}
}
