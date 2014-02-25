package com.squareworks.openworld.world;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Region implements Externalizable{
	private Tile[][] tiles = new Tile[1024][1024];

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
