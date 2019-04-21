package com.shatteredpixel.shatteredpixeldungeon.magic;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.ui.MagicIndicator;

public class SimpleSpell extends Magic {

    @Override
    public void doCast(Hero hero){

    }

    @Override
    public int icon() {
        return MagicIndicator.INVISIBLE;
    }
}
