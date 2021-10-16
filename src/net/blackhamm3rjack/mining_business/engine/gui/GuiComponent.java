package net.blackhamm3rjack.mining_business.engine.gui;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.Entity;
import net.blackhamm3rjack.mining_business.engine.interfaces.Renderable;
import net.blackhamm3rjack.mining_business.window.GamePanel;
import net.blackhamm3rjack.mining_business.window.input.buttons.ButtonInput;
import net.blackhamm3rjack.mining_business.window.input.keys.KeyInput;

/**
 * Super class for every GUI component
 * 
 * @author lucac
 *
 */
@Versioning(patch = 2, working = true)
public class GuiComponent extends Entity implements Renderable {
	/** The game panel reference */
	protected static GamePanel gamePanel;
	/** The key input handle */
	protected static KeyInput keyInput;
	/** The button input handle */
	protected static ButtonInput buttonInput;

	/**
	 * Set the game panel reference
	 * 
	 * @param gamePanel
	 *            The game panel reference
	 */
	public static void setGamePanel(GamePanel gamePanel) {
		GuiComponent.gamePanel = gamePanel;
	}

	/**
	 * Set the key input for the GUI component class
	 * 
	 * @param keyInput
	 *            The key input
	 */
	public static void setKeyInput(KeyInput keyInput) {
		GuiComponent.keyInput = keyInput;
	}

	/**
	 * Set the button input for the GUI component class
	 * 
	 * @param buttonInput
	 *            The button input
	 */
	public static void setButtonInput(ButtonInput buttonInput) {
		GuiComponent.buttonInput = buttonInput;
	}

	/**
	 * Create a new GUI component
	 * 
	 * @param bounds
	 *            The component bounds
	 * @param name
	 *            The component internal name
	 * @param visible
	 *            The component visibility state
	 */
	public GuiComponent(Rectangle2D.Double bounds, String name, boolean visible) {
		super(bounds, name, visible);
	}

	/**
	 * Create a new GUI component
	 * 
	 * @param bounds
	 *            The component bounds
	 * @param name
	 *            The component internal name
	 */
	public GuiComponent(Rectangle2D.Double bounds, String name) {
		super(bounds, name);
	}

	@Override
	public void update(long deltaTime) {
		// Empty body, no behavior
	}

	@Override
	public void render(Graphics2D graphics) {
		// Empty body, no behavior
	}
}
