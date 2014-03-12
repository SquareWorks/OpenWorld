package com.squareworks.openworld.world;

import java.util.Random;

public class WorldGenerator {
	Random rand;
	private long seed = 0xbada55;
	private float roughness;
	private float[][] grid;
	private float[][] points;
	private float range = 1f;

	public WorldGenerator(long seed) {
		this.seed = seed;
		rand = new Random(seed);
		roughness = -1f;
		points = new float[1024][1024];
		for (int x = 0; x < points.length; x++) {
			for (int y = 0; y < points[x].length; y++) {
				points[x][y] = rand.nextFloat();
			}
		}
	}

	public long getSeed() {
		return seed;
	}

	public Region generateRegion(int x, int y) {
		grid = new float[Region.HEIGHT][Region.WIDTH];
		int xh = grid.length - 1;
		int yh = grid[0].length - 1;
		rand = new Random(Long.parseLong(x + "" + seed + "" + y));
		// set the corner points
		grid[0][0] = points[x][y] - range / 2f;
		grid[0][yh] = points[x][y + 1] - range / 2f;
		grid[xh][0] = points[x + 1][y] - range / 2f;
		grid[xh][yh] = points[x + 1][y + 1] - range / 2f;
		generate(0, 0, xh, yh);
		return new Region(grid);
	}

	// Add a suitable amount of random displacement to a point
	private float roughen(float v, int l, int h) {
		return v + roughness * (float) (rand.nextGaussian() * (h - l));
	}

	private void diamond(int x1, int y1, int x2, int y2) {
		float topRight, topLeft, botRight, botLeft;
		topRight = grid[x2][y1];
		topLeft = grid[x1][y1];
		botRight = grid[x2][y2];
		botLeft = grid[x1][y2];
		grid[(x1 + x2) / 2][(y1 + y2) / 2] = (topRight + topLeft + botRight + botLeft)
				* 0.25f + floatInRange(range / 2);
	}

	private void square(int x1, int y1, int x2, int y2) {
		float topRight, topLeft, botRight, botLeft;
		topRight = grid[x2][y1];
		topLeft = grid[x1][y1];
		botRight = grid[x2][y2];
		botLeft = grid[x1][y2];
		int midx = (x1 + x2) / 2;
		int midy = (y1 + y2) / 2;
		if (grid[midx][y1] != 0) {
			grid[midx][y1] = (topRight + topLeft) * 0.5f
					+ floatInRange(range / 2);
		}
		if (grid[midx][y2] != 0) {
			grid[midx][y2] = (botRight + botLeft) * 0.5f
					+ floatInRange(range / 2);
		}
		if (grid[x1][midy] != 0) {
			grid[x1][midy] = (topLeft + botLeft) * 0.5f
					+ floatInRange(range / 2);
		}
		if (grid[x2][midy] != 0) {
			grid[x2][midy] = (topRight + botRight) * 0.5f
					+ floatInRange(range / 2);
		}
	}

	private float floatInRange(float range) {
		return (rand.nextFloat() - 0.5f) * 2 * range;
	}

	// generate the fractal
	private void generate(int x1, int y1, int x2, int y2) {
		int xm = (x1 + x2) / 2;
		int ym = (y1 + y2) / 2;
		if ((x1 == xm) && (y1 == ym))
			return;
		diamond(x1, y1, x2, y2);
		square(x1, y1, x2, y2);
		// grid[xm][yl] = 0.5f * (grid[xl][yl] + grid[xh][yl]);
		// grid[xm][yh] = 0.5f * (grid[xl][yh] + grid[xh][yh]);
		// grid[xl][ym] = 0.5f * (grid[xl][yl] + grid[xl][yh]);
		// grid[xh][ym] = 0.5f * (grid[xh][yl] + grid[xh][yh]);

		// float v = roughen(0.5f * (grid[xm][yl] + grid[xm][yh]), xl + yl, yh
		// + xh);
		// grid[xm][ym] = v;
		// grid[xm][yl] = roughen(grid[xm][yl], xl, xh);
		// grid[xm][yh] = roughen(grid[xm][yh], xl, xh);
		// grid[xl][ym] = roughen(grid[xl][ym], yl, yh);
		// grid[xh][ym] = roughen(grid[xh][ym], yl, yh);

		generate(x1, y1, xm, ym);
		generate(xm, y1, x2, ym);
		generate(x1, ym, xm, y2);
		generate(xm, ym, x2, y2);
	}

	/**
	 * Convert to a Boolean array
	 * 
	 * @return the boolean array
	 */
	public boolean[][] toBooleans() {
		int w = grid.length;
		int h = grid[0].length;
		boolean[][] ret = new boolean[w][h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				ret[i][j] = grid[i][j] < 0;
			}
		}
		return ret;
	}
}
