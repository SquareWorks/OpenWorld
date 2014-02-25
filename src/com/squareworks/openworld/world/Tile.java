package com.squareworks.openworld.world;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile {
	private Image graphic;
	public Tile() {
	}
	
	public Tile(Image graphic){
		this.graphic = graphic;
	}
	
	public void render(Graphics g){
		g.drawImage(graphic, 0, 0);
	}
}
