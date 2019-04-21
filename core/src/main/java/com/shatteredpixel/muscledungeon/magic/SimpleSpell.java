package com.shatteredpixel.muscledungeon.magic;
import com.shatteredpixel.muscledungeon.actors.hero.Hero;
import com.shatteredpixel.muscledungeon.ui.MagicIndicator;

public class SimpleSpell extends Magic {

    @Override
    public void doCast(Hero hero){

    }

    @Override
    public int icon() {
        return MagicIndicator.INVISIBLE;
    }
}
