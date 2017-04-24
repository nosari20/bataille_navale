package com.cad.motor2d.sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheetGrid implements SpriteSheet {
	
	private int row;
	private int col;
	
	
	private BufferedImage spriteSheet;

	public SpriteSheetGrid(String image, int r, int c) {
		row = r;
		col = c;	
		loadSpriteSheet(image);
	}
	
	private void loadSpriteSheet(String file) {
		try {
			spriteSheet = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Sprite getSprite(int index) {
		int sr = spriteSheet.getWidth()/row;
		int sc = spriteSheet.getHeight()/col;
		
		int r = (index % row)*sr;
		int c = (index / row)*sc;	
		
		System.out.println(r);
		System.out.println(c);
		
		return new Sprite(spriteSheet.getSubimage(r, c, sr, sc));

	}
	
	public Sprite getSprite(int ir, int ic) {
		int sr = spriteSheet.getWidth()/row;
		int sc = spriteSheet.getHeight()/col;
		
		int r = (ir)*sr;
		int c = (ic)*sc;	
		return new Sprite(spriteSheet.getSubimage(r, c, sr, sc));

	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	

}
