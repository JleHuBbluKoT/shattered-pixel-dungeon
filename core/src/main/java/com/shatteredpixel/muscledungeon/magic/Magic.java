package com.shatteredpixel.muscledungeon.magic;

import com.shatteredpixel.muscledungeon.actors.Actor;
import com.shatteredpixel.muscledungeon.actors.Char;
import com.shatteredpixel.muscledungeon.actors.hero.Hero;
import com.shatteredpixel.muscledungeon.ui.MagicIndicator;
import com.shatteredpixel.muscledungeon.PixelDungeonsAndShadowDragons;

public abstract class Magic extends Actor {

    public Char target;

    public boolean attachTo( Char target ) {

        if (target.isImmune( getClass() )) {
            return false;
        }

        this.target = target;
        target.addMagic( this );

        if (target.magics().contains(this)){
            if (target.sprite != null);
            return true;
        } else {
            this.target = null;
            return false;
        }
    }

    public void detach() {
        if (target.sprite != null);
        target.removeMagic( this );
    }

    @Override
    public boolean act() {
        diactivate();
        return true;
    }

    public int icon() {
        return MagicIndicator.NONE;
    }

    //creates a fresh instance of the buff and attaches that, this allows duplication.
    public static<T extends Magic> T append( Char target, Class<T> magicClass ) {
        try {
            T magic = magicClass.newInstance();
            magic.attachTo( target );
            return magic;
        } catch (Exception e) {
            PixelDungeonsAndShadowDragons.reportException(e);
            return null;
        }
    }


    //same as append, but prevents duplication.
    public static<T extends Magic> T affect( Char target, Class<T> magicClass ) {
        T magic = target.magic( magicClass );
        if (magic != null) {
            return magic;
        } else {
            return append( target, magicClass );
        }
    }


    public static void detach( Magic magic ) {
        if (magic != null) {
            magic.detach();
        }
    }
    public static void detach( Char target, Class<? extends Magic> cl ) {
        detach( target.magic( cl ) );
    }



    public abstract void doCast(Hero hero);
}




