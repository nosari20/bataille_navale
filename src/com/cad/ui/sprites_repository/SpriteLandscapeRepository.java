package com.cad.ui.sprites_repository;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.cad.motor2d.sprites.Sprite;

public class SpriteLandscapeRepository {
	
	private static SpriteLandscapeRepository instance = new SpriteLandscapeRepository();

	
	Sprite water;
	
	Sprite crosshair;

	public SpriteLandscapeRepository() {
		water = new Sprite(new ImageIcon("./assets/water.png").getImage());
		crosshair = new Sprite(new ImageIcon("./assets/crosshair.png").getImage());
	}
	
	public static SpriteLandscapeRepository getInstance(){
		return instance;
	}
	
	
	public Sprite water(){
		return water;
	}
	
	public Sprite crosshair(){
		return crosshair;
	}

}
