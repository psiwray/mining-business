package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralTitanium;

/**
 * Titanium block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockTitanium extends Block {
	/**
	 * Create a new titanium block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockTitanium(Point position) {
		super(position, Mineral.getMineral(MineralTitanium.ID), Background.STONE);
	}
}
