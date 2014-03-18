package com.squareworks.openworld.client;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class GraphicsLoader {
	private static Map<String, Map<String, Image>> graphics = new HashMap<String, Map<String, Image>>();
	
	public static Image getResource(String name){
		return getResource("openworld", name);
	}
	
	public static Image getResource(String pack, String name){
		return graphics.get(pack).get(name);
	}
	
	public static void loadResources() throws SlickException{
		System.out.println("loading graphics resources");
		for(File f: new File("assets").listFiles()){
			if(f.isDirectory()){
				loadResources(f, f.getName());
			}
		}
	}
	
	public static void loadResources(File dir, String pack) throws SlickException{
		if(!graphics.containsKey(pack)){
			graphics.put(pack, new HashMap<String, Image>());
		}
		for(File f : dir.listFiles()){
			if(f.isDirectory()){
				loadResources(f, pack);
			}else{
				if(FileType.graphic.isType(f)){
					Image graphic = new Image(f.getAbsolutePath());
					graphic.setFilter(Image.FILTER_LINEAR);
					graphics.get(pack).put(getSimpleName(f), graphic);
				}
			}
		}
	}
	
	private static String getSimpleName(File file){
		String name = file.getName();
		name = name.substring(0, name.indexOf("."));
		return name;
	}
}
