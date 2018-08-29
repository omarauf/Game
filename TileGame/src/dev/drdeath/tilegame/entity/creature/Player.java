package dev.drdeath.tilegame.entity.creature;

import java.awt.Graphics;

import dev.drdeath.tilegame.Game;
import dev.drdeath.tilegame.gfx.Assets;

public class Player extends Creature{
	

	public Player(Game game, float x, float y) {
		super(game, x, y, Creature.DEFALUT_CREATURE_WIDTH, Creature.DEFALUT_CREATURE_HEIGHT);

	}

	@Override
	public void tick() {
		getInput();	
		move();
		game.getGameCamera().centerOnEntity(this);
	}
	
	public void getInput() {
		yMove = 0;
		xMove = 0;
		
		if(game.getKeyManger().up)
			yMove = -speed;
		if(game.getKeyManger().down)
			yMove = speed;
		if(game.getKeyManger().right)
			xMove = speed;
		if(game.getKeyManger().left)
			xMove = -speed;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) (x - game.getGameCamera().getxOffset())
								 , (int) (y - game.getGameCamera().getyOffset()), width, height, null);
		
	}

}
