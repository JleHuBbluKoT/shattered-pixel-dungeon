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

package com.shatteredpixel.muscledungeon;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.shatteredpixel.muscledungeon.scenes.PixelScene;
import com.shatteredpixel.muscledungeon.scenes.WelcomeScene;
import com.watabou.noosa.Game;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.DeviceCompat;

import javax.microedition.khronos.opengles.GL10;

public class PixelDungeonsAndShadowDragons extends Game {
	
	//variable constants for specific older versions of shattered, used for data conversion
	//versions older than v0.6.2e are no longer supported, and data from them is ignored
	public static final int v0_6_2e = 229;
	public static final int v0_6_3b = 245;
	public static final int v0_6_4a = 252;
	public static final int v0_6_5c = 264;
	
	public static final int v0_7_0c = 311;
	public static final int v0_7_1d = 323;
	public static final int v0_7_2  = 333;
	
	public PixelDungeonsAndShadowDragons() {
		super( WelcomeScene.class );
		
		//v0.6.3
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.missiles.Tomahawk.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.Tamahawk" );
		
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.missiles.darts.Dart.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.Dart" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.missiles.darts.IncendiaryDart.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.IncendiaryDart" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.missiles.darts.ParalyticDart.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.CurareDart" );
		
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.wands.WandOfCorrosion.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfVenom" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.actors.blobs.CorrosiveGas.class,
				"com.shatteredpixel.shatteredpixeldungeon.actors.blobs.VenomGas" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.actors.buffs.Corrosion.class,
				"com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Venom" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.levels.traps.CorrosionTrap.class,
				"com.shatteredpixel.shatteredpixeldungeon.levels.traps.VenomTrap" );
		
		//v0.6.4
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.bags.VelvetPouch.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.bags.SeedPouch" );
		
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.bags.MagicalHolster.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.bags.WandHolster" );
		
		//v0.6.5
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.stones.StoneOfAugmentation.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.Weightstone" );
		
		//v0.7.0
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.bombs.Bomb.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.Bomb" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.scrolls.ScrollOfRetribution.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfPsionicBlast" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.potions.elixirs.ElixirOfMight.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMight" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.spells.MagicalInfusion.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMagicalInfusion" );
		
		//v0.7.1
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.SpiritBow.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.Boomerang" );
		
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.melee.Gloves.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Knuckles" );
		
		//v0.7.2
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.stones.StoneOfDisarming.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfDetectCurse" );
		
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.enchantments.Elastic.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.curses.Elastic" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.enchantments.Elastic.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Dazzling" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.enchantments.Elastic.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Eldritch" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.enchantments.Grim.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Stunning" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.enchantments.Chilling.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Venomous" );
		com.watabou.utils.Bundle.addAlias(
				com.shatteredpixel.muscledungeon.items.weapon.enchantments.Precise.class,
				"com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Vorpal" );
	}
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);

		updateSystemUI();
		SPDSettings.landscape ( SPDSettings.landscape() );
		
		Music.INSTANCE.enable( SPDSettings.music() );
		Music.INSTANCE.volume( SPDSettings.musicVol()/10f );
		Sample.INSTANCE.enable( SPDSettings.soundFx() );
		Sample.INSTANCE.volume( SPDSettings.SFXVol()/10f );
		
		Music.setMuteListener();

		Sample.INSTANCE.load(
				Assets.SND_CLICK,
				Assets.SND_BADGE,
				Assets.SND_GOLD,

				Assets.SND_STEP,
				Assets.SND_WATER,
				Assets.SND_OPEN,
				Assets.SND_UNLOCK,
				Assets.SND_ITEM,
				Assets.SND_DEWDROP,
				Assets.SND_HIT,
				Assets.SND_MISS,

				Assets.SND_DESCEND,
				Assets.SND_EAT,
				Assets.SND_READ,
				Assets.SND_LULLABY,
				Assets.SND_DRINK,
				Assets.SND_SHATTER,
				Assets.SND_ZAP,
				Assets.SND_LIGHTNING,
				Assets.SND_LEVELUP,
				Assets.SND_DEATH,
				Assets.SND_CHALLENGE,
				Assets.SND_CURSED,
				Assets.SND_EVOKE,
				Assets.SND_TRAP,
				Assets.SND_TOMB,
				Assets.SND_ALERT,
				Assets.SND_MELD,
				Assets.SND_BOSS,
				Assets.SND_BLAST,
				Assets.SND_PLANT,
				Assets.SND_RAY,
				Assets.SND_BEACON,
				Assets.SND_TELEPORT,
				Assets.SND_CHARMS,
				Assets.SND_MASTERY,
				Assets.SND_PUFF,
				Assets.SND_ROCKS,
				Assets.SND_BURNING,
				Assets.SND_FALLING,
				Assets.SND_GHOST,
				Assets.SND_SECRET,
				Assets.SND_BONES,
				Assets.SND_BEE,
				Assets.SND_DEGRADE,
				Assets.SND_MIMIC );

		if (!SPDSettings.systemFont()) {
			RenderedText.setFont("pixelfont.ttf");
		} else {
			RenderedText.setFont( null );
		}
		
	}

	@Override
	public void onWindowFocusChanged( boolean hasFocus ) {
		super.onWindowFocusChanged( hasFocus );
		if (hasFocus) updateSystemUI();
	}

	@Override
	@SuppressWarnings("deprecation")
	public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
		super.onMultiWindowModeChanged(isInMultiWindowMode);
		updateSystemUI();
	}

	public static void switchNoFade(Class<? extends PixelScene> c){
		switchNoFade(c, null);
	}

	public static void switchNoFade(Class<? extends PixelScene> c, SceneChangeCallback callback) {
		PixelScene.noFade = true;
		switchScene( c, callback );
	}

	@Override
	public void onSurfaceChanged( GL10 gl, int width, int height ) {

		super.onSurfaceChanged( gl, width, height );

		updateDisplaySize();

	}

	public void updateDisplaySize(){
		boolean landscape = SPDSettings.landscape();
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			instance.setRequestedOrientation(landscape ?
					ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE :
					ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
		} else {
			instance.setRequestedOrientation(landscape ?
					ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
					ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		if (view.getMeasuredWidth() == 0 || view.getMeasuredHeight() == 0)
			return;

		dispWidth = view.getMeasuredWidth();
		dispHeight = view.getMeasuredHeight();

		float dispRatio = dispWidth / (float)dispHeight;

		float renderWidth = dispRatio > 1 ? PixelScene.MIN_WIDTH_L : PixelScene.MIN_WIDTH_P;
		float renderHeight = dispRatio > 1 ? PixelScene.MIN_HEIGHT_L : PixelScene.MIN_HEIGHT_P;

		//force power saver in this case as all devices must run at at least 2x scale.
		if (dispWidth < renderWidth*2 || dispHeight < renderHeight*2)
			SPDSettings.put( SPDSettings.KEY_POWER_SAVER, true );

		if (SPDSettings.powerSaver()){

			int maxZoom = (int)Math.min(dispWidth/renderWidth, dispHeight/renderHeight);

			renderWidth *= Math.max( 2, Math.round(1f + maxZoom*0.4f));
			renderHeight *= Math.max( 2, Math.round(1f + maxZoom*0.4f));

			if (dispRatio > renderWidth / renderHeight){
				renderWidth = renderHeight * dispRatio;
			} else {
				renderHeight = renderWidth / dispRatio;
			}

			final int finalW = Math.round(renderWidth);
			final int finalH = Math.round(renderHeight);
			if (finalW != width || finalH != height){

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						view.getHolder().setFixedSize(finalW, finalH);
					}
				});

			}
		} else {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					view.getHolder().setSizeFromLayout();
				}
			});
		}
	}

	public static void updateSystemUI() {

		boolean fullscreen = Build.VERSION.SDK_INT < Build.VERSION_CODES.N
								|| !instance.isInMultiWindowMode();

		if (fullscreen){
			instance.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		} else {
			instance.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		}

		if (DeviceCompat.supportsFullScreen()){
			if (fullscreen && SPDSettings.fullscreen()) {
				instance.getWindow().getDecorView().setSystemUiVisibility(
						View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
						View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
						View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
						View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );
			} else {
				instance.getWindow().getDecorView().setSystemUiVisibility(
						View.SYSTEM_UI_FLAG_LAYOUT_STABLE );
			}
		}

	}
	
}