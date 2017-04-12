package com.cad.ui.sprites_repository;


import com.cad.motor2d.sprites.Sprite;
import com.cad.motor2d.sprites.SpriteSheet;

public class ShipsXX1 implements SpriteBateauRepository{

	private static ShipsXX1 instance = new ShipsXX1();


	private SpriteSheet p;
	private Sprite bateau2;
	private Sprite bateau3;
	private Sprite bateau4;
	private Sprite bateau5;

	private ShipsXX1() {
		p = new SpriteSheet("./assets/spritesheets/ships.png",
				"./assets/spritesheets/ships.pack");
		bateau2 = p.getSprite("ship2");
		bateau3 = p.getSprite("ship3");
		bateau4 = p.getSprite("ship4");
		bateau5 = p.getSprite("ship5");
	}

	public static ShipsXX1 getInstance(){
		return instance;
	}


	@Override
	public Sprite getBateau(int length) {
		switch (length) {
		case 1:
			return bateau2;
		case 2:
			return bateau2;
		case 3:
			return bateau3;
		case 4:
			return bateau4;
		case 5:
			return bateau5;
		default:
			return bateau2;
		}
	}

}
