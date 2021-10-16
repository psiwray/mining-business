package net.blackhamm3rjack.mining_business.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.Entity;

/**
 * Entity debugger that shows the outline and the name of every entity attached
 * to it
 * 
 * @author lucac
 *
 */
@Versioning(minor = 1, patch = 2, working = true)
public class EntityDebugger {
	private static ArrayList<Entity> entities;
	private static Font font;

	private static boolean showOutline;
	private static boolean showName;
	private static boolean enabled;

	static {
		EntityDebugger.entities = new ArrayList<>();
		EntityDebugger.font = new Font("Consolas", Font.PLAIN, 10);

		EntityDebugger.showOutline = true;
		EntityDebugger.showName = false;
		EntityDebugger.enabled = false;
	}

	public static boolean isShowOutline() {
		return showOutline;
	}

	public static void shouldShowOutline(boolean showOutline) {
		EntityDebugger.showOutline = showOutline;
	}

	public static boolean isShowName() {
		return showName;
	}

	public static void shouldShowName(boolean showName) {
		EntityDebugger.showName = showName;
	}

	/**
	 * Attach a group of entities
	 * 
	 * @param entities
	 *            The group of entities
	 */
	public static void attach(Entity... entities) {
		for (Entity entity : entities)
			EntityDebugger.entities.add(entity);
	}

	/**
	 * Detach a group of entities
	 * 
	 * @param entities
	 *            The group of entities
	 */
	public static void detach(Entity... entities) {
		for (Entity entity : entities)
			EntityDebugger.entities.remove(entity);
	}

	/** Detach all the entities from the list */
	public void detachAll() {
		entities.clear();
	}

	public static boolean isEnabled() {
		return enabled;
	}

	public static void setEnabled(boolean enabled) {
		EntityDebugger.enabled = enabled;
	}

	/**
	 * Show the graphics around the entities
	 * 
	 * @param graphics
	 *            The graphics object
	 */
	public static void globalRender(Graphics2D graphics) {
		if (!enabled)
			return;

		for (Entity entity : entities) {
			// Show the entity outline in red
			if (showOutline) {
				graphics.setColor(Color.red);
				graphics.drawRect((int) (entity.getPositionX() - 1), (int) (entity.getPositionY() - 1),
						(int) (entity.getWidth() + 1), (int) (entity.getHeight() + 1));
			}

			// Show the entity name in white
			if (showName) {
				graphics.setColor(Color.white);
				graphics.setFont(font);
				graphics.drawString(entity.getName() != null ? entity.getName() : "", (int) entity.getPositionX(),
						(int) (entity.getPositionY() - 4));
			}
		}
	}
}
