package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralAluminium;

/**
 * Aluminium block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockAluminium extends Block {
	/**
	 * Create a new aluminium block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockAluminium(Point position) {
		super(position, Mineral.getMineral(MineralAluminium.ID), Background.STONE);
	}
}
