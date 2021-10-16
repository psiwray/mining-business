package net.blackhamm3rjack.mining_business.engine.world.storage;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;

/**
 * Limited minerals storage
 * 
 * @author lucac
 *
 */
@Versioning(major = 2, working = true)
public class Storage {
	private StorageStack[][] units;
	private int width;
	private int height;

	private static final byte EMPTY = -1;

	public Storage(int width, int height, int maxAmount) {
		assert width > 0 && width < 64;
		assert height > 0 && height < 64;
		assert maxAmount > 0;

		this.width = width;
		this.height = height;
		this.units = new StorageStack[width][height];

		// Setup the stacks
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				units[x][y] = new StorageStack(EMPTY, 0, maxAmount);
	}

	public StorageStack getUnit(int x, int y) {
		assert x >= 0 && x < width;
		assert y >= 0 && y < height;

		if (units[x][y].getId() == EMPTY)
			// Empty cell
			return null;
		else
			// Return the stack
			return units[x][y];
	}

	/**
	 * Place a new stack from the current cell position
	 * 
	 * @param x
	 *            The cell position X
	 * @param y
	 *            The cell position Y
	 * @param mineral
	 *            The corresponding mineral
	 * @param forced
	 *            If forced, remove anyway
	 */
	public void place(int x, int y, Mineral mineral, boolean forced) {
		assert x >= 0 && x < width;
		assert y >= 0 && y < height;

		if (units[x][y].getId() == EMPTY)
			units[x][y].setId(mineral.getId());
		else if (forced)
			units[x][y].setId(mineral.getId());
	}

	/**
	 * Remove a stack from the current cell position
	 * 
	 * @param x
	 *            The cell position X
	 * @param y
	 *            The cell position Y
	 */
	public void remove(int x, int y) {
		assert x >= 0 && x < width;
		assert y >= 0 && y < height;

		units[x][y].setId(EMPTY);
	}
}
