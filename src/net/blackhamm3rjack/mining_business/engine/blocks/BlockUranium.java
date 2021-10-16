package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralUranium;

/**
 * Uranium block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockUranium extends Block {
	/**
	 * Create a new uranium block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockUranium(Point position) {
		super(position, Mineral.getMineral(MineralUranium.ID), Background.STONE);
	}
}
