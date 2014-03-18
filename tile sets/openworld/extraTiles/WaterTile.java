package extraTiles;

import com.squareworks.openworld.client.GraphicsLoader;
import com.squareworks.openworld.world.Tile;

public class WaterTile extends Tile{
	public WaterTile(){
		super(GraphicsLoader.getResource("water"));
	}
}
