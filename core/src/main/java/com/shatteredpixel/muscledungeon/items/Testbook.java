package com.shatteredpixel.muscledungeon.items;

import com.shatteredpixel.muscledungeon.magic.Magic;
import com.shatteredpixel.muscledungeon.magic.SimpleSpell;
import com.shatteredpixel.muscledungeon.actors.hero.Hero;
import com.shatteredpixel.muscledungeon.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class Testbook extends Item {

    public static final float BUFF_DURATION = 30f;

    public static final String AC_DO = "DO";


    {

        image = ItemSpriteSheet.GOLD;

        defaultAction = AC_DO;

        stackable = true;

    }

    @Override
    public ArrayList<String> actions(Hero hero ) {
        ArrayList<String> actions = super.actions( hero );
        actions.add( AC_DO );
        return actions;
    }


    @Override
    public void execute( final Hero hero, String action ) {

        super.execute( hero, action );

        if (action.equals( AC_DO )) {

            Magic.append(hero, SimpleSpell.class);

        }
    }
}
