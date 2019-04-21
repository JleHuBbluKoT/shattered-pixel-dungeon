/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.muscledungeon.plants;

import com.shatteredpixel.muscledungeon.Dungeon;
import com.shatteredpixel.muscledungeon.actors.Char;
import com.shatteredpixel.muscledungeon.actors.blobs.Blob;
import com.shatteredpixel.muscledungeon.actors.blobs.Fire;
import com.shatteredpixel.muscledungeon.actors.buffs.Buff;
import com.shatteredpixel.muscledungeon.actors.buffs.FireImbue;
import com.shatteredpixel.muscledungeon.actors.hero.Hero;
import com.shatteredpixel.muscledungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.muscledungeon.effects.CellEmitter;
import com.shatteredpixel.muscledungeon.effects.particles.FlameParticle;
import com.shatteredpixel.muscledungeon.scenes.GameScene;
import com.shatteredpixel.muscledungeon.sprites.ItemSpriteSheet;

public class Firebloom extends Plant {
	
	{
		image = 1;
	}
	
	@Override
	public void activate( Char ch ) {
		
		if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
			Buff.affect(ch, FireImbue.class).set(15f);
		}
		
		GameScene.add( Blob.seed( pos, 2, Fire.class ) );
		
		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).burst( FlameParticle.FACTORY, 5 );
		}
	}
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_FIREBLOOM;

			plantClass = Firebloom.class;
		}
	}
}
