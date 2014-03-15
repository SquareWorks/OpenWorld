package com.squareworks.openworld.world;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.pbuffer.GraphicsFactory;

import extraTiles.GrassTile;
import extraTiles.WaterTile;

public class Region implements Externalizable {
	private Tile[][] tiles = new Tile[WIDTH][HEIGHT];
	public static final int WIDTH = 129;
	public static final int HEIGHT = 129;
	private float[][] grid;
	private Image buffer;

	public Region(float[][] generated) {
		grid = generated;
		for (int i = 0; i < generated.length; i++) {
			for (int k = 0; k < generated[i].length; k++) {
				if (generated[i][k] > 0.1) {
					tiles[i][k] = GrassTile.getTile();
				} else {
					tiles[i][k] = new WaterTile();
				}
			}
		}
	}

	public Region() {

	}

	public void render(Graphics g, GameContainer gc, double scale) throws SlickException {
		int count = 0;
		if (buffer == null) {
			System.out.println("generating region buffer");
			buffer = new Image(Region.WIDTH*Tile.WIDTH, Region.HEIGHT*Tile.HEIGHT, Image.FILTER_LINEAR);
			Graphics g2 = buffer.getGraphics();
			for (int y = 0; y < tiles.length; y++) {
				for (int x = 0; x < tiles[y].length; x++) {
					tiles[x][y].render(g2, (float) (x * Tile.WIDTH),
							(float) (y * Tile.HEIGHT), 1.0);
					// float val = grid[x][y]/2 + 0.5f;
					// g.setColor(new Color(val, val, val));
					// g.fillRect((float)(x*16*scale), (float)(y*16*scale), 16f,
					// 16f);
				}
			}
			g2.destroy();
		}
		g.drawImage(buffer, 0, 0, (float)(buffer.getWidth()*scale), (float)(buffer.getHeight()*scale), 0,0, buffer.getWidth(), buffer.getHeight());
//		g.setColor(Color.blue);
//		g.drawRect(0, 0, (float) ((WIDTH + 1) * 16 * scale),
//				(float) ((HEIGHT + 1) * 16 * scale));

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		for (int i = 0; i < HEIGHT; i++) {
			for (int k = 0; k < WIDTH; k++) {
				try {
					tiles[i][k] = (Tile) Class.forName(in.readUTF())
							.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		for (int i = 0; i < HEIGHT; i++) {
			for (int k = 0; k < WIDTH; k++) {
				out.writeUTF(tiles[i][k].getClass().getName());
			}
		}

	}
}
