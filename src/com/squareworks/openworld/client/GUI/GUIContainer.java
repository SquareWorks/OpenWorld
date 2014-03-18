package com.squareworks.openworld.client.GUI;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class GUIContainer {

	private ArrayList<GUI> guiObjects = new ArrayList<GUI>();

	public GUIContainer() {
		// TODO Auto-generated constructor stub
	}

	public void remove(GUI guiObject) {
		guiObjects.remove(guiObject);
	}

	public void addGuiObject(GUI gui) {
		if (!guiObjects.contains(gui)) {
			guiObjects.add(gui);
		}
	}

	public void render(Graphics g) {
		for (GUI gui : guiObjects) {
			gui.render(g);
		}
	}

	public void update(int delta) {
		for (GUI gui : guiObjects) {
			gui.update(delta);
		}
	}

	public void mousePressed(int button, int x, int y) {
		for (GUI gui : guiObjects) {
			gui.mousePressed(button, x, y);
		}
	}

	public void mouseReleased(int button, int x, int y) {
		for (GUI gui : guiObjects) {
			gui.mouseReleased(button, x, y);
		}
	}

	public void mouseClicked(int button, int x, int y, int clickCount) {
		for (GUI gui : guiObjects) {
			gui.mouseClicked(button, x, y, clickCount);
		}
	}

	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		for (GUI gui : guiObjects) {
			gui.mouseDragged(oldx, oldy, newx, newy);
		}
	}

	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		for (GUI gui : guiObjects) {
			gui.mouseMoved(oldx, oldy, newx, newy);
		}
	}

}
