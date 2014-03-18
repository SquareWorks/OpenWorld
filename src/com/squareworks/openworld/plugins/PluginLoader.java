package com.squareworks.openworld.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.SlickException;
import org.xml.sax.SAXException;

import base.bin.BasePlugin;

import com.squareworks.openworld.client.FileType;
import com.squareworks.openworld.client.GraphicsLoader;
import com.squareworks.openworld.world.ResourceLoader;

public class PluginLoader {
	private static ArrayList<Plugin> loadedPlugins = new ArrayList<Plugin>();
	
	public static void loadPlugins(File dir) throws ClassNotFoundException, FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SlickException, IOException{
		for(File f : dir.listFiles()){
			if(f.isDirectory() && Arrays.asList(f.list()).contains("plugin.info")){
				loadPlugin(f);
			}
		}
	}

	public static void loadPlugin(File dir) throws SlickException, ClassNotFoundException, FileNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Class<Plugin> plugin = null;
		Properties properties = new Properties();
		List<String> files = Arrays.asList(dir.list());
		if(files.contains("res")){
			File f = new File(dir.getPath() + dir.separatorChar + "res");
			GraphicsLoader.loadResources(f, dir.getName());
		}
		for(File f : dir.listFiles()){
			if(f.getName().equals("bin") && f.isDirectory()){
				URL[] ul = {f.toURL()};
				URLClassLoader loader = new URLClassLoader(ul, ClassLoader.getSystemClassLoader());
				plugin = loadBinaries(f, dir.getName(), loader);
			}
			if(f.getName().equals("dat") && f.isDirectory()){
				loadData(f, dir.getName());
			}
			if(FileType.plugin_info.isType(f) && f.isFile()){
				properties.load(new FileInputStream(f));
			}
		}
		Plugin initializedPlugin = plugin.getConstructor(Properties.class).newInstance(properties);
		initializedPlugin.load();
		loadedPlugins.add(initializedPlugin);
	}
	
	private static void loadData(File dir, String pack) {
		for(File f : dir.listFiles()){
			if(f.isDirectory()){
				loadData(f, pack);
			}else{
				if(FileType.xml.isType(f)){
					try {
						ResourceLoader.loadResources(f, pack);
					} catch (ParserConfigurationException | SAXException
							| IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void invokePluginMethod(String name, Class[] types, Object... arguments) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		for(Plugin plugin : loadedPlugins){
			Method method = plugin.getClass().getMethod(name, types);
			method.invoke(plugin, arguments);
		}
	}
	
	private static Class<Plugin> loadBinaries(File dir, String pack, URLClassLoader classLoader) throws ClassNotFoundException, IOException{
		Class<Plugin> plugin = null;
		for(File f : dir.listFiles()){
			if(FileType.java.isType(f)){
				String name = f.getAbsolutePath().replace('\\', '.');
				name = name.substring(name.indexOf(pack), name.lastIndexOf('.'));
				Class loaded = Class.forName(name, false, classLoader);
				if(loaded.getSuperclass().equals(Plugin.class)){
					plugin = loaded;
				}
			}
		}
		return plugin;
	}
}
