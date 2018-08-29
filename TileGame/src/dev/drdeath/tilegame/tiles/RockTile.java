package dev.drdeath.tilegame.tiles;

import java.awt.image.BufferedImage;

import dev.drdeath.tilegame.gfx.Assets;

public class RockTile extends Tile{

	public RockTile(int id) {
		super(Assets.stone, id);
	}
	
	@Override
	public boolean isSoild() {
		return true;
	}

}
