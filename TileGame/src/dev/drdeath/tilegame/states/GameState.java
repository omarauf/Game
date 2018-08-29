package dev.drdeath.tilegame.states;

import java.awt.Graphics;

import dev.drdeath.tilegame.Game;
import dev.drdeath.tilegame.entity.creature.Player;
import dev.drdeath.tilegame.gfx.Assets;
import dev.drdeath.tilegame.tiles.Tile;
import dev.drdeath.tilegame.worlds.World;

public class GameState extends State{
	
	private Player player;
	private World world;
	
	public GameState(Game game){
		super(game);
		player = new Player(game, 100,10);
		world = new World(game, "res/worlds/world1.txt");
	}

	@Override
	public void tick() {
		world.tick();
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}

}
