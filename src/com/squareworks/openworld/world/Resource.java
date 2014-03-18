package com.squareworks.openworld.world;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;

public class Resource {
	private Map<String, Image> icons = new HashMap<String, Image>();
	private String name;
	public Resource(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addGraphic(String use, Image graphic){
		icons.put(use, graphic);
	}
	
	public Image getGraphic(String use){
		return icons.get(use);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Resource){
			if(this.getName().equals(((Resource)obj).getName())){
				return true;
			}
			return false;
		}
		return false;
	}
}
