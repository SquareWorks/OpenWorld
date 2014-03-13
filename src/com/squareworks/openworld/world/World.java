package com.squareworks.openworld.world;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class World implements Externalizable{
	
	private ArrayList<ArrayList<Region>> regions = new ArrayList<ArrayList<Region>>();
	private WorldGenerator generator;
	
	public World(long seed){
		generator = new WorldGenerator(seed);
		for(int i = 0; i < 9; i++){
			regions.add(new ArrayList<Region>());
			for(int k = 0; k < 9; k++){
				regions.get(i).add(generator.generateRegion(k,i));
			}
		}
	}
	
	public void draw(Graphics g, GameContainer gc, double xOffset, double yOffset, double scale){
		for(int y = (int) (yOffset/Region.HEIGHT/16.0); y <= (int)(((yOffset + gc.getHeight())/Region.HEIGHT/16.0) * (1/scale)); y++){
			for(int x = (int) (xOffset/Region.WIDTH/16.0); x <= (int)(((xOffset + gc.getWidth())/Region.WIDTH/16.0) * (1/scale)); x++){
				g.translate((float)(xOffset+x*Region.WIDTH*Tile.WIDTH*scale), (float)(yOffset+y*Region.HEIGHT*Tile.WIDTH*scale));
				regions.get(y).get(x).render(g, gc, scale);
				System.out.println(x + " " + y);
				g.resetTransform();
			}
		}
	}



	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		generator = new WorldGenerator(in.readLong());
		int height = in.readInt();
		for(int i = 0; i < height; i++){
			int width = in.readInt();
			regions.add(new ArrayList<Region>());
			for(int k = 0; k < width; k++){
				Region temp = new Region();
				temp.readExternal(in);
				regions.get(i).add(temp);
			}
		}
		
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(generator.getSeed());
		out.writeInt(regions.size());
		for(int i = 0; i < regions.size(); i ++){
			out.writeInt(regions.get(i).size());
			for(int k = 0; k < regions.get(i).size(); k++){
				regions.get(i).get(k).writeExternal(out);
			}
		}
		
	}}
