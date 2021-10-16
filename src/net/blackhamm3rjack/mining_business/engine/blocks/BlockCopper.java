package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralCopper;

/**
 * Copper block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockCopper extends Block {
	/**
	 * Create a new copper block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockCopper(Point position) {
		super(position, Mineral.getMineral(MineralCopper.ID), Background.STONE);
	}
}
