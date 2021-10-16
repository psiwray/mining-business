package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralIron;

/**
 * Iron block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockIron extends Block {
	/**
	 * Create a new iron block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockIron(Point position) {
		super(position, Mineral.getMineral(MineralIron.ID), Background.STONE);
	}
}
