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

package com.shatteredpixel.muscledungeon.items.potions;

import com.shatteredpixel.muscledungeon.actors.Char;
import com.shatteredpixel.muscledungeon.actors.buffs.Bleeding;
import com.shatteredpixel.muscledungeon.actors.buffs.Buff;
import com.shatteredpixel.muscledungeon.actors.buffs.Cripple;
import com.shatteredpixel.muscledungeon.actors.buffs.Healing;
import com.shatteredpixel.muscledungeon.actors.buffs.Poison;
import com.shatteredpixel.muscledungeon.actors.buffs.Weakness;
import com.shatteredpixel.muscledungeon.actors.hero.Hero;
import com.shatteredpixel.muscledungeon.messages.Messages;
import com.shatteredpixel.muscledungeon.utils.GLog;

public class PotionOfHealing extends Potion {

	{
		initials = 3;

		bones = true;
	}
	
	@Override
	public void apply( Hero hero ) {
		setKnown();
		//starts out healing 30 hp, equalizes with hero health total at level 11
		Buff.affect( hero, Healing.class ).setHeal((int)(0.8f*hero.HT + 14), 0.25f, 0);
		cure( hero );
		GLog.p( Messages.get(this, "heal") );
	}
	
	public static void cure( Char ch ) {
		Buff.detach( ch, Poison.class );
		Buff.detach( ch, Cripple.class );
		Buff.detach( ch, Weakness.class );
		Buff.detach( ch, Bleeding.class );
		
	}

	@Override
	public int price() {
		return isKnown() ? 30 * quantity : super.price();
	}
}
