/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2017 Evan Debenham
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

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

public class ArmoryRoom extends Room {

	public void paint( Level level, Room room ) {
		
		Painter.fill( level, room, Terrain.WALL );
		Painter.fill( level, room, 1, Terrain.EMPTY );
		
		Room.Door entrance = room.entrance();
		Point statue = null;
		if (entrance.x == room.left) {
			statue = new Point( room.right-1, Random.Int( 2 ) == 0 ? room.top+1 : room.bottom-1 );
		} else if (entrance.x == room.right) {
			statue = new Point( room.left+1, Random.Int( 2 ) == 0 ? room.top+1 : room.bottom-1 );
		} else if (entrance.y == room.top) {
			statue = new Point( Random.Int( 2 ) == 0 ? room.left+1 : room.right-1, room.bottom-1 );
		} else if (entrance.y == room.bottom) {
			statue = new Point( Random.Int( 2 ) == 0 ? room.left+1 : room.right-1, room.top+1 );
		}
		if (statue != null) {
			Painter.set( level, statue, Terrain.STATUE );
		}
		
		int n = Random.IntRange( 1, 2 );
		for (int i=0; i < n; i++) {
			int pos;
			do {
				pos = level.pointToCell(room.random());
			} while (level.map[pos] != Terrain.EMPTY || level.heaps.get( pos ) != null);
			level.drop( prize( level ), pos );
		}
		
		entrance.set( Room.Door.Type.LOCKED );
		level.addItemToSpawn( new IronKey( Dungeon.depth ) );
	}
	
	private static Item prize( Level level ) {
		return Random.Int( 6 ) == 0 ?
				new Bomb().random() :
				Generator.random( Random.oneOf(
						Generator.Category.ARMOR,
						Generator.Category.WEAPON
				) );
	}
}