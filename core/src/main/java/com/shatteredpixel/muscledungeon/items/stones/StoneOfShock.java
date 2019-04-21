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

package com.shatteredpixel.muscledungeon.items.stones;

import com.shatteredpixel.muscledungeon.Assets;
import com.shatteredpixel.muscledungeon.Dungeon;
import com.shatteredpixel.muscledungeon.actors.Actor;
import com.shatteredpixel.muscledungeon.actors.Char;
import com.shatteredpixel.muscledungeon.actors.buffs.Buff;
import com.shatteredpixel.muscledungeon.actors.buffs.Paralysis;
import com.shatteredpixel.muscledungeon.effects.CellEmitter;
import com.shatteredpixel.muscledungeon.effects.Lightning;
import com.shatteredpixel.muscledungeon.effects.particles.EnergyParticle;
import com.shatteredpixel.muscledungeon.effects.particles.SparkParticle;
import com.shatteredpixel.muscledungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.muscledungeon.utils.BArray;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class StoneOfShock extends Runestone {
	
	{
		image = ItemSpriteSheet.STONE_SHOCK;
	}
	
	@Override
	protected void activate(int cell) {
		
		Sample.INSTANCE.play( Assets.SND_LIGHTNING );
		
		ArrayList<Lightning.Arc> arcs = new ArrayList<>();
		int hits = 0;
		
		PathFinder.buildDistanceMap( cell, BArray.not( Dungeon.level.solid, null ), 2 );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE) {
				Char n = Actor.findChar(i);
				if (n != null) {
					arcs.add(new Lightning.Arc(cell, n.sprite.center()));
					Buff.prolong(n, Paralysis.class, 1f);
					hits++;
				}
			}
		}
		
		CellEmitter.center( cell ).burst( SparkParticle.FACTORY, 3 );
		
		if (hits > 0) {
			curUser.sprite.parent.addToFront( new Lightning( arcs, null ) );
			curUser.sprite.centerEmitter().burst(EnergyParticle.FACTORY, 10);
			curUser.belongings.charge(1f + hits);
		}
	
	}
}
