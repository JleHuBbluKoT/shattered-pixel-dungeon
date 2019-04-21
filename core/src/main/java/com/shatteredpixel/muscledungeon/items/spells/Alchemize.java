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

package com.shatteredpixel.muscledungeon.items.spells;

import com.shatteredpixel.muscledungeon.PixelDungeonsAndShadowDragons;
import com.shatteredpixel.muscledungeon.actors.hero.Hero;
import com.shatteredpixel.muscledungeon.items.potions.AlchemicalCatalyst;
import com.shatteredpixel.muscledungeon.messages.Messages;
import com.shatteredpixel.muscledungeon.scenes.AlchemyScene;
import com.shatteredpixel.muscledungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.muscledungeon.utils.GLog;

public class Alchemize extends Spell implements AlchemyScene.AlchemyProvider {
	
	{
		image = ItemSpriteSheet.ALCHEMIZE;
	}
	
	@Override
	protected void onCast(Hero hero) {
		if (hero.visibleEnemies() > hero.mindVisionEnemies.size()) {
			GLog.i( Messages.get(this, "enemy_near") );
			return;
		}
		detach( curUser.belongings.backpack );
		updateQuickslot();
		AlchemyScene.setProvider(this);
		PixelDungeonsAndShadowDragons.switchScene(AlchemyScene.class);
	}
	
	@Override
	public int getEnergy() {
		return 0;
	}
	
	@Override
	public void spendEnergy(int reduction) {
		//do nothing
	}
	
	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((40 + 40) / 4f));
	}
	
	public static class Recipe extends com.shatteredpixel.muscledungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ArcaneCatalyst.class, AlchemicalCatalyst.class};
			inQuantity = new int[]{1, 1};
			
			cost = 6;
			
			output = Alchemize.class;
			outQuantity = 4;
		}
		
	}
}