package com.squareworks.openworld.world;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import extraTiles.GrassTile;
import extraTiles.WaterTile;

public class Region implements Externalizable{
	private Tile[][] tiles = new Tile[WIDTH][HEIGHT];
	public static final int WIDTH = 129;
	public static final int HEIGHT = 129;
	private float[][] grid;
	
	public Region(float[][] generated){
		grid = generated;
		for(int i = 0; i < generated.length; i++){
			for(int k = 0; k < generated[i].length; k++){
				if(generated[i][k] > 0.1){
					tiles[i][k] = new GrassTile();
				}else{
					tiles[i][k] = new WaterTile();
				}
			}
		}
	}
	public Region(){
		
	}

	public void render(Graphics g, GameContainer gc, double scale){
		int count = 0;
		for(int y = 0; y < tiles.length; y++){
			for(int x = 0; x < tiles[y].length; x++){
				tiles[x][y].render(g, (float)(x*Tile.WIDTH*scale), (float)(y*Tile.HEIGHT*scale), scale);
//				float val = grid[x][y]/2 + 0.5f;
//				g.setColor(new Color(val, val, val));
//				g.fillRect((float)(x*16*scale), (float)(y*16*scale), 16f, 16f);
				count ++;
			}
		}
		g.setColor(Color.blue);
		g.drawRect(0,0, (float)((WIDTH+1)*16*scale), (float)((HEIGHT+1)*16*scale));
		
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		for(int i = 0; i < HEIGHT; i++){
			for(int k = 0; k < WIDTH; k++){
				try {
					tiles[i][k] = (Tile) Class.forName(in.readUTF()).newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		for(int i = 0; i < HEIGHT; i++){
			for(int k = 0; k < WIDTH; k++){
				out.writeUTF(tiles[i][k].getClass().getName());
			}
		}
		
	}
}
