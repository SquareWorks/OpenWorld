package extraTiles;
import com.squareworks.openworld.client.GraphicsLoader;
import com.squareworks.openworld.world.Tile;


public class GrassTile extends Tile {
	private static GrassTile instance;
	private GrassTile() {
		super(GraphicsLoader.getResource("grass"));
	}
	public static GrassTile getTile(){
		if(instance == null){
			instance = new GrassTile();
		}
		return instance;
	}
}
