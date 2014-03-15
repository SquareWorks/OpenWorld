package extraTiles;
import com.squareworks.openworld.client.ResourceLoader;
import com.squareworks.openworld.world.Tile;


public class GrassTile extends Tile {
	private static GrassTile instance;
	private GrassTile() {
		super(ResourceLoader.getResource("grass"));
	}
	public static GrassTile getTile(){
		if(instance == null){
			instance = new GrassTile();
		}
		return instance;
	}
}
