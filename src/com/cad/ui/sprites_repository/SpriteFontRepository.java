package com.cad.ui.sprites_repository;

import java.util.ArrayList;
import java.util.HashMap;

import com.cad.motor2d.sprites.Animation;
import com.cad.motor2d.sprites.Sprite;
import com.cad.motor2d.sprites.SpriteSheetGrid;

public class SpriteFontRepository {
	
	private HashMap<String, Sprite> map;
	
	
	public SpriteFontRepository(){
		SpriteSheetGrid p = new SpriteSheetGrid("./assets/font.png",13,9);
		map = new HashMap<String, Sprite>();
		map.put("A", p.getSprite(0));
		map.put("B", p.getSprite(1));
		map.put("C", p.getSprite(2));
		map.put("D", p.getSprite(3));
		map.put("E", p.getSprite(4));
		map.put("F", p.getSprite(5));
		map.put("G", p.getSprite(6));
		map.put("H", p.getSprite(7));
		map.put("I", p.getSprite(8));
		map.put("J", p.getSprite(9));
		map.put("K", p.getSprite(10));
		map.put("L", p.getSprite(11));
		map.put("M", p.getSprite(12));
		map.put("N", p.getSprite(13));
		map.put("O", p.getSprite(14));
		map.put("P", p.getSprite(15));
		map.put("Q", p.getSprite(16));
		map.put("R", p.getSprite(17));
		map.put("S", p.getSprite(18));
		map.put("T", p.getSprite(19));
		map.put("U", p.getSprite(20));
		map.put("V", p.getSprite(21));
		map.put("W", p.getSprite(22));
		map.put("X", p.getSprite(23));
		map.put("Y", p.getSprite(24));
		map.put("Z", p.getSprite(25));		
	}
	
	
	public Sprite get(char c){
		return map.get(""+c);
	}
	
	
	
	
	
	
	private static SpriteFontRepository instance = new SpriteFontRepository();
	public static SpriteFontRepository getInstance(){
		return instance;
	}
	

}
