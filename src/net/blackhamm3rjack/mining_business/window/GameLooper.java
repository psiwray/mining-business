package net.blackhamm3rjack.mining_business.window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.Camera;
import net.blackhamm3rjack.mining_business.engine.SplashScreen;
import net.blackhamm3rjack.mining_business.engine.blocks.Block;
import net.blackhamm3rjack.mining_business.engine.events.BlockDestroyEvent;
import net.blackhamm3rjack.mining_business.engine.events.EventDispatcher;
import net.blackhamm3rjack.mining_business.engine.events.listeners.BlockDestroyListener;
import net.blackhamm3rjack.mining_business.engine.gui.GuiButton;
import net.blackhamm3rjack.mining_business.engine.gui.GuiComponent;
import net.blackhamm3rjack.mining_business.engine.gui.GuiHelper;
import net.blackhamm3rjack.mining_business.engine.interfaces.Renderable;
import net.blackhamm3rjack.mining_business.engine.interfaces.Updatable;
import net.blackhamm3rjack.mining_business.engine.world.Player;
import net.blackhamm3rjack.mining_business.engine.world.World;
import net.blackhamm3rjack.mining_business.engine.world.settings.EasySettings;
import net.blackhamm3rjack.mining_business.utils.EntityDebugger;
import net.blackhamm3rjack.mining_business.utils.Logger;
import net.blackhamm3rjack.mining_business.utils.Logger.Tag;
import net.blackhamm3rjack.mining_business.utils.io.ChunkInputStream;
import net.blackhamm3rjack.mining_business.utils.io.ChunkOutputStream;
import net.blackhamm3rjack.mining_business.window.input.PointerManager;
import net.blackhamm3rjack.mining_business.window.input.buttons.ButtonInput;
import net.blackhamm3rjack.mining_business.window.input.keys.KeyAction;
import net.blackhamm3rjack.mining_business.window.input.keys.KeyGroup;
import net.blackhamm3rjack.mining_business.window.input.keys.KeyInput;

/**
 * Game loop manager
 * 
 * @author Luca
 *
 */
@Versioning(major = 3, minor = 112, working = true)
public class GameLooper implements Renderable, Updatable {
	private GamePanel instance;
	private KeyInput keyInput;
	private ButtonInput buttonInput;

	private World world;
	private Player player;

	private GuiButton button1;
	private GuiButton button2;

	public GameLooper(GamePanel instance) {
		this.keyInput = new KeyInput();
		this.buttonInput = new ButtonInput();
		this.instance = instance;

		// Attach the game panel to the mouse motion listener and set the key &
		// button input objects to the GUI component class
		PointerManager.attach(instance);
		GuiComponent.setGamePanel(instance);
		GuiComponent.setKeyInput(keyInput);
		GuiComponent.setButtonInput(buttonInput);

		this.world = new World("_", player, new EasySettings());
		this.player = new Player(new Point2D.Double(310, 0), "player", world, this);

		// Add an offset to the camera to center the player
		Camera.setPositionX(0);
		Camera.setPositionY(-215);

		EventDispatcher.register(new BlockDestroyListener("_") {
			@Override
			public void onBlockDestroy(BlockDestroyEvent event) {
				Block block = event.getBlock();
				World world = event.getWorld();

				Logger.print(Tag.DEBUG, GameLooper.class, "Block destroyed @[%d, %d] in world \"%s\"", block.getBlockPositionX(), block.getBlockPositionY(), world.getName());
			}
		});

		KeyGroup groupIO = new KeyGroup(false, true, false);
		groupIO.setEnabled(true);
		groupIO.addTriggers(KeyEvent.VK_S, KeyEvent.VK_L);
		groupIO.addAction(new KeyAction() {
			@Override
			public void trigger(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_S:
					// Save the world (output operation)
					try (ChunkOutputStream output = new ChunkOutputStream(new FileOutputStream("res/games/world_test.bin"), false)) {
						output.writeChunk(world.getChunk(new Point(0, 0)));
						output.writeChunk(world.getChunk(new Point(0, 1)));
						output.writeChunk(world.getChunk(new Point(0, 2)));
						output.writeChunk(world.getChunk(new Point(1, 0)));
						output.writeChunk(world.getChunk(new Point(1, 1)));
						output.writeChunk(world.getChunk(new Point(1, 2)));
						output.writeChunk(world.getChunk(new Point(2, 0)));
						output.writeChunk(world.getChunk(new Point(2, 1)));
						output.writeChunk(world.getChunk(new Point(2, 2)));

						Logger.print(Tag.DEBUG, GameLooper.class, "World saved");
					} catch (IOException ex) {
						Logger.print(Tag.ERROR, GameLooper.class, "Failed to save world: " + ex.getMessage());
					}

					break;
				case KeyEvent.VK_L:
					// Load the world (input operation)
					try (ChunkInputStream input = new ChunkInputStream(new FileInputStream("res/games/world_test.bin"), false)) {
						world.setChunk(input.readChunk());
						world.setChunk(input.readChunk());
						world.setChunk(input.readChunk());
						world.setChunk(input.readChunk());
						world.setChunk(input.readChunk());
						world.setChunk(input.readChunk());
						world.setChunk(input.readChunk());
						world.setChunk(input.readChunk());
						world.setChunk(input.readChunk());

						Logger.print(Tag.DEBUG, GameLooper.class, "World loaded");
					} catch (IOException ex) {
						Logger.print(Tag.ERROR, GameLooper.class, "Failed to load world: " + ex.getMessage());
					}

					break;
				}
			}
		});

		keyInput.addGroup(groupIO);

		// Setup the entity debugger
		EntityDebugger.setEnabled(false);
		EntityDebugger.shouldShowName(true);
		EntityDebugger.shouldShowOutline(true);

		this.button1 = GuiHelper.newButton(new Point(100, 100), "Button 1", Color.white, new Color(200, 200, 200, 200), new Color(60, 60, 60, 60));
		this.button2 = GuiHelper.newButton(new Point(200, 100), "Button 2", Color.white, new Color(200, 200, 200, 200), new Color(60, 60, 60, 60));

		// Hide the splash screen once loaded or the default delay time has
		// passed
		SplashScreen.setLoaded(true);
	}

	@Override
	public void update(long deltaTime) {
		if (SplashScreen.isVisible())
			SplashScreen.update(deltaTime);
		else {
			world.update(deltaTime);
			player.update(deltaTime);
		}
	}

	@Override
	public void render(Graphics2D graphics) {
		// Draw the splash screen if needed
		if (SplashScreen.isVisible())
			SplashScreen.render(graphics);
		else {
			graphics.setColor(Color.cyan);
			graphics.fillRect(0, 0, instance.getWidth(), instance.getHeight());

			world.render(graphics);
			player.render(graphics);

			button1.render(graphics);
			button2.render(graphics);

			EntityDebugger.globalRender(graphics);
		}
	}

	public KeyInput getKeyInput() {
		return keyInput;
	}

	public ButtonInput getButtonInput() {
		return buttonInput;
	}
}
