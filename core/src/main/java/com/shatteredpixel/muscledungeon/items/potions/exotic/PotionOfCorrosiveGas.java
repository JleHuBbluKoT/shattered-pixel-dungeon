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

package com.shatteredpixel.muscledungeon.items.potions.exotic;

import com.shatteredpixel.muscledungeon.Assets;
import com.shatteredpixel.muscledungeon.Dungeon;
import com.shatteredpixel.muscledungeon.actors.blobs.Blob;
import com.shatteredpixel.muscledungeon.actors.blobs.CorrosiveGas;
import com.shatteredpixel.muscledungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;

public class PotionOfCorrosiveGas extends ExoticPotion {
	
	{
		initials = 11;
	}
	
	@Override
	public void shatter( int cell ) {
		
		if (Dungeon.level.heroFOV[cell]) {
			setKnown();
			
			splash( cell );
			Sample.INSTANCE.play( Assets.SND_SHATTER );
		}
		
		GameScene.add( Blob.seed( cell, 200, CorrosiveGas.class ).setStrength( 1 + Dungeon.depth/5));
	}
}