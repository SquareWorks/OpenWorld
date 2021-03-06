package com.squareworks.openworld.world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.squareworks.openworld.client.GraphicsLoader;

public class Tile{
	private Image graphic = GraphicsLoader.getResource("grass");
	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;
	public Tile() {
	}
	
	public Tile(Image graphic){
		this.graphic = graphic;
	}
	
	public void render(Graphics g, float x, float y, double scale){
		g.drawImage(graphic, x, y, (float)(x+WIDTH*scale), (float)(y+HEIGHT*scale), 0f,0f,(float)graphic.getWidth(), (float)graphic.getHeight());
	}
}
