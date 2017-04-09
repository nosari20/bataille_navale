package com.cad.ui.sprites_repository;

import com.cad.motor2d.sprites.Sprite;
import com.cad.motor2d.sprites.SpriteSheet;

public class ShipsXX1 implements SpriteBateauRepository{
	
	private static ShipsXX1 instance = new ShipsXX1();
	
	
	private SpriteSheet p;	
	private Sprite bateau1;
	private Sprite bateau3;
	private Sprite bateau4;
	private Sprite bateau5;

	private ShipsXX1() {
		p = new SpriteSheet("./assets/spritesheets/ships.png", "./assets/spritesheets/ships.pack");	
		bateau1 = p.getSprite("ship2");
		bateau3 = p.getSprite("ship3");
		bateau4 = p.getSprite("ship4");
		bateau5 = p.getSprite("ship5");
	}
	
	public static ShipsXX1 getInstance(){
		return instance;
	}
	
	
	public Sprite getBateau1(){
		return bateau1;
	}
	
	public Sprite getBateau3(){
		return bateau3;
	}
	
	public Sprite getBateau4(){
		return bateau4;
	}
	
	public Sprite getBateau5(){
		return bateau5;
	}

}
