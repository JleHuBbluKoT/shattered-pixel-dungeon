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

package com.shatteredpixel.muscledungeon.levels.traps;

import com.shatteredpixel.muscledungeon.Dungeon;
import com.shatteredpixel.muscledungeon.actors.blobs.Blob;
import com.shatteredpixel.muscledungeon.actors.blobs.Fire;
import com.shatteredpixel.muscledungeon.effects.CellEmitter;
import com.shatteredpixel.muscledungeon.effects.particles.FlameParticle;
import com.shatteredpixel.muscledungeon.scenes.GameScene;
import com.watabou.utils.PathFinder;

public class BurningTrap extends Trap {

	{
		color = ORANGE;
		shape = DOTS;
	}

	@Override
	public void activate() {
		
		for( int i : PathFinder.NEIGHBOURS9) {
			if (!Dungeon.level.solid[pos + i]) {
				GameScene.add( Blob.seed( pos+i, 2, Fire.class ) );
				CellEmitter.get( pos+i ).burst( FlameParticle.FACTORY, 5 );
			}
		}

	}
}
