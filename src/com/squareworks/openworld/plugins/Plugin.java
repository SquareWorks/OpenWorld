package com.squareworks.openworld.plugins;

import java.util.Properties;

import com.squareworks.openworld.client.states.GUIGameState;


public abstract class Plugin {
	private Properties properties;
	public Plugin(Properties properties){
		this.properties = properties;
	}
	
	public void load(){
		System.out.println(getProperty("name") + " v" + getProperty("version") + " loaded");
	}
	
	public void enterState(GUIGameState state){
		System.out.println(state.getID());
	}
	
	public String getProperty(String property){
		return properties.getProperty(property);
	}

}
