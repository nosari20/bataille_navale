package com.cad.ui.sprites_repository;

import com.cad.motor2d.sprites.Sprite;
import com.cad.motor2d.sprites.SpriteSheetAtlas;

public class ShipsXIX implements SpriteBateauRepository{

    private static ShipsXIX instance = new ShipsXIX();


    private SpriteSheetAtlas p;
    private Sprite bateau2;
    private Sprite bateau3;
    private Sprite bateau4;
    private Sprite bateau5;

    private ShipsXIX() {
        p = new SpriteSheetAtlas("./assets/spritesheets/shipsXIX.png",
                "./assets/spritesheets/ships.pack");
        bateau2 = p.getSprite("ship2");
        bateau3 = p.getSprite("ship3");
        bateau4 = p.getSprite("ship4");
        bateau5 = p.getSprite("ship5");
    }

    public static ShipsXIX getInstance(){
        return instance;
    }


    @Override
    public Sprite getBateau(int length, boolean b) {
        Sprite p = bateau2;
        switch (length) {
            case 1:
                p =  bateau2;
                break;
            case 2:
                p = bateau2;
                break;
            case 3:
                p =  bateau3;
                break;
            case 4:
                p =  bateau4;
            case 5:
                p = bateau5;
        }

        if(b){
            return p.rotate(90);
        }else{
            return p;
        }
    }

}
