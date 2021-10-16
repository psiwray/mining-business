package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralDirt;

/**
 * Dirt block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockDirt extends Block {
	/**
	 * Create a new dirt block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockDirt(Point position, Background background) {
		super(position, Mineral.getMineral(MineralDirt.ID), background);
	}
}
