package com.squareworks.openworld.client;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public enum FileType {
	graphic("jpg","png"),
	java("class"),
	plugin_info("info"),
	xml("xml");
	
	private List<String> fileTypes;
	
	FileType(String... fileTypes){
		this.fileTypes = new ArrayList<String>();
		Collections.addAll(this.fileTypes, fileTypes);
	}
	
	public boolean isType(String file){
		if(file.lastIndexOf(".") != -1){
			file = file.substring(file.lastIndexOf('.')+1);
		}
		return fileTypes.contains(file);
	}
	
	public boolean isType(File file){
		return isType(file.getName());
	}
}
