package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralPlatinum;

/**
 * Platinum block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockPlatinum extends Block {
	/**
	 * Create a new platinum block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockPlatinum(Point position) {
		super(position, Mineral.getMineral(MineralPlatinum.ID), Background.STONE);
	}
}
