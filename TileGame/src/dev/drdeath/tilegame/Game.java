package dev.drdeath.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.drdeath.tilegame.display.Display;
import dev.drdeath.tilegame.gfx.Assets;
import dev.drdeath.tilegame.gfx.GameCamera;
import dev.drdeath.tilegame.input.KeyManger;
import dev.drdeath.tilegame.states.GameState;
import dev.drdeath.tilegame.states.MenuState;
import dev.drdeath.tilegame.states.State;

public class Game implements Runnable{
	private Display display;
	private int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	//States
	private State gameState;
	private State menuState;
	
	//input
	private KeyManger keyManger;

	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	//Camera
	private GameCamera gameCamera;

	private void init(){
		keyManger = new KeyManger();
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManger);
		Assets.init();
		
		gameCamera = new GameCamera(this, 	0, 0);
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
	}
	

	private void tick(){
		keyManger.tick();
		if(State.getState() != null) 
			State.getState().tick();

	}

	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!

		if(State.getState() != null)
			State.getState().render(g);

		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	

	public KeyManger getKeyManger() {
		return keyManger;
	}
		
	public GameCamera getGameCamera() {
		return gameCamera;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void run(){

		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}

			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();

	}

	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
