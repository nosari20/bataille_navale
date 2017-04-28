package com.cad.ui.sprites_repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.cad.motor2d.sprites.Animation;
import com.cad.motor2d.sprites.Sprite;
import com.cad.motor2d.sprites.SpriteSheetGrid;

public class SpriteExplostionRepository {

	private static SpriteExplostionRepository instance = new SpriteExplostionRepository();


	private SpriteSheetGrid p;
	private ArrayList<Sprite> ps;
	
	private SpriteSheetGrid f;
	private ArrayList<Sprite> fs;



	private SpriteExplostionRepository() {
		p = new SpriteSheetGrid("./assets/spritesheets/explosion.png",9,9);
		ps = new ArrayList<>();
		for (int i = 0; i < p.getRow(); i++) {
			for (int j = 0; j < p.getCol(); j++) {
				ps.add(p.getSprite(j,i));
			}
		}
		
		
		f = new SpriteSheetGrid("./assets/flames.png",8,4);
		fs = new ArrayList<>();
		ArrayList<Sprite> tmp = new ArrayList<>();
		for (int i = 0; i < f.getRow(); i++) {
			for (int j = 0; j < f.getCol(); j++) {
				if(!(j!=3 && i >= 7)){
					tmp.add(f.getSprite(i,j));
					fs.add(f.getSprite(i,j));
				}
				
			}
		}
		Collections.reverse(tmp);
		fs.addAll(tmp);



		
	}
	
	public Animation getExplosion(){		
		
		return new Animation(ps.toArray(new Sprite[ps.size()]));
		
	} 
	
	public Animation getFlames(){		
		
		return new Animation(fs.toArray(new Sprite[fs.size()]));
		
	} 
	
	

	public static SpriteExplostionRepository getInstance(){
		return instance;
	}


}
