package com.cad.motor2d.sprites;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private class Region {
		String name;
		int x;
		int y;
		int size_x;
		int size_y;
		int orig_x;
		int orig_y;

		public String toString() {
			return name + " xy : " + x + ", " + y + " size : " + size_x + ", " + size_y;
		}
	}

	private BufferedImage spriteSheet;
	private HashMap<String, Region> atlas;

	public SpriteSheet(String image, String atlas) {
		loadSpriteSheet(image);
		loadAtlas(atlas);
	}

	private void loadSpriteSheet(String file) {
		try {
			spriteSheet = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadAtlas(String file) {
		atlas = new HashMap();
		try {

			BufferedReader reader = new BufferedReader(new FileReader(file));
			int line_index = -1;
			while (true) {
				String line = reader.readLine();
				line_index++;
				if (line == null)
					break;
				if (line_index < 5)
					continue;

				Region r = new Region();
				r.name = line;

				String[] tab;

				// rotate
				line = reader.readLine();

				// xy
				line = reader.readLine();
				tab = line.split(": ");
				r.x = Integer.parseInt(tab[1].split(", ")[0]);
				r.y = Integer.parseInt(tab[1].split(", ")[1]);

				// size
				line = reader.readLine();
				tab = line.split(": ");
				r.size_x = Integer.parseInt(tab[1].split(", ")[0]);
				r.size_y = Integer.parseInt(tab[1].split(", ")[1]);

				// orig
				line = reader.readLine();
				tab = line.split(": ");
				r.orig_x = Integer.parseInt(tab[1].split(", ")[0]);
				r.orig_y = Integer.parseInt(tab[1].split(", ")[1]);

				// offset
				line = reader.readLine();

				// index
				line = reader.readLine();

				atlas.put(r.name, r);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sprite getSprite(String sprite) {
		Region r = atlas.get(sprite);
		return new Sprite(spriteSheet.getSubimage(r.x, r.y, r.size_x, r.size_y));
	}

}