package net.blackhamm3rjack.mining_business.window;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Game state manager
 * 
 * @author Luca
 *
 */
@Versioning()
public class GameState {
	private GamePanel instance;

	private boolean running;
	private boolean started;
	private boolean paused;

	public GameState(GamePanel instance) {
		this.instance = instance;
		this.running = false;
		this.started = false;
		this.paused = false;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;

		if (running && !started) {
			// If wasn't started before, spawn a new thread
			instance.setThread(new Thread(instance, "looper"));
			instance.getThread().start();

			started = true;
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
