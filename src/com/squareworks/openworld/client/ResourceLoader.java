package com.squareworks.openworld.client;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class ResourceLoader {
	private static Map<String, Map<String, Image>> graphics = new HashMap<String, Map<String, Image>>();
	
	public static Image getResource(String name){
		return getResource("openworld", name);
	}
	
	public static Image getResource(String pack, String name){
		return graphics.get(pack).get(name);
	}
	
	public void loadResources() throws SlickException{
		System.out.println("loading resources");
		for(File f: new File("assets").listFiles()){
			if(f.isDirectory()){
				graphics.put(f.getName(), new HashMap<String, Image>());
				loadResources(f, f.getName());
			}
		}
	}
	
	private void loadResources(File dir, String pack) throws SlickException{
		for(File f : dir.listFiles()){
			if(f.isDirectory()){
				loadResources(f, pack);
			}else{
				System.out.println(f.getName());
				if(FileType.graphic.isType(f)){
					graphics.get(pack).put(getSimpleName(f), new Image(f.getAbsolutePath()));
				}
			}
		}
	}
	
	private String getSimpleName(File file){
		String name = file.getName();
		name = name.substring(0, name.indexOf("."));
		return name;
	}
}
