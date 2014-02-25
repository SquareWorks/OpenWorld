package com.squareworks.openworld.world;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.squareworks.openworld.client.FileType;

public class TileLoader {
	private static Map<String, Map<String, Class<Tile>>> tiles = new HashMap<String, Map<String, Class<Tile>>>();

	public void loadResources() throws SlickException, MalformedURLException, InstantiationException, IllegalAccessException{
		System.out.println("loading resources");
		for(File f: new File("tile sets").listFiles()){
			if(f.isDirectory()){
				tiles.put(f.getName(), new HashMap<String, Class<Tile>>());
				URL[] ul = {f.toURL()};
				URLClassLoader loader = new URLClassLoader(ul, ClassLoader.getSystemClassLoader());
				for(URL url : loadResources(f, f.getName())){
					String name = url.getFile().replaceAll("/", ".");
					name = name.substring(name.indexOf(f.getName()) + f.getName().length() + 1, name.indexOf(".class"));
					try {
						System.out.println(Class.forName(name, true, loader).newInstance().getClass().getName());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private ArrayList<URL> loadResources(File dir, String pack) throws SlickException, MalformedURLException{
		URLClassLoader loader;
		ArrayList<URL> urls = new ArrayList<URL>();
		for(File f : dir.listFiles()){
			if(f.isDirectory()){
				urls.addAll(loadResources(f, pack));
			}else{
				System.out.println(f.getName());
				if(FileType.java.isType(f)){
					urls.add(f.toURL());
				}
			}
		}
		
		return urls;
	}
}
