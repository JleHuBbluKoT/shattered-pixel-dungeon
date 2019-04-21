package com.shatteredpixel.muscledungeon.ui;


import com.shatteredpixel.muscledungeon.Assets;
import com.shatteredpixel.muscledungeon.Dungeon;
import com.shatteredpixel.muscledungeon.actors.Char;
import com.shatteredpixel.muscledungeon.magic.Magic;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.noosa.ui.Button;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MagicIndicator extends Component {


    public static final int NONE	= 63;


    public static final int MIND_VISION	= 0;
    public static final int LEVITATION	= 1;
    public static final int FIRE		= 2;
    public static final int POISON		= 3;
    public static final int PARALYSIS	= 4;
    public static final int HUNGER		= 5;
    public static final int STARVATION	= 6;
    public static final int SLOW		= 7;
    public static final int OOZE		= 8;
    public static final int AMOK		= 9;
    public static final int TERROR		= 10;
    public static final int ROOTS		= 11;
    public static final int INVISIBLE	= 12;
    public static final int SHADOWS		= 13;
    public static final int WEAKNESS	= 14;
    public static final int FROST		= 15;
    public static final int BLINDNESS	= 16;
    public static final int COMBO		= 17;
    public static final int FURY		= 18;
    public static final int HERB_HEALING= 19;
    public static final int ARMOR		= 20;
    public static final int HEART		= 21;
    public static final int LIGHT		= 22;
    public static final int CRIPPLE		= 23;
    public static final int BARKSKIN	= 24;
    public static final int IMMUNITY	= 25;
    public static final int BLEEDING	= 26;
    public static final int MARK		= 27;
    public static final int DEFERRED	= 28;
    public static final int DROWSY      = 29;
    public static final int MAGIC_SLEEP = 30;
    public static final int THORNS      = 31;
    public static final int FORESIGHT   = 32;
    public static final int VERTIGO     = 33;
    public static final int RECHARGING 	= 34;
    public static final int LOCKED_FLOOR= 35;
    public static final int CORRUPT     = 36;
    public static final int BLESS       = 37;
    public static final int RAGE		= 38;
    public static final int SACRIFICE	= 39;
    public static final int BERSERK     = 40;
    public static final int MOMENTUM    = 41;
    public static final int PREPARATION = 42;
    public static final int WELL_FED    = 43;
    public static final int HEALING     = 44;
    public static final int WEAPON      = 45;

    public static final int SIZE	= 7;

    private static MagicIndicator heroInstance;

    private SmartTexture texture;
    private TextureFilm film;

    private LinkedHashMap<Magic, MagicIcon> magicIcons = new LinkedHashMap<>();
    private boolean needsRefresh;
    private Char ch;

    public MagicIndicator( Char ch ) {
        super();

        this.ch = ch;
        if (ch == Dungeon.hero) {
            heroInstance = this;
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        if (this == heroInstance) {
            heroInstance = null;
        }
    }

    @Override
    protected void createChildren() {
        texture = TextureCache.get( Assets.BUFFS_SMALL );
        film = new TextureFilm( texture, SIZE, SIZE );
    }

    @Override
    public synchronized void update() {
        super.update();
        if (needsRefresh){
            needsRefresh = false;
            layout();
        }
    }

    @Override
    protected void layout() {

        ArrayList<Magic> newMagics = new ArrayList<>();
        for (Magic magic : ch.magics()) {
            if (magic.icon() != NONE) {
                newMagics.add(magic);
            }
        }

        //remove any icons no longer present
        for (Magic magic : magicIcons.keySet().toArray(new Magic[0])){
            if (!newMagics.contains(magic)){
                Image icon = magicIcons.get( magic ).icon;
                icon.origin.set( SIZE / 2 );
                add( icon );
                add( new AlphaTweener( icon, 0, 0.6f ) {
                    @Override
                    protected void updateValues( float progress ) {
                        super.updateValues( progress );
                        image.scale.set( 1 + 5 * progress );
                    }

                    @Override
                    protected void onComplete() { image.killAndErase(); }
                } );

                magicIcons.get( magic ).destroy();
                remove(magicIcons.get( magic ));
                magicIcons.remove( magic );
            }
        }

        //add new icons
        for (Magic magic : newMagics) {
            if (!magicIcons.containsKey(magic)) {
                MagicIcon icon = new MagicIcon( magic );
                add(icon);
                magicIcons.put( magic, icon );
            }
        }

        //layout
        int pos = 0;
        for (MagicIcon icon : magicIcons.values()){
            icon.updateIcon();
            icon.setRect(x + pos * (SIZE + 2), y , 9, 12);
            pos++;
        }
    }

    private class MagicIcon extends Button {

        private Magic magic;

        public Image icon;

        public MagicIcon( Magic magic ){
            super();
            this.magic = magic;

            icon = new Image( texture );
            icon.frame( film.get( magic.icon() ) );
            add( icon );
        }

        public void updateIcon(){
            icon.frame( film.get( magic.icon() ) );
        }

        @Override
        protected void layout() {
            super.layout();
            icon.x = this.x+1;
            icon.y = this.y+2;
        }

        @Override
        protected void onClick() {
            if (magic.icon() != NONE)
                magic.doCast(Dungeon.hero);
        }
    }

    public static void refreshHero() {
        if (heroInstance != null) {
            heroInstance.needsRefresh = true;
        }
    }
}

