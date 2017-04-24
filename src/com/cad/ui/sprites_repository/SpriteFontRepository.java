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
		
		map.put("a", p.getSprite(26));
		map.put("b", p.getSprite(27));
		map.put("c", p.getSprite(28));
		map.put("d", p.getSprite(29));
		map.put("e", p.getSprite(30));
		map.put("f", p.getSprite(31));
		map.put("g", p.getSprite(32));
		map.put("h", p.getSprite(33));
		map.put("i", p.getSprite(34));
		map.put("j", p.getSprite(35));
		map.put("k", p.getSprite(36));
		map.put("l", p.getSprite(37));
		map.put("m", p.getSprite(38));
		map.put("n", p.getSprite(39));
		map.put("o", p.getSprite(40));
		map.put("p", p.getSprite(41));
		map.put("q", p.getSprite(42));
		map.put("r", p.getSprite(43));
		map.put("s", p.getSprite(44));
		map.put("t", p.getSprite(45));
		map.put("u", p.getSprite(46));
		map.put("v", p.getSprite(47));
		map.put("w", p.getSprite(48));
		map.put("x", p.getSprite(49));
		map.put("y", p.getSprite(50));
		map.put("z", p.getSprite(51));	
		
		map.put("0", p.getSprite(52));
		map.put("1", p.getSprite(53));
		map.put("2", p.getSprite(54));
		map.put("3", p.getSprite(55));
		map.put("4", p.getSprite(56));
		map.put("5", p.getSprite(57));
		map.put("6", p.getSprite(58));
		map.put("7", p.getSprite(59));
		map.put("8", p.getSprite(60));
		map.put("9", p.getSprite(61));
		
		
		map.put("!", p.getSprite(62));
		
	}
	
	
	public Sprite get(char c){
		return map.get(""+c);
	}
	
	
	
	
	
	
	private static SpriteFontRepository instance = new SpriteFontRepository();
	public static SpriteFontRepository getInstance(){
		return instance;
	}
	

}
