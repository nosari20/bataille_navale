package com.cad.ui.sprites_repository;

import java.awt.List;
import java.util.ArrayList;

import com.cad.motor2d.sprites.Animation;
import com.cad.motor2d.sprites.Sprite;
import com.cad.motor2d.sprites.SpriteSheet;
import com.cad.motor2d.sprites.SpriteSheetGrid;

public class SpriteExplostionRepository {

	private static SpriteExplostionRepository instance = new SpriteExplostionRepository();


	private SpriteSheetGrid p;
	
	
	private Animation exp;


	private SpriteExplostionRepository() {
		p = new SpriteSheetGrid("./assets/spritesheets/explosion.png",9,9);
		ArrayList<Sprite> s = new ArrayList<>();
		for (int i = 0; i < p.getRow(); i++) {
			for (int j = 0; j < p.getCol(); j++) {
				s.add(p.getSprite(j,i));
			}
		}
		
		exp = new Animation(s.toArray(new Sprite[s.size()]));
		exp.start();
	}
	
	public Sprite getExplosion(){
		Sprite p = exp.getSprite();
		exp.update();
		return p;
	} 

	public static SpriteExplostionRepository getInstance(){
		return instance;
	}


}
