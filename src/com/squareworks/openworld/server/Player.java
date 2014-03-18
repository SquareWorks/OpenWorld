package com.squareworks.openworld.server;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.squareworks.openworld.world.Resource;
import com.squareworks.openworld.world.ResourceLoader;

public class Player implements Externalizable{
	private Map<Resource, Integer> resources = new HashMap<Resource, Integer>();
	
	public Player() {
		resources = ResourceLoader.getBlankResources();
	}
	
	public Map<Resource,Integer> getResources(){
		return resources;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		ArrayList<Resource> resourceTypes = new ArrayList<Resource>();
		resources.keySet().addAll(resourceTypes);
		int size = in.readInt();
		for(int i = 0; i < size; i++){
			resources.put(resourceTypes.get(resourceTypes.indexOf(new Resource(in.readLine()))),in.readInt());
		}
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.write(resources.size());
		for(Resource resource : resources.keySet()){
			out.writeChars(resource.getName());
			out.writeInt(resources.get(resource));
		}
		
	}
	
}
