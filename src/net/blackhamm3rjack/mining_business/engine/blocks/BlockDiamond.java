package net.blackhamm3rjack.mining_business.engine.blocks;

import java.awt.Point;

import net.blackhamm3rjack.mining_business.annotations.Versioning;
import net.blackhamm3rjack.mining_business.engine.minerals.Mineral;
import net.blackhamm3rjack.mining_business.engine.minerals.MineralDiamond;

/**
 * Diamond block
 * 
 * @author lucac
 *
 */
@Versioning(working = true)
public class BlockDiamond extends Block {
	/**
	 * Create a new diamond block
	 * 
	 * @param position
	 *            The block position
	 */
	public BlockDiamond(Point position) {
		super(position, Mineral.getMineral(MineralDiamond.ID), Background.STONE);
	}
}
