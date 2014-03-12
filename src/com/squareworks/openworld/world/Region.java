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
	public static final int WIDTH = 1025;
	public static final int HEIGHT = 1025;
	private float[][] grid;
	
	public Region(float[][] generated){
		grid = generated;
		for(int i = 0; i < generated.length; i++){
			for(int k = 0; k < generated[i].length; k++){
				if(generated[i][k] < 0){
					tiles[i][k] = new GrassTile(generated[i][k]);
				}else{
					tiles[i][k] = new WaterTile();
				}
			}
		}
	}
	public Region(){
		
	}

	public void render(Graphics g, GameContainer gc, double xOffset, double yOffset, double scale){
		int count = 0;
		for(int y = (int) (yOffset*(1/scale)/16.0); y <= yOffset*(1/scale)/16.0+gc.getHeight()*(1/scale)/16.0; y++){
			for(int x = (int) (xOffset*(1/scale)/16.0); x <= xOffset*(1/scale)/16.0+gc.getWidth()*(1/scale)/16.0; x++){
//				tiles[x][y].render(g, x*16*scale - xOffset, y*16*scale - yOffset, scale);
				float val = grid[x][y]/2 + 0.5f;
				g.setColor(new Color(val, val, val));
				g.fillRect((float)(x*16*scale - xOffset), (float)(y*16*scale - yOffset), 16f, 16f);
				count ++;
			}
		}
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
