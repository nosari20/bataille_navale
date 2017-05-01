package com.cad.ui.sprites_repository;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.cad.motor2d.sprites.Animation;
import com.cad.motor2d.sprites.Sprite;
import com.cad.motor2d.sprites.SpriteSheetGrid;

public class SpriteLandscapeRepository {
	
	private static SpriteLandscapeRepository instance = new SpriteLandscapeRepository();

	
	private Sprite water;
	
	private Sprite crosshair;
	
	
	private SpriteSheetGrid water_ss;
	private ArrayList<Sprite> water_list;

	public SpriteLandscapeRepository() {
		water = new Sprite(new ImageIcon("./assets/water.png").getImage());
		crosshair = new Sprite(new ImageIcon("./assets/crosshair.png").getImage());
		
		water_ss = new SpriteSheetGrid("./assets/kleinAnimatedTiles.png",8,6);
		
		water_list = new ArrayList<>();
		//for (int i = 0; i < water_ss.getRow(); i++) {
			for (int j = 0; j < water_ss.getCol(); j++) {
				water_list.add(water_ss.getSprite(j,0));
			}
		//}
	}
	
	public static SpriteLandscapeRepository getInstance(){
		return instance;
	}
	
	
	public Sprite water(){
		return water;
	}
	
	public Animation waterAnimated(){		
		
		return new Animation(water_list.toArray(new Sprite[water_list.size()]));
		
	} 
	
	public Sprite crosshair(){
		return crosshair;
	}

}
