package com.squareworks.openworld.world;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.squareworks.openworld.client.ResourceLoader;

public class Tile{
	private Image graphic = ResourceLoader.getResource("grass");
	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;
	
	public Tile() {
	}
	
	public Tile(Image graphic){
		this.graphic = graphic;
	}
	
	public void render(Graphics g, double x, double y, double scale){
		g.drawImage(graphic, (float)x, (float)y, (float)(x+WIDTH*scale), (float)(y+HEIGHT*scale), 0f,0f,(float)graphic.getWidth(), (float)graphic.getHeight());
	}
}
