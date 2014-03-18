package com.squareworks.openworld.client.GUI;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public abstract class GUI {
	protected float x,y;
	private GUIContainer container;
	public GUI(Point pos, GUIContainer container){
		x = pos.getX();
		y = pos.getY();
		this.container = container;
	}
	public void setPosition(Point pos){
		x = pos.getX();
		y = pos.getY();
	}
	public Point getPosition(){
		return new Point(x, y);
	}
	
	public void close(){
		container.remove(this);
	}
	
	public abstract void render(Graphics g);
	public void update(int delta){}
	public void mousePressed(int button, int x, int y){}
	public void mouseReleased(int button, int x, int y){}
	public void mouseClicked(int button, int x, int y, int clickCount){}
	public void mouseDragged(int oldx, int oldy, int newx, int newy){}
	public void mouseMoved(int oldx, int oldy, int newx, int newy){}
}
